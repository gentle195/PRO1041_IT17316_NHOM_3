/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

import java.util.UUID;

/**
 *
 * @author TUAN ANH
 */
public class HangGiayViewModel {

    private UUID IdHang;
    private String MaHang;
    private String TenHang;
    private String MoTa;

    public HangGiayViewModel() {
    }

    public HangGiayViewModel(UUID IdHang, String MaHang, String TenHang, String MoTaHang) {
        this.IdHang = IdHang;
        this.MaHang = MaHang;
        this.TenHang = TenHang;
        this.MoTa = MoTaHang;
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
        return MoTa;
    }

    public void setMoTaHang(String MoTaHang) {
        this.MoTa = MoTaHang;
    }

}
