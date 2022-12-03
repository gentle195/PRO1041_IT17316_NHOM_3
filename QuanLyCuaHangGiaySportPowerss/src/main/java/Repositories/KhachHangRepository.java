/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import DomainModels.KhachHang;
import Repositories.Impl.KhachHangRepositoryInterface;
import Utilities.DBConnection;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import Utilities.JpaUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author trxua
 */
public class KhachHangRepository implements KhachHangRepositoryInterface {

    private EntityManager em;

    public KhachHangRepository() {
        this.em = JpaUtil.getEntityManager();
    }

    @Override
    public List<KhachHang> all() {
        String jpql = "SELECT kh FROM KhachHang kh";
        TypedQuery<KhachHang> query = this.em.createQuery(jpql, KhachHang.class);
        return query.getResultList();
    }

    @Override
    public Boolean create(KhachHang kh) throws Exception {
        try {
            this.em.getTransaction().begin();
            this.em.persist(kh);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
        return null;
    }

    @Override
    public Boolean update(KhachHang kh) throws Exception {
        try {
            this.em.getTransaction().begin();
            this.em.merge(kh);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
        return null;
    }

    @Override
    public void delete(KhachHang kh) throws Exception {
        try {
            this.em.getTransaction().begin();
            KhachHang khh = em.merge(kh);
            this.em.remove(khh);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    public List<KhachHang> search(String ten) {
        String query = "SELECT * FROM KhachHang where hoten =?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, ten);
            ResultSet rs = ps.executeQuery();
            List<KhachHang> listKhachHang = new ArrayList<>();
            while (rs.next()) {
                KhachHang kh = new KhachHang(
                        //                        rs.getString(1), 
                        rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(6), rs.getString(7));
                listKhachHang.add(kh);
            }
            return listKhachHang;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
