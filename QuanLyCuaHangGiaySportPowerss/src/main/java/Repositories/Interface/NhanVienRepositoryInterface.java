/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories.Impl;

import DomainModels.NhanVien;
import ViewModels.NhanVienViewModel;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface NhanVienRepositoryInterface {

    public List<NhanVien> getall();

    public void create(NhanVien nv) throws Exception;

    public void update(NhanVien nv) throws Exception;

    public void delete(NhanVien nv) throws Exception;
}
