package Services.Impl;




import DomainModels.SanPham;
import ViewModels.SanPhamViewModel;
import java.util.List;

public interface SanPhamServiceInterface {
    public List<SanPhamViewModel> all();
    
    public void add(SanPham sanPham) throws Exception;
    public void update(SanPham sanPham) throws Exception;
    public  void delete(SanPham sanPham) throws Exception;
}
