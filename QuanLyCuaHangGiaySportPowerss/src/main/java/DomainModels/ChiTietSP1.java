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
public class ChiTietSP1 {

    @Id
    @Column(name = "IdCTSP")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID IdCTSP;

    @ManyToOne
    @JoinColumn(name = "IdSP", nullable = false)
    private SanPham sanPham1;

   
    @Column(name = "SoLuong")
    private int SoLuong;

    @Column(name = "DonGia")
    private BigDecimal DonGia;

    @Column(name = "TrangThai")
    private int TrangThai;

    @Column(name = "MoTa")
    private String MoTa;

    public ChiTietSP1() {
    }

    public ChiTietSP1(UUID IdCTSP, SanPham sanPham1, int SoLuong, BigDecimal DonGia, int TrangThai, String MoTa) {
        this.IdCTSP = IdCTSP;
        this.sanPham1 = sanPham1;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
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
        return sanPham1;
    }

    public void setSanPham(SanPham sanPham2) {
        this.sanPham1 = sanPham1;
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
