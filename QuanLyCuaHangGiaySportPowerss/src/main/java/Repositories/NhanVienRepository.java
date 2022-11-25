/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;
import Repositories.Impl.NhanVienRepositoryInterface;
import DomainModels.NhanVien;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import Utilities.JpaUtil;
/**
 *
 * @author Admin
 */
public class NhanVienRepository implements NhanVienRepositoryInterface{
     private EntityManager em;

    public NhanVienRepository() {
        this.em = JpaUtil.getEntityManager();
    }

    @Override
    public List<NhanVien> all() {
        String jpql = "SELECT nv FROM NhanVien nv";
        TypedQuery<NhanVien> query = this.em.createQuery(jpql,NhanVien.class);
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
            NhanVien nvv = em.merge(nv);
            this.em.remove(nvv);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

}
