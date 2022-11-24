/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.Implement;

import DomainModels.KhachHang;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import Utilities.JpaUtil;
import Repositories.Interface.KhachHangRepositoryInterface;

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
    public void create(KhachHang kh) throws Exception {
        try {
            this.em.getTransaction().begin();
            this.em.persist(kh);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void update(KhachHang kh) throws Exception {
        try {
            this.em.getTransaction().begin();
            this.em.merge(kh);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
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

}
