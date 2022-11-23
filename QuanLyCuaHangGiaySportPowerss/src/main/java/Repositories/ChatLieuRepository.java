package Repositories;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import DomainModels.ChatLieu;
import Utilities.JpaUtil;
import DomainModels.SanPham;
import Repositories.Impl.ChatLieuRepositoryInterface;
import Repositories.Impl.SanPhamRepositoryInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

/**
 *
 * @author dinhq
 */
public class ChatLieuRepository implements ChatLieuRepositoryInterface {

    private EntityManager em;

    public ChatLieuRepository() {
        this.em = JpaUtil.getEntityManager();
    }

    @Override
    public List<ChatLieu> getall() {
        String jpql = "SELECT cate FROM ChatLieu cate";
        TypedQuery<ChatLieu> query = this.em.createQuery(jpql, ChatLieu.class);
        return query.getResultList();
    }

    @Override
    public void create(ChatLieu chatLieu) throws Exception {
        try {
            this.em.getTransaction().begin();
            this.em.persist(chatLieu);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void update(ChatLieu chatLieu) throws Exception {
        try {
            this.em.getTransaction().begin();
            this.em.merge(chatLieu);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void delete(ChatLieu chatLieu) throws Exception {
        try {
            this.em.getTransaction().begin();
            ChatLieu s = em.merge(chatLieu);
            this.em.remove(s);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }

    }

}
