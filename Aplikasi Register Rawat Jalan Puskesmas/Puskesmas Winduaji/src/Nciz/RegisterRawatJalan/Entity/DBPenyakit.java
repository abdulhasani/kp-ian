/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.Entity;

import Nciz.RegisterRawatJalan.render.RenderTabelDiagnosa;
import Nciz.RegisterRawatJalan.render.RenderTabelTanggal;
import com.stripbandunk.jwidget.annotation.TableColumn;
import java.util.Date;

/**
 *
 * @author Lucifer-PC
 */
public class DBPenyakit {
    
    
    @TableColumn(number = 1, name = "TANGGAL PERIKSA",renderer=RenderTabelTanggal.class)
    private Date tgl_periksa;
    @TableColumn(number = 2, name = "DIAGNOSA",renderer=RenderTabelDiagnosa.class)
    private String diagnosa;

    public String getDiagnosa() {
        return diagnosa;
    }

    public void setDiagnosa( String diagnosa) {
        this.diagnosa = diagnosa;
    }

    
}
