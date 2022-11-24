package Services;


import DomainModels.ChatLieu;
import ViewModels.SanPhamViewModel;
import Repositories.SanPhamRepository;
import DomainModels.SanPham;
import Repositories.ChatLieuRepository;
import Services.Interface.ChatLieuServiceInterface;
import Services.Interface.SanPhamServiceInterface;
import ViewModels.ChatLieuViewModel;
import java.util.ArrayList;
import java.util.List;


public class ChatLieuService implements ChatLieuServiceInterface {

    private ChatLieuRepository chatLieuRepo;

    public ChatLieuService() {
        this.chatLieuRepo = new ChatLieuRepository();
    }

    @Override
    public List<ChatLieu> all() {
       return chatLieuRepo.getall();
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
