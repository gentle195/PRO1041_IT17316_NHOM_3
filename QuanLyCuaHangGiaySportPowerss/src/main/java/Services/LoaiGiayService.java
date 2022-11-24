package Services;

import DomainModels.LoaiGiay;
import Repositories.LoaiGiayRepository;

import Services.Interface.LoaiGiayServiceInterface;
import ViewModels.LoaiGiayViewModel;
import java.util.ArrayList;
import java.util.List;

public class LoaiGiayService implements LoaiGiayServiceInterface {

    private LoaiGiayRepository LoaiGiayRepo;

    public LoaiGiayService() {
        this.LoaiGiayRepo = new LoaiGiayRepository();
    }

    @Override
    public List<LoaiGiay> all() {

       return LoaiGiayRepo.getall();
    }

    @Override
    public void add(LoaiGiay loai) throws Exception {
        LoaiGiayRepo.create(loai);
    }

    @Override
    public void update(LoaiGiay loai) throws Exception {
        LoaiGiayRepo.update(loai);
    }

    @Override
    public void delete(LoaiGiay loai) throws Exception {
        LoaiGiayRepo.delete(loai);
    }

}
