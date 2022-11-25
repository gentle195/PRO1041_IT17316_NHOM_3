/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import DomainModels.ChatLieu;
import DomainModels.ChiTietSP;
import DomainModels.DeGiay;
import DomainModels.HangGiay;
import DomainModels.KhachHang;
import DomainModels.LoaiGiay;
import DomainModels.SanPham;
import DomainModels.Size;
import Services.ChatLieuService;
import Services.ChiTietSPService;
import Services.DeGiayService;
import Services.HangGiayService;
import Services.Interface.ChatLieuServiceInterface;
import Services.Interface.ChiTietSPServiceInterface;
import Services.Interface.DeGiayServiceInterface;
import Services.Interface.HangGiayServiceInterface;
import Services.Interface.LoaiGiayServiceInterface;
import Services.Interface.SanPhamServiceInterface;
import Services.Interface.SizeServiceInterface;
import Services.KhachHangService;
import Services.LoaiGiayService;
import Services.SanPhamService;
import Services.SizeService;
import ViewModels.ChiTietSPViewModel;
import java.awt.CardLayout;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private KhachHangService serviceKH = new KhachHangService();
    private DefaultTableModel dtmKH = new DefaultTableModel();
    private List<KhachHang> listKH = new ArrayList<>();

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
        this.loadTableSanPham();
        this.loadTableChatLieu();
        this.loadTableHangGiay();
        this.loadTableDeGiay();
        this.loadTableLoaiGiay();
        this.loadTableSizeGiay();
        this.loadTableChiTietSP();
//        this.loadCBChatLieu();
//        this.loadCBDe();
//        this.loadCBHang();
//        this.loadCBLoai();
//        this.loadCBSize();
//        this.loadCBTenSP();
//        this.loadCBMaSP();

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
        tblKH.setModel(dtmKH);
        Object[] header = {"ID", "Mã", "Họ tên", "Giới tính", "Ngày sinh", "SĐT", "Địa chỉ"};
        dtmKH.setColumnIdentifiers(header);

        AutoCompleteDecorator.decorate(cbCL);
        AutoCompleteDecorator.decorate(cbTenSP);
        AutoCompleteDecorator.decorate(cbDe);
        AutoCompleteDecorator.decorate(cbHang);
        AutoCompleteDecorator.decorate(cbSIZE);
        AutoCompleteDecorator.decorate(cbLoai);
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
        this.buttonGroup1.clearSelection();
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
//
//    private void loadCBMaSP() {
//        DefaultComboBoxModel cb = new DefaultComboBoxModel();
//        List<SanPhamViewModel> sp = SanPhamService.all();
//        cb = (DefaultComboBoxModel) cbMaSP.getModel();
//        for (SanPhamViewModel x : sp) {
//            cb.addElement(x.getMaSP());
//        }
//    }
//
//    private void loadCBTenSP() {
//        DefaultComboBoxModel cb = new DefaultComboBoxModel();
//        List<SanPhamViewModel> sp = SanPhamService.all();
//        cb = (DefaultComboBoxModel) cbTenSP.getModel();
//        for (SanPhamViewModel x : sp) {
//            cb.addElement(x.getTenSP());
//        }
//    }
//
//    private void loadCBChatLieu() {
//        DefaultComboBoxModel cb = new DefaultComboBoxModel();
//        List<ChatLieuViewModel> sp = chatLieuService.all();
//        cb = (DefaultComboBoxModel) cbCL.getModel();
//        for (ChatLieuViewModel x : sp) {
//            cb.addElement(x.getTenCL());
//        }
//    }
//
//    private void loadCBSize() {
//        DefaultComboBoxModel cb = new DefaultComboBoxModel();
//        List<SizeViewModel> sp = sizeService.all();
//        cb = (DefaultComboBoxModel) cbSIZE.getModel();
//        for (SizeViewModel x : sp) {
//            cb.addElement(x.getSoSize());
//        }
//    }
//
//    private void loadCBLoai() {
//        DefaultComboBoxModel cb = new DefaultComboBoxModel();
//        List<LoaiGiayViewModel> sp = loaiGiayService.all();
//        cb = (DefaultComboBoxModel) cbLoai.getModel();
//        for (LoaiGiayViewModel x : sp) {
//            cb.addElement(x.getTenLoai());
//        }
//    }
//
//    private void loadCBHang() {
//        DefaultComboBoxModel cb = new DefaultComboBoxModel();
//        List<HangGiayViewModel> sp = hangGiayService.all();
//        cb = (DefaultComboBoxModel) cbHang.getModel();
//        for (HangGiayViewModel x : sp) {
//            cb.addElement(x.getTenHang());
//        }
//    }
//
//    private void loadCBDe() {
//        DefaultComboBoxModel cb = new DefaultComboBoxModel();
//        List<DeGiayViewModel> sp = deGiayService.all();
//        cb = (DefaultComboBoxModel) cbDe.getModel();
//        for (DeGiayViewModel x : sp) {
//            cb.addElement(x.getLoaiDe());
//        }
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        btntaohd = new javax.swing.JButton();
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
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        txttenkh = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblngay = new javax.swing.JLabel();
        btnxacnhan = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        lblmanv = new javax.swing.JLabel();
        txtmagiamgia = new javax.swing.JTextField();
        btnsudung = new javax.swing.JButton();
        cboloaikh = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        txtdiemtv = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        lbltongtien = new javax.swing.JLabel();
        txtphikhac = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        lblgiamgia = new javax.swing.JLabel();
        txtkhachtra = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        giamgia = new javax.swing.JLabel();
        txtghichu = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        lblkhachcantra = new javax.swing.JLabel();
        lbltientralai = new javax.swing.JLabel();
        btnthanhtoan = new javax.swing.JButton();
        pnlHoaDon = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
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
        jComboBox7 = new javax.swing.JComboBox<>();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jRadioButton5 = new javax.swing.JRadioButton();
        jLabel49 = new javax.swing.JLabel();
        jRadioButton6 = new javax.swing.JRadioButton();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jPanel36 = new javax.swing.JPanel();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jPanel38 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
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
        jTextField4 = new javax.swing.JTextField();
        jButton21 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        jButton22 = new javax.swing.JButton();
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
        txtSearch2 = new javax.swing.JTextField();
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
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("Giỏ hàng"));

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

        btntaohd.setBackground(new java.awt.Color(204, 204, 204));
        btntaohd.setText("Tạo hóa đơn");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnlammoi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnxoa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btntaohd)
                .addContainerGap())
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 814, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btntaohd)
                    .addComponent(btnxoa)
                    .addComponent(btnlammoi))
                .addGap(17, 17, 17))
        );

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách sản phẩm"));

        tbldssanpham.setForeground(new java.awt.Color(255, 255, 0));
        tbldssanpham.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên Sản phẩm", "Đơn giá", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
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
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 799, Short.MAX_VALUE)))
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
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnback)
                    .addComponent(jLabel36)
                    .addComponent(btnnext)
                    .addComponent(btnthem))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        jTabbedPane4.setBackground(new java.awt.Color(204, 255, 255));
        jTabbedPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tạo hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 51, 51))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "thông tin chung", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        txttenkh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttenkhActionPerformed(evt);
            }
        });

        jLabel7.setText("Loại Khách Hàng");

        jLabel8.setText("Họ và Tên KH");

        jLabel10.setText("Ngày Tạo");

        jLabel12.setText("Mã giảm giá");

        lblngay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblngay.setForeground(new java.awt.Color(51, 51, 255));
        lblngay.setText("11/7/2022  14:30 AM ");

        btnxacnhan.setBackground(new java.awt.Color(204, 204, 204));
        btnxacnhan.setText("Lấy thông tin");
        btnxacnhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxacnhanActionPerformed(evt);
            }
        });

        jLabel20.setText("Mã Nhân Viên");

        lblmanv.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblmanv.setForeground(new java.awt.Color(51, 51, 255));
        lblmanv.setText("NV01");

        txtmagiamgia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmagiamgiaActionPerformed(evt);
            }
        });

        btnsudung.setBackground(new java.awt.Color(204, 204, 204));
        btnsudung.setText("Sử dụng");
        btnsudung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsudungActionPerformed(evt);
            }
        });

        cboloaikh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Khách Hàng ", "Khách Vãng Lai", " " }));
        cboloaikh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboloaikhActionPerformed(evt);
            }
        });

        jLabel33.setText("Điểm Thành viên");

        txtdiemtv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdiemtvActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblmanv, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblngay)))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txttenkh, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboloaikh, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnxacnhan))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtmagiamgia, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtdiemtv, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnsudung, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(lblmanv))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lblngay))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboloaikh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttenkh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(btnxacnhan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(txtmagiamgia, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(txtdiemtv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsudung))
                .addGap(29, 29, 29))
        );

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        lbltongtien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbltongtien.setForeground(new java.awt.Color(204, 0, 51));
        lbltongtien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbltongtien.setText("VND");

        jLabel16.setText("Ghi chú");

        lblgiamgia.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblgiamgia.setForeground(new java.awt.Color(0, 51, 204));
        lblgiamgia.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblgiamgia.setText("VND");

        jLabel14.setText("Khách trả");

        jLabel34.setText("Tổng Tiền Hàng");

        jLabel35.setText("Khách cần trả");

        jLabel15.setText("Tiền Trả lại");

        giamgia.setText("Giảm giá");

        jLabel11.setText("Phí Khác");

        lblkhachcantra.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblkhachcantra.setForeground(new java.awt.Color(204, 0, 51));
        lblkhachcantra.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblkhachcantra.setText("VND");

        lbltientralai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbltientralai.setForeground(new java.awt.Color(0, 0, 153));
        lbltientralai.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbltientralai.setText("VND");

        btnthanhtoan.setBackground(new java.awt.Color(204, 204, 204));
        btnthanhtoan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnthanhtoan.setText("Thanh Toán");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addGap(18, 18, 18)
                        .addComponent(lbltongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(giamgia, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtghichu, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtphikhac, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblgiamgia, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbltientralai, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblkhachcantra, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtkhachtra, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(btnthanhtoan)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34)
                    .addComponent(lbltongtien))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblgiamgia)
                    .addComponent(giamgia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtphikhac, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblkhachcantra)
                    .addComponent(jLabel35))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtkhachtra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbltientralai))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(txtghichu, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnthanhtoan)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(130, 130, 130))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane4.addTab("", jPanel2);

        javax.swing.GroupLayout pnlBanHangLayout = new javax.swing.GroupLayout(pnlBanHang);
        pnlBanHang.setLayout(pnlBanHangLayout);
        pnlBanHangLayout.setHorizontalGroup(
            pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBanHangLayout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 849, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlBanHangLayout.setVerticalGroup(
            pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBanHangLayout.createSequentialGroup()
                .addGroup(pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlBanHangLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 705, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28))
        );

        pnlCacGiaoDien.add(pnlBanHang, "cardBanHang");

        pnlHoaDon.setBackground(new java.awt.Color(204, 204, 204));
        pnlHoaDon.setPreferredSize(new java.awt.Dimension(1070, 760));
        pnlHoaDon.setLayout(new java.awt.BorderLayout());

        jPanel20.setPreferredSize(new java.awt.Dimension(1271, 380));

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N
        jPanel21.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jTable7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable7.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã hóa đơn", "Mã nhân viên", "Ngày tạo", "Ngày Đặt", "Ngày Thanh Toán", "Ngày SHIP", "Ngày Nhận", "Mã khách hàng", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane10.setViewportView(jTable7);

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
                        .addGap(105, 105, 105)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 1009, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(369, Short.MAX_VALUE))
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
            .addGap(0, 1499, Short.MAX_VALUE)
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
                .addContainerGap(388, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
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

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chức vụ", "Quản lý", "Nhân Viên" }));

        jTextField12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField12ActionPerformed(evt);
            }
        });

        jTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });

        jLabel27.setText("LƯƠNG");

        jLabel42.setText("EMAIL");

        jTextField14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField14ActionPerformed(evt);
            }
        });

        jLabel43.setText("GIỚI TÍNH");

        jLabel44.setText("QUÊ QUÁN");

        jTextField15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField15ActionPerformed(evt);
            }
        });

        jTextField16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField16ActionPerformed(evt);
            }
        });

        jLabel45.setText("MẬT KHẨU");

        jTextField17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField17ActionPerformed(evt);
            }
        });

        jLabel46.setText("TÀI KHOẢN");

        jLabel47.setText("NGÀY SINH");

        jLabel48.setText("MÃ NHÂN VIÊN");

        jTextField18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField18ActionPerformed(evt);
            }
        });

        jRadioButton5.setText("NAM");
        jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton5ActionPerformed(evt);
            }
        });

        jLabel49.setText("HỌ VÀ TÊN");

        jRadioButton6.setText("NỮ");

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(jRadioButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel34Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 539, Short.MAX_VALUE)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(21, 21, 21)
                            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField17, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                                .addComponent(jTextField16)))
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
                                .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooser3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(38, 38, 38))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel44))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47)
                            .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel46)
                            .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45)))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel48))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel43)
                            .addComponent(jRadioButton5)
                            .addComponent(jRadioButton6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel42))))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jPanel36.setBackground(new java.awt.Color(255, 255, 255));
        jPanel36.setBorder(javax.swing.BorderFactory.createTitledBorder("CHỨC NĂNG"));

        jButton13.setBackground(new java.awt.Color(204, 204, 204));
        jButton13.setText("THÊM");

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

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
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
                "MÃ NHÂN VIÊN", "HỌ VÀ TÊN", "GIỚI TÍNH", "CHỨC VỤ", "EMAIL", "QUÊ QUÁN", "NGÀY SINH", "LƯƠNG", "TÀI KHOẢN", "MẬT KHẨU"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane15.setViewportView(jTable5);

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

        rdNam.setSelected(true);
        rdNam.setText("Nam");
        rdNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNamActionPerformed(evt);
            }
        });

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
                            .addComponent(txtTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE))))
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
            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, 1499, Short.MAX_VALUE)
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
                .addContainerGap(974, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(1194, Short.MAX_VALUE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Bảng doanh thu", jPanel9);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblBieuDo, javax.swing.GroupLayout.DEFAULT_SIZE, 1497, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblBieuDo, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
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
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1497, Short.MAX_VALUE)
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE))
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
                    .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, 111, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );

        javax.swing.GroupLayout pnlThongKeLayout = new javax.swing.GroupLayout(pnlThongKe);
        pnlThongKe.setLayout(pnlThongKeLayout);
        pnlThongKeLayout.setHorizontalGroup(
            pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1499, Short.MAX_VALUE)
            .addGroup(pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlThongKeLayout.setVerticalGroup(
            pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 788, Short.MAX_VALUE)
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
                .addContainerGap(613, Short.MAX_VALUE))
        );
        pnlDoiMatKhauLayout.setVerticalGroup(
            pnlDoiMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDoiMatKhauLayout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
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
            .addGap(0, 588, Short.MAX_VALUE)
        );

        pnlLayThongTin.add(jPanel1, java.awt.BorderLayout.LINE_START);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1499, Short.MAX_VALUE)
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
            .addGap(0, 588, Short.MAX_VALUE)
        );

        pnlLayThongTin.add(jPanel14, java.awt.BorderLayout.LINE_END);

        jPanel42.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1499, Short.MAX_VALUE)
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

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jButton21.setBackground(new java.awt.Color(204, 204, 204));
        jButton21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton21.setText("Tìm Kiếm");

        jTable9.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane8.setViewportView(jTable9);

        jButton22.setBackground(new java.awt.Color(204, 204, 204));
        jButton22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton22.setText("Xác Nhận");

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel44Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)))
                        .addGap(13, 13, 13)
                        .addComponent(jButton21))
                    .addGroup(jPanel44Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 779, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(470, Short.MAX_VALUE))
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
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton21)))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        rdoConHang.setText("Còn Hàng");
        rdoConHang.setEnabled(false);
        rdoConHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoConHangActionPerformed(evt);
            }
        });

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

        updatesp.setIcon(new javax.swing.ImageIcon("D:\\GitHub\\PRO1041_IT17316_NHOM_3\\QuanLyCuaHangGiaySportPowers\\PRO1041_IT17316_NHOM_3\\QuanLyCuaHangGiaySportPowerss\\src\\main\\java\\icon\\Add.png")); // NOI18N
        updatesp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatespActionPerformed(evt);
            }
        });

        updatesp2.setIcon(new javax.swing.ImageIcon("D:\\GitHub\\PRO1041_IT17316_NHOM_3\\QuanLyCuaHangGiaySportPowers\\PRO1041_IT17316_NHOM_3\\QuanLyCuaHangGiaySportPowerss\\src\\main\\java\\icon\\Add.png")); // NOI18N
        updatesp2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatesp2ActionPerformed(evt);
            }
        });

        updatesp3.setIcon(new javax.swing.ImageIcon("D:\\GitHub\\PRO1041_IT17316_NHOM_3\\QuanLyCuaHangGiaySportPowers\\PRO1041_IT17316_NHOM_3\\QuanLyCuaHangGiaySportPowerss\\src\\main\\java\\icon\\Add.png")); // NOI18N
        updatesp3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatesp3ActionPerformed(evt);
            }
        });

        updatesp4.setIcon(new javax.swing.ImageIcon("D:\\GitHub\\PRO1041_IT17316_NHOM_3\\QuanLyCuaHangGiaySportPowers\\PRO1041_IT17316_NHOM_3\\QuanLyCuaHangGiaySportPowerss\\src\\main\\java\\icon\\Add.png")); // NOI18N
        updatesp4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatesp4ActionPerformed(evt);
            }
        });

        updatesp5.setIcon(new javax.swing.ImageIcon("D:\\GitHub\\PRO1041_IT17316_NHOM_3\\QuanLyCuaHangGiaySportPowers\\PRO1041_IT17316_NHOM_3\\QuanLyCuaHangGiaySportPowerss\\src\\main\\java\\icon\\Add.png")); // NOI18N
        updatesp5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatesp5ActionPerformed(evt);
            }
        });

        updatesp6.setIcon(new javax.swing.ImageIcon("D:\\GitHub\\PRO1041_IT17316_NHOM_3\\QuanLyCuaHangGiaySportPowers\\PRO1041_IT17316_NHOM_3\\QuanLyCuaHangGiaySportPowerss\\src\\main\\java\\icon\\Add.png")); // NOI18N
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
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDonGia1)
                            .addComponent(lblDonGia)
                            .addComponent(lblDonGia3)
                            .addComponent(lblTrangThai)
                            .addComponent(lblMoTa)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                        .addGap(230, 230, 230)
                        .addComponent(lblMaSp)))
                .addGap(18, 18, 18)
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
                            .addComponent(updatesp6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        txtSearch2.setName(""); // NOI18N

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
                false, false, false, false, false, false, false, false, false, true, false, true, true
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

        lbPagination1.setText("1/1");

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
                        .addComponent(txtSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTimKiem2))
                    .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel51Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(btnPrevious1)
                            .addGap(18, 18, 18)
                            .addComponent(btnNext1)
                            .addGap(18, 18, 18)
                            .addComponent(lbPagination1)
                            .addGap(49, 49, 49)
                            .addComponent(lbTotalProducts1))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel51Layout.createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 974, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel51Layout.createSequentialGroup()
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel51Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTimKiem2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel51Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrevious1)
                    .addComponent(lbPagination1)
                    .addComponent(btnNext1)
                    .addComponent(lbTotalProducts1))
                .addGap(34, 34, 34))
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
                .addContainerGap(204, Short.MAX_VALUE))
        );
        pnlSanPhamLayout.setVerticalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel51, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
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

        updatesp1.setIcon(new javax.swing.ImageIcon("D:\\GitHub\\PRO1041_IT17316_NHOM_3\\QuanLyCuaHangGiaySportPowers\\PRO1041_IT17316_NHOM_3\\QuanLyCuaHangGiaySportPowerss\\src\\main\\java\\icon\\Add.png")); // NOI18N
        updatesp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatesp1ActionPerformed(evt);
            }
        });

        updatesp7.setIcon(new javax.swing.ImageIcon("D:\\GitHub\\PRO1041_IT17316_NHOM_3\\QuanLyCuaHangGiaySportPowers\\PRO1041_IT17316_NHOM_3\\QuanLyCuaHangGiaySportPowerss\\src\\main\\java\\icon\\Add.png")); // NOI18N
        updatesp7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatesp7ActionPerformed(evt);
            }
        });

        updatesp8.setIcon(new javax.swing.ImageIcon("D:\\GitHub\\PRO1041_IT17316_NHOM_3\\QuanLyCuaHangGiaySportPowers\\PRO1041_IT17316_NHOM_3\\QuanLyCuaHangGiaySportPowerss\\src\\main\\java\\icon\\Add.png")); // NOI18N
        updatesp8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatesp8ActionPerformed(evt);
            }
        });

        updatesp9.setIcon(new javax.swing.ImageIcon("D:\\GitHub\\PRO1041_IT17316_NHOM_3\\QuanLyCuaHangGiaySportPowers\\PRO1041_IT17316_NHOM_3\\QuanLyCuaHangGiaySportPowerss\\src\\main\\java\\icon\\Add.png")); // NOI18N
        updatesp9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatesp9ActionPerformed(evt);
            }
        });

        updatesp10.setIcon(new javax.swing.ImageIcon("D:\\GitHub\\PRO1041_IT17316_NHOM_3\\QuanLyCuaHangGiaySportPowers\\PRO1041_IT17316_NHOM_3\\QuanLyCuaHangGiaySportPowerss\\src\\main\\java\\icon\\Add.png")); // NOI18N
        updatesp10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatesp10ActionPerformed(evt);
            }
        });

        updatesp11.setIcon(new javax.swing.ImageIcon("D:\\GitHub\\PRO1041_IT17316_NHOM_3\\QuanLyCuaHangGiaySportPowers\\PRO1041_IT17316_NHOM_3\\QuanLyCuaHangGiaySportPowerss\\src\\main\\java\\icon\\Add.png")); // NOI18N
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
                .addGap(18, 18, Short.MAX_VALUE)
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
                .addGap(0, 0, Short.MAX_VALUE))
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
                .addContainerGap(76, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addContainerGap(757, Short.MAX_VALUE))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(219, Short.MAX_VALUE))
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
                .addContainerGap(757, Short.MAX_VALUE))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(219, Short.MAX_VALUE))
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
                .addContainerGap(757, Short.MAX_VALUE))
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(219, Short.MAX_VALUE))
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
                .addContainerGap(757, Short.MAX_VALUE))
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(219, Short.MAX_VALUE))
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
                .addContainerGap(757, Short.MAX_VALUE))
        );
        jPanel49Layout.setVerticalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(222, Short.MAX_VALUE))
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
                .addContainerGap(757, Short.MAX_VALUE))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(219, Short.MAX_VALUE))
        );

        pnlCacGiaoDien.add(jPanel40, "De");

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
    }//GEN-LAST:event_tblgiohangMouseClicked

    private void btnlammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlammoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnlammoiActionPerformed

    private void txttenkhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttenkhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttenkhActionPerformed

    private void btnxacnhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxacnhanActionPerformed
        // TODO add your handling code here:
        //        CardLayout layout = (CardLayout) pnlCacGiaoDien.getLayout();
        //        layout.show(pnlCacGiaoDien, "cardLayThongTin");
//        KH1 kh = new KH1();
//        kh.setVisible(true);
    }//GEN-LAST:event_btnxacnhanActionPerformed

    private void txtmagiamgiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmagiamgiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmagiamgiaActionPerformed

    private void btnsudungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsudungActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnsudungActionPerformed

    private void cboloaikhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboloaikhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboloaikhActionPerformed

    private void txtdiemtvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdiemtvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdiemtvActionPerformed

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        // TODO add your handling code here:
        //        int row = tbldssanpham.getSelectedRow();
        //        if (row == -1) {
        //            JOptionPane.showMessageDialog(this, "Bạn chưa chọn Sản Phẩm");
        //        } else {
        //            String soluong = JOptionPane.showInputDialog("Mời nhập số lượng");
        //            String PHONENUMBER_PATTERN = "\\d{1}";
        //
        //            if (!soluong.matches(PHONENUMBER_PATTERN)) {
        //                JOptionPane.showConfirmDialog(this, "mời nhập số");
        //
        //            } else {
        //                float sl = Float.parseFloat(soluong);
        //
        //                tblmodel = (DefaultTableModel) tblgiohang.getModel();
        //                tblmodel.setRowCount(0);
        //                String maSP = (String) tbldssanpham.getValueAt(row, 0);
        //
        //                for (SanPham sanPham : listsp) {
        //                    float tong = sanPham.getDonGia() * sl;
        //                    if (sanPham.getMaSP().equals(maSP)) {
        //                        tblmodel.addRow(new Object[]{sanPham.getMaSP(), sanPham.getTenSP(), soluong, sanPham.getDonGia(), tong});
        //                    }
        //
        //                }
        //
        //            }
        //
        //        }
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

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13ActionPerformed

    private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14ActionPerformed

    private void jTextField15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField15ActionPerformed

    private void jTextField16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField16ActionPerformed

    private void jTextField17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField17ActionPerformed

    private void jTextField18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField18ActionPerformed

    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton5ActionPerformed

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

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

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
        SanPham sp = new SanPham();
        sp.setMaSP(txtMaSP.getText());
        sp.setTenSP(txtTenSP.getText());
        sp.setMoTa(txtMoTaSP.getText());
        try {
            SanPhamService.add(sp);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        loadTableSanPham();
        //        loadCBMaSP();
        //        loadCBTenSP();
    }//GEN-LAST:event_btnThem2ActionPerformed

    private void btnSua2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua2ActionPerformed
        // TODO add your handling code here:
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
        loadTableSanPham();
        //        loadCBMaSP();
        //        loadCBTenSP();
    }//GEN-LAST:event_btnSua2ActionPerformed

    private void btnXoa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa2ActionPerformed
        // TODO add your handling code here:
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
        ChatLieu cl = new ChatLieu();
        cl.setMaCL(txtMaChatLieu.getText());
        cl.setTenCL(txtTenChatLieu.getText());
        cl.setMoTaCl(txtMoTaCL.getText());

        try {
            chatLieuService.add(cl);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        loadTableChatLieu();
        //        loadCBChatLieu();
    }//GEN-LAST:event_btnThem3ActionPerformed

    private void btnSua3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua3ActionPerformed
        // TODO add your handling code here:
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
        loadTableChatLieu();
        //        loadCBChatLieu();
    }//GEN-LAST:event_btnSua3ActionPerformed

    private void btnXoa3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa3ActionPerformed
        // TODO add your handling code here:
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
            HangGiay hangSP = new HangGiay();

            hangSP.setMaHang(txtMaHang.getText());
            hangSP.setTenHang(txtTenHang.getText());
            hangSP.setMoTaHang(txtMoTaHang.getText());
            if (hangSP == null) {
                return;
            }
            try {
                hangGiayService.add(hangSP);
                JOptionPane.showMessageDialog(this, "Thêm thành công");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            loadTableHangGiay();
            //            loadCBHang();
        }
    }//GEN-LAST:event_btnThem4ActionPerformed

    private void btnSua4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua4ActionPerformed
        // TODO add your handling code here:
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
        loadTableHangGiay();
        //        loadCBHang();
    }//GEN-LAST:event_btnSua4ActionPerformed

    private void btnXoa4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa4ActionPerformed
        // TODO add your handling code here:
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
        LoaiGiay lg = new LoaiGiay();
        lg.setMaLoai(txtMaLoai.getText());
        lg.setTenLoai(txtTenLoai.getText());
        lg.setMoTa(txtMoTaLoaiGiay.getText());
        try {
            loaiGiayService.add(lg);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        loadTableLoaiGiay();
        //        loadCBLoai();
    }//GEN-LAST:event_btnThem5ActionPerformed

    private void btnSua5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua5ActionPerformed
        // TODO add your handling code here:
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
        loadTableLoaiGiay();
        //        loadCBLoai();
    }//GEN-LAST:event_btnSua5ActionPerformed

    private void btnXoa5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa5ActionPerformed
        // TODO add your handling code here:
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
        Size s = new Size();
        s.setMaSize(txtMaSIZE.getText());
        s.setSoSize(Integer.parseInt(txtSoSize.getText()));
        try {
            sizeService.add(s);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        loadTableSizeGiay();
        //        loadCBSize();
    }//GEN-LAST:event_btnThem6ActionPerformed

    private void btnSua6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua6ActionPerformed
        // TODO add your handling code here:
        Size s = new Size();
        s.setIdSize(UUID.fromString(txtIDSIZE.getText()));
        s.setMaSize(txtMaSIZE.getText());
        s.setSoSize(Integer.parseInt(txtSoSize.getText()));
        try {
            sizeService.update(s);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        loadTableSizeGiay();
        //        loadCBSize();
    }//GEN-LAST:event_btnSua6ActionPerformed

    private void btnXoa6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa6ActionPerformed
        // TODO add your handling code here:
        Size s = new Size();
        s.setIdSize(UUID.fromString(txtIDSIZE.getText()));
        s.setMaSize(txtMaSIZE.getText());
        s.setSoSize(Integer.parseInt(txtSoSize.getText()));
        try {
            sizeService.delete(s);
        } catch (Exception ex) {
            ex.printStackTrace();
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
        DeGiay dg = new DeGiay();
        dg.setMaCL(txtMaDe.getText());
        dg.setLoaiDe(txtLoaiDe.getText());
        dg.setMoTaDG(txtMoTaDe.getText());
        try {
            deGiayService.add(dg);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        loadTableDeGiay();
        //        loadCBDe();
    }//GEN-LAST:event_btnThem7ActionPerformed

    private void btnSua7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua7ActionPerformed
        // TODO add your handling code here:
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
        loadTableDeGiay();
        //        loadCBDe();
    }//GEN-LAST:event_btnSua7ActionPerformed

    private void btnXoa7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa7ActionPerformed
        // TODO add your handling code here:
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
    }//GEN-LAST:event_tblQLSPMouseClicked

    private void btnNext1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNext1ActionPerformed

    private void btnPrevious1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevious1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPrevious1ActionPerformed

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
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBanHang2;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnHoaDon;
    private javax.swing.JButton btnKhachHang;
    private javax.swing.JButton btnNext1;
    private javax.swing.JButton btnNhanVien;
    private javax.swing.JButton btnPrevious1;
    private javax.swing.JButton btnSanPham;
    private javax.swing.JButton btnSearch;
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
    private javax.swing.JButton btntaohd;
    private javax.swing.JButton btnthanhtoan;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btntimkiem;
    private javax.swing.JButton btnxacnhan;
    private javax.swing.JButton btnxoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbCL;
    private javax.swing.JComboBox<String> cbCL1;
    private javax.swing.JComboBox<String> cbDe;
    private javax.swing.JComboBox<String> cbDe1;
    private javax.swing.JComboBox<String> cbHang;
    private javax.swing.JComboBox<String> cbHang1;
    private javax.swing.JComboBox<String> cbLoai;
    private javax.swing.JComboBox<String> cbLoai1;
    private javax.swing.JComboBox<String> cbSIZE;
    private javax.swing.JComboBox<String> cbSIZE1;
    private javax.swing.JComboBox<String> cbTenSP;
    private javax.swing.JComboBox<String> cbTenSP1;
    private javax.swing.JComboBox<String> cboloaikh;
    private javax.swing.JLabel giamgia;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton3;
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
    private javax.swing.JComboBox<String> jComboBox7;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
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
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable8;
    private javax.swing.JTable jTable9;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField4;
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
    private javax.swing.JLabel lblgiamgia;
    private javax.swing.JLabel lblkhachcantra;
    private javax.swing.JLabel lblmanv;
    private javax.swing.JLabel lblngay;
    private javax.swing.JLabel lbltientralai;
    private javax.swing.JLabel lbltongtien;
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
    private javax.swing.JRadioButton rdNu;
    private javax.swing.JRadioButton rdoConHang;
    private javax.swing.JRadioButton rdoConHang1;
    private javax.swing.JRadioButton rdoHetHang;
    private javax.swing.JRadioButton rdoHetHang1;
    private javax.swing.JTable tbChatLieu;
    private javax.swing.JTable tbDeGiay;
    private javax.swing.JTable tbHang;
    private javax.swing.JTable tbLoaiGiay;
    private javax.swing.JTable tbSIZE;
    private javax.swing.JTable tbSP;
    private javax.swing.JTable tblKH;
    private javax.swing.JComboBox<String> tblLoaiThoiGian;
    private javax.swing.JTable tblQLSP;
    private javax.swing.JTable tbldssanpham;
    private javax.swing.JTable tblgiohang;
    private javax.swing.JTextArea txtDC;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtDonGia1;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtID1;
    private javax.swing.JTextField txtIDChatLieu;
    private javax.swing.JTextField txtIDDeGiay;
    private javax.swing.JTextField txtIDHang;
    private javax.swing.JTextField txtIDLoai;
    private javax.swing.JTextField txtIDSIZE;
    private javax.swing.JTextField txtIDsp;
    private javax.swing.JTextField txtIdKH;
    private javax.swing.JTextField txtLoaiDe;
    private javax.swing.JTextField txtMaChatLieu;
    private javax.swing.JTextField txtMaDe;
    private javax.swing.JTextField txtMaHang;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaLoai;
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
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearch2;
    private javax.swing.JTextField txtSoLuongSP;
    private javax.swing.JTextField txtSoLuongSP1;
    private javax.swing.JTextField txtSoSize;
    private javax.swing.JTextField txtTenChatLieu;
    private javax.swing.JTextField txtTenHang;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTenLoai;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTrongLuong;
    private javax.swing.JTextField txtTrongLuong1;
    private javax.swing.JTextField txtdiemtv;
    private javax.swing.JTextField txtghichu;
    private javax.swing.JTextField txtkhachtra;
    private javax.swing.JTextField txtmagiamgia;
    private javax.swing.JTextField txtphikhac;
    private javax.swing.JTextField txttMaSPP;
    private javax.swing.JTextField txttMaSPP1;
    private javax.swing.JTextField txttenkh;
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
