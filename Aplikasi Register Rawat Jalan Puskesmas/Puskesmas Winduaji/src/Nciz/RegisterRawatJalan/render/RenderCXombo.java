/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.render;

import Nciz.RegisterRawatJalan.Entity.DBDokter;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

/**
 *
 * @author Ncizh
 */
public class RenderCXombo extends DefaultListCellRenderer{

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label= (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if(value instanceof DBDokter){
            DBDokter dbDokter = (DBDokter) value;
            label.setText(dbDokter.getNama_dokter());
        }
        return  label;
    }
    
}
