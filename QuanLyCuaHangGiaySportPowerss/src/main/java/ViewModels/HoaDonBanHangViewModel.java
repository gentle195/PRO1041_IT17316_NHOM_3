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
    private String MaNV;
    private int TinhTrang;

    public HoaDonBanHangViewModel() {
    }

    public HoaDonBanHangViewModel(String MaHD, Date NgayTao, String MaNV, int TinhTrang) {
        this.MaHD = MaHD;
        this.NgayTao = NgayTao;
        this.MaNV = MaNV;
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

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public int getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(int TinhTrang) {
        this.TinhTrang = TinhTrang;
    }
    
    
    
}
