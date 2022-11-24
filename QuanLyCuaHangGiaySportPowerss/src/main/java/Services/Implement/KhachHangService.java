/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.Implement;

import DomainModels.KhachHang;
import Repositories.Implement.KhachHangRepository;
import Services.Interface.KhachHangServiceInterface;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author trxua
 */
public class KhachHangService implements KhachHangServiceInterface {

    private KhachHangRepository KHRepo;

    public KhachHangService() {
        KHRepo = new KhachHangRepository();
    }

    @Override
    public List<KhachHang> getAll() {
        return new KhachHangRepository().all();
    }

    @Override
    public void create(KhachHang kh) throws Exception {
        KHRepo.create(kh);
    }

    @Override
    public void update(KhachHang kh) throws Exception {
        try {
            KHRepo.update(kh);
        } catch (Exception ex) {
            Logger.getLogger(KhachHangService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(KhachHang kh) throws Exception {
        KHRepo.delete(kh);
    }
}
