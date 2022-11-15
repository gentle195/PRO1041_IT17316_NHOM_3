/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.Impl;
import java.sql.*;
import DomainModels.ChucVu;
import DomainModels.NhanVien;
import Repositories.Repo;
import Services.Inter;
import ViewModels.ChucVu_ViewM;
import ViewModels.NhanVien_ViewM;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class Service implements Inter{
    public final Repo res;

    public Service() {
        res = new Repo();
    }
    @Override
    public List<ChucVu_ViewM> bangchucvu() {
        return  res.bangchucvu();
    }

    @Override
    public List<NhanVien_ViewM> bangNV() {
        return res.bangNV();
    }

    @Override
    public ChucVu Themcv(ChucVu v) {
        JOptionPane.showMessageDialog(null, "Thêm thành công");
        res.Themcv(v);
        return v;
    }
    

    @Override
    public ChucVu Suacv(ChucVu v) {
        JOptionPane.showMessageDialog(null, "Sửa thành công");
        res.Suacv(v);
        return v;
    }

    @Override
    public String Xoacv(String ma) {
        return res.Xoacv(ma);
    }

    @Override
    public NhanVien SuaNV(NhanVien v) {
        JOptionPane.showMessageDialog(null, "Sửa thành công");
        res.SuaNV(v);
        return v;
    }

    @Override
    public NhanVien ThemNV(NhanVien v) {
        JOptionPane.showMessageDialog(null, "Thêm thành công");
        res.ThemNV(v);
        return v;
    }

    @Override
    public String XoaNV(String ma) {
        return res.XoaNV(ma);
    }
    
}
