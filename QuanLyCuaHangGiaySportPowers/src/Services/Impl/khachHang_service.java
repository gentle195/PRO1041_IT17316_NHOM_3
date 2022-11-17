/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.Impl;
import java.util.List;
import DomainModels.khachHang_model;
import Repositories.khachHang_repos;
import Services.ikhachHang;
/**
 *
 * @author Admin
 */
public class khachHang_service implements ikhachHang{
    khachHang_repos repos = new khachHang_repos();
    @Override
    public List<khachHang_model> getALLKH() {
        return repos.getALLKH();
    }

    @Override
    public khachHang_model getBYmaKh(String ma) {
        return repos.getBYmaKh(ma);
    }

    @Override
    public int Add(khachHang_model s) {
        return repos.Add(s);
    }

    @Override
    public int delete(khachHang_model s) {
        return repos.delete(s);
    }

    @Override
    public int Update(khachHang_model s) {
        return repos.Update(s);
    }

//    @Override
//    public List<nhanvien_model> SearchSP(String KeyWord) {
//        return repos.SearchSP(KeyWord);
//    }
}
