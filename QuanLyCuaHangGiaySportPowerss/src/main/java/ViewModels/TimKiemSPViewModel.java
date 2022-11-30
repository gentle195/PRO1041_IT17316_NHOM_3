/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

import DomainModels.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import DomainModels.ChatLieu;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.UUID;

/**
 *
 * @author dinhq
 */
public class TimKiemSPViewModel {

 

    private String sanPham;

    private String hangGiay;

    private String chatlieu;

    private String deGiay;

    private String size;

    private String loaigiay;

   

    public TimKiemSPViewModel() {
    }

    public TimKiemSPViewModel(String sanPham, String hangGiay, String chatlieu, String deGiay, String size, String loaigiay) {
        this.sanPham = sanPham;
        this.hangGiay = hangGiay;
        this.chatlieu = chatlieu;
        this.deGiay = deGiay;
        this.size = size;
        this.loaigiay = loaigiay;
    }

    public String getSanPham() {
        return sanPham;
    }

    public void setSanPham(String sanPham) {
        this.sanPham = sanPham;
    }

    public String getHangGiay() {
        return hangGiay;
    }

    public void setHangGiay(String hangGiay) {
        this.hangGiay = hangGiay;
    }

    public String getChatlieu() {
        return chatlieu;
    }

    public void setChatlieu(String chatlieu) {
        this.chatlieu = chatlieu;
    }

    public String getDeGiay() {
        return deGiay;
    }

    public void setDeGiay(String deGiay) {
        this.deGiay = deGiay;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getLoaigiay() {
        return loaigiay;
    }

    public void setLoaigiay(String loaigiay) {
        this.loaigiay = loaigiay;
    }

    

}
