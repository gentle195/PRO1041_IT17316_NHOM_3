/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.Implement;

import Repositories.Implement.ThongKeRepository;
import ViewModels.HoaDonTKViewModel;
import java.util.List;
import Services.Interface.ThongKeServiceInterface;
import ViewModels.ChiTietSPViewModel;
import java.util.Date;

/**
 *
 * @author trxua
 */
public class ThongKeServiceImpl implements ThongKeServiceInterface {

    ThongKeRepository tkRepo = new ThongKeRepository();

    @Override
    public List<HoaDonTKViewModel> thongKeHD() {
        return tkRepo.tkHD();
    }

    @Override
    public List<ChiTietSPViewModel> thongKeSP() {
        return tkRepo.tkSP();
    }

    @Override
    public List<HoaDonTKViewModel> tkHDpM(Date bd, Date kt) {
        return tkRepo.tkHDpM(bd, kt);
    }

    @Override
    public List<ChiTietSPViewModel> tkSPpM(Date bd, Date kt) {
        return tkRepo.tkSPpM(bd, kt);
    }

    @Override
    public List<HoaDonTKViewModel> tkTHD() {
        return tkRepo.tkTHD();
    }

    @Override
    public List<HoaDonTKViewModel> tkTDT() {
        return tkRepo.tkTDT();
    }

    @Override
    public List<HoaDonTKViewModel> tkTDTpM(Date bd) {
        return tkRepo.tkTDTpM(bd);
    }

}
