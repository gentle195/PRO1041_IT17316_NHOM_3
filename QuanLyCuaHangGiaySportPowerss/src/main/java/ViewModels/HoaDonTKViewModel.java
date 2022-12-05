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
public class HoaDonTKViewModel {

    private Date ngayTao;
    private int tongHD;
    private BigDecimal tongTien;
    private String ngay;

    public HoaDonTKViewModel() {
    }

    public HoaDonTKViewModel(Date ngayTao, int tongHD, BigDecimal tongTien) {
        this.ngayTao = ngayTao;
        this.tongHD = tongHD;
        this.tongTien = tongTien;
    }

    public HoaDonTKViewModel(String ngay, int tongHD, BigDecimal tongTien) {
        this.tongHD = tongHD;
        this.tongTien = tongTien;
        this.ngay = ngay;
    }

    public HoaDonTKViewModel(int tongHD, BigDecimal tongTien) {
        this.tongHD = tongHD;
        this.tongTien = tongTien;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public int getTongHD() {
        return tongHD;
    }

    public void setTongHD(int tongHD) {
        this.tongHD = tongHD;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    @Override
    public String toString() {
        return "HoaDonTKViewModel{" + "ngayTao=" + ngayTao + ", tongHD=" + tongHD + ", tongTien=" + tongTien + '}';
    }

    public Object[] toDataRow() {
        return new Object[]{ngayTao, tongHD, tongTien};
    }
}
