/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DomainModels.NhanVien;
import Repositories.LoginRepository;
import Services.Interface.LoginService;
import java.util.List;

/**
 *
 * @author trxua
 */
public class LoginServiceImpl implements LoginService {

    private LoginRepository repo = new LoginRepository();

    @Override
    public List<NhanVien> getNV(String ma) {
        return repo.getnv(ma);

    }

}
