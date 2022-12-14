/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Viewss;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import DomainModels.ChiTietSP;
import DomainModels.HoaDon;
import DomainModels.HoaDonChiTiet;
import DomainModels.KhachHang;
import Services.ChiTietSPService;

import Services.BanHangService;
import Services.HoaDonService;
import Services.Interface.ChiTietSPServiceInterface;
import Services.Interface.KhachHangServiceInterface;
import Services.Interface.NhanVienServiceInteface;
import Services.KhachHangService;
import Services.NhanVienService;
import ViewModels.ChiTietSPViewModel;
import ViewModels.HoaDonBanHangViewModel;
import ViewModels.HoaDonChiTietViewModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Services.Interface.BanHangServiceInterface;
import Services.Interface.HoaDonServiceInterface;
import static Viewss.QuanLyViews.txtMaNhanVien;
import static Viewss.QuanLyViews.txtSDTKhachHang;
import static Viewss.QuanLyViews.txttenkh;
import java.sql.SQLException;
import javax.swing.JPanel;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import java.text.SimpleDateFormat;

/**
 *
 * @author dinhq
 */
public class NhanVienBanHangViews extends javax.swing.JFrame {

    JPanel jPanel;

    private ChiTietSPServiceInterface chiTietSPService;
    private BanHangServiceInterface banHangService;
    private HoaDonServiceInterface hoaDonService;
    private NhanVienServiceInteface nvService;
    private KhachHangServiceInterface khachHangService;
    DefaultTableModel dtmSpBH = new DefaultTableModel();
    ArrayList<HoaDonChiTietViewModel> listhdct = new ArrayList<>();
    ArrayList<HoaDonChiTiet> listhdctt = new ArrayList<>();
    List<ChiTietSPViewModel> listsp;
    ChiTietSP CT = new ChiTietSP();
    long count;
    int trang;
    int sotrang = 1;
    int start = 0, end = 7;

    /**
     * Creates new form QuanLyViews
     */
    public NhanVienBanHangViews() {
        initComponents();
        this.chiTietSPService = new ChiTietSPService();
        this.banHangService = new BanHangService();
        this.hoaDonService = new HoaDonService();
        this.nvService = new NhanVienService();
        this.khachHangService = new KhachHangService();
        loadTableHoaDonBanHang();
        listsp = chiTietSPService.all(start, end);
        count = chiTietSPService.dem();
        loadTableChiTietSPBH(chiTietSPService.all(start, end));

    }

    private String hinhThucThanhToan() {
        if (rdTienMat.isSelected()) {
            return "Ti???n m???t";
        } else {
            return "Chuy???n kho???n";
        }
    }

    @SuppressWarnings("unchecked")
    private void loadTableHoaDonBanHang() {
        DefaultTableModel modeltb = new DefaultTableModel();
        List<HoaDonBanHangViewModel> list = banHangService.allHoaDonCho();
        modeltb = (DefaultTableModel) tbHoaDonBanHang.getModel();
        modeltb.setRowCount(0);
        int g = 1;
        for (HoaDonBanHangViewModel hoaDon : list) {
            modeltb.addRow(new Object[]{
                g++, hoaDon.getMaHD(),
                hoaDon.getNgayTao(),
                hoaDon.getTinhTrang() == 0 ? "Ch??? x??? l??" : "???? thanh to??n"});
        }
    }

    private void loadTableChiTietSPBH(List<ChiTietSPViewModel> Sz) {

        dtmSpBH = (DefaultTableModel) tbldssanpham.getModel();
        dtmSpBH.setRowCount(0);
        int r = 1;
        for (ChiTietSPViewModel x : Sz) {
            dtmSpBH.addRow(new Object[]{
                r++, x.getSanPham().getMaSP(), x.getSanPham().getTenSP(), x.getDonGia(),
                x.getSoLuong(), x.getTrangThai() == 0 ? "C??n H??ng" : "H???t H??ng"});
        }
        trang = (int) (count / end) + 1;
        setStatePagination();
    }

    private void setStatePagination() {
        btnback.setEnabled(start > 1);
        btnnext.setEnabled(start < trang);
        jTrang.setText(sotrang + "/" + trang);
    }

    private void addTableGioHang(ArrayList<HoaDonChiTietViewModel> list) {
        DefaultTableModel modeltb = new DefaultTableModel();
        modeltb = (DefaultTableModel) tblgiohang.getModel();
        int h = 1;
        modeltb.setRowCount(0);
        for (HoaDonChiTietViewModel chiTietHoaDonViewModel : list) {
            modeltb.addRow(new Object[]{
                h++, chiTietHoaDonViewModel.getMaSP(), chiTietHoaDonViewModel.getTenSP(), chiTietHoaDonViewModel.getSoLuong(), chiTietHoaDonViewModel.getDonGia(),
                chiTietHoaDonViewModel.getSoLuong() * chiTietHoaDonViewModel.getDonGia().doubleValue()
            });
        }
    }

    public static final String pathUnicode = "font\\unicode.ttf";

    public void exportBill() {
//        int row = tblgiohang.getSelectedRow();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String date = sdf.format(new Date());
            String path = "hoa_don" + date + ".pdf";
            PdfWriter pdfWriter = new PdfWriter(path);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);
            float col = 280f;
            float columWidth[] = {col, col};

            PdfFont font = PdfFontFactory.createFont(pathUnicode, BaseFont.IDENTITY_H);

            Table table = new Table(columWidth);
            table.setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE);
            table.setFont(font);

            table.addCell(new Cell().add("Bill SPORT-POWERS").setTextAlignment(TextAlignment.CENTER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setMarginTop(30f)
                    .setMarginBottom(30f)
                    .setFontSize(30f)
                    .setBorder(Border.NO_BORDER));
            table.addCell(new Cell().add("M?? h??a ????n: "
                    + txtMaHdBH.getText()).setTextAlignment(TextAlignment.RIGHT)
                    .setMarginTop(30f)
                    .setMarginBottom(30f)
                    .setBorder(Border.NO_BORDER)
                    .setMarginRight(10f));

            float colWidth[] = {80, 250, 200, 200};
            Table customerInforTable = new Table(colWidth);
            customerInforTable.setFont(font);
            customerInforTable.addCell(new Cell(0, 4)
                    .add("Th??ng tin kh??ch h??ng").setBold().setBorder(Border.NO_BORDER));

            customerInforTable.addCell(new Cell().add("H??? t??n:").setBorder(Border.NO_BORDER));
            customerInforTable.addCell(new Cell().add(txttenkh.getText()).setBorder(Border.NO_BORDER));
            customerInforTable.addCell(new Cell().add("S??? ??i???n tho???i:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            customerInforTable.addCell(new Cell().add(txtSDTKhachHang.getText()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            customerInforTable.addCell(new Cell().add("HT Thanh To??n:").setBorder(Border.NO_BORDER));
            customerInforTable.addCell(new Cell().add(hinhThucThanhToan()).setBorder(Border.NO_BORDER));
            customerInforTable.addCell(new Cell().add("Ng??y thanh to??n:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            customerInforTable.addCell(new Cell().add(LBtime.getText()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));

            float itemColWidth[] = {15, 110, 110, 110, 110, 110};
            Table itemTable = new Table(itemColWidth);
            itemTable.setFont(font);
            itemTable.addCell(new Cell().add("STT").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE));
            itemTable.addCell(new Cell().add("M?? S???n Ph???m").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE));
            itemTable.addCell(new Cell().add("T??n S???n Ph???m").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE));
            itemTable.addCell(new Cell().add("S??? l?????ng").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE));
            itemTable.addCell(new Cell().add("Gi?? b??n").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE));
            itemTable.addCell(new Cell().add("Th??nh ti???n").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE));

//            itemTable.addCell(new Cell().add("1"));
            for (int i = 0; i < tblgiohang.getRowCount(); i++) {
                itemTable.addCell(new Cell().add(tblgiohang.getValueAt(i, 0).toString()));
                itemTable.addCell(new Cell().add(tblgiohang.getValueAt(i, 1).toString()));
                itemTable.addCell(new Cell().add(tblgiohang.getValueAt(i, 2).toString()));
                itemTable.addCell(new Cell().add(tblgiohang.getValueAt(i, 3).toString()));
                itemTable.addCell(new Cell().add(tblgiohang.getValueAt(i, 4).toString()));
                itemTable.addCell(new Cell().add(tblgiohang.getValueAt(i, 5).toString()));

            }

//            itemTable.addCell(new Cell().add("Vans M??u tr???ng Size 40"));
//            itemTable.addCell(new Cell().add("2"));
//            itemTable.addCell(new Cell().add("250,000 Vn??"));
//            itemTable.addCell(new Cell().add("500,000 Vn??"));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("T???ng ti???n").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
            itemTable.addCell(new Cell().add(txtThanhTien1.getText()).setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));

            document.add(table);
            document.add(new Paragraph("\n"));
            document.add(customerInforTable);
            document.add(new Paragraph("\n"));
            document.add(itemTable);
            document.close();
            System.out.println("Export successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnBanHang2 = new javax.swing.JButton();
        btnHoaDon = new javax.swing.JButton();
        btnSanPham = new javax.swing.JButton();
        btnKhachHang = new javax.swing.JButton();
        btnNhanVien = new javax.swing.JButton();
        btnDangXuat = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        panelTong = new javax.swing.JPanel();
        pnlBanHang = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblgiohang = new javax.swing.JTable();
        btnxoa = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        btnnext = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbldssanpham = new javax.swing.JTable();
        txttimkiem = new javax.swing.JTextField();
        jTrang = new javax.swing.JLabel();
        btnthem = new javax.swing.JButton();
        btnback = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane22 = new javax.swing.JScrollPane();
        tbHoaDonBanHang = new javax.swing.JTable();
        jPanel54 = new javax.swing.JPanel();
        txttenkh = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        btnxacnhan1 = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMaHdBH = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        rdTienMat = new javax.swing.JRadioButton();
        rdChuyenKhoan = new javax.swing.JRadioButton();
        btnthanhtoan2 = new javax.swing.JButton();
        txtGhiChu1 = new javax.swing.JTextField();
        txtTienDu1 = new javax.swing.JLabel();
        txtKhachTra1 = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        LBtime = new javax.swing.JLabel();
        btnhuyhd = new javax.swing.JButton();
        btntaohd1 = new javax.swing.JButton();
        txtThanhTien1 = new javax.swing.JTextField();
        txtSDTKhachHang = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtMaNhanVienbh = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setPreferredSize(new java.awt.Dimension(210, 549));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setMaximumSize(new java.awt.Dimension(240, 130));
        jLabel1.setMinimumSize(new java.awt.Dimension(240, 130));
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-25, 6, -1, -1));

        btnBanHang2.setBackground(new java.awt.Color(255, 255, 204));
        btnBanHang2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnBanHang2.setText("B??n H??ng");
        btnBanHang2.setPreferredSize(new java.awt.Dimension(170, 50));
        btnBanHang2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanHang2ActionPerformed(evt);
            }
        });
        jPanel1.add(btnBanHang2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        btnHoaDon.setBackground(new java.awt.Color(255, 255, 204));
        btnHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnHoaDon.setText("H??a ????n");
        btnHoaDon.setPreferredSize(new java.awt.Dimension(170, 50));
        btnHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoaDonActionPerformed(evt);
            }
        });
        jPanel1.add(btnHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, -1));

        btnSanPham.setBackground(new java.awt.Color(255, 255, 204));
        btnSanPham.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnSanPham.setText("T??m ki???m s???n ph???m");
        btnSanPham.setPreferredSize(new java.awt.Dimension(170, 50));
        btnSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSanPhamActionPerformed(evt);
            }
        });
        jPanel1.add(btnSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, -1, -1));

        btnKhachHang.setBackground(new java.awt.Color(255, 255, 204));
        btnKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnKhachHang.setText("Kh??ch h??ng");
        btnKhachHang.setPreferredSize(new java.awt.Dimension(170, 50));
        btnKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhachHangActionPerformed(evt);
            }
        });
        jPanel1.add(btnKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, -1, -1));

        btnNhanVien.setBackground(new java.awt.Color(255, 255, 204));
        btnNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnNhanVien.setText("Th??ng tin c?? nh??n");
        btnNhanVien.setPreferredSize(new java.awt.Dimension(170, 50));
        btnNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanVienActionPerformed(evt);
            }
        });
        jPanel1.add(btnNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 590, -1, -1));

        btnDangXuat.setBackground(new java.awt.Color(255, 255, 204));
        btnDangXuat.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnDangXuat.setText("????ng xu???t");
        btnDangXuat.setPreferredSize(new java.awt.Dimension(170, 50));
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });
        jPanel1.add(btnDangXuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 690, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon("D:\\GitHub\\PRO1041_IT17316_NHOM_3\\QuanLyCuaHangGiaySportPowers\\PRO1041_IT17316_NHOM_3\\QuanLyCuaHangGiaySportPowerss\\src\\main\\java\\icon\\rsz_21artboard_1.png")); // NOI18N
        jLabel2.setText("???nh");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 0, 270, 120));

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_START);

        panelTong.setBackground(new java.awt.Color(255, 255, 255));
        panelTong.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelTong.setLayout(new java.awt.CardLayout());

        pnlBanHang.setBackground(new java.awt.Color(255, 255, 255));
        pnlBanHang.setPreferredSize(new java.awt.Dimension(1220, 750));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setPreferredSize(new java.awt.Dimension(1070, 760));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Gi??? H??ng"));

        tblgiohang.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "M?? s???n ph???m", "T??n s???n ph???m", "S??? l?????ng", "????n gi??", "Th??nh ti???n"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

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

        btnxoa.setBackground(new java.awt.Color(204, 204, 204));
        btnxoa.setText("S???a s??? l?????ng");
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnxoa)
                .addGap(11, 11, 11))
        );

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "S???n Ph???m"));

        btnnext.setText(">");
        btnnext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnextActionPerformed(evt);
            }
        });

        tbldssanpham.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "M?? s???n ph???m", "T??n S???n ph???m", "????n gi??", "S??? L?????ng", "Tr???ng th??i"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Float.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false
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

        txttimkiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txttimkiemCaretUpdate(evt);
            }
        });

        jTrang.setText("1/3");

        btnthem.setBackground(new java.awt.Color(204, 204, 204));
        btnthem.setText("Th??m v??o gi??? H??ng");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        btnback.setText("<");
        btnback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbackActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setText("T??n SP");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnback, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5)))
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnback)
                    .addComponent(jTrang)
                    .addComponent(btnnext)
                    .addComponent(btnthem))
                .addContainerGap())
        );

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Ho?? ????n"));

        tbHoaDonBanHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "M??", "Ng??y T???o", "T??nh Tr???ng"
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
                .addContainerGap()
                .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 42, Short.MAX_VALUE))
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
        jPanel54.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Th??ng tin chung", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        txttenkh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttenkhActionPerformed(evt);
            }
        });

        jLabel28.setText("T??n KH");

        jLabel31.setText("Ng??y T???o");

        btnxacnhan1.setBackground(new java.awt.Color(204, 204, 204));
        btnxacnhan1.setText("L???y th??ng tin");
        btnxacnhan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxacnhan1ActionPerformed(evt);
            }
        });

        jLabel32.setText("M?? nh??n vi??n");

        jLabel6.setText("M?? h??a ????n");

        txtMaHdBH.setEditable(false);

        jLabel51.setText("H??nh th???c TT");

        rdTienMat.setText("Ti???n m???t");

        rdChuyenKhoan.setText("Chuy???n kho???n");

        btnthanhtoan2.setBackground(new java.awt.Color(204, 204, 204));
        btnthanhtoan2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnthanhtoan2.setText("Thanh To??n");
        btnthanhtoan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthanhtoan2ActionPerformed(evt);
            }
        });

        txtTienDu1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTienDu1.setForeground(new java.awt.Color(0, 0, 153));
        txtTienDu1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        txtKhachTra1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtKhachTra1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtKhachTra1CaretUpdate(evt);
            }
        });

        jLabel59.setText("Th??nh ti???n");

        jLabel60.setText("Ti???n kh??ch tr???");

        jLabel61.setText("Ti???n d??");

        jLabel62.setText("Ghi ch??");

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

        btnhuyhd.setBackground(new java.awt.Color(204, 204, 204));
        btnhuyhd.setText("H???y h??a ????n");
        btnhuyhd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhuyhdActionPerformed(evt);
            }
        });

        btntaohd1.setBackground(new java.awt.Color(204, 204, 204));
        btntaohd1.setText("T???o h??a ????n");
        btntaohd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntaohd1ActionPerformed(evt);
            }
        });

        txtThanhTien1.setEditable(false);
        txtThanhTien1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtThanhTien1.setForeground(new java.awt.Color(255, 51, 51));
        txtThanhTien1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtThanhTien1CaretUpdate(evt);
            }
        });

        txtSDTKhachHang.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSDTKhachHangCaretUpdate(evt);
            }
        });
        txtSDTKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTKhachHangActionPerformed(evt);
            }
        });

        jLabel29.setText("S??T");

        txtMaNhanVienbh.setEditable(false);

        javax.swing.GroupLayout jPanel54Layout = new javax.swing.GroupLayout(jPanel54);
        jPanel54.setLayout(jPanel54Layout);
        jPanel54Layout.setHorizontalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(btntaohd1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnhuyhd)
                .addGap(72, 72, 72))
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel54Layout.createSequentialGroup()
                        .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel54Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(txtMaHdBH, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel54Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMaNhanVienbh, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel54Layout.createSequentialGroup()
                        .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel51)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel54Layout.createSequentialGroup()
                                .addComponent(rdTienMat)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdChuyenKhoan))
                            .addGroup(jPanel54Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txttenkh, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addComponent(btnxacnhan1))
                            .addComponent(txtThanhTien1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKhachTra1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52)))
                .addContainerGap())
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addContainerGap()
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
                            .addComponent(txtTienDu1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel54Layout.createSequentialGroup()
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LBtime, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSDTKhachHang)
                .addGap(160, 160, 160))
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addComponent(btnthanhtoan2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel54Layout.setVerticalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtMaHdBH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(txtMaNhanVienbh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(LBtime))
                .addGap(18, 18, 18)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttenkh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28)
                    .addComponent(btnxacnhan1))
                .addGap(12, 12, 12)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDTKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addGap(18, 18, 18)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdTienMat)
                    .addComponent(rdChuyenKhoan)
                    .addComponent(jLabel51))
                .addGap(25, 25, 25)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(txtThanhTien1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(txtKhachTra1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienDu1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel62)
                    .addComponent(txtGhiChu1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(btnthanhtoan2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btntaohd1)
                    .addComponent(btnhuyhd))
                .addContainerGap(98, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlBanHangLayout = new javax.swing.GroupLayout(pnlBanHang);
        pnlBanHang.setLayout(pnlBanHangLayout);
        pnlBanHangLayout.setHorizontalGroup(
            pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBanHangLayout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 789, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel54, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
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

        panelTong.add(pnlBanHang, "card2");
        pnlBanHang.getAccessibleContext().setAccessibleName("jpBanHang");

        getContentPane().add(panelTong, java.awt.BorderLayout.CENTER);
        panelTong.getAccessibleContext().setAccessibleName("panelTong");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanPhamActionPerformed
        // TODO add your handling code here:
        jPanel = new TimSanPham();
        panelTong.removeAll();
        panelTong.add(jPanel);
        panelTong.validate();

    }//GEN-LAST:event_btnSanPhamActionPerformed

    private void btnHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoaDonActionPerformed
        // TODO add your handling code here:
        jPanel = new HoaDonView();
        panelTong.removeAll();
        panelTong.add(jPanel);
        panelTong.validate();
    }//GEN-LAST:event_btnHoaDonActionPerformed

    private void btnKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhachHangActionPerformed
        // TODO add your handling code here:
        jPanel = new KhachHang2Views();
        panelTong.removeAll();
        panelTong.add(jPanel);
        panelTong.validate();
    }//GEN-LAST:event_btnKhachHangActionPerformed

    private void btnBanHang2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanHang2ActionPerformed
        // TODO add your handling code here:
        jPanel = pnlBanHang;
        panelTong.removeAll();
        panelTong.add(jPanel);
        panelTong.validate();
        panelTong.repaint();
        loadTableChiTietSPBH(chiTietSPService.all(start, end));
        loadTableHoaDonBanHang();
    }//GEN-LAST:event_btnBanHang2ActionPerformed

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        // TODO add your handling code here:
        int check = JOptionPane.showConfirmDialog(this, "B???n c?? mu???n ????ng xu???t kh??ng", "????ng xu???t", JOptionPane.YES_NO_OPTION);
        if (check != JOptionPane.YES_OPTION) {
            return;
        }
        giaoDienDangNhap gd = new giaoDienDangNhap();
        gd.setVisible(true);
        this.dispose();
        JOptionPane.showMessageDialog(this, "B???n ???? ????ng xu???t");
    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void tblgiohangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblgiohangMouseClicked
        // TODO add your handling code here:
        //        int ro = tblgiohang.getSelectedRow();
        //        txtThanhTien1.setText(tblgiohang.getValueAt(ro, 4).toString());
    }//GEN-LAST:event_tblgiohangMouseClicked

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        // TODO add your handling code here:
        int row = tblgiohang.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "M???i ch???n s???n ph???m c???n s???a");
            return;
        }
        String sl = JOptionPane.showInputDialog("M???i nh???p s??? l?????ng");

        try {

            if (sl.equals("")) {
                return;
            } else if (Integer.valueOf(sl) < 0) {
                JOptionPane.showMessageDialog(this, "S??? l?????ng nh???p ph???i l???n h??n 0");
            } else {
                int slm = Integer.valueOf(sl);
                int thanhtien = 0;
                HoaDonChiTiet hd = new HoaDonChiTiet();

                try {
                    if (slm < Integer.parseInt(tblgiohang.getValueAt(row, 3).toString())) {
                        for (int i = 0; i < listhdct.size(); i++) {
                            if (listhdct.get(i).getMaSP().equals(tblgiohang.getValueAt(row, 1))) {
                                listhdct.get(i).setMaSP(tblgiohang.getValueAt(row, 1).toString());
                                listhdct.get(i).setTenSP(tblgiohang.getValueAt(row, 2).toString());
                                listhdct.get(i).setDonGia((BigDecimal) tblgiohang.getValueAt(row, 4));
                                listhdct.get(i).setSoLuong(Integer.parseInt(tblgiohang.getValueAt(row, 3).toString()) - slm);
                                hd.setSoLuong(listhdct.get(i).getSoLuong());
                                banHangService.updateSoLuong(tblgiohang.getValueAt(row, 1).toString(), hd);
                                thanhtien = thanhtien + (listhdct.get(i).getSoLuong() * Integer.parseInt(listhdct.get(i).getDonGia().toString()));
                                txtThanhTien1.setText((String.valueOf(thanhtien)));
                                for (int j = 0; j < listsp.size(); j++) {
                                    if (listsp.get(j).getMaSP().equals(tblgiohang.getValueAt(row, 1))) {
                                        listsp.get(j).setSoLuong(CT.getSoLuong() + slm);
                                        CT.setSoLuong(listsp.get(j).getSoLuong());
                                        CT.setTrangThai(0);
                                        chiTietSPService.updatesl(CT, tblgiohang.getValueAt(row, 1).toString());
                                    }
                                }
                                loadTableChiTietSPBH(chiTietSPService.all(start, end));
                                addTableGioHang(listhdct);
                            }
                        }
                    } else if (slm == Integer.parseInt(tblgiohang.getValueAt(row, 3).toString())) {
                        for (int i = 0; i < listhdct.size(); i++) {
                            banHangService.deleteSoLuong(tblgiohang.getValueAt(row, 1).toString());
                            if (listhdct.get(i).getMaSP().equals(tblgiohang.getValueAt(row, 1))) {
                                listhdct.remove(i);
                                for (int j = 0; j < listsp.size(); j++) {
                                    if (listsp.get(j).getMaSP().equals(tblgiohang.getValueAt(row, 1))) {
                                        listsp.get(j).setSoLuong(CT.getSoLuong() + slm);
                                        CT.setSoLuong(listsp.get(j).getSoLuong());
                                        CT.setTrangThai(0);
                                        chiTietSPService.updatesl(CT, tblgiohang.getValueAt(row, 1).toString());
                                    }
                                }
                            }
                            loadTableChiTietSPBH(chiTietSPService.all(start, end));
                            addTableGioHang(listhdct);
                        }
                        if (tblgiohang.getRowCount() == 0) {
                            txtThanhTien1.setText("0");
                        }
                        loadTableChiTietSPBH(chiTietSPService.all(start, end));
                        addTableGioHang(listhdct);
                    } else if (slm > Integer.parseInt(tblgiohang.getValueAt(row, 3).toString())) {
                        JOptionPane.showMessageDialog(this, "S??? l?????ng nh???p l???n h??n trong gi??? h??ng");
                    } else {
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ch??a nh???p ????ng s??? l?????ng");
            return;
        }
    }//GEN-LAST:event_btnxoaActionPerformed

    private void tbldssanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbldssanphamMouseClicked

    }//GEN-LAST:event_tbldssanphamMouseClicked

    private void txttimkiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txttimkiemCaretUpdate
        // TODO add your handling code here:
        List<ChiTietSPViewModel> sp = new ArrayList<>();
        for (ChiTietSPViewModel s : chiTietSPService.all(start, end)) {
            if (s.getSanPham().getTenSP().contains(txttimkiem.getText())) {
                sp.add(s);
            }
        }
        loadTableChiTietSPBH(sp);
    }//GEN-LAST:event_txttimkiemCaretUpdate

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        // TODO add your handling code here:
        int row = this.tbldssanpham.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "M???i ch???n s???n ph???m c???n th??m");
            return;
        }
        if (txtMaHdBH.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "M???i ch???n h??a ????n");
        } else {
            int ro = tblgiohang.getSelectedRow();
            int r = tbHoaDonBanHang.getSelectedRow();
            int thanhtien = 0;
            int slsp = 0;
            HoaDonChiTietViewModel chiTietHoaDonViewModel = new HoaDonChiTietViewModel();
            String sl = JOptionPane.showInputDialog("M???i nh???p s??? l?????ng");
            try {
                if (sl.equals("")) {
                    return;
                } else if (Integer.valueOf(sl) < 0) {
                    JOptionPane.showMessageDialog(this, "S??? l?????ng nh???p ph???i l???n h??n 0");
                } else if (Integer.valueOf(sl) >= 0) {
                    int slm = Integer.valueOf(sl);
                    if (slm > Integer.parseInt(tbldssanpham.getValueAt(row, 4).toString())) {
                        JOptionPane.showMessageDialog(this, "S??? l?????ng nh???p l???n h??n s??? l?????ng t???n trong kho");
                    } else if (slm == Integer.parseInt(tbldssanpham.getValueAt(row, 4).toString())) {
                        CT.setSoLuong(Integer.parseInt(tbldssanpham.getValueAt(row, 4).toString()));
                        CT.setSoLuong(CT.getSoLuong() - slm);
                        CT.setTrangThai(1);
                        HoaDonChiTiet hd = new HoaDonChiTiet();
                        try {
                            chiTietSPService.updatesl(CT, tbldssanpham.getValueAt(row, 1).toString());
                            chiTietHoaDonViewModel.setMaSP((String) tbldssanpham.getValueAt(row, 1));
                            chiTietHoaDonViewModel.setTenSP((String) tbldssanpham.getValueAt(row, 2));
                            chiTietHoaDonViewModel.setSoLuong(Integer.valueOf(sl));
                            chiTietHoaDonViewModel.setDonGia((BigDecimal) tbldssanpham.getValueAt(row, 3));
                            hd.setSoLuong(Integer.valueOf(sl));
                            hd.setDonGia((BigDecimal) tbldssanpham.getValueAt(row, 3));
                            listhdct.add(chiTietHoaDonViewModel);
                            listhdctt.add(hd);
                            banHangService.addSanPham(tbldssanpham.getValueAt(row, 1).toString(), tbHoaDonBanHang.getValueAt(r, 1).toString(), hd);
                            addTableGioHang(listhdct);
                            loadTableChiTietSPBH(chiTietSPService.all(start, end));
                            if (tblgiohang.getRowCount() > 0) {
                                for (int i = 0; i < listhdct.size(); i++) {
                                    thanhtien = thanhtien + (listhdct.get(i).getSoLuong() * Integer.parseInt(listhdct.get(i).getDonGia().toString()));
                                }
                                txtThanhTien1.setText((String.valueOf(thanhtien)));
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        CT.setSoLuong(Integer.parseInt(tbldssanpham.getValueAt(row, 4).toString()));
                        CT.setSoLuong(CT.getSoLuong() - slm);
                        CT.setTrangThai(0);
                        HoaDonChiTiet hd = new HoaDonChiTiet();
                        try {
                            chiTietSPService.updatesl(CT, tbldssanpham.getValueAt(row, 1).toString());
                            chiTietHoaDonViewModel.setMaSP((String) tbldssanpham.getValueAt(row, 1));
                            chiTietHoaDonViewModel.setTenSP((String) tbldssanpham.getValueAt(row, 2));
                            chiTietHoaDonViewModel.setSoLuong(Integer.valueOf(sl));
                            chiTietHoaDonViewModel.setDonGia((BigDecimal) tbldssanpham.getValueAt(row, 3));
                            hd.setSoLuong(Integer.valueOf(sl));
                            hd.setDonGia((BigDecimal) tbldssanpham.getValueAt(row, 3));
                            listhdct.add(chiTietHoaDonViewModel);
                            listhdctt.add(hd);
                            banHangService.addSanPham(tbldssanpham.getValueAt(row, 1).toString(), tbHoaDonBanHang.getValueAt(r, 1).toString(), hd);
                            addTableGioHang(listhdct);
                            loadTableChiTietSPBH(chiTietSPService.all(start, end));
                            if (tblgiohang.getRowCount() > 0) {
                                for (int i = 0; i < listhdct.size(); i++) {
                                    thanhtien = thanhtien + (listhdct.get(i).getSoLuong() * Integer.parseInt(listhdct.get(i).getDonGia().toString()));
                                }
                                txtThanhTien1.setText((String.valueOf(thanhtien)));
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ch??a nh???p ????ng s??? l?????ng");
                return;
            }

        }
    }//GEN-LAST:event_btnthemActionPerformed

    private void tbHoaDonBanHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHoaDonBanHangMouseClicked
        // TODO add your handling code here:
        int row = tbHoaDonBanHang.getSelectedRow();
        txtMaHdBH.setText(tbHoaDonBanHang.getValueAt(row, 1).toString());
        LBtime.setText(tbHoaDonBanHang.getValueAt(row, 2).toString());

        int row1 = tbHoaDonBanHang.getSelectedRow();
        HoaDonBanHangViewModel hoaDon = banHangService.allHoaDonCho().get(row1);

        try {
            listhdct = (ArrayList<HoaDonChiTietViewModel>) banHangService.getListById(hoaDon.getMaHD());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        int thanhtien = 0;
        if (tblgiohang.getRowCount() > 0) {
            for (int i = 0; i < listhdct.size(); i++) {
                thanhtien = thanhtien + (listhdct.get(i).getSoLuong() * Integer.parseInt(listhdct.get(i).getDonGia().toString()));
            }
            txtThanhTien1.setText((String.valueOf(thanhtien)));
        }

        addTableGioHang(listhdct);
    }//GEN-LAST:event_tbHoaDonBanHangMouseClicked

    private void txttenkhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttenkhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttenkhActionPerformed

    private void btnxacnhan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxacnhan1ActionPerformed
        // TODO add your handling code here:
        //        CardLayout layout = (CardLayout) pnlCacGiaoDien.getLayout();
        //        layout.show(pnlCacGiaoDien, "cardLayThongTin");
        //        listKH = serviceKH.getAll();
        //        showDataKH(listKH);
        //        KH1 kh = new KH1();
        //        kh.setVisible(true);

        LayThongTinKhachHang l = new LayThongTinKhachHang();
        l.setVisible(true);
    }//GEN-LAST:event_btnxacnhan1ActionPerformed
    void clear() {
        txtGhiChu1.setText("");
        txtKhachTra1.setText("");
        txtMaHdBH.setText("");
        txtMaNhanVienbh.setText("");
        txtSDTKhachHang.setText("");
        txtThanhTien1.setText("");
        txtTienDu1.setText("");
        txttenkh.setText("");
        LBtime.setText("");
    }
    private void btnthanhtoan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthanhtoan2ActionPerformed
        // TODO add your handling code here:
        Date date = new Date(System.currentTimeMillis());
        HoaDon hd = new HoaDon();
        int row = tbHoaDonBanHang.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui l??ng ch???n trong d??ng");
            return;
        }
        int bb = JOptionPane.showConfirmDialog(this, "B???n mu???n thanh to??n kh??ng ?", "C??", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
        if (bb == JOptionPane.YES_OPTION) {
            String ma = txtSDTKhachHang.getText();
            if (txtSDTKhachHang.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "S??? ??i???n tho???i kh??ch ??ang tr???ng");
                return;
            }
            if (txttenkh.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "T??n kh??ch h??ng ??ang tr???ng");
                return;
            }
            if (txtKhachTra1.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Ch??a nh???p s??? ti???n kh??ch ????a");
                return;
            }
            try {
                if (Double.parseDouble(txtKhachTra1.getText().toString()) < 0) {
                    JOptionPane.showMessageDialog(this, "Ti???n kh??ch tr??? ph???i l???n h??n 0");
                    return;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ti???n kh??ch tr??? ph???i l?? s???");
                return;
            }
            String ma1 = txtMaNhanVien.getText();
            hd.setMaHD(txtMaHdBH.getText());
            hd.setNgayThanhToan(new Date());
            hd.setTenNguoiNhan(txttenkh.getText());
            hd.setSDT(txtSDTKhachHang.getText());
            if (rdTienMat.isSelected()) {
                hd.setPTGD(0);
            } else {
                hd.setPTGD(1);
            }
            hd.setTinhTrang(1);
            hd.setTongTien(BigDecimal.valueOf(Double.parseDouble(txtThanhTien1.getText())));
            try {
                banHangService.updateThanhToan(hd, ma, ma1);
                JOptionPane.showMessageDialog(this, "Thanh To??n Th??nh C??ng");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            listhdct.clear();
            clear();
            loadTableHoaDonBanHang();
            addTableGioHang(listhdct);

        } else if (bb == JOptionPane.NO_OPTION) {
            return;
        } else {
            return;
        }
        int tb = JOptionPane.showConfirmDialog(this, "C?? in ra ho?? ????n", "Th??ng b??o", JOptionPane.YES_NO_OPTION);
        if (tb == JOptionPane.YES_OPTION) {
            exportBill();
        } else if (tb == JOptionPane.NO_OPTION) {
            return;
        } else {
            return;
        }
    }//GEN-LAST:event_btnthanhtoan2ActionPerformed

    private void txtKhachTra1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtKhachTra1CaretUpdate
        // TODO add your handling code here:
        if (txtKhachTra1.getText().equalsIgnoreCase("")) {
            return;
        } else {
            double tiendu = Double.parseDouble(txtKhachTra1.getText()) - Double.parseDouble(txtThanhTien1.getText());
            txtTienDu1.setText(String.valueOf(tiendu));
        }
    }//GEN-LAST:event_txtKhachTra1CaretUpdate

    private void LBtimeAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_LBtimeAncestorAdded
        // TODO add your handling code here:
        //        Time times = new Time(LBtime);
        //        times.start();
    }//GEN-LAST:event_LBtimeAncestorAdded

    private void btnhuyhdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhuyhdActionPerformed
        if (txtMaHdBH.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Xin m???i ch???n h??a ????n c???n x??a");
            return;
        } else {
            int bb = JOptionPane.showConfirmDialog(this, "Xo?? h??a ????n", "Th??ng b??o", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
            if (bb == JOptionPane.YES_OPTION) {
                String Ma = txtMaHdBH.getText();
                try {
                    banHangService.deleteHoaDon(Ma);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(this, "Xo?? th??nh c??ng");
            } else if (bb == JOptionPane.NO_OPTION) {
                return;
            } else {
                return;
            }
        }
        loadTableHoaDonBanHang();
        addTableGioHang(listhdct);
    }//GEN-LAST:event_btnhuyhdActionPerformed

    private void btntaohd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntaohd1ActionPerformed
        // TODO add your handling code here:
        int bb = JOptionPane.showConfirmDialog(this, "Th??m h??a ????n", "Th??ng b??o", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
        if (bb == JOptionPane.YES_OPTION) {
            Date date = new Date(System.currentTimeMillis());
            HoaDon hd = new HoaDon();
            List<HoaDonBanHangViewModel> st = banHangService.allHoaDonCho();
            for (int i = 0; i < 5 + 1; i++) {
                Random rdm = new Random();
                int rdmm = rdm.nextInt(10000) + 1;
                hd.setMaHD("HD" + rdmm);
            }
            hd.setNgayTao(new Date());
            hd.setTinhTrang(0);
            try {
                banHangService.addHoaDonCho(hd);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            loadTableHoaDonBanHang();
            JOptionPane.showMessageDialog(this, "Th??m h??a ????n th??nh c??ng");
        } else if (bb == JOptionPane.NO_OPTION) {
            return;
        } else {
            return;
        }
    }//GEN-LAST:event_btntaohd1ActionPerformed

    private void txtThanhTien1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtThanhTien1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtThanhTien1CaretUpdate

    private void txtSDTKhachHangCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSDTKhachHangCaretUpdate
        // TODO add your handling code here:
        List<KhachHang> sp = khachHangService.getAll();
        for (int j = 0; j < sp.size(); j++) {
            if (sp.get(j).getSdt().equalsIgnoreCase(txtSDTKhachHang.getText())) {
                txttenkh.setText(sp.get(j).getHoTen());

            }
        }
    }//GEN-LAST:event_txtSDTKhachHangCaretUpdate

    private void txtSDTKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTKhachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTKhachHangActionPerformed

    private void btnNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanVienActionPerformed
        // TODO add your handling code here:
        jPanel = new TTnhanVienView2();
        panelTong.removeAll();
        panelTong.add(jPanel);
        panelTong.validate();
    }//GEN-LAST:event_btnNhanVienActionPerformed

    private void btnnextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnextActionPerformed
        // TODO add your handling code here:
        if (start < trang) {
            start = start + 7;
            sotrang++;
        }
        loadTableChiTietSPBH(chiTietSPService.all(start, end));
    }//GEN-LAST:event_btnnextActionPerformed

    private void btnbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbackActionPerformed
        // TODO add your handling code here:
        if (start > 1) {
            start = start - 7;
            sotrang--;
        }
        loadTableChiTietSPBH(chiTietSPService.all(start, end));
    }//GEN-LAST:event_btnbackActionPerformed
    private static final String FILE_NAME = "D:/itext.pdf";

    public String phuongthucthanhtoan() {
        HoaDon hd = new HoaDon();
        if (rdTienMat.isSelected()) {
            return "Tien Mat";
        } else {
            return "Chuy???n Kho???n";
        }

    }

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
            java.util.logging.Logger.getLogger(NhanVienBanHangViews.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhanVienBanHangViews.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhanVienBanHangViews.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhanVienBanHangViews.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NhanVienBanHangViews().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LBtime;
    private javax.swing.JButton btnBanHang2;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnHoaDon;
    private javax.swing.JButton btnKhachHang;
    private javax.swing.JButton btnNhanVien;
    private javax.swing.JButton btnSanPham;
    private javax.swing.JButton btnback;
    private javax.swing.JButton btnhuyhd;
    private javax.swing.JButton btnnext;
    private javax.swing.JButton btntaohd1;
    private javax.swing.JButton btnthanhtoan2;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btnxacnhan1;
    private javax.swing.JButton btnxoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel jTrang;
    private javax.swing.JPanel panelTong;
    private javax.swing.JPanel pnlBanHang;
    private javax.swing.JRadioButton rdChuyenKhoan;
    private javax.swing.JRadioButton rdTienMat;
    private javax.swing.JTable tbHoaDonBanHang;
    private javax.swing.JTable tbldssanpham;
    private javax.swing.JTable tblgiohang;
    private javax.swing.JTextField txtGhiChu1;
    private javax.swing.JTextField txtKhachTra1;
    private javax.swing.JTextField txtMaHdBH;
    public static javax.swing.JTextField txtMaNhanVienbh;
    public static javax.swing.JTextField txtSDTKhachHang;
    private javax.swing.JTextField txtThanhTien1;
    private javax.swing.JLabel txtTienDu1;
    public static javax.swing.JTextField txttenkh;
    private javax.swing.JTextField txttimkiem;
    // End of variables declaration//GEN-END:variables
}
