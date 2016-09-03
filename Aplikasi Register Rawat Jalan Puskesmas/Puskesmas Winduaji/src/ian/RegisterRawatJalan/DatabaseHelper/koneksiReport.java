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
public class koneksiReport {
    
    public static String PathReport = System.getProperty("user.dir")+ "/ian/RegisterRawatJalan/Ireport";
    public static Connection koneksi;
    public static Connection getConnection(){
        if(koneksi == null){
            try{
                String url = new String();
                String user = new String();
                String password = new String();
                url = "jdbc:mysql://localhost:3306/puskesmas";
                user="root";
                password="";
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                koneksi = DriverManager.getConnection(url,user,password);
             
            }catch(SQLException e){
                System.out.println("koneksi error");
            }
        }return koneksi;
    }
    
}
