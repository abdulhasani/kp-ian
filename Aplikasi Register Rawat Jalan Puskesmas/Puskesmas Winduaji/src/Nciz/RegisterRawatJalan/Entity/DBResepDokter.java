/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.Entity;

import Nciz.RegisterRawatJalan.render.RenderTabelObat;
import com.stripbandunk.jwidget.annotation.TableColumn;

/**
 *
 * @author Ncizh
 */
public class DBResepDokter {
    
    @TableColumn(number=1,name="NAMA OBAT",size=29,renderer=RenderTabelObat.class)
    private DBObat obat;
    @TableColumn(number=2,name="JUMLAH ITEM",editable=true)
    private Integer jmlahItem;

    public Integer getJmlahItem() {
        return jmlahItem;
    }

    public void setJmlahItem(Integer jmlahItem) {
        this.jmlahItem = jmlahItem;
    }

    public DBObat getObat() {
        return obat;
    }

    public void setObat(DBObat obat) {
        this.obat = obat;
    }
    
    
}
