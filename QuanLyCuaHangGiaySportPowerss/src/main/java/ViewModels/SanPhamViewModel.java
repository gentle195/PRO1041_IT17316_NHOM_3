package ViewModels;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



import java.util.UUID;

/**
 *
 * @author dinhq
 */

public class SanPhamViewModel {
    
    private UUID IdSP;
    
 
    private String MaSP;
    
  
    private String TenSP;
    
  
    private String MoTa;

    public SanPhamViewModel() {
    }

    public SanPhamViewModel(UUID IdSP, String MaSP, String TenSP, String MoTa) {
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

    
}
