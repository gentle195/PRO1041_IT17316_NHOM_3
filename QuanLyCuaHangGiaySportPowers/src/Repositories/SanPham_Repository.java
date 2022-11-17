/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;


import Utilities.DBContect;
import ViewModels.SanPham_ViewModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author TUAN ANH
 */
public class SanPham_Repository {
    public  List<SanPham_ViewModel> getAll(){
        List<SanPham_ViewModel> sp= new ArrayList<>();
        
        try {
           String sql = "Select IdSP,MaSP,TenSP,MoTa from SanPham";
            Connection con = DBContect.getConnection();
            PreparedStatement ptm = con.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            
            while(rs.next()){
                sp.add(new SanPham_ViewModel(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
  
            }
            
        } catch (Exception e) {
        e.printStackTrace();
        }
        return sp;
    }
    
    public void add(SanPham_ViewModel sp){
        try {
            String sql = "Insert into SanPham(MaSP,TenSP,MoTa)" +"values(?,?,?)";
            Connection con = DBContect.getConnection();
            PreparedStatement ptm = con.prepareStatement(sql);
          //  ptm.setString(1, sp.getIdSP());
            ptm.setString(1, sp.getMaSP());
            ptm.setString(2, sp.getTenSP());
            ptm.setString(3, sp.getMoTa());
            ptm.executeUpdate();          
                               
        } catch (Exception e) {
        e.printStackTrace();
        }
    }
    public void Delete(String IdSP){
        try {
            String sql = "Delete From SanPham where IdSP=?";
            Connection con =DBContect.getConnection();
            PreparedStatement ptm = con.prepareStatement(sql);
            ptm.setString(1,IdSP);
            
            ptm.executeUpdate();             
        } catch (Exception e) {
        }
    }
    
    public void Update(String IdSP, SanPham_ViewModel sp){
        try {
            String sql= "update SanPham set MaSP=?,TenSP=?, MoTa=? where IdSP=?";
            Connection con = DBContect.getConnection();
            PreparedStatement ptm = con.prepareStatement(sql);
            ptm.setString(1, sp.getMaSP());
            ptm.setString(2, sp.getTenSP());
            ptm.setString(3, sp.getMoTa());
            ptm.setString(4, IdSP);
            
            ptm.executeUpdate();
            
        } catch (Exception e) {
        e.printStackTrace();
        }
        
        
    }
 /*   public List<SanPham_ViewModel> find(String MaSP,String TenSP){
        List<SanPham_ViewModel> sp = new ArrayList<>();
        for (SanPham_ViewModel sanPham_ViewModel : sp) {
            if(sanPham_ViewModel.getMaSP().equalsIgnoreCase(MaSP)||sanPham_ViewModel.getTenSP().equalsIgnoreCase(TenSP)){
                sp.add(sanPham_ViewModel);
            }
            
        }
        return sp;
    }
    */
    
    
    
}
