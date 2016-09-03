/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.dao;

import Nciz.RegisterRawatJalan.Entity.DBPembayaran;
import Nciz.RegisterRawatJalan.ex.PembayaranException;
import java.util.List;

/**
 *
 * @author Ncizh
 */
public interface DAOPembayaran {

    public void insert(DBPembayaran pembayaran, Integer kodePeriksa) throws PembayaranException;

    public void update(DBPembayaran pembayaran) throws PembayaranException;

    public void delete(Integer kode) throws PembayaranException;

    public List<DBPembayaran> getAll(int skip, int max) throws PembayaranException;

    public List<DBPembayaran> getCari(String cari, int skip, int max) throws PembayaranException;

    public Long getCount(String cari) throws PembayaranException;

    public Long getCount() throws PembayaranException;

    public DBPembayaran getkode(int Kode) throws PembayaranException;

    public DBPembayaran getkodePeriksa(int kodePeriksa) throws PembayaranException;
}
