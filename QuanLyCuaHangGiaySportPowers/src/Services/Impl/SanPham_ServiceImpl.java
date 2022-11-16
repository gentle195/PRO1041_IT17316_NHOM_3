/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.Impl;


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
    public SanPham_ViewModel addModel(SanPham_ViewModel sp) {
        sp_Repository.add(sp);
        return sp;
    }

    @Override
    public SanPham_ViewModel UpdateModel(SanPham_ViewModel sp, String idSP) {
        sp_Repository.Update(idSP, sp);
        return sp;

    }

    @Override
    public SanPham_ViewModel DeleteModel(String idSP) {
        sp_Repository.Delete(idSP);
        return DeleteModel(idSP);

    }
    

}
