package Repositories.Impl;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */


import DomainModels.LoaiGiay;

import java.util.List;

/**
 *
 * @author dinhq
 */
public interface LoaiGiayRepositoryInterface {

    public List<LoaiGiay> getall();

    public void create(LoaiGiay loai) throws Exception;

    public void update(LoaiGiay loai) throws Exception;

    public void delete(LoaiGiay loai) throws Exception;
}
