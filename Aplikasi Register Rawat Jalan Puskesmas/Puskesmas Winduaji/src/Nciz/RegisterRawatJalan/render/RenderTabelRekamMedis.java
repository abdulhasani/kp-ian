/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.render;

import Nciz.RegisterRawatJalan.Entity.DBDetailPeriksa;
import Nciz.RegisterRawatJalan.Entity.DBPeriksa;
import java.awt.Component;
import java.text.SimpleDateFormat;
import static javax.swing.JComponent.getDefaultLocale;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Lucifer-PC
 */
public class RenderTabelRekamMedis extends DefaultTableCellRenderer {

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
