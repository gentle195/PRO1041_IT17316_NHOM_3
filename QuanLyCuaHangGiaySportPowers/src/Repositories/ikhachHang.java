/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories;

import java.util.List;
import DomainModels.khachHang_model;

/**
 *
 * @author Admin
 */
;

public interface ikhachHang {

    List<khachHang_model> getALLKH();

    khachHang_model getBYmaKh(String ma);

    int Add(khachHang_model s);

    int delete(khachHang_model s);

    int Update(khachHang_model s);
}
