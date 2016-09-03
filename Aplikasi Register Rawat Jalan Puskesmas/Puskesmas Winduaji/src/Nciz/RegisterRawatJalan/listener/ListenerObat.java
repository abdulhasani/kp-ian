/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.listener;

import Nciz.RegisterRawatJalan.Entity.DBObat;

/**
 *
 * @author Ncizh
 */
public interface ListenerObat {

    public void onInsert(DBObat obat);

    public void onUpdate(DBObat obat);

    public void onDetele(Integer kode);
}
