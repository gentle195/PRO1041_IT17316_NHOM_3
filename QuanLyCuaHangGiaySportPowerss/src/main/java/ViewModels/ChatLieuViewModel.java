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

public class ChatLieuViewModel {

    private UUID IdCL;
    
  
    private String MaCL;
    
   
    private String TenCL;
    
   
    private String MoTaCL;

    public ChatLieuViewModel() {
    }

    public ChatLieuViewModel(UUID IdCL, String MaCL, String TenCL, String MoTaCL) {
        this.IdCL = IdCL;
        this.MaCL = MaCL;
        this.TenCL = TenCL;
        this.MoTaCL = MoTaCL;
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

    public String getMoTaCL() {
        return MoTaCL;
    }

    public void setMoTaCL(String MoTaCL) {
        this.MoTaCL = MoTaCL;
    }

    

    
    
}
