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
public class DBPasien {
   private Integer idpasien;
   @TableColumn(number=1,name="NAMA",size=19)
   private String nama_pasien;
   @TableColumn(number=2,name="JENIS KELAMIN")
   private String jenis_kelamin;
   @TableColumn(number=3,name="ALAMAT PASIEN",size=28)
   private String alamat_pasien;
   @TableColumn(number=4,name="UMUR",size=19)
   private String umur;
   @TableColumn(number=5,name="NAMA KK",size=20)
   private String nama_kk;
   @TableColumn(number=6,name="KUNJUNGAN",size=20)
   private String kunjungan;
   @TableColumn(number=7,name="UNIT",size=19)
   private String unit_tujuan;
   @TableColumn(number=8,name="JENIS PELAYANAN",size=20)
   private String jenis_pelayanan;

    public String getJenis_pelayanan() {
        return jenis_pelayanan;
    }

    public void setJenis_pelayanan(String jenis_pelayanan) {
        this.jenis_pelayanan = jenis_pelayanan;
    }

    public String getUnit_tujuan() {
        return unit_tujuan;
    }

    public void setUnit_tujuan(String unit_tujuan) {
        this.unit_tujuan = unit_tujuan;
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

   public String getAlamat_pasien() {
        return alamat_pasien;
    }

    public void setAlamat_pasien(String alamat_pasien) {
        this.alamat_pasien = alamat_pasien;
    }

    public Integer getIdpasien() {
        return idpasien;
    }

    public void setIdpasien(Integer idpasien) {
        this.idpasien = idpasien;
    }


    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getNama_pasien() {
        return nama_pasien;
    }

    public void setNama_pasien(String nama_pasien) {
        this.nama_pasien = nama_pasien;
    }

    

    
   
}
