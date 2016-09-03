/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.controller;

import ian.RegisterRawatJalan.DatabaseHelper.DatabaseHelper;
import Nciz.RegisterRawatJalan.Entity.DBDetailPeriksa;
import Nciz.RegisterRawatJalan.Entity.DBDokter;
import Nciz.RegisterRawatJalan.Entity.DBPasien;
import Nciz.RegisterRawatJalan.Entity.DBResepDokter;
import Nciz.RegisterRawatJalan.ex.PasienException;
import Nciz.RegisterRawatJalan.frame.panel.MenuPeriksa;
import Nciz.RegisterRawatJalan.model.PeriksaModel;
import java.awt.Color;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ncizh
 */
public class PeriksaController {

    private PeriksaModel periksaModel;

    public void setPeriksaModel(PeriksaModel periksaModel) {
        this.periksaModel = periksaModel;
    }

    public void insert(MenuPeriksa menuPeriksa) {
        String idPeriksa;
        Date tglPeriksa;
        String idPasien;
        DBDokter dokter;
        String diagnosa;
        String alergiObat;
        List<DBResepDokter> resepDokters;
        idPeriksa = menuPeriksa.getTxtIdPeriksa().getText().trim();
        tglPeriksa = menuPeriksa.getTxtTanggalPeriksa().getDate();
        idPasien = menuPeriksa.getTxtIdPasien().getText().trim();
        dokter = (DBDokter) menuPeriksa.getComboDokter().getSelectedItem();
        diagnosa = menuPeriksa.getTxtDiagnosa().getText().trim();
        alergiObat = menuPeriksa.getTxtAlergiObat().getText().trim();
        resepDokters = menuPeriksa.getTabelModelResepDokter().getData();
        if (tglPeriksa == null
                && idPasien.isEmpty()
                && dokter == null
                && diagnosa.isEmpty()
                && alergiObat.isEmpty()
                && resepDokters.isEmpty()) {
            menuPeriksa.getRegRwtJalan().getMessageComponent1().show("Isi semua data", Color.darkGray, Color.red);
        } else if (tglPeriksa == null) {
            menuPeriksa.getRegRwtJalan().getMessageComponent1().show("Tangg periksa masih kosong", Color.darkGray, Color.red);
        } else if (idPasien.isEmpty()) {
            menuPeriksa.getRegRwtJalan().getMessageComponent1().show("Id pasien masih kosong", Color.darkGray, Color.red);
        } else if (dokter == null) {
            menuPeriksa.getRegRwtJalan().getMessageComponent1().show("Dokter masih kosong", Color.darkGray, Color.red);
        } else if (diagnosa.isEmpty()) {
            menuPeriksa.getRegRwtJalan().getMessageComponent1().show("Dignosa masih kosong", Color.darkGray, Color.red);
        } else if (alergiObat.isEmpty()) {
            menuPeriksa.getRegRwtJalan().getMessageComponent1().show("Alergi masih kosong", Color.darkGray, Color.red);
        } else if (resepDokters.isEmpty()) {
            menuPeriksa.getRegRwtJalan().getMessageComponent1().show("Resep dokter masih kosong", Color.darkGray, Color.red);
        } else if (tglPeriksa.compareTo(new Date()) > 0) {
            menuPeriksa.getRegRwtJalan().getMessageComponent1().show("Tanggal periksa tidak valid", Color.darkGray, Color.red);
        } else {
            try {
                periksaModel.setIdPeriksa(Integer.parseInt(idPeriksa));
                periksaModel.setTgl(tglPeriksa);
                DBDetailPeriksa detailPeriksa;
                detailPeriksa = new DBDetailPeriksa();
                detailPeriksa.setDokter(dokter);
                detailPeriksa.setDiagnosa(diagnosa);
                detailPeriksa.setAlergiObat(alergiObat);
                DBPasien kode = DatabaseHelper.getPasien().getkode(Integer.parseInt(idPasien));
                if (kode instanceof DBPasien) {
                    detailPeriksa.setPasien(kode);
                    periksaModel.setResepDokters(resepDokters);
                    periksaModel.setDetailPeriksa(detailPeriksa);
                    periksaModel.insert();

                } else {
                    menuPeriksa.getRegRwtJalan().getMessageComponent1().show("Id periksa tidak tersedia", Color.darkGray, Color.RED);
                }
            } catch (PasienException ex) {
                Logger.getLogger(PeriksaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void update(MenuPeriksa menuPeriksa) {
        String idPeriksa;
        Date tglPeriksa;
        String idPasien;
        DBDokter dokter;
        String diagnosa;
        String alergiObat;
        idPeriksa = menuPeriksa.getTxtIdPeriksa().getText().trim();
        tglPeriksa = menuPeriksa.getTxtTanggalPeriksa().getDate();
        idPasien = menuPeriksa.getTxtIdPasien().getText().trim();
        dokter = (DBDokter) menuPeriksa.getComboDokter().getSelectedItem();
        diagnosa = menuPeriksa.getTxtDiagnosa().getText().trim();
        alergiObat = menuPeriksa.getTxtAlergiObat().getText().trim();
        if (tglPeriksa == null
                && idPasien.isEmpty()
                && dokter == null
                && diagnosa.isEmpty()
                && alergiObat.isEmpty()) {
            menuPeriksa.getRegRwtJalan().getMessageComponent1().show("Isi semua data", Color.darkGray, Color.red);
        } else if (tglPeriksa == null) {
            menuPeriksa.getRegRwtJalan().getMessageComponent1().show("Tangg periksa masih kosong", Color.darkGray, Color.red);
        } else if (idPasien.isEmpty()) {
            menuPeriksa.getRegRwtJalan().getMessageComponent1().show("Id pasien masih kosong", Color.darkGray, Color.red);
        } else if (dokter == null) {
            menuPeriksa.getRegRwtJalan().getMessageComponent1().show("Dokter masih kosong", Color.darkGray, Color.red);
        } else if (diagnosa.isEmpty()) {
            menuPeriksa.getRegRwtJalan().getMessageComponent1().show("Dignosa masih kosong", Color.darkGray, Color.red);
        } else if (alergiObat.isEmpty()) {
            menuPeriksa.getRegRwtJalan().getMessageComponent1().show("Alergi masih kosong", Color.darkGray, Color.red);
        } else if (tglPeriksa.compareTo(new Date()) > 0) {
            menuPeriksa.getRegRwtJalan().getMessageComponent1().show("Tanggal periksa tidak valid", Color.darkGray, Color.red);
        } else {
            try {
                periksaModel.setIdPeriksa(Integer.parseInt(idPeriksa));
                periksaModel.setTgl(tglPeriksa);
                DBDetailPeriksa detailPeriksa;
                detailPeriksa = new DBDetailPeriksa();
                detailPeriksa.setDokter(dokter);
                detailPeriksa.setDiagnosa(diagnosa);
                detailPeriksa.setAlergiObat(alergiObat);
                DBPasien kode = DatabaseHelper.getPasien().getkode(Integer.parseInt(idPasien));
                if (kode instanceof DBPasien) {
                    detailPeriksa.setPasien(kode);

                    periksaModel.setDetailPeriksa(detailPeriksa);
                    periksaModel.update();

                } else {
                    menuPeriksa.getRegRwtJalan().getMessageComponent1().show("Id periksa tidak tersedia", Color.darkGray, Color.RED);
                }
            } catch (PasienException ex) {
                Logger.getLogger(PeriksaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void detele(Integer kodePeriksa){
        periksaModel.delete(kodePeriksa);
    }
}
