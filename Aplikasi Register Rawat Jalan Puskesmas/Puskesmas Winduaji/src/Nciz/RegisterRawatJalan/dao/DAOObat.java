/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.dao;

import Nciz.RegisterRawatJalan.Entity.DBObat;
import Nciz.RegisterRawatJalan.ex.ObatException;
import java.util.List;

/**
 *
 * @author Ncizh
 */
public interface DAOObat {

    public void insert(DBObat obat) throws ObatException;

    public void update(DBObat obat) throws ObatException;

    public void delete(Integer kode) throws ObatException;

    public List<DBObat> getAll(int skip, int max) throws ObatException;

    public List<DBObat> getCari(String cari, int skip, int max) throws ObatException;

    public Long getCount(String cari) throws ObatException;

    public Long getCount() throws ObatException;

    public DBObat getkode(int Kode) throws ObatException;
}
