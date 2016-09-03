/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.dao;

import Nciz.RegisterRawatJalan.Entity.DBAdministrator;
import Nciz.RegisterRawatJalan.ex.AdministratorException;
import java.util.List;

/**
 *
 * @author Ncizh
 */
public interface DAOAdministrator {

    public void insert(DBAdministrator administrator) throws AdministratorException;

    public void update(DBAdministrator administrator) throws AdministratorException;

    public void delete(Integer kode) throws AdministratorException;

    public List<DBAdministrator> getAll(int skip, int max) throws AdministratorException;

    public List<DBAdministrator> getCari(String cari, int skip, int max) throws AdministratorException;

    public DBAdministrator getkode(Integer kode) throws AdministratorException;

    public DBAdministrator getLogin(String userName, String Password) throws AdministratorException;

    public DBAdministrator getLogin(String userName) throws AdministratorException;

    public Long getCount() throws AdministratorException;

    public Long getCount(String cari) throws AdministratorException;
}
