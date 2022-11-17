/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import DomainModels.khachHang_model;
import Utilities.JDBC_Helper;
import Repositories.ikhachHang;
/**
 *
 * @author Admin
 */
public class khachHang_repos implements ikhachHang{
    @Override
    public List<khachHang_model> getALLKH() {
        List<khachHang_model> list = new ArrayList<>();
        String sql = "SELECT * FROM khachHang ";
        ResultSet rs = JDBC_Helper.Query(sql);
        try {
            while (rs.next()) {
                list.add(new khachHang_model(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
@Override
    public khachHang_model getBYmaKh(String ma ) {
    khachHang_model s = null;
        String sql = "SELECT * FROM khachhang WHERE MAKH = ?";
        ResultSet rs = JDBC_Helper.Query(sql,ma);
        try {
            while (rs.next()) {
                s =   new khachHang_model(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
            }
            return s;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
         @Override
    public  int Add(khachHang_model s ){
        String sql = "INSERT INTO khachhang(makh,tenkh,ngaysinh,sdt,diachi,thanhpho,quocgia,mota) VALUES (?,?,?,?,?,?,?,?)";
        return JDBC_Helper.Update(sql,s.getMakh(),s.getTenkh(),s.getNgaysinh(),s.getSdt(),s.getDiachi(),s.getThanhpho(),s.getQuocgia(),s.getMota());
    }
    @Override
    public  int delete(khachHang_model s ){
        String sql = "DELETE FROM khachHang WHERE makh = ?";
        return JDBC_Helper.Update(sql,s.getMakh());
    }
    @Override
    public  int Update(khachHang_model s){
        String sql = "UPDATE nhanvien SET tenkh = ? , ngaysinh= ? , sdt= ? , diachi = ? , thanhpho= ?,quocgia= ?,mota = ? WHERE Makh = ?";
        return JDBC_Helper.Update(sql,s.getTenkh(),s.getNgaysinh(),s.getSdt(),s.getDiachi(),s.getThanhpho(),s.getQuocgia(),s.getMota(),s.getMakh());
    }
//    @Override
//     public  List<nhanvien_model> SearchSP(String KeyWord) {
//        List<nhanvien_model> list = new ArrayList<>();
//        String sql = "SELECT * FROM nhanvien "
//                + "WHERE hotennv like CONCAT('%',?,'%') "
//                + " ORDER BY Manv";
//        ResultSet rs = JDBC_Helper.Query(sql,KeyWord);
//        try {
//            while (rs.next()) {
//                list.add(new nhanvien_model(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
//            }
//            return list;
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            return null;
//        }
}
