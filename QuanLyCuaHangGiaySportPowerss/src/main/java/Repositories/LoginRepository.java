/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import DomainModels.TaiKhoan;
import Utilities.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author trxua
 */
public class LoginRepository {

//    public TaiKhoan getnv(String ma) {
//        String query = "SELECT * FROM TaiKhoan where MaNV =?";
//        try (Connection conn = DBConnection.getConnection();
//                PreparedStatement ps = conn.prepareStatement(query)) {
//            ps.setObject(1, ma);
//            ResultSet rs = ps.executeQuery();
////            List<NhanVien> listNV = new ArrayList<>();
//            while (rs.next()) {
//                TaiKhoan nv = new TaiKhoan(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),rs.getString(9));
////                listNV.add(nv);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
    public List<TaiKhoan> getnv(String ma) {
        String query = "SELECT * FROM NhanVien where MaNV =?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, ma);
            ResultSet rs = ps.executeQuery();
            List<TaiKhoan> listNV = new ArrayList<>();
            while (rs.next()) {
                TaiKhoan nv = new TaiKhoan(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
                listNV.add(nv);
            }
            return listNV;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String ma = "ql";
        List<TaiKhoan> ls = new ArrayList<>();
        ls = new LoginRepository().getnv(ma);
        for (TaiKhoan l : ls) {
            System.out.println(l.toString());
        }
    }
}
