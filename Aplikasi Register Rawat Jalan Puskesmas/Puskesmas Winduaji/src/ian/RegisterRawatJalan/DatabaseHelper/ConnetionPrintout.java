/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ian.RegisterRawatJalan.DatabaseHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Lucifer-PC
 */
public class ConnetionPrintout {

    public static String PathReport = System.getProperty("user.dir") + "\\src\\ian\\RegisterRawatJalan\\Ireport\\";

    public static Connection getConnection() {
        Connection koneksi = null;
        if (koneksi == null) {
            try {
                String url;
                String user;
                String password;
                url = "jdbc:mysql://localhost:3306/puskesmas";
                user = "root";
                password = "";
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                koneksi = DriverManager.getConnection(url, user, password);

            } catch (SQLException e) {
                System.out.println("koneksi error");
            }
        }
        return koneksi;
    }

}
