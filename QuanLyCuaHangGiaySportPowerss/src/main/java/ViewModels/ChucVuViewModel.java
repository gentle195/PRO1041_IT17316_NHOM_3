/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

import java.util.UUID;

/**
 *
 * @author Admin
 */
public class ChucVuViewModel {
    private UUID IdCV;
    private String MaCV;
    private String TenCV;

    public ChucVuViewModel() {
    }

    public ChucVuViewModel(UUID IdCV, String MaCV, String TenCV) {
        this.IdCV = IdCV;
        this.MaCV = MaCV;
        this.TenCV = TenCV;
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
    
}
