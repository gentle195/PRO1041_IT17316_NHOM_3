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

public class DeGiayViewModel {
 
    private UUID IdDG;
    
  
    private String MaDG;
    
   
    private String LoaiDe;
    
   
    private String MoTaDeGiay;

    public DeGiayViewModel() {
    }

    public DeGiayViewModel(UUID IdDG, String MaDG, String LoaiDe, String MoTaDeGiay) {
        this.IdDG = IdDG;
        this.MaDG = MaDG;
        this.LoaiDe = LoaiDe;
        this.MoTaDeGiay = MoTaDeGiay;
    }

    public UUID getIdDG() {
        return IdDG;
    }

    public void setIdDG(UUID IdDG) {
        this.IdDG = IdDG;
    }

    public String getMaDG() {
        return MaDG;
    }

    public void setMaDG(String MaDG) {
        this.MaDG = MaDG;
    }

    public String getLoaiDe() {
        return LoaiDe;
    }

    public void setLoaiDe(String LoaiDe) {
        this.LoaiDe = LoaiDe;
    }

    public String getMoTaDeGiay() {
        return MoTaDeGiay;
    }

    public void setMoTaDeGiay(String MoTaDeGiay) {
        this.MoTaDeGiay = MoTaDeGiay;
    }

    
    

   

    
    
}
