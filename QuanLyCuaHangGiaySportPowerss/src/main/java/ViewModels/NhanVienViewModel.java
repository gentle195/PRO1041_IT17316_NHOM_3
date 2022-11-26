/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

import DomainModels.ChucVu;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author Admin
 */
public class NhanVienViewModel {
    private UUID IdNV;
    private String MaNV;
    private String HoTenNV;
    private String GioiTinh;
    private Date NgaySinh;
    private String DiaChi;
    private String Sdt;
    private ChucVu chucvu;
    private String Matkhau;

    public NhanVienViewModel() {
    }

    public NhanVienViewModel(UUID IdNV, String MaNV, String HoTenNV, String GioiTinh, Date NgaySinh, String DiaChi, String Sdt, ChucVu chucvu, String Matkhau) {
        this.IdNV = IdNV;
        this.MaNV = MaNV;
        this.HoTenNV = HoTenNV;
        this.GioiTinh = GioiTinh;
        this.NgaySinh = NgaySinh;
        this.DiaChi = DiaChi;
        this.Sdt = Sdt;
        this.chucvu = chucvu;
        this.Matkhau = Matkhau;
    }

    public UUID getIdNV() {
        return IdNV;
    }

    public void setIdNV(UUID IdNV) {
        this.IdNV = IdNV;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getHoTenNV() {
        return HoTenNV;
    }

    public void setHoTenNV(String HoTenNV) {
        this.HoTenNV = HoTenNV;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String Sdt) {
        this.Sdt = Sdt;
    }

    public ChucVu getChucvu() {
        return chucvu;
    }

    public void setChucvu(ChucVu chucvu) {
        this.chucvu = chucvu;
    }

    public String getMatkhau() {
        return Matkhau;
    }

    public void setMatkhau(String Matkhau) {
        this.Matkhau = Matkhau;
    }

    @Override
    public String toString() {
        return  MaNV ;
    }
    
    
}
