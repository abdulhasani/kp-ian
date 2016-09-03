/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.listener;

import Nciz.RegisterRawatJalan.Entity.DBAdministrator;

/**
 *
 * @author Ncizh
 */
public interface ListenerAdmin {

    public void onInsert(DBAdministrator administrator);

    public void onUpdate(DBAdministrator administrator);

    public void onDetele(Integer kode);
}
