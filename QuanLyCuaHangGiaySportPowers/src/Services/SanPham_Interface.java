/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import DomainModels.SanPham_DomainModel;
import ViewModels.SanPham_ViewModel;

import java.util.List;

/**
 *
 * @author TUAN ANH
 */
public interface SanPham_Interface {

    List<SanPham_ViewModel> getAll();

    SanPham_DomainModel addModel(SanPham_DomainModel sp);

    String DeleteModel(String idSP);

    SanPham_DomainModel UpdateModel(SanPham_DomainModel sp, String idSP);

}
