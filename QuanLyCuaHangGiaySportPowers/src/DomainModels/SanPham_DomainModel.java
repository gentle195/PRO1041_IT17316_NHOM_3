/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels;

/**
 *
 * @author TUAN ANH
 */
public class SanPham_DomainModel {
   private String IdSP;
   private  String MaSP,TenSP;
   private String MoTa;
   
    public SanPham_DomainModel() {
    }

    public SanPham_DomainModel(String IdSP, String MaSP, String TenSP, String MoTa) {
        this.IdSP = IdSP;
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.MoTa = MoTa;
    }

    public String getIdSP() {
        return IdSP;
    }

    public void setIdSP(String IdSP) {
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
