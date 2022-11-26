/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;

/**
 *
 * @author dinhq
 */
public class Time extends Thread {

    private JLabel label;

    public Time(JLabel label) {
        this.label = label;
    }

    @Override
    public void run() {

        while (true) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy "
                    + " HH:mm:ss");
            Date d = new Date();
            this.label.setText(sdf.format(d));

            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {

            }
        }
    }
}