/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DomainModels.KhachHang;
import Repositories.KhachHangRepository;
import Services.Interface.KhachHangServiceInterface;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author trxua
 */
public class KhachHangService implements KhachHangServiceInterface {

    private KhachHangRepository KHRepo;

    public KhachHangService() {
        KHRepo = new KhachHangRepository();
    }

    @Override
    public List<KhachHang> getAll() {
        return new KhachHangRepository().all();
    }

    @Override
    public String create(KhachHang kh) throws Exception {
        List<KhachHang> lstKH = KHRepo.all();
        for (KhachHang khachHangtrc : lstKH) {
            if (kh.getMa().equals(khachHangtrc.getMa())) {
                return "mã trùng";
            }
        }
        if (kh.getMa().equals("")) {
            return "Mã null";
        }
        if (kh.getHoTen().equals("")) {
            return "Tên null";
        }
        if (kh.getDiaChi().equals("")) {
            return "Địa chỉ null";
        }
        if (kh.getSdt().equals("")) {
            return "Sđt null";
        }
        if (!kh.getSdt().matches(".*[^0-9].*") && kh.getSdt().length() == 10) {
            return "Sđt sai";
        }
        if (kh.getGioiTinh().equals("")) {
            return "gt null";
        }
        if (kh.getNgaySinh().toString().equals("")) {
            return "ns null";
        }
        if (KHRepo.create(kh) == true) {
            return "Them thanh cong";
        } else {
            return "Them that bai";
        }
    }

    @Override
    public String update(KhachHang kh) throws Exception {
        if (kh.getHoTen().equals("")) {
            return "Tên null";
        }
        if (kh.getDiaChi().equals("")) {
            return "Địa chỉ null";
        }
        if (kh.getSdt().equals("")) {
            return "Sđt null";
        }
        if (!kh.getSdt().matches(".*[^0-9].*") && kh.getSdt().length() == 10) {
            return "Sđt sai";
        }
        if (kh.getGioiTinh().equals("")) {
            return "gt null";
        }
        if (kh.getNgaySinh().toString().equals("")) {
            return "ns null";
        }
        if (KHRepo.update(kh) == true) {
            return "Update thanh cong";
        } else {
            return "Update that bai";
        }
    }

    @Override
    public void delete(KhachHang kh) throws Exception {
        KHRepo.delete(kh);
    }

    @Override
    public List<KhachHang> search(String ten) {
        return KHRepo.search(ten);
    }
}
