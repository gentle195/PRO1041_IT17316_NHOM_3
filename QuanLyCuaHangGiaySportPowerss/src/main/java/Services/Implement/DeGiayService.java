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
    public List<DeGiay> all() {
       return DeGiayRepo.getall();
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
