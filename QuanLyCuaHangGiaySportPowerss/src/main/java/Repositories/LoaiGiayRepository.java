/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import DomainModels.LoaiGiay;

import Repositories.Impl.LoaiGiayRepositoryInterface;
import Utilities.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author TUAN ANH
 */
public class LoaiGiayRepository implements LoaiGiayRepositoryInterface {

    private EntityManager em;

    public LoaiGiayRepository() {
        this.em = JpaUtil.getEntityManager();
    }

    // public List<LoaiGiay> getall() {
    //     String jpql = "SELECT cate FROM LoaiGiay cate";
    //   TypedQuery<LoaiGiay> query = this.em.createQuery(jpql, LoaiGiay.class);
    //  return query.getResultList();
    //}
    @Override
    public List<LoaiGiay> getall() {
        String jpql = "SELECT cate FROM LoaiGiay cate";
        TypedQuery<LoaiGiay> query = this.em.createQuery(jpql, LoaiGiay.class);
        return query.getResultList();
    }

    @Override
    public void create(LoaiGiay loai) throws Exception {
        try {
            this.em.getTransaction().begin();
            this.em.persist(loai);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void update(LoaiGiay loai) throws Exception {
        try {
            this.em.getTransaction().begin();
            this.em.merge(loai);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void delete(LoaiGiay loai) throws Exception {
        try {
            this.em.getTransaction().begin();
            LoaiGiay loaigiay = em.merge(loai);
            this.em.remove(loaigiay);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

}
