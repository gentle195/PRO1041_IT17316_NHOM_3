package Services;


import ViewModels.SanPhamViewModel;
import Repositories.SanPhamRepository;
import DomainModels.SanPham;
import Services.Interface.SanPhamServiceInterface;
import java.util.ArrayList;
import java.util.List;


public class SanPhamService implements SanPhamServiceInterface {

    private SanPhamRepository sanPhamRepo;

    public SanPhamService() {
        this.sanPhamRepo = new SanPhamRepository();
    }

    @Override
    public List<SanPham> all() {
        return sanPhamRepo.getall();
    }

    @Override
    public void add(SanPham sanPham) throws Exception {
        sanPhamRepo.create(sanPham);
    }

    @Override
    public void update(SanPham sanPham) throws Exception {
        sanPhamRepo.update(sanPham);
    }

    @Override
    public void delete(SanPham sanPham) throws Exception {
        sanPhamRepo.delete( sanPham);
      
    }
}
