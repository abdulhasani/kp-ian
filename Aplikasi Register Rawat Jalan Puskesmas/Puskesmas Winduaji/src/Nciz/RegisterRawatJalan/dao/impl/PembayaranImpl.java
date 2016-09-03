/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.dao.impl;

import Nciz.RegisterRawatJalan.Entity.DBDetailPeriksa;
import Nciz.RegisterRawatJalan.Entity.DBObat;
import Nciz.RegisterRawatJalan.Entity.DBPembayaran;
import Nciz.RegisterRawatJalan.dao.DAODetilPeriksa;
import Nciz.RegisterRawatJalan.dao.DAOObat;
import Nciz.RegisterRawatJalan.dao.DAOPembayaran;
import Nciz.RegisterRawatJalan.ex.DetailPeriksaException;
import Nciz.RegisterRawatJalan.ex.ObatException;
import Nciz.RegisterRawatJalan.ex.PembayaranException;
import Nciz.RegisterRawatJalan.ex.PeriksaException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ncizh
 */
public class PembayaranImpl implements DAOPembayaran {

    private Connection connection;
    private final String insert = "INSERT INTO dbpembayaran (idpembayaran,idperiksa,bayar_obat"
            + ",bayar_periksa,total) VALUES (?,?,?,?,?) ";
    private final String update = "UPDATE dbobat SET pemakaian=?,"
            + "permintaan=?,sisa_stok=?  WHERE idobat=? ";
    private final String delete = "DELETE FROM dbpembayaran WHERE idpembayaran=? ";
    private final String getAll = "SELECT * FROM dbpembayaran ";
    private final String getKode = "SELECT * FROM dbpembayaran WHERE idpembayaran=? ";
    private final String getKodePeriksa = "SELECT * FROM dbpembayaran INNER JOIN "
            + "dbperiksa ON dbpembayaran.idperiksa=dbperiksa.idperiksa WHERE dbperiksa.idperiksa=?  ";
    private final String getCount = "SELECT COUNT(idpembayaran) AS total FROM dbpembayaran ";
    private final String updateStokZero="UPDATE dbobat SET stok_awal=?,"
            + "penerimaan=?,persediaan=?,pemakaian=?,"
            + "permintaan=?,sisa_stok=?  WHERE idobat=? ";

    public PembayaranImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(DBPembayaran pembayaran, Integer kodePeriksa) throws PembayaranException {
        PreparedStatement bayar;
        PreparedStatement obat;
        bayar = null;
        obat = null;
        try {
            connection.setAutoCommit(false);
            bayar = connection.prepareStatement(insert);
            bayar.setInt(1, pembayaran.getIdpembayaran());
            bayar.setInt(2, kodePeriksa);
            bayar.setDouble(3, pembayaran.getBayar_obat());
            bayar.setDouble(4, pembayaran.getBayar_periksa());
            bayar.setDouble(5, pembayaran.getTotal());
            bayar.executeUpdate();
            DAODetilPeriksa dAODetilPeriksa;
            DAOObat oObat;
            dAODetilPeriksa = new DetialPeriksaImpl(connection);
            oObat = new ObatImpl(connection);
            List<DBDetailPeriksa> idPeriksaAll;
            idPeriksaAll = dAODetilPeriksa.getIdPeriksaAll(kodePeriksa);

            for (DBDetailPeriksa db : idPeriksaAll) {
                DBObat kode;
                kode = oObat.getkode(db.getObat().getIdobat());

                Integer updatePemakaian = kode.getPemakaian() + db.getObatPerBijij();
                Integer updateSisaStok = kode.getPersediaan() - updatePemakaian;
                if (updateSisaStok == 0) {
                    obat = connection.prepareStatement(updateStokZero);
                    obat.setInt(1, 0);
                    obat.setInt(2, 0);
                    obat.setInt(3, 0);
                    obat.setInt(4, 0);
                    obat.setInt(5, 0);
                    obat.setInt(6, 0);
                    obat.setInt(7, kode.getIdobat());
                } else {
                    Integer updatePermintaan = kode.getPersediaan() - updateSisaStok;
                    kode.setPemakaian(updatePemakaian);
                    kode.setSisa_stok(updateSisaStok);
                    kode.setPermintaan(updatePermintaan);
                    obat = connection.prepareStatement(update);
                    obat.setInt(1, kode.getPemakaian());
                    obat.setInt(2, kode.getPermintaan());
                    obat.setInt(3, kode.getSisa_stok());
                    obat.setInt(4, kode.getIdobat());
                }
                obat.executeUpdate();
            }
            connection.commit();
        } catch (SQLException | DetailPeriksaException | ObatException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PembayaranImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(PembayaranImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(PembayaranImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (bayar != null) {
                try {
                    bayar.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PembayaranImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (obat != null) {
                try {
                    obat.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PembayaranImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void update(DBPembayaran pembayaran) throws PembayaranException {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(update);

            statement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PembayaranImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new PembayaranException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(PembayaranImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PembayaranImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void delete(Integer kode) throws PembayaranException {
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
                Logger.getLogger(PembayaranImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new PembayaranException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(PembayaranImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PembayaranImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<DBPembayaran> getAll(int skip, int max) throws PembayaranException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<DBPembayaran> list = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getAll + " LIMIT " + skip + "," + max);
            resultSet = statement.executeQuery();
            DBPembayaran pembayaran;
            while (resultSet.next()) {
                pembayaran = new DBPembayaran();
                pembayaran.setIdpembayaran(resultSet.getInt("idpembayaran"));
                pembayaran.setPeriksa(new PeriksaImpl(connection).getKode(resultSet.getInt("idperiksa")));
                pembayaran.setBayar_obat(resultSet.getDouble("bayar_obat"));
                pembayaran.setBayar_periksa(resultSet.getDouble("bayar_periksa"));
                pembayaran.setTotal(resultSet.getDouble("total"));
                list.add(pembayaran);
            }
            connection.commit();
            return list;
        } catch (SQLException | PeriksaException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PembayaranImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new PembayaranException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(PembayaranImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PembayaranImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PembayaranImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public DBPembayaran getkode(int Kode) throws PembayaranException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getKode);
            statement.setInt(1, Kode);
            resultSet = statement.executeQuery();
            DBPembayaran pembayaran = null;
            while (resultSet.next()) {
                pembayaran = new DBPembayaran();
                pembayaran.setIdpembayaran(resultSet.getInt("idpembayaran"));
                //pembayaran.setPeriksa(new DetialPeriksaImpl(connection).getKode(resultSet.getInt("idperiksa")));
                pembayaran.setBayar_obat(resultSet.getDouble("bayar_obat"));
                pembayaran.setBayar_periksa(resultSet.getDouble("bayar_periksa"));
                pembayaran.setTotal(resultSet.getDouble("total"));


            }
            connection.commit();
            return pembayaran;
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PembayaranImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new PembayaranException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(PembayaranImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PembayaranImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PembayaranImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public Long getCount() throws PembayaranException {
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
                Logger.getLogger(PembayaranImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new PembayaranException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(PembayaranImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PembayaranImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PembayaranImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<DBPembayaran> getCari(String cari, int skip, int max) throws PembayaranException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<DBPembayaran> list = new ArrayList<>();
        String sql = "SELECT * FROM dbpembayaran INNER JOIN dbperiksa ON dbpembayaran.idperiksa=dbperiksa.idperiksa"
                + " WHERE dbperiksa.idperiksa='" + cari + "'";
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql
                    + " LIMIT " + skip + "," + max);
            resultSet = statement.executeQuery();
            DBPembayaran pembayaran;
            while (resultSet.next()) {
                pembayaran = new DBPembayaran();
                pembayaran.setIdpembayaran(resultSet.getInt("idpembayaran"));
                pembayaran.setPeriksa(new PeriksaImpl(connection).getKode(resultSet.getInt("idperiksa")));
                pembayaran.setBayar_obat(resultSet.getDouble("bayar_obat"));
                pembayaran.setBayar_periksa(resultSet.getDouble("bayar_periksa"));
                pembayaran.setTotal(resultSet.getDouble("total"));
                list.add(pembayaran);
            }
            connection.commit();
            return list;
        } catch (SQLException | PeriksaException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PembayaranImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new PembayaranException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(PembayaranImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PembayaranImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PembayaranImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public Long getCount(String cari) throws PembayaranException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Long total = 0l;
        String sql = "SELECT COUNT(dbpembayaran.idpembayaran) as total FROM dbpembayaran INNER JOIN "
                + " dbperiksa ON dbpembayaran.idperiksa=dbperiksa.idperiksa "
                + "WHERE dbperiksa.idperiksa='" + cari + "'";
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
                Logger.getLogger(PembayaranImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new PembayaranException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(PembayaranImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PembayaranImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PembayaranImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public DBPembayaran getkodePeriksa(int kodePeriksa) throws PembayaranException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getKode);
            statement.setInt(1, kodePeriksa);
            resultSet = statement.executeQuery();
            DBPembayaran pembayaran = null;
            while (resultSet.next()) {
                pembayaran = new DBPembayaran();
                pembayaran.setIdpembayaran(resultSet.getInt("dbpembayaran.idpembayaran"));
                pembayaran.setPeriksa(new PeriksaImpl(connection).getKode(resultSet.getInt("dbpembayaran.idperiksa")));
                pembayaran.setBayar_obat(resultSet.getDouble("dbpembayaran.bayar_obat"));
                pembayaran.setBayar_periksa(resultSet.getDouble("dbpembayaran.bayar_periksa"));
                pembayaran.setTotal(resultSet.getDouble("dbpembayaran.total"));


            }
            connection.commit();
            return pembayaran;
        } catch (SQLException | PeriksaException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PembayaranImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new PembayaranException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(PembayaranImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PembayaranImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PembayaranImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
