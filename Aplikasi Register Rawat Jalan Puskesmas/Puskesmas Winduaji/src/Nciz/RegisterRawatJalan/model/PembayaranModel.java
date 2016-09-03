/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.model;

import ian.RegisterRawatJalan.DatabaseHelper.DatabaseHelper;
import Nciz.RegisterRawatJalan.Entity.DBPembayaran;
import Nciz.RegisterRawatJalan.ex.PembayaranException;
import Nciz.RegisterRawatJalan.listener.ListenerPembayaran;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ncizh
 */
public class PembayaranModel {

    private Integer idPembayaran;
    private Integer idPeriksa;
    private Double bayarObat;
    private Double bayarPeriksa;
    private Double total;
    private ListenerPembayaran listenerPembayaran;

    public Integer getIdPeriksa() {
        return idPeriksa;
    }

    public void setIdPeriksa(Integer idPeriksa) {
        this.idPeriksa = idPeriksa;
    }

    
    public Double getBayarObat() {
        return bayarObat;
    }

    public void setBayarObat(Double bayarObat) {
        this.bayarObat = bayarObat;
    }

    public Double getBayarPeriksa() {
        return bayarPeriksa;
    }

    public void setBayarPeriksa(Double bayarPeriksa) {
        this.bayarPeriksa = bayarPeriksa;
    }

    public Integer getIdPembayaran() {
        return idPembayaran;
    }

    public void setIdPembayaran(Integer idPembayaran) {
        this.idPembayaran = idPembayaran;
    }


    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public ListenerPembayaran getListenerPembayaran() {
        return listenerPembayaran;
    }

    public void setListenerPembayaran(ListenerPembayaran listenerPembayaran) {
        this.listenerPembayaran = listenerPembayaran;
    }

    private void fireOnInsert(DBPembayaran pembayaran) {
        if (listenerPembayaran != null) {
            listenerPembayaran.onInsert(pembayaran);
        }
    }

    public void insert(){
        try {
            DBPembayaran pembayaran;
            pembayaran=new DBPembayaran();
            pembayaran.setIdpembayaran(idPembayaran);
            
            pembayaran.setBayar_obat(bayarObat);
            pembayaran.setBayar_periksa(bayarPeriksa);
            pembayaran.setTotal(total);
            DatabaseHelper.getPembayaran().insert(pembayaran,idPeriksa);
            fireOnInsert(pembayaran);
        } catch (PembayaranException ex) {
            Logger.getLogger(PembayaranModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
