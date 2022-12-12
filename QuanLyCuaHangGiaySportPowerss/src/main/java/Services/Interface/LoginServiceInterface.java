/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services.Interface;

import DomainModels.Login_Result;
import DomainModels.TaiKhoan;
import java.util.List;

/**
 *
 * @author trxua
 */
public interface LoginServiceInterface {

    public Login_Result doLogin(String tk, String ma);
}
