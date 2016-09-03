package Nciz.RegisterRawatJalan.render;

import Nciz.RegisterRawatJalan.Entity.DBDokter;
import Nciz.RegisterRawatJalan.Entity.DBObat;
import Nciz.RegisterRawatJalan.Entity.DBPasien;
import Nciz.RegisterRawatJalan.Entity.DBPeriksa;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class RenderTabelDetailPeriksa extends DefaultTableCellRenderer {
    
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", getDefaultLocale());
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (value instanceof DBPeriksa) {
            DBPeriksa dbPeriksa = (DBPeriksa) value;
            
            String ubah = dateFormat.format(dbPeriksa.getTgl_periksa());
            label.setText(ubah);
        }
        if (value instanceof DBPasien) {
            DBPasien dbPasien = (DBPasien) value;
            label.setText(dbPasien.getNama_pasien());
        }
        if (value instanceof DBDokter) {
            DBDokter dbDokter = (DBDokter) value;
            label.setText(dbDokter.getNama_dokter());
        }
        if (value instanceof DBObat) {
            DBObat dbObat = (DBObat) value;
            label.setText(dbObat.getNama_obat());
        }
        return label;
    }
}
