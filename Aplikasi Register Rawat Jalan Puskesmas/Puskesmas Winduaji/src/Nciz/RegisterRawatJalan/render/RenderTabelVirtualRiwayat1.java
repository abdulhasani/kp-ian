package Nciz.RegisterRawatJalan.render;

import Nciz.RegisterRawatJalan.Entity.*;
import java.awt.Component;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Ncizh
 */
public class RenderTabelVirtualRiwayat1 extends DefaultTableCellRenderer {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", getDefaultLocale());

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if(value instanceof DBPeriksa){
            DBPeriksa dBPeriksa = (DBPeriksa) value;
            if(dBPeriksa.getDiagnosa() instanceof DBDetailPeriksa){
                label.setText(dBPeriksa.getDiagnosa().toString());
                
            }
        }
        return label;
    }
}
