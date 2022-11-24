package DomainModels;

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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



/**
 *
 * @author dinhq
 */
@Entity
@Table(name = "HangGiay")
public class HangGiay {
    @Id
    @Column(name = "IdHang")
    @GeneratedValue (strategy = GenerationType.AUTO)
    private UUID IdHang;
    
    @Column(name = "MaHang")
    private String MaHang;
    
    @Column(name = "TenHang")
    private String TenHang;
    
    @Column(name = "MoTa")
    private String MoTaHang;
    
          @OneToMany(fetch = FetchType.LAZY, mappedBy = "hangGiay")
    private Set<ChiTietSP> list = new HashSet<>();

    public Set<ChiTietSP> getList() {
        return list;
    }

    public void setList(Set<ChiTietSP> list) {
        this.list = list;
    }

    public HangGiay() {
    }

    public HangGiay(UUID IdHang, String MaHang, String TenHang, String MoTaHang) {
        this.IdHang = IdHang;
        this.MaHang = MaHang;
        this.TenHang = TenHang;
        this.MoTaHang = MoTaHang;
    }

    public UUID getIdHang() {
        return IdHang;
    }

    public void setIdHang(UUID IdHang) {
        this.IdHang = IdHang;
    }

    public String getMaHang() {
        return MaHang;
    }

    public void setMaHang(String MaHang) {
        this.MaHang = MaHang;
    }

    public String getTenHang() {
        return TenHang;
    }

    public void setTenHang(String TenHang) {
        this.TenHang = TenHang;
    }

    public String getMoTaHang() {
        return MoTaHang;
    }

    public void setMoTaHang(String MoTaHang) {
        this.MoTaHang = MoTaHang;
    }

    
@Override
    public String toString() {
        return  TenHang ;
    }
    
    
}
