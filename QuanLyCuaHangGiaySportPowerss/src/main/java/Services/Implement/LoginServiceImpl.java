/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DomainModels.ChucVuLG;
import DomainModels.Login_Result;
import DomainModels.TaiKhoan;
import Repositories.LoginRepository;
import Services.Interface.LoginService;
import Viewss.NhanVienBanHangViews;
import Viewss.QuanLyViews;
import Viewss.giaoDienDangNhap;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author trxua
 */
public class LoginServiceImpl implements LoginService {

    private LoginRepository repo = new LoginRepository();

    @Override
    public Login_Result doLogin(String tk, String ma) {
        Login_Result result = new Login_Result();
        try {
            TaiKhoan user = repo.getUserByUserName(tk, ma);
            ChucVuLG cv = repo.getcv(tk);
            if (user.getMa() != null) {
                if (user.getMa().equals(tk)
                        && user.getMatKhau().equals(ma)) {
                    giaoDienDangNhap login = new giaoDienDangNhap();
                    if (cv.getTen().contains("Quản lý")) {
                        login.setVisible(false);
                        QuanLyViews ql = new QuanLyViews();
                        ql.setVisible(true);
                        QuanLyViews.txtMaNhanVien.setText(tk);
                    }
                    
                    if (cv.getTen().contains("Nhân viên")) {
                        login.setVisible(false);
                        NhanVienBanHangViews qldsv = new NhanVienBanHangViews();
                        qldsv.setVisible(true);
                        NhanVienBanHangViews.txtMaNhanVienbh.setText(tk);
                    }
                    result.setStatus(Boolean.TRUE);
                } else {
                    result.setStatus(Boolean.FALSE);
                    result.setMessage("Mật khẩu không đúng");
                }
            } else {
                result.setStatus(Boolean.FALSE);
                result.setMessage("Không tìm thấy thông tin tài khoản");
            }
        } catch (SQLException ex) {
            result.setStatus(Boolean.FALSE);
            result.setMessage("Đã xảy ra lỗi");
            Logger.getLogger(LoginServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
