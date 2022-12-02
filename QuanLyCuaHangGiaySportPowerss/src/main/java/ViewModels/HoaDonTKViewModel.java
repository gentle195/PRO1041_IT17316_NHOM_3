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

    public HoaDonTKViewModel() {
    }

    public HoaDonTKViewModel(Date ngayTao, int tongHD, BigDecimal tongTien) {
        this.ngayTao = ngayTao;
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

    @Override
    public String toString() {
        return "HoaDonTKViewModel{" + "ngayTao=" + ngayTao + ", tongHD=" + tongHD + ", tongTien=" + tongTien + '}';
    }

    public Object[] toDataRow() {
        return new Object[]{ngayTao, tongHD, tongTien};
    }
}
