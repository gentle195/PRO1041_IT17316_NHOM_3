/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels;

/**
 *
 * @author dinhq
 */
public class HoaDon {
    private String idHD, idKH, idNV, idPT, Ma, NgayTao, NgayThanhToan, NgayShip, NgayNhan, tenNgNhan, diaChi, sdt;
    private int TinhTrang;

    public HoaDon() {
    }

    public HoaDon(String idHD, String idKH, String idNV, String idPT, String Ma, String NgayTao, String NgayThanhToan, String NgayShip, String NgayNhan, String tenNgNhan, String diaChi, String sdt, int TinhTrang) {
        this.idHD = idHD;
        this.idKH = idKH;
        this.idNV = idNV;
        this.idPT = idPT;
        this.Ma = Ma;
        this.NgayTao = NgayTao;
        this.NgayThanhToan = NgayThanhToan;
        this.NgayShip = NgayShip;
        this.NgayNhan = NgayNhan;
        this.tenNgNhan = tenNgNhan;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.TinhTrang = TinhTrang;
    }

    public String getIdHD() {
        return idHD;
    }

    public void setIdHD(String idHD) {
        this.idHD = idHD;
    }

    public String getIdKH() {
        return idKH;
    }

    public void setIdKH(String idKH) {
        this.idKH = idKH;
    }

    public String getIdNV() {
        return idNV;
    }

    public void setIdNV(String idNV) {
        this.idNV = idNV;
    }

    public String getIdPT() {
        return idPT;
    }

    public void setIdPT(String idPT) {
        this.idPT = idPT;
    }

    public String getMa() {
        return Ma;
    }

    public void setMa(String Ma) {
        this.Ma = Ma;
    }

    public String getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(String NgayTao) {
        this.NgayTao = NgayTao;
    }

    public String getNgayThanhToan() {
        return NgayThanhToan;
    }

    public void setNgayThanhToan(String NgayThanhToan) {
        this.NgayThanhToan = NgayThanhToan;
    }

    public String getNgayShip() {
        return NgayShip;
    }

    public void setNgayShip(String NgayShip) {
        this.NgayShip = NgayShip;
    }

    public String getNgayNhan() {
        return NgayNhan;
    }

    public void setNgayNhan(String NgayNhan) {
        this.NgayNhan = NgayNhan;
    }

    public String getTenNgNhan() {
        return tenNgNhan;
    }

    public void setTenNgNhan(String tenNgNhan) {
        this.tenNgNhan = tenNgNhan;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public int getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(int TinhTrang) {
        this.TinhTrang = TinhTrang;
    }
    
    
}
