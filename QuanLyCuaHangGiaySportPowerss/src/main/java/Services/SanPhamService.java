package Services;


import ViewModels.SanPhamViewModel;
import Repositories.SanPhamRepository;
import DomainModels.SanPham;
import Services.Impl.SanPhamServiceInterface;
import java.util.ArrayList;
import java.util.List;


public class SanPhamService implements SanPhamServiceInterface {

    private SanPhamRepository sanPhamRepo;

    public SanPhamService() {
        this.sanPhamRepo = new SanPhamRepository();
    }

    @Override
    public List<SanPhamViewModel> all() {
        List<SanPham> listDomainModel = this.sanPhamRepo.getall();
        List<SanPhamViewModel> listVModel = new ArrayList<>();
        for (SanPham c : listDomainModel) {
            SanPhamViewModel vmodel = new SanPhamViewModel(c.getIdSP(), c.getMaSP(), c.getTenSP(), c.getMoTa());
            listVModel.add(vmodel);
        }

        return listVModel;
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
