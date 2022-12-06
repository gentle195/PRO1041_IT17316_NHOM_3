/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels;

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
@Entity
@Table(name = "ChiTietSP")
public class ChiTietSP {

    @Id
    @Column(name = "IdCTSP")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID IdCTSP;

    @ManyToOne
    @JoinColumn(name = "IdSP", nullable = false)
    private SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "IdHang", nullable = false)
    private HangGiay hangGiay;

    @ManyToOne
    @JoinColumn(name = "IdCL", nullable = false)
    private ChatLieu chatlieu;

    @ManyToOne
    @JoinColumn(name = "IdDeGiay", nullable = false)
    private DeGiay deGiay;

    @ManyToOne
    @JoinColumn(name = "IdSize", nullable = false)
    private Size size;

    @ManyToOne
    @JoinColumn(name = "IdLoai", nullable = false)
    private LoaiGiay loaigiay;

    @Column(name = "SoLuong")
    private int SoLuong;

    @Column(name = "DonGia")
    private BigDecimal DonGia;

    @Column(name = "TrongLuong")
    private int TrongLuong;

    @Column(name = "TrangThai")
    private int TrangThai;

    @Column(name = "MoTa")
    private String MoTa;

    public ChiTietSP() {
    }

    public ChiTietSP(UUID IdCTSP, SanPham sanPham, HangGiay hangGiay, ChatLieu chatlieu, DeGiay deGiay, Size size, LoaiGiay loaigiay, int SoLuong, BigDecimal DonGia, int TrongLuong, int TrangThai, String MoTa) {
        this.IdCTSP = IdCTSP;
        this.sanPham = sanPham;
        this.hangGiay = hangGiay;
        this.chatlieu = chatlieu;
        this.deGiay = deGiay;
        this.size = size;
        this.loaigiay = loaigiay;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
        this.TrongLuong = TrongLuong;
        this.TrangThai = TrangThai;
        this.MoTa = MoTa;
    }

    public UUID getIdCTSP() {
        return IdCTSP;
    }

    public void setIdCTSP(UUID IdCTSP) {
        this.IdCTSP = IdCTSP;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public HangGiay getHangGiay() {
        return hangGiay;
    }

    public void setHangGiay(HangGiay hangGiay) {
        this.hangGiay = hangGiay;
    }

    public ChatLieu getChatlieu() {
        return chatlieu;
    }

    public void setChatlieu(ChatLieu chatlieu) {
        this.chatlieu = chatlieu;
    }

    public DeGiay getDeGiay() {
        return deGiay;
    }

    public void setDeGiay(DeGiay deGiay) {
        this.deGiay = deGiay;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public LoaiGiay getLoaigiay() {
        return loaigiay;
    }

    public void setLoaigiay(LoaiGiay loaigiay) {
        this.loaigiay = loaigiay;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public BigDecimal getDonGia() {
        return DonGia;
    }

    public void setDonGia(BigDecimal DonGia) {
        this.DonGia = DonGia;
    }

    public int getTrongLuong() {
        return TrongLuong;
    }

    public void setTrongLuong(int TrongLuong) {
        this.TrongLuong = TrongLuong;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }
    
    

}
