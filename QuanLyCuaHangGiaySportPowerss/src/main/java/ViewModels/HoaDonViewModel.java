/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author trxua
 */
public class HoaDonViewModel {

    private String maHD;
    private String maNV;
    private String maKH;
    private String tenKH;
    private String IdHD;
    private Date ngayTao;
    private Date ngayDat;
    private Date ngayShip;
    private Date ngayNhan;
    private Date ngayThanhToan;
    private int TinhTrang;
    private BigDecimal tongTien;

    public HoaDonViewModel() {
    }

    public HoaDonViewModel(String maHD, String maNV, String maKH, String tenKH, String IdHD, Date ngayTao, Date ngayDat, Date ngayShip, Date ngayNhan, Date ngayThanhToan, int TinhTrang, BigDecimal tongTien) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.IdHD = IdHD;
        this.ngayTao = ngayTao;
        this.ngayDat = ngayDat;
        this.ngayShip = ngayShip;
        this.ngayNhan = ngayNhan;
        this.ngayThanhToan = ngayThanhToan;
        this.TinhTrang = TinhTrang;
        this.tongTien = tongTien;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getIdHD() {
        return IdHD;
    }

    public void setIdHD(String IdHD) {
        this.IdHD = IdHD;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }

    public Date getNgayShip() {
        return ngayShip;
    }

    public void setNgayShip(Date ngayShip) {
        this.ngayShip = ngayShip;
    }

    public Date getNgayNhan() {
        return ngayNhan;
    }

    public void setNgayNhan(Date ngayNhan) {
        this.ngayNhan = ngayNhan;
    }

    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public int getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(int TinhTrang) {
        this.TinhTrang = TinhTrang;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    

    
}
