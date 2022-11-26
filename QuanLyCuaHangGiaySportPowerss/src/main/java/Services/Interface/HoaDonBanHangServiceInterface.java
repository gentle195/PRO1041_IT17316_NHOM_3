package Services.Interface;

import DomainModels.HoaDon;
import ViewModels.HoaDonBanHangViewModel;
import java.util.List;

public interface HoaDonBanHangServiceInterface {

    public List<HoaDonBanHangViewModel> all();

    public void add(HoaDon hd) throws Exception;

    public void update(HoaDon hd) throws Exception;

    public void delete(HoaDon hd) throws Exception;
}
