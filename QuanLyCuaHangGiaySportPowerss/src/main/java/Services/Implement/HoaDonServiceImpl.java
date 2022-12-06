/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DomainModels.HoaDon;
import Repositories.HoaDonRepository;
import Services.Interface.HoaDonService;
import ViewModels.HoaDonViewModel;
import java.util.List;

/**
 *
 * @author nguyenvv
 */
public class HoaDonServiceImpl implements HoaDonService {

    private HoaDonRepository hoaDonRepository = new HoaDonRepository();

     @Override
    public List<HoaDonViewModel> all() {
        return hoaDonRepository.all();
    }

}
