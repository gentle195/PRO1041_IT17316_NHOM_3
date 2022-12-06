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
    public List<HangGiay> all() {
       return hangSPRepository.getall();
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
