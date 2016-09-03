/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ian.RegisterRawatJalan.DatabaseHelper;

import Nciz.RegisterRawatJalan.dao.*;
import Nciz.RegisterRawatJalan.dao.impl.*;
import com.dbMIF.AutoNumber;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ncizh
 */
public class DatabaseHelper {

    private static Connection koneksi;
    private static DAOAdministrator administrator;
    private static DAODokter dokter;
    private static DAOPasien pasien;
    private static DAOPembayaran pembayaran;
    private static DAODetilPeriksa detailPeriksa;
    private static DAOObat obat;
    private static DAOPeriksa periksaUtama;

    public static DAOPeriksa getPeriksaUtama() {
        if (periksaUtama == null) {
            periksaUtama = new PeriksaImpl(getConnection());
        }
        return periksaUtama;
    }

    public static DAOAdministrator getAdministrator() {
        if (administrator == null) {
            administrator = new AdministratorImpl(getConnection());
        }
        return administrator;
    }

    public static DAODokter getDokter() {
        if (dokter == null) {
            dokter = new DokterImpl(getConnection());
        }
        return dokter;
    }

    public static DAOPasien getPasien() {
        if (pasien == null) {
            pasien = new PasienImpl(getConnection());
        }
        return pasien;
    }

    public static DAOPembayaran getPembayaran() {
        if (pembayaran == null) {
            pembayaran = new PembayaranImpl(getConnection());
        }
        return pembayaran;
    }

    public static DAODetilPeriksa getPeriksa() {
        if (detailPeriksa == null) {
            detailPeriksa = new DetialPeriksaImpl(getConnection());
        }
        return detailPeriksa;
    }

    public static DAOObat getObat() {
        if (obat == null) {
            obat = new ObatImpl(getConnection());
        }
        return obat;
    }

    /**
     * @return the administrator
     */
    public static Connection getConnection() {
        if (koneksi == null) {
            try {
                MysqlDataSource mysqlDataSource = new MysqlConnectionPoolDataSource();
                mysqlDataSource.setURL("jdbc:mysql://localhost:3306/puskesmas");
                mysqlDataSource.setUser("root");
                mysqlDataSource.setPassword("");
                koneksi = mysqlDataSource.getConnection();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return koneksi;
    }

    public static String getAutoNumber(AutoNumber number) {
        number.setConnection(getConnection());
        number.setTableName("dbadministrator");
        String autoNumberInt = number.getAutoNumberInt();
        return autoNumberInt;
    }

    public static String getAutoNumberDokter(AutoNumber number) {
        number.setConnection(getConnection());
        number.setTableName("dbdokter");
        String autoNumberInt = number.getAutoNumberInt();
        return autoNumberInt;
    }

    public static String getAutoNumberPasien(AutoNumber number) {
        number.setConnection(getConnection());
        number.setTableName("dbpasien");
        String autoNumberInt = number.getAutoNumberInt();
        return autoNumberInt;
    }

    public static String getAutoNumberObat(AutoNumber number) {
        number.setConnection(getConnection());
        number.setTableName("dbobat");
        String autoNumberInt = number.getAutoNumberInt();
        return autoNumberInt;
    }

    public static String getAutoNumberPeriksa(AutoNumber number) {
        number.setConnection(getConnection());
        number.setTableName("dbperiksa");
        String autoNumberInt = number.getAutoNumberInt();
        return autoNumberInt;
    }
     public static String getAutoNumberDetailPeriksa(AutoNumber number) {
        number.setConnection(getConnection());
        number.setTableName("dbdetailperiksa");
        String autoNumberInt = number.getAutoNumberInt();
        return autoNumberInt;
    }

    public static String getAutoNumberPembayaran(AutoNumber number) {
        number.setConnection(getConnection());
        number.setTableName("dbpembayaran");
        String autoNumberInt = number.getAutoNumberInt();
        return autoNumberInt;
    }
}
