/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Ian.RegisterRawatJalan.DatabaseHelper.Testing;

import ian.RegisterRawatJalan.DatabaseHelper.DatabaseHelper;
import Nciz.RegisterRawatJalan.Entity.DBDetailPeriksa;
import Nciz.RegisterRawatJalan.ex.DetailPeriksaException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ncizh
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            DBDetailPeriksa idPeriksa = DatabaseHelper.getPeriksa().getIdPeriksa(1);
            System.out.println(idPeriksa.getDiagnosa());
        } catch (DetailPeriksaException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
