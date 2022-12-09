/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DomainModels.HoaDon;
import Repositories.BanHangRepository;
import Repositories.HoaDonRepository;
import ViewModels.HoaDonViewModel;
import java.util.List;
import Services.Interface.HoaDonServiceInterface;
import ViewModels.HoaDonBanHangViewModel;
import ViewModels.HoaDonChiTietViewModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author nguyenvv
 */
public class HoaDonService implements HoaDonServiceInterface {

    private HoaDonRepository hoaDonRepository;
    private BanHangRepository BanHangService = new BanHangRepository();

    public HoaDonService() {
        hoaDonRepository = new HoaDonRepository();
    }

    @Override
    public List<HoaDonViewModel> all() {
        return hoaDonRepository.all();
    }

    @Override
    public List<HoaDonChiTietViewModel> getListById(String idhoaDon) throws SQLException {
        return hoaDonRepository.getListById(idhoaDon);
    }

    @Override
    public List<HoaDonViewModel> ListHdSearch(Date bd, Date kt) {
        return hoaDonRepository.ListHdSearch(bd, kt);
    }

}
