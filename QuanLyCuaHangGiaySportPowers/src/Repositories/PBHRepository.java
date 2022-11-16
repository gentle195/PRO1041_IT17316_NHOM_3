/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import db.DBConnection1;
import java.util.List;
import viewmodel.PhieuBaoHanh;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Hung
 */
public class PBHRepository {

    public List<PhieuBaoHanh> getAll() {
        String sql = "SELECT IdPBH, MaPhieu,TenPhieu,ThoiGianBaoHanh,MoTa FROM PhieuBaoHanh";
        try (Connection con = DBConnection1.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            List<PhieuBaoHanh> list = new ArrayList<>();

            while (rs.next()) {
                list.add(new PhieuBaoHanh(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public boolean add(PhieuBaoHanh pbh) {
        String sql = "INSERT INTO PhieuBaoHanh ( MaPhieu,TenPhieu,ThoiGianBaoHanh,MoTa) VALUES(?,?,?,?)";
        int check = 0;
        try (Connection con = DBConnection1.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, pbh.getMaPhieu());
            ps.setObject(2, pbh.getTenPhieu());
            ps.setObject(3, pbh.getThoiGianBaoHanh());
            ps.setObject(4, pbh.getMota());
            check = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check>0;
    }
    public boolean update(PhieuBaoHanh pbh,String id) {
        String sql = " UPDATE PhieubaoHanh set MaPhieu=?,TenPhieu=?,ThoiGianBaoHanh=?,MoTa=? WHERE IdPBH =? " ;
        int check = 0;
        try (Connection con = DBConnection1.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, pbh.getMaPhieu());
            ps.setObject(2, pbh.getTenPhieu());
            ps.setObject(3, pbh.getThoiGianBaoHanh());
            ps.setObject(4, pbh.getMota());
            ps.setObject(5, id);
            check = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check>0;
    }
    public boolean delete(String id) {
        String sql = " DELETE FROM PhieubaoHanh WHERE IdPBH =? " ;
        int check = 0;
        try (Connection con = DBConnection1.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1,id);
            check = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check>0;
    }
    public static void main(String[] args) {
        System.out.println(new PBHRepository().getAll());
    }
}
