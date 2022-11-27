package Services.Interface;







import DomainModels.ChiTietSP;
import DomainModels.ChiTietSP1;
import ViewModels.ChiTietSPViewModel;
import java.util.List;

public interface ChiTietSPServiceInterface {
    public List<ChiTietSPViewModel> all();
    
    public void add(ChiTietSP chiTietSP) throws Exception;
    public void update(ChiTietSP chiTietSP) throws Exception;
    public  void delete(ChiTietSP chiTietSP) throws Exception;
    public void updatesl(ChiTietSP ct,String ma) throws Exception;
}
