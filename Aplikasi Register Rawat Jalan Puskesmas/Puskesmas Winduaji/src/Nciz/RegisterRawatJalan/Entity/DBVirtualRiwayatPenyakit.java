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
public class DBVirtualRiwayatPenyakit {

    @TableColumn(number = 1, name = "TANGGAL PERIKSA", size = 20, renderer = RenderTabelTanggal.class)
    private Date Tglperiksa; 
    @TableColumn(number = 2, name = "DIAGNOSA PENYAKIT", size = 33, renderer = RenderTabelDiagnosa.class)
    private String  Diagnosa;


    public Date getTglPeriksa() {
        return Tglperiksa;
    }

    public void setTglPeriksa(Date Tglperiksa) {
        this.Tglperiksa = Tglperiksa;
    }

    public String getDiagnosa() {
        return Diagnosa;
    }

    public void setDiagnosa(String Diagnosa) {
        this.Diagnosa = Diagnosa;
    }
    
    private DBDetailPeriksa virtualInsert;
    
    public DBDetailPeriksa getVirtualInsert() {
        return virtualInsert;
    }

    public void setVirtualInsert(DBDetailPeriksa virtualInsert) {
        this.virtualInsert = virtualInsert;
    }

    public void setTglPeriksa(DBDetailPeriksa d) {
        this.Tglperiksa = Tglperiksa;
        
    }
    

    
   
}
