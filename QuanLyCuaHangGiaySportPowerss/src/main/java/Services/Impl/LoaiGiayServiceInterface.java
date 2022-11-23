package Services.Impl;





import DomainModels.LoaiGiay;
import ViewModels.LoaiGiayViewModel;
import java.util.List;

public interface LoaiGiayServiceInterface {
    public List<LoaiGiayViewModel> all();
    
    public void add(LoaiGiay loai) throws Exception;
    public void update(LoaiGiay loai) throws Exception;
    public  void delete(LoaiGiay loai) throws Exception;
}
