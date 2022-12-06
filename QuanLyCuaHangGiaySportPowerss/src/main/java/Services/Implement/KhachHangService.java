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
                return "Mã khách hàng không được trùng";
            }
        }
        if (kh.getMa().equals("")) {
            return "Mã khách hàng không được để trống!";
        }
        if (kh.getHoTen().equals("")) {
            return "Tên khách hàng không được để trống!";
        }
        if (kh.getSdt().equals("")) {
            return "Sđt khách hàng không được để trống!";
        }
        if (!(kh.getSdt().matches(".*[^0-9].*") || kh.getSdt().length() == 10)) {
            return "Sđt phải gồm 10 chữ số";
        }
        if (kh.getNgaySinh() == null) {
            return "Ngày sinh khách hàng không được để trống!";
        }
        if (kh.getDiaChi().equals("")) {
            return "Địa chỉ khách hàng không được để trống!";
        }
        if (KHRepo.create(kh) == true) {
            return "Thêm thành công ^^";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public String update(KhachHang kh) throws Exception {
        if (kh.getMa().equals("")) {
            return "Mã khách hàng không được để trống!";
        }
        if (kh.getHoTen().equals("")) {
            return "Tên khách hàng không được để trống!";
        }
        if (kh.getSdt().equals("")) {
            return "Sđt khách hàng không được để trống!";
        }
        if (!(kh.getSdt().matches(".*[^0-9].*") || kh.getSdt().length() == 10)) {
            return "Sđt phải gồm 10 chữ số";
        }
        if (kh.getNgaySinh() == null) {
            return "Ngày sinh khách hàng không được để trống!";
        }
        if (kh.getDiaChi().equals("")) {
            return "Địa chỉ khách hàng không được để trống!";
        }
        if (KHRepo.update(kh) == true) {
            return "Sửa thành công ^^";
        } else {
            return "Sửa thất bại";
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
