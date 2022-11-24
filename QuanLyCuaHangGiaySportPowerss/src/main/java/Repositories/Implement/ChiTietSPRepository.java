package Repositories.Implement;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import DomainModels.ChatLieu;
import DomainModels.ChiTietSP;
import Utilities.JpaUtil;
import DomainModels.SanPham;
import Repositories.Interface.ChatLieuRepositoryInterface;
import Repositories.Interface.ChiTietSPRepositoryInterface;
import Repositories.Interface.SanPhamRepositoryInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

/**
 *
 * @author dinhq
 */
public class ChiTietSPRepository implements ChiTietSPRepositoryInterface {

    private EntityManager em;

    public ChiTietSPRepository() {
        this.em = JpaUtil.getEntityManager();
    }

    @Override
    public List<ChiTietSP> getall() {
        String jpql = "SELECT cate FROM ChiTietSP cate";
        TypedQuery<ChiTietSP> query = this.em.createQuery(jpql, ChiTietSP.class);
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

}
