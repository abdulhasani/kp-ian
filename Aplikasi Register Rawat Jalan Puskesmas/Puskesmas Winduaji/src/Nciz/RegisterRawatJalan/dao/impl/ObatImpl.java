/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.dao.impl;

import Nciz.RegisterRawatJalan.Entity.DBObat;
import Nciz.RegisterRawatJalan.dao.DAOObat;
import Nciz.RegisterRawatJalan.ex.ObatException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ncizh
 */
public class ObatImpl implements DAOObat {

    private Connection connection;
    private final String insert = "INSERT INTO dbobat (idobat,nama_obat,stok_awal,penerimaan,persediaan,pemakaian,sisa_stok,permintaan,harga_satuan,tgl_exp"
            + ") VALUES (?,?,?,?,?,?,?,?,?,?) ";
    private final String update = "UPDATE dbobat SET nama_obat=?,stok_awal=?,penerimaan=?,persediaan=?,pemakaian=?,sisa_stok=?,permintaan=?,harga_satuan=?,tgl_exp=?"
            + " WHERE idobat=?  ";
    private final String delete = "DELETE FROM dbobat WHERE idobat=? ";
    private final String getAll = "SELECT * FROM dbobat ";
    private final String getKode = "SELECT * FROM dbobat WHERE idobat=? ";
    private final String getCount = "SELECT COUNT(idobat) AS total FROM dbobat ";

    public ObatImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(DBObat obat) throws ObatException {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(insert);

            statement.setInt(1, obat.getIdobat());
            statement.setString(2, obat.getNama_obat());
            statement.setInt (3, obat.getStok_awal());
            statement.setInt(4, obat.getPenerimaan());
            statement.setInt(5, obat.getPersediaan());
            statement.setInt(6, obat.getPemakaian());
            statement.setInt(7, obat.getSisa_stok());
            statement.setInt(8, obat.getPermintaan());
            statement.setDouble(9, obat.getHarga_satuan());
            statement.setDate(10, new Date(obat.getTgl().getTime()));
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ObatImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new ObatException(ex.getMessage());
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(ObatImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ObatImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    @Override
    public void update(DBObat obat) throws ObatException {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(update);

           
            statement.setString(1, obat.getNama_obat());
            statement.setInt (2, obat.getStok_awal());
            statement.setInt(3, obat.getPenerimaan());
            statement.setInt(4, obat.getPersediaan());
            statement.setInt(5, obat.getPemakaian());
            statement.setInt(6, obat.getSisa_stok());
            statement.setInt(7, obat.getPermintaan());
            statement.setDouble(8, obat.getHarga_satuan());
            statement.setDate(9, new Date(obat.getTgl().getTime()));
            statement.setInt(10, obat.getIdobat());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ObatImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new ObatException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(ObatImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ObatImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void delete(Integer kode) throws ObatException {
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
                Logger.getLogger(ObatImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new ObatException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(ObatImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ObatImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<DBObat> getAll(int skip, int max) throws ObatException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<DBObat> list = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getAll + " LIMIT " + skip + "," + max);
            resultSet = statement.executeQuery();
            DBObat obat;
            while (resultSet.next()) {
                obat = new DBObat();
                obat.setIdobat(resultSet.getInt("idobat"));
                obat.setNama_obat(resultSet.getString("nama_obat"));
                obat.setStok_awal(resultSet.getInt("stok_awal"));
                obat.setPenerimaan(resultSet.getInt("penerimaan"));
                obat.setPersediaan(resultSet.getInt("persediaan"));
                obat.setPemakaian(resultSet.getInt("pemakaian"));
                obat.setSisa_stok(resultSet.getInt("sisa_stok"));
                obat.setPermintaan(resultSet.getInt("permintaan"));                
                obat.setHarga_satuan(resultSet.getDouble("harga_satuan"));
                obat.setTgl(resultSet.getDate("tgl_exp"));
                list.add(obat);
            }
            connection.commit();
            return list;
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ObatImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new ObatException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(ObatImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ObatImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ObatImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public DBObat getkode(int Kode) throws ObatException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            
            statement = connection.prepareStatement(getKode);
            statement.setInt(1, Kode);
            resultSet = statement.executeQuery();
            DBObat obat = null;
            if (resultSet.next()) {
               obat = new DBObat();
               obat.setIdobat(resultSet.getInt("idobat"));
                obat.setNama_obat(resultSet.getString("nama_obat"));
                obat.setStok_awal(resultSet.getInt("stok_awal"));
                obat.setPenerimaan(resultSet.getInt("penerimaan"));
                obat.setPersediaan(resultSet.getInt("persediaan"));
                obat.setPemakaian(resultSet.getInt("pemakaian"));
                obat.setSisa_stok(resultSet.getInt("sisa_stok"));
                obat.setPermintaan(resultSet.getInt("permintaan"));                
                obat.setHarga_satuan(resultSet.getDouble("harga_satuan"));
                obat.setTgl(resultSet.getDate("tgl_exp"));
            }
            
            return obat;
        } catch (SQLException ex) {
           
            throw new ObatException(ex.getMessage());
        } finally {
            
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ObatImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ObatImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public Long getCount() throws ObatException {
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
                Logger.getLogger(ObatImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new ObatException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(ObatImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ObatImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ObatImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<DBObat> getCari(String cari, int skip, int max) throws ObatException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<DBObat> list = new ArrayList<>();
        final String sql = "SELECT * FROM dbobat WHERE nama_obat LIKE '" + cari + "%' "
                + "OR tgl_exp='" + cari + "' ";
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql + " LIMIT " + skip + "," + max);
            resultSet = statement.executeQuery();
            DBObat obat;
            while (resultSet.next()) {
                obat = new DBObat();
                obat.setIdobat(resultSet.getInt("idobat"));
                obat.setNama_obat(resultSet.getString("nama_obat"));
                obat.setStok_awal(resultSet.getInt("stok_awal"));
                obat.setPenerimaan(resultSet.getInt("penerimaan"));
                obat.setPersediaan(resultSet.getInt("persediaan"));
                obat.setPemakaian(resultSet.getInt("pemakaian"));
                obat.setSisa_stok(resultSet.getInt("sisa_stok"));
                obat.setPermintaan(resultSet.getInt("permintaan"));                
                obat.setHarga_satuan(resultSet.getDouble("harga_satuan"));
                obat.setTgl(resultSet.getDate("tgl_exp"));
                list.add(obat);
            }
            connection.commit();
            return list;
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ObatImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new ObatException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(ObatImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ObatImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ObatImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public Long getCount(String cari) throws ObatException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Long total = 0l;
        final String sql = "SELECT COUNT(idobat) AS total FROM dbobat WHERE nama_obat LIKE '" + cari + "%' "
                + "OR tgl_exp='" + cari + "' ";
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
                Logger.getLogger(ObatImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new ObatException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(ObatImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ObatImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ObatImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
