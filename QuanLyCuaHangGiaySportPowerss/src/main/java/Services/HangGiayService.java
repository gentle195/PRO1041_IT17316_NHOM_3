/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import ViewModels.HangGiayViewModel;
import Repositories.HangGiayRepository;
import DomainModels.HangGiay;

import java.util.ArrayList;
import java.util.List;
import Services.Interface.HangGiayServiceInterface;

/**
 *
 * @author TUAN ANH
 */
public class HangGiayService implements HangGiayServiceInterface {

    private HangGiayRepository hangSPRepository;

    public HangGiayService() {
        this.hangSPRepository = new HangGiayRepository();
    }

    @Override
    public List<HangGiayViewModel> all() {
        List<HangGiay> listDomainModel = this.hangSPRepository.getall();
        List<HangGiayViewModel> listVModel = new ArrayList<>();
        for (HangGiay hang : listDomainModel) {
            HangGiayViewModel vModel = new HangGiayViewModel(hang.getIdHang(), hang.getMaHang(), hang.getTenHang(), hang.getMoTaHang());
            listVModel.add(vModel);

        }
        return listVModel;
    }

    @Override
    public void add(HangGiay hangSP) throws Exception {
        hangSPRepository.create(hangSP);
    }

    @Override
    public void update(HangGiay hangSP) throws Exception {
        hangSPRepository.update(hangSP);
    }

    @Override
    public void delete(HangGiay hangSP) throws Exception {
        hangSPRepository.delete(hangSP);
    }

}
