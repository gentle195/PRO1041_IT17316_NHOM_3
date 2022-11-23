package Repositories.Impl;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */



import DomainModels.ChatLieu;
import DomainModels.DeGiay;
import java.util.List;

/**
 *
 * @author dinhq
 */
public interface DeGiayRepositoryInterface {

    public List<DeGiay> getall();

    public void create(DeGiay deGiay) throws Exception;

    public void update(DeGiay deGiay) throws Exception;

    public void delete(DeGiay deGiay) throws Exception;
}
