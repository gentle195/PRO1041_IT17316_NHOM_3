package Services;

import DomainModels.LoaiGiay;
import Repositories.LoaiGiayRepository;

import Services.Impl.LoaiGiayServiceInterface;
import ViewModels.LoaiGiayViewModel;
import java.util.ArrayList;
import java.util.List;

public class LoaiGiayService implements LoaiGiayServiceInterface {

    private LoaiGiayRepository LoaiGiayRepo;

    public LoaiGiayService() {
        this.LoaiGiayRepo = new LoaiGiayRepository();
    }

    @Override
    public List<LoaiGiayViewModel> all() {

        List<LoaiGiay> listDomainModel = this.LoaiGiayRepo.getall();
        List<LoaiGiayViewModel> listVModel = new ArrayList<>();
        for (LoaiGiay loai : listDomainModel) {
            LoaiGiayViewModel vmodel = new LoaiGiayViewModel(loai.getIdLoai(), loai.getMaLoai(),
                    loai.getTenLoai(), loai.getMoTa());
            listVModel.add(vmodel);

        }
        return listVModel;
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
