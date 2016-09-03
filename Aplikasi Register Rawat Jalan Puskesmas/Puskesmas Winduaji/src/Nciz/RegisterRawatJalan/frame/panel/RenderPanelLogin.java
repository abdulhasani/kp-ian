/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.frame.panel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author wannabe
 */
public class RenderPanelLogin extends JPanel {

    private Image image = new ImageIcon(getClass().getResource("/Nciz/RegisterRawatJalan/Gambar/black pearl.jpg")).getImage();

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        Graphics2D gd2 = (Graphics2D) grphcs.create();
        gd2.drawImage(image, 0, 0, getWidth(), getHeight(), null);

        gd2.dispose();
    }
}
