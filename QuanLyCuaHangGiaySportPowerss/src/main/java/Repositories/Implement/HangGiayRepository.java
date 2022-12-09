/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import DomainModels.HangGiay;
import Utilities.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import Repositories.Impl.HangGiayRepositoryInterface;

/**
 *
 * @author TUAN ANH
 */
public class HangGiayRepository implements HangGiayRepositoryInterface {

    private EntityManager em;

    public HangGiayRepository() {
        this.em = JpaUtil.getEntityManager();
    }

    public List<HangGiay> getall() {
        String jpql = "SELECT cate FROM HangGiay cate order by cate.MaHang asc";
        TypedQuery<HangGiay> query = this.em.createQuery(jpql, HangGiay.class);
        return query.getResultList();
    }

    @Override
    public void create(HangGiay hangSP) throws Exception {
        try {
            this.em.getTransaction().begin();
            this.em.persist(hangSP);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void update(HangGiay hangSP) throws Exception {
        try {
            this.em.getTransaction().begin();
            this.em.merge(hangSP);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void delete(HangGiay hangSP) throws Exception {
        try {
            this.em.getTransaction().begin();
            HangGiay hang = em.merge(hangSP);
            this.em.remove(hang);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }

    }

}
