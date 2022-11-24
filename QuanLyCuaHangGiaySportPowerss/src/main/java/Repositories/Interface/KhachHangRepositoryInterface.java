/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories.Interface;

import DomainModels.KhachHang;
import java.util.List;

/**
 *
 * @author trxua
 */
public interface KhachHangRepositoryInterface {

    public List<KhachHang> all();

    public void create(KhachHang kh) throws Exception;

    public void update(KhachHang kh) throws Exception;

    public void delete(KhachHang kh) throws Exception;

}