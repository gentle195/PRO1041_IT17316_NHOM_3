/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;
import DomainModels.NhanVien;
import Repositories.NhanVienRepository;
import Services.Interface.NhanVienServiceInteface;
import ViewModels.NhanVienViewModel;
import java.util.List;
/**
 *
 * @author Admin
 */
public class NhanVienService implements NhanVienServiceInteface{
    private NhanVienRepository NVrepo;

    public  NhanVienService (){
        this.NVrepo = new NhanVienRepository();
    }

    @Override
    public List<NhanVienViewModel> getall() {
       return NVrepo.getall();
    }

    @Override
    public void create (NhanVien nv) throws Exception {
        NVrepo.create(nv);
    }

    @Override
    public void update(NhanVien nv) throws Exception {
        NVrepo.update(nv);
    }

    @Override
    public void delete(NhanVien nv) throws Exception {
        NVrepo.delete(nv);
      
    }
}
