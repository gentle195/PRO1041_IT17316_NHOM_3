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
            String sql = "select HoaDon.Ma, HoaDon.NgayTao,HoaDon.TinhTrang from HoaDon order by Ma asc";
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
            String sql = "insert into HoaDon (Ma,NgayTao,TinhTrang)values(?,?,?)"
                  ;
            Connection cn = DBConnection.getConnection();
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setObject(1, hoaDon.getMaHD());
            pstm.setObject(2,  hoaDon.getNgayTao());
            pstm.setObject(3, hoaDon.getTinhTrang());
            pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(HoaDon hoaDon) throws Exception {
    }

    @Override
    public void delete(HoaDon hoaDon) throws Exception {
    }

}
