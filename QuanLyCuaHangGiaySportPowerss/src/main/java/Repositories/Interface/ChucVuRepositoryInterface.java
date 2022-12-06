/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories.Impl;

import DomainModels.ChucVu;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ChucVuRepositoryInterface {
    public List<ChucVu> getall();
    public void create(ChucVu cv ) throws Exception;
    public void delete(ChucVu cv ) throws Exception;
    public void update(ChucVu cv ) throws Exception;
}
