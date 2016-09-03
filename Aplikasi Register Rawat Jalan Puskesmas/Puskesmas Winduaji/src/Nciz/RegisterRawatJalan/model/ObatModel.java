/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.model;

import ian.RegisterRawatJalan.DatabaseHelper.DatabaseHelper;
import Nciz.RegisterRawatJalan.Entity.DBObat;
import Nciz.RegisterRawatJalan.ex.ObatException;
import Nciz.RegisterRawatJalan.listener.ListenerObat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ncizh
 */
public class ObatModel {

    private Integer IdObat;
    private String nama;
    private Integer stok_awal;
    private Integer penerimaan;
    private Integer persediaan;
    private Integer pemakaian;
    private Integer sisa_stok;
    private Integer permintaan;
    private Date tglExp;
    private Double hargaSatuan;
    private ListenerObat listenerObat;

    public Integer getPemakaian() {
        return pemakaian;
    }

    public void setPemakaian(Integer pemakaian) {
        this.pemakaian = pemakaian;
    }

    public Integer getPenerimaan() {
        return penerimaan;
    }

    public void setPenerimaan(Integer penerimaan) {
        this.penerimaan = penerimaan;
    }

    public Integer getPermintaan() {
        return permintaan;
    }

    public void setPermintaan(Integer permintaan) {
        this.permintaan = permintaan;
    }

    public Integer getPersediaan() {
        return persediaan;
    }

    public void setPersediaan(Integer persediaan) {
        this.persediaan = persediaan;
    }

    public Integer getSisa_stok() {
        return sisa_stok;
    }

    public void setSisa_stok(Integer sisa_stok) {
        this.sisa_stok = sisa_stok;
    }

    public Integer getStok_awal() {
        return stok_awal;
    }

    public void setStok_awal(Integer stok_awal) {
        this.stok_awal = stok_awal;
    }

    
    public Integer getIdObat() {
        return IdObat;
    }

    public void setIdObat(Integer IdObat) {
        this.IdObat = IdObat;
    }

    public Double getHargaSatuan() {
        return hargaSatuan;
    }

    public void setHargaSatuan(Double hargaSatuan) {
        this.hargaSatuan = hargaSatuan;
    }

    

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Date getTglExp() {
        return tglExp;
    }

    public void setTglExp(Date tglExp) {
        this.tglExp = tglExp;
    }

    public ListenerObat getListenerObat() {
        return listenerObat;
    }

    public void setListenerObat(ListenerObat listenerObat) {
        this.listenerObat = listenerObat;
    }

    private void fireOnInsert(DBObat obat) {
        if (listenerObat != null) {
            listenerObat.onInsert(obat);
        }
    }

    private void fireOnUpdate(DBObat obat) {
        if (listenerObat != null) {
            listenerObat.onUpdate(obat);
        }
    }

    private void fireOnDelete(Integer kode) {
        if (listenerObat != null) {
            listenerObat.onDetele(kode);
        }
    }

    public void insert() {
        try {
            DBObat obat;
            obat = new DBObat();
            obat.setIdobat(IdObat);
            obat.setNama_obat(nama);
            obat.setStok_awal(stok_awal);
            obat.setPenerimaan(penerimaan);
            obat.setPersediaan(persediaan);
            obat.setPemakaian(pemakaian);
            obat.setSisa_stok(sisa_stok);
            obat.setPermintaan(permintaan);
            obat.setTgl(tglExp);
            obat.setHarga_satuan(hargaSatuan);
            
            DatabaseHelper.getObat().insert(obat);
            fireOnInsert(obat);
        } catch (ObatException ex) {
            Logger.getLogger(DBObat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update() {
        try {
            DBObat obat;
            obat = new DBObat();
            obat.setIdobat(IdObat);
            obat.setNama_obat(nama);
            obat.setStok_awal(stok_awal);
            obat.setPenerimaan(penerimaan);
            obat.setPersediaan(persediaan);
            obat.setPemakaian(pemakaian);
            obat.setSisa_stok(sisa_stok);
            obat.setPermintaan(permintaan);
            obat.setTgl(tglExp);
            obat.setHarga_satuan(hargaSatuan);
            
            DatabaseHelper.getObat().update(obat);
            fireOnUpdate(obat);
        } catch (ObatException ex) {
            Logger.getLogger(DBObat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Integer kode) {
        try {
            DatabaseHelper.getObat().delete(kode);
        } catch (ObatException ex) {
            Logger.getLogger(ObatModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
