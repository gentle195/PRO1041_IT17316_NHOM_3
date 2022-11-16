/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import DomainModels.HoaDon;
import ViewModels.HoaDonViewModel;
import Utilities.DBcontext;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
/**
 *
 * @author dinhq
 */
public class HoaDonRepo {
     public List<HoaDonViewModel> getall() {
        List<HoaDonViewModel> hoaDonDuANs = new ArrayList<>();
        try {
            String sql = "SELECT [IdHD]\n"
                    + "      \n"
                    + "      ,[Ma]\n"
                    + "      ,[NgayTao]\n"
                    + "      ,[NgayThanhToan]\n"
                    + "      ,[NgayShip]\n"
                    + "      ,[NgayNhan]\n"
                    + "      ,[TenNguoiNhan]\n"
                    + "      ,[DiaChi]\n"
                    + "      ,[Sdt]\n"
                    + "      ,[TinhTrang]\n"
                    + "  FROM [dbo].[HoaDon]\n"
                    + "\n"
                    + "GO";
            Connection cn = DBcontext.getConnection();
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                hoaDonDuANs.add(new HoaDonViewModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hoaDonDuANs;
    }

    public void add(HoaDon hd) {
        try {
            String sql = "INSERT INTO [dbo].[HoaDon]\n"
                    + "           (\n"
                    + "           [Ma]\n"
                    + "           ,[NgayTao]\n"
                    + "           ,[NgayThanhToan]\n"
                    + "           ,[NgayShip]\n"
                    + "           ,[NgayNhan]\n"
                    + "           ,[TenNguoiNhan]\n"
                    + "           ,[DiaChi]\n"
                    + "           ,[Sdt]\n"
                    + "		   ,[TinhTrang])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?,?,?,?)";
            Connection cn = DBcontext.getConnection();
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, hd.getMa());
            pstm.setString(2, hd.getNgayTao());
            pstm.setString(3, hd.getNgayThanhToan());
            pstm.setString(4, hd.getNgayShip());
            pstm.setString(5, hd.getNgayNhan());
            pstm.setString(6, hd.getTenNgNhan());
            pstm.setString(7, hd.getDiaChi());
            pstm.setString(8, hd.getSdt());
            pstm.setInt(9, hd.getTinhTrang());
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(String ma) {
        try {
            String sql = " delete from HoaDon where IdHD =? ";
            Connection cn = DBcontext.getConnection();
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, ma);
            pstm.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void update(HoaDon hd) {
        try {
            String sql = "update HoaDon set "
                    + "            Ma = ?\n"
                    + "           ,NgayTao= ?\n"
                    + "           ,NgayThanhToan= ?\n"
                    + "           ,NgayShip= ?\n"
                    + "           ,NgayNhan = ?\n"
                    + "           ,[TenNguoiNhan]= ?\n"
                    + "           ,[DiaChi]= ?\n"
                    + "           ,[Sdt]= ?\n"
                    + "		  ,[TinhTrang]= ?"
                    + "             where IdHD = ? ";
            Connection cn = DBcontext.getConnection();
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, hd.getMa());
            pstm.setString(2, hd.getNgayTao());
            pstm.setString(3, hd.getNgayThanhToan());
            pstm.setString(4, hd.getNgayShip());
            pstm.setString(5, hd.getNgayNhan());
            pstm.setString(6, hd.getTenNgNhan());
            pstm.setString(7, hd.getDiaChi());
            pstm.setString(8, hd.getSdt());
            pstm.setInt(9, hd.getTinhTrang());
            pstm.setString(10, hd.getIdHD());
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
