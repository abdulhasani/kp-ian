/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.Entity;

import Nciz.RegisterRawatJalan.render.RenderTabelVirtualRiwayat;
import com.stripbandunk.jwidget.annotation.TableColumn;
import java.util.Date;

/**
 *
 * @author Ncizh
 */
public class DbVirtualRiwayatDokter {

    @TableColumn(number = 1, name = "TANGGAL PERIKSA", size = 20)
    private Date TglPeriksa;
    @TableColumn(number = 2, name = "DIAGNOSA", size = 40)
    private String diagnosa;
    @TableColumn(number = 3, name = "NAMA OBAT", size = 25, renderer = RenderTabelVirtualRiwayat.class)
    private DBDetailPeriksa namaObat;
    @TableColumn(number = 4, name = "JUMLAH OBAT/BUTIR", size = 20)
    private Integer jumlahItem;

    public Date getTglPeriksa() {
        return TglPeriksa;
    }

    public void setTglPeriksa(Date TglPeriksa) {
        this.TglPeriksa = TglPeriksa;
    }

    public String getDiagnosa() {
        return diagnosa;
    }

    public void setDiagnosa(String diagnosa) {
        this.diagnosa = diagnosa;
    }

    public Integer getJumlahItem() {
        return jumlahItem;
    }

    public void setJumlahItem(Integer jumlahItem) {
        this.jumlahItem = jumlahItem;
    }

    public DBDetailPeriksa getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(DBDetailPeriksa namaObat) {
        this.namaObat = namaObat;
    }

}
