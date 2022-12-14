/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories.Impl;

import DomainModels.HoaDon;
import DomainModels.HoaDonChiTiet;
import ViewModels.HoaDonBanHangViewModel;
import ViewModels.HoaDonChiTietViewModel;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author dinhq
 */
public interface BanHangRepositoryInterface {

    void addHoaDonCho(HoaDon hoaDon) throws Exception;

    void addSanPham(String ma, String mahd, HoaDonChiTiet nv) throws Exception;

    List<HoaDonBanHangViewModel> allHoaDonCho();

    void deleteHoaDon(String Ma) throws Exception;

    void deleteSoLuong(String ma) throws Exception;

    void updateSoLuong(String ma, HoaDonChiTiet hd) throws Exception;

    void updateThanhToan(HoaDon hoaDon, String ma, String ma1) throws Exception;
    void updatetrung(String ma,int sl);
    // Lọc bên hoá đơn xuống hoá đơn chi tiết
    List<HoaDonChiTietViewModel> getListById(String idhoaDon) throws SQLException;

}
