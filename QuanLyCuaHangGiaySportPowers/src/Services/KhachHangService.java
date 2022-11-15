/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import DomainModels.KhachHang;
import java.util.List;

/**
 *
 * @author trxua
 */
public interface KhachHangService {

    List<KhachHang> getAll();

    String create(KhachHang kh);

    String update(KhachHang kh, String ma);

    String delete(String ma);

}
