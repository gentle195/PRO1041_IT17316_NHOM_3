/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import DomainModels.NhanVien;
import Utilities.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import Repositories.Impl.NhanVienRepositoryInterface;
import Utilities.DBConnection;
import java.sql.*;
import java.util.List;

/**
 *
 * @author Admin
 */
public class NhanVienRepository implements NhanVienRepositoryInterface {

    private EntityManager em;
    private static final Connection con = DBConnection.getConnection();

    public NhanVienRepository() {
        this.em = JpaUtil.getEntityManager();
    }

    @Override
    public List<NhanVien> getall(int min, int max) {
        String jpql = "SELECT cate FROM NhanVien cate";
        TypedQuery<NhanVien> query = this.em.createQuery(jpql, NhanVien.class);
        query.setFirstResult(min);
        query.setMaxResults(max);
        return query.getResultList();
    }

    @Override
    public void create(NhanVien nv) throws Exception {
        try {
            this.em.getTransaction().begin();
            this.em.persist(nv);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void update(NhanVien nv) throws Exception {
        try {
            this.em.getTransaction().begin();
            this.em.merge(nv);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void delete(NhanVien nv) throws Exception {
        try {
            this.em.getTransaction().begin();
            NhanVien s = em.merge(nv);
            this.em.remove(s);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }

    }

    @Override
    public List<NhanVien> getallNhanVien(String ma) {
        String jpql = "SELECT cate FROM NhanVien cate where cate.MaNV =:ma ";
        JpaUtil.getEntityManager();
        TypedQuery q = JpaUtil.getEntityManager().createQuery(jpql, NhanVien.class);
        q.setParameter("ma", ma);
        q.getResultList();
        return q.getResultList();
    }

    @Override
    public long dem() {
        long count = 0;
        try {
            String pr = "select count(*) from NhanVien";
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

    @Override
    public String updatett(NhanVien nv,String ma) {
        try {
            String pr="update NhanVien set DiaChi=?,Sđt=?,MatKhau=? where MaNV=?";
            PreparedStatement ps=con.prepareStatement(pr);
            ps.setObject(1, nv.getDiaChi());
            ps.setObject(2, nv.getSdt());
            ps.setObject(3, nv.getMatkhau());
            ps.setObject(4, ma);
            ps.executeUpdate();
            return "Sửa thành công";
        } catch (Exception e) {
            return "Sửa thất bại";
        }
    }

   
}
