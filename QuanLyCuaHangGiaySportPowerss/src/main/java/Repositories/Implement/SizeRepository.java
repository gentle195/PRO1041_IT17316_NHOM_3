/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import Repositories.Impl.SizeRepositoryInterface;
import DomainModels.HangGiay;
import DomainModels.LoaiGiay;
import DomainModels.Size;

import Utilities.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author TUAN ANH
 */
public class SizeRepository implements SizeRepositoryInterface {

    private EntityManager em;

    public SizeRepository() {
        this.em = JpaUtil.getEntityManager();
    }

    @Override
    public List<Size> getall() {
        String jpql = "SELECT cate FROM Size cate";
        TypedQuery<Size> query = this.em.createQuery(jpql, Size.class);
        return query.getResultList();
    }

    @Override
    public void create(Size s) throws Exception {
        try {
            this.em.getTransaction().begin();
            this.em.persist(s);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void update(Size s) throws Exception {
        try {
            this.em.getTransaction().begin();
            this.em.merge(s);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void delete(Size s) throws Exception {
        try {
            this.em.getTransaction().begin();
            Size size = em.merge(s);
            this.em.remove(size);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

}
