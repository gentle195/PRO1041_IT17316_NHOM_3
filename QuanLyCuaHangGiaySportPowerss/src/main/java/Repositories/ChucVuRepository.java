/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;
import DomainModels.ChucVu;
import Utilities.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import Repositories.Impl.ChucVuRepositoryInterface;

import java.util.List;

/**
 *
 * @author Admin
 */
public class ChucVuRepository  implements ChucVuRepositoryInterface{
     private EntityManager em;

    public ChucVuRepository() {
        this.em = JpaUtil.getEntityManager();
    }

    @Override
    public List<ChucVu> getall() {
        String jpql = "SELECT cate FROM ChucVu cate order by cate.MaCV asc";
        TypedQuery<ChucVu> query = this.em.createQuery(jpql,ChucVu.class);
        return query.getResultList();
    }

    @Override
    public void create(ChucVu cv ) throws Exception {
        try {
            this.em.getTransaction().begin();
            this.em.persist(cv);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void update(ChucVu cv) throws Exception {
        try {
            this.em.getTransaction().begin();
            this.em.merge(cv);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void delete(ChucVu cv) throws Exception {
        try {
            this.em.getTransaction().begin();
            ChucVu s = em.merge(cv);
            this.em.remove(s);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }

    }
}
