/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.Implement;

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
import java.util.Date;

/**
 *
 * @author trxua
 */
public class ThongKeRepository implements ThongKeRepositoryInterface {

    @Override
    public List<HoaDonTKViewModel> tkHD() {
        String query = "select NgayThanhToan, COUNT(IdHD) as SLHD, SUM(tongTien) as TongDoanhThu "
                + "from HoaDon where TinhTrang=1 "
                + "group by NgayThanhToan";
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

    @Override
    public List<HoaDonTKViewModel> tkHDpM(Date bd, Date kt) {
        String query = "select COUNT(IdHD) as SLHD, SUM(tongTien) as TongDoanhThu \n"
                + "from HoaDon where TinhTrang=1 and NgayThanhToan between  ? and ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, bd);
            ps.setObject(2, kt);
            ResultSet rs = ps.executeQuery();
            List<HoaDonTKViewModel> listhd = new ArrayList<>();
            while (rs.next()) {
                HoaDonTKViewModel hdtk = new HoaDonTKViewModel(rs.getInt(1), rs.getBigDecimal(2));
                listhd.add(hdtk);
            }
            return listhd;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ChiTietSPViewModel> tkSPpM(Date bd, Date kt) {
        String query = "select  SanPham.MaSP, SanPham.TenSP, SUM(ChiTietHoaDon.soLuong) as tongSLBan   \n"
                + "from HoaDon join ChiTietHoaDon on HoaDon.IdHD = ChiTietHoaDon.IdHD  \n"
                + "join ChiTietSP on ChiTietHoaDon.IdChiTietSP= ChiTietSP.IdCTSP\n"
                + "join SanPham on ChiTietSP.IdSP = SanPham.IdSP\n"
                + "where HoaDon.NgayTao between ? and ?\n"
                + "group by SanPham.MaSP, SanPham.TenSP,HoaDon.NgayTao order by SUM(ChiTietHoaDon.soLuong) desc ";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, bd);
            ps.setObject(2, kt);
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

    @Override
    public List<HoaDonTKViewModel> tkTHD() {
        String query = "select count(IdHD) as SLHD\n"
                + "from HoaDon where TinhTrang=1";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            List<HoaDonTKViewModel> listhd = new ArrayList<>();
            while (rs.next()) {
                HoaDonTKViewModel hdtk = new HoaDonTKViewModel(rs.getInt(1));
                listhd.add(hdtk);
            }
            return listhd;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<HoaDonTKViewModel> tkTDT() {
        String query = "select SUM(tongTien) as TongDoanhThu \n"
                + "from HoaDon where TinhTrang=1";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            List<HoaDonTKViewModel> listhd = new ArrayList<>();
            while (rs.next()) {
                HoaDonTKViewModel hdtk = new HoaDonTKViewModel(rs.getBigDecimal(1));
                listhd.add(hdtk);
            }
            return listhd;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<HoaDonTKViewModel> tkTDTpM(Date bd) {
        String query = "select NgayThanhToan,  SUM(tongTien) as TongDoanhThu \n"
                + "from HoaDon where TinhTrang=1 and MONTH(NgayThanhToan) =MONTH(?)";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, bd);
            ResultSet rs = ps.executeQuery();
            List<HoaDonTKViewModel> listhd = new ArrayList<>();
            while (rs.next()) {
                HoaDonTKViewModel hdtk = new HoaDonTKViewModel(rs.getBigDecimal(2));
                listhd.add(hdtk);
            }
            return listhd;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
