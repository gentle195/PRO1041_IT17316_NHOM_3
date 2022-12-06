package Services.Interface;

import DomainModels.ChiTietSP;
import DomainModels.ChiTietSP1;
import ViewModels.ChiTietSPViewModel;
import java.util.List;
import java.util.UUID;

public interface ChiTietSPServiceInterface {

    public List<ChiTietSPViewModel> all();

    public List<ChiTietSPViewModel> search(String ten);

    public void add(ChiTietSP chiTietSP) throws Exception;

    public void update(ChiTietSP chiTietSP) throws Exception;

    public void delete(ChiTietSP chiTietSP) throws Exception;

    public void updatesl(ChiTietSP ct, String ma) throws Exception;
    
    List<ChiTietSPViewModel> loc(UUID IDCL, UUID SizeID, UUID IDHang, UUID IDDe, UUID IDLoaiGiay,int tt);

}
