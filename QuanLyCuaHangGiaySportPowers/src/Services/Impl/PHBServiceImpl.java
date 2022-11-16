/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.Impl;

import java.util.List;
import repository.PBHRepository;
import service.PBHService;
import viewmodel.PhieuBaoHanh;

/**
 *
 * @author Hung
 */
public class PHBServiceImpl implements PBHService{
    
    private PBHRepository ql = new PBHRepository();
    @Override
    public List<PhieuBaoHanh> getAll() {
        return ql.getAll();
    }

    @Override
    public String add(PhieuBaoHanh pbh) {
        boolean add= ql.add(pbh);
        if(add){
            return  "Thêm thành công";
        }else{
             return  "Thêm thất bại";
        }
    }

    @Override
    public String update(PhieuBaoHanh pbh, String id) {
          boolean update= ql.update(pbh, id);
        if(update){
            return  "Sửa thành công";
        }else{
             return  "Sửa thất bại";
        }
    }

    @Override
    public String delete(String id) {
           boolean delete= ql.delete(id);
        if(delete){
            return  "Xóa thành công";
        }else{
             return  "Xóa thất bại";
        }
    }
    
}
