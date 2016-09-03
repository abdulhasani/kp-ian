package Nciz.RegisterRawatJalan.render;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Ncizh
 */
public class EditorTabelResepDokter extends AbstractCellEditor implements TableCellEditor {

    private JSpinner spinner;

    public EditorTabelResepDokter(JSpinner spinner) {
        this.spinner = spinner;
    }

    
    @Override
    public Object getCellEditorValue() {
        return spinner.getValue();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        spinner.setValue(value);
        return spinner;

    }
}
