/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services.Interface;
import ViewModels.HangGiayViewModel;
import DomainModels.HangGiay;
import java.util.List;

        
/**
 *
 * @author TUAN ANH
 */
public interface HangGiayServiceInterface {
    public List<HangGiay> all();
    
    public void add(HangGiay hangSP) throws Exception;
    public void update(HangGiay hangSP) throws Exception;
    public  void delete(HangGiay hangSP) throws Exception;
}
