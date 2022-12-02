/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import DomainModels.SanPham;
import Repositories.Impl.ThongKeRepositoryInterface;
import Utilities.DBConnection;
import ViewModels.ChiTietSPViewModel;
import ViewModels.HoaDonTKViewModel;
import java.math.BigDecimal;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author trxua
 */
public class ThongKeRepositoryImpl implements ThongKeRepositoryInterface {

    @Override
    public List<HoaDonTKViewModel> tkHD() {
        String query = "select NgayTao, COUNT(IdHD) as SLHD, SUM(tongTien) as TongDoanhThu "
                + "from HoaDon where TinhTrang=1 "
                + "group by NgayTao";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            List<HoaDonTKViewModel> listhd = new ArrayList<>();
            while (rs.next()) {
                HoaDonTKViewModel hdtk = new HoaDonTKViewModel(rs.getDate(1), rs.getInt(2), rs.getBigDecimal(3));
                listhd.add(hdtk);
            }
            return listhd;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ChiTietSPViewModel> tkSP() {
        String query = "select SanPham.MaSP, SanPham.TenSP, SUM(ChiTietHoaDon.soLuong) as tongSLBan   \n"
                + "from ChiTietHoaDon join ChiTietSP on ChiTietHoaDon.IdChiTietSP= ChiTietSP.IdCTSP\n"
                + "join SanPham on ChiTietSP.IdSP = SanPham.IdSP\n"
                + "group by SanPham.MaSP, SanPham.TenSP\n"
                + "order by SUM(ChiTietHoaDon.soLuong) desc";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            List<ChiTietSPViewModel> listsp = new ArrayList<>();
            while (rs.next()) {
                SanPham sp = new SanPham(rs.getString(1), rs.getString(2));
                ChiTietSPViewModel sptk = new ChiTietSPViewModel(sp, rs.getInt(3));
                listsp.add(sptk);
            }
            return listsp;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        List<ChiTietSPViewModel> listhd = new ThongKeRepositoryImpl().tkSP();
        for (ChiTietSPViewModel hoaDonTKViewModel : listhd) {
            System.out.println(hoaDonTKViewModel.toString());
        }
    }
}
