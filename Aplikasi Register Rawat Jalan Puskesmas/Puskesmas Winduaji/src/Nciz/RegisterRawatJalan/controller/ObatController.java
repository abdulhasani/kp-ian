/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.controller;

import ian.RegisterRawatJalan.DatabaseHelper.DatabaseHelper;
import Nciz.RegisterRawatJalan.Entity.DBObat;
import Nciz.RegisterRawatJalan.ex.ObatException;
import Nciz.RegisterRawatJalan.frame.panel.MenuObat;
import Nciz.RegisterRawatJalan.model.ObatModel;
import java.awt.Color;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ncizh
 */
public class ObatController {

    private ObatModel obatModel;

    public void setObatModel(ObatModel obatModel) {
        this.obatModel = obatModel;
    }

    public void insert(MenuObat menuObat) {
        String idObat;
        String nama;
        String stok_awal;
        String penerimaan;
        String hargaSatuan;
        Date tgl_ex;
        idObat = menuObat.getTxtIdObat().getText().trim();
        nama = menuObat.getTxtNamaObat1().getText().trim();
        tgl_ex = menuObat.getTxtTanggalExp().getDate();
        hargaSatuan = menuObat.getTxtHargaSatuan().getText().trim();
        penerimaan = menuObat.getTxtpenerimaan().getText().trim();
        stok_awal = menuObat.getTxtstok_awal().getText().trim();
        if (nama.isEmpty()
                && tgl_ex == null
                && penerimaan.isEmpty()) {
            menuObat.getRegRwtJalan().
                    getMessageComponent1().show("Harap isi semua data", Color.darkGray, Color.red);
        } else if (nama.isEmpty()) {
            menuObat.getRegRwtJalan().
                    getMessageComponent1().show("Nama masih kosong", Color.darkGray, Color.red);
        } else if (tgl_ex == null) {
            menuObat.getRegRwtJalan().
                    getMessageComponent1().show("Tanggal Exp masih kosong", Color.darkGray, Color.red);
        } else if (tgl_ex.compareTo(new Date()) <= 0) {
            menuObat.getRegRwtJalan().
                    getMessageComponent1().show("Tanggal Exp tidak valid", Color.darkGray, Color.red);
        } else if (hargaSatuan.isEmpty()) {
            menuObat.getRegRwtJalan().
                    getMessageComponent1().show("Harga satuan tidak valid", Color.darkGray, Color.red);
        } else if (Double.parseDouble(hargaSatuan) <= 0) {
            menuObat.getRegRwtJalan().
                    getMessageComponent1().show("Haraga satuan tidak valid", Color.darkGray, Color.red);
        } else if (stok_awal.isEmpty()) {
            menuObat.getRegRwtJalan().
                    getMessageComponent1().show("Stok awal masih kosong", Color.darkGray, Color.red);
        } else if (penerimaan.isEmpty()) {
            menuObat.getRegRwtJalan().
                    getMessageComponent1().show("Penerimaan masih kosong", Color.darkGray, Color.red);
        } else if (Integer.parseInt(stok_awal) <= 0) {
            menuObat.getRegRwtJalan().
                    getMessageComponent1().show("Stok awal tidak valid", Color.darkGray, Color.red);
        } else if (Integer.parseInt(penerimaan) > 0) {
            menuObat.getRegRwtJalan().
                    getMessageComponent1().show("Penerimaan tidak valid", Color.darkGray, Color.red);
        } else {
            obatModel.setIdObat(Integer.parseInt(idObat));
            obatModel.setNama(nama);
            obatModel.setHargaSatuan(Double.parseDouble(hargaSatuan));
            obatModel.setTglExp(tgl_ex);
            obatModel.setStok_awal(Integer.parseInt(stok_awal));
            obatModel.setPenerimaan(0);
            obatModel.setPersediaan(Integer.parseInt(stok_awal));
            obatModel.setPemakaian(0);
            obatModel.setSisa_stok(Integer.parseInt(stok_awal));
            obatModel.setPermintaan(0);
            obatModel.insert();
            menuObat.reloadData();
        }
    }

    public void update(MenuObat menuObat) {
        String idObat;
        String nama;
        String stok_awal;
        String penerimaan;
        String pemakaian;
        String sisa_stok;
        String permintaan;
        String hargaSatuan;
        Date tgl_ex;

        idObat = menuObat.getTxtIdObat().getText().trim();
        nama = menuObat.getTxtNamaObat1().getText().trim();
        stok_awal = menuObat.getTxtstok_awal().getText().trim();
        penerimaan = menuObat.getTxtpenerimaan().getText().trim();
        hargaSatuan = menuObat.getTxtHargaSatuan().getText().trim();
        tgl_ex = menuObat.getTxtTanggalExp().getDate();

        if (nama.isEmpty()
                && tgl_ex == null
                && penerimaan.isEmpty()) {
            menuObat.getRegRwtJalan().
                    getMessageComponent1().show("Harap isi semua data", Color.darkGray, Color.red);
        } else if (nama.isEmpty()) {
            menuObat.getRegRwtJalan().
                    getMessageComponent1().show("Nama masih kosong", Color.darkGray, Color.red);
        } else if (tgl_ex == null) {
            menuObat.getRegRwtJalan().
                    getMessageComponent1().show("Tanggal Exp masih kosong", Color.darkGray, Color.red);
        } else if (tgl_ex.compareTo(new Date()) <= 0) {
            menuObat.getRegRwtJalan().
                    getMessageComponent1().show("Tanggal Exp tidak valid", Color.darkGray, Color.red);
        } else if (hargaSatuan.isEmpty()) {
            menuObat.getRegRwtJalan().
                    getMessageComponent1().show("Harga satuan tidak valid", Color.darkGray, Color.red);
        } else if (Double.parseDouble(hargaSatuan) <= 0) {
            menuObat.getRegRwtJalan().
                    getMessageComponent1().show("Haraga satuan tidak valid", Color.darkGray, Color.red);
        } else if (stok_awal.isEmpty()) {
            menuObat.getRegRwtJalan().
                    getMessageComponent1().show("Stok awal masih kosong", Color.darkGray, Color.red);
        } else if (penerimaan.isEmpty()) {
            menuObat.getRegRwtJalan().
                    getMessageComponent1().show("Penerimaan masih kosong", Color.darkGray, Color.red);
        } else if (Integer.parseInt(stok_awal) <= 0) {
            menuObat.getRegRwtJalan().
                    getMessageComponent1().show("Stok awal tidak valid", Color.darkGray, Color.red);
        } else {

            obatModel.setIdObat(Integer.parseInt(idObat));
            obatModel.setNama(nama);
            obatModel.setStok_awal(Integer.parseInt(stok_awal));
            try {
                DBObat kode = DatabaseHelper.getObat().getkode(Integer.parseInt(idObat));
                if (kode instanceof DBObat) {
                    if (kode.getStok_awal() > 0) {
                        Integer hitungPernerimaan = Integer.parseInt(penerimaan) + kode.getPenerimaan();
                        obatModel.setPenerimaan(hitungPernerimaan);
                        Integer hitungPersediaan = kode.getStok_awal() + hitungPernerimaan;
                        obatModel.setPersediaan(hitungPersediaan);
                        obatModel.setPemakaian(kode.getPemakaian());
                        Integer hitungSisaStok = hitungPersediaan - kode.getPemakaian();
                        obatModel.setSisa_stok(hitungSisaStok);
                        obatModel.setPermintaan(hitungPersediaan - hitungSisaStok);
                        obatModel.setTglExp(tgl_ex);
                        obatModel.setHargaSatuan(Double.parseDouble(hargaSatuan));
                    } else {
                        obatModel.setTglExp(tgl_ex);
                        obatModel.setHargaSatuan(Double.parseDouble(hargaSatuan));
                        obatModel.setPenerimaan(0);
                        obatModel.setPersediaan(Integer.parseInt(stok_awal));
                        obatModel.setPemakaian(0);
                        obatModel.setSisa_stok(Integer.parseInt(stok_awal));
                        obatModel.setPermintaan(0);
                    }
                    obatModel.update();
                }
                menuObat.reloadData();
                /**
                 */
            } catch (ObatException ex) {
                Logger.getLogger(ObatController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void delete(Integer kode, MenuObat menuObat) {
        obatModel.delete(kode);
        menuObat.reloadData();
    }
}
