/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.listener;

import Nciz.RegisterRawatJalan.Entity.DBPeriksa;
import Nciz.RegisterRawatJalan.Entity.DBResepDokter;
import java.util.List;

/**
 *
 * @author Ncizh
 */
public interface ListenerPeriksa {

    public void onInsert(DBPeriksa periksa);

    public void onUpdate(DBPeriksa periksa);

    public void onDetele(Integer kdPeriksa);
}
