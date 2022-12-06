package Services;

import DomainModels.HoaDon;

import Repositories.HoaDonBanHangRepository;
import Repositories.HoaDonChiTietRepository;

import Services.Interface.HoaDonBanHangServiceInterface;

import ViewModels.HoaDonBanHangViewModel;
import ViewModels.HoaDonChiTietViewModel;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

public class HoaDonBanHangService implements HoaDonBanHangServiceInterface {

    private HoaDonBanHangRepository hoadonrepo;
    private HoaDonChiTietRepository hoaDonChiTietRepository = new HoaDonChiTietRepository();

    public HoaDonBanHangService() {
        this.hoadonrepo = new HoaDonBanHangRepository();
    }

    @Override
    public void add(HoaDon hd) throws Exception {
        hoadonrepo.add(hd);
    }

    @Override
    public void update(HoaDon hd, String ma,String ma1) throws Exception {
        hoadonrepo.update(hd, ma, ma1);
    }

    @Override
    public void delete(String Ma) throws Exception {
        hoadonrepo.delete(Ma);
    }

    @Override
    public List<HoaDonBanHangViewModel> all() {
        return hoadonrepo.all();
    }
    
     @Override
    public List<HoaDonChiTietViewModel> getAll(String idHoaDon) {
        List<HoaDonChiTietViewModel> list = new ArrayList<>();
        try {
            list = hoaDonChiTietRepository.getListById(idHoaDon);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
