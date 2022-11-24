package Repositories;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import DomainModels.ChatLieu;
import DomainModels.DeGiay;
import Repositories.Impl.DeGiayRepositoryInterface;
import Utilities.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

/**
 *
 * @author dinhq
 */
public class DeGiayRepository implements DeGiayRepositoryInterface {

    private EntityManager em;

    public DeGiayRepository() {
        this.em = JpaUtil.getEntityManager();
    }

    @Override
    public List<DeGiay> getall() {
        String jpql = "SELECT cate FROM DeGiay cate order by cate.MaDG asc";
        TypedQuery<DeGiay> query = this.em.createQuery(jpql, DeGiay.class);
        return query.getResultList();
    }

    @Override
    public void create(DeGiay deGiay) throws Exception {
        try {
            this.em.getTransaction().begin();
            this.em.persist(deGiay);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void update(DeGiay deGiay) throws Exception {
        try {
            this.em.getTransaction().begin();
            this.em.merge(deGiay);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void delete(DeGiay deGiay) throws Exception {
        try {
            this.em.getTransaction().begin();
            DeGiay s = em.merge(deGiay);
            this.em.remove(s);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }

    }

}
