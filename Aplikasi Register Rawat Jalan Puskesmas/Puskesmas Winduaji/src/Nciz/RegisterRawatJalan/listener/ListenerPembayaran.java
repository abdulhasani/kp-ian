/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.listener;

import Nciz.RegisterRawatJalan.Entity.DBPembayaran;

/**
 *
 * @author Ncizh
 */
public interface ListenerPembayaran {

    public void onInsert(DBPembayaran pembayaran);

    public void onUpdate(DBPembayaran pembayaran);

    public void onDetele(Integer kode);
}
