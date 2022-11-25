/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services.Interface;

import DomainModels.ChucVu;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ChucVuServiceInterface {
      public List<ChucVu> getall();
    public void create(ChucVu cv ) throws Exception;
    public void delete(ChucVu cv ) throws Exception;
    public void update(ChucVu cv ) throws Exception;
}
