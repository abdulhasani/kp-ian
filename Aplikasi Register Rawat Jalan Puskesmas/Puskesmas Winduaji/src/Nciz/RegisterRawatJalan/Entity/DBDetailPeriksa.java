/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.Entity;

import java.util.Objects;

/**
 *
 * @author Ncizh
 */
public class DBDetailPeriksa {

    Long idDetailPeriksa;
    private DBPeriksa periksa;
    private DBPasien pasien;
    private DBDokter dokter;
    private DBObat obat;
    String diagnosa;
    private String alergiObat;
    private Integer obatPerBijij;
    
    

    public String getAlergiObat() {
        return alergiObat;
    }

    public void setAlergiObat(String alergiObat) {
        this.alergiObat = alergiObat;
    }

    public String getDiagnosa() {
        return diagnosa;
    }

    public void setDiagnosa(String diagnosa) {
        this.diagnosa = diagnosa;
    }

    public DBDokter getDokter() {
        return dokter;
    }

    public void setDokter(DBDokter dokter) {
        this.dokter = dokter;
    }

    public Long getIdDetailPeriksa() {
        return idDetailPeriksa;
    }

    public void setIdDetailPeriksa(Long idDetailPeriksa) {
        this.idDetailPeriksa = idDetailPeriksa;
    }

    public DBObat getObat() {
        return obat;
    }

    public void setObat(DBObat obat) {
        this.obat = obat;
    }

    public Integer getObatPerBijij() {
        return obatPerBijij;
    }

    public void setObatPerBijij(Integer obatPerBijij) {
        this.obatPerBijij = obatPerBijij;
    }

    public DBPasien getPasien() {
        return pasien;
    }

    public void setPasien(DBPasien pasien) {
        this.pasien = pasien;
    }

    public DBPeriksa getPeriksa() {
        return periksa;
    }

    public void setPeriksa(DBPeriksa periksa) {
        this.periksa = periksa;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DBDetailPeriksa other = (DBDetailPeriksa) obj;
        if (!Objects.equals(this.idDetailPeriksa, other.idDetailPeriksa)) {
            return false;
        }
        if (!Objects.equals(this.periksa, other.periksa)) {
            return false;
        }
        if (!Objects.equals(this.pasien, other.pasien)) {
            return false;
        }
        if (!Objects.equals(this.dokter, other.dokter)) {
            return false;
        }
        if (!Objects.equals(this.obat, other.obat)) {
            return false;
        }
        if (!Objects.equals(this.diagnosa, other.diagnosa)) {
            return false;
        }
        if (!Objects.equals(this.alergiObat, other.alergiObat)) {
            return false;
        }
        if (!Objects.equals(this.obatPerBijij, other.obatPerBijij)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.idDetailPeriksa);
        hash = 23 * hash + Objects.hashCode(this.periksa);
        hash = 23 * hash + Objects.hashCode(this.pasien);
        hash = 23 * hash + Objects.hashCode(this.dokter);
        hash = 23 * hash + Objects.hashCode(this.obat);
        hash = 23 * hash + Objects.hashCode(this.diagnosa);
        hash = 23 * hash + Objects.hashCode(this.alergiObat);
        hash = 23 * hash + Objects.hashCode(this.obatPerBijij);
        return hash;
    }

  
}
