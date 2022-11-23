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
@Table(name = "DeGiay")
public class DeGiay {
    @Id
    @Column(name = "IdDG")
    @GeneratedValue (strategy = GenerationType.AUTO)
    private UUID IdDG;
    
    @Column(name = "MaDG")
    private String MaDG;
    
    @Column(name = "LoaiDe")
    private String LoaiDe;
    
    @Column(name = "MoTa")
    private String MoTaDG;
    
         @OneToMany(fetch = FetchType.LAZY, mappedBy = "deGiay")
    private Set<ChiTietSP> list = new HashSet<>();

    public Set<ChiTietSP> getList() {
        return list;
    }

    public void setList(Set<ChiTietSP> list) {
        this.list = list;
    }

    public DeGiay() {
    }

    public UUID getIdDG() {
        return IdDG;
    }

    public void setIdDG(UUID IdDG) {
        this.IdDG = IdDG;
    }

    public String getMaCL() {
        return MaDG;
    }

    public void setMaCL(String MaDG) {
        this.MaDG = MaDG;
    }

    public String getLoaiDe() {
        return LoaiDe;
    }

    public void setLoaiDe(String LoaiDe) {
        this.LoaiDe = LoaiDe;
    }

    public String getMoTaDG() {
        return MoTaDG;
    }

    public void setMoTaDG(String MoTaDG) {
        this.MoTaDG = MoTaDG;
    }

    public DeGiay(UUID IdDG, String MaDG, String LoaiDe, String MoTaDG) {
        this.IdDG = IdDG;
        this.MaDG = MaDG;
        this.LoaiDe = LoaiDe;
        this.MoTaDG = MoTaDG;
    }


    

    
    
}
