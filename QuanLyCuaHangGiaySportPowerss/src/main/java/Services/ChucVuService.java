/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;
import DomainModels.ChucVu;
import ViewModels.SanPhamViewModel;
import Repositories.SanPhamRepository;
import Repositories.ChucVuRepository;
import Services.Interface.ChucVuServiceInterface;
import Services.Interface.SanPhamServiceInterface;
import ViewModels.ChatLieuViewModel;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Admin
 */
public class ChucVuService implements ChucVuServiceInterface{
     
    private ChucVuRepository CVrepos;
    
     public ChucVuService() {
        this.CVrepos = new ChucVuRepository();
    }

    @Override
    public List<ChucVu> getall() {
       return CVrepos.getall();
    }

    @Override
    public void create (ChucVu cv) throws Exception {
        CVrepos.create(cv);
    }

    @Override
    public void update(ChucVu cv) throws Exception {
        CVrepos.update(cv);
    }

    @Override
    public void delete(ChucVu cv) throws Exception {
        CVrepos.delete( cv);
      
    }
}
