package Services;

import DomainModels.HoaDon;
import DomainModels.HoaDonChiTiet;

import Repositories.BanHangRepository;
import ViewModels.HoaDonBanHangViewModel;

import java.util.List;
import Services.Interface.BanHangServiceInterface;
import ViewModels.HoaDonChiTietViewModel;
import java.sql.SQLException;

public class BanHangService implements BanHangServiceInterface {

    private BanHangRepository banHangRespository;

    public BanHangService() {
        banHangRespository = new BanHangRepository();
    }

    @Override
    public void addHoaDonCho(HoaDon hoaDon) throws Exception {
        banHangRespository.addHoaDonCho(hoaDon);
    }

    @Override
    public void addSanPham(String ma, String mahd, HoaDonChiTiet nv) throws Exception {
        banHangRespository.addSanPham(ma, mahd, nv);
    }

    @Override
    public List<HoaDonBanHangViewModel> allHoaDonCho() {
        return banHangRespository.allHoaDonCho();
    }

    @Override
    public void deleteHoaDon(String Ma) throws Exception {
        banHangRespository.deleteHoaDon(Ma);
    }

    @Override
    public void deleteSoLuong(String ma) throws Exception {
        banHangRespository.deleteSoLuong(ma);
    }

    @Override
    public void updateSoLuong(String ma, HoaDonChiTiet hd) throws Exception {

        banHangRespository.updateSoLuong(ma, hd);
    }

    @Override
    public void updateThanhToan(HoaDon hoaDon, String ma, String ma1) throws Exception {
        banHangRespository.updateThanhToan(hoaDon, ma, ma1);
    }

    @Override
    public List<HoaDonChiTietViewModel> getListById(String idhoaDon) throws SQLException {
        return banHangRespository.getListById(idhoaDon);
    }

    @Override
    public void updatetrung(String ma, int sl) {
        banHangRespository.updatetrung(ma, sl);
    }
}
