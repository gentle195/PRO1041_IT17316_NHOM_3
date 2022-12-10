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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

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
        txtMaNhanVientt.setText(giaoDienDangNhap.ma);
        this.nvService = new NhanVienService();
        this.cvService = new ChucVuSevice();
        List<NhanVienViewModel> nv = nvService.getallNhanVien(txtMaNhanVientt.getText());
        txtHoTenNV.setText(nv.get(0).getHoTenNV());
        if (nv.get(0).getGioiTinh().equals("Nam")) {
            rdNamNV.setSelected(true);
        } else {
            rdNuNV.setSelected(true);
        }
        txtSDTNhanVien.setText(nv.get(0).getSdt());
        dateNSNV.setDate(nv.get(0).getNgaySinh());
        txtMKNV.setText(nv.get(0).getMatkhau());
        txtDCNV.setText(nv.get(0).getDiaChi());
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
        txtDCNV = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        txtHoTenNV = new javax.swing.JTextField();
        rdNamNV = new javax.swing.JRadioButton();
        jLabel49 = new javax.swing.JLabel();
        rdNuNV = new javax.swing.JRadioButton();
        dateNSNV = new com.toedter.calendar.JDateChooser();
        jLabel56 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        txtSDTNhanVien = new javax.swing.JTextField();
        jButton14 = new javax.swing.JButton();
        txtMKNV = new javax.swing.JTextField();

        panel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel34.setBackground(new java.awt.Color(255, 255, 255));
        jPanel34.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "THÔNG TIN NHÂN VIÊN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(51, 0, 0))); // NOI18N
        jPanel34.setForeground(new java.awt.Color(153, 153, 153));

        txtDCNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDCNVActionPerformed(evt);
            }
        });

        jLabel43.setText("GIỚI TÍNH");

        jLabel44.setText("QUÊ QUÁN");

        jLabel45.setText("MẬT KHẨU");

        jLabel47.setText("NGÀY SINH");

        txtHoTenNV.setEditable(false);
        txtHoTenNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoTenNVActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdNamNV);
        rdNamNV.setText("NAM");
        rdNamNV.setEnabled(false);
        rdNamNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNamNVActionPerformed(evt);
            }
        });

        jLabel49.setText("HỌ VÀ TÊN");

        buttonGroup1.add(rdNuNV);
        rdNuNV.setText("NỮ");
        rdNuNV.setEnabled(false);
        rdNuNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNuNVActionPerformed(evt);
            }
        });

        dateNSNV.setEnabled(false);

        jLabel56.setText("MÃ NHÂN VIÊN");

        txtMaNhanVientt.setEditable(false);
        txtMaNhanVientt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNhanVienttActionPerformed(evt);
            }
        });

        jLabel48.setText("SĐT");

        txtSDTNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTNhanVienActionPerformed(evt);
            }
        });

        jButton14.setBackground(new java.awt.Color(204, 204, 204));
        jButton14.setText("SỬA");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        txtMKNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMKNVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel56)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel34Layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(rdNamNV, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68)
                                .addComponent(rdNuNV, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel34Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSDTNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtHoTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaNhanVientt, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel34Layout.createSequentialGroup()
                            .addComponent(jLabel44)
                            .addGap(65, 65, 65)
                            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtMKNV, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDCNV, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dateNSNV, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(140, 140, 140))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMaNhanVientt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel56)
                        .addComponent(jLabel47))
                    .addComponent(dateNSNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(128, 128, 128)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(txtHoTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45)
                    .addComponent(txtMKNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(rdNamNV)
                    .addComponent(rdNuNV)
                    .addComponent(jLabel44)
                    .addComponent(txtDCNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(txtSDTNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14))
                .addGap(127, 127, 127))
        );

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(156, Short.MAX_VALUE))
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        int bb = JOptionPane.showConfirmDialog(this, "Bạn muốn sửa lại không ?", "Có", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
        if (bb == JOptionPane.YES_OPTION) {
            NhanVien nv = new NhanVien();
            if(txtHoTenNV.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Không để trống tên");
                return;
            }
            if(txtDCNV.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Không để trống địa chỉ");
                return;
            }
            if(txtMKNV.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Không để trống mật khẩu");
                return;
            }
            if(dateNSNV.getDate()==null){
                JOptionPane.showMessageDialog(this, "Không để trống ngày sinh");
                return;
            }
            
            if(txtSDTNhanVien.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Không để trống số điện thoại");
                return; 
            }
            List<NhanVienViewModel> nvvm = nvService.getallNhanVien(txtMaNhanVientt.getText());
            nv.setIdNV(nvvm.get(0).getIdNV());
            nv.setChucvu(nvvm.get(0).getChucvu());
            nv.setMaNV(txtMaNhanVientt.getText());
            nv.setHoTenNV(txtHoTenNV.getText());
            if (rdNamNV.isSelected()) {
                nv.setGioiTinh("Nam");
            } else {
                nv.setGioiTinh("Nữ");
            }
            nv.setDiaChi(txtDCNV.getText());
            nv.setNgaySinh((Date) dateNSNV.getDate());
            nv.setMatkhau(txtMKNV.getText());
            if(txtSDTNhanVien.getText().matches("^0\\d{9,10}")){
                 nv.setSdt(txtSDTNhanVien.getText());
            }else{
                JOptionPane.showMessageDialog(this, "Số điện thoại nhập chưa đúng");
                return;
            }
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
    }//GEN-LAST:event_jButton14ActionPerformed

    private void txtSDTNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTNhanVienActionPerformed

    private void txtMaNhanVienttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNhanVienttActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNhanVienttActionPerformed

    private void rdNamNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNamNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNamNVActionPerformed

    private void txtHoTenNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoTenNVActionPerformed
        // TODO add your handling code here:
        if (txtHoTenNV.getText().equals("")) {
            return;
        } else {
            for (int i = 0; i < 5 + 1; i++) {
                Random rdm = new Random();
                int rdmm = rdm.nextInt(100000) + 1;
                txtMaNhanVientt.setText("NV" + rdmm);

            }
        }
    }//GEN-LAST:event_txtHoTenNVActionPerformed

    private void txtDCNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDCNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDCNVActionPerformed

    private void rdNuNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNuNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNuNVActionPerformed

    private void txtMKNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMKNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMKNVActionPerformed

    void clearNV() {
        txtMaNhanVientt.setText("");
        txtHoTenNV.setText("");
        txtDCNV.setText("");
        dateNSNV.setDate(null);
        txtMKNV.setText("");
        txtSDTNhanVien.setText("");
        buttonGroup1.clearSelection();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser dateNSNV;
    private javax.swing.JButton jButton14;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JPanel jPanel34;
    private java.awt.Panel panel3;
    private javax.swing.JRadioButton rdNamNV;
    private javax.swing.JRadioButton rdNuNV;
    private javax.swing.JTextField txtDCNV;
    private javax.swing.JTextField txtHoTenNV;
    private javax.swing.JTextField txtMKNV;
    public static final javax.swing.JTextField txtMaNhanVientt = new javax.swing.JTextField();
    private javax.swing.JTextField txtSDTNhanVien;
    // End of variables declaration//GEN-END:variables
}
