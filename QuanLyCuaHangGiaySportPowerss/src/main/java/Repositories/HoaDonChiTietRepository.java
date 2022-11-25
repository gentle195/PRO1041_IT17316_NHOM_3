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

/**
 *
 * @author nguyenvv
 */
public class HoaDonChiTietRepository {

    private DBConnection dBConnection;

    public Boolean saveHoaDonChiTiet(HoaDonChiTiet hoaDonChiTiet) {

        int checkInsert = 0;

        String sql = "INSERT INTO [dbo].[ChiTietHoaDon]\n"
                + "           (\n"
                + "           [IdHD]\n"
                + "           ,[IdChiTietSP]\n"
                + "           ,[SoLuong]\n"
                + "           ,[DonGia])\n"
                + "     VALUES\n"
                + "           (?,?,?,?)";
        try (Connection con = dBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, hoaDonChiTiet.getIdHD());
            ps.setObject(2, hoaDonChiTiet.getIdChiTietSP());
            ps.setObject(3, hoaDonChiTiet.getSoLuong());
            ps.setObject(4, hoaDonChiTiet.getDonGia());
            checkInsert = ps.executeUpdate();
            return checkInsert > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
