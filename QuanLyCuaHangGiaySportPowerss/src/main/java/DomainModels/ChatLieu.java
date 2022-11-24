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
@Table(name = "ChatLieu")
public class ChatLieu {

    @Id
    @Column(name = "IdCL")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID IdCL;

    @Column(name = "MaCL")
    private String MaCL;

    @Column(name = "TenCL")
    private String TenCL;

    @Column(name = "MoTa")
    private String MoTaCl;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chatlieu")
    private Set<ChiTietSP> list = new HashSet<>();

    public Set<ChiTietSP> getList() {
        return list;
    }

    public void setList(Set<ChiTietSP> list) {
        this.list = list;
    }

    public ChatLieu() {
    }

    public ChatLieu(UUID IdCL, String MaCL, String TenCL, String MoTaCl) {
        this.IdCL = IdCL;
        this.MaCL = MaCL;
        this.TenCL = TenCL;
        this.MoTaCl = MoTaCl;
    }

    public UUID getIdCL() {
        return IdCL;
    }

    public void setIdCL(UUID IdCL) {
        this.IdCL = IdCL;
    }

    public String getMaCL() {
        return MaCL;
    }

    public void setMaCL(String MaCL) {
        this.MaCL = MaCL;
    }

    public String getTenCL() {
        return TenCL;
    }

    public void setTenCL(String TenCL) {
        this.TenCL = TenCL;
    }

    public String getMoTaCl() {
        return MoTaCl;
    }

    public void setMoTaCl(String MoTaCl) {
        this.MoTaCl = MoTaCl;
    }

    @Override
    public String toString() {
        return  TenCL ;
    }
    

}
