/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.listener;

import Nciz.RegisterRawatJalan.Entity.DBPasien;

/**
 *
 * @author Ncizh
 */
public interface ListenerPasien {

    public void onInsert(DBPasien pasien);

    public void onUpdate(DBPasien pasien);

    public void onDetele(Integer kode);
}
