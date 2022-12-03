/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services.Interface;

import DomainModels.KhachHang;
import java.util.List;

/**
 *
 * @author trxua
 */
public interface KhachHangServiceInterface {

    public List<KhachHang> getAll();

    public String create(KhachHang kh) throws Exception;

    public String update(KhachHang kh) throws Exception;

    public void delete(KhachHang kh) throws Exception;

    public List<KhachHang> search(String ten);
}
