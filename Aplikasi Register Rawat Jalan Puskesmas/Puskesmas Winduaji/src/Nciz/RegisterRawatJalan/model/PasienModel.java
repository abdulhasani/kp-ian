/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.model;

import ian.RegisterRawatJalan.DatabaseHelper.DatabaseHelper;
import Nciz.RegisterRawatJalan.Entity.DBPasien;
import Nciz.RegisterRawatJalan.ex.PasienException;
import Nciz.RegisterRawatJalan.listener.ListenerPasien;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ncizh
 */
public class PasienModel {

    private Integer idPasien;
    private String nama;
    private String umur;
    private String nama_kk;
    private String alamat;
    private String kunjungan;
    private String unit;
    private String jenis;
    private String gender;
    private ListenerPasien listenerPasien;

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getKunjungan() {
        return kunjungan;
    }

    public void setKunjungan(String kunjungan) {
        this.kunjungan = kunjungan;
    }

    public String getNama_kk() {
        return nama_kk;
    }

    public void setNama_kk(String nama_kk) {
        this.nama_kk = nama_kk;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    
    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(Integer idPasien) {
        this.idPasien = idPasien;
    }

    public ListenerPasien getListenerPasien() {
        return listenerPasien;
    }

    public void setListenerPasien(ListenerPasien listenerPasien) {
        this.listenerPasien = listenerPasien;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

   

    private void fireOnInsert(DBPasien pasien) {
        if (listenerPasien != null) {
            listenerPasien.onInsert(pasien);
        }
    }

    private void fireOnUpdate(DBPasien pasien) {
        if (listenerPasien != null) {
            listenerPasien.onUpdate(pasien);
        }
    }

    private void fireOnDelete(Integer kode) {
        if (listenerPasien != null) {
            listenerPasien.onDetele(kode);
        }
    }

    public void insert() {
        try {
            DBPasien pasien;
            pasien=new DBPasien();
            pasien.setIdpasien(idPasien);
            pasien.setNama_pasien(nama);
            pasien.setUmur(umur);
            pasien.setNama_kk(nama_kk);
            pasien.setAlamat_pasien(alamat);
            pasien.setKunjungan(kunjungan);
            pasien.setUnit_tujuan(unit);
            pasien.setJenis_pelayanan(jenis);
            pasien.setJenis_kelamin(gender);
            DatabaseHelper.getPasien().insert(pasien);
            fireOnInsert(pasien);
        } catch (PasienException ex) {
            Logger.getLogger(PasienModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update() {
         try {
            DBPasien pasien;
            pasien=new DBPasien();
            pasien.setIdpasien(idPasien);
            pasien.setNama_pasien(nama);
            pasien.setUmur(umur);
            pasien.setNama_kk(nama_kk);
            pasien.setJenis_kelamin(gender);
            pasien.setAlamat_pasien(alamat);
            pasien.setKunjungan(kunjungan);
            pasien.setUnit_tujuan(unit);
            pasien.setJenis_pelayanan(jenis);
            
            DatabaseHelper.getPasien().update(pasien);
            fireOnUpdate(pasien);
        } catch (PasienException ex) {
            Logger.getLogger(PasienModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Integer kode) {
        try {
            DatabaseHelper.getPasien().delete(kode);
        } catch (PasienException ex) {
            Logger.getLogger(PasienModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
