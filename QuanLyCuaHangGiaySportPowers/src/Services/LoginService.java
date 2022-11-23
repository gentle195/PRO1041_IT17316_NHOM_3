/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import DomainModels.NhanVien;
import java.util.List;

/**
 *
 * @author trxua
 */
public interface LoginService {

    List<NhanVien> getNV(String ma);
}
