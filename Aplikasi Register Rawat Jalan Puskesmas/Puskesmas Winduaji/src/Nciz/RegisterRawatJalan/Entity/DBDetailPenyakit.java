/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.Entity;

import Nciz.RegisterRawatJalan.render.RenderTabelVirtualRiwayat1;
import com.stripbandunk.jwidget.annotation.TableColumn;

/**
 *
 * @author Lucifer-PC
 */
public class DBDetailPenyakit {

     @TableColumn(number = 1, name = "TANGGAL PERIKSA", size = 20, renderer = RenderTabelVirtualRiwayat1.class)
    private DBDetailPeriksa periksa; 
    @TableColumn(number = 2, name = "DIAGNOSA PENYAKIT", size = 33)
    private String  Diagnosa;


    public DBDetailPeriksa getTglPeriksa(DBDetailPeriksa periksa) {
        return periksa;
    }

    public void setTglPeriksa(DBDetailPeriksa periksa) {
        this.periksa = periksa;
    }

    public String getDiagnosa() {
        return Diagnosa;
    }

    public void setDiagnosa(String Diagnosa) {
        this.Diagnosa = Diagnosa;
    }

}
