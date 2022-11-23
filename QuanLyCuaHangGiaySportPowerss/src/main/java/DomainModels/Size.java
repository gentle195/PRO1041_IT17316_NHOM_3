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
@Table(name = "Size")
public class Size {

    @Id
    @Column(name = "IdSize")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID IdSize;

    @Column(name = "MaSize")
    private String MaSize;

    @Column(name = "SoSize")
    private int SoSize;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "size")
    private Set<ChiTietSP> list = new HashSet<>();

    public Set<ChiTietSP> getList() {
        return list;
    }

    public void setList(Set<ChiTietSP> list) {
        this.list = list;
    }

    public Size() {
    }

    public Size(UUID IdSize, String MaSize, int SoSize) {
        this.IdSize = IdSize;
        this.MaSize = MaSize;
        this.SoSize = SoSize;
    }

    public UUID getIdSize() {
        return IdSize;
    }

    public void setIdSize(UUID IdSize) {
        this.IdSize = IdSize;
    }

    public String getMaSize() {
        return MaSize;
    }

    public void setMaSize(String MaSize) {
        this.MaSize = MaSize;
    }

    public int getSoSize() {
        return SoSize;
    }

    public void setSoSize(int SoSize) {
        this.SoSize = SoSize;
    }

}
