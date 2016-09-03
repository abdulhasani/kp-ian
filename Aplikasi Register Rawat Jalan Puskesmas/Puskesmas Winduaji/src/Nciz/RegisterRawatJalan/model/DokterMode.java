/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.model;

import ian.RegisterRawatJalan.DatabaseHelper.DatabaseHelper;
import Nciz.RegisterRawatJalan.Entity.DBDokter;
import Nciz.RegisterRawatJalan.ex.DokterException;
import Nciz.RegisterRawatJalan.listener.ListenerDokter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ncizh
 */
public class DokterMode {

    private Integer iddokter;
    private String nama_dokter;
    private String alamat_dokter;
    private String telp_dokter;
    private Double biaya;
    private ListenerDokter listenerDokter;

    public String getAlamat_dokter() {
        return alamat_dokter;
    }

    public void setAlamat_dokter(String alamat_dokter) {
        this.alamat_dokter = alamat_dokter;
    }

    public Double getBiaya() {
        return biaya;
    }

    public void setBiaya(Double biaya) {
        this.biaya = biaya;
    }

    public Integer getIddokter() {
        return iddokter;
    }

    public void setIddokter(Integer iddokter) {
        this.iddokter = iddokter;
    }

    public String getNama_dokter() {
        return nama_dokter;
    }

    public void setNama_dokter(String nama_dokter) {
        this.nama_dokter = nama_dokter;
    }

    public String getTelp_dokter() {
        return telp_dokter;
    }

    public void setTelp_dokter(String telp_dokter) {
        this.telp_dokter = telp_dokter;
    }

    /**
     * @return the listenerDokter
     */
    public ListenerDokter getListenerDokter() {
        return listenerDokter;
    }

    /**
     * @param listenerDokter the listenerDokter to set
     */
    public void setListenerDokter(ListenerDokter listenerDokter) {
        this.listenerDokter = listenerDokter;
    }

    private void fireOnInsert(DBDokter dokter) {
        if (listenerDokter != null) {
            listenerDokter.onInsert(dokter);
        }
    }

    private void fireOnUpdate(DBDokter dokter) {
        if (listenerDokter != null) {
            listenerDokter.onUpdate(dokter);
        }
    }

    private void fireOnDelete(Integer kode) {
        if (listenerDokter != null) {
            listenerDokter.onDetele(kode);
        }
    }

    public void insert() {
        try {
            DBDokter dokter;
            dokter = new DBDokter();
            dokter.setIddokter(iddokter);
            dokter.setNama_dokter(nama_dokter);
            dokter.setAlamat_dokter(alamat_dokter);
            dokter.setBiaya(biaya);
            dokter.setTelp_dokter(telp_dokter);
            DatabaseHelper.getDokter().insert(dokter);
            fireOnInsert(dokter);
        } catch (DokterException ex) {
            Logger.getLogger(DokterMode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void update() {
        try {
            DBDokter dokter;
            dokter = new DBDokter();
            dokter.setIddokter(iddokter);
            dokter.setNama_dokter(nama_dokter);
            dokter.setAlamat_dokter(alamat_dokter);
            dokter.setBiaya(biaya);
            dokter.setTelp_dokter(telp_dokter);
            DatabaseHelper.getDokter().update(dokter);
            fireOnUpdate(dokter);
        } catch (DokterException ex) {
            Logger.getLogger(DokterMode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void delete(Integer kode){
        try {
            DatabaseHelper.getDokter().delete(kode);
            fireOnDelete(kode);
        } catch (DokterException ex) {
            Logger.getLogger(DokterMode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
