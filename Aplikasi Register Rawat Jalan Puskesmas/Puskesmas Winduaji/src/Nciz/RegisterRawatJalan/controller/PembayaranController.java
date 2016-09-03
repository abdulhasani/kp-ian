/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.controller;

import Nciz.RegisterRawatJalan.frame.panel.MenuPembayaran;
import Nciz.RegisterRawatJalan.model.PembayaranModel;
import java.awt.Color;

/**
 *
 * @author Ncizh
 */
public class PembayaranController {

    private PembayaranModel pembayaranModel;

    public void setPembayaranModel(PembayaranModel pembayaranModel) {
        this.pembayaranModel = pembayaranModel;
    }

    public void insert(MenuPembayaran menuPembayaran) {
        String idPembayaran;
        String idPeriksa;
        String bayarObat;
        String bayarPeriksa;
        String total;
        idPembayaran = menuPembayaran.getTxtIdPeriksa().getText().trim();
        idPeriksa = menuPembayaran.getTxtIdPeriksa().getText().trim();
        bayarObat = menuPembayaran.getTxtBayarObat().getText().trim();
        bayarPeriksa = menuPembayaran.getTxtBayarPeriksa().getText().trim();
        total = menuPembayaran.getTxtTotal().getText().trim();
        if (idPeriksa.isEmpty()
                && bayarObat.isEmpty()
                && bayarPeriksa.isEmpty()
                && total.isEmpty()) {
            menuPembayaran.getRegRwtJalan().getMessageComponent1().show("Isi semua data", Color.darkGray, Color.red);
        } else if (idPeriksa.isEmpty()) {
            menuPembayaran.getRegRwtJalan().getMessageComponent1().show("Id periksa masih kosong", Color.darkGray, Color.red);
        } else if (bayarObat.isEmpty()) {
            menuPembayaran.getRegRwtJalan().getMessageComponent1().show("Bayar obat masih kosong", Color.darkGray, Color.red);
        } else if (bayarPeriksa.isEmpty()) {
            menuPembayaran.getRegRwtJalan().getMessageComponent1().show("Bayar periksa masih kosong", Color.darkGray, Color.red);
        } else if (total.isEmpty()) {
            menuPembayaran.getRegRwtJalan().getMessageComponent1().show("Total masih kosong", Color.darkGray, Color.red);
        } else {

            pembayaranModel.setIdPembayaran(Integer.parseInt(idPembayaran));
            pembayaranModel.setIdPeriksa(Integer.parseInt(idPeriksa));
            pembayaranModel.setBayarObat(Double.parseDouble(bayarObat));
            pembayaranModel.setBayarPeriksa(Double.parseDouble(bayarPeriksa));
            pembayaranModel.setTotal(Double.parseDouble(total));
            pembayaranModel.insert();
            menuPembayaran.reloadData();

        }
    }
}
