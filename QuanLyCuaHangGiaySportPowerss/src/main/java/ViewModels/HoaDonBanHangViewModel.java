/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

import java.util.Date;

/**
 *
 * @author dinhq
 */
public class HoaDonBanHangViewModel {
    private String MaHD;
    private Date NgayTao;
    private int TinhTrang;

    public HoaDonBanHangViewModel() {
    }

    public HoaDonBanHangViewModel(String MaHD, Date NgayTao, int TinhTrang) {
        this.MaHD = MaHD;
        this.NgayTao = NgayTao;
        this.TinhTrang = TinhTrang;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }


    public int getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(int TinhTrang) {
        this.TinhTrang = TinhTrang;
    }
    
    
    
}
