/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories.Impl;

import DomainModels.HoaDon;
import ViewModels.HoaDonBanHangViewModel;
import ViewModels.HoaDonChiTietViewModel;
import ViewModels.HoaDonViewModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author dinhq
 */
public interface HoaDonRepositoryInterface {

    ArrayList<HoaDonViewModel> all();

    List<HoaDonViewModel> ListHdSearch(Date bd, Date kt);

}
