/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DomainModels.HoaDonChiTiet;
import DomainModels.SanPham;
import Repositories.HoaDonChiTietRepository;
import ViewModels.HoaDonChiTietViewModel;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author admin
 */
public class HDCTService {
    private HoaDonChiTietRepository chiTietHDRepo;

    private List<HoaDonChiTiet> chiTietSPs;

    public HDCTService() {
        this.chiTietHDRepo = new HoaDonChiTietRepository();
        chiTietSPs = new ArrayList<>();
    }
    public void add(String ma,String mahd,HoaDonChiTiet hd){
        chiTietHDRepo.add(ma,mahd,hd);
    }
    public void updateSL(String ma,HoaDonChiTiet hd){
        chiTietHDRepo.updateSL(ma,hd);
    }
    public void deleteSL(String ma){
        chiTietHDRepo.deleteSL(ma);
    }
}
