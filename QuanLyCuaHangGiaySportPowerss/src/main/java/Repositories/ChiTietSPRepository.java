package Repositories;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import DomainModels.ChatLieu;
import DomainModels.ChiTietSP;
import DomainModels.ChiTietSP1;
import Utilities.JpaUtil;
import DomainModels.SanPham;
import Repositories.Impl.ChatLieuRepositoryInterface;
import Repositories.Impl.ChiTietSPRepositoryInterface;
import Repositories.Impl.SanPhamRepositoryInterface;
import Utilities.DBConnection;
import ViewModels.ChiTietSPViewModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        this.em.clear();
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

    @Override
    public void updates1(ChiTietSP ct, String ma) throws Exception {
        try {
            Connection conn = DBConnection.getConnection();
            String insert = "update ChiTietSp set SoLuong=? where IdSP like(Select IdSP from SanPham where MaSP=?)";
//                    "update  ChiTietHoaDon set SoLuong=? where IDChiTietSP like(Select IDCTSP from ChiTietSP CT left join SanPham SP on CT.IdSP=SP.IdSP where MaSP=?)";
            PreparedStatement ps = conn.prepareStatement(insert);
            ps.setObject(1, ct.getSoLuong());
            ps.setObject(2, ma);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietSPRepository.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public List<ChiTietSPViewModel> search(String ten) {
        String sql = "select SanPham.MaSP, SanPham.TenSP, DonGia, SoLuong, TrangThai "
                + "from ChiTietSP join SanPham on ChiTietSP.IdSP= SanPham.IdSP "
                + "where TenSP = ?";
        try(Connection conn = DBConnection.getConnection();
               PreparedStatement ps = conn.prepareStatement(sql) ){
            List<ChiTietSPViewModel> lstCTSPVM= new ArrayList<>();
            ps.setObject(1, ten);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                SanPham sp = new SanPham(rs.getString(1), rs.getString(2));
                lstCTSPVM.add(new ChiTietSPViewModel(sp, rs.getBigDecimal(3), rs.getInt(4), rs.getInt(5)) );
                return lstCTSPVM;
            }
        }
        catch(Exception e){
            
        }
        return null;
    }

}
