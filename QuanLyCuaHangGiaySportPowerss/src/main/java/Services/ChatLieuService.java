package Services;


import DomainModels.ChatLieu;
import ViewModels.SanPhamViewModel;
import Repositories.SanPhamRepository;
import DomainModels.SanPham;
import Repositories.ChatLieuRepository;
import Services.Impl.ChatLieuServiceInterface;
import Services.Impl.SanPhamServiceInterface;
import ViewModels.ChatLieuViewModel;
import java.util.ArrayList;
import java.util.List;


public class ChatLieuService implements ChatLieuServiceInterface {

    private ChatLieuRepository chatLieuRepo;

    public ChatLieuService() {
        this.chatLieuRepo = new ChatLieuRepository();
    }

    @Override
    public List<ChatLieuViewModel> all() {
        List<ChatLieu> listDomainModel = this.chatLieuRepo.getall();
        List<ChatLieuViewModel> listVModel = new ArrayList<>();
        for (ChatLieu c : listDomainModel) {
            ChatLieuViewModel vmodel = new ChatLieuViewModel(c.getIdCL(), c.getMaCL(), c.getTenCL(), c.getMoTaCl());
            listVModel.add(vmodel);
        }

        return listVModel;
    }

    @Override
    public void add(ChatLieu chatLieu) throws Exception {
        chatLieuRepo.create(chatLieu);
    }

    @Override
    public void update(ChatLieu chatLieu) throws Exception {
        chatLieuRepo.update(chatLieu);
    }

    @Override
    public void delete(ChatLieu chatLieu) throws Exception {
        chatLieuRepo.delete( chatLieu);
      
    }
}
