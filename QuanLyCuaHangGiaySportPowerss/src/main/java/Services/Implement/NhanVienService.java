/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DomainModels.NhanVien;
import Repositories.NhanVienRepository;
import Services.Interface.NhanVienServiceInteface;
import ViewModels.NhanVienViewModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class NhanVienService implements NhanVienServiceInteface {

    private NhanVienRepository NVrepo;

    public NhanVienService() {
        this.NVrepo = new NhanVienRepository();
    }

    @Override
    public List<NhanVienViewModel> getall(int min, int max) {
        List<NhanVien> listDomainModel = this.NVrepo.getall(min, max);
        List<NhanVienViewModel> listVModel = new ArrayList<>();
        for (NhanVien c : listDomainModel) {
            NhanVienViewModel vmodel = new NhanVienViewModel(c.getIdNV(), c.getMaNV(), c.getHoTenNV(), c.getGioiTinh(), c.getNgaySinh(), c.getDiaChi(), c.getSdt(), c.getChucvu(), c.getMatkhau());
            listVModel.add(vmodel);
        }

        return listVModel;
    }

    @Override
    public void create(NhanVien nv) throws Exception {
        NVrepo.create(nv);
    }

    @Override
    public void update(NhanVien nv) throws Exception {
        NVrepo.update(nv);
    }

    @Override
    public void delete(NhanVien nv) throws Exception {
        NVrepo.delete(nv);

    }

    @Override
    public List<NhanVienViewModel> getallNhanVien(String ma) {
        List<NhanVien> listDomainModel = this.NVrepo.getallNhanVien(ma);
        List<NhanVienViewModel> listVModel = new ArrayList<>();
        for (NhanVien c : listDomainModel) {
            NhanVienViewModel vmodel = new NhanVienViewModel(c.getIdNV(), c.getMaNV(), c.getHoTenNV(), c.getGioiTinh(), c.getNgaySinh(), c.getDiaChi(), c.getSdt(), c.getChucvu(), c.getMatkhau());
            listVModel.add(vmodel);
        }
        return listVModel;
    }

    @Override
    public long dem() {
        return NVrepo.dem();
    }
}
