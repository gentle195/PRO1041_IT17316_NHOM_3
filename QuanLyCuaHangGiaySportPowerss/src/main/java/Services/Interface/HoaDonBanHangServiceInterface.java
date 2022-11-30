package Services.Interface;

import DomainModels.HoaDon;
import ViewModels.HoaDonBanHangViewModel;
import java.util.List;

public interface HoaDonBanHangServiceInterface {

    public List<HoaDonBanHangViewModel> all();

    public void add(HoaDon hd) throws Exception;

    public void update(HoaDon hd, String ma, String ma1) throws Exception;

    public void delete(String Ma) throws Exception;
}
