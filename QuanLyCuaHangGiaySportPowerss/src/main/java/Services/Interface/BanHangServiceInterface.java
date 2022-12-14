package Services.Interface;

import DomainModels.HoaDon;
import DomainModels.HoaDonChiTiet;
import ViewModels.HoaDonBanHangViewModel;
import ViewModels.HoaDonChiTietViewModel;
import java.sql.SQLException;
import java.util.List;

public interface BanHangServiceInterface {

    void addHoaDonCho(HoaDon hoaDon) throws Exception;

    void addSanPham(String ma, String mahd, HoaDonChiTiet nv) throws Exception;

    List<HoaDonBanHangViewModel> allHoaDonCho();

    void deleteHoaDon(String Ma) throws Exception;

    void deleteSoLuong(String ma) throws Exception;

    void updateSoLuong(String ma, HoaDonChiTiet hd) throws Exception;

    void updateThanhToan(HoaDon hoaDon, String ma, String ma1) throws Exception;
    public void updatetrung(String ma, int sl);
    List<HoaDonChiTietViewModel> getListById(String idhoaDon) throws SQLException;
   
}
