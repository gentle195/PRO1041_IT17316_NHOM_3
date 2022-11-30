package Services;

import Services.*;
import DomainModels.ChatLieu;
import DomainModels.ChiTietSP;
import DomainModels.ChiTietSP1;
import ViewModels.SanPhamViewModel;
import Repositories.SanPhamRepository;
import DomainModels.SanPham;
import Repositories.ChatLieuRepository;
import Repositories.ChiTietSPRepository;
import Services.Interface.ChatLieuServiceInterface;
import Services.Interface.ChatLieuServiceInterface;
import Services.Interface.ChiTietSPServiceInterface;
import Services.Interface.SanPhamServiceInterface;
import ViewModels.ChatLieuViewModel;
import ViewModels.ChiTietSPViewModel;
import static java.lang.Integer.toString;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ChiTietSPService implements ChiTietSPServiceInterface {

    private ChiTietSPRepository chiTietSPRepo;

    private List<ChiTietSP> chiTietSPs;

    public ChiTietSPService() {
        this.chiTietSPRepo = new ChiTietSPRepository();
        chiTietSPs = new ArrayList<>();
    }

    @Override
    public List<ChiTietSPViewModel> all() {
        List<ChiTietSP> listDomainModel = this.chiTietSPRepo.getall();
        List<ChiTietSPViewModel> listVModel = new ArrayList<>();
        for (ChiTietSP c : listDomainModel) {
            ChiTietSPViewModel vmodel = new ChiTietSPViewModel(c.getIdCTSP(), c.getSanPham(), c.getHangGiay(), c.getChatlieu(), c.getDeGiay(), c.getSize(), c.getLoaigiay(), c.getSoLuong(), c.getDonGia(), c.getTrongLuong(), c.getTrangThai(), c.getMoTa());
            listVModel.add(vmodel);
        }

        return listVModel;

    }

    @Override
    public void add(ChiTietSP chiTietSP) throws Exception {
        chiTietSPRepo.create(chiTietSP);
    }

    @Override
    public void update(ChiTietSP chiTietSP) throws Exception {
        chiTietSPRepo.update(chiTietSP);
    }

    @Override
    public void delete(ChiTietSP chiTietSP) throws Exception {
        chiTietSPRepo.delete(chiTietSP);

    }

    @Override
    public void updatesl(ChiTietSP ct, String ma) throws Exception {
        chiTietSPRepo.updates1(ct, ma);
    }

    @Override
    public List<ChiTietSPViewModel> search(String ten) {
        return chiTietSPRepo.search(ten);
    }

}
