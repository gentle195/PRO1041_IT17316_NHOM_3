/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import DomainModels.HoaDon;
import DomainModels.HoaDonChiTiet;
import Repositories.Impl.BanHangRepositoryInterface;
import Utilities.DBConnection;
import ViewModels.HoaDonBanHangViewModel;
import ViewModels.HoaDonChiTietViewModel;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dinhq
 */
public class BanHangRepository implements BanHangRepositoryInterface {

    @Override
    public void addSanPham(String ma, String mahd, HoaDonChiTiet nv) throws Exception {
        try {
            String sql = "declare @idctsp UNIQUEIDENTIFIER\n"
                    + "set @idctsp=(select IdCTSP from ChiTietSP where MaSP=?)\n"
                    + "declare @idhd UNIQUEIDENTIFIER\n" + "set @idhd=(select IdHD from HoaDon where Ma=?) "
                    + "insert into ChiTietHoaDon(IdHD,IdChiTietSP,SoLuong,DonGia) values(@idhd,@idctsp,?,?)";
            Connection cn = DBConnection.getConnection();
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, ma);
            pstm.setString(2, mahd);
            pstm.setObject(3, nv.getSoLuong());
            pstm.setObject(4, nv.getDonGia());
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSoLuong(String ma, HoaDonChiTiet hd) throws Exception {
        String sql = "declare @idctsp UNIQUEIDENTIFIER "
                + "set @idctsp=(select a.IdHoaDonCT from ChiTietHoaDon a left join ChiTietSP b on a.IdChiTietSP=b.IdCTSP where IdChiTietSP = (Select IdCTSP from ChiTietSP where MaSP=?)) "
                + "UPDATE ChiTietHoaDon SET SoLuong = ? WHERE IdHoaDonCT= @idctsp";
        Connection cn = DBConnection.getConnection();
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setObject(1, ma);
            ps.setObject(2, hd.getSoLuong());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteSoLuong(String ma) throws Exception {
        String sql = "declare @idctsp UNIQUEIDENTIFIER\n"
                + "set @idctsp=(select IdCTSP from ChiTietSP where MaSP=?)\n"
                + "Delete ChiTietHoaDon where IdChiTietSP=@idctsp";
        try {
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setObject(1, ma);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<HoaDonBanHangViewModel> allHoaDonCho() {
        List<HoaDonBanHangViewModel> hd = new ArrayList<>();
        try {
            String sql = "select Ma, NgayTao,TinhTrang from HoaDon where TinhTrang=0 order by Ma asc";
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
    public void addHoaDonCho(HoaDon hoaDon) throws Exception {
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
    public void deleteHoaDon(String Ma) throws Exception {
        String sql = "declare @idhd UNIQUEIDENTIFIER set @idhd=(select IdHD from HoaDon where Ma=?) Delete ChiTietHoaDon where IdHD=@idhd Delete from HoaDon where Ma=?";
        Connection cn = DBConnection.getConnection();
        PreparedStatement pstm = cn.prepareStatement(sql);
        pstm.setObject(1, Ma);
        pstm.setObject(2, Ma);
        pstm.executeUpdate();
    }

    @Override
    public void updateThanhToan(HoaDon hoaDon, String ma, String ma1) throws Exception {
        try {
            String sql = "Declare @Idkh UNIQUEIDENTIFIER \n"
                    + "set @Idkh = (select IdKH from KhachHang where Sdt = ?)\n"
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
            pstm.setObject(7, hoaDon.getPTGD());
            pstm.setObject(8, hoaDon.getTinhTrang());
            pstm.setObject(9, hoaDon.getTongTien());
            pstm.setObject(10, hoaDon.getMaHD());

            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Lọc bên hoá đơn xuống hoá đơn chi tiết
    @Override
    public List<HoaDonChiTietViewModel> getListById(String idhoaDon) throws SQLException{
        String query = "SELECT  b.MaSP , c.TenSP , a.SoLuong , a.DonGia fROM ChiTietHoaDon a left join ChiTietSP b on a.IdChiTietSP = b.IdCTSP left join SanPham c on c.IdSP = b.IdSP left join HoaDon d on a.IdHD = d.IdHD where d.Ma = ?";
        List<HoaDonChiTietViewModel> list = new ArrayList<>();
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, idhoaDon);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTietViewModel model = new HoaDonChiTietViewModel(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getBigDecimal(4));
                list.add(model);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public void updatetrung(String ma, int sl) {
        String sql="update ChiTietHoaDon set SoLuong=? where IdChiTietSP like(select IdCTSP from ChiTietSP where MaSP=?)";
        Connection cn = DBConnection.getConnection();
        try {
            PreparedStatement ps= cn.prepareStatement(sql);
            ps.setObject(1, sl);
            ps.setObject(2, ma);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BanHangRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
