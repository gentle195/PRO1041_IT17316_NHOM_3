package Services.Interface;





import DomainModels.ChatLieu;
import DomainModels.DeGiay;
import ViewModels.DeGiayViewModel;
import java.util.List;

public interface DeGiayServiceInterface {
    public List<DeGiayViewModel> all();
    
    public void add(DeGiay deGiay) throws Exception;
    public void update(DeGiay deGiay) throws Exception;
    public  void delete(DeGiay deGiay) throws Exception;
}
