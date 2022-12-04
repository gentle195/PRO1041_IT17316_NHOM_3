/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import DomainModels.HoaDon;
import Repositories.Impl.HoaDonRepositoryInterface;
import Utilities.DBConnection;
import ViewModels.HoaDonBanHangViewModel;
import ViewModels.HoaDonChiTietViewModel;
import ViewModels.HoaDonViewModel;
import ViewModels.SanPhamViewModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoaDonRepository implements HoaDonRepositoryInterface {
 
    @Override
    public ArrayList<HoaDonViewModel> all() {
        ArrayList<HoaDonViewModel> list = new ArrayList<>();
        String sql = "select a.Ma,b.MaNV,c.MaKH,c.HoTen,a.NgayTao,a.NgayDat,a.NgayThanhToan,a.NgayShip,a.NgayNhan,a.TinhTrang,a.TongTien from HoaDon a left join NhanVien b on a.IdNV = b.IdNV left join  KhachHang c on a.IdKH = c.IdKH order by a.Ma asc";
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonViewModel hoaDon = new HoaDonViewModel();
                hoaDon.setMaHD(rs.getString(1));
                hoaDon.setMaNV(rs.getString(2));
                hoaDon.setMaKH(rs.getString(3));
                hoaDon.setTenKH(rs.getString(4));
                hoaDon.setNgayTao(rs.getDate(5));
                hoaDon.setNgayDat(rs.getDate(6));
                hoaDon.setNgayThanhToan(rs.getDate(7));
                hoaDon.setNgayShip(rs.getDate(8));
                hoaDon.setNgayNhan(rs.getDate(9));
                hoaDon.setTinhTrang(rs.getInt(10));
                hoaDon.setTongTien(rs.getBigDecimal(11));
                
                list.add(hoaDon);
            }
        } catch (Exception e) {
            e.getMessage();
        }

        return list;
    }
    
  
    
     @Override
    public List<HoaDonChiTietViewModel> getListById(String idhoaDon) throws SQLException{
        String query = "SELECT  c.MaSP , c.TenSP , a.SoLuong , a.DonGia fROM ChiTietHoaDon a left join ChiTietSP b on a.IdChiTietSP = b.IdCTSP left join SanPham c on c.IdSP = b.IdSP left join HoaDon d on a.IdHD = d.IdHD where d.Ma = ?";
        List<HoaDonChiTietViewModel> list = new ArrayList<>();
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, idhoaDon);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTietViewModel model = new HoaDonChiTietViewModel(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getBigDecimal(4));
                list.add(model);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

 
    
}
