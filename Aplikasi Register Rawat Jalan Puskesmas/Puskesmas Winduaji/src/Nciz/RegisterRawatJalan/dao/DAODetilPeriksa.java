/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.dao;

import Nciz.RegisterRawatJalan.Entity.DBDetailPeriksa;
import Nciz.RegisterRawatJalan.ex.DetailPeriksaException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ncizh
 */
public interface DAODetilPeriksa {

    public void delete(Integer kdDetailPeriksa) throws DetailPeriksaException;

    public List<DBDetailPeriksa> getAll(int skip, int max) throws DetailPeriksaException;

    public List<DBDetailPeriksa> getAll(String cari, int skip, int max) throws DetailPeriksaException;

    public List<DBDetailPeriksa> getIdPeriksaAll(Integer idPeriksa, Date tgl) throws DetailPeriksaException;

    public List<DBDetailPeriksa> getIdPeriksaAll(Integer idPeriksa) throws DetailPeriksaException;

    public DBDetailPeriksa getIdPeriksa(Integer idPeriksa) throws DetailPeriksaException;

    public Long getCount() throws DetailPeriksaException;

    public Long getCount(String cari) throws DetailPeriksaException;

    public List<DBDetailPeriksa> getKode(Integer kodePeriksa) throws DetailPeriksaException;
}
