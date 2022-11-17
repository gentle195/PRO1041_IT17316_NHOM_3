/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;



import ViewModels.SanPham_ViewModel;

import java.util.List;

/**
 *
 * @author TUAN ANH
 */
public interface SanPham_Interface {
    List<SanPham_ViewModel> getAll();
    
    SanPham_ViewModel addModel(SanPham_ViewModel sp);
    String DeleteModel(String idSP);
    SanPham_ViewModel UpdateModel(SanPham_ViewModel sp,String idSP);
    
    
    
}
 