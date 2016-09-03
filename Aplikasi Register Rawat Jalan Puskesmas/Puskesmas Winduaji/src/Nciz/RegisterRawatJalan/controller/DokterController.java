/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.controller;

import Nciz.RegisterRawatJalan.frame.panel.MenuDokter;
import Nciz.RegisterRawatJalan.model.DokterMode;
import java.awt.Color;

/**
 *
 * @author Ncizh
 */
public class DokterController {

    private DokterMode dokterModel;

    public void setDokterModel(DokterMode dokterModel) {
        this.dokterModel = dokterModel;
    }
    

    public void insertAdmin(MenuDokter menuDokter) {
      String idDokter;
      String nama;
      String alamat;
      String telp;
      String biaya;
      idDokter=menuDokter.getTxtIdDokter().getText().trim();
      nama=menuDokter.getTxtNama().getText().trim();
      alamat=menuDokter.getTxtAlamat().getText().trim();
      telp=menuDokter.getTxtTelepon().getText().trim();
      biaya=menuDokter.getTxtBiaya().getText().trim();
      if(idDokter.isEmpty() && nama.isEmpty()
              && alamat.isEmpty() && telp.isEmpty()
              && biaya.isEmpty()){
          menuDokter.getRegRwtJalan().getMessageComponent1()
                  .show("Isi semua data", Color.darkGray, Color.red);
      }else if(nama.isEmpty()){
          menuDokter.getRegRwtJalan().getMessageComponent1()
                  .show("Nama masih kosong", Color.darkGray, Color.red);
      }else if(alamat.isEmpty()){
          menuDokter.getRegRwtJalan().getMessageComponent1()
                  .show("Alamat masih kosong", Color.darkGray, Color.red);
      }else if(telp.isEmpty()){
          menuDokter.getRegRwtJalan().getMessageComponent1()
                  .show("Telepon masih kosong", Color.darkGray, Color.red);
      }else if(biaya.isEmpty()){
          menuDokter.getRegRwtJalan().getMessageComponent1()
                  .show("Biaya masih kosong", Color.darkGray, Color.red);
      }else{
          dokterModel.setIddokter(Integer.parseInt(idDokter));
          dokterModel.setNama_dokter(nama);
          dokterModel.setAlamat_dokter(alamat);
          dokterModel.setTelp_dokter(telp);
          dokterModel.setBiaya(Double.parseDouble(biaya));
          dokterModel.insert();
          menuDokter.reloadData();
      }
    }

    public void update(MenuDokter menuDokter) {
         String idDokter;
      String nama;
      String alamat;
      String telp;
      String biaya;
      idDokter=menuDokter.getTxtIdDokter().getText().trim();
      nama=menuDokter.getTxtNama().getText().trim();
      alamat=menuDokter.getTxtAlamat().getText().trim();
      telp=menuDokter.getTxtTelepon().getText().trim();
      biaya=menuDokter.getTxtBiaya().getText().trim();
      if(idDokter.isEmpty() && nama.isEmpty()
              && alamat.isEmpty() && telp.isEmpty()
              && biaya.isEmpty()){
          menuDokter.getRegRwtJalan().getMessageComponent1()
                  .show("Isi semua data", Color.darkGray, Color.red);
      }else if(nama.isEmpty()){
          menuDokter.getRegRwtJalan().getMessageComponent1()
                  .show("Nama masih kosong", Color.darkGray, Color.red);
      }else if(alamat.isEmpty()){
          menuDokter.getRegRwtJalan().getMessageComponent1()
                  .show("Alamat masih kosong", Color.darkGray, Color.red);
      }else if(telp.isEmpty()){
          menuDokter.getRegRwtJalan().getMessageComponent1()
                  .show("Telepon masih kosong", Color.darkGray, Color.red);
      }else if(biaya.isEmpty()){
          menuDokter.getRegRwtJalan().getMessageComponent1()
                  .show("Biaya masih kosong", Color.darkGray, Color.red);
      }else{
          dokterModel.setIddokter(Integer.parseInt(idDokter));
          dokterModel.setNama_dokter(nama);
          dokterModel.setAlamat_dokter(alamat);
          dokterModel.setTelp_dokter(telp);
          dokterModel.setBiaya(Double.parseDouble(biaya));
          dokterModel.update();
          menuDokter.reloadData();
      }
        
    }

    public void delete(Integer kode, MenuDokter menuDokter) {
        dokterModel.delete(kode);
        menuDokter.reloadData();
    }
}
