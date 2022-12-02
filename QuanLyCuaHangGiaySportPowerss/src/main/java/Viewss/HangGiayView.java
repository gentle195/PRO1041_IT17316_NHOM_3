/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Viewss;

import DomainModels.HangGiay;
import Services.HangGiayService;
import Services.Interface.HangGiayServiceInterface;
import java.util.List;
import java.util.UUID;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TUAN ANH
 */
public class HangGiayView extends javax.swing.JFrame {

    /**
     * Creates new form DeGiay
     */
     private HangGiayServiceInterface hangGiayService;
    public HangGiayView() {
        
        initComponents();
          this.hangGiayService = new HangGiayService();
          this.loadTableHangGiay();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
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
            java.util.logging.Logger.getLogger(HangGiayView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HangGiayView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HangGiayView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HangGiayView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HangGiayView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua4;
    private javax.swing.JButton btnThem4;
    private javax.swing.JButton btnXoa4;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JLabel lblMaSp22;
    private javax.swing.JLabel lblMaSp23;
    private javax.swing.JLabel lblMaSp24;
    private javax.swing.JLabel lblMaSp25;
    private javax.swing.JTable tbHang;
    private javax.swing.JTextField txtIDHang;
    private javax.swing.JTextField txtMaHang;
    private javax.swing.JTextField txtMoTaHang;
    private javax.swing.JTextField txtTenHang;
    // End of variables declaration//GEN-END:variables
}
