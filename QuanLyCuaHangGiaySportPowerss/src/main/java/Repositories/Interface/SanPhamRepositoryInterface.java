package Repositories.Interface;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

import DomainModels.SanPham;
import java.util.List;

/**
 *
 * @author dinhq
 */
public interface SanPhamRepositoryInterface {

    public List<SanPham> getall();

    public void create(SanPham sanPham) throws Exception;

    public void update(SanPham sanPham) throws Exception;

    public void delete(SanPham sanPham) throws Exception;
}
