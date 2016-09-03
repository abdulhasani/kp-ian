/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.dao;

import Nciz.RegisterRawatJalan.Entity.DBPasien;
import Nciz.RegisterRawatJalan.ex.PasienException;
import java.util.List;

/**
 *
 * @author Ncizh
 */
public interface DAOPasien {

    public void insert(DBPasien pasien) throws PasienException;

    public void update(DBPasien pasien) throws PasienException;

    public void delete(Integer kode) throws PasienException;

    public List<DBPasien> getAll(int skip, int max) throws PasienException;

    public List<DBPasien> getCari(String caril, int skip, int max) throws PasienException;

    public Long getCount(String cari) throws PasienException;

    public Long getCount() throws PasienException;

    public DBPasien getkode(int Kode) throws PasienException;
}
