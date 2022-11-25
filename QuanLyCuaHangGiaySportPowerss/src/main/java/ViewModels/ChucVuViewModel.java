/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import DomainModels.ChatLieu;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.UUID;
import java.util.Date;
/**
 *
 * @author Admin
 */
public class ChucVuViewModel {
      private UUID IdCV;
    
  
    private String MaCV;
    
   
    private String TenCV;
    
   
    private String MoTaCV;

    public ChucVuViewModel() {
    }

    public ChucVuViewModel(UUID IdCV, String MaCV, String TenCV, String MoTaCV) {
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
        return TenCV ;
    }
    
}
