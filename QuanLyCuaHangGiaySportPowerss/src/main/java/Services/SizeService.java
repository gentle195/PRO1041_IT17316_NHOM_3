package Services;

import DomainModels.LoaiGiay;
import DomainModels.Size;
import Repositories.LoaiGiayRepository;
import Repositories.SizeRepository;

import Services.Interface.LoaiGiayServiceInterface;
import Services.Interface.SizeServiceInterface;
import ViewModels.LoaiGiayViewModel;
import ViewModels.SizeViewModel;
import java.util.ArrayList;
import java.util.List;

public class SizeService implements SizeServiceInterface {

    private SizeRepository sizeRepo;

    public SizeService() {
        this.sizeRepo = new SizeRepository();
    }

    @Override
    public List<Size> all() {
        return sizeRepo.getall();
    }

    @Override
    public void add(Size s) throws Exception {
        sizeRepo.create(s);
    }

    @Override
    public void update(Size s) throws Exception {
        sizeRepo.update(s);
    }

    @Override
    public void delete(Size s) throws Exception {
        sizeRepo.delete(s);
    }

}
