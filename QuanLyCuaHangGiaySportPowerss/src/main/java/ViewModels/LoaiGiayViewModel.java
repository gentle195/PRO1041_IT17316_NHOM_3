package ViewModels;


import java.util.UUID;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author dinhq
 */
public class LoaiGiayViewModel {

    private UUID IdLoai;
    private String MaLoai;
    private String TenLoai;
    private String MoTa;

    public LoaiGiayViewModel() {
    }

    public LoaiGiayViewModel(UUID IdLoai, String MaLoai, String TenLoai, String MoTa) {
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
