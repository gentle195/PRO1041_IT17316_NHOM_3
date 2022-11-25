/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services.Interface;

import DomainModels.NhanVien;
import ViewModels.NhanVienViewModel;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface NhanVienServiceInteface {
    
    public List<NhanVienViewModel> getall();

    public void create(NhanVien nv) throws Exception;

    public void update(NhanVien nv) throws Exception;

    public void delete(NhanVien nv) throws Exception;
}
