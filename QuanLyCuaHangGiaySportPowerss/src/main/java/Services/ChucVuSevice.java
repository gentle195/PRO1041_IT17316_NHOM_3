/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DomainModels.ChucVu;
import Repositories.ChucVuRepository;
import Services.Interface.ChucVuServiceInterface;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ChucVuSevice implements ChucVuServiceInterface{
       private ChucVuRepository CVrepo;

    public  ChucVuSevice (){
        this.CVrepo = new ChucVuRepository();
    }

    @Override
    public List<ChucVu> getall() {
       return CVrepo.getall();
    }

    @Override
    public void create (ChucVu cv) throws Exception {
        CVrepo.create(cv);
    }

    @Override
    public void update(ChucVu cv) throws Exception {
        CVrepo.update(cv);
    }

    @Override
    public void delete(ChucVu cv) throws Exception {
        CVrepo.delete(cv);
      
    }
}
