/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels;

import java.math.BigDecimal;

/**
 *
 * @author trxua
 */
public class HoaDonChiTiet {

    private String IdHDCT;
    private HoaDon IdHD;
    private ChiTietSP IdChiTietSP;
    private int soLuong;
    private BigDecimal donGia;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(String IdHDCT, HoaDon IdHD, ChiTietSP IdChiTietSP, int soLuong, BigDecimal donGia) {
        this.IdHDCT = IdHDCT;
        this.IdHD = IdHD;
        this.IdChiTietSP = IdChiTietSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public String getIdHDCT() {
        return IdHDCT;
    }

    public void setIdHDCT(String IdHDCT) {
        this.IdHDCT = IdHDCT;
    }

    public HoaDon getIdHD() {
        return IdHD;
    }

    public void setIdHD(HoaDon IdHD) {
        this.IdHD = IdHD;
    }

    public ChiTietSP getIdChiTietSP() {
        return IdChiTietSP;
    }

    public void setIdChiTietSP(ChiTietSP IdChiTietSP) {
        this.IdChiTietSP = IdChiTietSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }
    
    @Override
    public String toString() {
        return "HoaDonChiTiet{" + "IdHDCT=" + IdHDCT + ", IdHD=" + IdHD + ", IdChiTietSP=" + IdChiTietSP + ", soLuong=" + soLuong + ", donGia=" + donGia + '}';
    }

}
