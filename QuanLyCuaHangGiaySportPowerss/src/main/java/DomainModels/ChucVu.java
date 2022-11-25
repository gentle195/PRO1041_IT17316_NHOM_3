/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
/**
 *
 * @author Admin
 */
@Entity
@Table(name = "ChucVu")
public class ChucVu {
    
    @Id
    @Column(name = "IdCV")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID IdCV;

    @Column(name = "MaCV")
    private String MaCV;

    @Column(name = "TenCV")
    private String TenCV;

    @Column(name = "MoTa")
    private String MoTaCV;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chucvu")
    private Set<NhanVien> list = new HashSet<>();

    public Set<NhanVien> getList() {
        return list;
    }

    public void setList(Set<NhanVien> list) {
        this.list = list;
    }

    public ChucVu() {
    }

    public ChucVu(UUID IdCV, String MaCV, String TenCV, String MoTaCV) {
        this.IdCV = IdCV;
        this.MaCV = MaCV;
        this.TenCV = TenCV;
        this.MoTaCV = MoTaCV;
    }

    public UUID getIdCV() {
        return IdCV;
    }

    public void setIdCV(UUID IdCV) {
        this.IdCV = IdCV;
    }

    public String getMaCV() {
        return MaCV;
    }

    public void setMaCV(String MaCV) {
        this.MaCV = MaCV;
    }

    public String getTenCV() {
        return TenCV;
    }

    public void setTenCV(String TenCV) {
        this.TenCV = TenCV;
    }

    public String getMoTaCV() {
        return MoTaCV;
    }

    public void setMoTaCV(String MoTaCV) {
        this.MoTaCV = MoTaCV;
    }

    @Override
    public String toString() {
        return  TenCV ;
    }
    
}
