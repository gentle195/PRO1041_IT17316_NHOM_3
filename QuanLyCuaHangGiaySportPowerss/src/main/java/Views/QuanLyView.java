/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import DomainModels.ChatLieu;
import DomainModels.ChiTietSP;
import DomainModels.ChiTietSP1;
import DomainModels.ChucVu;
import DomainModels.DeGiay;
import DomainModels.HangGiay;
import DomainModels.HoaDon;
import DomainModels.HoaDonChiTiet;
import DomainModels.KhachHang;
import DomainModels.LoaiGiay;
import DomainModels.NhanVien;
import DomainModels.SanPham;
import DomainModels.Size;
import DomainModels.Time;
import Repositories.HoaDonRepository;
import Services.ChatLieuService;
import Services.ChiTietSPService;
import Services.ChucVuSevice;
import Services.DeGiayService;
import Services.HDCTService;
import Services.HangGiayService;
import Services.HoaDonBanHangService;
import Services.HoaDonServiceImpl;
import Services.Interface.ChatLieuServiceInterface;
import Services.Interface.ChiTietSPServiceInterface;
import Services.Interface.ChucVuServiceInterface;
import Services.Interface.DeGiayServiceInterface;
import Services.Interface.HangGiayServiceInterface;
import Services.Interface.HoaDonBanHangServiceInterface;
import Services.Interface.HoaDonService;
import Services.Interface.LoaiGiayServiceInterface;
import Services.Interface.NhanVienServiceInteface;
import Services.Interface.SanPhamServiceInterface;
import Services.Interface.SizeServiceInterface;
import Services.KhachHangService;
import Services.LoaiGiayService;
import Services.NhanVienService;
import Services.SanPhamService;
import Services.SizeService;
import ViewModels.ChiTietSPViewModel;
import ViewModels.HoaDonBanHangViewModel;
import ViewModels.HoaDonChiTietViewModel;
import ViewModels.HoaDonViewModel;
import ViewModels.NhanVienViewModel;
import ViewModels.TimKiemSPViewModel;
import java.awt.CardLayout;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author dinhq
 */
public class QuanLyView extends javax.swing.JFrame {

    private SanPhamServiceInterface SanPhamService;
    private ChatLieuServiceInterface chatLieuService;
    private HangGiayServiceInterface hangGiayService;
    private DeGiayServiceInterface deGiayService;
    private LoaiGiayServiceInterface loaiGiayService;
    private SizeServiceInterface sizeService;
    private ChiTietSPServiceInterface chiTietSPService;
    private ChucVuServiceInterface cvService;
    private NhanVienServiceInteface nvService;
    private HoaDonBanHangServiceInterface hoaDonBanHangService;
    private HDCTService HDCT;
    private KhachHangService serviceKH = new KhachHangService();
    private DefaultTableModel dtmKH = new DefaultTableModel();
    private List<KhachHang> listKH = new ArrayList<>();
    private HoaDonService hoadonService = new HoaDonServiceImpl();
    private DefaultTableModel defaultTableModelGioHang;

    private HoaDonRepository hoaDonRepository = new HoaDonRepository();
    private ArrayList<HoaDonViewModel> lsthd = new ArrayList<>();
    ArrayList<HoaDonChiTietViewModel> listhdct = new ArrayList<>();
    ArrayList<HoaDonChiTiet> listhdctt = new ArrayList<>();

    /**
     * Creates new form QuanyView
     */
    public QuanLyView() {
        initComponents();
        this.SanPhamService = new SanPhamService();
        this.chatLieuService = new ChatLieuService();
        this.hangGiayService = new HangGiayService();
        this.deGiayService = new DeGiayService();
        this.loaiGiayService = new LoaiGiayService();
        this.sizeService = new SizeService();
        this.chiTietSPService = new ChiTietSPService();
        this.cvService = new ChucVuSevice();
        this.nvService = new NhanVienService();
        this.hoaDonBanHangService = new HoaDonBanHangService();
        this.HDCT = new HDCTService();

        this.loadTableSanPham();
        this.loadTableChatLieu();
        this.loadTableHangGiay();
        this.loadTableDeGiay();
        this.loadTableLoaiGiay();
        this.loadTableSizeGiay();
        this.loadTableChiTietSP();
        this.loadTableChiTietSPBH();
        this.loadTableChucVu();
        this.loadTableNhanVien();
        this.loadTableHoaDonBanHang();
        this.loadTableHoaDon();

        List<SanPham> sp = SanPhamService.all();
        cbTenSP.setModel(new DefaultComboBoxModel((sp.toArray())));

        List<ChatLieu> cl = chatLieuService.all();
        cbCL.setModel(new DefaultComboBoxModel((cl.toArray())));

        List<DeGiay> dg = deGiayService.all();
        cbDe.setModel(new DefaultComboBoxModel((dg.toArray())));

        List<HangGiay> hang = hangGiayService.all();
        cbHang.setModel(new DefaultComboBoxModel((hang.toArray())));

        List<LoaiGiay> lg = loaiGiayService.all();
        cbLoai.setModel(new DefaultComboBoxModel((lg.toArray())));

        List<Size> sz = sizeService.all();
        cbSIZE.setModel(new DefaultComboBoxModel((sz.toArray())));

        List<ChucVu> cv = cvService.getall();
        cbCV.setModel(new DefaultComboBoxModel(cv.toArray()));

//        Đỗ Minh Vương
        List<NhanVienViewModel> nv1 = nvService.getall();
        cbMaNhanVien.setModel(new DefaultComboBoxModel(nv1.toArray()));

        List<KhachHang> kh = serviceKH.getAll();
        cbTenKH.setModel(new DefaultComboBoxModel(kh.toArray()));

        //       Duy
        tblKhachHang.setModel(dtmKH);
        tblKH.setModel(dtmKH);
        Object[] header = {"Mã", "Họ tên", "Giới tính", "Ngày sinh", "SĐT", "Địa chỉ"};
        dtmKH.setColumnIdentifiers(header);

//      Combobox sreach
        AutoCompleteDecorator.decorate(cbCL);
        AutoCompleteDecorator.decorate(cbTenSP);
        AutoCompleteDecorator.decorate(cbDe);
        AutoCompleteDecorator.decorate(cbHang);
        AutoCompleteDecorator.decorate(cbSIZE);
        AutoCompleteDecorator.decorate(cbLoai);
        AutoCompleteDecorator.decorate(cbCV);
    }

    private void showDataKH(List<KhachHang> list) {
        dtmKH.setRowCount(0);
        for (KhachHang kh : list) {
            dtmKH.addRow(kh.toDataRow());
        }
    }

    private void fillDataKH(int index) {
        KhachHang kh = listKH.get(index);
        this.txtIdKH.setText(kh.getID().toString());
        this.txtMaKH.setText(kh.getMa());
        this.txtTenKH.setText(kh.getHoTen());
        String gt = kh.getGioiTinh();
        if (gt.equalsIgnoreCase("Nam")) {
            rdNam.setSelected(true);
        } else {
            rdNu.setSelected(true);
        }
        this.txtDC.setText(kh.getDiaChi());
        this.txtSDT.setText(kh.getSdt());
        this.txtNgaySinh.setDate(kh.getNgaySinh());
    }

    private void clearTblKH() {
        this.txtMaKH.setText("");
        this.txtSDT.setText("");
        this.txtTenKH.setText("");
        this.txtID.setText("");
        this.txtDC.setText("");
        this.txtNgaySinh.setDate(null);
        this.buttonNV.clearSelection();
    }

    private void loadTableSanPham() {
        DefaultTableModel modeltb = new DefaultTableModel();
        List<SanPham> sp = SanPhamService.all();
        modeltb = (DefaultTableModel) tbSP.getModel();
        modeltb.setRowCount(0);
        for (SanPham x : sp) {
            modeltb.addRow(new Object[]{
                x.getIdSP(),
                x.getMaSP(), x.getTenSP(), x.getMoTa()
            });
        }
    }

    private void loadTableChatLieu() {
        DefaultTableModel modeltb = new DefaultTableModel();
        List<ChatLieu> cl = chatLieuService.all();
        modeltb = (DefaultTableModel) tbChatLieu.getModel();
        modeltb.setRowCount(0);
        for (ChatLieu x : cl) {
            modeltb.addRow(new Object[]{
                x.getIdCL(),
                x.getMaCL(), x.getTenCL(), x.getMaCL()
            });
        }
    }

    private void loadTableHangGiay() {
        DefaultTableModel modeltb = new DefaultTableModel();
        List<HangGiay> hang = hangGiayService.all();
        modeltb = (DefaultTableModel) tbHang.getModel();
        modeltb.setRowCount(0);
        for (HangGiay x : hang) {
            modeltb.addRow(new Object[]{
                x.getIdHang(), x.getMaHang(),
                x.getTenHang(), x.getMoTaHang()
            });

        }

    }

    private void loadTableDeGiay() {
        DefaultTableModel modeltb = new DefaultTableModel();
        List<DeGiay> dg = deGiayService.all();
        modeltb = (DefaultTableModel) tbDeGiay.getModel();
        modeltb.setRowCount(0);
        for (DeGiay x : dg) {
            modeltb.addRow(new Object[]{
                x.getIdDG(), x.getMaCL(),
                x.getLoaiDe(), x.getMoTaDG()
            });

        }

    }

    private void loadTableLoaiGiay() {
        DefaultTableModel modeltb = new DefaultTableModel();
        List<LoaiGiay> lg = loaiGiayService.all();
        modeltb = (DefaultTableModel) tbLoaiGiay.getModel();
        modeltb.setRowCount(0);
        for (LoaiGiay x : lg) {
            modeltb.addRow(new Object[]{
                x.getIdLoai(), x.getMaLoai(),
                x.getTenLoai(), x.getMoTa()
            });

        }

    }

    private void loadTableSizeGiay() {
        DefaultTableModel modeltb = new DefaultTableModel();
        List<Size> Sz = sizeService.all();
        modeltb = (DefaultTableModel) tbSIZE.getModel();
        modeltb.setRowCount(0);
        for (Size x : Sz) {
            modeltb.addRow(new Object[]{
                x.getIdSize(), x.getMaSize(),
                x.getSoSize()
            });

        }

    }

    private void loadTableChiTietSP() {
        DefaultTableModel modeltb = new DefaultTableModel();
        List<ChiTietSPViewModel> Sz = chiTietSPService.all();
        modeltb = (DefaultTableModel) tblQLSP.getModel();
        modeltb.setRowCount(0);
        for (ChiTietSPViewModel x : Sz) {
            modeltb.addRow(new Object[]{
                x.getIdCTSP(), x.getSanPham(), x.getSanPham(), x.getSize(), x.getLoaigiay(), x.getHangGiay(), x.getDeGiay(),
                x.getChatlieu(), x.getSoLuong(), x.getDonGia(), x.getTrongLuong(),
                x.getTrangThai() == 1 ? "Còn Hàng" : "Hết Hàng",
                x.getMoTa()
            });

        }
    }

    private void loadTableChucVu() {
        DefaultTableModel modeltb = new DefaultTableModel();
        List<ChucVu> cv = cvService.getall();
        modeltb = (DefaultTableModel) tbChucVu.getModel();
        modeltb.setRowCount(0);
        for (ChucVu s : cv) {
            modeltb.addRow(new Object[]{
                s.getIdCV(),
                s.getMaCV(), s.getMaCV(), s.getTenCV()
            });
        }
    }

    private void loadTableNhanVien() {
        DefaultTableModel modeltb = new DefaultTableModel();
        List<NhanVienViewModel> Sz = nvService.getall();
        modeltb = (DefaultTableModel) tbNhanVien.getModel();
        modeltb.setRowCount(0);
        for (NhanVienViewModel x : Sz) {
            modeltb.addRow(new Object[]{
                x.getIdNV(), x.getMaNV(), x.getHoTenNV(), x.getGioiTinh(), x.getChucvu(), x.getDiaChi(), x.getNgaySinh(),
                x.getMaNV(), x.getMatkhau(), x.getSdt()
            });

        }
    }

    private void loadTableChiTietSPBH() {
        DefaultTableModel dtmSpBH = new DefaultTableModel();
        List<ChiTietSPViewModel> Sz = chiTietSPService.all();
        dtmSpBH = (DefaultTableModel) tbldssanpham.getModel();
        dtmSpBH.setRowCount(0);
        for (ChiTietSPViewModel x : Sz) {
            dtmSpBH.addRow(new Object[]{
                x.getSanPham().getMaSP(), x.getSanPham().getTenSP(), x.getDonGia(),
                x.getSoLuong(), x.getTrangThai() == 1 ? "Còn Hàng" : "Hết Hàng"});
        }
    }

    private void addTableGioHang(ArrayList<HoaDonChiTietViewModel> list) {
        defaultTableModelGioHang = (DefaultTableModel) tblgiohang.getModel();
        defaultTableModelGioHang.setRowCount(0);
        for (HoaDonChiTietViewModel chiTietHoaDonViewModel : list) {
            defaultTableModelGioHang.addRow(new Object[]{
                chiTietHoaDonViewModel.getMaSP(), chiTietHoaDonViewModel.getTenSP(), chiTietHoaDonViewModel.getSoLuong(), chiTietHoaDonViewModel.getDonGia(),
                chiTietHoaDonViewModel.getSoLuong() * chiTietHoaDonViewModel.getDonGia().doubleValue()
            });
        }
    }

    private void loadTableHoaDon() {
        DefaultTableModel modeltb = new DefaultTableModel();
        List<HoaDonViewModel> hd = hoadonService.all();
        modeltb = (DefaultTableModel) tblHoaDon.getModel();
        modeltb.setRowCount(0);
        int i = 0;
        for (HoaDonViewModel hoaDon : hd) {
            modeltb.addRow(new Object[]{
                i++,
                hoaDon.getMaHD(),
                hoaDon.getMaNV(),
                hoaDon.getMaKH(),
                hoaDon.getTenKH(),
                hoaDon.getNgayTao(),
                hoaDon.getNgayDat(),
                hoaDon.getNgayShip(),
                hoaDon.getNgayNhan(),
                hoaDon.getNgayThanhToan(),
                hoaDon.getTinhTrang() == 0 ? "Chờ" : "Đã Thanh Toán",
                hoaDon.getTongTien()

            });
        }
    }

    private void loadTableHoaDonBanHang() {
        DefaultTableModel modeltb = new DefaultTableModel();
        List<HoaDonBanHangViewModel> list = hoaDonBanHangService.all();
        modeltb = (DefaultTableModel) tbHoaDonBanHang.getModel();
        modeltb.setRowCount(0);
        for (HoaDonBanHangViewModel hoaDon : list) {
            modeltb.addRow(new Object[]{
                hoaDon.getMaHD(),
                hoaDon.getNgayTao(),
                hoaDon.getTinhTrang() == 0 ? "Chờ xử lý" : "Ðã thanh toán"});
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonNV = new javax.swing.ButtonGroup();
        buttonKH = new javax.swing.ButtonGroup();
        buttonSP = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlTong = new javax.swing.JPanel();
        pnlMenu2 = new javax.swing.JPanel();
        pnlAnhDaiDien2 = new javax.swing.JPanel();
        lblAnhDaiDien2 = new javax.swing.JLabel();
        btnBanHang2 = new javax.swing.JButton();
        btnHoaDon = new javax.swing.JButton();
        btnSanPham = new javax.swing.JButton();
        btnNhanVien = new javax.swing.JButton();
        btnKhachHang = new javax.swing.JButton();
        btnThongKe = new javax.swing.JButton();
        btnDangXuat = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        pnlCacGiaoDien = new javax.swing.JPanel();
        pnlBanHang = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblgiohang = new javax.swing.JTable();
        btnlammoi = new javax.swing.JButton();
        btnxoa = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        btnnext = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbldssanpham = new javax.swing.JTable();
        txttimkiem = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        btnthem = new javax.swing.JButton();
        btntimkiem = new javax.swing.JButton();
        btnback = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane22 = new javax.swing.JScrollPane();
        tbHoaDonBanHang = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jPanel54 = new javax.swing.JPanel();
        txttenkh1 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        btnxacnhan1 = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMaHdBH = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        btnGiaoHang = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel51 = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        btnthanhtoan2 = new javax.swing.JButton();
        txtGhiChu1 = new javax.swing.JTextField();
        txtTienDu1 = new javax.swing.JLabel();
        txtKhachTra1 = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        LBtime = new javax.swing.JLabel();
        btntaohd = new javax.swing.JButton();
        btntaohd1 = new javax.swing.JButton();
        cbMaNhanVien = new javax.swing.JComboBox<>();
        txtThanhTien1 = new javax.swing.JTextField();
        cbTenKH = new javax.swing.JComboBox<>();
        pnlHoaDon = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jTextField6 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        pnlNhanVien = new javax.swing.JPanel();
        panel3 = new java.awt.Panel();
        jPanel34 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        cbCV = new javax.swing.JComboBox<>();
        jTextField12 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtDCNV = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        txtIDNhanVien = new javax.swing.JTextField();
        txtMKNV = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        txtTKNV = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        ID = new javax.swing.JLabel();
        txtHoTenNV = new javax.swing.JTextField();
        rdNamNV = new javax.swing.JRadioButton();
        jLabel49 = new javax.swing.JLabel();
        rdNuNV = new javax.swing.JRadioButton();
        dateNSNV = new com.toedter.calendar.JDateChooser();
        jButton4 = new javax.swing.JButton();
        jLabel56 = new javax.swing.JLabel();
        txtMaNhanVien = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        txtSDTNhanVien = new javax.swing.JTextField();
        jPanel36 = new javax.swing.JPanel();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jPanel38 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        tbNhanVien = new javax.swing.JTable();
        jTextField19 = new javax.swing.JTextField();
        jButton16 = new javax.swing.JButton();
        pnlKhachHang = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        rdNam = new javax.swing.JRadioButton();
        rdNu = new javax.swing.JRadioButton();
        txtSDT = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtDC = new javax.swing.JTextArea();
        jPanel24 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        txtIdKH = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        txtNgaySinh = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblKH = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        pnlThongKe = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tblLoaiThoiGian = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        lblBieuDo = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        pnlDoiMatKhau = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        pnlLayThongTin = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel42 = new javax.swing.JPanel();
        jPanel43 = new javax.swing.JPanel();
        jPanel44 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtSearch1 = new javax.swing.JTextField();
        btnSearch1 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        btnXacNhan = new javax.swing.JButton();
        pnlSanPham = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        lblMaSp = new javax.swing.JLabel();
        lblDonGia = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        lblMoTa = new javax.swing.JLabel();
        lblTrangThai = new javax.swing.JLabel();
        rdoConHang = new javax.swing.JRadioButton();
        rdoHetHang = new javax.swing.JRadioButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtMotaChiTietSP = new javax.swing.JTextArea();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        cbSIZE = new javax.swing.JComboBox<>();
        lblMaSp1 = new javax.swing.JLabel();
        cbCL = new javax.swing.JComboBox<>();
        lblMaSp2 = new javax.swing.JLabel();
        lblMaSp3 = new javax.swing.JLabel();
        cbTenSP = new javax.swing.JComboBox<>();
        lblMaSp4 = new javax.swing.JLabel();
        cbLoai = new javax.swing.JComboBox<>();
        cbHang = new javax.swing.JComboBox<>();
        lblMaSp5 = new javax.swing.JLabel();
        lblDonGia1 = new javax.swing.JLabel();
        txtTrongLuong = new javax.swing.JTextField();
        lblMaSp6 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        cbDe = new javax.swing.JComboBox<>();
        lblMaSp26 = new javax.swing.JLabel();
        txtSoLuongSP = new javax.swing.JTextField();
        lblDonGia3 = new javax.swing.JLabel();
        txttMaSPP = new javax.swing.JTextField();
        updatesp = new javax.swing.JButton();
        updatesp2 = new javax.swing.JButton();
        updatesp3 = new javax.swing.JButton();
        updatesp4 = new javax.swing.JButton();
        updatesp5 = new javax.swing.JButton();
        updatesp6 = new javax.swing.JButton();
        jPanel51 = new javax.swing.JPanel();
        txtSearchSP = new javax.swing.JTextField();
        btnTimKiem2 = new javax.swing.JButton();
        jScrollPane20 = new javax.swing.JScrollPane();
        tblQLSP = new javax.swing.JTable();
        jPanel52 = new javax.swing.JPanel();
        lblDonGia7 = new javax.swing.JLabel();
        lblTrangThai3 = new javax.swing.JLabel();
        lblDanhMuc7 = new javax.swing.JLabel();
        jComboBox12 = new javax.swing.JComboBox<>();
        jComboBox13 = new javax.swing.JComboBox<>();
        jComboBox14 = new javax.swing.JComboBox<>();
        lblDanhMuc8 = new javax.swing.JLabel();
        jComboBox15 = new javax.swing.JComboBox<>();
        lblDanhMuc9 = new javax.swing.JLabel();
        jComboBox16 = new javax.swing.JComboBox<>();
        jComboBox17 = new javax.swing.JComboBox<>();
        lblDanhMuc10 = new javax.swing.JLabel();
        jComboBox18 = new javax.swing.JComboBox<>();
        lblDanhMuc11 = new javax.swing.JLabel();
        lbTotalProducts1 = new javax.swing.JLabel();
        lbPagination1 = new javax.swing.JLabel();
        btnNext1 = new javax.swing.JButton();
        btnPrevious1 = new javax.swing.JButton();
        jPanel26 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        lblMaSp7 = new javax.swing.JLabel();
        lblDonGia2 = new javax.swing.JLabel();
        txtDonGia1 = new javax.swing.JTextField();
        lblMoTa1 = new javax.swing.JLabel();
        lblTrangThai1 = new javax.swing.JLabel();
        rdoConHang1 = new javax.swing.JRadioButton();
        rdoHetHang1 = new javax.swing.JRadioButton();
        jScrollPane12 = new javax.swing.JScrollPane();
        txtMotaChiTietSP1 = new javax.swing.JTextArea();
        btnThem1 = new javax.swing.JButton();
        btnXoa1 = new javax.swing.JButton();
        btnSua1 = new javax.swing.JButton();
        cbSIZE1 = new javax.swing.JComboBox<>();
        lblMaSp8 = new javax.swing.JLabel();
        cbCL1 = new javax.swing.JComboBox<>();
        lblMaSp9 = new javax.swing.JLabel();
        lblMaSp10 = new javax.swing.JLabel();
        cbTenSP1 = new javax.swing.JComboBox<>();
        lblMaSp11 = new javax.swing.JLabel();
        cbLoai1 = new javax.swing.JComboBox<>();
        cbHang1 = new javax.swing.JComboBox<>();
        lblMaSp12 = new javax.swing.JLabel();
        lblDonGia4 = new javax.swing.JLabel();
        txtTrongLuong1 = new javax.swing.JTextField();
        lblMaSp13 = new javax.swing.JLabel();
        txtID1 = new javax.swing.JTextField();
        cbDe1 = new javax.swing.JComboBox<>();
        lblMaSp27 = new javax.swing.JLabel();
        txtSoLuongSP1 = new javax.swing.JTextField();
        lblDonGia5 = new javax.swing.JLabel();
        txttMaSPP1 = new javax.swing.JTextField();
        updatesp1 = new javax.swing.JButton();
        updatesp7 = new javax.swing.JButton();
        updatesp8 = new javax.swing.JButton();
        updatesp9 = new javax.swing.JButton();
        updatesp10 = new javax.swing.JButton();
        updatesp11 = new javax.swing.JButton();
        jPanel29 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        lblMaSp14 = new javax.swing.JLabel();
        txtIDsp = new javax.swing.JTextField();
        lblMaSp15 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        txtTenSP = new javax.swing.JTextField();
        lblMaSp16 = new javax.swing.JLabel();
        txtMoTaSP = new javax.swing.JTextField();
        lblMaSp17 = new javax.swing.JLabel();
        btnThem2 = new javax.swing.JButton();
        btnSua2 = new javax.swing.JButton();
        btnXoa2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbSP = new javax.swing.JTable();
        jPanel37 = new javax.swing.JPanel();
        jPanel39 = new javax.swing.JPanel();
        lblMaSp18 = new javax.swing.JLabel();
        txtIDChatLieu = new javax.swing.JTextField();
        lblMaSp19 = new javax.swing.JLabel();
        txtMaChatLieu = new javax.swing.JTextField();
        txtTenChatLieu = new javax.swing.JTextField();
        lblMaSp20 = new javax.swing.JLabel();
        txtMoTaCL = new javax.swing.JTextField();
        lblMaSp21 = new javax.swing.JLabel();
        btnThem3 = new javax.swing.JButton();
        btnSua3 = new javax.swing.JButton();
        btnXoa3 = new javax.swing.JButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        tbChatLieu = new javax.swing.JTable();
        jPanel45 = new javax.swing.JPanel();
        jPanel46 = new javax.swing.JPanel();
        lblMaSp22 = new javax.swing.JLabel();
        txtIDHang = new javax.swing.JTextField();
        lblMaSp23 = new javax.swing.JLabel();
        txtMaHang = new javax.swing.JTextField();
        txtTenHang = new javax.swing.JTextField();
        lblMaSp24 = new javax.swing.JLabel();
        txtMoTaHang = new javax.swing.JTextField();
        lblMaSp25 = new javax.swing.JLabel();
        btnThem4 = new javax.swing.JButton();
        btnSua4 = new javax.swing.JButton();
        btnXoa4 = new javax.swing.JButton();
        jScrollPane14 = new javax.swing.JScrollPane();
        tbHang = new javax.swing.JTable();
        jPanel47 = new javax.swing.JPanel();
        jPanel48 = new javax.swing.JPanel();
        lblMaSp28 = new javax.swing.JLabel();
        txtIDLoai = new javax.swing.JTextField();
        lblMaSp29 = new javax.swing.JLabel();
        txtMaLoai = new javax.swing.JTextField();
        txtTenLoai = new javax.swing.JTextField();
        lblMaSp30 = new javax.swing.JLabel();
        txtMoTaLoaiGiay = new javax.swing.JTextField();
        lblMaSp31 = new javax.swing.JLabel();
        btnThem5 = new javax.swing.JButton();
        btnSua5 = new javax.swing.JButton();
        btnXoa5 = new javax.swing.JButton();
        jScrollPane17 = new javax.swing.JScrollPane();
        tbLoaiGiay = new javax.swing.JTable();
        jPanel49 = new javax.swing.JPanel();
        jPanel50 = new javax.swing.JPanel();
        lblMaSp32 = new javax.swing.JLabel();
        txtIDSIZE = new javax.swing.JTextField();
        lblMaSp33 = new javax.swing.JLabel();
        txtMaSIZE = new javax.swing.JTextField();
        txtSoSize = new javax.swing.JTextField();
        lblMaSp34 = new javax.swing.JLabel();
        btnThem6 = new javax.swing.JButton();
        btnSua6 = new javax.swing.JButton();
        btnXoa6 = new javax.swing.JButton();
        jScrollPane18 = new javax.swing.JScrollPane();
        tbSIZE = new javax.swing.JTable();
        jPanel40 = new javax.swing.JPanel();
        jPanel41 = new javax.swing.JPanel();
        lblMaSp35 = new javax.swing.JLabel();
        txtIDDeGiay = new javax.swing.JTextField();
        lblMaSp36 = new javax.swing.JLabel();
        txtMaDe = new javax.swing.JTextField();
        txtLoaiDe = new javax.swing.JTextField();
        lblMaSp37 = new javax.swing.JLabel();
        txtMoTaDe = new javax.swing.JTextField();
        lblMaSp38 = new javax.swing.JLabel();
        btnThem7 = new javax.swing.JButton();
        btnSua7 = new javax.swing.JButton();
        btnXoa7 = new javax.swing.JButton();
        jScrollPane16 = new javax.swing.JScrollPane();
        tbDeGiay = new javax.swing.JTable();
        jPanel18 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jDateNgayDat = new com.toedter.calendar.JDateChooser();
        jDateNgaySHIP = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        jDateNgayNhan = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtIDChucVu = new javax.swing.JTextField();
        txtMaChucVu = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTenChucVu = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane19 = new javax.swing.JScrollPane();
        tbChucVu = new javax.swing.JTable();
        btnsudung = new javax.swing.JButton();
        btnsudung1 = new javax.swing.JButton();
        btnsudung2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlTong.setLayout(new java.awt.BorderLayout());

        pnlMenu2.setBackground(new java.awt.Color(204, 255, 255));
        pnlMenu2.setMaximumSize(new java.awt.Dimension(200, 610));
        pnlMenu2.setMinimumSize(new java.awt.Dimension(200, 610));
        pnlMenu2.setPreferredSize(new java.awt.Dimension(200, 760));

        pnlAnhDaiDien2.setPreferredSize(new java.awt.Dimension(140, 130));

        javax.swing.GroupLayout pnlAnhDaiDien2Layout = new javax.swing.GroupLayout(pnlAnhDaiDien2);
        pnlAnhDaiDien2.setLayout(pnlAnhDaiDien2Layout);
        pnlAnhDaiDien2Layout.setHorizontalGroup(
            pnlAnhDaiDien2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAnhDaiDien2, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
        );
        pnlAnhDaiDien2Layout.setVerticalGroup(
            pnlAnhDaiDien2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAnhDaiDien2, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
        );

        pnlMenu2.add(pnlAnhDaiDien2);

        btnBanHang2.setBackground(new java.awt.Color(204, 255, 255));
        btnBanHang2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnBanHang2.setText("Bán Hàng");
        btnBanHang2.setPreferredSize(new java.awt.Dimension(170, 50));
        btnBanHang2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanHang2ActionPerformed(evt);
            }
        });
        pnlMenu2.add(btnBanHang2);

        btnHoaDon.setBackground(new java.awt.Color(204, 255, 255));
        btnHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnHoaDon.setText("Hóa đơn");
        btnHoaDon.setPreferredSize(new java.awt.Dimension(170, 50));
        btnHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoaDonActionPerformed(evt);
            }
        });
        pnlMenu2.add(btnHoaDon);

        btnSanPham.setBackground(new java.awt.Color(204, 255, 255));
        btnSanPham.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnSanPham.setText("Sản phẩm");
        btnSanPham.setPreferredSize(new java.awt.Dimension(170, 50));
        btnSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSanPhamActionPerformed(evt);
            }
        });
        pnlMenu2.add(btnSanPham);

        btnNhanVien.setBackground(new java.awt.Color(204, 255, 255));
        btnNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnNhanVien.setText("Nhân viên");
        btnNhanVien.setPreferredSize(new java.awt.Dimension(170, 50));
        btnNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanVienActionPerformed(evt);
            }
        });
        pnlMenu2.add(btnNhanVien);

        btnKhachHang.setBackground(new java.awt.Color(204, 255, 255));
        btnKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnKhachHang.setText("Khách hàng");
        btnKhachHang.setPreferredSize(new java.awt.Dimension(170, 50));
        btnKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhachHangActionPerformed(evt);
            }
        });
        pnlMenu2.add(btnKhachHang);

        btnThongKe.setBackground(new java.awt.Color(204, 255, 255));
        btnThongKe.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnThongKe.setText("Thống kê");
        btnThongKe.setPreferredSize(new java.awt.Dimension(170, 50));
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });
        pnlMenu2.add(btnThongKe);

        btnDangXuat.setBackground(new java.awt.Color(204, 255, 255));
        btnDangXuat.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnDangXuat.setText("Đăng xuất");
        btnDangXuat.setPreferredSize(new java.awt.Dimension(170, 50));
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });
        pnlMenu2.add(btnDangXuat);

        btnThoat.setBackground(new java.awt.Color(204, 255, 255));
        btnThoat.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnThoat.setText("Thoát");
        btnThoat.setPreferredSize(new java.awt.Dimension(170, 50));
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });
        pnlMenu2.add(btnThoat);

        pnlTong.add(pnlMenu2, java.awt.BorderLayout.LINE_START);

        pnlCacGiaoDien.setLayout(new java.awt.CardLayout());

        pnlBanHang.setBackground(new java.awt.Color(255, 255, 255));
        pnlBanHang.setPreferredSize(new java.awt.Dimension(1070, 760));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setPreferredSize(new java.awt.Dimension(1070, 760));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Giỏ Hàng"));

        tblgiohang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblgiohang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblgiohangMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblgiohang);

        btnlammoi.setBackground(new java.awt.Color(204, 204, 204));
        btnlammoi.setText("Làm mới");
        btnlammoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlammoiActionPerformed(evt);
            }
        });

        btnxoa.setBackground(new java.awt.Color(204, 204, 204));
        btnxoa.setText("Xóa khỏi Giỏ hàng");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(374, 374, 374)
                .addComponent(btnlammoi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnxoa)
                .addGap(0, 154, Short.MAX_VALUE))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnxoa)
                    .addComponent(btnlammoi))
                .addGap(11, 11, 11))
        );

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Sản Phẩm"));

        tbldssanpham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên Sản phẩm", "Đơn giá", "Số Lượng", "Trạng thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Float.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbldssanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbldssanphamMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbldssanpham);

        jLabel36.setText("1/3");

        btnthem.setBackground(new java.awt.Color(204, 204, 204));
        btnthem.setText("Thêm vào giỏ Hàng");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        btntimkiem.setBackground(new java.awt.Color(204, 204, 204));
        btntimkiem.setText("Tìm kiếm");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setText("Tên SP");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnback, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnnext, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105)
                .addComponent(btnthem)
                .addGap(17, 17, 17))
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btntimkiem))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btntimkiem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnback)
                    .addComponent(jLabel36)
                    .addComponent(btnnext)
                    .addComponent(btnthem))
                .addContainerGap())
        );

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Hoá Đơn"));

        tbHoaDonBanHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã", "Ngày Tạo", "Tình Trạng"
            }
        ));
        tbHoaDonBanHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHoaDonBanHangMouseClicked(evt);
            }
        });
        jScrollPane22.setViewportView(tbHoaDonBanHang);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 14, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        jPanel54.setBackground(new java.awt.Color(255, 255, 255));
        jPanel54.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "thông tin chung", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        txttenkh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttenkh1ActionPerformed(evt);
            }
        });

        jLabel28.setText("Tên KH");

        jLabel31.setText("Ngày Tạo");

        btnxacnhan1.setBackground(new java.awt.Color(204, 204, 204));
        btnxacnhan1.setText("Lấy thông tin");
        btnxacnhan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxacnhan1ActionPerformed(evt);
            }
        });

        jLabel32.setText("Mã nhân viên");

        jLabel6.setText("Mã hóa đơn");

        jLabel50.setText("Hình thức GD");

        buttonGroup1.add(btnGiaoHang);
        btnGiaoHang.setText("Giao hàng");
        btnGiaoHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGiaoHangActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Tại quầy");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jLabel51.setText("Hình thức TT");

        jRadioButton3.setText("Tiền mặt");

        jRadioButton4.setText("Chuyển khoản");

        btnthanhtoan2.setBackground(new java.awt.Color(204, 204, 204));
        btnthanhtoan2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnthanhtoan2.setText("Thanh Toán");
        btnthanhtoan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthanhtoan2ActionPerformed(evt);
            }
        });

        txtTienDu1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTienDu1.setForeground(new java.awt.Color(0, 0, 153));
        txtTienDu1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTienDu1.setText("VND");

        txtKhachTra1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtKhachTra1CaretUpdate(evt);
            }
        });

        jLabel59.setText("Thành tiền");

        jLabel60.setText("Tiền khách trả");

        jLabel61.setText("Tiền dư");

        jLabel62.setText("Ghi chú");

        LBtime.setFont(new java.awt.Font("Serif", 1, 16)); // NOI18N
        LBtime.setForeground(new java.awt.Color(255, 51, 51));
        LBtime.setText("         ");
        LBtime.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                LBtimeAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        btntaohd.setBackground(new java.awt.Color(204, 204, 204));
        btntaohd.setText("Tạo hóa đơn");
        btntaohd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntaohdActionPerformed(evt);
            }
        });

        btntaohd1.setBackground(new java.awt.Color(204, 204, 204));
        btntaohd1.setText("Tạo hóa đơn");
        btntaohd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntaohd1ActionPerformed(evt);
            }
        });

        txtThanhTien1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtThanhTien1CaretUpdate(evt);
            }
        });

        cbTenKH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel54Layout = new javax.swing.GroupLayout(jPanel54);
        jPanel54.setLayout(jPanel54Layout);
        jPanel54Layout.setHorizontalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel54Layout.createSequentialGroup()
                        .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMaHdBH)
                            .addComponent(cbMaNhanVien, 0, 151, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel54Layout.createSequentialGroup()
                        .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel54Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel54Layout.createSequentialGroup()
                                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel50)
                                    .addComponent(jLabel51))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel54Layout.createSequentialGroup()
                                .addComponent(txttenkh1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnxacnhan1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel54Layout.createSequentialGroup()
                                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButton2)
                                    .addComponent(jRadioButton3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButton4)
                                    .addComponent(btnGiaoHang))
                                .addGap(43, 43, 43)))
                        .addGap(38, 38, 38))
                    .addGroup(jPanel54Layout.createSequentialGroup()
                        .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel54Layout.createSequentialGroup()
                                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel59)
                                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel60, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel61, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtGhiChu1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTienDu1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtThanhTien1, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtKhachTra1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))))
                            .addGroup(jPanel54Layout.createSequentialGroup()
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(LBtime, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel54Layout.createSequentialGroup()
                                .addGap(102, 102, 102)
                                .addComponent(btnthanhtoan2)
                                .addGap(18, 18, 18)
                                .addComponent(btntaohd))
                            .addGroup(jPanel54Layout.createSequentialGroup()
                                .addGap(232, 232, 232)
                                .addComponent(btntaohd1)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel54Layout.setVerticalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtMaHdBH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel54Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel54Layout.createSequentialGroup()
                        .addComponent(cbMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(LBtime))
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel54Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttenkh1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28)
                            .addComponent(btnxacnhan1)))
                    .addGroup(jPanel54Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(btnGiaoHang)
                    .addComponent(jRadioButton2))
                .addGap(18, 18, 18)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton4))
                .addGap(27, 27, 27)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(txtThanhTien1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(txtKhachTra1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienDu1))
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel54Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel62))
                    .addGroup(jPanel54Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtGhiChu1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnthanhtoan2)
                    .addComponent(btntaohd))
                .addGap(32, 32, 32)
                .addComponent(btntaohd1)
                .addContainerGap(135, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlBanHangLayout = new javax.swing.GroupLayout(pnlBanHang);
        pnlBanHang.setLayout(pnlBanHangLayout);
        pnlBanHangLayout.setHorizontalGroup(
            pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBanHangLayout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 789, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel54, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlBanHangLayout.setVerticalGroup(
            pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBanHangLayout.createSequentialGroup()
                .addGroup(pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlBanHangLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlCacGiaoDien.add(pnlBanHang, "cardBanHang");

        pnlHoaDon.setBackground(new java.awt.Color(204, 204, 204));
        pnlHoaDon.setPreferredSize(new java.awt.Dimension(1070, 760));
        pnlHoaDon.setLayout(new java.awt.BorderLayout());

        jPanel20.setPreferredSize(new java.awt.Dimension(1271, 380));

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N
        jPanel21.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        tblHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã hóa đơn", "Mã nhân viên", "Mã khách hàng", "Tên Khách", "Ngày tạo", "Ngày Đặt", "Ngày Thanh Toán", "Ngày SHIP", "Ngày Nhận", "Tình Trạng", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane10.setViewportView(tblHoaDon);

        jButton10.setBackground(new java.awt.Color(204, 204, 204));
        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton10.setText("Tìm mã khách hàng");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(204, 204, 204));
        jButton11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton11.setText("Tìm mã hóa đơn");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(262, 262, 262)
                        .addComponent(jButton10)
                        .addGap(243, 243, 243)
                        .addComponent(jButton11))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(456, 456, 456)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 1155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(85, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton11)
                    .addComponent(jButton10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1301, Short.MAX_VALUE)
            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel20Layout.createSequentialGroup()
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlHoaDon.add(jPanel20, java.awt.BorderLayout.PAGE_START);

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin hóa đơn chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N
        jPanel22.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jTable8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá/1 sản phẩm", "Thành tiền", "Trạng thái"
            }
        ));
        jScrollPane11.setViewportView(jTable8);

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 974, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(190, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        pnlHoaDon.add(jPanel22, java.awt.BorderLayout.CENTER);

        pnlCacGiaoDien.add(pnlHoaDon, "cardHoaDon");

        pnlNhanVien.setBackground(new java.awt.Color(255, 102, 0));
        pnlNhanVien.setPreferredSize(new java.awt.Dimension(1070, 760));

        panel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel34.setBackground(new java.awt.Color(255, 255, 255));
        jPanel34.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "THÔNG TIN NHÂN VIÊN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(51, 0, 0))); // NOI18N
        jPanel34.setForeground(new java.awt.Color(153, 153, 153));

        jLabel26.setText("CHỨC VỤ");

        cbCV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chức vụ", "Quản lý", "Nhân Viên" }));

        jTextField12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField12ActionPerformed(evt);
            }
        });

        jLabel27.setText("LƯƠNG");

        txtDCNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDCNVActionPerformed(evt);
            }
        });

        jLabel43.setText("GIỚI TÍNH");

        jLabel44.setText("QUÊ QUÁN");

        txtIDNhanVien.setEditable(false);
        txtIDNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDNhanVienActionPerformed(evt);
            }
        });

        txtMKNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMKNVActionPerformed(evt);
            }
        });

        jLabel45.setText("MẬT KHẨU");

        txtTKNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTKNVActionPerformed(evt);
            }
        });

        jLabel46.setText("TÀI KHOẢN");

        jLabel47.setText("NGÀY SINH");

        ID.setText("ID");

        txtHoTenNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoTenNVActionPerformed(evt);
            }
        });

        buttonNV.add(rdNamNV);
        rdNamNV.setText("NAM");
        rdNamNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNamNVActionPerformed(evt);
            }
        });

        jLabel49.setText("HỌ VÀ TÊN");

        buttonNV.add(rdNuNV);
        rdNuNV.setText("NỮ");

        jButton4.setText("+");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel56.setText("MÃ NHÂN VIÊN");

        txtMaNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNhanVienActionPerformed(evt);
            }
        });

        jLabel48.setText("SĐT");

        txtSDTNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTNhanVienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbCV, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rdNamNV, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(rdNuNV, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(txtHoTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel34Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIDNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 341, Short.MAX_VALUE)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(21, 21, 21)
                            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtTKNV, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                                .addComponent(txtMKNV)
                                .addComponent(txtSDTNhanVien)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                                    .addComponent(jLabel44)
                                    .addGap(29, 29, 29))
                                .addGroup(jPanel34Layout.createSequentialGroup()
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(21, 21, 21)))
                            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDCNV, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(dateNSNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(38, 38, 38))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDCNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel44))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47)
                            .addComponent(dateNSNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel46)
                            .addComponent(txtTKNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMKNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSDTNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel48)))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIDNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ID))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel56))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHoTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel43)
                            .addComponent(rdNamNV)
                            .addComponent(rdNuNV))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(cbCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4))))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        jPanel36.setBackground(new java.awt.Color(255, 255, 255));
        jPanel36.setBorder(javax.swing.BorderFactory.createTitledBorder("CHỨC NĂNG"));

        jButton13.setBackground(new java.awt.Color(204, 204, 204));
        jButton13.setText("THÊM");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setBackground(new java.awt.Color(204, 204, 204));
        jButton14.setText("SỬA");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setBackground(new java.awt.Color(204, 204, 204));
        jButton15.setText("XÓA");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton13)
                .addGap(39, 39, 39)
                .addComponent(jButton14)
                .addGap(34, 34, 34)
                .addComponent(jButton15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel38.setBackground(new java.awt.Color(255, 255, 255));
        jPanel38.setBorder(javax.swing.BorderFactory.createTitledBorder("DANH SÁCH NHÂN VIÊN"));
        jPanel38.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        tbNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "MÃ NHÂN VIÊN", "HỌ VÀ TÊN", "GIỚI TÍNH", "CHỨC VỤ", "QUÊ QUÁN", "NGÀY SINH", "TÀI KHOẢN", "MẬT KHẨU", "SĐT"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNhanVienMouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(tbNhanVien);

        jTextField19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField19jTextField8ActionPerformed(evt);
            }
        });

        jButton16.setBackground(new java.awt.Color(204, 204, 204));
        jButton16.setText("TÌM KIẾM");

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel38Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jButton16)
                .addGap(211, 211, 211))
            .addComponent(jScrollPane15, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel38Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField19)
                    .addComponent(jButton16))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(56, 56, 56))
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pnlNhanVienLayout = new javax.swing.GroupLayout(pnlNhanVien);
        pnlNhanVien.setLayout(pnlNhanVienLayout);
        pnlNhanVienLayout.setHorizontalGroup(
            pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlNhanVienLayout.setVerticalGroup(
            pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pnlCacGiaoDien.add(pnlNhanVien, "cardNhanVien");

        pnlKhachHang.setBackground(new java.awt.Color(153, 255, 51));
        pnlKhachHang.setPreferredSize(new java.awt.Dimension(1070, 760));
        pnlKhachHang.setLayout(new java.awt.BorderLayout());

        jPanel12.setPreferredSize(new java.awt.Dimension(1271, 300));

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thiết lập thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N
        jPanel23.setPreferredSize(new java.awt.Dimension(1271, 380));

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel37.setText("Mã khách hàng");

        jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel38.setText("Tên khách hàng");

        jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel39.setText("Giới tính");

        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel40.setText("Số điện thoại");

        buttonKH.add(rdNam);
        rdNam.setSelected(true);
        rdNam.setText("Nam");
        rdNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNamActionPerformed(evt);
            }
        });

        buttonKH.add(rdNu);
        rdNu.setText("Nữ");

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel41.setText("Địa chỉ");

        txtDC.setColumns(20);
        txtDC.setRows(5);
        jScrollPane9.setViewportView(txtDC);

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel24.setForeground(new java.awt.Color(102, 255, 102));

        btnAdd.setBackground(new java.awt.Color(204, 204, 204));
        btnAdd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAdd.setText("Thêm thông tin");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(204, 204, 204));
        btnDelete.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDelete.setText("Xóa thông tin");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(204, 204, 204));
        btnUpdate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnUpdate.setText("Sửa thông tin");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btnAdd)
                .addGap(50, 50, 50)
                .addComponent(btnUpdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(btnDelete)
                .addGap(44, 44, 44))
        );

        txtIdKH.setEditable(false);

        jLabel57.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel57.setText("ID khách hàng");

        jLabel58.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel58.setText("Ngày Sinh");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel57)
                            .addComponent(jLabel37))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdKH, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38)
                            .addComponent(jLabel39))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addComponent(rdNam)
                                .addGap(54, 54, 54)
                                .addComponent(rdNu))
                            .addComponent(txtTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE))))
                .addGap(133, 133, 133)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel41)
                    .addComponent(jLabel40)
                    .addComponent(jLabel58))
                .addGap(32, 32, 32)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtNgaySinh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)))
                .addGap(62, 62, 62)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 38, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jLabel57))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel58)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(txtIdKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel39)
                            .addComponent(rdNam)
                            .addComponent(rdNu)))
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, 1301, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pnlKhachHang.add(jPanel12, java.awt.BorderLayout.PAGE_START);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        tblKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã khách hàng", "Tên khách hàng", "Giới tính", "Ngày Sinh", "Số điện thoại", "Địa chỉ"
            }
        ));
        tblKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKHMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblKH);

        btnSearch.setBackground(new java.awt.Color(204, 204, 204));
        btnSearch.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSearch.setText("Tìm kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6)
                .addContainerGap())
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSearch)
                .addContainerGap(776, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlKhachHang.add(jPanel3, java.awt.BorderLayout.CENTER);

        pnlCacGiaoDien.add(pnlKhachHang, "cardKhachHang");

        pnlThongKe.setBackground(new java.awt.Color(255, 255, 51));
        pnlThongKe.setPreferredSize(new java.awt.Dimension(1070, 760));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel27.setBackground(new java.awt.Color(204, 255, 255));
        jPanel27.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel18.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel18.setText("200 hóa đơn");

        jLabel25.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel25.setText("Tổng số hóa đơn");

        jLabel19.setText("jLabel15");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18))
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        jPanel6.setBackground(new java.awt.Color(204, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel9.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel9.setText("Tổng doanh thu");

        jLabel13.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel13.setText("100.000.000 VND");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Loại thời gian:");

        tblLoaiThoiGian.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn khoảng thời gian", "Ngày hôm nay" }));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Ngày bắt đầu:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Ngày kết thúc:");

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("Tìm kiếm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(204, 204, 204));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setText("Làm mới");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(204, 204, 204));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setText("Xuất báo cáo");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tblLoaiThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel3))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(45, 45, 45)
                .addComponent(jButton2)
                .addGap(34, 34, 34)
                .addComponent(jButton3)
                .addGap(44, 44, 44))
            .addComponent(jSeparator1)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton2)
                            .addComponent(jButton1)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(tblLoaiThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jTabbedPane1.setBackground(new java.awt.Color(204, 255, 102));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Ngày", "Tổng số hóa đơn trong ngày", "Tổng doanh thu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel4.setText("Sắp xếp:");

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ngày", "Số hóa đơn", "Tổng doanh thu" }));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(996, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Bảng doanh thu", jPanel9);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblBieuDo, javax.swing.GroupLayout.DEFAULT_SIZE, 1299, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblBieuDo, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Biểu đồ", jPanel8);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Tên đồ uống", "Số lượng đã bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel5.setText("Lọc:");

        jComboBox2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Bán chạy", "Không bán được" }));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1299, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2))
        );

        jTabbedPane1.addTab("Sản phẩm", jPanel10);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103))
            .addComponent(jTabbedPane1)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );

        javax.swing.GroupLayout pnlThongKeLayout = new javax.swing.GroupLayout(pnlThongKe);
        pnlThongKe.setLayout(pnlThongKeLayout);
        pnlThongKeLayout.setHorizontalGroup(
            pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1301, Short.MAX_VALUE)
            .addGroup(pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlThongKeLayout.setVerticalGroup(
            pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 792, Short.MAX_VALUE)
            .addGroup(pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlCacGiaoDien.add(pnlThongKe, "cardThongKe");

        pnlDoiMatKhau.setBackground(new java.awt.Color(255, 255, 255));
        pnlDoiMatKhau.setPreferredSize(new java.awt.Dimension(1070, 760));

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đổi mật khẩu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText("Mật khẩu cũ");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setText("Nhập lại mật khẩu mới");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setText("Mật khẩu mới");

        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(204, 204, 204));
        jButton8.setText("Đổi mật khẩu");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel22))
                .addGap(49, 49, 49)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField10)
                    .addComponent(jTextField9)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(176, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(322, 322, 322))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(149, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlDoiMatKhauLayout = new javax.swing.GroupLayout(pnlDoiMatKhau);
        pnlDoiMatKhau.setLayout(pnlDoiMatKhauLayout);
        pnlDoiMatKhauLayout.setHorizontalGroup(
            pnlDoiMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoiMatKhauLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(415, Short.MAX_VALUE))
        );
        pnlDoiMatKhauLayout.setVerticalGroup(
            pnlDoiMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDoiMatKhauLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(183, 183, 183))
        );

        pnlCacGiaoDien.add(pnlDoiMatKhau, "cardDoiMatKhau");

        pnlLayThongTin.setBackground(new java.awt.Color(255, 255, 255));
        pnlLayThongTin.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 592, Short.MAX_VALUE)
        );

        pnlLayThongTin.add(jPanel1, java.awt.BorderLayout.LINE_START);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1301, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        pnlLayThongTin.add(jPanel7, java.awt.BorderLayout.PAGE_START);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 592, Short.MAX_VALUE)
        );

        pnlLayThongTin.add(jPanel14, java.awt.BorderLayout.LINE_END);

        jPanel42.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1301, Short.MAX_VALUE)
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        pnlLayThongTin.add(jPanel42, java.awt.BorderLayout.PAGE_END);

        jPanel43.setBackground(new java.awt.Color(255, 255, 255));
        jPanel43.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel44.setBackground(new java.awt.Color(255, 255, 255));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel29.setText("THÔNG TIN KHÁCH HÀNG");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel30.setText("Tên Khách Hàng");

        txtSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearch1ActionPerformed(evt);
            }
        });

        btnSearch1.setBackground(new java.awt.Color(204, 204, 204));
        btnSearch1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSearch1.setText("Tìm Kiếm");
        btnSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch1ActionPerformed(evt);
            }
        });

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane8.setViewportView(tblKhachHang);

        btnXacNhan.setBackground(new java.awt.Color(204, 204, 204));
        btnXacNhan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXacNhan.setText("Xác Nhận");
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel44Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel44Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel44Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addGap(38, 38, 38))
                            .addGroup(jPanel44Layout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addGap(31, 31, 31)
                                .addComponent(txtSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)))
                        .addGap(13, 13, 13)
                        .addComponent(btnSearch1))
                    .addGroup(jPanel44Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 779, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(272, Short.MAX_VALUE))
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel29)
                .addGap(18, 18, 18)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel30)
                    .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearch1)))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlLayThongTin.add(jPanel43, java.awt.BorderLayout.CENTER);

        pnlCacGiaoDien.add(pnlLayThongTin, "cardLayThongTin");

        pnlSanPham.setBackground(new java.awt.Color(255, 255, 255));

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));
        jPanel31.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)), "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel31.setForeground(new java.awt.Color(51, 51, 51));
        jPanel31.setName(""); // NOI18N

        lblMaSp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp.setText("Mã Sản Phẩm");

        lblDonGia.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDonGia.setText("Đơn giá");

        txtDonGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDonGiaActionPerformed(evt);
            }
        });

        lblMoTa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMoTa.setText("Mô tả");

        lblTrangThai.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTrangThai.setText("Trạng thái");

        buttonSP.add(rdoConHang);
        rdoConHang.setText("Còn Hàng");
        rdoConHang.setEnabled(false);
        rdoConHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoConHangActionPerformed(evt);
            }
        });

        buttonSP.add(rdoHetHang);
        rdoHetHang.setText("Hết Hàng");
        rdoHetHang.setEnabled(false);

        txtMotaChiTietSP.setColumns(20);
        txtMotaChiTietSP.setRows(5);
        jScrollPane7.setViewportView(txtMotaChiTietSP);

        btnThem.setBackground(new java.awt.Color(204, 204, 204));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem.setText("Thêm thông tin");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(204, 204, 204));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa.setText("Xóa thông tin");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(204, 204, 204));
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSua.setText("Sửa thông tin");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        lblMaSp1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp1.setText("SIZE");

        lblMaSp2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp2.setText("Chất Liệu");

        lblMaSp3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp3.setText("Tên Sản Phẩm");

        cbTenSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTenSPActionPerformed(evt);
            }
        });

        lblMaSp4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp4.setText("Loại");

        lblMaSp5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp5.setText("Hãng");

        lblDonGia1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDonGia1.setText("Trọng Lượng");

        txtTrongLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTrongLuongActionPerformed(evt);
            }
        });

        lblMaSp6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp6.setText("ID");

        txtID.setEditable(false);

        cbDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDeActionPerformed(evt);
            }
        });

        lblMaSp26.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp26.setText("Đế");

        txtSoLuongSP.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSoLuongSPCaretUpdate(evt);
            }
        });

        lblDonGia3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDonGia3.setText("Số Lượng");

        txttMaSPP.setEditable(false);
        txttMaSPP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttMaSPPActionPerformed(evt);
            }
        });

        updatesp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatespActionPerformed(evt);
            }
        });

        updatesp2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatesp2ActionPerformed(evt);
            }
        });

        updatesp3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatesp3ActionPerformed(evt);
            }
        });

        updatesp4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatesp4ActionPerformed(evt);
            }
        });

        updatesp5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatesp5ActionPerformed(evt);
            }
        });

        updatesp6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatesp6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addComponent(lblMaSp2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbCL, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel31Layout.createSequentialGroup()
                                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblMaSp1)
                                            .addComponent(lblMaSp4))
                                        .addGap(88, 88, 88)
                                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbLoai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbSIZE, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(lblMaSp5)
                                    .addGroup(jPanel31Layout.createSequentialGroup()
                                        .addComponent(lblMaSp26)
                                        .addGap(97, 97, 97)
                                        .addComponent(cbDe, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(cbHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMaSp3)
                            .addComponent(lblMaSp6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtID)
                            .addComponent(cbTenSP, 0, 164, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(updatesp)
                    .addComponent(updatesp2)
                    .addComponent(updatesp3)
                    .addComponent(updatesp5)
                    .addComponent(updatesp4)
                    .addComponent(updatesp6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDonGia1)
                    .addComponent(lblDonGia)
                    .addComponent(lblDonGia3)
                    .addComponent(lblTrangThai)
                    .addComponent(lblMoTa)
                    .addComponent(lblMaSp))
                .addGap(88, 88, 88)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTrongLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDonGia)
                            .addComponent(txttMaSPP)
                            .addComponent(txtSoLuongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addComponent(rdoConHang)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rdoHetHang)))
                        .addGap(97, 97, 97)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMaSp6))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblMaSp3)
                                .addComponent(cbTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(updatesp, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(updatesp2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblMaSp2)
                                .addComponent(cbCL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblMaSp1)
                                .addComponent(cbSIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(updatesp3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(updatesp5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblMaSp4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblMaSp5))
                            .addComponent(updatesp4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblMaSp26))
                            .addComponent(updatesp6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMaSp)
                            .addComponent(txttMaSPP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThem))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDonGia)
                            .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSua))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDonGia1)
                            .addComponent(txtTrongLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoa))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDonGia3)
                            .addComponent(txtSoLuongSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTrangThai)
                            .addComponent(rdoConHang)
                            .addComponent(rdoHetHang))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMoTa)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jPanel51.setBackground(new java.awt.Color(255, 255, 255));
        jPanel51.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(255, 255, 255), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)), "Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        txtSearchSP.setName(""); // NOI18N
        txtSearchSP.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearchSPCaretUpdate(evt);
            }
        });

        btnTimKiem2.setBackground(new java.awt.Color(204, 204, 204));
        btnTimKiem2.setText("Tìm Kiếm");

        tblQLSP.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(0, 0, 0)));
        tblQLSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Mã SP", "Tên SP", "SIZE", "Loại", "Hãng", "Đê Giày", "Chất Liệu", "Số Lượng", "Ðơn Giá", "Trọng Lượng", "Trạng Thái", "Mô Tả"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Float.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblQLSP.setGridColor(new java.awt.Color(204, 255, 255));
        tblQLSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQLSPMouseClicked(evt);
            }
        });
        jScrollPane20.setViewportView(tblQLSP);

        jPanel52.setBackground(new java.awt.Color(255, 255, 255));
        jPanel52.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 255, 255), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)), "Lọc", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        lblDonGia7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDonGia7.setText("Đơn giá");

        lblTrangThai3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTrangThai3.setText("Trạng thái");

        lblDanhMuc7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDanhMuc7.setText("Size");

        jComboBox12.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "<50k", "50k-100k", ">100k" }));

        jComboBox13.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Trà", "Cafe" }));

        jComboBox14.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Còn Hàng", "Hết Hàng" }));

        lblDanhMuc8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDanhMuc8.setText("Loại");

        jComboBox15.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Trà", "Cafe" }));

        lblDanhMuc9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDanhMuc9.setText("Hãng");

        jComboBox16.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Trà", "Cafe" }));

        jComboBox17.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Trà", "Cafe" }));

        lblDanhMuc10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDanhMuc10.setText("Đế");

        jComboBox18.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Trà", "Cafe" }));

        lblDanhMuc11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDanhMuc11.setText("Chất Liệu");

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lblDonGia7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTrangThai3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lblDanhMuc11, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDanhMuc10, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDanhMuc9, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDanhMuc8, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDanhMuc7, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox13, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox15, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox16, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox17, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox18, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jComboBox14, 0, 113, Short.MAX_VALUE)
                        .addComponent(jComboBox12, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDonGia7)
                    .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTrangThai3)
                    .addComponent(jComboBox14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDanhMuc7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDanhMuc8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDanhMuc9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDanhMuc10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDanhMuc11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbTotalProducts1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbTotalProducts1.setText("Total: 0");

        lbPagination1.setText("     1/1");

        btnNext1.setText(">");
        btnNext1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext1ActionPerformed(evt);
            }
        });

        btnPrevious1.setText("<");
        btnPrevious1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevious1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
        jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel51Layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(txtSearchSP, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTimKiem2))
                    .addGroup(jPanel51Layout.createSequentialGroup()
                        .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel51Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 974, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel51Layout.createSequentialGroup()
                                .addGap(334, 334, 334)
                                .addComponent(btnPrevious1)
                                .addGap(82, 82, 82)
                                .addComponent(lbPagination1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)
                                .addComponent(btnNext1)
                                .addGap(63, 63, 63)
                                .addComponent(lbTotalProducts1)))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel51Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel51Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnPrevious1)
                            .addComponent(btnNext1)
                            .addComponent(lbTotalProducts1)))
                    .addGroup(jPanel51Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbPagination1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(66, 66, 66))
        );

        javax.swing.GroupLayout pnlSanPhamLayout = new javax.swing.GroupLayout(pnlSanPham);
        pnlSanPham.setLayout(pnlSanPhamLayout);
        pnlSanPhamLayout.setHorizontalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlSanPhamLayout.setVerticalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel51, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlCacGiaoDien.add(pnlSanPham, "cardSanPham");

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));
        jPanel26.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel26.setForeground(new java.awt.Color(255, 255, 255));

        jPanel32.setBackground(new java.awt.Color(255, 255, 255));
        jPanel32.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel32.setForeground(new java.awt.Color(255, 255, 255));
        jPanel32.setName(""); // NOI18N

        lblMaSp7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp7.setText("Mã Sản Phẩm");

        lblDonGia2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDonGia2.setText("Đơn giá");

        txtDonGia1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDonGia1ActionPerformed(evt);
            }
        });

        lblMoTa1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMoTa1.setText("Mô tả");

        lblTrangThai1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTrangThai1.setText("Trạng thái");

        rdoConHang1.setText("Còn Hàng");
        rdoConHang1.setEnabled(false);
        rdoConHang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoConHang1ActionPerformed(evt);
            }
        });

        rdoHetHang1.setText("Hết Hàng");
        rdoHetHang1.setEnabled(false);

        txtMotaChiTietSP1.setColumns(20);
        txtMotaChiTietSP1.setRows(5);
        jScrollPane12.setViewportView(txtMotaChiTietSP1);

        btnThem1.setBackground(new java.awt.Color(204, 204, 204));
        btnThem1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem1.setText("Thêm thông tin");
        btnThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem1ActionPerformed(evt);
            }
        });

        btnXoa1.setBackground(new java.awt.Color(204, 204, 204));
        btnXoa1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa1.setText("Xóa thông tin");
        btnXoa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa1ActionPerformed(evt);
            }
        });

        btnSua1.setBackground(new java.awt.Color(204, 204, 204));
        btnSua1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSua1.setText("Sửa thông tin");
        btnSua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua1ActionPerformed(evt);
            }
        });

        lblMaSp8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp8.setText("SIZE");

        lblMaSp9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp9.setText("Chất Liệu");

        lblMaSp10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp10.setText("Tên Sản Phẩm");

        cbTenSP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTenSP1ActionPerformed(evt);
            }
        });

        lblMaSp11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp11.setText("Loại");

        lblMaSp12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp12.setText("Hãng");

        lblDonGia4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDonGia4.setText("Trọng Lượng");

        txtTrongLuong1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTrongLuong1ActionPerformed(evt);
            }
        });

        lblMaSp13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp13.setText("ID");

        txtID1.setEditable(false);

        cbDe1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDe1ActionPerformed(evt);
            }
        });

        lblMaSp27.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp27.setText("Đế");

        txtSoLuongSP1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSoLuongSP1CaretUpdate(evt);
            }
        });

        lblDonGia5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDonGia5.setText("Số Lượng");

        txttMaSPP1.setEditable(false);
        txttMaSPP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttMaSPP1ActionPerformed(evt);
            }
        });

        updatesp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatesp1ActionPerformed(evt);
            }
        });

        updatesp7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatesp7ActionPerformed(evt);
            }
        });

        updatesp8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatesp8ActionPerformed(evt);
            }
        });

        updatesp9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatesp9ActionPerformed(evt);
            }
        });

        updatesp10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatesp10ActionPerformed(evt);
            }
        });

        updatesp11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatesp11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel32Layout.createSequentialGroup()
                                .addComponent(lblMaSp9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbCL1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel32Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel32Layout.createSequentialGroup()
                                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblMaSp8)
                                            .addComponent(lblMaSp11))
                                        .addGap(88, 88, 88)
                                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbLoai1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbSIZE1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(lblMaSp12)
                                    .addGroup(jPanel32Layout.createSequentialGroup()
                                        .addComponent(lblMaSp27)
                                        .addGap(97, 97, 97)
                                        .addComponent(cbDe1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(cbHang1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMaSp10)
                            .addComponent(lblMaSp13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtID1)
                            .addComponent(cbTenSP1, 0, 164, Short.MAX_VALUE))))
                .addGap(18, 75, Short.MAX_VALUE)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(updatesp1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(updatesp11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(updatesp9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(updatesp10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(updatesp8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(updatesp7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDonGia4)
                            .addComponent(lblDonGia2)
                            .addComponent(lblDonGia5)
                            .addComponent(lblTrangThai1)
                            .addComponent(lblMoTa1)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                        .addGap(230, 230, 230)
                        .addComponent(lblMaSp7)))
                .addGap(18, 18, 18)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTrongLuong1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDonGia1)
                            .addComponent(txttMaSPP1)
                            .addComponent(txtSoLuongSP1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel32Layout.createSequentialGroup()
                                .addComponent(rdoConHang1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rdoHetHang1)))
                        .addGap(97, 97, 97)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSua1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThem1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoa1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 70, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtID1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMaSp13))
                .addGap(21, 21, 21)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblMaSp10)
                        .addComponent(cbTenSP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(updatesp1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(updatesp7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblMaSp9)
                        .addComponent(cbCL1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblMaSp8)
                        .addComponent(cbSIZE1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(updatesp8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(updatesp10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbLoai1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblMaSp11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbHang1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblMaSp12))
                    .addComponent(updatesp9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbDe1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblMaSp27))
                    .addComponent(updatesp11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(80, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaSp7)
                    .addComponent(txttMaSPP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem1))
                .addGap(18, 18, 18)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDonGia2)
                    .addComponent(txtDonGia1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua1))
                .addGap(18, 18, 18)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDonGia4)
                    .addComponent(txtTrongLuong1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDonGia5)
                    .addComponent(txtSoLuongSP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTrangThai1)
                    .addComponent(rdoConHang1)
                    .addComponent(rdoHetHang1))
                .addGap(18, 18, 18)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMoTa1)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));
        jPanel29.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)), "Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(120, Short.MAX_VALUE))
        );

        pnlCacGiaoDien.add(jPanel26, "");

        jPanel33.setBackground(new java.awt.Color(255, 255, 255));

        jPanel35.setBackground(new java.awt.Color(255, 255, 255));
        jPanel35.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel35.setForeground(new java.awt.Color(255, 255, 255));

        lblMaSp14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp14.setText("ID");

        txtIDsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDspActionPerformed(evt);
            }
        });

        lblMaSp15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp15.setText("Mã SP");

        txtMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSPActionPerformed(evt);
            }
        });

        txtTenSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSPActionPerformed(evt);
            }
        });

        lblMaSp16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp16.setText("Tên SP");

        txtMoTaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMoTaSPActionPerformed(evt);
            }
        });

        lblMaSp17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp17.setText("Mô Tả");

        btnThem2.setBackground(new java.awt.Color(204, 204, 204));
        btnThem2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem2.setText("Thêm thông tin");
        btnThem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem2ActionPerformed(evt);
            }
        });

        btnSua2.setBackground(new java.awt.Color(204, 204, 204));
        btnSua2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSua2.setText("Sửa thông tin");
        btnSua2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua2ActionPerformed(evt);
            }
        });

        btnXoa2.setBackground(new java.awt.Color(204, 204, 204));
        btnXoa2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa2.setText("Xóa thông tin");
        btnXoa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa2ActionPerformed(evt);
            }
        });

        tbSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID SP", "Mã SP", "Tên SP", "Mô Tả"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSPMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbSP);

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel35Layout.createSequentialGroup()
                                    .addComponent(lblMaSp14)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtIDsp, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel35Layout.createSequentialGroup()
                                    .addComponent(lblMaSp15)
                                    .addGap(21, 21, 21)
                                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel35Layout.createSequentialGroup()
                                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMaSp16)
                                    .addComponent(lblMaSp17))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMoTaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnXoa2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSua2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThem2, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
                        .addGap(18, 18, 18))))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMaSp14)
                            .addComponent(txtIDsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThem2))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMaSp15)
                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMaSp16)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addComponent(btnSua2)
                        .addGap(33, 33, 33)
                        .addComponent(btnXoa2)))
                .addGap(31, 31, 31)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaSp17)
                    .addComponent(txtMoTaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(559, Short.MAX_VALUE))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(223, Short.MAX_VALUE))
        );

        pnlCacGiaoDien.add(jPanel33, "SanPham");

        jPanel37.setBackground(new java.awt.Color(255, 255, 255));

        jPanel39.setBackground(new java.awt.Color(255, 255, 255));
        jPanel39.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chất Liệu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel39.setForeground(new java.awt.Color(255, 255, 255));

        lblMaSp18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp18.setText("ID");

        txtIDChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDChatLieuActionPerformed(evt);
            }
        });

        lblMaSp19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp19.setText("Mã Chất Liệu");

        txtMaChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaChatLieuActionPerformed(evt);
            }
        });

        txtTenChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenChatLieuActionPerformed(evt);
            }
        });

        lblMaSp20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp20.setText("Tên Chất Liệu");

        txtMoTaCL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMoTaCLActionPerformed(evt);
            }
        });

        lblMaSp21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp21.setText("Mô Tả");

        btnThem3.setBackground(new java.awt.Color(204, 204, 204));
        btnThem3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem3.setText("Thêm thông tin");
        btnThem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem3ActionPerformed(evt);
            }
        });

        btnSua3.setBackground(new java.awt.Color(204, 204, 204));
        btnSua3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSua3.setText("Sửa thông tin");
        btnSua3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua3ActionPerformed(evt);
            }
        });

        btnXoa3.setBackground(new java.awt.Color(204, 204, 204));
        btnXoa3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa3.setText("Xóa thông tin");
        btnXoa3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa3ActionPerformed(evt);
            }
        });

        tbChatLieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Mã Chất Liệu", "Tên Chất Liệu", "Mô Tả"
            }
        ));
        tbChatLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbChatLieuMouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(tbChatLieu);

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel39Layout.createSequentialGroup()
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addContainerGap(29, Short.MAX_VALUE)
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel39Layout.createSequentialGroup()
                                .addComponent(lblMaSp18)
                                .addGap(81, 81, 81)
                                .addComponent(txtIDChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel39Layout.createSequentialGroup()
                                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMaSp20)
                                    .addComponent(lblMaSp21))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMoTaCL, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTenChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblMaSp19, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnXoa3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSua3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThem3, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))))
                .addGap(18, 18, 18))
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMaSp18)
                            .addComponent(txtIDChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThem3))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMaSp19)
                            .addComponent(txtMaChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMaSp20)
                            .addComponent(txtTenChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addComponent(btnSua3)
                        .addGap(33, 33, 33)
                        .addComponent(btnXoa3)))
                .addGap(31, 31, 31)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaSp21)
                    .addComponent(txtMoTaCL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(559, Short.MAX_VALUE))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(223, Short.MAX_VALUE))
        );

        pnlCacGiaoDien.add(jPanel37, "ChatLieu");

        jPanel45.setBackground(new java.awt.Color(255, 255, 255));

        jPanel46.setBackground(new java.awt.Color(255, 255, 255));
        jPanel46.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hãng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel46.setForeground(new java.awt.Color(255, 255, 255));

        lblMaSp22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp22.setText("ID");

        txtIDHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDHangActionPerformed(evt);
            }
        });

        lblMaSp23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp23.setText("Mã Hãng");

        txtMaHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaHangActionPerformed(evt);
            }
        });

        txtTenHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenHangActionPerformed(evt);
            }
        });

        lblMaSp24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp24.setText("Tên Hãng");

        txtMoTaHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMoTaHangActionPerformed(evt);
            }
        });

        lblMaSp25.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp25.setText("Mô Tả");

        btnThem4.setBackground(new java.awt.Color(204, 204, 204));
        btnThem4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem4.setText("Thêm thông tin");
        btnThem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem4ActionPerformed(evt);
            }
        });

        btnSua4.setBackground(new java.awt.Color(204, 204, 204));
        btnSua4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSua4.setText("Sửa thông tin");
        btnSua4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua4ActionPerformed(evt);
            }
        });

        btnXoa4.setBackground(new java.awt.Color(204, 204, 204));
        btnXoa4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa4.setText("Xóa thông tin");
        btnXoa4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa4ActionPerformed(evt);
            }
        });

        tbHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Mã Hãng", "Tên Hãng", "Mô Tả"
            }
        ));
        tbHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHangMouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(tbHang);

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel46Layout.createSequentialGroup()
                                    .addComponent(lblMaSp22)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtIDHang, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel46Layout.createSequentialGroup()
                                    .addComponent(lblMaSp23)
                                    .addGap(21, 21, 21)
                                    .addComponent(txtMaHang, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel46Layout.createSequentialGroup()
                                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMaSp24)
                                    .addComponent(lblMaSp25))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMoTaHang, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTenHang, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                        .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnXoa4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSua4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThem4, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
                        .addGap(18, 18, 18))))
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMaSp22)
                            .addComponent(txtIDHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThem4))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMaSp23)
                            .addComponent(txtMaHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMaSp24)
                            .addComponent(txtTenHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addComponent(btnSua4)
                        .addGap(33, 33, 33)
                        .addComponent(btnXoa4)))
                .addGap(31, 31, 31)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaSp25)
                    .addComponent(txtMoTaHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(559, Short.MAX_VALUE))
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(223, Short.MAX_VALUE))
        );

        pnlCacGiaoDien.add(jPanel45, "Hang");

        jPanel47.setBackground(new java.awt.Color(255, 255, 255));

        jPanel48.setBackground(new java.awt.Color(255, 255, 255));
        jPanel48.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Loại Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel48.setForeground(new java.awt.Color(255, 255, 255));

        lblMaSp28.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp28.setText("ID");

        txtIDLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDLoaiActionPerformed(evt);
            }
        });

        lblMaSp29.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp29.setText("Mã Loại");

        txtMaLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaLoaiActionPerformed(evt);
            }
        });

        txtTenLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenLoaiActionPerformed(evt);
            }
        });

        lblMaSp30.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp30.setText("Tên Loại");

        txtMoTaLoaiGiay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMoTaLoaiGiayActionPerformed(evt);
            }
        });

        lblMaSp31.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp31.setText("Mô Tả");

        btnThem5.setBackground(new java.awt.Color(204, 204, 204));
        btnThem5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem5.setText("Thêm thông tin");
        btnThem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem5ActionPerformed(evt);
            }
        });

        btnSua5.setBackground(new java.awt.Color(204, 204, 204));
        btnSua5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSua5.setText("Sửa thông tin");
        btnSua5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua5ActionPerformed(evt);
            }
        });

        btnXoa5.setBackground(new java.awt.Color(204, 204, 204));
        btnXoa5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa5.setText("Xóa thông tin");
        btnXoa5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa5ActionPerformed(evt);
            }
        });

        tbLoaiGiay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Mã Loại", "Tên Loại", "Mô Tả"
            }
        ));
        tbLoaiGiay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbLoaiGiayMouseClicked(evt);
            }
        });
        jScrollPane17.setViewportView(tbLoaiGiay);

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel48Layout.createSequentialGroup()
                        .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel48Layout.createSequentialGroup()
                        .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel48Layout.createSequentialGroup()
                                    .addComponent(lblMaSp28)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtIDLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel48Layout.createSequentialGroup()
                                    .addComponent(lblMaSp29)
                                    .addGap(21, 21, 21)
                                    .addComponent(txtMaLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel48Layout.createSequentialGroup()
                                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMaSp30)
                                    .addComponent(lblMaSp31))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMoTaLoaiGiay, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTenLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
                        .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnXoa5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSua5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThem5, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
                        .addGap(18, 18, 18))))
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel48Layout.createSequentialGroup()
                        .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMaSp28)
                            .addComponent(txtIDLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThem5))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMaSp29)
                            .addComponent(txtMaLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMaSp30)
                            .addComponent(txtTenLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel48Layout.createSequentialGroup()
                        .addComponent(btnSua5)
                        .addGap(33, 33, 33)
                        .addComponent(btnXoa5)))
                .addGap(31, 31, 31)
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaSp31)
                    .addComponent(txtMoTaLoaiGiay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(559, Short.MAX_VALUE))
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(223, Short.MAX_VALUE))
        );

        pnlCacGiaoDien.add(jPanel47, "LoaiSanPham");

        jPanel49.setBackground(new java.awt.Color(255, 255, 255));

        jPanel50.setBackground(new java.awt.Color(255, 255, 255));
        jPanel50.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SIZE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel50.setForeground(new java.awt.Color(255, 255, 255));

        lblMaSp32.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp32.setText("ID");

        txtIDSIZE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDSIZEActionPerformed(evt);
            }
        });

        lblMaSp33.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp33.setText("Mã SIZE");

        txtMaSIZE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSIZEActionPerformed(evt);
            }
        });

        txtSoSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoSizeActionPerformed(evt);
            }
        });

        lblMaSp34.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp34.setText("Số SIZE");

        btnThem6.setBackground(new java.awt.Color(204, 204, 204));
        btnThem6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem6.setText("Thêm thông tin");
        btnThem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem6ActionPerformed(evt);
            }
        });

        btnSua6.setBackground(new java.awt.Color(204, 204, 204));
        btnSua6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSua6.setText("Sửa thông tin");
        btnSua6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua6ActionPerformed(evt);
            }
        });

        btnXoa6.setBackground(new java.awt.Color(204, 204, 204));
        btnXoa6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa6.setText("Xóa thông tin");
        btnXoa6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa6ActionPerformed(evt);
            }
        });

        tbSIZE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Mã SP", "Số SIZE"
            }
        ));
        tbSIZE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSIZEMouseClicked(evt);
            }
        });
        jScrollPane18.setViewportView(tbSIZE);

        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
        jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel50Layout.createSequentialGroup()
                        .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel50Layout.createSequentialGroup()
                        .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel50Layout.createSequentialGroup()
                                .addComponent(lblMaSp32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtIDSIZE, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel50Layout.createSequentialGroup()
                                .addComponent(lblMaSp33)
                                .addGap(21, 21, 21)
                                .addComponent(txtMaSIZE, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel50Layout.createSequentialGroup()
                                .addComponent(lblMaSp34)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtSoSize, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                        .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnXoa6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSua6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThem6, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
                        .addGap(18, 18, 18))))
        );
        jPanel50Layout.setVerticalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel50Layout.createSequentialGroup()
                        .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMaSp32)
                            .addComponent(txtIDSIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThem6))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMaSp33)
                            .addComponent(txtMaSIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMaSp34)
                            .addComponent(txtSoSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel50Layout.createSequentialGroup()
                        .addComponent(btnSua6)
                        .addGap(33, 33, 33)
                        .addComponent(btnXoa6)))
                .addGap(68, 68, 68)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
        jPanel49.setLayout(jPanel49Layout);
        jPanel49Layout.setHorizontalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(559, Short.MAX_VALUE))
        );
        jPanel49Layout.setVerticalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(226, Short.MAX_VALUE))
        );

        pnlCacGiaoDien.add(jPanel49, "Size");

        jPanel40.setBackground(new java.awt.Color(255, 255, 255));

        jPanel41.setBackground(new java.awt.Color(255, 255, 255));
        jPanel41.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel41.setForeground(new java.awt.Color(255, 255, 255));

        lblMaSp35.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp35.setText("ID");

        txtIDDeGiay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDDeGiayActionPerformed(evt);
            }
        });

        lblMaSp36.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp36.setText("Mã Đế");

        txtMaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaDeActionPerformed(evt);
            }
        });

        txtLoaiDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoaiDeActionPerformed(evt);
            }
        });

        lblMaSp37.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp37.setText("Loại Đế");

        txtMoTaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMoTaDeActionPerformed(evt);
            }
        });

        lblMaSp38.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaSp38.setText("Mô Tả");

        btnThem7.setBackground(new java.awt.Color(204, 204, 204));
        btnThem7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem7.setText("Thêm thông tin");
        btnThem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem7ActionPerformed(evt);
            }
        });

        btnSua7.setBackground(new java.awt.Color(204, 204, 204));
        btnSua7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSua7.setText("Sửa thông tin");
        btnSua7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua7ActionPerformed(evt);
            }
        });

        btnXoa7.setBackground(new java.awt.Color(204, 204, 204));
        btnXoa7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa7.setText("Xóa thông tin");
        btnXoa7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa7ActionPerformed(evt);
            }
        });

        tbDeGiay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID ", "Mã Đế", "Tên Đế", "Mô Tả"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbDeGiay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDeGiayMouseClicked(evt);
            }
        });
        jScrollPane16.setViewportView(tbDeGiay);

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel41Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane16)
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel41Layout.createSequentialGroup()
                                    .addComponent(lblMaSp35)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtIDDeGiay, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel41Layout.createSequentialGroup()
                                    .addComponent(lblMaSp36)
                                    .addGap(21, 21, 21)
                                    .addComponent(txtMaDe, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel41Layout.createSequentialGroup()
                                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMaSp37)
                                    .addComponent(lblMaSp38))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMoTaDe, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtLoaiDe, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                        .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnXoa7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSua7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThem7, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))))
                .addGap(18, 18, 18))
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMaSp35)
                            .addComponent(txtIDDeGiay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThem7))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMaSp36)
                            .addComponent(txtMaDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMaSp37)
                            .addComponent(txtLoaiDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addComponent(btnSua7)
                        .addGap(33, 33, 33)
                        .addComponent(btnXoa7)))
                .addGap(31, 31, 31)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaSp38)
                    .addComponent(txtMoTaDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(559, Short.MAX_VALUE))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(223, Short.MAX_VALUE))
        );

        pnlCacGiaoDien.add(jPanel40, "De");

        jLabel12.setText("Ngày Đặt");

        jLabel14.setText("Ngày SHIP");

        jLabel15.setText("Ngày Nhận");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel14)
                        .addComponent(jLabel15))
                    .addComponent(jLabel12))
                .addGap(21, 21, 21)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jDateNgayDat, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                    .addComponent(jDateNgaySHIP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateNgayNhan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(1042, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateNgayDat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateNgaySHIP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateNgayNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(633, Short.MAX_VALUE))
        );

        pnlCacGiaoDien.add(jPanel18, "cardThoiGian");
        jPanel18.getAccessibleContext().setAccessibleName("");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel7.setText("ID");

        jLabel8.setText("Mã");

        jLabel10.setText("Tên");

        tbChucVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Mã", "Tên"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane19.setViewportView(tbChucVu);

        btnsudung.setBackground(new java.awt.Color(204, 204, 204));
        btnsudung.setText("Xoá");
        btnsudung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsudungActionPerformed(evt);
            }
        });

        btnsudung1.setBackground(new java.awt.Color(204, 204, 204));
        btnsudung1.setText("Sửa");
        btnsudung1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsudung1ActionPerformed(evt);
            }
        });

        btnsudung2.setBackground(new java.awt.Color(204, 204, 204));
        btnsudung2.setText("Thêm");
        btnsudung2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsudung2ActionPerformed(evt);
            }
        });

        jLabel11.setText("Chức Vụ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(56, 56, 56)
                                    .addComponent(txtTenChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnsudung, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(56, 56, 56)
                                    .addComponent(txtMaChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnsudung1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(56, 56, 56)
                                    .addComponent(txtIDChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(52, 52, 52)
                                    .addComponent(btnsudung2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(189, 189, 189)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(796, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtIDChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsudung2))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtMaChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(btnsudung1)))
                .addGap(46, 46, 46)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtTenChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsudung))
                .addGap(79, 79, 79)
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(240, Short.MAX_VALUE))
        );

        pnlCacGiaoDien.add(jPanel2, "cardChucVu");

        pnlTong.add(pnlCacGiaoDien, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlTong, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBanHang2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanHang2ActionPerformed
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) pnlCacGiaoDien.getLayout();
        layout.show(pnlCacGiaoDien, "cardBanHang");
    }//GEN-LAST:event_btnBanHang2ActionPerformed

    private void btnHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoaDonActionPerformed
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) pnlCacGiaoDien.getLayout();
        layout.show(pnlCacGiaoDien, "cardHoaDon");
    }//GEN-LAST:event_btnHoaDonActionPerformed

    private void btnSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanPhamActionPerformed
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) pnlCacGiaoDien.getLayout();
        layout.show(pnlCacGiaoDien, "cardSanPham");
    }//GEN-LAST:event_btnSanPhamActionPerformed

    private void btnNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanVienActionPerformed
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) pnlCacGiaoDien.getLayout();
        layout.show(pnlCacGiaoDien, "cardNhanVien");
    }//GEN-LAST:event_btnNhanVienActionPerformed

    private void btnKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhachHangActionPerformed
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) pnlCacGiaoDien.getLayout();
        layout.show(pnlCacGiaoDien, "cardKhachHang");
        listKH = serviceKH.getAll();
        showDataKH(listKH);
    }//GEN-LAST:event_btnKhachHangActionPerformed

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) pnlCacGiaoDien.getLayout();
        layout.show(pnlCacGiaoDien, "cardThongKe");
    }//GEN-LAST:event_btnThongKeActionPerformed

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        // TODO add your handling code here:
        int check = JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất không", "Đăng xuất", JOptionPane.YES_NO_OPTION);
        if (check != JOptionPane.YES_OPTION) {
            return;
        }
        giaoDienDN gd = new giaoDienDN();
        gd.setVisible(true);
        this.dispose();
        JOptionPane.showMessageDialog(this, "Bạn đã đăng xuất");
    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnThoatActionPerformed

    private void tblgiohangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblgiohangMouseClicked
        // TODO add your handling code here:
//        int ro = tblgiohang.getSelectedRow();
//        txtThanhTien1.setText(tblgiohang.getValueAt(ro, 4).toString());
    }//GEN-LAST:event_tblgiohangMouseClicked

    private void btnlammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlammoiActionPerformed
        // TODO add your handling code here:
        loadTableChiTietSPBH();
    }//GEN-LAST:event_btnlammoiActionPerformed

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        // TODO add your handling code here:
        int row = tbldssanpham.getSelectedRow();
        int ro = tblgiohang.getSelectedRow();
        int r = tbHoaDonBanHang.getSelectedRow();
        HoaDonChiTietViewModel chiTietHoaDonViewModel = new HoaDonChiTietViewModel();
        ChiTietSP CT = new ChiTietSP();
        String sl = JOptionPane.showInputDialog("Mời nhập số lượng");
        int slm = Integer.valueOf(sl);
        CT.setSoLuong(Integer.parseInt(tbldssanpham.getValueAt(row, 3).toString()));
        CT.setSoLuong(CT.getSoLuong() - slm);
        chiTietHoaDonViewModel.setMaSP((String) tbldssanpham.getValueAt(row, 0));
        chiTietHoaDonViewModel.setTenSP((String) tbldssanpham.getValueAt(row, 1));
        chiTietHoaDonViewModel.setSoLuong(Integer.valueOf(sl));
        chiTietHoaDonViewModel.setDonGia((BigDecimal) tbldssanpham.getValueAt(row, 2));
        HoaDonChiTiet hd = new HoaDonChiTiet();
        hd.setSoLuong(Integer.valueOf(sl));
        hd.setDonGia((BigDecimal) tbldssanpham.getValueAt(row, 2));
        listhdct.add(chiTietHoaDonViewModel);
        listhdctt.add(hd);

        try {
            chiTietSPService.updatesl(CT, tbldssanpham.getValueAt(row, 0).toString());
            addTableGioHang(listhdct);
            System.out.println("abcabc");
            loadTableChiTietSPBH();
            HDCT.add(tbldssanpham.getValueAt(row, 0).toString(), tbHoaDonBanHang.getValueAt(r, 0).toString(), hd);
            if (tblgiohang.getRowCount() > 0) {
                for (int i = 0; i < listhdct.size(); i++) {

                }
                txtThanhTien1.setText(tblgiohang.getValueAt(row, 4).toString());
            }
        } catch (Exception ex) {
            Logger.getLogger(QuanLyView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnthemActionPerformed
    void clearQLSP() {
        txttMaSPP.setText("");
        txtDonGia.setText("");
        txtTrongLuong.setText("");
        txtSoLuongSP.setText("");
        txtMotaChiTietSP.setText("");
        cbCL.setSelectedIndex(0);
        cbDe.setSelectedIndex(0);
        cbHang.setSelectedIndex(0);
        cbLoai.setSelectedIndex(0);
        cbSIZE.setSelectedIndex(0);
        cbTenSP.setSelectedIndex(0);
    }
    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jTextField12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField12ActionPerformed

    private void txtDCNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDCNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDCNVActionPerformed

    private void txtIDNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDNhanVienActionPerformed

    private void txtMKNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMKNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMKNVActionPerformed

    private void txtTKNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTKNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTKNVActionPerformed

    private void txtHoTenNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoTenNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoTenNVActionPerformed

    private void rdNamNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNamNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNamNVActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jTextField19jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField19jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField19jTextField8ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void txtSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearch1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearch1ActionPerformed

    private void rdNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNamActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        KhachHang kh = new KhachHang();
        kh.setDiaChi(txtDC.getText());
        if (rdNam.isSelected()) {
            kh.setGioiTinh("Nam");
        } else {
            kh.setGioiTinh("Nữ");
        }
        kh.setHoTen(txtTenKH.getText());
        kh.setMa(txtMaKH.getText());
        kh.setNgaySinh(txtNgaySinh.getDate());
        kh.setSdt(txtSDT.getText());
        try {
            serviceKH.create(kh);
        } catch (Exception ex) {
            Logger.getLogger(QuanLyView.class.getName()).log(Level.SEVERE, null, ex);
        }
        listKH = serviceKH.getAll();
        showDataKH(listKH);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        KhachHang kh = new KhachHang();
        kh.setID(UUID.fromString(txtIdKH.getText()));
        kh.setMa(txtMaKH.getText());
        kh.setDiaChi(txtDC.getText());
        if (rdNam.isSelected()) {
            kh.setGioiTinh("Nam");
        } else {
            kh.setGioiTinh("Nữ");
        }
        kh.setHoTen(txtTenKH.getText());
        kh.setNgaySinh(txtNgaySinh.getDate());
        kh.setSdt(txtSDT.getText());
        try {
            serviceKH.delete(kh);
        } catch (Exception e) {
            e.printStackTrace();
        }
        listKH = serviceKH.getAll();
        showDataKH(listKH);
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int row = this.tblKH.getSelectedRow();
        KhachHang kh = listKH.get(row);
        kh.setDiaChi(txtDC.getText());
        if (rdNam.isSelected()) {
            kh.setGioiTinh("Nam");
        } else {
            kh.setGioiTinh("Nữ");
        }
        kh.setHoTen(txtTenKH.getText());
        kh.setMa(txtMaKH.getText());
        kh.setNgaySinh(txtNgaySinh.getDate());
        kh.setSdt(txtSDT.getText());
        try {
            serviceKH.update(kh);
        } catch (Exception ex) {
            Logger.getLogger(QuanLyView.class.getName()).log(Level.SEVERE, null, ex);
        }
        listKH = serviceKH.getAll();
        showDataKH(listKH);
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tblKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKHMouseClicked
        int row = this.tblKH.getSelectedRow();
        fillDataKH(row);
    }//GEN-LAST:event_tblKHMouseClicked

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        //        String ten = this.txtSearch.getText();
        //        listKH = serviceKH.search(ten);
        //        showDataKH(listKH);
        //        if (listKH.isEmpty()) {
        //            JOptionPane.showMessageDialog(this, "khong tim thấy tên cần");
        //        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtDonGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDonGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDonGiaActionPerformed

    private void rdoConHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoConHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoConHangActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (txttMaSPP.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập Mã");
            return;
        }
        if (txtDonGia.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đơn giá");
            return;
        }
        if (Double.parseDouble(txtDonGia.getText()) <= 0) {
            JOptionPane.showMessageDialog(this, "Đơn giá phải lớn hơn 0");
            return;
        }
        if (txtTrongLuong.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập trọng lượng");
            return;
        }
        if (Integer.parseInt(txtTrongLuong.getText()) <= 0) {
            JOptionPane.showMessageDialog(this, "Trọng lượng phải lớn hơn 0");
            return;
        }
        if (txtSoLuongSP.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng");
            return;
        }
        if (Integer.parseInt(txtSoLuongSP.getText()) < 0) {
            JOptionPane.showMessageDialog(this, "Số lượng lượng không được âm");
            return;
        }
        if (txtMotaChiTietSP.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mô tả");
            return;
        }
        int bb = JOptionPane.showConfirmDialog(this, "Bạn muốn thêm không ?", "Có", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
        if (bb == JOptionPane.YES_OPTION) {
            ChiTietSP ctsp = new ChiTietSP();
            ctsp.setSanPham((SanPham) cbTenSP.getSelectedItem());
            ctsp.setChatlieu((ChatLieu) cbCL.getSelectedItem());
            ctsp.setHangGiay((HangGiay) cbHang.getSelectedItem());
            ctsp.setSize((Size) cbSIZE.getSelectedItem());
            ctsp.setLoaigiay((LoaiGiay) cbLoai.getSelectedItem());
            ctsp.setDeGiay((DeGiay) cbDe.getSelectedItem());
            ctsp.setDonGia(BigDecimal.valueOf(Double.parseDouble(txtDonGia.getText())));
            ctsp.setTrongLuong(Integer.parseInt(txtTrongLuong.getText()));
            ctsp.setSoLuong(Integer.parseInt(txtSoLuongSP.getText()));
            if (rdNam.isSelected()) {
                ctsp.setTrangThai(1);
            } else if (rdNu.isSelected()) {
                ctsp.setTrangThai(2);
            }
            ctsp.setMoTa(txtMotaChiTietSP.getText());
            try {
                chiTietSPService.add(ctsp);
            } catch (Exception ex) {
                Logger.getLogger(QuanLyView.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(this, "Thêm thành công");
        } else if (bb == JOptionPane.NO_OPTION) {
            return;
        } else {
            return;
        }

        loadTableChiTietSP();
        clearQLSP();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int row = tblQLSP.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn vào trong bảng");
            return;
        }
        int bb = JOptionPane.showConfirmDialog(this, "Thông báo", "Xoá", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
        if (bb == JOptionPane.YES_OPTION) {
            ChiTietSP ctsp = new ChiTietSP();
            ctsp.setIdCTSP(UUID.fromString(txtID.getText()));
            ctsp.setSanPham((SanPham) cbTenSP.getSelectedItem());
            ctsp.setChatlieu((ChatLieu) cbCL.getSelectedItem());
            ctsp.setHangGiay((HangGiay) cbHang.getSelectedItem());
            ctsp.setSize((Size) cbSIZE.getSelectedItem());
            ctsp.setLoaigiay((LoaiGiay) cbLoai.getSelectedItem());
            ctsp.setDeGiay((DeGiay) cbDe.getSelectedItem());
            ctsp.setDonGia(BigDecimal.valueOf(Double.parseDouble(txtDonGia.getText())));
            ctsp.setTrongLuong(Integer.parseInt(txtTrongLuong.getText()));
            ctsp.setSoLuong(Integer.parseInt(txtSoLuongSP.getText()));
            if (rdNam.isSelected()) {
                ctsp.setTrangThai(0);
            } else if (rdNu.isSelected()) {
                ctsp.setTrangThai(1);
            }
            ctsp.setMoTa(txtMotaChiTietSP.getText());
            try {
                chiTietSPService.delete(ctsp);
            } catch (Exception ex) {
                Logger.getLogger(QuanLyView.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(this, "Xoá thành công");
        } else if (bb == JOptionPane.NO_OPTION) {
            return;
        } else {
            return;
        }

        loadTableChiTietSP();
        clearQLSP();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if (txttMaSPP.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập Mã");
            return;
        }
        if (txtDonGia.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đơn giá");
            return;
        }
        if (Double.parseDouble(txtDonGia.getText()) <= 0) {
            JOptionPane.showMessageDialog(this, "Đơn giá phải lớn hơn 0");
            return;
        }
        if (txtTrongLuong.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập trọng lượng");
            return;
        }
        if (Integer.parseInt(txtTrongLuong.getText()) <= 0) {
            JOptionPane.showMessageDialog(this, "Trọng lượng phải lớn hơn 0");
            return;
        }
        if (txtSoLuongSP.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng");
            return;
        }
        if (Integer.parseInt(txtSoLuongSP.getText()) < 0) {
            JOptionPane.showMessageDialog(this, "Số lượng lượng không được âm");
            return;
        }
        if (txtMotaChiTietSP.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mô tả");
            return;
        }
        int bb = JOptionPane.showConfirmDialog(this, "Bạn muốn sửa lại không ?", "Có", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
        if (bb == JOptionPane.YES_OPTION) {
            ChiTietSP ctsp = new ChiTietSP();
            ctsp.setIdCTSP(UUID.fromString(txtID.getText()));
            ctsp.setSanPham((SanPham) cbTenSP.getSelectedItem());
            ctsp.setChatlieu((ChatLieu) cbCL.getSelectedItem());
            ctsp.setHangGiay((HangGiay) cbHang.getSelectedItem());
            ctsp.setSize((Size) cbSIZE.getSelectedItem());
            ctsp.setLoaigiay((LoaiGiay) cbLoai.getSelectedItem());
            ctsp.setDeGiay((DeGiay) cbDe.getSelectedItem());
            ctsp.setDonGia(BigDecimal.valueOf(Double.parseDouble(txtDonGia.getText())));
            ctsp.setTrongLuong(Integer.parseInt(txtTrongLuong.getText()));
            ctsp.setSoLuong(Integer.parseInt(txtSoLuongSP.getText()));
            if (rdNam.isSelected()) {
                ctsp.setTrangThai(0);
            } else if (rdNu.isSelected()) {
                ctsp.setTrangThai(1);
            }
            ctsp.setMoTa(txtMotaChiTietSP.getText());
            try {
                chiTietSPService.update(ctsp);
            } catch (Exception ex) {
                Logger.getLogger(QuanLyView.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(this, "Sửa thành công");
        } else if (bb == JOptionPane.NO_OPTION) {
            return;
        } else {
            return;
        }

        loadTableChiTietSP();
        clearQLSP();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void cbTenSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTenSPActionPerformed
        // TODO add your handling code here:
        List<SanPham> sp = SanPhamService.all();
        for (int j = 0; j < sp.size(); j++) {
            if (sp.get(j).getTenSP().equalsIgnoreCase(cbTenSP.getSelectedItem().toString())) {
                txttMaSPP.setText(sp.get(j).getMaSP());
            }
        }
    }//GEN-LAST:event_cbTenSPActionPerformed

    private void txtTrongLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTrongLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTrongLuongActionPerformed

    private void cbDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbDeActionPerformed

    private void txtSoLuongSPCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSoLuongSPCaretUpdate
        // TODO add your handling code here:

        if (Integer.parseInt(txtSoLuongSP.getText()) == 0) {
            rdoHetHang.setSelected(true);
        } else {
            rdoConHang.setSelected(true);
        }
    }//GEN-LAST:event_txtSoLuongSPCaretUpdate

    private void txttMaSPPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttMaSPPActionPerformed
        // TODO add your handling code here:
        List<SanPham> sp = SanPhamService.all();
        for (int j = 0; j < sp.size(); j++) {
            if (sp.get(j).getTenSP().equalsIgnoreCase(cbTenSP.getSelectedItem().toString())) {
                txttMaSPP.setText(sp.get(j).getMaSP());
            }
        }
    }//GEN-LAST:event_txttMaSPPActionPerformed

    private void updatespActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatespActionPerformed
        CardLayout layout = (CardLayout) pnlCacGiaoDien.getLayout();
        layout.show(pnlCacGiaoDien, "SanPham");
    }//GEN-LAST:event_updatespActionPerformed

    private void updatesp2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatesp2ActionPerformed
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) pnlCacGiaoDien.getLayout();
        layout.show(pnlCacGiaoDien, "ChatLieu");
    }//GEN-LAST:event_updatesp2ActionPerformed

    private void updatesp3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatesp3ActionPerformed
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) pnlCacGiaoDien.getLayout();
        layout.show(pnlCacGiaoDien, "Size");
    }//GEN-LAST:event_updatesp3ActionPerformed

    private void updatesp4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatesp4ActionPerformed
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) pnlCacGiaoDien.getLayout();
        layout.show(pnlCacGiaoDien, "Hang");
    }//GEN-LAST:event_updatesp4ActionPerformed

    private void updatesp5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatesp5ActionPerformed
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) pnlCacGiaoDien.getLayout();
        layout.show(pnlCacGiaoDien, "LoaiSanPham");
    }//GEN-LAST:event_updatesp5ActionPerformed

    private void updatesp6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatesp6ActionPerformed
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) pnlCacGiaoDien.getLayout();
        layout.show(pnlCacGiaoDien, "De");
    }//GEN-LAST:event_updatesp6ActionPerformed

    private void txtDonGia1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDonGia1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDonGia1ActionPerformed

    private void rdoConHang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoConHang1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoConHang1ActionPerformed

    private void btnThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem1ActionPerformed
        // TODO add your handling code here:
        if (txttMaSPP.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập Mã");
            return;
        }
        if (txtDonGia.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đơn giá");
            return;
        }
        if (Double.parseDouble(txtDonGia.getText()) <= 0) {
            JOptionPane.showMessageDialog(this, "Đơn giá phải lớn hơn 0");
            return;
        }
        if (txtTrongLuong.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập trọng lượng");
            return;
        }
        if (Integer.parseInt(txtTrongLuong.getText()) <= 0) {
            JOptionPane.showMessageDialog(this, "Trọng lượng phải lớn hơn 0");
            return;
        }
        if (txtSoLuongSP.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng");
            return;
        }
        if (Integer.parseInt(txtSoLuongSP.getText()) < 0) {
            JOptionPane.showMessageDialog(this, "Số lượng lượng không được âm");
            return;
        }
        if (txtMotaChiTietSP.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mô tả");
            return;
        }
        int bb = JOptionPane.showConfirmDialog(this, "Bạn muốn thêm không ?", "Có", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
        if (bb == JOptionPane.YES_OPTION) {
            ChiTietSP ctsp = new ChiTietSP();
            ctsp.setSanPham((SanPham) cbTenSP.getSelectedItem());
            ctsp.setChatlieu((ChatLieu) cbCL.getSelectedItem());
            ctsp.setHangGiay((HangGiay) cbHang.getSelectedItem());
            ctsp.setSize((Size) cbSIZE.getSelectedItem());
            ctsp.setLoaigiay((LoaiGiay) cbLoai.getSelectedItem());
            ctsp.setDeGiay((DeGiay) cbDe.getSelectedItem());
            ctsp.setDonGia(BigDecimal.valueOf(Double.parseDouble(txtDonGia.getText())));
            ctsp.setTrongLuong(Integer.parseInt(txtTrongLuong.getText()));
            ctsp.setSoLuong(Integer.parseInt(txtSoLuongSP.getText()));
            if (rdNam.isSelected()) {
                ctsp.setTrangThai(1);
            } else if (rdNu.isSelected()) {
                ctsp.setTrangThai(2);
            }
            ctsp.setMoTa(txtMotaChiTietSP.getText());
            try {
                chiTietSPService.add(ctsp);
            } catch (Exception ex) {
                Logger.getLogger(QuanLyView.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(this, "Thêm thành công");
        } else if (bb == JOptionPane.NO_OPTION) {
            return;
        } else {
            return;
        }

        loadTableChiTietSP();
        clearQLSP();
    }//GEN-LAST:event_btnThem1ActionPerformed

    private void btnXoa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa1ActionPerformed
        // TODO add your handling code here:
        int row = tblQLSP.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn vào trong bảng");
            return;
        }
        int bb = JOptionPane.showConfirmDialog(this, "Thông báo", "Xoá", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
        if (bb == JOptionPane.YES_OPTION) {
            ChiTietSP ctsp = new ChiTietSP();
            ctsp.setIdCTSP(UUID.fromString(txtID.getText()));
            ctsp.setSanPham((SanPham) cbTenSP.getSelectedItem());
            ctsp.setChatlieu((ChatLieu) cbCL.getSelectedItem());
            ctsp.setHangGiay((HangGiay) cbHang.getSelectedItem());
            ctsp.setSize((Size) cbSIZE.getSelectedItem());
            ctsp.setLoaigiay((LoaiGiay) cbLoai.getSelectedItem());
            ctsp.setDeGiay((DeGiay) cbDe.getSelectedItem());
            ctsp.setDonGia(BigDecimal.valueOf(Double.parseDouble(txtDonGia.getText())));
            ctsp.setTrongLuong(Integer.parseInt(txtTrongLuong.getText()));
            ctsp.setSoLuong(Integer.parseInt(txtSoLuongSP.getText()));
            if (rdNam.isSelected()) {
                ctsp.setTrangThai(0);
            } else if (rdNu.isSelected()) {
                ctsp.setTrangThai(1);
            }
            ctsp.setMoTa(txtMotaChiTietSP.getText());
            try {
                chiTietSPService.delete(ctsp);
            } catch (Exception ex) {
                Logger.getLogger(QuanLyView.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(this, "Xoá thành công");
        } else if (bb == JOptionPane.NO_OPTION) {
            return;
        } else {
            return;
        }

        loadTableChiTietSP();
        clearQLSP();
    }//GEN-LAST:event_btnXoa1ActionPerformed

    private void btnSua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua1ActionPerformed
        // TODO add your handling code here:
        if (txttMaSPP.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập Mã");
            return;
        }
        if (txtDonGia.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đơn giá");
            return;
        }
        if (Double.parseDouble(txtDonGia.getText()) <= 0) {
            JOptionPane.showMessageDialog(this, "Đơn giá phải lớn hơn 0");
            return;
        }
        if (txtTrongLuong.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập trọng lượng");
            return;
        }
        if (Integer.parseInt(txtTrongLuong.getText()) <= 0) {
            JOptionPane.showMessageDialog(this, "Trọng lượng phải lớn hơn 0");
            return;
        }
        if (txtSoLuongSP.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng");
            return;
        }
        if (Integer.parseInt(txtSoLuongSP.getText()) < 0) {
            JOptionPane.showMessageDialog(this, "Số lượng lượng không được âm");
            return;
        }
        if (txtMotaChiTietSP.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mô tả");
            return;
        }
        int bb = JOptionPane.showConfirmDialog(this, "Bạn muốn sửa lại không ?", "Có", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
        if (bb == JOptionPane.YES_OPTION) {
            ChiTietSP ctsp = new ChiTietSP();
            ctsp.setIdCTSP(UUID.fromString(txtID.getText()));
            ctsp.setSanPham((SanPham) cbTenSP.getSelectedItem());
            ctsp.setChatlieu((ChatLieu) cbCL.getSelectedItem());
            ctsp.setHangGiay((HangGiay) cbHang.getSelectedItem());
            ctsp.setSize((Size) cbSIZE.getSelectedItem());
            ctsp.setLoaigiay((LoaiGiay) cbLoai.getSelectedItem());
            ctsp.setDeGiay((DeGiay) cbDe.getSelectedItem());
            ctsp.setDonGia(BigDecimal.valueOf(Double.parseDouble(txtDonGia.getText())));
            ctsp.setTrongLuong(Integer.parseInt(txtTrongLuong.getText()));
            ctsp.setSoLuong(Integer.parseInt(txtSoLuongSP.getText()));
            if (rdNam.isSelected()) {
                ctsp.setTrangThai(0);
            } else if (rdNu.isSelected()) {
                ctsp.setTrangThai(1);
            }
            ctsp.setMoTa(txtMotaChiTietSP.getText());
            try {
                chiTietSPService.update(ctsp);
            } catch (Exception ex) {
                Logger.getLogger(QuanLyView.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(this, "Sửa thành công");
        } else if (bb == JOptionPane.NO_OPTION) {
            return;
        } else {
            return;
        }

        loadTableChiTietSP();
        clearQLSP();
    }//GEN-LAST:event_btnSua1ActionPerformed

    private void cbTenSP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTenSP1ActionPerformed
        // TODO add your handling code here:
        List<SanPham> sp = SanPhamService.all();
        for (int j = 0; j < sp.size(); j++) {
            if (sp.get(j).getTenSP().equalsIgnoreCase(cbTenSP.getSelectedItem().toString())) {
                txttMaSPP.setText(sp.get(j).getMaSP());
            }
        }
    }//GEN-LAST:event_cbTenSP1ActionPerformed

    private void txtTrongLuong1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTrongLuong1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTrongLuong1ActionPerformed

    private void cbDe1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDe1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbDe1ActionPerformed

    private void txtSoLuongSP1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSoLuongSP1CaretUpdate
        // TODO add your handling code here:

        if (Integer.parseInt(txtSoLuongSP.getText()) == 0) {
            rdoHetHang.setSelected(true);
        } else {
            rdoConHang.setSelected(true);
        }
    }//GEN-LAST:event_txtSoLuongSP1CaretUpdate

    private void txttMaSPP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttMaSPP1ActionPerformed
        // TODO add your handling code here:
        List<SanPham> sp = SanPhamService.all();
        for (int j = 0; j < sp.size(); j++) {
            if (sp.get(j).getTenSP().equalsIgnoreCase(cbTenSP.getSelectedItem().toString())) {
                txttMaSPP.setText(sp.get(j).getMaSP());
            }
        }
    }//GEN-LAST:event_txttMaSPP1ActionPerformed

    private void updatesp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatesp1ActionPerformed
        // TODO add your handling code here:
        //        jPanel33.setVisible(true);
    }//GEN-LAST:event_updatesp1ActionPerformed

    private void updatesp7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatesp7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updatesp7ActionPerformed

    private void updatesp8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatesp8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updatesp8ActionPerformed

    private void updatesp9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatesp9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updatesp9ActionPerformed

    private void updatesp10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatesp10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updatesp10ActionPerformed

    private void updatesp11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatesp11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updatesp11ActionPerformed

    private void txtIDspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDspActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDspActionPerformed

    private void txtMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSPActionPerformed

    private void txtTenSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSPActionPerformed

    private void txtMoTaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMoTaSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMoTaSPActionPerformed

    private void btnThem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem2ActionPerformed

        // TODO add your handling code here:
        if (txtMaSP.getText().equals("") || txtTenSP.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Ban Chưa nhập đủ dữ liệu");
            return;
        }
        int xacnhan = JOptionPane.showConfirmDialog(this, "Bạn muốn thêm hay không?", "Có", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
        if (xacnhan == JOptionPane.YES_OPTION) {
            SanPham sp = new SanPham();
            sp.setMaSP(txtMaSP.getText());
            sp.setTenSP(txtTenSP.getText());
            sp.setMoTa(txtMoTaSP.getText());
            try {
                SanPhamService.add(sp);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công");
        } else if (xacnhan == JOptionPane.NO_OPTION) {
            return;
        } else {
            return;
        }

        loadTableSanPham();
        //        loadCBMaSP();
        //        loadCBTenSP();
    }//GEN-LAST:event_btnThem2ActionPerformed

    private void btnSua2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua2ActionPerformed
        // TODO add your handling code here:
        int row = tbSP.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn vào trong bảng");
            return;
        }
        if (txtMaSP.getText().equals("") || txtTenSP.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Ban Chưa nhập đủ dữ liệu");
            return;
        }
        int bb = JOptionPane.showConfirmDialog(this, "Thông báo", "Sửa", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
        if (bb == JOptionPane.YES_OPTION) {
            SanPham sp = new SanPham();
            sp.setIdSP(UUID.fromString(txtIDsp.getText()));
            sp.setMaSP(txtMaSP.getText());
            sp.setTenSP(txtTenSP.getText());
            sp.setMoTa(txtMoTaSP.getText());
            try {
                SanPhamService.update(sp);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Sửa thành công");
        } else if (bb == JOptionPane.NO_OPTION) {
            return;

        }

        loadTableSanPham();
        //        loadCBMaSP();
        //        loadCBTenSP();
    }//GEN-LAST:event_btnSua2ActionPerformed

    private void btnXoa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa2ActionPerformed
        // TODO add your handling code here:
        int row = tbSP.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn vào trong bảng");
            return;
        }
        int bb = JOptionPane.showConfirmDialog(this, "Thông báo", "Xoá", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
        if (bb == JOptionPane.YES_OPTION) {
            SanPham sp = new SanPham();
            sp.setIdSP(UUID.fromString(txtIDsp.getText()));
            sp.setMaSP(txtMaSP.getText());
            sp.setTenSP(txtTenSP.getText());
            sp.setMoTa(txtMoTaSP.getText());
            try {
                SanPhamService.delete(sp);
            } catch (Exception e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Xóa thành công");
        } else if (bb == JOptionPane.NO_OPTION) {
            return;

        }
        loadTableSanPham();
        //        loadCBMaSP();
        //        loadCBTenSP();
    }//GEN-LAST:event_btnXoa2ActionPerformed

    private void tbSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSPMouseClicked
        // TODO add your handling code here:
        int row = tbSP.getSelectedRow();
        txtIDsp.setText(tbSP.getValueAt(row, 0).toString());
        txtMaSP.setText(tbSP.getValueAt(row, 1).toString());
        txtTenSP.setText(tbSP.getValueAt(row, 2).toString());
        txtMoTaSP.setText(tbSP.getValueAt(row, 3).toString());
    }//GEN-LAST:event_tbSPMouseClicked

    private void txtIDChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDChatLieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDChatLieuActionPerformed

    private void txtMaChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaChatLieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaChatLieuActionPerformed

    private void txtTenChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenChatLieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenChatLieuActionPerformed

    private void txtMoTaCLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMoTaCLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMoTaCLActionPerformed

    private void btnThem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem3ActionPerformed
        // TODO add your handling code here:
        if (txtMaChatLieu.getText().equals("") || txtTenChatLieu.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Ban Chưa nhập đủ dữ liệu");
            return;
        }

        int xacnhan = JOptionPane.showConfirmDialog(this, "Bạn muốn thêm hay không?", "Có", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
        if (xacnhan == JOptionPane.YES_OPTION) {
            ChatLieu cl = new ChatLieu();
            cl.setMaCL(txtMaChatLieu.getText());
            cl.setTenCL(txtTenChatLieu.getText());
            cl.setMoTaCl(txtMoTaCL.getText());

            try {
                chatLieuService.add(cl);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công");
        } else if (xacnhan == JOptionPane.NO_OPTION) {
            return;
        } else {
            return;
        }
        loadTableChatLieu();
        //        loadCBChatLieu();
    }//GEN-LAST:event_btnThem3ActionPerformed

    private void btnSua3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua3ActionPerformed
        // TODO add your handling code here:
        int row = tbChatLieu.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn vào trong bảng");
            return;
        }
        if (txtMaChatLieu.getText().equals("") || txtTenChatLieu.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Ban Chưa nhập đủ dữ liệu");
            return;
        }
        int bb = JOptionPane.showConfirmDialog(this, "Thông báo", "Sửa", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
        if (bb == JOptionPane.YES_OPTION) {
            ChatLieu cl = new ChatLieu();
            cl.setIdCL(UUID.fromString(txtIDChatLieu.getText()));
            cl.setMaCL(txtMaChatLieu.getText());
            cl.setTenCL(txtTenChatLieu.getText());
            cl.setMoTaCl(txtMoTaCL.getText());

            try {
                chatLieuService.update(cl);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Sửa thành công");
        } else if (bb == JOptionPane.NO_OPTION) {
            return;
        }
        loadTableChatLieu();
        //        loadCBChatLieu();
    }//GEN-LAST:event_btnSua3ActionPerformed

    private void btnXoa3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa3ActionPerformed
        // TODO add your handling code here:
        int row = tbChatLieu.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn vào trong bảng");
            return;
        }

        int bb = JOptionPane.showConfirmDialog(this, "Thông báo bạn chắc chắn xóa", "Xóa", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
        if (bb == JOptionPane.YES_OPTION) {
            ChatLieu cl = new ChatLieu();
            cl.setIdCL(UUID.fromString(txtIDChatLieu.getText()));
            cl.setMaCL(txtMaChatLieu.getText());
            cl.setTenCL(txtTenChatLieu.getText());
            cl.setMoTaCl(txtMoTaCL.getText());

            try {
                chatLieuService.delete(cl);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Xóa thành công");
        } else if (bb == JOptionPane.NO_OPTION) {
            return;
        }
        loadTableChatLieu();
    }//GEN-LAST:event_btnXoa3ActionPerformed

    private void tbChatLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbChatLieuMouseClicked
        // TODO add your handling code here:
        int row = tbChatLieu.getSelectedRow();
        txtIDChatLieu.setText(tbChatLieu.getValueAt(row, 0).toString());
        txtMaChatLieu.setText(tbChatLieu.getValueAt(row, 1).toString());
        txtTenChatLieu.setText(tbChatLieu.getValueAt(row, 2).toString());
        txtMoTaCL.setText(tbChatLieu.getValueAt(row, 3).toString());
    }//GEN-LAST:event_tbChatLieuMouseClicked

    private void txtIDHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDHangActionPerformed

    private void txtMaHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaHangActionPerformed

    private void txtTenHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenHangActionPerformed

    private void txtMoTaHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMoTaHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMoTaHangActionPerformed

    private void btnThem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem4ActionPerformed
        // TODO add your handling code here:
        if (txtMaHang.getText().equals("") || txtTenHang.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Ban Chưa nhập đủ dữ liệu");
            return;

        } else {
            int xacnhan = JOptionPane.showConfirmDialog(this, "Bạn muốn thêm hay không?", "Có", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
            if (xacnhan == JOptionPane.YES_OPTION) {
                HangGiay hangSP = new HangGiay();

                hangSP.setMaHang(txtMaHang.getText());
                hangSP.setTenHang(txtTenHang.getText());
                hangSP.setMoTaHang(txtMoTaHang.getText());
                if (hangSP == null) {
                    return;
                }
                try {
                    hangGiayService.add(hangSP);
                    JOptionPane.showMessageDialog(this, "Thêm hãng thành công");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (xacnhan == JOptionPane.NO_OPTION) {
                return;
            } else {
                return;
            }
            loadTableHangGiay();
            //            loadCBHang();
        }
    }//GEN-LAST:event_btnThem4ActionPerformed

    private void btnSua4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua4ActionPerformed

        // TODO add your handling code here:
        int row = tbHang.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn vào trong bảng");
            return;
        }
        if (txtMaHang.getText().equals("") || txtTenHang.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Ban Chưa nhập đủ dữ liệu");
            return;
        }
        int bb = JOptionPane.showConfirmDialog(this, "Thông báo", "Sửa", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
        if (bb == JOptionPane.YES_OPTION) {
            HangGiay hg = new HangGiay();
            hg.setIdHang(UUID.fromString(txtIDHang.getText()));
            hg.setMaHang(txtMaHang.getText());
            hg.setTenHang(txtTenHang.getText());
            hg.setMoTaHang(txtMoTaHang.getText());
            try {
                hangGiayService.update(hg);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Sửa thành công");
        } else if (bb == JOptionPane.NO_OPTION) {
            return;

        }
        loadTableHangGiay();
        //        loadCBHang();
    }//GEN-LAST:event_btnSua4ActionPerformed

    private void btnXoa4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa4ActionPerformed
        // TODO add your handling code here:
        int row = tbHang.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn vào trong bảng");
            return;
        }
        int bb = JOptionPane.showConfirmDialog(this, "Thông báo", "Xóa", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
        if (bb == JOptionPane.YES_OPTION) {
            HangGiay hg = new HangGiay();
            hg.setIdHang(UUID.fromString(txtIDHang.getText()));
            hg.setMaHang(txtMaHang.getText());
            hg.setTenHang(txtTenHang.getText());
            hg.setMoTaHang(txtMoTaHang.getText());
            try {
                hangGiayService.delete(hg);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Xóa thành công");
        } else if (bb == JOptionPane.NO_OPTION) {
            return;
        }
        loadTableHangGiay();
        //        loadCBHang();
    }//GEN-LAST:event_btnXoa4ActionPerformed

    private void tbHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHangMouseClicked
        // TODO add your handling code here:
        int row = tbHang.getSelectedRow();
        txtIDHang.setText(tbHang.getValueAt(row, 0).toString());
        txtMaHang.setText(tbHang.getValueAt(row, 1).toString());
        txtTenHang.setText(tbHang.getValueAt(row, 2).toString());
        txtMoTaHang.setText(tbHang.getValueAt(row, 3).toString());
    }//GEN-LAST:event_tbHangMouseClicked

    private void txtIDLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDLoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDLoaiActionPerformed

    private void txtMaLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaLoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaLoaiActionPerformed

    private void txtTenLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenLoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenLoaiActionPerformed

    private void txtMoTaLoaiGiayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMoTaLoaiGiayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMoTaLoaiGiayActionPerformed

    private void btnThem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem5ActionPerformed
        // TODO add your handling code here:
        if (txtMaLoai.getText().equals("") || txtTenLoai.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa nhập đủ dữ liệu");
            return;
        }
        int xacnhan = JOptionPane.showConfirmDialog(this, "Bạn muốn thêm hay không?", "Có", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
        if (xacnhan == JOptionPane.YES_OPTION) {
            LoaiGiay lg = new LoaiGiay();
            lg.setMaLoai(txtMaLoai.getText());
            lg.setTenLoai(txtTenLoai.getText());
            lg.setMoTa(txtMoTaLoaiGiay.getText());
            try {
                loaiGiayService.add(lg);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Thêm thành công");
        } else if (xacnhan == JOptionPane.NO_OPTION) {
            return;
        } else {
            return;
        }
        loadTableLoaiGiay();
        //        loadCBLoai();
    }//GEN-LAST:event_btnThem5ActionPerformed

    private void btnSua5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua5ActionPerformed

        int row = tbLoaiGiay.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn vào trong bảng");
            return;
        }
        if (txtMaLoai.getText().equals("") || txtTenLoai.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Ban Chưa nhập đủ dữ liệu");
            return;
        }
        // TODO add your handling code here:
        int xacnhan = JOptionPane.showConfirmDialog(this, "Bạn muốn sửa hay không?", "Có", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
        if (xacnhan == JOptionPane.YES_OPTION) {
            LoaiGiay lg = new LoaiGiay();
            lg.setIdLoai(UUID.fromString(txtIDLoai.getText()));
            lg.setMaLoai(txtMaLoai.getText());
            lg.setTenLoai(txtTenLoai.getText());
            lg.setMoTa(txtMoTaLoaiGiay.getText());
            try {
                loaiGiayService.update(lg);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Sửa thành công");
        } else if (xacnhan == JOptionPane.NO_OPTION) {
            return;
        }
        loadTableLoaiGiay();
        //        loadCBLoai();
    }//GEN-LAST:event_btnSua5ActionPerformed

    private void btnXoa5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa5ActionPerformed
        // TODO add your handling code here:

        int row = tbLoaiGiay.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn vào trong bảng");
            return;
        }
        // TODO add your handling code here:
        int xacnhan = JOptionPane.showConfirmDialog(this, "Bạn muốn Xóa hay không?", "Có", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
        if (xacnhan == JOptionPane.YES_OPTION) {
            LoaiGiay lg = new LoaiGiay();
            lg.setIdLoai(UUID.fromString(txtIDLoai.getText()));
            lg.setMaLoai(txtMaLoai.getText());
            lg.setTenLoai(txtTenLoai.getText());
            lg.setMoTa(txtMoTaLoaiGiay.getText());
            try {
                loaiGiayService.delete(lg);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Xóa thành công");
        } else if (xacnhan == JOptionPane.NO_OPTION) {
            return;
        }
        loadTableLoaiGiay();
        //        loadCBLoai();
    }//GEN-LAST:event_btnXoa5ActionPerformed

    private void tbLoaiGiayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbLoaiGiayMouseClicked
        // TODO add your handling code here:
        int row = tbLoaiGiay.getSelectedRow();
        txtIDLoai.setText(tbLoaiGiay.getValueAt(row, 0).toString());
        txtMaLoai.setText(tbLoaiGiay.getValueAt(row, 1).toString());
        txtTenLoai.setText(tbLoaiGiay.getValueAt(row, 2).toString());
        txtMoTaLoaiGiay.setText(tbLoaiGiay.getValueAt(row, 3).toString());
    }//GEN-LAST:event_tbLoaiGiayMouseClicked

    private void txtIDSIZEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDSIZEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDSIZEActionPerformed

    private void txtMaSIZEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSIZEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSIZEActionPerformed

    private void txtSoSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoSizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoSizeActionPerformed

    private void btnThem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem6ActionPerformed
        // TODO add your handling code here:
        if (txtMaSIZE.getText().equals("") || txtSoSize.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa nhập đủ dữ liệu");
            return;
        }
        if (Integer.parseInt(txtSoSize.getText()) <= 30) {
            JOptionPane.showMessageDialog(this, "Số size phải lớn hơn 30");
            return;
        }
        if (Integer.parseInt(txtSoSize.getText()) >= 50) {
            JOptionPane.showMessageDialog(this, "Số size phải nhỏ hơn 50");
            return;
        }

        int xacnhan = JOptionPane.showConfirmDialog(this, "Bạn muốn thêm hay không?", "Có", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
        if (xacnhan == JOptionPane.YES_OPTION) {

            Size s = new Size();
            s.setMaSize(txtMaSIZE.getText());
            s.setSoSize(Integer.parseInt(txtSoSize.getText()));
            try {
                sizeService.add(s);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Thêm thành công");
        } else if (xacnhan == JOptionPane.NO_OPTION) {
            return;
        } else {
            return;
        }

        loadTableSizeGiay();
        //        loadCBSize();
    }//GEN-LAST:event_btnThem6ActionPerformed

    private void btnSua6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua6ActionPerformed
        int row = tbSIZE.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn vào trong bảng");
            return;
        }
        if (txtMaSIZE.getText().equals("") || txtSoSize.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Ban Chưa nhập đủ dữ liệu");
            return;
        }

        if (Integer.parseInt(txtSoSize.getText()) <= 30) {
            JOptionPane.showMessageDialog(this, "Số size phải lớn hơn 30");
            return;
        }
        if (Integer.parseInt(txtSoSize.getText()) >= 50) {
            JOptionPane.showMessageDialog(this, "Số size phải nhỏ hơn 50");
            return;
        }// TODO add your handling code here:
        int xacnhan = JOptionPane.showConfirmDialog(this, "Bạn muốn sửa hay không?", "Có", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
        if (xacnhan == JOptionPane.YES_OPTION) {
            Size s = new Size();
            s.setIdSize(UUID.fromString(txtIDSIZE.getText()));
            s.setMaSize(txtMaSIZE.getText());
            s.setSoSize(Integer.parseInt(txtSoSize.getText()));
            try {
                sizeService.update(s);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Sửa thành công");
        } else if (xacnhan == JOptionPane.NO_OPTION) {
            return;

        }
        loadTableSizeGiay();
        //        loadCBSize();
    }//GEN-LAST:event_btnSua6ActionPerformed

    private void btnXoa6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa6ActionPerformed
        // TODO add your handling code here:
        int row = tbSIZE.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn vào trong bảng");
            return;
        }
        int xacnhan = JOptionPane.showConfirmDialog(this, "Bạn muốn thêm hay không?", "Có", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
        if (xacnhan == JOptionPane.YES_OPTION) {
            Size s = new Size();
            s.setIdSize(UUID.fromString(txtIDSIZE.getText()));
            s.setMaSize(txtMaSIZE.getText());
            s.setSoSize(Integer.parseInt(txtSoSize.getText()));
            try {
                sizeService.delete(s);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Xóa thành công");
        } else if (xacnhan == JOptionPane.NO_OPTION) {
            return;
        }
        loadTableSizeGiay();
        //        loadCBSize();
    }//GEN-LAST:event_btnXoa6ActionPerformed

    private void tbSIZEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSIZEMouseClicked
        // TODO add your handling code here:
        int row = tbSIZE.getSelectedRow();
        txtIDSIZE.setText(tbSIZE.getValueAt(row, 0).toString());
        txtMaSIZE.setText(tbSIZE.getValueAt(row, 1).toString());
        txtSoSize.setText(tbSIZE.getValueAt(row, 2).toString());
    }//GEN-LAST:event_tbSIZEMouseClicked

    private void txtIDDeGiayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDDeGiayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDDeGiayActionPerformed

    private void txtMaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaDeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaDeActionPerformed

    private void txtLoaiDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLoaiDeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLoaiDeActionPerformed

    private void txtMoTaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMoTaDeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMoTaDeActionPerformed

    private void btnThem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem7ActionPerformed
        // TODO add your handling code here:
        if (txtMaDe.getText().equals("") || txtLoaiDe.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa nhập đủ dữ liệu");
            return;
        }
        int xacnhan = JOptionPane.showConfirmDialog(this, "Bạn muốn thêm hay không?", "Có", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
        if (xacnhan == JOptionPane.YES_OPTION) {
            DeGiay dg = new DeGiay();
            dg.setMaCL(txtMaDe.getText());
            dg.setLoaiDe(txtLoaiDe.getText());
            dg.setMoTaDG(txtMoTaDe.getText());
            try {
                deGiayService.add(dg);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Thêm thành công");
        } else if (xacnhan == JOptionPane.NO_OPTION) {
            return;
        } else {
            return;
        }
        loadTableDeGiay();
        //        loadCBDe();
    }//GEN-LAST:event_btnThem7ActionPerformed

    private void btnSua7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua7ActionPerformed
        int row = tbDeGiay.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn vào trong bảng");
            return;
        }
        if (txtMaDe.getText().equals("") || txtLoaiDe.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Ban Chưa nhập đủ dữ liệu");
            return;
        }
        // TODO add your handling code here:
        int xacnhan = JOptionPane.showConfirmDialog(this, "Bạn muốn sửa hay không?", "Có", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
        if (xacnhan == JOptionPane.YES_OPTION) {
            DeGiay dg = new DeGiay();
            dg.setIdDG(UUID.fromString(txtIDDeGiay.getText()));
            dg.setMaCL(txtMaDe.getText());
            dg.setLoaiDe(txtLoaiDe.getText());
            dg.setMoTaDG(txtMoTaDe.getText());
            try {
                deGiayService.update(dg);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "sửa thành công");
        } else if (xacnhan == JOptionPane.NO_OPTION) {
            return;
        }
        loadTableDeGiay();
        //        loadCBDe();
    }//GEN-LAST:event_btnSua7ActionPerformed

    private void btnXoa7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa7ActionPerformed
        // TODO add your handling code here:
        int row = tbDeGiay.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn vào trong bảng");
            return;
        }

        // TODO add your handling code here:
        int xacnhan = JOptionPane.showConfirmDialog(this, "Bạn muốn xóa hay không?", "Có", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
        if (xacnhan == JOptionPane.YES_OPTION) {
            DeGiay dg = new DeGiay();
            dg.setIdDG(UUID.fromString(txtIDDeGiay.getText()));
            dg.setMaCL(txtMaDe.getText());
            dg.setLoaiDe(txtLoaiDe.getText());
            dg.setMoTaDG(txtMoTaDe.getText());
            try {
                deGiayService.delete(dg);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Xóa thành công");
        } else if (xacnhan == JOptionPane.NO_OPTION) {
            return;
        }
        loadTableDeGiay();
        //        loadCBDe();
    }//GEN-LAST:event_btnXoa7ActionPerformed

    private void tbDeGiayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDeGiayMouseClicked
        // TODO add your handling code here:
        int row = tbDeGiay.getSelectedRow();
        txtIDDeGiay.setText(tbDeGiay.getValueAt(row, 0).toString());
        txtMaDe.setText(tbDeGiay.getValueAt(row, 1).toString());
        txtLoaiDe.setText(tbDeGiay.getValueAt(row, 2).toString());
        txtMoTaDe.setText(tbDeGiay.getValueAt(row, 3).toString());
    }//GEN-LAST:event_tbDeGiayMouseClicked

    private void tblQLSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQLSPMouseClicked
        // TODO add your handling code here:
        List<Size> Sz = sizeService.all();
        List<LoaiGiay> lg = loaiGiayService.all();
        List<DeGiay> dg = deGiayService.all();
        List<HangGiay> hang = hangGiayService.all();
        List<ChatLieu> cl = chatLieuService.all();
        List<SanPham> sp = SanPhamService.all();
        int row = tblQLSP.getSelectedRow();
        txtID.setText(tblQLSP.getValueAt(row, 0).toString());
        if (tblQLSP.getValueAt(row, 1) == null) {
            JOptionPane.showMessageDialog(this, "Nhân viên không có chức vụ ");
            cbTenSP.setSelectedIndex(0);
        } else {
            for (int j = 0; j < sp.size(); j++) {
                if (sp.get(j).getMaSP().equalsIgnoreCase(tblQLSP.getValueAt(row, 1).toString())) {
                    cbTenSP.setSelectedIndex(j);
                }
            }
        }
        if (tblQLSP.getValueAt(row, 2) == null) {
            JOptionPane.showMessageDialog(this, "Nhân viên không có chức vụ ");
            cbTenSP.setSelectedIndex(0);
        } else {
            for (int j = 0; j < sp.size(); j++) {
                if (sp.get(j).getTenSP().equalsIgnoreCase(tblQLSP.getValueAt(row, 2).toString())) {
                    cbTenSP.setSelectedIndex(j);
                }
            }
        }
        if (tblQLSP.getValueAt(row, 3) == null) {
            JOptionPane.showMessageDialog(this, "Nhân viên không có chức vụ ");
            cbSIZE.setSelectedIndex(0);
        } else {
            for (int j = 0; j < Sz.size(); j++) {
                if (Sz.get(j).getSoSize() == Integer.parseInt(tblQLSP.getValueAt(row, 3).toString())) {
                    cbSIZE.setSelectedIndex(j);
                }
            }
        }
        if (tblQLSP.getValueAt(row, 4) == null) {
            JOptionPane.showMessageDialog(this, "Nhân viên không có chức vụ ");
            cbLoai.setSelectedIndex(0);
        } else {
            for (int j = 0; j < lg.size(); j++) {
                if (lg.get(j).getTenLoai().equalsIgnoreCase(tblQLSP.getValueAt(row, 4).toString())) {
                    cbLoai.setSelectedIndex(j);
                }
            }
        }
        if (tblQLSP.getValueAt(row, 5) == null) {
            JOptionPane.showMessageDialog(this, "Nhân viên không có chức vụ ");
            cbHang.setSelectedIndex(0);
        } else {
            for (int j = 0; j < hang.size(); j++) {
                if (hang.get(j).getTenHang().equalsIgnoreCase(tblQLSP.getValueAt(row, 5).toString())) {
                    cbHang.setSelectedIndex(j);
                }
            }
        }
        if (tblQLSP.getValueAt(row, 6) == null) {
            JOptionPane.showMessageDialog(this, "Nhân viên không có chức vụ ");
            cbDe.setSelectedIndex(0);
        } else {
            for (int j = 0; j < dg.size(); j++) {
                if (dg.get(j).getLoaiDe().equalsIgnoreCase(tblQLSP.getValueAt(row, 6).toString())) {
                    cbDe.setSelectedIndex(j);
                }
            }
        }
        if (tblQLSP.getValueAt(row, 7) == null) {
            JOptionPane.showMessageDialog(this, "Nhân viên không có chức vụ ");
            cbCL.setSelectedIndex(0);
        } else {
            for (int j = 0; j < cl.size(); j++) {
                if (cl.get(j).getTenCL().equalsIgnoreCase(tblQLSP.getValueAt(row, 7).toString())) {
                    cbCL.setSelectedIndex(j);
                }
            }
        }
        txtDonGia.setText(tblQLSP.getValueAt(row, 9).toString());
        txtTrongLuong.setText(tblQLSP.getValueAt(row, 10).toString());
        txtSoLuongSP.setText(tblQLSP.getValueAt(row, 8).toString());
        if (tblQLSP.getValueAt(row, 11).toString().equals("Còn Hàng")) {
            rdoConHang.setSelected(true);
        } else {
            rdoHetHang.setSelected(true);
        }
        txtMotaChiTietSP.setText(tblQLSP.getValueAt(row, 12).toString());
    }//GEN-LAST:event_tblQLSPMouseClicked

    private void btnNext1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNext1ActionPerformed

    private void btnPrevious1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevious1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPrevious1ActionPerformed

    private void tbldssanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbldssanphamMouseClicked


    }//GEN-LAST:event_tbldssanphamMouseClicked

    private void btntaohdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntaohdActionPerformed
//        HoaDonViewModel hoaDon = new HoaDonViewModel();
//        hoaDon.setNgayTao(new Date());
//        hoadonService.saveHoaDon(hoaDon);
//        lsthd = hoaDonRepository.getListHoaDon();
//        addTableHoaDon(lsthd);
//        System.out.println(hoaDon);
    }//GEN-LAST:event_btntaohdActionPerformed

    private void tbNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNhanVienMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbNhanVienMouseClicked

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        int bb = JOptionPane.showConfirmDialog(this, "Bạn muốn thêm không ?", "Có", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
        if (bb == JOptionPane.YES_OPTION) {
            NhanVien nv = new NhanVien();
            nv.setMaNV(txtMaNhanVien.getText());
            nv.setHoTenNV(txtHoTenNV.getText());
            if (rdNamNV.isSelected()) {
                nv.setGioiTinh("Nam");
            } else {
                nv.setGioiTinh("Nữ");
            }
            nv.setSdt(txtSDTNhanVien.getText());
            nv.setChucvu((ChucVu) cbCV.getSelectedItem());
            nv.setDiaChi(txtDCNV.getText());
            nv.setNgaySinh((Date) dateNSNV.getDate());
            nv.setMatkhau(txtMKNV.getText());
            try {
                nvService.create(nv);
            } catch (Exception ex) {
                Logger.getLogger(QuanLyView.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(this, "Thêm thành công");
        } else if (bb == JOptionPane.NO_OPTION) {
            return;
        } else {
            return;
        }
        loadTableNhanVien();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void btnsudungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsudungActionPerformed
        // TODO add your handling code here:
        ChucVu cv = new ChucVu();
        cv.setIdCV(UUID.fromString(txtIDChucVu.getText()));
        cv.setMaCV(txtMaChucVu.getText());
        cv.setTenCV(txtTenChucVu.getText());
        try {
            cvService.delete(cv);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        loadTableChucVu();
    }//GEN-LAST:event_btnsudungActionPerformed

    private void btnsudung1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsudung1ActionPerformed
        int row = tbChucVu.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn vào trong bảng");
            return;
        }
        if (txtMaChucVu.getText().equals("") || txtTenChucVu.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Ban Chưa nhập đủ dữ liệu");
            return;
        }        // TODO add your handling code here:
        ChucVu cv = new ChucVu();
        cv.setIdCV(UUID.fromString(txtIDChucVu.getText()));
        cv.setMaCV(txtMaChucVu.getText());
        cv.setTenCV(txtTenChucVu.getText());
        try {
            cvService.update(cv);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        loadTableChucVu();
    }//GEN-LAST:event_btnsudung1ActionPerformed

    private void btnsudung2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsudung2ActionPerformed
        // TODO add your handling code here:
        ChucVu cv = new ChucVu();
        cv.setMaCV(txtMaChucVu.getText());
        cv.setTenCV(txtTenChucVu.getText());
        try {
            cvService.create(cv);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        loadTableChucVu();

    }//GEN-LAST:event_btnsudung2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) pnlCacGiaoDien.getLayout();
        layout.show(pnlCacGiaoDien, "cardChucVu");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtMaNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNhanVienActionPerformed

    private void btnxacnhan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxacnhan1ActionPerformed
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) pnlCacGiaoDien.getLayout();
        layout.show(pnlCacGiaoDien, "cardLayThongTin");
        listKH = serviceKH.getAll();
        showDataKH(listKH);
        //        KH1 kh = new KH1();
        //        kh.setVisible(true);
    }//GEN-LAST:event_btnxacnhan1ActionPerformed

    private void LBtimeAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_LBtimeAncestorAdded
        // TODO add your handling code here:
//        Time times = new Time(LBtime);
//        times.start();
    }//GEN-LAST:event_LBtimeAncestorAdded

    private void btntaohd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntaohd1ActionPerformed
        // TODO add your handling code here:
        Date date = new Date(System.currentTimeMillis());
        HoaDon hd = new HoaDon();
        List<HoaDonBanHangViewModel> st = hoaDonBanHangService.all();
        for (int i = 0; i < st.size() + 1; i++) {
            hd.setMaHD("HD" + (i + 1));
        }

        hd.setNgayTao(new Date());
        hd.setTinhTrang(0);
        try {
            hoaDonBanHangService.add(hd);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        loadTableHoaDonBanHang();

    }//GEN-LAST:event_btntaohd1ActionPerformed

    private void txtSDTNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTNhanVienActionPerformed

    private void tbHoaDonBanHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHoaDonBanHangMouseClicked
        // TODO add your handling code here:
        int row = tbHoaDonBanHang.getSelectedRow();
        txtMaHdBH.setText(tbHoaDonBanHang.getValueAt(row, 0).toString());
        LBtime.setText(tbHoaDonBanHang.getValueAt(row, 1).toString());
    }//GEN-LAST:event_tbHoaDonBanHangMouseClicked

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        int row = tblKhachHang.getSelectedRow();
        KhachHang kh = listKH.get(row);
       
        String tenKH = kh.getHoTen();
        String tenKH1 = kh.getMa();
        CardLayout layout = (CardLayout) pnlCacGiaoDien.getLayout();
        layout.show(pnlCacGiaoDien, "cardBanHang");
        txttenkh1.setText(tenKH);
        cbTenKH.setSelectedItem(tenKH1);
        System.out.println(tenKH1);

       


    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void txtKhachTra1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtKhachTra1CaretUpdate
        // TODO add your handling code here:
        double tiendu = Double.parseDouble(txtKhachTra1.getText()) - Double.parseDouble(txtThanhTien1.getText());
        txtTienDu1.setText(String.valueOf(tiendu));
    }//GEN-LAST:event_txtKhachTra1CaretUpdate

    private void txtThanhTien1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtThanhTien1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtThanhTien1CaretUpdate

    private void btnthanhtoan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthanhtoan2ActionPerformed
        // TODO add your handling code here:
        Date date = new Date(System.currentTimeMillis());
        HoaDon hd = new HoaDon();
        int row = tbHoaDonBanHang.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn trong dòng");
            return;
        }
        String ma = cbTenKH.getSelectedItem().toString();
        String ma1 = cbMaNhanVien.getSelectedItem().toString();
        hd.setMaHD(txtMaHdBH.getText());
        hd.setNgayThanhToan(new Date());
        hd.setNgayDat(jDateNgayDat.getDate());
        hd.setNgayShip(jDateNgaySHIP.getDate());
        hd.setNgayNhan(jDateNgayNhan.getDate());
        hd.setTinhTrang(1);
        hd.setTongTien(BigDecimal.valueOf(Double.parseDouble(txtThanhTien1.getText())));
        try {
            hoaDonBanHangService.update(hd, ma, ma1);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        loadTableHoaDon();
        loadTableHoaDonBanHang();
    }//GEN-LAST:event_btnthanhtoan2ActionPerformed

    private void txttenkh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttenkh1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttenkh1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void txtSearchSPCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearchSPCaretUpdate
        // TODO add your handling code here:
        List<ChiTietSPViewModel> ds = new ArrayList<>();
        for (ChiTietSPViewModel g : chiTietSPService.all()) {
            if (g.getDeGiay().toString().contains(txtSearchSP.getText())) {
                ds.add(g);
            }
        }
        loadTableChiTietSP();
    }//GEN-LAST:event_txtSearchSPCaretUpdate

    private void btnGiaoHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGiaoHangActionPerformed
        // TODO add your handling code here:

        CardLayout layout = (CardLayout) pnlCacGiaoDien.getLayout();
        layout.show(pnlCacGiaoDien, "cardThoiGian");
    }//GEN-LAST:event_btnGiaoHangActionPerformed

    private void btnSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearch1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuanLyView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ID;
    private javax.swing.JLabel LBtime;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBanHang2;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnDelete;
    private javax.swing.JRadioButton btnGiaoHang;
    private javax.swing.JButton btnHoaDon;
    private javax.swing.JButton btnKhachHang;
    private javax.swing.JButton btnNext1;
    private javax.swing.JButton btnNhanVien;
    private javax.swing.JButton btnPrevious1;
    private javax.swing.JButton btnSanPham;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearch1;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSua1;
    private javax.swing.JButton btnSua2;
    private javax.swing.JButton btnSua3;
    private javax.swing.JButton btnSua4;
    private javax.swing.JButton btnSua5;
    private javax.swing.JButton btnSua6;
    private javax.swing.JButton btnSua7;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThem1;
    private javax.swing.JButton btnThem2;
    private javax.swing.JButton btnThem3;
    private javax.swing.JButton btnThem4;
    private javax.swing.JButton btnThem5;
    private javax.swing.JButton btnThem6;
    private javax.swing.JButton btnThem7;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnThongKe;
    private javax.swing.JButton btnTimKiem2;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoa1;
    private javax.swing.JButton btnXoa2;
    private javax.swing.JButton btnXoa3;
    private javax.swing.JButton btnXoa4;
    private javax.swing.JButton btnXoa5;
    private javax.swing.JButton btnXoa6;
    private javax.swing.JButton btnXoa7;
    private javax.swing.JButton btnback;
    private javax.swing.JButton btnlammoi;
    private javax.swing.JButton btnnext;
    private javax.swing.JButton btnsudung;
    private javax.swing.JButton btnsudung1;
    private javax.swing.JButton btnsudung2;
    private javax.swing.JButton btntaohd;
    private javax.swing.JButton btntaohd1;
    private javax.swing.JButton btnthanhtoan2;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btntimkiem;
    private javax.swing.JButton btnxacnhan1;
    private javax.swing.JButton btnxoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonKH;
    private javax.swing.ButtonGroup buttonNV;
    private javax.swing.ButtonGroup buttonSP;
    private javax.swing.JComboBox<String> cbCL;
    private javax.swing.JComboBox<String> cbCL1;
    private javax.swing.JComboBox<String> cbCV;
    private javax.swing.JComboBox<String> cbDe;
    private javax.swing.JComboBox<String> cbDe1;
    private javax.swing.JComboBox<String> cbHang;
    private javax.swing.JComboBox<String> cbHang1;
    private javax.swing.JComboBox<String> cbLoai;
    private javax.swing.JComboBox<String> cbLoai1;
    private javax.swing.JComboBox<String> cbMaNhanVien;
    private javax.swing.JComboBox<String> cbSIZE;
    private javax.swing.JComboBox<String> cbSIZE1;
    private javax.swing.JComboBox<String> cbTenKH;
    private javax.swing.JComboBox<String> cbTenSP;
    private javax.swing.JComboBox<String> cbTenSP1;
    private com.toedter.calendar.JDateChooser dateNSNV;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox12;
    private javax.swing.JComboBox<String> jComboBox13;
    private javax.swing.JComboBox<String> jComboBox14;
    private javax.swing.JComboBox<String> jComboBox15;
    private javax.swing.JComboBox<String> jComboBox16;
    private javax.swing.JComboBox<String> jComboBox17;
    private javax.swing.JComboBox<String> jComboBox18;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateNgayDat;
    private com.toedter.calendar.JDateChooser jDateNgayNhan;
    private com.toedter.calendar.JDateChooser jDateNgaySHIP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable8;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel lbPagination1;
    private javax.swing.JLabel lbTotalProducts1;
    private javax.swing.JLabel lblAnhDaiDien2;
    private javax.swing.JLabel lblBieuDo;
    private javax.swing.JLabel lblDanhMuc10;
    private javax.swing.JLabel lblDanhMuc11;
    private javax.swing.JLabel lblDanhMuc7;
    private javax.swing.JLabel lblDanhMuc8;
    private javax.swing.JLabel lblDanhMuc9;
    private javax.swing.JLabel lblDonGia;
    private javax.swing.JLabel lblDonGia1;
    private javax.swing.JLabel lblDonGia2;
    private javax.swing.JLabel lblDonGia3;
    private javax.swing.JLabel lblDonGia4;
    private javax.swing.JLabel lblDonGia5;
    private javax.swing.JLabel lblDonGia7;
    private javax.swing.JLabel lblMaSp;
    private javax.swing.JLabel lblMaSp1;
    private javax.swing.JLabel lblMaSp10;
    private javax.swing.JLabel lblMaSp11;
    private javax.swing.JLabel lblMaSp12;
    private javax.swing.JLabel lblMaSp13;
    private javax.swing.JLabel lblMaSp14;
    private javax.swing.JLabel lblMaSp15;
    private javax.swing.JLabel lblMaSp16;
    private javax.swing.JLabel lblMaSp17;
    private javax.swing.JLabel lblMaSp18;
    private javax.swing.JLabel lblMaSp19;
    private javax.swing.JLabel lblMaSp2;
    private javax.swing.JLabel lblMaSp20;
    private javax.swing.JLabel lblMaSp21;
    private javax.swing.JLabel lblMaSp22;
    private javax.swing.JLabel lblMaSp23;
    private javax.swing.JLabel lblMaSp24;
    private javax.swing.JLabel lblMaSp25;
    private javax.swing.JLabel lblMaSp26;
    private javax.swing.JLabel lblMaSp27;
    private javax.swing.JLabel lblMaSp28;
    private javax.swing.JLabel lblMaSp29;
    private javax.swing.JLabel lblMaSp3;
    private javax.swing.JLabel lblMaSp30;
    private javax.swing.JLabel lblMaSp31;
    private javax.swing.JLabel lblMaSp32;
    private javax.swing.JLabel lblMaSp33;
    private javax.swing.JLabel lblMaSp34;
    private javax.swing.JLabel lblMaSp35;
    private javax.swing.JLabel lblMaSp36;
    private javax.swing.JLabel lblMaSp37;
    private javax.swing.JLabel lblMaSp38;
    private javax.swing.JLabel lblMaSp4;
    private javax.swing.JLabel lblMaSp5;
    private javax.swing.JLabel lblMaSp6;
    private javax.swing.JLabel lblMaSp7;
    private javax.swing.JLabel lblMaSp8;
    private javax.swing.JLabel lblMaSp9;
    private javax.swing.JLabel lblMoTa;
    private javax.swing.JLabel lblMoTa1;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JLabel lblTrangThai1;
    private javax.swing.JLabel lblTrangThai3;
    private java.awt.Panel panel3;
    private javax.swing.JPanel pnlAnhDaiDien2;
    private javax.swing.JPanel pnlBanHang;
    private javax.swing.JPanel pnlCacGiaoDien;
    private javax.swing.JPanel pnlDoiMatKhau;
    private javax.swing.JPanel pnlHoaDon;
    private javax.swing.JPanel pnlKhachHang;
    private javax.swing.JPanel pnlLayThongTin;
    private javax.swing.JPanel pnlMenu2;
    private javax.swing.JPanel pnlNhanVien;
    private javax.swing.JPanel pnlSanPham;
    private javax.swing.JPanel pnlThongKe;
    private javax.swing.JPanel pnlTong;
    private javax.swing.JRadioButton rdNam;
    private javax.swing.JRadioButton rdNamNV;
    private javax.swing.JRadioButton rdNu;
    private javax.swing.JRadioButton rdNuNV;
    private javax.swing.JRadioButton rdoConHang;
    private javax.swing.JRadioButton rdoConHang1;
    private javax.swing.JRadioButton rdoHetHang;
    private javax.swing.JRadioButton rdoHetHang1;
    private javax.swing.JTable tbChatLieu;
    private javax.swing.JTable tbChucVu;
    private javax.swing.JTable tbDeGiay;
    private javax.swing.JTable tbHang;
    private javax.swing.JTable tbHoaDonBanHang;
    private javax.swing.JTable tbLoaiGiay;
    private javax.swing.JTable tbNhanVien;
    private javax.swing.JTable tbSIZE;
    private javax.swing.JTable tbSP;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblKH;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JComboBox<String> tblLoaiThoiGian;
    private javax.swing.JTable tblQLSP;
    private javax.swing.JTable tbldssanpham;
    private javax.swing.JTable tblgiohang;
    private javax.swing.JTextArea txtDC;
    private javax.swing.JTextField txtDCNV;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtDonGia1;
    private javax.swing.JTextField txtGhiChu1;
    private javax.swing.JTextField txtHoTenNV;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtID1;
    private javax.swing.JTextField txtIDChatLieu;
    private javax.swing.JTextField txtIDChucVu;
    private javax.swing.JTextField txtIDDeGiay;
    private javax.swing.JTextField txtIDHang;
    private javax.swing.JTextField txtIDLoai;
    private javax.swing.JTextField txtIDNhanVien;
    private javax.swing.JTextField txtIDSIZE;
    private javax.swing.JTextField txtIDsp;
    private javax.swing.JTextField txtIdKH;
    private javax.swing.JTextField txtKhachTra1;
    private javax.swing.JTextField txtLoaiDe;
    private javax.swing.JTextField txtMKNV;
    private javax.swing.JTextField txtMaChatLieu;
    private javax.swing.JTextField txtMaChucVu;
    private javax.swing.JTextField txtMaDe;
    private javax.swing.JTextField txtMaHang;
    private javax.swing.JTextField txtMaHdBH;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaLoai;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JTextField txtMaSIZE;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtMoTaCL;
    private javax.swing.JTextField txtMoTaDe;
    private javax.swing.JTextField txtMoTaHang;
    private javax.swing.JTextField txtMoTaLoaiGiay;
    private javax.swing.JTextField txtMoTaSP;
    private javax.swing.JTextArea txtMotaChiTietSP;
    private javax.swing.JTextArea txtMotaChiTietSP1;
    private com.toedter.calendar.JDateChooser txtNgaySinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSDTNhanVien;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearch1;
    private javax.swing.JTextField txtSearchSP;
    private javax.swing.JTextField txtSoLuongSP;
    private javax.swing.JTextField txtSoLuongSP1;
    private javax.swing.JTextField txtSoSize;
    private javax.swing.JTextField txtTKNV;
    private javax.swing.JTextField txtTenChatLieu;
    private javax.swing.JTextField txtTenChucVu;
    private javax.swing.JTextField txtTenHang;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTenLoai;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtThanhTien1;
    private javax.swing.JLabel txtTienDu1;
    private javax.swing.JTextField txtTrongLuong;
    private javax.swing.JTextField txtTrongLuong1;
    private javax.swing.JTextField txttMaSPP;
    private javax.swing.JTextField txttMaSPP1;
    private javax.swing.JTextField txttenkh1;
    private javax.swing.JTextField txttimkiem;
    private javax.swing.JButton updatesp;
    private javax.swing.JButton updatesp1;
    private javax.swing.JButton updatesp10;
    private javax.swing.JButton updatesp11;
    private javax.swing.JButton updatesp2;
    private javax.swing.JButton updatesp3;
    private javax.swing.JButton updatesp4;
    private javax.swing.JButton updatesp5;
    private javax.swing.JButton updatesp6;
    private javax.swing.JButton updatesp7;
    private javax.swing.JButton updatesp8;
    private javax.swing.JButton updatesp9;
    // End of variables declaration//GEN-END:variables
}
