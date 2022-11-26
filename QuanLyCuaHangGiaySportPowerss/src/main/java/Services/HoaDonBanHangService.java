package Services;

import DomainModels.HoaDon;

import Repositories.HoaDonBanHangRepository;

import Services.Interface.HoaDonBanHangServiceInterface;

import ViewModels.HoaDonBanHangViewModel;

import java.util.List;

public class HoaDonBanHangService implements HoaDonBanHangServiceInterface {

    private HoaDonBanHangRepository hoadonrepo;

    public HoaDonBanHangService() {
        this.hoadonrepo = new HoaDonBanHangRepository();
    }

    @Override
    public void add(HoaDon hd) throws Exception {
hoadonrepo.add(hd);
    }

    @Override
    public void update(HoaDon hd) throws Exception {
    }

    @Override
    public void delete(HoaDon hd) throws Exception {
    }

    @Override
    public List<HoaDonBanHangViewModel> all() {
        return hoadonrepo.all();
    }
}
