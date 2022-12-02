/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import DomainModels.ChucVuLG;
import DomainModels.TaiKhoan;
import Utilities.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author trxua
 */
public class LoginRepository {

public TaiKhoan getUserByUserName(String tk, String mk) throws SQLException {
        TaiKhoan user = new TaiKhoan();
        String query = "SELECT * FROM NhanVien WHERE MaNV = '" + tk + "' and MatKhau='" + mk + "'";
        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            user.setMa(rs.getString("MaNV"));
            user.setMatKhau(rs.getString("MatKhau"));
        }
        return user;
    }

    public ChucVuLG getcv(String ma) {
        ChucVuLG cv = new ChucVuLG();
        List<ChucVuLG> list=new ArrayList<>();
        String query = "select TenCV from ChucVu where IdCV=(Select IdCV from NhanVien where MaNV=?)";
        try ( Connection conn = DBConnection.getConnection();  PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, ma);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cv.setTen(rs.getString("TenCV"));
                list.add(cv);
            }
            return cv;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
