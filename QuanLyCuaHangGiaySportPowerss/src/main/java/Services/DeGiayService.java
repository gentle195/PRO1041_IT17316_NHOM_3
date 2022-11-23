package Services;




import DomainModels.DeGiay;
import Repositories.DeGiayRepository;
import Services.Interface.DeGiayServiceInterface;
import ViewModels.DeGiayViewModel;
import java.util.ArrayList;
import java.util.List;


public class DeGiayService implements DeGiayServiceInterface {

    private DeGiayRepository DeGiayRepo;

    public DeGiayService() {
        this.DeGiayRepo = new DeGiayRepository();
    }

    @Override
    public List<DeGiayViewModel> all() {
        List<DeGiay> listDomainModel = this.DeGiayRepo.getall();
        List<DeGiayViewModel> listVModel = new ArrayList<>();
        for (DeGiay c : listDomainModel) {
            DeGiayViewModel vmodel = new DeGiayViewModel(c.getIdDG(), c.getMaCL(), c.getLoaiDe(), c.getMoTaDG());
            listVModel.add(vmodel);
        }

        return listVModel;
    }

    @Override
    public void add(DeGiay deGiay) throws Exception {
        DeGiayRepo.create(deGiay);
    }

    @Override
    public void update(DeGiay deGiay) throws Exception {
        DeGiayRepo.update(deGiay);
    }

    @Override
    public void delete(DeGiay deGiay) throws Exception {
        DeGiayRepo.delete( deGiay);
      
    }
}
