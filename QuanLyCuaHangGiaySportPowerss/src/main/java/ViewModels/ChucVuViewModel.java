/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

/**
 *
 * @author Admin
 */
import java.util.UUID;
public class ChucVuViewModel {
        
  private UUID IdCV;
    private String MaVC;
    
   
    private String TenCV;
    
   
    private String MoTaCV;

    public ChucVuViewModel() {
    }

    public ChucVuViewModel(UUID IdCV, String MaVC, String TenCV, String MoTaCV) {
        this.IdCV = IdCV;
        this.MaVC = MaVC;
        this.TenCV = TenCV;
        this.MoTaCV = MoTaCV;
    }

    public UUID getIdCV() {
        return IdCV;
    }

    public void setIdCV(UUID IdCV) {
        this.IdCV = IdCV;
    }

    public String getMaVC() {
        return MaVC;
    }

    public void setMaVC(String MaVC) {
        this.MaVC = MaVC;
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
    
    
}
