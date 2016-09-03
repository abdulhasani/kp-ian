package Nciz.RegisterRawatJalan.render;

import Nciz.RegisterRawatJalan.Entity.DBDetailPeriksa;
import java.awt.Component;
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
public class RenderTabelKeterangan extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (value instanceof DBDetailPeriksa) {
            DBDetailPeriksa dbDetailPeriksa = (DBDetailPeriksa) value;
            label.setText(dbDetailPeriksa.getPasien().getKunjungan());
        }

        return label;
    }
}
