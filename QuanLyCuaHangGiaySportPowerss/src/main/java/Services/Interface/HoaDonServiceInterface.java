/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services.Interface;

import DomainModels.HoaDon;
import ViewModels.HoaDonBanHangViewModel;
import ViewModels.HoaDonChiTietViewModel;
import ViewModels.HoaDonViewModel;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author nguyenvv
 */
public interface HoaDonServiceInterface {

    public List<HoaDonViewModel> all();

    List<HoaDonChiTietViewModel> getListById(String idhoaDon) throws SQLException;

    List<HoaDonViewModel> ListHdSearch(Date bd, Date kt);
}
