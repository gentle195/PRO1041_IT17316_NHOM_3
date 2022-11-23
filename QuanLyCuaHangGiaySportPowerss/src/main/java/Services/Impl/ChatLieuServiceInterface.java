package Services.Impl;




import DomainModels.ChatLieu;

import ViewModels.ChatLieuViewModel;
import java.util.List;

public interface ChatLieuServiceInterface {
    public List<ChatLieuViewModel> all();
    
    public void add(ChatLieu chatLieu) throws Exception;
    public void update(ChatLieu chatLieu) throws Exception;
    public  void delete(ChatLieu chatLieu) throws Exception;
}
