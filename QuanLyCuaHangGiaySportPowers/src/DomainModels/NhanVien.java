/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels;

import java.util.Date;

/**
 *
 * @author admin
 */
public class NhanVien {
    private String id;
    private String ma;
    private String hoten;
    private String gioiTing;
    private String ngaySinh;
    private String diaChi;
    private String SDT;
    private String chucVu;
    private String idCV;

    public NhanVien() {
    }

    public NhanVien(String id, String ma, String hoten, String gioiTing, String ngaySinh, String diaChi, String SDT, String chucVu, String idCV) {
        this.id = id;
        this.ma = ma;
        this.hoten = hoten;
        this.gioiTing = gioiTing;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.SDT = SDT;
        this.chucVu = chucVu;
        this.idCV = idCV;
    }

    

    public String getIdCV() {
        return idCV;
    }

    public void setIdCV(String idCV) {
        this.idCV = idCV;
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
