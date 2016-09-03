/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.render;

import Nciz.RegisterRawatJalan.Entity.DBObat;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

/**
 *
 * @author Ncizh
 */
public class ListRender extends DefaultListCellRenderer{

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label= (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if(value instanceof DBObat){
            DBObat dbObat = (DBObat) value;
            label.setText(dbObat.getNama_obat()+"  = "+dbObat.getJumlahPerBijij());
        }
        return label;
    }
    
}
