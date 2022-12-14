/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Viewss;

import DomainModels.Size;
import Services.Interface.SizeServiceInterface;
import Services.SizeService;
import static Viewss.ChiTietSanPhamView.cbSIZE;
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
public class SizeView extends javax.swing.JFrame {

    private SizeServiceInterface sizeService;

    /**
     * Creates new form SizeView
     */
    public SizeView() {
        initComponents();
        this.sizeService = new SizeService();
        this.loadTableSizeGiay();
    }

    void clear() {
        txtIDSIZE.setText("");
        txtMaSIZE.setText("");
        txtSoSize.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        btnXoa7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

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
        lblMaSp33.setText("M?? SIZE");

        txtMaSIZE.setEditable(false);
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
        lblMaSp34.setText("S??? SIZE");

        btnThem6.setBackground(new java.awt.Color(204, 204, 204));
        btnThem6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem6.setText("Th??m th??ng tin");
        btnThem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem6ActionPerformed(evt);
            }
        });

        btnSua6.setBackground(new java.awt.Color(204, 204, 204));
        btnSua6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSua6.setText("S???a th??ng tin");
        btnSua6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua6ActionPerformed(evt);
            }
        });

        btnXoa6.setBackground(new java.awt.Color(204, 204, 204));
        btnXoa6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa6.setText("X??a th??ng tin");
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
                "ID", "M?? SP", "S??? SIZE"
            }
        ));
        tbSIZE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSIZEMouseClicked(evt);
            }
        });
        jScrollPane18.setViewportView(tbSIZE);

        btnXoa7.setBackground(new java.awt.Color(204, 204, 204));
        btnXoa7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa7.setText("Exit");
        btnXoa7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa7ActionPerformed(evt);
            }
        });

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
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel50Layout.createSequentialGroup()
                                .addComponent(lblMaSp34)
                                .addGap(25, 25, 25)
                                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSoSize)
                                    .addComponent(btnXoa7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                .addGap(18, 18, 18)
                .addComponent(btnXoa7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
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
    private void txtIDSIZEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDSIZEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDSIZEActionPerformed

    private void txtMaSIZEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSIZEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSIZEActionPerformed

    private void txtSoSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoSizeActionPerformed
        // TODO add your handling code here:
        if (txtSoSize.getText().equals("")) {
            return;
        } else {
            for (int i = 0; i < 5 + 1; i++) {
                Random rdm = new Random();
                int rdmm = rdm.nextInt(100000) + 1;
                txtMaSIZE.setText("S" + rdmm);
            }
        }
    }//GEN-LAST:event_txtSoSizeActionPerformed

    private void btnThem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem6ActionPerformed
        // TODO add your handling code here:
        if (txtMaSIZE.getText().equals("") || txtSoSize.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "B???n Ch??a nh???p ????? d??? li???u");
            return;
        }
        if (Integer.parseInt(txtSoSize.getText()) <= 30) {
            JOptionPane.showMessageDialog(this, "S??? size ph???i l???n h??n 30");
            return;
        }
        if (Integer.parseInt(txtSoSize.getText()) >= 50) {
            JOptionPane.showMessageDialog(this, "S??? size ph???i nh??? h??n 50");
            return;
        }

        int xacnhan = JOptionPane.showConfirmDialog(this, "B???n mu???n th??m hay kh??ng?", "C??", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
        if (xacnhan == JOptionPane.YES_OPTION) {

            Size s = new Size();
            s.setMaSize(txtMaSIZE.getText());
            s.setSoSize(txtSoSize.getText());
            try {
                sizeService.add(s);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Th??m th??nh c??ng");
        } else if (xacnhan == JOptionPane.NO_OPTION) {
            return;
        } else {
            return;
        }
        clear();
        loadTableSizeGiay();
        //        loadCBSize();
    }//GEN-LAST:event_btnThem6ActionPerformed

    private void btnSua6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua6ActionPerformed
        int row = tbSIZE.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui l??ng ch???n v??o trong b???ng");
            return;
        }
        if (txtMaSIZE.getText().equals("") || txtSoSize.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Ban Ch??a nh???p ????? d??? li???u");
            return;
        }

        if (Integer.parseInt(txtSoSize.getText()) <= 30) {
            JOptionPane.showMessageDialog(this, "S??? size ph???i l???n h??n 30");
            return;
        }
        if (Integer.parseInt(txtSoSize.getText()) >= 50) {
            JOptionPane.showMessageDialog(this, "S??? size ph???i nh??? h??n 50");
            return;
        }// TODO add your handling code here:
        int xacnhan = JOptionPane.showConfirmDialog(this, "B???n mu???n s???a hay kh??ng?", "C??", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
        if (xacnhan == JOptionPane.YES_OPTION) {
            Size s = new Size();
            s.setIdSize(UUID.fromString(txtIDSIZE.getText()));
            s.setMaSize(txtMaSIZE.getText());
            s.setSoSize(txtSoSize.getText());
            try {
                sizeService.update(s);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "S???a th??nh c??ng");
        } else if (xacnhan == JOptionPane.NO_OPTION) {
            return;

        }
        clear();
        loadTableSizeGiay();
        //        loadCBSize();
    }//GEN-LAST:event_btnSua6ActionPerformed

    private void btnXoa6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa6ActionPerformed
        // TODO add your handling code here:
        int row = tbSIZE.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui l??ng ch???n v??o trong b???ng");
            return;
        }
        int xacnhan = JOptionPane.showConfirmDialog(this, "B???n mu???n th??m hay kh??ng?", "C??", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
        if (xacnhan == JOptionPane.YES_OPTION) {
            Size s = new Size();
            s.setIdSize(UUID.fromString(txtIDSIZE.getText()));
            s.setMaSize(txtMaSIZE.getText());
            s.setSoSize(txtSoSize.getText());
            try {
                sizeService.delete(s);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "X??a th??nh c??ng");
        } else if (xacnhan == JOptionPane.NO_OPTION) {
            return;
        }
        clear();
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

    private void btnXoa7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa7ActionPerformed
        // TODO add your handling code here:
        List<Size> sz = sizeService.all();
        cbSIZE.setModel(new DefaultComboBoxModel((sz.toArray())));
        ChiTietSanPhamView ql = new ChiTietSanPhamView();
        ql.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_btnXoa7ActionPerformed

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
//            java.util.logging.Logger.getLogger(SizeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(SizeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(SizeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(SizeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new SizeView().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua6;
    private javax.swing.JButton btnThem6;
    private javax.swing.JButton btnXoa6;
    private javax.swing.JButton btnXoa7;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JLabel lblMaSp32;
    private javax.swing.JLabel lblMaSp33;
    private javax.swing.JLabel lblMaSp34;
    private javax.swing.JTable tbSIZE;
    private javax.swing.JTextField txtIDSIZE;
    private javax.swing.JTextField txtMaSIZE;
    private javax.swing.JTextField txtSoSize;
    // End of variables declaration//GEN-END:variables
}
