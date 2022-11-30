/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import DomainModels.HoaDon;
import DomainModels.HoaDonChiTiet;
import DomainModels.SanPham;
import Utilities.DBConnection;
import ViewModels.HoaDonChiTietViewModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nguyenvv
 */
public class HoaDonChiTietRepository {

    private DBConnection dBConnection;

    public void add(String ma, String mahd, HoaDonChiTiet nv) {
        try {
            String sql = "declare @idctsp UNIQUEIDENTIFIER\n"
                    + "set @idctsp=(select a.IdCTSP from ChiTietSP a left join SanPham b on a.IdSP=b.IdSP where b.MaSP=?)\n"
                    + "declare @idhd UNIQUEIDENTIFIER\n" + "set @idhd=(select IdHD from HoaDon where Ma=?) "
                    + "insert into ChiTietHoaDon(IdHD,IdChiTietSP,SoLuong,DonGia) values(@idhd,@idctsp,?,?)";
            Connection cn = dBConnection.getConnection();
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

    public void updateSL(String ma, HoaDonChiTiet hd) {
        String sql = "declare @idctsp UNIQUEIDENTIFIER set @idctsp=(select a.IdHoaDonCT from ChiTietHoaDon a left join ChiTietSP b on a.IdChiTietSP=b.IdCTSP where IdChiTietSP = (Select IdCTSP from ChiTietSP b join SanPham c on b.IdSP=c.IdSP  where c.MaSP=?)) UPDATE ChiTietHoaDon SET SoLuong = ? WHERE IdHoaDonCT= @idctsp";
        Connection cn = dBConnection.getConnection();
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setObject(1, ma);
            ps.setObject(2, hd.getSoLuong());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonChiTietRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteSL(String ma) {
        String sql = "declare @idctsp UNIQUEIDENTIFIER\n"
                + "set @idctsp=(select a.IdCTSP from ChiTietSP a left join SanPham b on a.IdSP=b.IdSP where b.MaSP=?)\n"
                + "Delete ChiTietHoaDon where IdChiTietSP=@idctsp";
        try {
            PreparedStatement ps = dBConnection.getConnection().prepareStatement(sql);
            ps.setObject(1, ma);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonChiTietRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
