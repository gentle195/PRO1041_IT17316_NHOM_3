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
@Table(name = "SanPham")
public class SanPham {
    @Id
    @Column(name = "IdSP")
    @GeneratedValue (strategy = GenerationType.AUTO)
    private UUID IdSP;
    
    @Column(name = "MaSP")
    private String MaSP;
    
    @Column(name = "TenSP")
    private String TenSP;
    
    @Column(name = "MoTa")
    private String MoTa;
    
         @OneToMany(fetch = FetchType.LAZY, mappedBy = "sanPham")
    private Set<ChiTietSP> list = new HashSet<>();

    public Set<ChiTietSP> getList() {
        return list;
    }

    public void setList(Set<ChiTietSP> list) {
        this.list = list;
    }

    public SanPham() {
    }

    public SanPham(UUID IdSP, String MaSP, String TenSP, String MoTa) {
        this.IdSP = IdSP;
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.MoTa = MoTa;
    }

    public UUID getIdSP() {
        return IdSP;
    }

    public void setIdSP(UUID IdSP) {
        this.IdSP = IdSP;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }

    @Override
    public String toString() {
        return  MaSP ;
    }

    
}
