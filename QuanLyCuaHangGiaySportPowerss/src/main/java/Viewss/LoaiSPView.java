/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Viewss;

import DomainModels.LoaiGiay;
import Services.Interface.LoaiGiayServiceInterface;
import Services.LoaiGiayService;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TUAN ANH
 */
public class LoaiSPView extends javax.swing.JFrame {

    /**
     * Creates new form LoaiSPView
     */
    
    private LoaiGiayServiceInterface loaiGiayService;

    public LoaiSPView() {
        initComponents();
        this.loaiGiayService = new LoaiGiayService();
        this.loadTableLoaiGiay();
    }
    void check(){
        if(txtTenLoai.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Chưa nhập tên loại sản phẩm");
            return;
        }
        if(txtMoTaLoaiGiay.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Chưa nhập mô tả loại sản phẩm");
            return;
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
        btnXoa6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

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

        txtMaLoai.setEditable(false);
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

        btnXoa6.setBackground(new java.awt.Color(204, 204, 204));
        btnXoa6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa6.setText("Exit");
        btnXoa6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa6ActionPerformed(evt);
            }
        });

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
                            .addComponent(btnThem5, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                            .addComponent(btnXoa6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(txtMoTaLoaiGiay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa6))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
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

    void clear() {
        txtIDLoai.setText("");
        txtMaLoai.setText("");
        txtMoTaLoaiGiay.setText("");
        txtTenLoai.setText("");
    }
    private void txtIDLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDLoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDLoaiActionPerformed

    private void txtMaLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaLoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaLoaiActionPerformed

    private void txtTenLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenLoaiActionPerformed
        // TODO add your handling code here:
        if (txtTenLoai.getText().equals("")) {
            return;
        } else {
            for (int i = 0; i < 5 + 1; i++) {
                Random rdm = new Random();
                int rdmm = rdm.nextInt(100000) + 1;
                txtMaLoai.setText("L" + rdmm);
            }
        }
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
            check();
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
        clear();
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
            check();
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
        clear();
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
        clear();
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

    private void btnXoa6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa6ActionPerformed
        // TODO add your handling code here:
        
        List<LoaiGiay> lg = loaiGiayService.all();
        ChiTietSanPhamView.cbLoai.setModel(new DefaultComboBoxModel((lg.toArray())));
        ChiTietSanPhamView ql = new ChiTietSanPhamView();
        ql.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_btnXoa6ActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(LoaiSPView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(LoaiSPView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(LoaiSPView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(LoaiSPView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new LoaiSPView().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua5;
    private javax.swing.JButton btnThem5;
    private javax.swing.JButton btnXoa5;
    private javax.swing.JButton btnXoa6;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JLabel lblMaSp28;
    private javax.swing.JLabel lblMaSp29;
    private javax.swing.JLabel lblMaSp30;
    private javax.swing.JLabel lblMaSp31;
    private javax.swing.JTable tbLoaiGiay;
    private javax.swing.JTextField txtIDLoai;
    private javax.swing.JTextField txtMaLoai;
    private javax.swing.JTextField txtMoTaLoaiGiay;
    private javax.swing.JTextField txtTenLoai;
    // End of variables declaration//GEN-END:variables
}
