/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import DomainModels.HoaDon;
import Utilities.DBConnection;
import ViewModels.HoaDonViewModel;
import ViewModels.SanPhamViewModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoaDonRepository {

    public Boolean saveHoaDon(HoaDonViewModel hd) {
        String sql = "INSERT INTO [dbo].[HoaDon] "
                + "           ([TenKH]"
                + "           ,[MaNV]"
                + "           ,[NgayTao] "
                + "           ,[NgayDat] "
                + "           ,[NgayThanhToan] "
                + "           ,[NgayShip] "
                + "           ,[NgayNhan]) "
                + "     VALUES "
                + "           (?,?,?,?,?,?,?)";
        int checkInsert = 0;
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, hd.getTenKH());
            ps.setObject(2, hd.getMaNV());
            ps.setObject(3, hd.getNgayTao());
            ps.setObject(4, hd.getNgayDat());
            ps.setObject(5, hd.getNgayThanhToan());
            ps.setObject(6, hd.getNgayShip());
            ps.setObject(7, hd.getNgayNhan());
            checkInsert = ps.executeUpdate();
            return checkInsert > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<HoaDonViewModel> getListHoaDon() {
        ArrayList<HoaDonViewModel> list = new ArrayList<>();
        String sql = "select stt,TenKH,maNV,IDHD,NgayTao,NgayDat,NgayThanhToan,NgayShip,NgayNhan from HoaDon";
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonViewModel hoaDon = new HoaDonViewModel();
                hoaDon.setSTT(rs.getInt(1));
                hoaDon.setTenKH(rs.getString(2));
                hoaDon.setMaNV(rs.getString(3));
                hoaDon.setIdHD(rs.getString(4));
                hoaDon.setNgayTao(rs.getDate(5));
                hoaDon.setNgayDat(rs.getDate(6));
                hoaDon.setNgayThanhToan(rs.getDate(7));
                hoaDon.setNgayShip(rs.getDate(8));
                hoaDon.setNgayNhan(rs.getDate(9));
                list.add(hoaDon);
            }
        } catch (Exception e) {
            e.getMessage();
        }

        return list;
    }

    public static void main(String[] args) {
//        List<HoaDonViewModel> ls = new HoaDonRepository().getListHoaDon();
//        for (HoaDonViewModel l : ls) {
//            System.out.println(l.toString());
//        }

        HoaDonViewModel hd = new HoaDonViewModel();
        hd.setTenKH("kh2");
        hd.setMaNV("nv1");
        boolean add = new HoaDonRepository().saveHoaDon(hd);
        System.out.println(add);
    }
}
