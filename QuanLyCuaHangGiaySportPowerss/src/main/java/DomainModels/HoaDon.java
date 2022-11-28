/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels;

import ViewModels.NhanVienViewModel;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author trxua
 */
public class HoaDon {

    private String IdHD;
    private NhanVienViewModel IdNV;
    private String IdKH;
    private String IdPT;
    private String maHD;
    private Date ngayTao;
    private Date ngayDat;
    private Date ngayShip;
    private Date ngayNhan;
    private Date ngayThanhToan;
    private int tinhTrang;
    private String tenNguoiNhan;
    private String diaChi;
    private String SDT;
    private String moTa;
    private BigDecimal TongTien;
    public HoaDon() {
    }

    public HoaDon(String IdHD, NhanVienViewModel IdNV, String IdKH, String IdPT, String maHD, Date ngayTao, Date ngayDat, Date ngayShip, Date ngayNhan, Date ngayThanhToan, int tinhTrang, String tenNguoiNhan, String diaChi, String SDT, String moTa, BigDecimal TongTien) {
        this.IdHD = IdHD;
        this.IdNV = IdNV;
        this.IdKH = IdKH;
        this.IdPT = IdPT;
        this.maHD = maHD;
        this.ngayTao = ngayTao;
        this.ngayDat = ngayDat;
        this.ngayShip = ngayShip;
        this.ngayNhan = ngayNhan;
        this.ngayThanhToan = ngayThanhToan;
        this.tinhTrang = tinhTrang;
        this.tenNguoiNhan = tenNguoiNhan;
        this.diaChi = diaChi;
        this.SDT = SDT;
        this.moTa = moTa;
        this.TongTien = TongTien;
    }

    public BigDecimal getTongTien() {
        return TongTien;
    }

    public void setTongTien(BigDecimal TongTien) {
        this.TongTien = TongTien;
    }

    

    public String getIdHD() {
        return IdHD;
    }

    public void setIdHD(String IdHD) {
        this.IdHD = IdHD;
    }

    public NhanVienViewModel getIdNV() {
        return IdNV;
    }

    public void setIdNV(NhanVienViewModel IdNV) {
        this.IdNV = IdNV;
    }

    public String getIdKH() {
        return IdKH;
    }

    public void setIdKH(String IdKH) {
        this.IdKH = IdKH;
    }

    public String getIdPT() {
        return IdPT;
    }

    public void setIdPT(String IdPT) {
        this.IdPT = IdPT;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
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
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getTenNguoiNhan() {
        return tenNguoiNhan;
    }

    public void setTenNguoiNhan(String tenNguoiNhan) {
        this.tenNguoiNhan = tenNguoiNhan;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public String toString() {
        return "HoaDon{" + "IdHD=" + IdHD + ", IdNV=" + IdNV + ", IdKH=" + IdKH + ", IdPT=" + IdPT + ", maHD=" + maHD + ", ngayTao=" + ngayTao + ", ngayDat=" + ngayDat + ", ngayShip=" + ngayShip + ", ngayNhan=" + ngayNhan + ", ngayThanhToan=" + ngayThanhToan + ", tinhTrang=" + tinhTrang + ", tenNguoiNhan=" + tenNguoiNhan + ", diaChi=" + diaChi + ", SDT=" + SDT + ", moTa=" + moTa + '}';
    }

}
