/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.controller;

import ian.RegisterRawatJalan.DatabaseHelper.DatabaseHelper;
import Nciz.RegisterRawatJalan.Entity.DBAdministrator;
import Nciz.RegisterRawatJalan.ex.AdministratorException;
import Nciz.RegisterRawatJalan.frame.panel.MenuAdmin;
import Nciz.RegisterRawatJalan.model.AdminMode;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ncizh
 */
public class AdminController {

    private AdminMode adminMode;

    public void setAdminMode(AdminMode adminMode) {
        this.adminMode = adminMode;
    }

    public void insertAdmin(MenuAdmin menuAdmin) {
        String IDAdmin;
        String nama;
        String alamat;
        String telp;
        String userName;
        String password;
        String bagian;
        IDAdmin = menuAdmin.getTxtIdAdmin().getText().trim();
        nama = menuAdmin.getTxtNamaAdmin().getText().trim();
        alamat = menuAdmin.getTxtAlamatAdmin().getText().trim();
        telp = menuAdmin.getTxtTelpAdmin().getText().trim();
        userName = menuAdmin.getTxtUserNameAdmin().getText().trim();
        char[] pwd = menuAdmin.getTxtPwdAdmin().getPassword();
        bagian = menuAdmin.getComboBagian().getSelectedItem().toString().trim();
        if (IDAdmin.isEmpty()
                && nama.isEmpty()
                && alamat.isEmpty()
                && telp.isEmpty()
                && userName.isEmpty()
                && pwd.length == 0
                && bagian.isEmpty()) {
            menuAdmin.getRegRwtJalan().getMessageComponent1().show("Harap input semua data", Color.darkGray, Color.red);
        } else if (nama.isEmpty()) {
            menuAdmin.getRegRwtJalan().getMessageComponent1().show("Nama masih kosong", Color.darkGray, Color.red);
        } else if (alamat.isEmpty()) {
            menuAdmin.getRegRwtJalan().getMessageComponent1().show("Alamat masih kosong", Color.darkGray, Color.red);
        } else if (telp.isEmpty()) {
            menuAdmin.getRegRwtJalan().getMessageComponent1().show("Telepon masih kosong", Color.darkGray, Color.red);
        } else if (userName.isEmpty()) {
            menuAdmin.getRegRwtJalan().getMessageComponent1().show("User Name masih kosong", Color.darkGray, Color.red);
        } else if (pwd.length == 0) {
            menuAdmin.getRegRwtJalan().getMessageComponent1().show("Password masih kosong", Color.darkGray, Color.red);
        } else if (bagian.isEmpty()) {
            menuAdmin.getRegRwtJalan().getMessageComponent1().show("Pilih hak akses", Color.darkGray, Color.red);
        } else {
            try {
                DBAdministrator login = DatabaseHelper.getAdministrator().getLogin(userName);
                if (login instanceof DBAdministrator) {
                    menuAdmin.getRegRwtJalan().getMessageComponent1().show("User  tersebut sudah dipakai", Color.darkGray, Color.red);
                } else {
                    adminMode.setIdAdmin(Integer.parseInt(IDAdmin));
                    adminMode.setNama(nama);
                    adminMode.setAlamat(alamat);
                    adminMode.setTelepon(telp);
                    adminMode.setUserName(userName);
                    password = new String(pwd);
                    adminMode.setPassword(password);
                    adminMode.setBagian(bagian);
                    adminMode.insert();
                }
                menuAdmin.reload();
            } catch (AdministratorException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void update(MenuAdmin menuAdmin) {
        String IDAdmin;
        String nama;
        String alamat;
        String telp;
        String userName;
        String password;
        String bagian;
        IDAdmin = menuAdmin.getTxtIdAdmin().getText().trim();
        nama = menuAdmin.getTxtNamaAdmin().getText().trim();
        alamat = menuAdmin.getTxtAlamatAdmin().getText().trim();
        telp = menuAdmin.getTxtTelpAdmin().getText().trim();
        userName = menuAdmin.getTxtUserNameAdmin().getText().trim();
        char[] pwd = menuAdmin.getTxtPwdAdmin().getPassword();
        bagian = menuAdmin.getComboBagian().getSelectedItem().toString().trim();
        if (IDAdmin.isEmpty()
                && nama.isEmpty()
                && alamat.isEmpty()
                && telp.isEmpty()
                && userName.isEmpty()
                && pwd.length == 0
                && bagian.isEmpty()) {
            menuAdmin.getRegRwtJalan().getMessageComponent1().show("Harap input semua data", Color.darkGray, Color.red);
        } else if (nama.isEmpty()) {
            menuAdmin.getRegRwtJalan().getMessageComponent1().show("Nama masih kosong", Color.darkGray, Color.red);
        } else if (alamat.isEmpty()) {
            menuAdmin.getRegRwtJalan().getMessageComponent1().show("Alamat masih kosong", Color.darkGray, Color.red);
        } else if (telp.isEmpty()) {
            menuAdmin.getRegRwtJalan().getMessageComponent1().show("Telepon masih kosong", Color.darkGray, Color.red);
        } else if (userName.isEmpty()) {
            menuAdmin.getRegRwtJalan().getMessageComponent1().show("User Name masih kosong", Color.darkGray, Color.red);
        } else if (pwd.length == 0) {
            menuAdmin.getRegRwtJalan().getMessageComponent1().show("Password masih kosong", Color.darkGray, Color.red);
        } else if (bagian.isEmpty()) {
            menuAdmin.getRegRwtJalan().getMessageComponent1().show("Pilih hak akses", Color.darkGray, Color.red);
        } else {
            try {
                DBAdministrator login;
                login = DatabaseHelper.getAdministrator().getLogin(userName);
                if (login instanceof DBAdministrator) {
                    menuAdmin.getRegRwtJalan().getMessageComponent1().show("User  tersebut sudah dipakai", Color.darkGray, Color.red);
                } else {
                    adminMode.setIdAdmin(Integer.parseInt(IDAdmin));
                    adminMode.setNama(nama);
                    adminMode.setAlamat(alamat);
                    adminMode.setTelepon(telp);
                    adminMode.setUserName(userName);
                    password = new String(pwd);
                    adminMode.setPassword(password);
                    adminMode.setBagian(bagian);
                    adminMode.update();
                }
                menuAdmin.reload();
            } catch (AdministratorException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void delete(Integer kode, MenuAdmin menuAdmin) {
        adminMode.delete(kode);
        menuAdmin.reload();
    }
}
