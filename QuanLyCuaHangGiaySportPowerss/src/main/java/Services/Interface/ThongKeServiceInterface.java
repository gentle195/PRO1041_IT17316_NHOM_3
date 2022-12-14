/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services.Interface;

import ViewModels.ChiTietSPViewModel;
import ViewModels.HoaDonTKViewModel;
import java.util.Date;
import java.util.List;

/**
 *
 * @author trxua
 */
public interface ThongKeServiceInterface {

    public List<HoaDonTKViewModel> thongKeHD();

    public List<ChiTietSPViewModel> thongKeSP();

    public List<HoaDonTKViewModel> tkHDpM(Date bd, Date kt);

    public List<ChiTietSPViewModel> tkSPpM(Date bd, Date kt);

    public List<HoaDonTKViewModel> tkTHD();

    public List<HoaDonTKViewModel> tkTDT();

    public List<HoaDonTKViewModel> tkTDTpM(Date bd);
}
