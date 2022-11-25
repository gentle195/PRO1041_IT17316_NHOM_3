/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels;

/**
 *
 * @author Admin
 */
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
import java.util.Date;

/**
 *
 * @author dinhq
 */
@Entity
@Table(name = "NhanVien")
public class NhanVien {

    @Id
    @Column(name = "IdNV")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID IdNV;

    @Column(name = "MaNV")
    private String MaNV;

    @Column(name = "HoTenNV")
    private String HoTenNV;

    @Column(name = "GioiTinh")
    private String GioiTinh;

    @Column(name = "NgaySinh")
    private Date NgaySinh;

    @Column(name = "DiaChi")
    private String DiaChi;
    @Column(name = "Sdt")
    private String Sdt;

    @ManyToOne
    @JoinColumn(name = "IdCV", nullable = false)
    private ChucVu chucVu;
    @Column(name = "MatKhau")
    private String MatKhau;

    public NhanVien() {
    }

    public NhanVien(UUID IdNV, String MaNV, String HoTenNV, String GioiTinh, Date NgaySinh, String DiaChi, String Sdt, ChucVu chucVu, String MatKhau) {
        this.IdNV = IdNV;
        this.MaNV = MaNV;
        this.HoTenNV = HoTenNV;
        this.GioiTinh = GioiTinh;
        this.NgaySinh = NgaySinh;
        this.DiaChi = DiaChi;
        this.Sdt = Sdt;
        this.chucVu = chucVu;
        this.MatKhau = MatKhau;
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

    public ChucVu getChucVu() {
        return chucVu;
    }

    public void setChucVu(ChucVu chucVu) {
        this.chucVu = chucVu;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }
    public Object[] toDataRow (){
    return new Object[]{IdNV,MaNV,HoTenNV,GioiTinh,NgaySinh,DiaChi,Sdt,chucVu,MatKhau};
    }
}
