/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels;

/**
 *
 * @author Admin
 */
public class khachHang_model {

    String id;
    private String makh;
    private String tenkh;
    private String ngaysinh;
    private String sdt;
    private String diachi;
    private String thanhpho;
    private String quocgia;
    private String mota;

    public khachHang_model() {
    }

    public khachHang_model(String id, String makh, String tenkh, String ngaysinh, String sdt, String diachi, String thanhpho, String quocgia, String mota) {
        this.id = id;
        this.makh = makh;
        this.tenkh = tenkh;
        this.ngaysinh = ngaysinh;
        this.sdt = sdt;
        this.diachi = diachi;
        this.thanhpho = thanhpho;
        this.quocgia = quocgia;
        this.mota = mota;
    }

    public khachHang_model(String makh, String tenkh, String ngaysinh, String sdt, String diachi, String thanhpho, String quocgia, String mota) {
        this.makh = makh;
        this.tenkh = tenkh;
        this.ngaysinh = ngaysinh;
        this.sdt = sdt;
        this.diachi = diachi;
        this.thanhpho = thanhpho;
        this.quocgia = quocgia;
        this.mota = mota;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getThanhpho() {
        return thanhpho;
    }

    public void setThanhpho(String thanhpho) {
        this.thanhpho = thanhpho;
    }

    public String getQuocgia() {
        return quocgia;
    }

    public void setQuocgia(String quocgia) {
        this.quocgia = quocgia;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    @Override
    public String toString() {
        return "khachHang_model{" + "id=" + id + ", makh=" + makh + ", tenkh=" + tenkh + ", ngaysinh=" + ngaysinh + ", sdt=" + sdt + ", diachi=" + diachi + ", thanhpho=" + thanhpho + ", quocgia=" + quocgia + ", mota=" + mota + '}';
    }
}
