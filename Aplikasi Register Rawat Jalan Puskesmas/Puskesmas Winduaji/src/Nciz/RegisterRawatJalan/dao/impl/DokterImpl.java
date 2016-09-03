/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.dao.impl;

import Nciz.RegisterRawatJalan.Entity.DBDokter;
import Nciz.RegisterRawatJalan.dao.DAODokter;
import Nciz.RegisterRawatJalan.ex.DokterException;
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
public class DokterImpl implements DAODokter {

    private Connection connection;
    private final String insert = "INSERT INTO dbdokter (iddokter,nama_dokter,alamat_dokter,telp_dokter"
            + ",biaya) VALUES (?,?,?,?,?) ";
    private final String update = "UPDATE dbdokter SET nama_dokter=?,alamat_dokter=?,telp_dokter=?"
            + ",biaya=? WHERE iddokter=?  ";
    private final String delete = "DELETE FROM dbdokter WHERE iddokter=? ";
    private final String getAll = "SELECT * FROM dbdokter ";
    private final String getKode = "SELECT * FROM dbdokter WHERE iddokter=? ";
    private final String getCount = "SELECT COUNT(iddokter) AS total FROM dbdokter ";

    public DokterImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(DBDokter dokter) throws DokterException {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(insert);
            statement.setInt(1, dokter.getIddokter());
            statement.setString(2, dokter.getNama_dokter());
            statement.setString(3, dokter.getAlamat_dokter());
            statement.setString(4, dokter.getTelp_dokter());
            statement.setDouble(5, dokter.getBiaya());

            statement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(DokterImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new DokterException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(DokterImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DokterImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void update(DBDokter dokter) throws DokterException {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(update);
            statement.setString(1, dokter.getNama_dokter());
            statement.setString(2, dokter.getAlamat_dokter());
            statement.setString(3, dokter.getTelp_dokter());
            statement.setDouble(4, dokter.getBiaya());
            statement.setInt(5, dokter.getIddokter());

            statement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(DokterImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new DokterException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(DokterImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DokterImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void delete(Integer kode) throws DokterException {
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
                Logger.getLogger(DokterImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new DokterException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(DokterImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DokterImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<DBDokter> getAll(int skip, int max) throws DokterException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<DBDokter> list = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getAll + " LIMIT " + skip + "," + max);
            resultSet = statement.executeQuery();
            DBDokter dokter;
            while (resultSet.next()) {
                dokter = new DBDokter();
                dokter.setIddokter(resultSet.getInt("iddokter"));
                dokter.setNama_dokter(resultSet.getString("Nama_dokter"));
                dokter.setAlamat_dokter(resultSet.getString("Alamat_dokter"));
                dokter.setTelp_dokter(resultSet.getString("Telp_dokter"));
                dokter.setBiaya(resultSet.getDouble("Biaya"));

                list.add(dokter);
            }
            connection.commit();
            return list;
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(DokterImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new DokterException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(DokterImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DokterImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DokterImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public DBDokter getkode(int Kode) throws DokterException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            statement = connection.prepareStatement(getKode);
            statement.setInt(1, Kode);
            resultSet = statement.executeQuery();
            DBDokter dokter = null;
            while (resultSet.next()) {
                dokter = new DBDokter();
                dokter.setIddokter(resultSet.getInt("iddokter"));
                dokter.setNama_dokter(resultSet.getString("nama_dokter"));
                dokter.setAlamat_dokter(resultSet.getString("alamat_dokter"));
                dokter.setTelp_dokter(resultSet.getString("telp_dokter"));
                dokter.setBiaya(resultSet.getDouble("biaya"));

            }

            return dokter;
        } catch (SQLException ex) {

            throw new DokterException(ex.getMessage());
        } finally {

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DokterImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DokterImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public Long getCount() throws DokterException {
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
                Logger.getLogger(DokterImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new DokterException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(DokterImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DokterImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DokterImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<DBDokter> getCari(String cari, int skip, int max) throws DokterException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<DBDokter> list = new ArrayList<>();
        final String sql = "SELECT * FROM dbdokter WHERE nama_dokter LIKE '" + cari + "%' OR telp_dokter='" + cari + "' ";
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql + " LIMIT " + skip + "," + max);
            resultSet = statement.executeQuery();
            DBDokter dokter;
            while (resultSet.next()) {
                dokter = new DBDokter();
                dokter.setIddokter(resultSet.getInt("iddokter"));
                dokter.setNama_dokter(resultSet.getString("Nama_dokter"));
                dokter.setAlamat_dokter(resultSet.getString("Alamat_dokter"));
                dokter.setTelp_dokter(resultSet.getString("Telp_dokter"));
                dokter.setBiaya(resultSet.getDouble("Biaya"));

                list.add(dokter);
            }
            connection.commit();
            return list;
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(DokterImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new DokterException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(DokterImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DokterImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DokterImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public Long getCount(String cari) throws DokterException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Long total = 0l;
        final String sql = "SELECT COUNT(iddokter) AS total FROM dbdokter WHERE nama_dokter LIKE '" + cari + "%' OR telp_dokter='" + cari + "' ";
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
                Logger.getLogger(DokterImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new DokterException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(DokterImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DokterImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DokterImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<DBDokter> getAll() throws DokterException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<DBDokter> list = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getAll);
            resultSet = statement.executeQuery();
            DBDokter dokter;
            while (resultSet.next()) {
                dokter = new DBDokter();
                dokter.setIddokter(resultSet.getInt("iddokter"));
                dokter.setNama_dokter(resultSet.getString("Nama_dokter"));
                dokter.setAlamat_dokter(resultSet.getString("Alamat_dokter"));
                dokter.setTelp_dokter(resultSet.getString("Telp_dokter"));
                dokter.setBiaya(resultSet.getDouble("Biaya"));

                list.add(dokter);
            }
            connection.commit();
            return list;
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(DokterImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new DokterException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(DokterImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DokterImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DokterImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    
}
