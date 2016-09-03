/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.dao;

import Nciz.RegisterRawatJalan.Entity.DBPeriksa;
import Nciz.RegisterRawatJalan.Entity.DBResepDokter;
import Nciz.RegisterRawatJalan.ex.PeriksaException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ncizh
 */
public interface DAOPeriksa {

    public void insert(DBPeriksa dBPeriksa, List<DBResepDokter> resepDokters) throws PeriksaException;

    public void update(DBPeriksa dBPeriksa) throws PeriksaException;

    public void delete(Integer kodePeriksa) throws PeriksaException;

    public DBPeriksa getKode(Integer kode) throws PeriksaException;

    public List<DBPeriksa> getAll(int skip, int max) throws PeriksaException;

    public List<DBPeriksa> getAll(String cari, Date tgl, int skip, int max) throws PeriksaException;

    public Long getCount(String cari, Date tgl) throws PeriksaException;

    public Long getCount() throws PeriksaException;
}
