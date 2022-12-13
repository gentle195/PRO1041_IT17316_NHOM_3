package Repositories;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import DomainModels.ChiTietSP;
import Utilities.JpaUtil;
import DomainModels.SanPham;
import Repositories.Impl.ChiTietSPRepositoryInterface;
import Utilities.DBConnection;
import ViewModels.ChiTietSPViewModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dinhq
 */
public class ChiTietSPRepository implements ChiTietSPRepositoryInterface {

    private EntityManager em;
    private static final Connection con = DBConnection.getConnection();

    public ChiTietSPRepository() {
        this.em = JpaUtil.getEntityManager();
    }

    @Override
    public List<ChiTietSP> getall(int min, int max) {
        String jpql = "SELECT cate FROM ChiTietSP cate";
        TypedQuery<ChiTietSP> query = this.em.createQuery(jpql, ChiTietSP.class);
        query.setFirstResult(min);
        query.setMaxResults(max);
        this.em.clear();
        return query.getResultList();
    }

    @Override
    public void create(ChiTietSP chiTietSP) throws Exception {
        try {
            this.em.getTransaction().begin();
            this.em.persist(chiTietSP);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void update(ChiTietSP chiTietSP) throws Exception {
        try {
            this.em.getTransaction().begin();
            this.em.merge(chiTietSP);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void delete(ChiTietSP chiTietSP) throws Exception {
        try {
            this.em.getTransaction().begin();
            ChiTietSP s = em.merge(chiTietSP);
            this.em.remove(s);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }

    }

    @Override
    public void updates1(ChiTietSP ct, String ma) throws Exception {
        try {
            Connection conn = DBConnection.getConnection();
            String insert = "update ChiTietSp set SoLuong=?,TrangThai=? where MaSP=?";
//                    "update  ChiTietHoaDon set SoLuong=? where IDChiTietSP like(Select IDCTSP from ChiTietSP CT left join SanPham SP on CT.IdSP=SP.IdSP where MaSP=?)";
            PreparedStatement ps = conn.prepareStatement(insert);
            ps.setObject(1, ct.getSoLuong());
            ps.setObject(2, ct.getTrangThai());
            ps.setObject(3, ma);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietSPRepository.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public List<ChiTietSPViewModel> search(String ten) {
        String sql = "select SanPham.MaSP, SanPham.TenSP, DonGia, SoLuong, TrangThai "
                + "from ChiTietSP join SanPham on ChiTietSP.IdSP= SanPham.IdSP "
                + "where TenSP = ?";
        try ( Connection conn = DBConnection.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            List<ChiTietSPViewModel> lstCTSPVM = new ArrayList<>();
            ps.setObject(1, ten);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham(rs.getString(1), rs.getString(2));
                lstCTSPVM.add(new ChiTietSPViewModel(sp, rs.getBigDecimal(3), rs.getInt(4), rs.getInt(5)));
                return lstCTSPVM;
            }
        } catch (Exception e) {

        }
        return null;
    }

    public List<ChiTietSP> loc(UUID IDCL, UUID SizeID, UUID IDHang, UUID IDDe, UUID IDLoaiGiay, int tt) {
        String sql = "select ct from ChiTietSP ct\n"
                + "left join ct.hangGiay\n"
                + "left join ct.chatlieu\n"
                + "left join ct.deGiay\n"
                + "left JOin ct.loaigiay\n"
                + "left JOin ct.size\n";
        //  + "where ct.size.IdSize=?1 and ct.hangGiay.IdHang=?2 and ct.chatlieu.IdCL=?3 and ct.deGiay.IdDG=?4 and ct.loaigiay.IdLoai=?5 ";
        boolean loc = true;
        if (IDCL != null) {
            if (loc) {
                loc = false;
                sql += " where ct.chatlieu.IdCL=:cl";
            } else {
                sql += " and ct.chatlieu.IdCL=:cl";
            }
        }
        if (SizeID != null) {
            if (loc) {
                loc = false;
                sql += " where ct.size.IdSize=:size";
            } else {
                sql += " and ct.size.IdSize=:size";
            }
        }
        if (IDHang != null) {
            if (loc) {
                loc = false;
                sql += " where ct.hangGiay.IdHang=:h";
            } else {
                sql += " and ct.hangGiay.IdHang=:h";
            }
        }
        if (IDLoaiGiay != null) {
            if (loc) {
                loc = false;
                sql += " where ct.loaigiay.IdLoai=:l";
            } else {
                sql += " and ct.loaigiay.IdLoai=:l";
            }
        }
        if (IDDe != null) {
            if (loc) {
                loc = false;
                sql += " where ct.deGiay.IdDG=:d";
            } else {
                sql += " and ct.deGiay.IdDG=:d";
            }
        }
        if (String.valueOf(tt) != null) {
            if (loc) {
                loc = false;
                sql += " where ct.TrangThai=:tt";
            } else {
                sql += " and ct.TrangThai=:tt";
            }
        }
        // }
        //   this.em.clear();
        JpaUtil.getEntityManager();
        TypedQuery q = JpaUtil.getEntityManager().createQuery(sql, ChiTietSP.class);

        if (SizeID != null) {
            q.setParameter("size", SizeID);
        }
        if (IDCL != null) {
            q.setParameter("cl", IDCL);
        }
        if (IDHang != null) {
            q.setParameter("h", IDHang);
        }
        if (IDDe != null) {
            q.setParameter("d", IDDe);
        }

        if (IDLoaiGiay != null) {
            q.setParameter("l", IDLoaiGiay);
        }
        if (String.valueOf(tt) != null) {
            q.setParameter("tt", tt);
        }
        q.getResultList();
        List<ChiTietSP> list = q.getResultList();
        return list;
    }

    @Override
    public long dem() {
        long count = 0;
        try {
            String pr = "select count(*) from ChiTietSP";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(pr);
            while (rs.next()) {
                count = rs.getLong(1);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

}
