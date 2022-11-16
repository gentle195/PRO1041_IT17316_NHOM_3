/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import java.util.List;
import viewmodel.PhieuBaoHanh;

/**
 *
 * @author Hung
 */
public interface PBHService {
    List<PhieuBaoHanh> getAll();
    String add(PhieuBaoHanh pbh);
    String update(PhieuBaoHanh pbh,String id);
    String delete(String id);
}
