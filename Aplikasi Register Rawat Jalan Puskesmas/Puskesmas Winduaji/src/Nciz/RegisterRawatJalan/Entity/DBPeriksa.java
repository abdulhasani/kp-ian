/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.Entity;

import Nciz.RegisterRawatJalan.render.*;
import com.stripbandunk.jwidget.annotation.TableColumn;
import java.util.Date;

/**
 *
 * @author tian
 */
public class DBPeriksa {

    private Integer idperiksa;
    @TableColumn(number = 1, name = "Tanggal Periksa", size = 20,renderer=RenderTabelTanggal.class)
    private Date tgl_periksa;
    @TableColumn(number = 2, name = "Nama Pasien", size = 27,renderer=RenderTabelNamaPasien.class)
    private DBDetailPeriksa namaPasien;
    @TableColumn(number = 3, name = "Diagnosa", size = 26,renderer=RenderTabelDiagnosa.class)
    private DBDetailPeriksa diagnosa;
    @TableColumn(number = 4, name = "Keterangan", size = 10,renderer=RenderTabelKeterangan.class)
    private DBDetailPeriksa keterangan;
    @TableColumn(number = 5, name = "Dokter", size = 20,renderer=RenderTabelDokter.class)
    private DBDetailPeriksa dokter;
    
   private DBDetailPeriksa virtualInsert;
    
    public DBDetailPeriksa getDokter() {
        return dokter;
    }

    public DBDetailPeriksa getKeterangan() {
        return keterangan;
    }

    public DBDetailPeriksa getNamaPasien() {
        return namaPasien;
    }

    public DBDetailPeriksa getDiagnosa() {
        return diagnosa;
    }

    public void setDiagnosa(DBDetailPeriksa diagnosa) {
        this.diagnosa = diagnosa;
    }

    public void setDokter(DBDetailPeriksa dokter) {
        this.dokter = dokter;
    }

    public void setKeterangan(DBDetailPeriksa keterangan) {
        this.keterangan = keterangan;
    }

    public void setNamaPasien(DBDetailPeriksa namaPasien) {
        this.namaPasien = namaPasien;
    }
    
    public Integer getIdperiksa() {
        return idperiksa;
    }

    public void setIdperiksa(Integer idperiksa) {
        this.idperiksa = idperiksa;
    }

    public Date getTgl_periksa() {
        return tgl_periksa;
    }

    public void setTgl_periksa(Date tgl_periksa) {
        this.tgl_periksa = tgl_periksa;
    }

    public DBDetailPeriksa getVirtualInsert() {
        return virtualInsert;
    }

    public void setVirtualInsert(DBDetailPeriksa virtualInsert) {
        this.virtualInsert = virtualInsert;
    }
    
    
}
