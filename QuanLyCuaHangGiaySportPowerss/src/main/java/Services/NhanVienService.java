/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;
import DomainModels.NhanVien;
import Repositories.NhanVienRepository;
import Services.Interface.NhanVienServiceInterface;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author Admin
 */
public class NhanVienService implements NhanVienServiceInterface{
    private NhanVienRepository NVrepo ;
    
    public NhanVienService (){
    NVrepo = new NhanVienRepository();
    }
     @Override
    public List<NhanVien> all() {
        return new NhanVienRepository().all();
    }

    @Override
    public void create(NhanVien nv) throws Exception {
        NVrepo.create(nv);
    }

    @Override
    public void update(NhanVien nv) throws Exception {
        try {
            NVrepo.update(nv);
        } catch (Exception ex) {
            Logger.getLogger(KhachHangService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(NhanVien nv) throws Exception {
         NVrepo.delete(nv);
    }
}
