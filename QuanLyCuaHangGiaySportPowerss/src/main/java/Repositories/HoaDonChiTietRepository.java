/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import DomainModels.HoaDon;
import DomainModels.HoaDonChiTiet;
import Utilities.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nguyenvv
 */
public class HoaDonChiTietRepository {

    private DBConnection dBConnection;

//    public Boolean saveHoaDonChiTiet(HoaDonChiTiet hoaDonChiTiet) {
//
//        int checkInsert = 0;
//
//        String sql = "INSERT INTO [dbo].[ChiTietHoaDon]\n"
//                + "           (\n"
//                + "           [IdHD]\n"
//                + "           ,[IdChiTietSP]\n"
//                + "           ,[SoLuong]\n"
//                + "           ,[DonGia])\n"
//                + "     VALUES\n"
//                + "           (?,?,?,?)";
//        try ( Connection con = dBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setObject(1, hoaDonChiTiet.getIdHD());
//            ps.setObject(2, hoaDonChiTiet.getIdChiTietSP());
//            ps.setObject(3, hoaDonChiTiet.getSoLuong());
//            ps.setObject(4, hoaDonChiTiet.getDonGia());
//            checkInsert = ps.executeUpdate();
//            return checkInsert > 0;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public void create(HoaDonChiTiet hd) {
        Connection conn = DBConnection.getConnection();
        String pr = "insert into ChiTietHoaDon(IdHD,IdChiTietSP,SoLuong,DonGia) values(?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(pr);
            ps.setObject(1, hd.getIdHD());
            ps.setObject(2, hd.getIdChiTietSP());
            ps.setObject(3, hd.getSoLuong());
            ps.setObject(4, hd.getDonGia());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonChiTietRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
