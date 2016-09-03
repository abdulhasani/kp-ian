/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.Gambar;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author Ncizh
 */
public class EfekGambar {
     public static final BufferedImage getBuffered(final Image source){
        final int lebar=source.getWidth(null);
        final int tinggi=source.getHeight(null);
        final BufferedImage dest=new BufferedImage(lebar, tinggi, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = (Graphics2D) dest.getGraphics();
        graphics.drawImage(source, 0, 0,null);
        graphics.dispose();
        
        return dest;
    }
    public static final BufferedImage getEfekKaca(final BufferedImage source){
        final BufferedImage dest=new BufferedImage(source.getWidth(),source.getHeight()
                +source.getHeight()/2,BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = (Graphics2D) dest.getGraphics();
        graphics.drawImage(source, 0, 0,null);
        graphics.scale(1, -1);
        graphics.drawImage(source, 0, -source.getHeight()*2, null);
        graphics.scale(1, -1);
        graphics.translate(0,source.getHeight());
        graphics.setPaint(new GradientPaint(0, 0,
                new Color(1f,1f,1f,0.3f),0,source.getHeight()/2,
                new Color(1f,1f,1f,0f)));
        graphics.setComposite(AlphaComposite.DstIn);
        graphics.fillRect(0,0, source.getWidth(), source.getHeight());
        graphics.dispose();
        return dest;
    }   
    public static final BufferedImage getEfekKaca(final Image source){
        return EfekGambar.getEfekKaca(EfekGambar.getBuffered(source));
    }

}
