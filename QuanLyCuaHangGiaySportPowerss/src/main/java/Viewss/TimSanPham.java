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
public class TimSanPham extends javax.swing.JPanel {

    private ChiTietSPServiceInterface chiTietSPService;
    private SanPhamServiceInterface SanPhamService;
    private ChatLieuServiceInterface chatLieuService;
    private HangGiayServiceInterface hangGiayService;
    private DeGiayServiceInterface deGiayService;
    private LoaiGiayServiceInterface loaiGiayService;
    private SizeServiceInterface sizeService;
    List< ChiTietSPViewModel> list;
    private DefaultComboBoxModel defaultComboBoxModel;
    long count;
    int trang;
    int sotrang=1;
    int start = 0, end = 14;
    /**
     * Creates new form SanPham
     */
    public TimSanPham() {
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
        count = chiTietSPService.dem();
        lbTotalProducts1.setText("Total: " + count);
        loadTableChiTietSP(chiTietSPService.all(start, end));
        

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
    private void setStatePagination() {
        btnPrevious1.setEnabled(start > 1);
        btnNext1.setEnabled(start < trang);
        lbPagination1.setText(sotrang + "/" + trang);
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
        
        if (cbbTrangThai.getSelectedIndex() == 0) {
            loadTableChiTietSP(chiTietSPService.all(start, end));
        }
        if (cbbTrangThai.getSelectedIndex() == 1) {
            loadTableChiTietSP(l);
        } 
        if (cbbTrangThai.getSelectedIndex() == 2) {
            loadTableChiTietSP(l);

        }
        loadTableChiTietSP(l);
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
        jPanel51 = new javax.swing.JPanel();
        jScrollPane20 = new javax.swing.JScrollPane();
        tblQLSP = new javax.swing.JTable();
        jPanel52 = new javax.swing.JPanel();
        lblDonGia7 = new javax.swing.JLabel();
        lblTrangThai3 = new javax.swing.JLabel();
        lblDanhMuc7 = new javax.swing.JLabel();
        jComboBox12 = new javax.swing.JComboBox<>();
        cbbSize = new javax.swing.JComboBox<>();
        cbbTrangThai = new javax.swing.JComboBox<>();
        lblDanhMuc8 = new javax.swing.JLabel();
        cbbLoai = new javax.swing.JComboBox<>();
        lblDanhMuc9 = new javax.swing.JLabel();
        cbbHang = new javax.swing.JComboBox<>();
        cbbDe = new javax.swing.JComboBox<>();
        lblDanhMuc10 = new javax.swing.JLabel();
        cbbChatLieu = new javax.swing.JComboBox<>();
        lblDanhMuc11 = new javax.swing.JLabel();
        txtSearch2 = new javax.swing.JTextField();
        lblDanhMuc12 = new javax.swing.JLabel();
        lbTotalProducts1 = new javax.swing.JLabel();
        lbPagination1 = new javax.swing.JLabel();
        btnNext1 = new javax.swing.JButton();
        btnPrevious1 = new javax.swing.JButton();

        pnlSanPham.setBackground(new java.awt.Color(255, 255, 255));

        jPanel51.setBackground(new java.awt.Color(255, 255, 255));
        jPanel51.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(255, 255, 255), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)), "Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

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

        jComboBox12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox12ActionPerformed(evt);
            }
        });

        cbbSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSizeActionPerformed(evt);
            }
        });

        cbbTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Het Hang", "Con hang" }));
        cbbTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTrangThaiActionPerformed(evt);
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

        txtSearch2.setName(""); // NOI18N
        txtSearch2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearch2CaretUpdate(evt);
            }
        });

        lblDanhMuc12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDanhMuc12.setText("Tìm Kiếm");

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblDonGia7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox12, javax.swing.GroupLayout.Alignment.TRAILING, 0, 113, Short.MAX_VALUE)
                    .addComponent(cbbDe, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDanhMuc10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbbChatLieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTrangThai3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbTrangThai, 0, 113, Short.MAX_VALUE)
                    .addComponent(lblDanhMuc11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel52Layout.createSequentialGroup()
                        .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbbSize, 0, 113, Short.MAX_VALUE)
                            .addComponent(lblDanhMuc7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDanhMuc8, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel52Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(cbbHang, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblDanhMuc9, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel52Layout.createSequentialGroup()
                        .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSearch2, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                            .addComponent(lblDanhMuc12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDonGia7)
                    .addComponent(lblTrangThai3)
                    .addComponent(lblDanhMuc7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDanhMuc8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDanhMuc9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDanhMuc10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDanhMuc11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDanhMuc12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
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
                .addComponent(jScrollPane20)
                .addContainerGap())
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel51Layout.createSequentialGroup()
                .addContainerGap(418, Short.MAX_VALUE)
                .addComponent(btnPrevious1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(lbPagination1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(btnNext1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(lbTotalProducts1)
                .addGap(405, 405, 405))
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel51Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrevious1)
                    .addComponent(btnNext1)
                    .addComponent(lbTotalProducts1)
                    .addComponent(lbPagination1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(90, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlSanPhamLayout = new javax.swing.GroupLayout(pnlSanPham);
        pnlSanPham.setLayout(pnlSanPhamLayout);
        pnlSanPhamLayout.setHorizontalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSanPhamLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlSanPhamLayout.setVerticalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(180, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1452, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 126, Short.MAX_VALUE)
                    .addComponent(pnlSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 126, Short.MAX_VALUE)))
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
            start=start - 14;
            sotrang--;
        }
        loadTableChiTietSP(chiTietSPService.all(start, end));
    }//GEN-LAST:event_btnPrevious1ActionPerformed

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

    private void jComboBox12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox12ActionPerformed

    private void cbbTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTrangThaiActionPerformed
        // TODO add your handling code here:
        locSP();
    }//GEN-LAST:event_cbbTrangThaiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNext1;
    private javax.swing.JButton btnPrevious1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbChatLieu;
    private javax.swing.JComboBox<String> cbbDe;
    private javax.swing.JComboBox<String> cbbHang;
    private javax.swing.JComboBox<String> cbbLoai;
    private javax.swing.JComboBox<String> cbbSize;
    private javax.swing.JComboBox<String> cbbTrangThai;
    private javax.swing.JComboBox<String> jComboBox12;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JLabel lbPagination1;
    private javax.swing.JLabel lbTotalProducts1;
    private javax.swing.JLabel lblDanhMuc10;
    private javax.swing.JLabel lblDanhMuc11;
    private javax.swing.JLabel lblDanhMuc12;
    private javax.swing.JLabel lblDanhMuc7;
    private javax.swing.JLabel lblDanhMuc8;
    private javax.swing.JLabel lblDanhMuc9;
    private javax.swing.JLabel lblDonGia7;
    private javax.swing.JLabel lblTrangThai3;
    private javax.swing.JPanel pnlSanPham;
    private javax.swing.JTable tblQLSP;
    private javax.swing.JTextField txtSearch2;
    // End of variables declaration//GEN-END:variables
}
