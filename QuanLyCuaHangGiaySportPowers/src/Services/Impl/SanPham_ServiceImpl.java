/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.Impl;


import DomainModels.SanPham_DomainModel;
import Repositories.SanPham_Repository;

import Services.SanPham_Interface;

import ViewModels.SanPham_ViewModel;

import java.util.List;


/**
 *
 * @author TUAN ANH
 */
public class SanPham_ServiceImpl implements SanPham_Interface {

    private final SanPham_Repository sp_Repository;

    public SanPham_ServiceImpl() {
        sp_Repository = new SanPham_Repository();

    }

    @Override
    public List<SanPham_ViewModel> getAll() {
        return sp_Repository.getAll();

    }
    
    
    @Override
    public SanPham_DomainModel addModel(SanPham_DomainModel sp) {
        sp_Repository.add(sp);
        return sp;
    }

    @Override
    public SanPham_DomainModel UpdateModel(SanPham_DomainModel sp, String idSP) {
        sp_Repository.Update(idSP, sp);
        return sp;

    }

    @Override
    public String DeleteModel(String idSP) {
        sp_Repository.Delete(idSP);
        return idSP;

    }
    

}
