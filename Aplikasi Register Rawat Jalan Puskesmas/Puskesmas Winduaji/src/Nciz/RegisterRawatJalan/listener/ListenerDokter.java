/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.listener;

import Nciz.RegisterRawatJalan.Entity.DBDokter;

/**
 *
 * @author Ncizh
 */
public interface ListenerDokter {

    public void onInsert(DBDokter dokter);

    public void onUpdate(DBDokter dokter);

    public void onDetele(Integer kode);
}
