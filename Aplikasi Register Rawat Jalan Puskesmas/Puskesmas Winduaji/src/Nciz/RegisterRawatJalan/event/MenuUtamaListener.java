/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.event;

import java.util.EventListener;

/**
 *
 * @author Ncizh
 */
public interface MenuUtamaListener extends EventListener {

    public void pasien(MenuUtamaEvent menuUtamaEvent);

    public void periksa(MenuUtamaEvent menuUtamaEvent);

    public void pembayaran(MenuUtamaEvent menuUtamaEvent);

    public void obat(MenuUtamaEvent menuUtamaEvent);

    public void dokter(MenuUtamaEvent menuUtamaEvent);

    public void user(MenuUtamaEvent menuUtamaEvent);
}
