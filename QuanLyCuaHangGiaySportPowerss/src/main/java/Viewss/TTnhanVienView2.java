/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Viewss;

import DomainModels.ChucVu;
import DomainModels.NhanVien;
import Services.ChucVuSevice;
import Services.Interface.ChucVuServiceInterface;
import Services.Interface.NhanVienServiceInteface;
import Services.NhanVienService;
import ViewModels.NhanVienViewModel;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dinhq
 */
public class TTnhanVienView2 extends javax.swing.JPanel {

    private NhanVienServiceInteface nvService;
    private ChucVuServiceInterface cvService;

    /**
     * Creates new form TTnhanVienView
     */
    public TTnhanVienView2() {
        initComponents();
        this.cvService = new ChucVuSevice();
        this.nvService = new NhanVienService();
        List<ChucVu> cv = cvService.getall();
        cbCV.setModel(new DefaultComboBoxModel(cv.toArray()));
        loadTableNhanVien();
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panel3 = new java.awt.Panel();
        jPanel34 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        cbCV = new javax.swing.JComboBox<>();
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
        jButton14 = new javax.swing.JButton();
        jPanel38 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        tbNhanVien = new javax.swing.JTable();
        jTextField19 = new javax.swing.JTextField();

        panel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel34.setBackground(new java.awt.Color(255, 255, 255));
        jPanel34.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "THÔNG TIN NHÂN VIÊN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(51, 0, 0))); // NOI18N
        jPanel34.setForeground(new java.awt.Color(153, 153, 153));

        jLabel26.setText("CHỨC VỤ");

        cbCV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chức vụ", "Quản lý", "Nhân Viên" }));

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

        buttonGroup1.add(rdNamNV);
        rdNamNV.setText("NAM");
        rdNamNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNamNVActionPerformed(evt);
            }
        });

        jLabel49.setText("HỌ VÀ TÊN");

        buttonGroup1.add(rdNuNV);
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 176, Short.MAX_VALUE)
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
                            .addComponent(jLabel44)
                            .addGap(29, 29, 29)
                            .addComponent(txtDCNV, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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

        jButton14.setBackground(new java.awt.Color(204, 204, 204));
        jButton14.setText("SỬA");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jButton14)
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
                false, false, false, false, false, false, false, false, false, false
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

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel38Layout.createSequentialGroup()
                .addContainerGap(504, Short.MAX_VALUE)
                .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(337, 337, 337))
            .addComponent(jScrollPane15, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel38Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField19)
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

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
        if(txtHoTenNV.getText().equals("")){
            return; 
        }else{
            for (int i = 0; i < 5 + 1; i++) {
                Random rdm = new Random();
                int rdmm = rdm.nextInt(100000) + 1;
                txtMaNhanVien.setText("NV" + rdmm);
                txtTKNV.setText(txtMaNhanVien.getText());
            }
        }
    }//GEN-LAST:event_txtHoTenNVActionPerformed

    private void rdNamNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNamNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNamNVActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        ChucVuView cv = new ChucVuView();
        cv.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtMaNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNhanVienActionPerformed

    private void txtSDTNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTNhanVienActionPerformed

    void clearNV() {
        txtMaNhanVien.setText("");
        txtHoTenNV.setText("");
        cbCV.setSelectedIndex(0);
        txtDCNV.setText("");
        dateNSNV.setDate(null);
        txtTKNV.setText("");
        txtMKNV.setText("");
        txtSDTNhanVien.setText("");
        buttonGroup1.clearSelection();
    }

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        int row = tbNhanVien.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn vào trong bảng");
            return;
        }
        if (txtMaNhanVien.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập Mã");
            return;
        }
        if (txtHoTenNV.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập họ tên");
            return;
        }
        if (txtDCNV.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập địa chỉ");
            return;
        }
        if (txtMKNV.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu");
            return;
        }
        if (txtSDTNhanVien.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập SDT");
            return;
        }
        int bb = JOptionPane.showConfirmDialog(this, "Bạn muốn sửa lại không ?", "Có", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
        if (bb == JOptionPane.YES_OPTION) {
            NhanVien nv = new NhanVien();
            nv.setIdNV(UUID.fromString(txtIDNhanVien.getText()));
            nv.setMaNV(txtMaNhanVien.getText());
            nv.setHoTenNV(txtHoTenNV.getText());
            nv.setChucvu((ChucVu) cbCV.getSelectedItem());
            if (rdNamNV.isSelected()) {
                nv.setGioiTinh("Nam");
            } else {
                nv.setGioiTinh("Nữ");
            }
            nv.setDiaChi(txtDCNV.getText());
            nv.setNgaySinh(dateNSNV.getDate());
            nv.setMatkhau(txtMKNV.getText());
            nv.setSdt(txtSDTNhanVien.getText());
            try {
                nvService.update(nv);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Sửa thành công");
        } else if (bb == JOptionPane.NO_OPTION) {
            return;
        } else {
            return;
        }
        loadTableNhanVien();
        clearNV();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void tbNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNhanVienMouseClicked
        // TODO add your handling code here:
        int row = tbNhanVien.getSelectedRow();
        txtIDNhanVien.setText(tbNhanVien.getValueAt(row, 0).toString());
        txtMaNhanVien.setText(tbNhanVien.getValueAt(row, 1).toString());
        txtHoTenNV.setText(tbNhanVien.getValueAt(row, 2).toString());
        if (tbNhanVien.getValueAt(row, 3).toString().equalsIgnoreCase("Nam")) {
            rdNamNV.setSelected(true);
        } else {
            rdNuNV.setSelected(true);
        }
        cbCV.setSelectedItem(tbNhanVien.getValueAt(row, 4).toString());
        txtDCNV.setText(tbNhanVien.getValueAt(row, 5).toString());
        dateNSNV.setDate((Date) tbNhanVien.getValueAt(row, 6));

        txtTKNV.setText(tbNhanVien.getValueAt(row, 7).toString());
        txtMKNV.setText(tbNhanVien.getValueAt(row, 8).toString());
        txtSDTNhanVien.setText(tbNhanVien.getValueAt(row, 9).toString());
    }//GEN-LAST:event_tbNhanVienMouseClicked

    private void jTextField19jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField19jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField19jTextField8ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ID;
    private javax.swing.ButtonGroup buttonGroup1;
    public static javax.swing.JComboBox<String> cbCV;
    private com.toedter.calendar.JDateChooser dateNSNV;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JTextField jTextField19;
    private java.awt.Panel panel3;
    private javax.swing.JRadioButton rdNamNV;
    private javax.swing.JRadioButton rdNuNV;
    private javax.swing.JTable tbNhanVien;
    private javax.swing.JTextField txtDCNV;
    private javax.swing.JTextField txtHoTenNV;
    private javax.swing.JTextField txtIDNhanVien;
    private javax.swing.JTextField txtMKNV;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JTextField txtSDTNhanVien;
    private javax.swing.JTextField txtTKNV;
    // End of variables declaration//GEN-END:variables
}
