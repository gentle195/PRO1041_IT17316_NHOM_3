package Repositories;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import Utilities.JpaUtil;
import DomainModels.SanPham;
import Repositories.Impl.SanPhamRepositoryInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

/**
 *
 * @author dinhq
 */
public class SanPhamRepository implements SanPhamRepositoryInterface {

    private EntityManager em;

    public SanPhamRepository() {
        this.em = JpaUtil.getEntityManager();
    }

    @Override
    public List<SanPham> getall() {
        String jpql = "SELECT cate FROM SanPham cate";
        TypedQuery<SanPham> query = this.em.createQuery(jpql, SanPham.class);
        return query.getResultList();
    }

    @Override
    public void create(SanPham sanPham) throws Exception {
        try {
            this.em.getTransaction().begin();
            this.em.persist(sanPham);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void update(SanPham sanPham) throws Exception {
        try {
            this.em.getTransaction().begin();
            this.em.merge(sanPham);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void delete(SanPham sanPham) throws Exception {
        try {
            this.em.getTransaction().begin();
            SanPham s = em.merge(sanPham);
            this.em.remove(s);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }

    }

}
