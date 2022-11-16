/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

/**
 *
 * @author Hung
 */
public class PhieuBaoHanh {
    private String id;
    private String maPhieu;
    private String tenPhieu;
    private int thoiGianBaoHanh;
    private String mota;

    public PhieuBaoHanh() {
    }

    public PhieuBaoHanh(String id, String maPhieu, String tenPhieu, int thoiGianBaoHanh, String mota) {
        this.id = id;
        this.maPhieu = maPhieu;
        this.tenPhieu = tenPhieu;
        this.thoiGianBaoHanh = thoiGianBaoHanh;
        this.mota = mota;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    public String getTenPhieu() {
        return tenPhieu;
    }

    public void setTenPhieu(String tenPhieu) {
        this.tenPhieu = tenPhieu;
    }

    public int getThoiGianBaoHanh() {
        return thoiGianBaoHanh;
    }

    public void setThoiGianBaoHanh(int thoiGianBaoHanh) {
        this.thoiGianBaoHanh = thoiGianBaoHanh;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    @Override
    public String toString() {
        return "PhieuBaoHanh{" + "id=" + id + ", maPhieu=" + maPhieu + ", tenPhieu=" + tenPhieu + ", thoiGianBaoHanh=" + thoiGianBaoHanh + ", mota=" + mota + '}';
    }
    
    
    
}
