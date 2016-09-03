/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.controller;

import Nciz.RegisterRawatJalan.frame.panel.MenuPasien;
import Nciz.RegisterRawatJalan.model.PasienModel;
import java.awt.Color;
import java.util.Date;

/**
 *
 * @author Ncizh
 */
public class PasienController {

    private PasienModel pasienModel;

    public void setPasienModel(PasienModel pasienModel) {
        this.pasienModel = pasienModel;
    }

    public void insert(MenuPasien menuPasien) {
        String idPasein;
        String nama;
        String umur;
        String nama_kk;
        String alamat;
        String gender;
        String kunjungan;
        String unit;
        String jenis;



        idPasein = menuPasien.getTxtIdPasien().getText().trim();
        nama = menuPasien.getTxtNama().getText().trim();
        umur = menuPasien.getTxtUmur().getText().trim();
        nama_kk = menuPasien.getTxtNama_kk().getText().trim();
        alamat = menuPasien.getTxtAlamat().getText().trim();

        if (menuPasien.getRdCowo().isSelected() == true) {
            gender = "Laki-laki";

        } else if (menuPasien.getRdCewe().isSelected() == true) {
            gender = "Perempuan";
        } else {
            gender = null;
        }

        if (menuPasien.getRdbaru().isSelected() == true) {
            kunjungan = "Baru";

        } else if (menuPasien.getRdlama().isSelected() == true) {
            kunjungan = "Lama";
        } else {
            kunjungan = null;
        }

        if (menuPasien.getRdbayar().isSelected() == true) {
            jenis = "Bayar";
        } else if (menuPasien.getRdgratis().isSelected() == true) {
            jenis = "Gratis";

        } else if (menuPasien.getRdjps().isSelected() == true) {
            jenis = "BPJS";

        } else if (menuPasien.getRdHI().isSelected() == true) {
            jenis = "HI";

        } else if (menuPasien.getRdaskes().isSelected() == true) {
            jenis = "Askes";

        } else if (menuPasien.getRdjamsostek().isSelected() == true) {
            jenis = "Jamsostek";
        } else {
            jenis = null;
        }

        if (menuPasien.getRdBPU().isSelected() == true) {
            unit = "BPU";
        } else if (menuPasien.getRdKIA().isSelected() == true) {
            unit = "KIA";
        } else if (menuPasien.getRdGIGI().isSelected() == true) {
            unit = "GIGI";
        } else {
            unit = null;
        }

        if (nama.isEmpty()
                && nama_kk.isEmpty()
                && umur.isEmpty()
                && alamat.isEmpty()
                && gender == null
                && jenis == null
                && kunjungan == null
                && unit == null) {
            menuPasien.getRegRwtJalan().getMessageComponent1().show("Harap isi semua data ", Color.darkGray, Color.red);
        } else if (nama.isEmpty()) {
            menuPasien.getRegRwtJalan().getMessageComponent1().show("Nama masih kosong ", Color.darkGray, Color.red);
        } else if (umur.isEmpty()) {
            menuPasien.getRegRwtJalan().getMessageComponent1().show("Umur masih kosong ", Color.darkGray, Color.red);
        } else if (nama_kk.isEmpty()) {
            menuPasien.getRegRwtJalan().getMessageComponent1().show("nama KK masih kosong ", Color.darkGray, Color.red);
        } else if (alamat.isEmpty()) {
            menuPasien.getRegRwtJalan().getMessageComponent1().show("Alamat masih kosong ", Color.darkGray, Color.red);
        } else if (gender == null) {
            menuPasien.getRegRwtJalan().getMessageComponent1().show("Pilih jenis kelamin ", Color.darkGray, Color.red);
        } else if (jenis == null) {
            menuPasien.getRegRwtJalan().getMessageComponent1().show("Pilih jenis pelayanan ", Color.darkGray, Color.red);
        } else if (kunjungan == null) {
            menuPasien.getRegRwtJalan().getMessageComponent1().show("Pilih kunjungan", Color.darkGray, Color.red);

        } else if (unit == null) {
            menuPasien.getRegRwtJalan().getMessageComponent1().show("Pilih unit tujuan ", Color.darkGray, Color.red);
        } else {
            pasienModel.setIdPasien(Integer.parseInt(idPasein));
            pasienModel.setNama(nama);
            pasienModel.setUmur(umur);
            pasienModel.setNama_kk(nama_kk);
            pasienModel.setAlamat(alamat);
            pasienModel.setGender(gender);
            pasienModel.setKunjungan(kunjungan);
            pasienModel.setUnit(unit);
            pasienModel.setJenis(jenis);
            pasienModel.insert();
            menuPasien.reloadDate();
        }
    }

    public void update(MenuPasien menuPasien) {
      String idPasein;
        String nama;
        String umur;
        String nama_kk;
        String alamat;
        String gender;
        String kunjungan;
        String unit;
        String jenis;



        idPasein = menuPasien.getTxtIdPasien().getText().trim();
        nama = menuPasien.getTxtNama().getText().trim();
        umur = menuPasien.getTxtUmur().getText().trim();
        nama_kk = menuPasien.getTxtNama_kk().getText().trim();
        alamat = menuPasien.getTxtAlamat().getText().trim();

        if (menuPasien.getRdCowo().isSelected() == true) {
            gender = "Laki-laki";

        } else if (menuPasien.getRdCewe().isSelected() == true) {
            gender = "Perempuan";
        } else {
            gender = null;
        }

        if (menuPasien.getRdbaru().isSelected() == true) {
            kunjungan = "Baru";

        } else if (menuPasien.getRdlama().isSelected() == true) {
            kunjungan = "Lama";
        } else {
            kunjungan = null;
        }

        if (menuPasien.getRdbayar().isSelected() == true) {
            jenis = "Bayar";
        } else if (menuPasien.getRdgratis().isSelected() == true) {
            jenis = "Gratis";

        } else if (menuPasien.getRdjps().isSelected() == true) {
            jenis = "BPJS";

        } else if (menuPasien.getRdHI().isSelected() == true) {
            jenis = "HI";

        } else if (menuPasien.getRdaskes().isSelected() == true) {
            jenis = "Askes";

        } else if (menuPasien.getRdjamsostek().isSelected() == true) {
            jenis = "Jamsostek";
        } else {
            jenis = null;
        }

        if (menuPasien.getRdBPU().isSelected() == true) {
            unit = "BPU";
        } else if (menuPasien.getRdKIA().isSelected() == true) {
            unit = "KIA";
        } else if (menuPasien.getRdGIGI().isSelected() == true) {
            unit = "GIGI";
        } else {
            unit = null;
        }

        if (nama.isEmpty()
                && nama_kk.isEmpty()
                && umur.isEmpty()
                && alamat.isEmpty()
                && gender == null
                && jenis == null
                && kunjungan == null
                && unit == null) {
            menuPasien.getRegRwtJalan().getMessageComponent1().show("Harap isi semua data ", Color.darkGray, Color.red);
        } else if (nama.isEmpty()) {
            menuPasien.getRegRwtJalan().getMessageComponent1().show("Nama masih kosong ", Color.darkGray, Color.red);
        } else if (umur.isEmpty()) {
            menuPasien.getRegRwtJalan().getMessageComponent1().show("Umur masih kosong ", Color.darkGray, Color.red);
        } else if (nama_kk.isEmpty()) {
            menuPasien.getRegRwtJalan().getMessageComponent1().show("nama KK masih kosong ", Color.darkGray, Color.red);
        } else if (alamat.isEmpty()) {
            menuPasien.getRegRwtJalan().getMessageComponent1().show("Alamat masih kosong ", Color.darkGray, Color.red);
        } else if (gender == null) {
            menuPasien.getRegRwtJalan().getMessageComponent1().show("Pilih jenis kelamin ", Color.darkGray, Color.red);
        } else if (jenis == null) {
            menuPasien.getRegRwtJalan().getMessageComponent1().show("Pilih jenis pelayanan ", Color.darkGray, Color.red);
        } else if (kunjungan == null) {
            menuPasien.getRegRwtJalan().getMessageComponent1().show("Pilih kunjungan", Color.darkGray, Color.red);

        } else if (unit == null) {
            menuPasien.getRegRwtJalan().getMessageComponent1().show("Pilih unit tujuan ", Color.darkGray, Color.red);
        } else {
            pasienModel.setIdPasien(Integer.parseInt(idPasein));
            pasienModel.setNama(nama);
            pasienModel.setUmur(umur);
            pasienModel.setNama_kk(nama_kk);
            pasienModel.setAlamat(alamat);
            pasienModel.setGender(gender);
            pasienModel.setKunjungan(kunjungan);
            pasienModel.setUnit(unit);
            pasienModel.setJenis(jenis);
            pasienModel.update();
            menuPasien.reloadDate();
        }
    }

    public void delete(Integer kode, MenuPasien menuPasien) {
        pasienModel.delete(kode);
        menuPasien.reloadDate();
    }
}
