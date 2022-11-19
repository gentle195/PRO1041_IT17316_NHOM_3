/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.Impl;

import DomainModels.KhachHang;
import Repositories.KhachHangRepository;
import Services.KhachHangService;
import java.util.List;

/**
 *
 * @author trxua
 */
public class KhachHangServiceImpl implements KhachHangService {

    private KhachHangRepository khachHangRepository = new KhachHangRepository();

    @Override
    public List<KhachHang> getAll() {
        return new KhachHangRepository().getAll();
    }

    @Override
    public String create(KhachHang kh) {
        List<KhachHang> lstKH = khachHangRepository.getAll();
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
//        if (!kh.getSdt().matches(".*[^0-9].*") && kh.getSdt().length() == 10) {
//            return "Sđt sai";
//        }
        if (kh.getGioiTinh().equals("")) {
            return "gt null";
        }
        if (kh.getNgaySinh().toString().equals("")) {
            return "ns null";
        }
        if (khachHangRepository.create(kh) == true) {
            return "Them thanh cong";
        } else {
            return "Them that bai";
        }
        
    }

    @Override
    public String update(KhachHang kh, String ma) {
        if (kh.getHoTen().equals("")) {
            return "Tên null";
        }
        if (kh.getDiaChi().equals("")) {
            return "Địa chỉ null";
        }
        if (kh.getSdt().equals("")) {
            return "Sđt null";
        }
//        if (kh.getSdt().matches(".*[^0-9].*") && kh.getSdt().length() == 10) {
//            return "Sđt sai";
//        }
        if (kh.getGioiTinh().equals("")) {
            return "gt null";
        }
        if (kh.getNgaySinh().toString().equals(null)) {
            return "ns null";
        }
        if (khachHangRepository.update(kh, ma) == true) {
            return "Sua thanh cong";
        } else {
            return "Sua that bai";
        }
    }

    @Override
    public String delete(String ma) {
        boolean delete = new KhachHangRepository().delete(ma);
        if (delete) {
            return "Delete successfully!";
        }
        return "Delete failed!";
    }

//    @Override
//    public String insert(KhachHangViewModel kh) {
//        if (kh.getMa().equals("")) {
//            return "Mã null";
//        }
//        if (kh.getTen().equals("")) {
//            return "Tên null";
//        }
//        if (kh.getDiaChi().equals("")) {
//            return "Địa chỉ null";
//        }
//        if (kh.getSdt().equals("")) {
//            return "Sđt null";
//        }
//        if (khachHangRepository.insert(kh) == true) {
//            return "Them thanh cong";
//        } else {
//            return "Them that bai";
//        }
}

//    @Override
//    public List<KhachHangViewModel> getKH() {
//        return khachHangRepository.getKH();
//    }
//
//    @Override
//    public List<KhachHangViewModel> search(String ten) {
//        return khachHangRepository.search(ten);
//    }

