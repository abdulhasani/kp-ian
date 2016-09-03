/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.frame.panel;

import Nciz.RegisterRawatJalan.Gambar.EfekGambar;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Ncizh
 */
public class RenderMenuUtama extends JButton {

    private boolean over;
    private boolean press;
    public ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Nciz/RegisterRawatJalan/Gambar/monitor-icon6.png"));

    public RenderMenuUtama() {
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        BufferedImage efekKaca;
        efekKaca = EfekGambar.getEfekKaca(imageIcon.getImage());
        setIcon(new ImageIcon(efekKaca));
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                setOver(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setOver(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setPress(true);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setPress(false);
            }
        });
   
}

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public boolean isPress() {
        return press;
    }

    public void setPress(boolean press) {
        this.press = press;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Color cahaya = new Color(1f, 1f, 1f, 0.7f);
        final Color gelap = new Color(1f, 1f, 1f, 0f);
        GradientPaint paint;

        if (isOver()) {
            RoundRectangle2D.Double bentuk = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 50, 50);
            if (isPress()) {
                paint = new GradientPaint(0, 0, gelap, 0, getHeight(), cahaya);
            } else {
                paint = new GradientPaint(0, 0, cahaya, 0, getHeight(), gelap);
            }
            Graphics2D create = (Graphics2D) g.create();
            create.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            create.setPaint(paint);
            create.fill(bentuk);
        }

    }
}
