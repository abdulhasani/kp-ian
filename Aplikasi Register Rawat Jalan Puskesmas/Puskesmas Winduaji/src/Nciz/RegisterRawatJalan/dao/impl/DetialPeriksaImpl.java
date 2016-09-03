/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.dao.impl;

import Nciz.RegisterRawatJalan.Entity.DBDetailPeriksa;
import Nciz.RegisterRawatJalan.dao.DAODetilPeriksa;
import Nciz.RegisterRawatJalan.ex.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ncizh
 */
public class DetialPeriksaImpl implements DAODetilPeriksa {

    private Connection connection;
    private final String delete = "DELETE FROM dbperiksa WHERE idperiksa=? ";
    private final String getAll = "SELECT * FROM dbperiksa LEFT OUTER JOIN dbdetailperiksa ON "
            + "dbperiksa.idperiksa=dbdetailperiksa.idperiksa ";
    private final String getIdPeriksa = "SELECT * FROM dbdetailperiksa  WHERE idperiksa=?";
    private final String getIdPeriksaAndDate = "SELECT * FROM dbdetailperiksa INNER JOIN dbperiksa ON "
            + "dbdetailperiksa.idperiksa=dbperiksa.idperiksa WHERE dbdetailperiksa.idperiksa=? AND dbperiksa.tgl_periksa=?";
    private final String getKode = "SELECT * FROM dbperiksa LEFT OUTER JOIN dbdetailperiksa ON "
            + "dbperiksa.idperiksa=dbdetailperiksa.idperiksa WHERE dbdetailperiksa.idperiksa=?";
    private final String getCount = "SELECT COUNT(dbperiksa.idperiksa) as total FROM dbperiksa INNER JOIN dbdetailperiksa ON "
            + "dbperiksa.idperiksa=dbdetailperiksa.idperiksa ";
    private final String getIdPeriksaUpdateObat = "SELECT * FROM dbdetailperiksa INNER JOIN dbperiksa ON "
            + "dbdetailperiksa.idperiksa=dbperiksa.idperiksa WHERE dbdetailperiksa.idperiksa=?";

    public DetialPeriksaImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void delete(Integer kdDetailPeriksa) throws DetailPeriksaException {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(delete);
            statement.setInt(1, kdDetailPeriksa);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(DetialPeriksaImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new DetailPeriksaException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(DetialPeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DetialPeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<DBDetailPeriksa> getAll(int skip, int max) throws DetailPeriksaException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<DBDetailPeriksa> list = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getAll + " LIMIT " + skip + "," + max);
            resultSet = statement.executeQuery();
            DBDetailPeriksa detailPeriksa;
            while (resultSet.next()) {
                detailPeriksa = new DBDetailPeriksa();
                detailPeriksa.setIdDetailPeriksa(resultSet.getLong("dbdetailperiksa.iddetailperiksa"));
                detailPeriksa.setPeriksa(new PeriksaImpl(connection).getKode(resultSet.getInt("dbperiksa.idperiksa")));
                detailPeriksa.setPasien(new PasienImpl(connection).getkode(resultSet.getInt("dbdetailperiksa.idpasien")));
                detailPeriksa.setDokter(new DokterImpl(connection).getkode(resultSet.getInt("dbdetailperiksa.iddokter")));
                detailPeriksa.setObat(new ObatImpl(connection).getkode(resultSet.getInt("dbdetailperiksa.idobat")));
                detailPeriksa.setObatPerBijij(resultSet.getInt("dbdetailperiksa.ObatPerbiji"));
                detailPeriksa.setDiagnosa(resultSet.getString("dbdetailperiksa.diagnosa"));
                detailPeriksa.setAlergiObat(resultSet.getString("dbdetailperiksa.alergiObat"));
                list.add(detailPeriksa);
            }
            connection.commit();
            return list;
        } catch (SQLException | PeriksaException | PasienException | DokterException | ObatException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(DetialPeriksaImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }


            throw new DetailPeriksaException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(DetialPeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DetialPeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DetialPeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<DBDetailPeriksa> getKode(Integer kodePeriksa) throws DetailPeriksaException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<DBDetailPeriksa> list = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getKode);
            statement.setInt(1, kodePeriksa);
            resultSet = statement.executeQuery();
            DBDetailPeriksa detailPeriksa;
            while (resultSet.next()) {
                detailPeriksa = new DBDetailPeriksa();
                detailPeriksa.setIdDetailPeriksa(resultSet.getLong("dbdetailperiksa.iddetailperiksa"));
                detailPeriksa.setPeriksa(new PeriksaImpl(connection).getKode(resultSet.getInt("dbperiksa.idperiksa")));
                detailPeriksa.setPasien(new PasienImpl(connection).getkode(resultSet.getInt("dbdetailperiksa.idpasien")));
                detailPeriksa.setDokter(new DokterImpl(connection).getkode(resultSet.getInt("dbdetailperiksa.iddokter")));
                detailPeriksa.setObat(new ObatImpl(connection).getkode(resultSet.getInt("dbdetailperiksa.idobat")));
                detailPeriksa.setObatPerBijij(resultSet.getInt("dbdetailperiksa.ObatPerbiji"));
                detailPeriksa.setDiagnosa(resultSet.getString("dbdetailperiksa.diagnosa"));
                detailPeriksa.setAlergiObat(resultSet.getString("dbdetailperiksa.alergiObat"));
                list.add(detailPeriksa);
            }
            connection.commit();
            return list;
        } catch (SQLException | PeriksaException | PasienException |
                DokterException | ObatException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(DetialPeriksaImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new DetailPeriksaException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(DetialPeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DetialPeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DetialPeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public Long getCount() throws DetailPeriksaException {
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
                Logger.getLogger(DetialPeriksaImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new DetailPeriksaException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(DetialPeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DetialPeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DetialPeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<DBDetailPeriksa> getAll(String cari, int skip, int max) throws DetailPeriksaException {
        String sql = "SELECT * FROM dbperiksa LEFT OUTER JOIN dbdetailperiksa ON "
                + "dbperiksa.idperiksa=dbdetailperiksa.idperiksa "
                + "LEFT OUTER JOIN dbpasien ON dbpasien.idpasien=dbdetailperiksa.idpasien "
                + "WHERE dbperiksa.tgl_periksa='" + cari + "' OR dbpasien.nama_pasien LIKE '" + cari + "%'";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<DBDetailPeriksa> list = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql + " LIMIT " + skip + "," + max);
            resultSet = statement.executeQuery();
            DBDetailPeriksa detailPeriksa;
            while (resultSet.next()) {
                detailPeriksa = new DBDetailPeriksa();
                detailPeriksa.setIdDetailPeriksa(resultSet.getLong("dbdetailperiksa.iddetailperiksa"));
                detailPeriksa.setPeriksa(new PeriksaImpl(connection).getKode(resultSet.getInt("dbperiksa.idperiksa")));
                detailPeriksa.setPasien(new PasienImpl(connection).getkode(resultSet.getInt("dbdetailperiksa.idpasien")));
                detailPeriksa.setDokter(new DokterImpl(connection).getkode(resultSet.getInt("dbdetailperiksa.iddokter")));
                detailPeriksa.setObat(new ObatImpl(connection).getkode(resultSet.getInt("dbdetailperiksa.idobat")));
                detailPeriksa.setObatPerBijij(resultSet.getInt("dbdetailperiksa.ObatPerbiji"));
                detailPeriksa.setDiagnosa(resultSet.getString("dbdetailperiksa.diagnosa"));
                detailPeriksa.setAlergiObat(resultSet.getString("dbdetailperiksa.alergiObat"));
                list.add(detailPeriksa);
            }
            connection.commit();
            return list;
        } catch (SQLException | PeriksaException | PasienException | DokterException | ObatException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(DetialPeriksaImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }


            throw new DetailPeriksaException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(DetialPeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DetialPeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DetialPeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public Long getCount(String cari) throws DetailPeriksaException {
        String sql = "SELECT COUNT(dbdetailperiksa.iddetailperiksa) AS total FROM dbperiksa LEFT OUTER JOIN dbdetailperiksa ON "
                + "dbperiksa.idperiksa=dbdetailperiksa.idperiksa "
                + "LEFT OUTER JOIN dbpasien ON dbpasien.idpasien=dbdetailperiksa.idpasien "
                + "WHERE dbperiksa.tgl_periksa='" + cari + "' OR dbpasien.nama_pasien LIKE '" + cari + "%'";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Long total = 0l;
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
                Logger.getLogger(DetialPeriksaImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new DetailPeriksaException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(DetialPeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DetialPeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DetialPeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public DBDetailPeriksa getIdPeriksa(Integer idPeriksa) throws DetailPeriksaException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(getIdPeriksa);
            statement.setInt(1, idPeriksa);
            resultSet = statement.executeQuery();
            DBDetailPeriksa detailPeriksa = null;
            if (resultSet.next()) {
                detailPeriksa = new DBDetailPeriksa();
                detailPeriksa.setIdDetailPeriksa(resultSet.getLong("iddetailperiksa"));
                detailPeriksa.setPeriksa(new PeriksaImpl(connection).getKode(resultSet.getInt("idperiksa")));
                detailPeriksa.setPasien(new PasienImpl(connection).getkode(resultSet.getInt("idpasien")));
                detailPeriksa.setDokter(new DokterImpl(connection).getkode(resultSet.getInt("iddokter")));
                detailPeriksa.setObat(new ObatImpl(connection).getkode(resultSet.getInt("idobat")));
                detailPeriksa.setObatPerBijij(resultSet.getInt("ObatPerbiji"));
                detailPeriksa.setDiagnosa(resultSet.getString("diagnosa"));
                detailPeriksa.setAlergiObat(resultSet.getString("alergiObat"));
            }
            return detailPeriksa;
        } catch (SQLException | PeriksaException | PasienException |
                DokterException | ObatException ex) {
            throw new DetailPeriksaException(ex.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DetialPeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DetialPeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<DBDetailPeriksa> getIdPeriksaAll(Integer idPeriksa, Date date) throws DetailPeriksaException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<DBDetailPeriksa> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement(getIdPeriksaAndDate);
            statement.setInt(1, idPeriksa);
            statement.setDate(2, new java.sql.Date(date.getTime()));
            resultSet = statement.executeQuery();
            DBDetailPeriksa detailPeriksa = null;
            while (resultSet.next()) {
                detailPeriksa = new DBDetailPeriksa();
                detailPeriksa.setIdDetailPeriksa(resultSet.getLong("iddetailperiksa"));
                detailPeriksa.setPeriksa(new PeriksaImpl(connection).getKode(resultSet.getInt("idperiksa")));
                detailPeriksa.setPasien(new PasienImpl(connection).getkode(resultSet.getInt("idpasien")));
                detailPeriksa.setDokter(new DokterImpl(connection).getkode(resultSet.getInt("iddokter")));
                detailPeriksa.setObat(new ObatImpl(connection).getkode(resultSet.getInt("idobat")));
                detailPeriksa.setObatPerBijij(resultSet.getInt("ObatPerbiji"));
                detailPeriksa.setDiagnosa(resultSet.getString("diagnosa"));
                detailPeriksa.setAlergiObat(resultSet.getString("alergiObat"));
                list.add(detailPeriksa);
            }
            return list;
        } catch (SQLException | PeriksaException | PasienException |
                DokterException | ObatException ex) {
            throw new DetailPeriksaException(ex.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DetialPeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DetialPeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<DBDetailPeriksa> getIdPeriksaAll(Integer idPeriksa) throws DetailPeriksaException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<DBDetailPeriksa> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement(getIdPeriksaUpdateObat);
            statement.setInt(1, idPeriksa);
            resultSet = statement.executeQuery();
            DBDetailPeriksa detailPeriksa = null;
            while (resultSet.next()) {
                detailPeriksa = new DBDetailPeriksa();
                detailPeriksa.setIdDetailPeriksa(resultSet.getLong("iddetailperiksa"));
                detailPeriksa.setPeriksa(new PeriksaImpl(connection).getKode(resultSet.getInt("idperiksa")));
                detailPeriksa.setPasien(new PasienImpl(connection).getkode(resultSet.getInt("idpasien")));
                detailPeriksa.setDokter(new DokterImpl(connection).getkode(resultSet.getInt("iddokter")));
                detailPeriksa.setObat(new ObatImpl(connection).getkode(resultSet.getInt("idobat")));
                detailPeriksa.setObatPerBijij(resultSet.getInt("ObatPerbiji"));
                detailPeriksa.setDiagnosa(resultSet.getString("diagnosa"));
                detailPeriksa.setAlergiObat(resultSet.getString("alergiObat"));
                list.add(detailPeriksa);
            }
            return list;
        } catch (SQLException | PeriksaException | PasienException |
                DokterException | ObatException ex) {
            throw new DetailPeriksaException(ex.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DetialPeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DetialPeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
