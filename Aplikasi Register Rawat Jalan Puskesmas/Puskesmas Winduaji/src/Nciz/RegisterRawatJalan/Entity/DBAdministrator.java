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
public class DBAdministrator {

    private Integer idadministrator;
    @TableColumn(number = 1, name = "NAMA",size=25)
    private String nama;
    @TableColumn(number = 2, name = "ALAMAT", size = 40)
    private String alamat;
    @TableColumn(number = 3, name = "TELEPON",size=20)
    private String telp;
    @TableColumn(number = 4, name = "USER NAME",size=20)
    private String username;
    @TableColumn(number = 5, name = "PASSWORD",size=20)
    private String password;
    @TableColumn(number = 6, name = "BAGIAN")
    private String bagian;

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Integer getIdadministrator() {
        return idadministrator;
    }

    public void setIdadministrator(Integer idadministrator) {
        this.idadministrator = idadministrator;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBagian() {
        return bagian;
    }

    public void setBagian(String bagian) {
        this.bagian = bagian;
    }
}
