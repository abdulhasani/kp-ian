/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.model;

import ian.RegisterRawatJalan.DatabaseHelper.DatabaseHelper;
import Nciz.RegisterRawatJalan.Entity.DBDetailPeriksa;
import Nciz.RegisterRawatJalan.Entity.DBPeriksa;
import Nciz.RegisterRawatJalan.Entity.DBResepDokter;
import Nciz.RegisterRawatJalan.ex.PeriksaException;
import Nciz.RegisterRawatJalan.listener.ListenerPeriksa;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ncizh
 */
public class PeriksaModel {
    
    private Integer idPeriksa;
    private Date tgl;
    private DBDetailPeriksa detailPeriksa;
    private List<DBResepDokter> resepDokters;
    private ListenerPeriksa listenerPeriksa;
    
    public Integer getIdPeriksa() {
        return idPeriksa;
    }
    
    public void setIdPeriksa(Integer idPeriksa) {
        this.idPeriksa = idPeriksa;
    }
    
    public DBDetailPeriksa getDetailPeriksa() {
        return detailPeriksa;
    }
    
    public void setDetailPeriksa(DBDetailPeriksa detailPeriksa) {
        this.detailPeriksa = detailPeriksa;
    }
    
    public List<DBResepDokter> getResepDokters() {
        return resepDokters;
    }
    
    public void setResepDokters(List<DBResepDokter> resepDokters) {
        this.resepDokters = resepDokters;
    }
    
    public Date getTgl() {
        return tgl;
    }
    
    public void setTgl(Date tgl) {
        this.tgl = tgl;
    }
    
    public ListenerPeriksa getListenerPeriksa() {
        return listenerPeriksa;
    }
    
    public void setListenerPeriksa(ListenerPeriksa listenerPeriksa) {
        this.listenerPeriksa = listenerPeriksa;
    }
    
    private void fireOnInsert(DBPeriksa dBPeriksa) {
        if (listenerPeriksa != null) {
            listenerPeriksa.onInsert(dBPeriksa);
        }
    }
    
    private void fireOnUpdate(DBPeriksa dBPeriksa) {
        if (listenerPeriksa != null) {
            listenerPeriksa.onUpdate(dBPeriksa);
        }
    }
    
    private void fireOnDelete(Integer kdPeriksa) {
        if (listenerPeriksa != null) {
            listenerPeriksa.onDetele(kdPeriksa);
        }
    }
    
    public void insert() {
        try {
            DBPeriksa dBPeriksa;
            dBPeriksa = new DBPeriksa();
            dBPeriksa.setIdperiksa(idPeriksa);
            dBPeriksa.setTgl_periksa(tgl);
            dBPeriksa.setVirtualInsert(detailPeriksa);
            DatabaseHelper.getPeriksaUtama().insert(dBPeriksa, resepDokters);
            fireOnInsert(dBPeriksa);
        } catch (PeriksaException ex) {
            Logger.getLogger(PeriksaModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update() {
        try {
            DBPeriksa dBPeriksa;
            dBPeriksa = new DBPeriksa();
            dBPeriksa.setIdperiksa(idPeriksa);
            dBPeriksa.setTgl_periksa(tgl);
            dBPeriksa.setVirtualInsert(detailPeriksa);
            DatabaseHelper.getPeriksaUtama().update(dBPeriksa);
            fireOnUpdate(dBPeriksa);
        } catch (PeriksaException ex) {
            Logger.getLogger(PeriksaModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Integer kodePeriksa) {
        try {
            DatabaseHelper.getPeriksaUtama().delete(kodePeriksa);
            fireOnDelete(kodePeriksa);
        } catch (PeriksaException ex) {
            Logger.getLogger(PeriksaModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
