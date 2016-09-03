/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.dao.impl;

import Nciz.RegisterRawatJalan.Entity.DBAdministrator;
import Nciz.RegisterRawatJalan.dao.DAOAdministrator;
import Nciz.RegisterRawatJalan.ex.AdministratorException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ncizh
 */
public class AdministratorImpl implements DAOAdministrator {

    private Connection connection;
    private final String insert = "INSERT INTO dbadministrator (idadministrator,nama,alamat,telp, "
            + "username,password,bagian) VALUES (?,?,?,?,?,?,?) ";
    private final String update = "UPDATE dbadministrator SET nama=?,alamat=?,telp=? "
            + ",username=?,password=? ,bagian=? WHERE idadministrator=?  ";
    private final String delete = "DELETE FROM dbadministrator WHERE idadministrator=? ";
    private final String getAll = "SELECT * FROM dbadministrator ";
    private final String getKode = "SELECT * FROM dbadministrator WHERE idadministrator=? ";
    private final String getCount = "SELECT COUNT(idadministrator) AS total FROM dbadministrator ";
    private final String getLoin = "SELECT * FROM dbadministrator WHERE username=? AND password=?";
    private final String getUserName = "SELECT * FROM dbadministrator WHERE username=?";

    public AdministratorImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(DBAdministrator administrator) throws AdministratorException {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(insert);
            statement.setInt(1, administrator.getIdadministrator());
            statement.setString(2, administrator.getNama());
            statement.setString(3, administrator.getAlamat());
            statement.setString(4, administrator.getTelp());
            statement.setString(5, administrator.getUsername());
            statement.setString(6, administrator.getPassword());
            statement.setString(7, administrator.getBagian());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AdministratorImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new AdministratorException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AdministratorImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdministratorImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void update(DBAdministrator administrator) throws AdministratorException {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(update);
            statement.setString(1, administrator.getNama());
            statement.setString(2, administrator.getAlamat());
            statement.setString(3, administrator.getTelp());
            statement.setString(4, administrator.getUsername());
            statement.setString(5, administrator.getPassword());
            statement.setString(6, administrator.getBagian());
            statement.setInt(7, administrator.getIdadministrator());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AdministratorImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new AdministratorException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AdministratorImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdministratorImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void delete(Integer kode) throws AdministratorException {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(delete);
            statement.setInt(1, kode);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AdministratorImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new AdministratorException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AdministratorImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdministratorImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<DBAdministrator> getAll(int skip, int max) throws AdministratorException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<DBAdministrator> list = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getAll + " LIMIT " + skip + "," + max);
            resultSet = statement.executeQuery();
            DBAdministrator administrator;
            while (resultSet.next()) {
                administrator = new DBAdministrator();
                administrator.setIdadministrator(resultSet.getInt("idadministrator"));
                administrator.setNama(resultSet.getString("nama"));
                administrator.setAlamat(resultSet.getString("alamat"));
                administrator.setTelp(resultSet.getString("telp"));
                administrator.setUsername(resultSet.getString("username"));
                administrator.setPassword(resultSet.getString("password"));
                administrator.setBagian(resultSet.getString("bagian"));
                list.add(administrator);
            }
            connection.commit();
            return list;
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AdministratorImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new AdministratorException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AdministratorImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdministratorImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdministratorImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public DBAdministrator getkode(Integer kode) throws AdministratorException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            statement = connection.prepareStatement(getKode);
            statement.setInt(1, kode);
            resultSet = statement.executeQuery();
            DBAdministrator administrator = null;
            while (resultSet.next()) {
                administrator = new DBAdministrator();
                administrator.setIdadministrator(resultSet.getInt("idadministrator"));
                administrator.setNama(resultSet.getString("nama"));
                administrator.setAlamat(resultSet.getString("alamat"));
                administrator.setTelp(resultSet.getString("telp"));
                administrator.setUsername(resultSet.getString("username"));
                administrator.setPassword(resultSet.getString("password"));
                administrator.setBagian(resultSet.getString("bagian"));
            }

            return administrator;
        } catch (SQLException ex) {
            throw new AdministratorException(ex.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdministratorImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdministratorImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public Long getCount() throws AdministratorException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Long total = 0l;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getCount);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                total = resultSet.getLong("total");
            }
            connection.commit();
            return total;
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AdministratorImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new AdministratorException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AdministratorImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdministratorImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdministratorImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<DBAdministrator> getCari(String cari, int skip, int max) throws AdministratorException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<DBAdministrator> list = new ArrayList<>();
        final String getCari = "SELECT * FROM dbadministrator WHERE nama LIKE '" + cari + "%' OR telp='" + cari + "' OR username='" + cari + "%' "
                + " OR  bagian='" + cari + "'";
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getCari + " LIMIT " + skip + "," + max);

            resultSet = statement.executeQuery();
            DBAdministrator administrator;
            while (resultSet.next()) {
                administrator = new DBAdministrator();
                administrator.setIdadministrator(resultSet.getInt("idadministrator"));
                administrator.setNama(resultSet.getString("nama"));
                administrator.setAlamat(resultSet.getString("alamat"));
                administrator.setTelp(resultSet.getString("telp"));
                administrator.setUsername(resultSet.getString("username"));
                administrator.setPassword(resultSet.getString("password"));
                administrator.setBagian(resultSet.getString("bagian"));
                list.add(administrator);
            }
            connection.commit();
            return list;
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AdministratorImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new AdministratorException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AdministratorImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdministratorImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdministratorImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public Long getCount(String cari) throws AdministratorException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Long total = 0l;
        final String getCari = "SELECT COUNT(idadministrator) as total FROM dbadministrator WHERE nama LIKE '" + cari + "%' OR telp='" + cari + "' OR username='" + cari + "%'  "
                + " OR bagian='" + cari + "'";
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getCari);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                total = resultSet.getLong("total");
            }
            connection.commit();
            return total;
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AdministratorImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new AdministratorException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AdministratorImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdministratorImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdministratorImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public DBAdministrator getLogin(String userName, String Password) throws AdministratorException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            statement = connection.prepareStatement(getLoin);
            statement.setString(1, userName);
            statement.setString(2, Password);
            resultSet = statement.executeQuery();
            DBAdministrator administrator = null;
            while (resultSet.next()) {
                administrator = new DBAdministrator();
                administrator.setIdadministrator(resultSet.getInt("idadministrator"));
                administrator.setNama(resultSet.getString("nama"));
                administrator.setAlamat(resultSet.getString("alamat"));
                administrator.setTelp(resultSet.getString("telp"));
                administrator.setUsername(resultSet.getString("username"));
                administrator.setPassword(resultSet.getString("password"));
                administrator.setBagian(resultSet.getString("bagian"));
            }

            return administrator;
        } catch (SQLException ex) {
            throw new AdministratorException(ex.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdministratorImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdministratorImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public DBAdministrator getLogin(String userName) throws AdministratorException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            statement = connection.prepareStatement(getUserName);
            statement.setString(1, userName);
            resultSet = statement.executeQuery();
            DBAdministrator administrator = null;
            if (resultSet.next()) {
                administrator = new DBAdministrator();
                administrator.setIdadministrator(resultSet.getInt("idadministrator"));
                administrator.setNama(resultSet.getString("nama"));
                administrator.setAlamat(resultSet.getString("alamat"));
                administrator.setTelp(resultSet.getString("telp"));
                administrator.setUsername(resultSet.getString("username"));
                administrator.setPassword(resultSet.getString("password"));
                administrator.setBagian(resultSet.getString("bagian"));
            }

            return administrator;
        } catch (SQLException ex) {
            throw new AdministratorException(ex.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdministratorImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdministratorImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
