/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import DomainModels.KhachHang;
import Utilities.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KhachHangRepository {
    

    public List<KhachHang> getAll() {
        String query = "SELECT * FROM KhachHang";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            List<KhachHang> listKhachHang = new ArrayList<>();
            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(6), rs.getString(7));
                listKhachHang.add(kh);
            }
            return listKhachHang;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public List<KhachHangViewModel> search(String ten) {
//        String query = "SELECT id,ma,ten,sdt,diachi,quocGia FROM KhachHang where ten =?";
//        try (Connection conn = DBConnection.getConnection();
//                PreparedStatement ps = conn.prepareStatement(query)) {
//            ps.setObject(1, ten);
//            ResultSet rs = ps.executeQuery();
//            List<KhachHangViewModel> listKhachHang = new ArrayList<>();
//            while (rs.next()) {
//                KhachHangViewModel kh = new KhachHangViewModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
//                listKhachHang.add(kh);
//            }
//            return listKhachHang;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
    
      public List<KhachHang> search(String ten) {
        String query = "SELECT * FROM KhachHang where hoten =?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, ten);
            ResultSet rs = ps.executeQuery();
            List<KhachHang> listKhachHang = new ArrayList<>();
            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(6), rs.getString(7));
                listKhachHang.add(kh);
            }
            return listKhachHang;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
      
    public Boolean create(KhachHang kh) {
        String query = "INSERT INTO [dbo].[KhachHang] "
                + "           ([MaKH] "
                + "           ,[HoTen] "
                + "           ,[GioiTinh] "
                + "           ,[NgaySinh] "
                + "           ,[Sdt] "
                + "           ,[DiaChi]) "
                + "     VALUES "
                + "           (?,?,?,?,?,?)";
        int check = 0;
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, kh.getMa());
            ps.setObject(2, kh.getHoTen());
            ps.setObject(3, kh.getGioiTinh());
            ps.setObject(4, kh.getNgaySinh());
            ps.setObject(5, kh.getSdt());
            ps.setObject(6, kh.getDiaChi());
            check = ps.executeUpdate();
            return check > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public boolean create(KhachHang kh) {
//        String query = "INSERT INTO [dbo].[KhachHang] "
//                + "           ([Ma] "
//                + "           ,[Ten] "
//                + "           ,[TenDem] "
//                + "           ,[Ho] "
//                + "           ,[NgaySinh] "
//                + "           ,[Sdt] "
//                + "           ,[DiaChi] "
//                + "           ,[ThanhPho] "
//                + "           ,[QuocGia] "
//                + "           ,[MatKhau] )"
//                + "     VALUES "
//                + "           (?,?,?,?,?,?,?,?,?,?)";
//        int check = 0;
//        try (Connection conn = DBConnection.getConnection();
//                PreparedStatement ps = conn.prepareStatement(query)) {
//            ps.setObject(1, kh.getMa());
//            ps.setObject(2, kh.getTen());
//            ps.setObject(3, kh.getTenDem());
//            ps.setObject(4, kh.getHo());
//            ps.setObject(5, kh.getNgaySinh());
//            ps.setObject(6, kh.getSdt());
//            ps.setObject(7, kh.getDiaChi());
//            ps.setObject(8, kh.getThanhPho());
//            ps.setObject(9, kh.getQuocGia());
//            ps.setObject(10, kh.getMatKhau());
//            check = ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return check > 0;
//    }
    public boolean update(KhachHang kh, String ma) {
        String query = "UPDATE [dbo].[KhachHang] "
                + "   SET [HoTen] = ? "
                + "      ,[GioiTinh] = ? "
                + "      ,[NgaySinh] = ? "
                + "      ,[Sdt] = ? "
                + "      ,[DiaChi] = ? "
                + " WHERE MaKH = ? ";
        int check = 0;
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, kh.getHoTen());
            ps.setObject(2, kh.getGioiTinh());
            ps.setObject(3, kh.getNgaySinh());
            ps.setObject(4, kh.getSdt());
            ps.setObject(5, kh.getDiaChi());
            ps.setObject(6, kh.getMa());
            check = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean delete(String ma) {
        String query = "DELETE FROM [dbo].[KhachHang] "
                + "      WHERE Makh= ?";
        int check = 0;
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, ma);
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check > 0;
    }

//
}
