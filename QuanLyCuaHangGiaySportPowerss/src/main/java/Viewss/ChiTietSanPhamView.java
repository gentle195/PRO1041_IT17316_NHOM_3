/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Viewss;

import DomainModels.ChatLieu;
import DomainModels.ChiTietSP;
import DomainModels.DeGiay;
import DomainModels.HangGiay;
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
import Services.LoaiGiayService;
import Services.SanPhamService;
import Services.SizeService;
import ViewModels.ChiTietSPViewModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author dinhq
 */
public class ChiTietSanPhamView extends javax.swing.JPanel {

    private ChiTietSPServiceInterface chiTietSPService;
    private SanPhamServiceInterface SanPhamService;
    private ChatLieuServiceInterface chatLieuService;
    private HangGiayServiceInterface hangGiayService;
    private DeGiayServiceInterface deGiayService;
    private LoaiGiayServiceInterface loaiGiayService;
    private SizeServiceInterface sizeService;
    List<ChiTietSPViewModel> list;
    private DefaultComboBoxModel defaultComboBoxModel;
    long count;
    int trang;
    int sotrang = 1;
    int start = 0, end = 14;

    /**
     * Creates new form SanPham
     */
    public ChiTietSanPhamView() {
        initComponents();

        this.chiTietSPService = new ChiTietSPService();
        this.SanPhamService = new SanPhamService();
        this.chatLieuService = new ChatLieuService();
        this.hangGiayService = new HangGiayService();
        this.deGiayService = new DeGiayService();
        this.loaiGiayService = new LoaiGiayService();
        this.sizeService = new SizeService();
        this.chiTietSPService = new ChiTietSPService();
        list = chiTietSPService.all(start, end);

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

        // lọc sp
        List<DeGiay> dgg = deGiayService.all();
        dgg.add(0, new DeGiay(null, "", "Tất cả", ""));
        DefaultComboBoxModel de = new DefaultComboBoxModel((dgg.toArray()));
        cbbDe.setModel(de);

        List<HangGiay> hangg = hangGiayService.all();
        hangg.add(0, new HangGiay(null, "", "Tất cả", ""));
        DefaultComboBoxModel hg = new DefaultComboBoxModel((hangg.toArray()));
        cbbHang.setModel(hg);

        List<LoaiGiay> lgg = loaiGiayService.all();
        lgg.add(0, new LoaiGiay(null, "", "Tất cả", ""));
        DefaultComboBoxModel loaiModel = new DefaultComboBoxModel((lgg.toArray()));
        cbbLoai.setModel(loaiModel);

        List<Size> szz = sizeService.all();
        szz.add(0, new Size(null, "", "Tất cả"));
        DefaultComboBoxModel size = new DefaultComboBoxModel((szz.toArray()));
        cbbSize.setModel(size);

        List<ChatLieu> cll = chatLieuService.all();
        cll.add(0, new ChatLieu(null, "", "Tất cả", ""));
        DefaultComboBoxModel chatlieu = new DefaultComboBoxModel((cll.toArray()));
        cbbChatLieu.setModel(chatlieu);
//        List<ChiTietSPViewModel> ct = chiTietSPService.all();
//        cbbTrangThai.setModel(new DefaultComboBoxModel(ct.toArray()));
        AutoCompleteDecorator.decorate(cbCL);
        AutoCompleteDecorator.decorate(cbTenSP);
        AutoCompleteDecorator.decorate(cbDe);
        AutoCompleteDecorator.decorate(cbHang);
        AutoCompleteDecorator.decorate(cbSIZE);
        AutoCompleteDecorator.decorate(cbLoai);
        count = chiTietSPService.dem();
        lbTotalProducts1.setText("Total: " + count);
        loadTableChiTietSP(chiTietSPService.all(start, end));
    }

    private void setStatePagination() {
        btnPrevious1.setEnabled(start > 1);
        btnNext1.setEnabled(start < trang);
        lbPagination1.setText(sotrang + "/" + trang);
    }

    private void loadTableChiTietSP(List<ChiTietSPViewModel> Sz) {
        DefaultTableModel modeltb = new DefaultTableModel();

        modeltb = (DefaultTableModel) tblQLSP.getModel();
        modeltb.setRowCount(0);
        for (ChiTietSPViewModel x : Sz) {
            modeltb.addRow(new Object[]{
                x.getIdCTSP(), x.getSanPham().getMaSP(), x.getSanPham().getTenSP(), x.getSize(), x.getLoaigiay(), x.getHangGiay(), x.getDeGiay(),
                x.getChatlieu(), x.getSoLuong(), x.getDonGia(), x.getTrongLuong(),
                x.getTrangThai() == 0 ? "Còn Hàng" : "Hết Hàng",
                x.getMoTa()
            });

        }
        trang = (int) (count / end) + 1;
        setStatePagination();
    }

    private void locSP() {

        HangGiay IDHang = (HangGiay) cbbHang.getSelectedItem();
        LoaiGiay IDLoaiGiay = (LoaiGiay) cbbLoai.getSelectedItem();
        Size SizeID = (Size) cbbSize.getSelectedItem();
        DeGiay IDDe = (DeGiay) cbbDe.getSelectedItem();
        ChatLieu IDCL = (ChatLieu) cbbChatLieu.getSelectedItem();
        ChiTietSPViewModel ct = new ChiTietSPViewModel();
        List<ChiTietSPViewModel> l = chiTietSPService.loc(IDCL.getIdCL(), SizeID.getIdSize(), IDHang.getIdHang(), IDDe.getIdDG(), IDLoaiGiay.getIdLoai(), ct.getTrangThai());
        if (cbbHang.getSelectedIndex() == 0) {
            loadTableChiTietSP(chiTietSPService.all(start, end));
        }
        if (cbbLoai.getSelectedIndex() == 0) {
            loadTableChiTietSP(chiTietSPService.all(start, end));
        }
        if (cbbSize.getSelectedIndex() == 0) {
            loadTableChiTietSP(chiTietSPService.all(start, end));
        }
        if (cbbDe.getSelectedIndex() == 0) {
            loadTableChiTietSP(chiTietSPService.all(start, end));
        }
        if (cbbChatLieu.getSelectedIndex() == 0) {
            loadTableChiTietSP(chiTietSPService.all(start, end));
        }
        loadTableChiTietSP(l);
    }

    void clear() {
        txtDonGia.setText("");
        txtID.setText("");
        txtMaSP.setText("");
        txtMotaChiTietSP.setText("");
        txtSoLuongSP.setText("");
        txtTrongLuong.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
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
        txtMaSP = new javax.swing.JTextField();
        updatesp = new javax.swing.JButton();
        updatesp2 = new javax.swing.JButton();
        updatesp3 = new javax.swing.JButton();
        updatesp4 = new javax.swing.JButton();
        updatesp5 = new javax.swing.JButton();
        updatesp6 = new javax.swing.JButton();
        jPanel51 = new javax.swing.JPanel();
        txtSearch2 = new javax.swing.JTextField();
        jScrollPane20 = new javax.swing.JScrollPane();
        tblQLSP = new javax.swing.JTable();
        jPanel52 = new javax.swing.JPanel();
        lblDanhMuc7 = new javax.swing.JLabel();
        cbbSize = new javax.swing.JComboBox<>();
        lblDanhMuc8 = new javax.swing.JLabel();
        cbbLoai = new javax.swing.JComboBox<>();
        lblDanhMuc9 = new javax.swing.JLabel();
        cbbHang = new javax.swing.JComboBox<>();
        cbbDe = new javax.swing.JComboBox<>();
        lblDanhMuc10 = new javax.swing.JLabel();
        cbbChatLieu = new javax.swing.JComboBox<>();
        lblDanhMuc11 = new javax.swing.JLabel();
        lbTotalProducts1 = new javax.swing.JLabel();
        lbPagination1 = new javax.swing.JLabel();
        btnNext1 = new javax.swing.JButton();
        btnPrevious1 = new javax.swing.JButton();

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

        buttonGroup1.add(rdoConHang);
        rdoConHang.setText("Còn Hàng");
        rdoConHang.setEnabled(false);
        rdoConHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoConHangActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoHetHang);
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

        txtMaSP.setEditable(false);
        txtMaSP.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtMaSPCaretUpdate(evt);
            }
        });
        txtMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSPActionPerformed(evt);
            }
        });

        updatesp.setText("Update");
        updatesp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatespActionPerformed(evt);
            }
        });

        updatesp2.setText("Update");
        updatesp2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatesp2ActionPerformed(evt);
            }
        });

        updatesp3.setText("Update");
        updatesp3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatesp3ActionPerformed(evt);
            }
        });

        updatesp4.setText("Update");
        updatesp4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatesp4ActionPerformed(evt);
            }
        });

        updatesp5.setText("Update");
        updatesp5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatesp5ActionPerformed(evt);
            }
        });

        updatesp6.setText("Update");
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
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbSIZE, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbLoai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel31Layout.createSequentialGroup()
                                        .addComponent(lblMaSp5)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel31Layout.createSequentialGroup()
                                        .addComponent(lblMaSp26)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbDe, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(cbHang, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMaSp3)
                            .addComponent(lblMaSp6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbTenSP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(updatesp2)
                    .addComponent(updatesp3)
                    .addComponent(updatesp5)
                    .addComponent(updatesp4)
                    .addComponent(updatesp6)
                    .addComponent(updatesp))
                .addGap(174, 174, 174)
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
                            .addComponent(txtMaSP)
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
                        .addGap(18, 18, 18)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblMaSp3)
                                    .addComponent(cbTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbCL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMaSp2))
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel31Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(lblMaSp1)
                                        .addGap(11, 11, 11))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cbSIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMaSp4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMaSp5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMaSp26)))
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addComponent(updatesp, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(updatesp2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(updatesp3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addComponent(updatesp5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(updatesp4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(updatesp6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMaSp)
                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        txtSearch2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearch2CaretUpdate(evt);
            }
        });

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
        if (tblQLSP.getColumnModel().getColumnCount() > 0) {
            tblQLSP.getColumnModel().getColumn(1).setHeaderValue("Mã SP");
        }

        jPanel52.setBackground(new java.awt.Color(255, 255, 255));
        jPanel52.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 255, 255), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)), "Lọc", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        lblDanhMuc7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDanhMuc7.setText("Size");

        cbbSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSizeActionPerformed(evt);
            }
        });

        lblDanhMuc8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDanhMuc8.setText("Loại");

        cbbLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLoaiActionPerformed(evt);
            }
        });

        lblDanhMuc9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDanhMuc9.setText("Hãng");

        cbbHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbHangActionPerformed(evt);
            }
        });

        cbbDe.setName(""); // NOI18N
        cbbDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbDeActionPerformed(evt);
            }
        });

        lblDanhMuc10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDanhMuc10.setText("Đế");

        cbbChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbChatLieuActionPerformed(evt);
            }
        });

        lblDanhMuc11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDanhMuc11.setText("Chất Liệu");

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDanhMuc11, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDanhMuc10, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDanhMuc9, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDanhMuc8, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDanhMuc7, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbSize, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbHang, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbDe, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDanhMuc7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDanhMuc8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDanhMuc9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDanhMuc10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDanhMuc11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel51Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 974, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel51Layout.createSequentialGroup()
                                .addGap(334, 334, 334)
                                .addComponent(btnPrevious1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(65, 65, 65)
                                .addComponent(lbPagination1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(btnNext1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(lbTotalProducts1)))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel51Layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(txtSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel51Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrevious1)
                    .addComponent(btnNext1)
                    .addComponent(lbTotalProducts1)
                    .addComponent(lbPagination1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(65, 65, 65))
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
                .addContainerGap(61, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1306, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnlSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 792, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnlSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pnlSanPham.getAccessibleContext().setAccessibleName("pnlSanPham");
    }// </editor-fold>//GEN-END:initComponents

    private void txtDonGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDonGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDonGiaActionPerformed

    private void rdoConHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoConHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoConHangActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (txtMaSP.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập Mã");
            return;
        }
        if (txtDonGia.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đơn giá");
            return;
        }
        try {
            if (Double.parseDouble(txtDonGia.getText().toString()) < 0) {
                JOptionPane.showMessageDialog(this, "Đơn giá phải lớn hơn 0");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Đơn giá phải là số");
            return;
        }
        if (txtTrongLuong.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập trọng lượng");
            return;
        }
        try {
            if (Integer.parseInt(txtTrongLuong.getText().toString()) <= 0) {
                JOptionPane.showMessageDialog(this, "Trọng lượng phải lớn hơn 0");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Trọng lượng phải là số");
            return;
        }
        if (txtSoLuongSP.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng");
            return;
        }
        try {
            if (Integer.parseInt(txtSoLuongSP.getText().toString()) < 0) {
                JOptionPane.showMessageDialog(this, "Số lượng lượng không được âm");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là số");
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
            if (rdoConHang.isSelected()) {
                ctsp.setTrangThai(0);
            } else if (rdoHetHang.isSelected()) {
                ctsp.setTrangThai(1);
            }
            ctsp.setMoTa(txtMotaChiTietSP.getText());
            try {
                chiTietSPService.add(ctsp);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Thêm thành công");
        } else if (bb == JOptionPane.NO_OPTION) {
            return;
        } else {
            return;
        }
        clear();
        loadTableChiTietSP(chiTietSPService.all(start, end));

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        if (txtMaSP.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập Mã");
            return;
        }
        if (txtDonGia.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đơn giá");
            return;
        }
        try {
            if (Double.parseDouble(txtDonGia.getText().toString()) < 0) {
                JOptionPane.showMessageDialog(this, "Đơn giá phải lớn hơn 0");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Đơn giá phải là số");
            return;
        }
        if (txtTrongLuong.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập trọng lượng");
            return;
        }
        try {
            if (Integer.parseInt(txtTrongLuong.getText().toString()) <= 0) {
                JOptionPane.showMessageDialog(this, "Trọng lượng phải lớn hơn 0");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Trọng lượng phải là số");
            return;
        }
        if (txtSoLuongSP.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng");
            return;
        }
        try {
            if (Integer.parseInt(txtSoLuongSP.getText().toString()) <= 0) {
                JOptionPane.showMessageDialog(this, "Số lượng lượng không được âm");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là số");
            return;
        }
        if (txtMotaChiTietSP.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mô tả");
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
            if (rdoConHang.isSelected()) {
                ctsp.setTrangThai(0);
            } else if (rdoHetHang.isSelected()) {
                ctsp.setTrangThai(1);
            }
            ctsp.setMoTa(txtMotaChiTietSP.getText());
            try {
                chiTietSPService.delete(ctsp);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Xoá thành công");
        } else if (bb == JOptionPane.NO_OPTION) {
            return;
        } else {
            return;
        }
        clear();
        loadTableChiTietSP(chiTietSPService.all(start, end));

    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if (txtMaSP.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập Mã");
            return;
        }
        if (txtDonGia.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đơn giá");
            return;
        }
        try {
            if (Double.parseDouble(txtDonGia.getText().toString()) < 0) {
                JOptionPane.showMessageDialog(this, "Đơn giá phải lớn hơn 0");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Đơn giá phải là số");
            return;
        }
        if (txtTrongLuong.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập trọng lượng");
            return;
        }
        try {
            if (Integer.parseInt(txtTrongLuong.getText().toString()) <= 0) {
                JOptionPane.showMessageDialog(this, "Trọng lượng phải lớn hơn 0");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Trọng lượng phải là số");
            return;
        }
        if (txtSoLuongSP.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng");
            return;
        }
        try {
            if (Integer.parseInt(txtSoLuongSP.getText().toString()) <= 0) {
                JOptionPane.showMessageDialog(this, "Số lượng lượng không được âm");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là số");
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
            if (rdoConHang.isSelected()) {
                ctsp.setTrangThai(0);
            } else if (rdoHetHang.isSelected()) {
                ctsp.setTrangThai(1);
            }
            ctsp.setMoTa(txtMotaChiTietSP.getText());
            try {
                chiTietSPService.update(ctsp);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Sửa thành công");
        } else if (bb == JOptionPane.NO_OPTION) {
            return;
        } else {
            return;
        }
        clear();
        loadTableChiTietSP(chiTietSPService.all(start, end));

    }//GEN-LAST:event_btnSuaActionPerformed

    private void cbTenSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTenSPActionPerformed
        // TODO add your handling code here:
        List<SanPham> sp = SanPhamService.all();
        for (int j = 0; j < sp.size(); j++) {
            if (sp.get(j).getTenSP().equalsIgnoreCase(cbTenSP.getSelectedItem().toString())) {
                txtMaSP.setText(sp.get(j).getMaSP());

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
        if (txtSoLuongSP.getText().equalsIgnoreCase("")) {
            return;
        } else {
            if (Integer.parseInt(txtSoLuongSP.getText()) == 0) {
                rdoHetHang.setSelected(true);
            } else {
                rdoConHang.setSelected(true);
            }
        }
    }//GEN-LAST:event_txtSoLuongSPCaretUpdate

    private void txtMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSPActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtMaSPActionPerformed

    private void updatespActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatespActionPerformed

        SanPhamView sp = new SanPhamView();
        sp.setVisible(true);

        List<SanPham> sp1 = SanPhamService.all();
        cbTenSP.setModel(new DefaultComboBoxModel((sp1.toArray())));

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

    }//GEN-LAST:event_updatespActionPerformed

    private void updatesp2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatesp2ActionPerformed
        // TODO add your handling code here:
        ChatLieuView cl = new ChatLieuView();
        cl.setVisible(true);
        List<SanPham> sp = SanPhamService.all();
        cbTenSP.setModel(new DefaultComboBoxModel((sp.toArray())));

        List<ChatLieu> cl1 = chatLieuService.all();
        cbCL.setModel(new DefaultComboBoxModel((cl1.toArray())));

        List<DeGiay> dg = deGiayService.all();
        cbDe.setModel(new DefaultComboBoxModel((dg.toArray())));

        List<HangGiay> hang = hangGiayService.all();
        cbHang.setModel(new DefaultComboBoxModel((hang.toArray())));

        List<LoaiGiay> lg = loaiGiayService.all();
        cbLoai.setModel(new DefaultComboBoxModel((lg.toArray())));

        List<Size> sz = sizeService.all();
        cbSIZE.setModel(new DefaultComboBoxModel((sz.toArray())));
    }//GEN-LAST:event_updatesp2ActionPerformed

    private void updatesp3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatesp3ActionPerformed
        // TODO add your handling code here:
        SizeView s = new SizeView();
        s.setVisible(true);
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
    }//GEN-LAST:event_updatesp3ActionPerformed

    private void updatesp4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatesp4ActionPerformed
        // TODO add your handling code here:
        HangGiayView h = new HangGiayView();
        h.setVisible(true);
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
    }//GEN-LAST:event_updatesp4ActionPerformed

    private void updatesp5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatesp5ActionPerformed
        // TODO add your handling code here:
        LoaiSPView l = new LoaiSPView();
        l.setVisible(true);
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
    }//GEN-LAST:event_updatesp5ActionPerformed

    private void updatesp6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatesp6ActionPerformed
        // TODO add your handling code here:
        DeGiayView d = new DeGiayView();
        d.setVisible(true);
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
    }//GEN-LAST:event_updatesp6ActionPerformed

    private void btnNext1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext1ActionPerformed
        // TODO add your handling code here:
        if (start < trang) {
            start = start + 14;
            sotrang++;
        }
        loadTableChiTietSP(chiTietSPService.all(start, end));
    }//GEN-LAST:event_btnNext1ActionPerformed

    private void btnPrevious1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevious1ActionPerformed
        // TODO add your handling code here:
        if (start > 1) {
            start = start - 14;
            sotrang--;
        }
        loadTableChiTietSP(chiTietSPService.all(start, end));
    }//GEN-LAST:event_btnPrevious1ActionPerformed

    private void txtMaSPCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMaSPCaretUpdate
        // TODO add your handling code here:

    }//GEN-LAST:event_txtMaSPCaretUpdate

    private void txtSearch2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearch2CaretUpdate
        // TODO add your handling code here:
        List<ChiTietSPViewModel> ds = new ArrayList<>();
        for (ChiTietSPViewModel g : chiTietSPService.all(start, end)) {
            if (g.getSanPham().getTenSP().contains(txtSearch2.getText())) {
                ds.add(g);
            }
        }
        loadTableChiTietSP(ds);
    }//GEN-LAST:event_txtSearch2CaretUpdate

    private void cbbSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSizeActionPerformed
        // TODO add your handling code here:
//        if (cbbLoai.getSelectedIndex() == 0 && cbbSize.getSelectedIndex() == 0) {
//            loadTableChiTietSP(chiTietSPService.all());
//        } else {
//            List<ChiTietSPViewModel> ds = new ArrayList<>();
//            for (ChiTietSPViewModel g : chiTietSPService.all()) {
//                if (g.getLoaigiay().getTenLoai().equalsIgnoreCase(cbbLoai.getSelectedItem().toString()) && g.getSize().getSoSize().equalsIgnoreCase(cbbSize.getSelectedItem().toString())) {
//                    ds.add(g);
//                }
//            }
//            loadTableChiTietSP(ds);
//        }
        locSP();
    }//GEN-LAST:event_cbbSizeActionPerformed

    private void cbbLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLoaiActionPerformed
        // TODO add your handling code here:
//        if (cbbLoai.getSelectedIndex() == 0 && cbbSize.getSelectedIndex() == 0) {
//            loadTableChiTietSP(chiTietSPService.all());
//        } else {
//            List<ChiTietSPViewModel> ds = new ArrayList<>();
//            for (ChiTietSPViewModel g : chiTietSPService.all()) {
//                if (g.getLoaigiay().getTenLoai().equalsIgnoreCase(cbbLoai.getSelectedItem().toString()) && g.getSize().getSoSize().equalsIgnoreCase(cbbSize.getSelectedItem().toString())) {
//                    ds.add(g);
//                }
//            }
//            loadTableChiTietSP(ds);
//        }
        locSP();
    }//GEN-LAST:event_cbbLoaiActionPerformed

    private void cbbHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbHangActionPerformed
        // TODO add your handling code here:
        locSP();

//        if (cbbHang.getSelectedIndex() == 0) {
//            loadTableChiTietSP(chiTietSPService.all());
//        } else {
//            List<ChiTietSPViewModel> ds = new ArrayList<>();
//            for (ChiTietSPViewModel g : chiTietSPService.all()) {
//                if (g.getHangGiay().getTenHang().equalsIgnoreCase(cbbHang.getSelectedItem().toString())) {
//                    ds.add(g);
//                }
//            }
//            loadTableChiTietSP(ds);
//        }

    }//GEN-LAST:event_cbbHangActionPerformed

    private void cbbDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbDeActionPerformed
//        if (cbbDe.getSelectedIndex() == 0) {
//            loadTableChiTietSP(chiTietSPService.all());
//        } else {
//            List<ChiTietSPViewModel> ds = new ArrayList<>();
//            for (ChiTietSPViewModel g : chiTietSPService.all()) {
//                if (g.getDeGiay().getLoaiDe().equalsIgnoreCase(cbbDe.getSelectedItem().toString())) {
//                    ds.add(g);
//                }
//            }
//            loadTableChiTietSP(ds);
//        }   
        locSP();// TODO add your handling code here:
    }//GEN-LAST:event_cbbDeActionPerformed

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
//        cbTenSP.setSelectedItem(tblQLSP.getValueAt(row, 2).toString());
        if (tblQLSP.getValueAt(row, 2) == null) {
            JOptionPane.showMessageDialog(this, "Sản phẩm không tên ");
            cbTenSP.setSelectedIndex(0);
        } else {
            for (int j = 0; j < sp.size(); j++) {
                if (sp.get(j).getMaSP().equalsIgnoreCase(tblQLSP.getValueAt(row, 1).toString())) {
                    txtMaSP.setText(tblQLSP.getValueAt(row, 1).toString());
                }
            }
        }
        if (tblQLSP.getValueAt(row, 2) == null) {
            JOptionPane.showMessageDialog(this, "Sản phẩm không mã ");
            cbTenSP.setSelectedIndex(0);
        } else {
            for (int j = 0; j < sp.size(); j++) {
                if (sp.get(j).getTenSP().equalsIgnoreCase(tblQLSP.getValueAt(row, 2).toString())) {
                    cbTenSP.setSelectedIndex(j);
                }
            }
        }
        if (tblQLSP.getValueAt(row, 3) == null) {
            JOptionPane.showMessageDialog(this, "Sản phẩm không size ");
            cbSIZE.setSelectedIndex(0);
        } else {
            for (int j = 0; j < Sz.size(); j++) {
                if (Sz.get(j).getSoSize().equalsIgnoreCase(tblQLSP.getValueAt(row, 3).toString())) {
                    cbSIZE.setSelectedIndex(j);
                }
            }
        }
        if (tblQLSP.getValueAt(row, 4) == null) {
            JOptionPane.showMessageDialog(this, "Sản phẩm không loại ");
            cbLoai.setSelectedIndex(0);
        } else {
            for (int j = 0; j < lg.size(); j++) {
                if (lg.get(j).getTenLoai().equalsIgnoreCase(tblQLSP.getValueAt(row, 4).toString())) {
                    cbLoai.setSelectedIndex(j);
                }
            }
        }
        if (tblQLSP.getValueAt(row, 5) == null) {
            JOptionPane.showMessageDialog(this, "Sản phẩm không hãng");
            cbHang.setSelectedIndex(0);
        } else {
            for (int j = 0; j < hang.size(); j++) {
                if (hang.get(j).getTenHang().equalsIgnoreCase(tblQLSP.getValueAt(row, 5).toString())) {
                    cbHang.setSelectedIndex(j);
                }
            }
        }
        if (tblQLSP.getValueAt(row, 6) == null) {
            JOptionPane.showMessageDialog(this, "Sản phẩm không đế");
            cbDe.setSelectedIndex(0);
        } else {
            for (int j = 0; j < dg.size(); j++) {
                if (dg.get(j).getLoaiDe().equalsIgnoreCase(tblQLSP.getValueAt(row, 6).toString())) {
                    cbDe.setSelectedIndex(j);
                }
            }
        }
        if (tblQLSP.getValueAt(row, 7) == null) {
            JOptionPane.showMessageDialog(this, "Sản phẩm không chất liệu ");
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

    private void cbbChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbChatLieuActionPerformed
        // TODO add your handling code here:

//        if (cbbChatLieu.getSelectedIndex() == 0) {
//            loadTableChiTietSP(chiTietSPService.all());
//        } else {
//            List<ChiTietSPViewModel> ds = new ArrayList<>();
//            for (ChiTietSPViewModel g : chiTietSPService.all()) {
//                if (g.getChatlieu().getTenCL().equalsIgnoreCase(cbbChatLieu.getSelectedItem().toString())) {
//                    ds.add(g);
//                }
//            }
//            loadTableChiTietSP(ds);
//        }
        locSP();
    }//GEN-LAST:event_cbbChatLieuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNext1;
    private javax.swing.JButton btnPrevious1;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    public static javax.swing.JComboBox<String> cbCL;
    public static javax.swing.JComboBox<String> cbDe;
    public static javax.swing.JComboBox<String> cbHang;
    public static javax.swing.JComboBox<String> cbLoai;
    public static javax.swing.JComboBox<String> cbSIZE;
    public static javax.swing.JComboBox<String> cbTenSP;
    private javax.swing.JComboBox<String> cbbChatLieu;
    private javax.swing.JComboBox<String> cbbDe;
    private javax.swing.JComboBox<String> cbbHang;
    private javax.swing.JComboBox<String> cbbLoai;
    private javax.swing.JComboBox<String> cbbSize;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lbPagination1;
    private javax.swing.JLabel lbTotalProducts1;
    private javax.swing.JLabel lblDanhMuc10;
    private javax.swing.JLabel lblDanhMuc11;
    private javax.swing.JLabel lblDanhMuc7;
    private javax.swing.JLabel lblDanhMuc8;
    private javax.swing.JLabel lblDanhMuc9;
    private javax.swing.JLabel lblDonGia;
    private javax.swing.JLabel lblDonGia1;
    private javax.swing.JLabel lblDonGia3;
    private javax.swing.JLabel lblMaSp;
    private javax.swing.JLabel lblMaSp1;
    private javax.swing.JLabel lblMaSp2;
    private javax.swing.JLabel lblMaSp26;
    private javax.swing.JLabel lblMaSp3;
    private javax.swing.JLabel lblMaSp4;
    private javax.swing.JLabel lblMaSp5;
    private javax.swing.JLabel lblMaSp6;
    private javax.swing.JLabel lblMoTa;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JPanel pnlSanPham;
    private javax.swing.JRadioButton rdoConHang;
    private javax.swing.JRadioButton rdoHetHang;
    private javax.swing.JTable tblQLSP;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextArea txtMotaChiTietSP;
    private javax.swing.JTextField txtSearch2;
    private javax.swing.JTextField txtSoLuongSP;
    private javax.swing.JTextField txtTrongLuong;
    private javax.swing.JButton updatesp;
    private javax.swing.JButton updatesp2;
    private javax.swing.JButton updatesp3;
    private javax.swing.JButton updatesp4;
    private javax.swing.JButton updatesp5;
    private javax.swing.JButton updatesp6;
    // End of variables declaration//GEN-END:variables
}
