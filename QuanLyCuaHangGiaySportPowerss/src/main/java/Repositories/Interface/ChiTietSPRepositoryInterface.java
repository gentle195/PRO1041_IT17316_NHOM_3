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
import java.util.UUID;

/**
 *
 * @author dinhq
 */
public interface ChiTietSPRepositoryInterface {

    public List<ChiTietSP> getall(int min,int max);

    public void create(ChiTietSP chiTietSP) throws Exception;

    public void update(ChiTietSP chiTietSP) throws Exception;

    public void delete(ChiTietSP chiTietSP) throws Exception;

    public void updates1(ChiTietSP ct, String ma) throws Exception;

    public List<ChiTietSPViewModel> search(String ten);

    List<ChiTietSP> loc(UUID IDCL, UUID SizeID, UUID IDHang, UUID IDDe, UUID IDLoaiGiay,int tt);
    public long dem();
   
}
