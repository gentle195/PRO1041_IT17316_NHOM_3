package DomainModels;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 *
 * @author dinhq
 */
@Entity
@Table(name = "LoaiGiay")
public class LoaiGiay {
    @Id
    @Column(name = "IdLoai")
    @GeneratedValue (strategy = GenerationType.AUTO)
    private UUID IdLoai;
    
    @Column(name = "MaLoai")
    private String MaLoai;
    
    @Column(name = "TenLoai")
    private String TenLoai;
    
    @Column(name = "MoTa")
    private String MoTa;

    
      @OneToMany(fetch = FetchType.LAZY, mappedBy = "loaigiay")
    private Set<ChiTietSP> list = new HashSet<>();

    public Set<ChiTietSP> getList() {
        return list;
    }

    public void setList(Set<ChiTietSP> list) {
        this.list = list;
    }
    public LoaiGiay() {
    }

    public LoaiGiay(UUID IdLoai, String MaLoai, String TenLoai, String MoTa) {
        this.IdLoai = IdLoai;
        this.MaLoai = MaLoai;
        this.TenLoai = TenLoai;
        this.MoTa = MoTa;
    }

    public UUID getIdLoai() {
        return IdLoai;
    }

    public void setIdLoai(UUID IdLoai) {
        this.IdLoai = IdLoai;
    }

    public String getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(String MaLoai) {
        this.MaLoai = MaLoai;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String TenLoai) {
        this.TenLoai = TenLoai;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }

    
}
