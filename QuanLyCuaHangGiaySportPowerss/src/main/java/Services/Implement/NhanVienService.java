/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DomainModels.NhanVien;
import Repositories.NhanVienRepository;
import Services.Interface.NhanVienServiceInteface;
import ViewModels.NhanVienViewModel;
import ViewModels.NhanVienViewModel1;
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
    public List<NhanVienViewModel1> getallNhanVien(String ma) {
        List<NhanVien> listDomainModel = this.NVrepo.getallNhanVien(ma);
        List<NhanVienViewModel1> listVModel = new ArrayList<>();
        for (NhanVien c : listDomainModel) {
            NhanVienViewModel1 vmodel = new NhanVienViewModel1(c.getIdNV(), c.getMaNV(), c.getHoTenNV(), c.getGioiTinh(), c.getNgaySinh(), c.getDiaChi(), c.getSdt(), c.getChucvu(), c.getMatkhau());
            listVModel.add(vmodel);
        }
        return listVModel;
    }
    
    @Override
    public long dem() {
        return NVrepo.dem();
    }
    
    @Override
    public String updatett(NhanVien nv, String ma) {
         return NVrepo.updatett(nv, ma);
    }
}
