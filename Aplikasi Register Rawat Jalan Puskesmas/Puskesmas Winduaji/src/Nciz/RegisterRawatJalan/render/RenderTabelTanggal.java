package Nciz.RegisterRawatJalan.render;


import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
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
public class RenderTabelTanggal extends DefaultTableCellRenderer {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", getDefaultLocale());

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (value instanceof Date) {
            Date date = (Date) value;
            String ubah = dateFormat.format(date);
            label.setText(ubah);
        }
        return label;
    }
}
