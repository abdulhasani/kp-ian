/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.dao.impl;

import ian.RegisterRawatJalan.DatabaseHelper.DatabaseHelper;
import Nciz.RegisterRawatJalan.Entity.DBPeriksa;
import Nciz.RegisterRawatJalan.Entity.DBResepDokter;
import Nciz.RegisterRawatJalan.dao.DAOPeriksa;
import Nciz.RegisterRawatJalan.ex.DetailPeriksaException;
import Nciz.RegisterRawatJalan.ex.PeriksaException;
import com.dbMIF.AutoNumber;
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
public class PeriksaImpl implements DAOPeriksa {

    private Connection connection;
    private final String getKode = "SELECT * FROM dbperiksa WHERE idperiksa=?";
    private final String getAll = "SELECT * FROM dbperiksa ";
    private final String getCount = "SELECT COUNT(idperiksa) as total FROM dbperiksa  ";
    private final String insert = "INSERT INTO dbperiksa (idperiksa,tgl_periksa) VALUES (?,?) ";
    private final String insertDetailPeriksa = "INSERT INTO dbdetailperiksa (iddetailperiksa,"
            + "idperiksa,idpasien,iddokter,"
            + "idobat,obatperbiji,diagnosa,alergiobat) VALUES (?,?,?,?,?,?,?,?) ";
    private final String updatePeriksa = "UPDATE dbperiksa SET tgl_periksa=? WHERE idperiksa=?";
    private final String updateDetailPeriksa = "UPDATE dbdetailperiksa SET idpasien=?,iddokter=?,diagnosa=?,alergiobat=? WHERE idperiksa=? ";
    private final String delete = "DELETE FROM dbperiksa WHERE idperiksa=?";

    ;

    public PeriksaImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public DBPeriksa getKode(Integer kode) throws PeriksaException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {

            statement = connection.prepareStatement(getKode);
            statement.setInt(1, kode);
            resultSet = statement.executeQuery();
            DBPeriksa periksa = null;
            if (resultSet.next()) {
                periksa = new DBPeriksa();
                periksa.setIdperiksa(resultSet.getInt("idperiksa"));
                periksa.setTgl_periksa(resultSet.getDate("tgl_periksa"));

            }
            return periksa;
        } catch (SQLException ex) {
            throw new PeriksaException(ex.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    @Override
    public List<DBPeriksa> getAll(int skip, int max) throws PeriksaException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<DBPeriksa> list = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getAll + " LIMIT " + skip + "," + max);
            resultSet = statement.executeQuery();
            DBPeriksa periksa;
            while (resultSet.next()) {
                periksa = new DBPeriksa();
                periksa.setIdperiksa(resultSet.getInt("idperiksa"));
                periksa.setTgl_periksa(resultSet.getDate("tgl_periksa"));
                periksa.setDiagnosa(new DetialPeriksaImpl(connection).getIdPeriksa(resultSet.getInt("idperiksa")));
                periksa.setNamaPasien(new DetialPeriksaImpl(connection).getIdPeriksa(resultSet.getInt("idperiksa")));
                periksa.setDokter(new DetialPeriksaImpl(connection).getIdPeriksa(resultSet.getInt("idperiksa")));
                periksa.setKeterangan(new DetialPeriksaImpl(connection).getIdPeriksa(resultSet.getInt("idperiksa")));
                list.add(periksa);
            }
            connection.commit();
            return list;
        } catch (SQLException | DetailPeriksaException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PeriksaImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new PeriksaException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(PeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public Long getCount() throws PeriksaException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Long totalItem = 0l;
        try {

            statement = connection.prepareStatement(getCount);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                totalItem = resultSet.getLong("total");
            }
            return totalItem;
        } catch (SQLException ex) {
            throw new PeriksaException(ex.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<DBPeriksa> getAll(String cari, Date tgl, int skip, int max) throws PeriksaException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<DBPeriksa> list = new ArrayList<>();
        final String sql = "SELECT * FROM dbperiksa INNER JOIN dbdetailperiksa ON "
                + "dbperiksa.idperiksa=dbdetailperiksa.idperiksa "
                + "INNER JOIN dbpasien ON dbpasien.idpasien=dbdetailperiksa.idpasien "
                + "INNER JOIN dbdokter ON dbdokter.iddokter=dbdetailperiksa.iddokter "
                + "WHERE dbperiksa.tgl_periksa='" + new java.sql.Date(tgl.getTime()) + "' AND dbpasien.nama_pasien='" + cari + "'";
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql + " LIMIT " + skip + "," + max);
            resultSet = statement.executeQuery();
            DBPeriksa periksa;
            if (resultSet.next()) {
                periksa = new DBPeriksa();
                periksa.setIdperiksa(resultSet.getInt("idperiksa"));
                periksa.setTgl_periksa(resultSet.getDate("tgl_periksa"));
                periksa.setDiagnosa(new DetialPeriksaImpl(connection).getIdPeriksa(resultSet.getInt("idperiksa")));
                periksa.setNamaPasien(new DetialPeriksaImpl(connection).getIdPeriksa(resultSet.getInt("idperiksa")));
                periksa.setDokter(new DetialPeriksaImpl(connection).getIdPeriksa(resultSet.getInt("idperiksa")));
                periksa.setKeterangan(new DetialPeriksaImpl(connection).getIdPeriksa(resultSet.getInt("idperiksa")));

                list.add(periksa);

            }
            connection.commit();
            return list;
        } catch (SQLException | DetailPeriksaException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PeriksaImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new PeriksaException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(PeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public Long getCount(String cari, Date tgl) throws PeriksaException {
        final String sql = "SELECT COUNT(dbperiksa.idperiksa) as total FROM dbperiksa INNER JOIN dbdetailperiksa ON "
                + "dbperiksa.idperiksa=dbdetailperiksa.idperiksa "
                + "INNER JOIN dbpasien ON dbpasien.idpasien=dbdetailperiksa.idpasien "
                + "INNER JOIN dbdokter ON dbdokter.iddokter=dbdetailperiksa.iddokter "
                + "WHERE dbperiksa.tgl_periksa='" + new java.sql.Date(tgl.getTime()) + "' AND dbpasien.nama_pasien='" + cari + "' ";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Long totalItem = 0l;
        try {

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                totalItem = resultSet.getLong("total");
            }
            return totalItem;
        } catch (SQLException ex) {
            throw new PeriksaException(ex.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void insert(DBPeriksa dBPeriksa, List<DBResepDokter> resepDokters) throws PeriksaException {
        PreparedStatement periksa = null;
        PreparedStatement detailPeriksa = null;

        try {
            connection.setAutoCommit(false);
            periksa = connection.prepareStatement(insert);
            periksa.setInt(1, dBPeriksa.getIdperiksa());
            periksa.setDate(2, new java.sql.Date(dBPeriksa.getTgl_periksa().getTime()));
            periksa.executeUpdate();
            for (DBResepDokter d : resepDokters) {
                detailPeriksa = connection.prepareStatement(insertDetailPeriksa);
                detailPeriksa.setInt(1, Integer.parseInt(DatabaseHelper.getAutoNumberDetailPeriksa(new AutoNumber())));
                detailPeriksa.setInt(2, dBPeriksa.getIdperiksa());
                detailPeriksa.setInt(3, dBPeriksa.getVirtualInsert().getPasien().getIdpasien());
                detailPeriksa.setInt(4, dBPeriksa.getVirtualInsert().getDokter().getIddokter());
                detailPeriksa.setInt(5, d.getObat().getIdobat());
                detailPeriksa.setInt(6, d.getJmlahItem());
                detailPeriksa.setString(7, dBPeriksa.getVirtualInsert().getDiagnosa());
                detailPeriksa.setString(8, dBPeriksa.getVirtualInsert().getAlergiObat());
                detailPeriksa.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PeriksaImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }

            throw new PeriksaException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(PeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (periksa != null) {
                try {
                    periksa.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (detailPeriksa != null) {
                try {
                    detailPeriksa.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void update(DBPeriksa dBPeriksa) throws PeriksaException {
        PreparedStatement periksa = null;
        PreparedStatement detailPeriksa = null;

        try {
            connection.setAutoCommit(false);
            periksa = connection.prepareStatement(updatePeriksa);
            periksa.setDate(1, new java.sql.Date(dBPeriksa.getTgl_periksa().getTime()));
            periksa.setInt(2, dBPeriksa.getIdperiksa());
            periksa.executeUpdate();
            detailPeriksa = connection.prepareStatement(updateDetailPeriksa);
            detailPeriksa.setInt(1, dBPeriksa.getVirtualInsert().getPasien().getIdpasien());
            detailPeriksa.setInt(2, dBPeriksa.getVirtualInsert().getDokter().getIddokter());
            detailPeriksa.setString(3, dBPeriksa.getVirtualInsert().getDiagnosa());
            detailPeriksa.setString(4, dBPeriksa.getVirtualInsert().getAlergiObat());
            detailPeriksa.setInt(5, dBPeriksa.getIdperiksa());
            detailPeriksa.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PeriksaImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }

            throw new PeriksaException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(PeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (periksa != null) {
                try {
                    periksa.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (detailPeriksa != null) {
                try {
                    detailPeriksa.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void delete(Integer kodePeriksa) throws PeriksaException {
        PreparedStatement periksa = null;

        try {
            connection.setAutoCommit(false);
            periksa = connection.prepareStatement(delete);
            periksa.setInt(1, kodePeriksa);
            periksa.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PeriksaImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new PeriksaException(ex.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(PeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (periksa != null) {
                try {
                    periksa.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PeriksaImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
