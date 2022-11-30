package Repositories.Impl;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

import DomainModels.ChatLieu;
import DomainModels.HoaDon;
import ViewModels.HoaDonBanHangViewModel;
import ViewModels.HoaDonViewModel;

import java.util.List;

/**
 *
 * @author dinhq
 */
public interface HoaDonBanHangRepositoryInterface {

    public List<HoaDonBanHangViewModel> all();

    public void add(HoaDon hoaDon) throws Exception;

    public void update(HoaDon hoaDon, String ma, String ma1) throws Exception;

    public void delete(HoaDon hoaDon) throws Exception;
}
