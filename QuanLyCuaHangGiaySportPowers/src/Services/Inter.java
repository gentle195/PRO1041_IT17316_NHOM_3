/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;
import java.sql.*;
import DomainModels.ChucVu;
import DomainModels.NhanVien;
import ViewModels.ChucVu_ViewM;
import ViewModels.NhanVien_ViewM;
import java.util.List;
/**
 *
 * @author admin
 */
public interface Inter {
    List<ChucVu_ViewM> bangchucvu();
    List<NhanVien_ViewM> bangNV();
    ChucVu Themcv(ChucVu v);
    ChucVu Suacv(ChucVu v);
    String Xoacv(String ma);
    NhanVien SuaNV(NhanVien v);
    NhanVien ThemNV(NhanVien v);
    String XoaNV(String ma);
}
