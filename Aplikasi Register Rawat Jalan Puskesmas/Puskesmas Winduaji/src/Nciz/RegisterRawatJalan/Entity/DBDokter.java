/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.Entity;

import com.stripbandunk.jwidget.annotation.TableColumn;

/**
 *
 * @author Ncizh
 */
public class DBDokter {

    private Integer iddokter;
    @TableColumn(number=1,name="NAMA",size=25)
    private String nama_dokter;
    @TableColumn(number=2,name="ALAMAT",size=40)
    private String alamat_dokter;
    @TableColumn(number=3,name="TELEPON",size=25)
    private String telp_dokter;
    @TableColumn(number=4,name="BIAYA",size=20)
    private Double biaya;

    public String getAlamat_dokter() {
        return alamat_dokter;
    }

    public void setAlamat_dokter(String alamat_dokter) {
        this.alamat_dokter = alamat_dokter;
    }

    /**
     * @return the iddokter
     */
    public Integer getIddokter() {
        return iddokter;
    }

    /**
     * @param iddokter the iddokter to set
     */
    public void setIddokter(Integer iddokter) {
        this.iddokter = iddokter;
    }

    /**
     * @return the nama_dokter
     */
    public String getNama_dokter() {
        return nama_dokter;
    }

    /**
     * @param nama_dokter the nama_dokter to set
     */
    public void setNama_dokter(String nama_dokter) {
        this.nama_dokter = nama_dokter;
    }

    /**
     * @return the telp_dokter
     */
    public String getTelp_dokter() {
        return telp_dokter;
    }

    /**
     * @param telp_dokter the telp_dokter to set
     */
    public void setTelp_dokter(String telp_dokter) {
        this.telp_dokter = telp_dokter;
    }

    /**
     * @return the biaya
     */
    public Double getBiaya() {
        return biaya;
    }

    /**
     * @param biaya the biaya to set
     */
    public void setBiaya(Double biaya) {
        this.biaya = biaya;
    }
}
