package Repositories.Interface;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

import DomainModels.ChatLieu;

import java.util.List;

/**
 *
 * @author dinhq
 */
public interface ChatLieuRepositoryInterface {

    public List<ChatLieu> getall();

    public void create(ChatLieu chatLieu) throws Exception;

    public void update(ChatLieu chatLieu) throws Exception;

    public void delete(ChatLieu chatLieu) throws Exception;
}
