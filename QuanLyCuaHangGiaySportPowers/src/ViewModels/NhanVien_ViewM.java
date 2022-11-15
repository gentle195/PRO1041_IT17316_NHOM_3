/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

import java.util.Date;

/**
 *
 * @author admin
 */
public class NhanVien_ViewM {
    private String id;
    private String ma;
    private String hoten;
    private String gioiTing;
    private String ngaySinh;
    private String diaChi;
    private String SDT;
    private String chucVu;

    public NhanVien_ViewM() {
    }

    public NhanVien_ViewM(String id, String ma, String hoten, String gioiTing, String ngaySinh, String diaChi, String SDT, String chucVu) {
        this.id = id;
        this.ma = ma;
        this.hoten = hoten;
        this.gioiTing = gioiTing;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.SDT = SDT;
        this.chucVu = chucVu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getGioiTing() {
        return gioiTing;
    }

    public void setGioiTing(String gioiTing) {
        this.gioiTing = gioiTing;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
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

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
    
}
