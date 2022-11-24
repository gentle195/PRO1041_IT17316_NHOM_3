package Repositories.Interface;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */


import DomainModels.LoaiGiay;
import DomainModels.Size;

import java.util.List;

/**
 *
 * @author dinhq
 */
public interface SizeRepositoryInterface {

    public List<Size> getall();

    public void create(Size s) throws Exception;

    public void update(Size s) throws Exception;

    public void delete(Size s) throws Exception;
}
