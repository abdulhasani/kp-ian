/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.model;

import ian.RegisterRawatJalan.DatabaseHelper.DatabaseHelper;
import Nciz.RegisterRawatJalan.Entity.DBAdministrator;
import Nciz.RegisterRawatJalan.ex.AdministratorException;
import Nciz.RegisterRawatJalan.listener.ListenerAdmin;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ncizh
 */
public class AdminMode {

    private Integer idAdmin;
    private String nama;
    private String alamat;
    private String telepon;
    private String userName;
    private String password;
    private String bagian;
    private ListenerAdmin listenerAdmin;

    /**
     * @return the idAdmin
     */
    public Integer getIdAdmin() {
        return idAdmin;
    }

    /**
     * @param idAdmin the idAdmin to set
     */
    public void setIdAdmin(Integer idAdmin) {
        this.idAdmin = idAdmin;
    }

    /**
     * @return the nama
     */
    public String getNama() {
        return nama;
    }

    /**
     * @param nama the nama to set
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * @return the alamat
     */
    public String getAlamat() {
        return alamat;
    }

    /**
     * @param alamat the alamat to set
     */
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    /**
     * @return the telepon
     */
    public String getTelepon() {
        return telepon;
    }

    /**
     * @param telepon the telepon to set
     */
    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String getBagian() {
        return bagian;
    }

    public void setBagian(String bagian) {
        this.bagian = bagian;
    }

    /**
     * @return the listenerAdmin
     */
    public ListenerAdmin getListenerAdmin() {
        return listenerAdmin;
    }

    private void fireOnInsert(DBAdministrator administrator) {
        if (listenerAdmin != null) {
            listenerAdmin.onInsert(administrator);
        }
    }

    private void fireOnupdate(DBAdministrator administrator) {
        if (listenerAdmin != null) {
            listenerAdmin.onUpdate(administrator);
        }
    }

    private void fireOnDelete(Integer kode) {
        if (listenerAdmin != null) {
            listenerAdmin.onDetele(kode);
        }
    }

    public void insert() {
        try {
            DBAdministrator administrator;
            administrator = new DBAdministrator();
            administrator.setIdadministrator(idAdmin);
            administrator.setNama(nama);
            administrator.setAlamat(alamat);
            administrator.setTelp(telepon);
            administrator.setUsername(userName);
            administrator.setPassword(password);
            administrator.setBagian(bagian);
            
            DatabaseHelper.getAdministrator().insert(administrator);
            fireOnInsert(administrator);
        } catch (AdministratorException ex) {
            Logger.getLogger(AdminMode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update() {
        try {
            DBAdministrator administrator;
            administrator = new DBAdministrator();
            administrator.setIdadministrator(idAdmin);
            administrator.setNama(nama);
            administrator.setAlamat(alamat);
            administrator.setTelp(telepon);
            administrator.setUsername(userName);
            administrator.setPassword(password);
            administrator.setBagian(bagian);
            DatabaseHelper.getAdministrator().update(administrator);
            fireOnupdate(administrator);
        } catch (AdministratorException ex) {
            Logger.getLogger(AdminMode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Integer kode) {
        try {
            DatabaseHelper.getAdministrator().delete(kode);
            fireOnDelete(kode);
        } catch (AdministratorException ex) {
            Logger.getLogger(AdminMode.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * @param listenerAdmin the listenerAdmin to set
     */
    public void setListenerAdmin(ListenerAdmin listenerAdmin) {
        this.listenerAdmin = listenerAdmin;
    }
}
