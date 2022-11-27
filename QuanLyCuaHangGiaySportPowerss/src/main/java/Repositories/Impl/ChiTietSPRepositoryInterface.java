package Repositories.Impl;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
import DomainModels.ChatLieu;
import DomainModels.ChiTietSP;
import DomainModels.ChiTietSP1;
import DomainModels.SanPham;
import ViewModels.ChiTietSPViewModel;

import java.util.List;

/**
 *
 * @author dinhq
 */
public interface ChiTietSPRepositoryInterface {

    public List<ChiTietSP> getall();

    public void create(ChiTietSP chiTietSP) throws Exception;

    public void update(ChiTietSP chiTietSP) throws Exception;

    public void delete(ChiTietSP chiTietSP) throws Exception;

    public void updates1(ChiTietSP ct,String ma) throws Exception;
}
