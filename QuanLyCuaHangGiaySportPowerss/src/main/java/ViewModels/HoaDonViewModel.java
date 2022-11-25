/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

import java.util.Date;

/**
 *
 * @author trxua
 */
public class HoaDonViewModel {

    private int STT;
    private String maNV;
    private String tenKH;
    private String IdHD;
    private Date ngayTao;
    private Date ngayDat;
    private Date ngayShip;
    private Date ngayNhan;
    private Date ngayThanhToan;

    public HoaDonViewModel() {
    }

    public HoaDonViewModel(int STT, String maNV, String tenKH, String IdHD, Date ngayTao, Date ngayDat, Date ngayShip, Date ngayNhan, Date ngayThanhToan) {
        this.STT = STT;
        this.maNV = maNV;
        this.tenKH = tenKH;
        this.IdHD = IdHD;
        this.ngayTao = ngayTao;
        this.ngayDat = ngayDat;
        this.ngayShip = ngayShip;
        this.ngayNhan = ngayNhan;
        this.ngayThanhToan = ngayThanhToan;
    }

    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
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

    @Override
    public String toString() {
        return "HoaDonViewModel{" + "STT=" + STT + ", maNV=" + maNV + ", tenKH=" + tenKH + ", IdHD=" + IdHD + ", ngayTao=" + ngayTao + ", ngayDat=" + ngayDat + ", ngayShip=" + ngayShip + ", ngayNhan=" + ngayNhan + ", ngayThanhToan=" + ngayThanhToan + '}';
    }

}
