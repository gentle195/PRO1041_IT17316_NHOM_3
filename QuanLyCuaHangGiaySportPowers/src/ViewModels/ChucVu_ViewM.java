/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

/**
 *
 * @author admin
 */
public class ChucVu_ViewM {

    private String id;
    private String ma;
    private String ten;
    private String mota;

    public ChucVu_ViewM() {
    }

    public ChucVu_ViewM(String id, String ma, String ten, String mota) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.mota = mota;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
    @Override
    public String toString() {
        return ten;
    }
}
