/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import DomainModels.HoaDon;
import Repositories.Impl.HoaDonBanHangRepositoryInterface;
import Utilities.DBConnection;
import ViewModels.HoaDonBanHangViewModel;
import ViewModels.HoaDonViewModel;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author dinhq
 */
public class HoaDonBanHangRepository implements HoaDonBanHangRepositoryInterface {

    @Override
    public List<HoaDonBanHangViewModel> all() {
        List<HoaDonBanHangViewModel> hd = new ArrayList<>();
        try {
            String sql = "select Ma, NgayTao,TinhTrang from HoaDon order by Ma asc";
            Connection cn = DBConnection.getConnection();
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                hd.add(new HoaDonBanHangViewModel(rs.getString(1), rs.getDate(2), rs.getInt(3)));
            }
            return hd;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(HoaDon hoaDon) throws Exception {
        try {
            String sql = "insert into HoaDon (Ma,NgayTao,TinhTrang)values(?,?,?)";
            Connection cn = DBConnection.getConnection();
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setObject(1, hoaDon.getMaHD());
            pstm.setObject(2, hoaDon.getNgayTao());
            pstm.setObject(3, hoaDon.getTinhTrang());
            pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(HoaDon hoaDon, String ma, String ma1) throws Exception {
        try {
            String sql = "Declare @Idkh UNIQUEIDENTIFIER \n"
                    + "set @Idkh = (select IdKH from KhachHang where MaKH = ?)\n"
                    + "Declare @Idnv UNIQUEIDENTIFIER \n"
                    + "set @Idnv = (select IdNV from NhanVien where MaNV = ?)"
                    + "update HoaDon set IdKH = @Idkh , IdNV = @Idnv, NgayThanhToan = ?,NgayDat = ?,NgayShip = ?,NgayNhan = ?,PTGH = ?,TinhTrang= ?,TongTien = ? where Ma = ?";
            Connection cn = DBConnection.getConnection();
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setObject(1, ma);
            pstm.setObject(2, ma1);
            pstm.setObject(3, hoaDon.getNgayThanhToan());
            pstm.setObject(4, hoaDon.getNgayDat());
            pstm.setObject(5, hoaDon.getNgayShip());
            pstm.setObject(6, hoaDon.getNgayNhan());
            pstm.setObject(7, hoaDon.getNgayNhan());
            pstm.setObject(8, hoaDon.getPTGD());
            pstm.setObject(9, hoaDon.getTongTien());
            pstm.setObject(10, hoaDon.getMaHD());

            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String Ma) throws Exception {
        String sql = "declare @idhd UNIQUEIDENTIFIER set @idhd=(select IdHD from HoaDon where Ma=?) Delete ChiTietHoaDon where IdHD=@idhd Delete from HoaDon where Ma=?";
        Connection cn = DBConnection.getConnection();
        PreparedStatement pstm = cn.prepareStatement(sql);
        pstm.setObject(1, Ma);
        pstm.setObject(2, Ma);
        pstm.executeUpdate();
    }

}
