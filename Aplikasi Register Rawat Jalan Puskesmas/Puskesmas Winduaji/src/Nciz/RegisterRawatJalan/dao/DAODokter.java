/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.dao;

import Nciz.RegisterRawatJalan.Entity.DBDokter;
import Nciz.RegisterRawatJalan.ex.DokterException;
import java.util.List;

/**
 *
 * @author Ncizh
 */
public interface DAODokter {

    public void insert(DBDokter dokter) throws DokterException;

    public void update(DBDokter dokter) throws DokterException;

    public void delete(Integer kode) throws DokterException;

    public List<DBDokter> getAll(int skip, int max) throws DokterException;

    public List<DBDokter> getAll() throws DokterException;

    public List<DBDokter> getCari(String cari, int skip, int max) throws DokterException;

    public Long getCount() throws DokterException;

    public Long getCount(String cari) throws DokterException;

    public DBDokter getkode(int Kode) throws DokterException;
}
