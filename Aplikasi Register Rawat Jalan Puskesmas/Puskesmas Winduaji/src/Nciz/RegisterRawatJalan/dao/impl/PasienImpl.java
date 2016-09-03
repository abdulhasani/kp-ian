/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.dao.impl;

import Nciz.RegisterRawatJalan.Entity.DBPasien;
import Nciz.RegisterRawatJalan.dao.DAOPasien;
import Nciz.RegisterRawatJalan.ex.PasienException;
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
public class PasienImpl implements DAOPasien {

    private Connection connection;
    private final String insert = "INSERT INTO DBPasien (idpasien,nama_pasien,umur,nama_kk,jenis_kelamin,alamat_pasien,kunjungan,unit_tujuan,jenis_pelayanan"
            + ") VALUES (?,?,?,?,?,?,?,?,?) ";
    private final String update = "UPDATE DBPasien SET nama_pasien=?,umur=?,nama_kk=?,jenis_kelamin=?,alamat_pasien=?,kunjungan=?,unit_tujuan=?,jenis_pelayanan=?"
            + "WHERE idpasien=?  ";
    private final String delete = "DELETE FROM DBPasien WHERE idpasien=? ";
    private final String getAll = "SELECT * FROM DBPasien ";
    private final String getKode = "SELECT * FROM DBPasien WHERE idpasien=? ";
    private final String getCount = "SELECT COUNT(idpasien) AS total FROM DBPasien ";

    public PasienImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(DBPasien pasien) throws PasienException {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(insert);
            statement.setInt(1, pasien.getIdpasien());
            statement.setString(2, pasien.getNama_pasien());
            statement.setString(3, pasien.getUmur());
            statement.setString(4, pasien.getNama_kk());
            statement.setString(5, pasien.getJenis_kelamin());
            statement.setString(6, pasien.getAlamat_pasien());
            statement.setString(7, pasien.getKunjungan());
            statement.setString(8, pasien.getUnit_tujuan());
            statement.setString(9, pasien.getJenis_pelayanan());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PasienImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new PasienException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(PasienImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PasienImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void update(DBPasien pasien) throws PasienException {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(update);
            statement.setString(1, pasien.getNama_pasien());
            statement.setString(2, pasien.getUmur());
            statement.setString(3, pasien.getNama_kk());
            statement.setString(4, pasien.getJenis_kelamin());
            statement.setString(5, pasien.getAlamat_pasien());
            statement.setString(6, pasien.getKunjungan());
            statement.setString(7, pasien.getUnit_tujuan());
            statement.setString(8, pasien.getJenis_pelayanan());
            statement.setInt(9, pasien.getIdpasien());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PasienImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new PasienException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(PasienImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PasienImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void delete(Integer kode) throws PasienException {
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
                Logger.getLogger(PasienImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new PasienException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(PasienImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PasienImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<DBPasien> getAll(int skip, int max) throws PasienException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<DBPasien> list = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getAll + " LIMIT " + skip + "," + max);
            resultSet = statement.executeQuery();
            DBPasien pasien;
            while (resultSet.next()) {
                pasien = new DBPasien();
                pasien.setIdpasien(resultSet.getInt("idpasien"));
                pasien.setNama_pasien(resultSet.getString("Nama_pasien"));
                pasien.setUmur(resultSet.getString("Umur"));
                pasien.setNama_kk(resultSet.getString("Nama_KK"));
                pasien.setJenis_kelamin(resultSet.getString("Jenis_kelamin"));
                pasien.setAlamat_pasien(resultSet.getString("Alamat_pasien"));
                pasien.setKunjungan(resultSet.getString("Kunjungan"));
                pasien.setUnit_tujuan(resultSet.getString("Unit_Tujuan"));
                pasien.setJenis_pelayanan(resultSet.getString("Jenis_Pelayanan"));
                list.add(pasien);
            }
            connection.commit();
            return list;
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PasienImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new PasienException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(PasienImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PasienImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PasienImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public DBPasien getkode(int Kode) throws PasienException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            statement = connection.prepareStatement(getKode);
            statement.setInt(1, Kode);
            resultSet = statement.executeQuery();
            DBPasien pasien = null;
            while (resultSet.next()) {
                pasien = new DBPasien();
                pasien.setIdpasien(resultSet.getInt("idpasien"));
                pasien.setNama_pasien(resultSet.getString("Nama_pasien"));
                pasien.setUmur(resultSet.getString("Umur"));
                pasien.setNama_kk(resultSet.getString("Nama_KK"));
                pasien.setJenis_kelamin(resultSet.getString("Jenis_kelamin"));
                pasien.setAlamat_pasien(resultSet.getString("Alamat_pasien"));
                pasien.setKunjungan(resultSet.getString("Kunjungan"));
                pasien.setUnit_tujuan(resultSet.getString("Unit_Tujuan"));
                pasien.setJenis_pelayanan(resultSet.getString("Jenis_Pelayanan"));
            }

            return pasien;
        } catch (SQLException ex) {

            throw new PasienException(ex.getMessage());
        } finally {

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PasienImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PasienImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public Long getCount() throws PasienException {
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
                Logger.getLogger(PasienImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new PasienException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(PasienImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PasienImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PasienImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<DBPasien> getCari(String caril, int skip, int max) throws PasienException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<DBPasien> list = new ArrayList<>();
        final String sql = "SELECT * FROM dbpasien WHERE nama_pasien LIKE '" + caril + "' OR "
                + " jenis_kelamin='" + caril + "' OR alamat_pasien='" + caril + "' OR nama_kk='" + caril + "' ";
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql + " LIMIT " + skip + "," + max);
            resultSet = statement.executeQuery();
            DBPasien pasien;
            while (resultSet.next()) {
                pasien = new DBPasien();
                pasien.setIdpasien(resultSet.getInt("idpasien"));
                pasien.setNama_pasien(resultSet.getString("Nama_pasien"));
                pasien.setUmur(resultSet.getString("Umur"));
                pasien.setNama_kk(resultSet.getString("Nama_KK"));
                pasien.setJenis_kelamin(resultSet.getString("Jenis_kelamin"));
                pasien.setAlamat_pasien(resultSet.getString("Alamat_pasien"));
                pasien.setKunjungan(resultSet.getString("Kunjungan"));
                pasien.setUnit_tujuan(resultSet.getString("Unit_Tujuan"));
                pasien.setJenis_pelayanan(resultSet.getString("Jenis_Pelayanan"));
                list.add(pasien);
            }
            connection.commit();
            return list;
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PasienImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new PasienException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(PasienImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PasienImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PasienImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public Long getCount(String cari) throws PasienException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Long total = 0l;
        final String sql = "SELECT COUNT(idpasien) as total FROM dbpasien WHERE nama_pasien LIKE '" + cari + "' OR "
                + "jenis_kelamin='" + cari + "' OR alamat_pasien='" + cari + "' OR nama_kk='" + cari + "' ";
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
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
                Logger.getLogger(PasienImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new PasienException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(PasienImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PasienImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PasienImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
