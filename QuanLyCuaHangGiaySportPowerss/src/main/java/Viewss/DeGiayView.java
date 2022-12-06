/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Viewss;

import DomainModels.DeGiay;
import Services.DeGiayService;
import Services.Interface.DeGiayServiceInterface;
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
public class DeGiayView extends javax.swing.JFrame {

   
    private DeGiayServiceInterface deGiayService;

    /**
     * Creates new form DeGiayView
     */
    public DeGiayView() {
        initComponents();
        
        this.deGiayService = new DeGiayService();
        this.loadTableDeGiay();
    }

    void clear() {
        txtIDDeGiay.setText("");
        txtLoaiDe.setText("");
        txtMaDe.setText("");
        txtMoTaDe.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        btnXoa8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        txtMaDe.setEditable(false);
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

        btnXoa8.setBackground(new java.awt.Color(204, 204, 204));
        btnXoa8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa8.setText("Exit");
        btnXoa8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa8ActionPerformed(evt);
            }
        });

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnXoa7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSua7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThem7, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                            .addComponent(btnXoa8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                    .addComponent(txtMoTaDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(119, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel41.getAccessibleContext().setAccessibleName("Đế giày");

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
    private void txtIDDeGiayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDDeGiayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDDeGiayActionPerformed

    private void txtMaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaDeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaDeActionPerformed

    private void txtLoaiDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLoaiDeActionPerformed
        // TODO add your handling code here:
        if (txtLoaiDe.getText().equals("")) {
            return;
        } else {
            for (int i = 0; i < 5 + 1; i++) {
                Random rdm = new Random();
                int rdmm = rdm.nextInt(100000) + 1;
                txtMaDe.setText("D" + rdmm);
            }
        }
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
        clear();
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
        clear();
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
        clear();
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

    private void btnXoa8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa8ActionPerformed
        // TODO add your handling code here:

        List<DeGiay> dg = deGiayService.all();
        ChiTietSanPhamView.cbDe.setModel(new DefaultComboBoxModel((dg.toArray())));
        ChiTietSanPhamView ql = new ChiTietSanPhamView();
        ql.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_btnXoa8ActionPerformed

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
            java.util.logging.Logger.getLogger(DeGiayView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DeGiayView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DeGiayView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DeGiayView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DeGiayView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua7;
    private javax.swing.JButton btnThem7;
    private javax.swing.JButton btnXoa7;
    private javax.swing.JButton btnXoa8;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JLabel lblMaSp35;
    private javax.swing.JLabel lblMaSp36;
    private javax.swing.JLabel lblMaSp37;
    private javax.swing.JLabel lblMaSp38;
    private javax.swing.JTable tbDeGiay;
    private javax.swing.JTextField txtIDDeGiay;
    private javax.swing.JTextField txtLoaiDe;
    private javax.swing.JTextField txtMaDe;
    private javax.swing.JTextField txtMoTaDe;
    // End of variables declaration//GEN-END:variables
}
