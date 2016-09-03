/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.Entity;

import Nciz.RegisterRawatJalan.render.RenderTabelTanggal;
import com.stripbandunk.jwidget.annotation.TableColumn;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Ncizh
 */
public class DBObat {

    private Integer idobat;
    @TableColumn(number = 1, name = "NAMA OBAT", size = 35)
    private String nama_obat;
    @TableColumn(number = 2, name = "STOK AWAL", size = 18)
    private Integer stok_awal;
    @TableColumn(number = 3, name = "PENERIMAAN", size = 18)
    private Integer penerimaan;
    @TableColumn(number = 4, name = "PERSEDIAAN", size = 18)
    private Integer persediaan;
    @TableColumn(number = 5, name = "PEMAKAIAN", size = 18)
    private Integer pemakaian;
    @TableColumn(number = 6, name = "SISA STOK", size = 18)
    private Integer sisa_stok;
    @TableColumn(number = 7, name = "PERMINTAAN", size = 19)
    private Integer permintaan;
    @TableColumn(number = 8, name = "HARGA SATUAN", size = 19)
    private Double harga_satuan;
    @TableColumn(number = 0, name = "TANGGAL EXP", size = 25, renderer = RenderTabelTanggal.class)
    private Date tgl;
    private Integer JumlahPerBijij;

    //Untuk ListObat
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

    public Integer getJumlahPerBijij() {
        return JumlahPerBijij;
    }

    public void setJumlahPerBijij(Integer JumlahPerBijij) {
        this.JumlahPerBijij = JumlahPerBijij;
    }

    public Double getHarga_satuan() {
        return harga_satuan;
    }

    public void setHarga_satuan(Double harga_satuan) {
        this.harga_satuan = harga_satuan;
    }

    public Integer getIdobat() {
        return idobat;
    }

    public void setIdobat(Integer idobat) {
        this.idobat = idobat;
    }

    public String getNama_obat() {
        return nama_obat;
    }

    public void setNama_obat(String nama_obat) {
        this.nama_obat = nama_obat;
    }

    public Date getTgl() {
        return tgl;
    }

    public void setTgl(Date tgl) {
        this.tgl = tgl;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DBObat other = (DBObat) obj;
        if (!Objects.equals(this.idobat, other.idobat)) {
            return false;
        }
        if (!Objects.equals(this.nama_obat, other.nama_obat)) {
            return false;
        }
        if (!Objects.equals(this.stok_awal, other.stok_awal)) {
            return false;
        }
        if (!Objects.equals(this.penerimaan, other.penerimaan)) {
            return false;
        }
        if (!Objects.equals(this.persediaan, other.persediaan)) {
            return false;
        }
        if (!Objects.equals(this.pemakaian, other.pemakaian)) {
            return false;
        }
        if (!Objects.equals(this.sisa_stok, other.sisa_stok)) {
            return false;
        }
        if (!Objects.equals(this.permintaan, other.permintaan)) {
            return false;
        }
        if (!Objects.equals(this.harga_satuan, other.harga_satuan)) {
            return false;
        }
        if (!Objects.equals(this.tgl, other.tgl)) {
            return false;
        }
        if (!Objects.equals(this.JumlahPerBijij, other.JumlahPerBijij)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.idobat);
        hash = 67 * hash + Objects.hashCode(this.nama_obat);
        hash = 67 * hash + Objects.hashCode(this.stok_awal);
        hash = 67 * hash + Objects.hashCode(this.penerimaan);
        hash = 67 * hash + Objects.hashCode(this.persediaan);
        hash = 67 * hash + Objects.hashCode(this.pemakaian);
        hash = 67 * hash + Objects.hashCode(this.sisa_stok);
        hash = 67 * hash + Objects.hashCode(this.permintaan);
        hash = 67 * hash + Objects.hashCode(this.harga_satuan);
        hash = 67 * hash + Objects.hashCode(this.tgl);
        hash = 67 * hash + Objects.hashCode(this.JumlahPerBijij);
        return hash;
    }
}
