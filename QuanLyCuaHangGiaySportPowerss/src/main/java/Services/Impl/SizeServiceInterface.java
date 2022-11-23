package Services.Impl;






import DomainModels.Size;
import ViewModels.SizeViewModel;
import java.util.List;

public interface SizeServiceInterface {
    public List<SizeViewModel> all();
    
    public void add(Size s) throws Exception;
    public void update(Size s) throws Exception;
    public  void delete(Size s) throws Exception;
}
