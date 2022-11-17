/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import DomainModels.khachHang_model;
import Services.Impl.khachHang_service;
import Utilities.Chk;

/**
 *
 * @author Admin
 */
public class khachHang extends javax.swing.JFrame {

    khachHang_service ALLKH = new khachHang_service();
    List<khachHang_model> list = new ArrayList<>();
    DefaultTableModel mol;

    /**
     * Creates new form khachHang
     */
    public khachHang() {
        initComponents();
        fillTable();
    }

    public void fillTable() {
        list = ALLKH.getALLKH();
        mol = (DefaultTableModel) tbl.getModel();
        mol.setRowCount(0);
        for (khachHang_model s : list) {
            mol.addRow(new Object[]{s.getId(), s.getMakh(), s.getTenkh(),s.getNgaysinh(), s.getSdt(), s.getDiachi(), s.getThanhpho(), s.getQuocgia(), s.getMota()});
        }
    }

    public void show(int index) {
        khachHang_model s = ALLKH.getBYmaKh(tbl.getValueAt(index, 1).toString());
        txtid.setText(s.getId());
        txtma.setText(s.getMakh());
        txtten.setText(s.getTenkh());
        txtngsinh.setText(s.getNgaysinh());
        txtsdt.setText(s.getSdt());
        txtdiachi.setText(s.getDiachi());
        txtthanhpho.setText(s.getThanhpho());
        txtquocgia.setText(s.getQuocgia());
        txtmota.setText(s.getMota());
    }

    public khachHang_model getKH() {
        String makh = txtma.getText();
        String ten = txtten.getText();
        String ngsinh = txtngsinh.getText();
        String sdt = txtsdt.getText();
        String diachi = txtdiachi.getText();
        String thanhpho = txtthanhpho.getText();
        String quocgia = txtquocgia.getText();
        String mota = txtmota.getText();
        return new khachHang_model(makh, ten, ngsinh, sdt, diachi, thanhpho, quocgia, mota);
    }
        public boolean chkvalidate() {
        if (Chk.chknull("Không được để trống mã ", txtma)) {
            return false;
        } else if (Chk.chknull("Không được để trống tên ", txtten)) {
            return false;
        } else if (Chk.chknull("Không được để trống ngày sinh ", txtngsinh)) {
            return false;
        } else if (Chk.chknull("Không được để trống địa chỉ ", txtdiachi)) {
            return false;
        } else if (Chk.chknull("Không được để trống sdt  ", txtsdt)) {
            return false;
        } else if (Chk.chkInt("Không được để trống sdt ", "Sdt phải là số ", txtsdt)) {
            return false;
        }else if (Chk.chknull("Không được để trống thành phố", txtthanhpho)){
        return false;
        }else if (Chk.chknull("Không được để trống quốc gia", txtquocgia)){
        return false;
        } else {
            return true;
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

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        txtsdt = new javax.swing.JTextField();
        txtngsinh = new javax.swing.JTextField();
        txtten = new javax.swing.JTextField();
        txtma = new javax.swing.JTextField();
        txtdiachi = new javax.swing.JTextField();
        txtquocgia = new javax.swing.JTextField();
        txtthanhpho = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtmota = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("ID :");

        jLabel3.setText("Địa Chỉ");

        jLabel4.setText("Mã :");

        jLabel5.setText(" SDT :");

        jLabel6.setText("Ngày sinh :");

        jLabel7.setText("Tên :");

        jLabel8.setText("Quốc Gia");

        jLabel9.setText("Thành phố");

        txtid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidActionPerformed(evt);
            }
        });

        txtsdt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsdtActionPerformed(evt);
            }
        });

        txtngsinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtngsinhActionPerformed(evt);
            }
        });

        txtten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttenActionPerformed(evt);
            }
        });

        txtma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmaActionPerformed(evt);
            }
        });

        txtdiachi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdiachiActionPerformed(evt);
            }
        });

        txtquocgia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtquocgiaActionPerformed(evt);
            }
        });

        txtthanhpho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtthanhphoActionPerformed(evt);
            }
        });

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Mã KH", "Tên KH", "Ngày Sinh", "SDT", "Địa Chỉ", "Thành phố", "Quốc Gia", "Mô Tả"
            }
        ));
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl);
        if (tbl.getColumnModel().getColumnCount() > 0) {
            tbl.getColumnModel().getColumn(8).setHeaderValue("Mô Tả");
        }

        jButton1.setText("Thêm ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Xóa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Sửa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        txtmota.setColumns(20);
        txtmota.setRows(5);
        jScrollPane2.setViewportView(txtmota);

        jLabel10.setText("Mô Tả ");

        jButton4.setText("Tìm Kiếm");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtma, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtngsinh, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtten, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(47, 47, 47)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtdiachi, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtquocgia, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel9)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtthanhpho, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(67, 67, 67)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton2)
                                            .addComponent(jButton3)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButton1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton4))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(25, 25, 25)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(29, 29, 29))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton3, jButton4});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdiachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton4))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel9)
                    .addComponent(txtma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtthanhpho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(txtten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtquocgia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jButton2)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtngsinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton2, jButton3, jButton4});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidActionPerformed

    private void txtsdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsdtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsdtActionPerformed

    private void txttenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttenActionPerformed

    private void txtmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmaActionPerformed

    private void txtdiachiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdiachiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdiachiActionPerformed

    private void txtquocgiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtquocgiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtquocgiaActionPerformed

    private void txtthanhphoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtthanhphoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtthanhphoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
            if (chkvalidate()) {
            if (ALLKH.getBYmaKh(txtma.getText()) == null) {
                int a = JOptionPane.showConfirmDialog(this, "Thêm khách hàng" + txtma.getText() + "?", "Lỗi", JOptionPane.YES_NO_OPTION);
                if (a == JOptionPane.YES_OPTION) {
                    ALLKH.Add(getKH());
                    fillTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Mã khách đã tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         if (Chk.chknull("chọn khách trước khi xóa", txtma)) {
        } else {
            if (ALLKH.getBYmaKh(txtma.getText()) != null) {
                int a = JOptionPane.showConfirmDialog(this, "Xóa khách hàng" + txtma.getText() + "?", "Lỗi", JOptionPane.YES_NO_OPTION);
                if (a == JOptionPane.YES_OPTION) {
                    ALLKH.delete(getKH());
                    fillTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Khách Hàng không tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtngsinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtngsinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtngsinhActionPerformed

    private void tblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMouseClicked
        // TODO add your handling code here:
      int index = tbl.getSelectedRow();
        show(index);
    }//GEN-LAST:event_tblMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(khachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(khachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(khachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(khachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new khachHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tbl;
    private javax.swing.JTextField txtdiachi;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtma;
    private javax.swing.JTextArea txtmota;
    private javax.swing.JTextField txtngsinh;
    private javax.swing.JTextField txtquocgia;
    private javax.swing.JTextField txtsdt;
    private javax.swing.JTextField txtten;
    private javax.swing.JTextField txtthanhpho;
    // End of variables declaration//GEN-END:variables
}
