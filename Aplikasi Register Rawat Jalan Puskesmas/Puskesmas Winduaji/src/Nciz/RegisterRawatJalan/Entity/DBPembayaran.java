/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.Entity;

import Nciz.RegisterRawatJalan.render.RenderTabelPembayaran;
import com.stripbandunk.jwidget.annotation.TableColumn;

/**
 *
 * @author Ncizh
 */
public class DBPembayaran {

    private Integer idpembayaran;
    @TableColumn(number = 1, name = "ID PERIKSA",renderer=RenderTabelPembayaran.class)
    private DBPeriksa periksa;
    @TableColumn(number = 2, name = "BAYAR OBAT",size=20)
    private Double bayar_obat;
    @TableColumn(number = 3, name = "BAYAR PERIKSA",size=19)
    private Double bayar_periksa;
    @TableColumn(number = 4, name = "TOTAL",size=25)
    private Double total;

    public Double getBayar_obat() {
        return bayar_obat;
    }

    public void setBayar_obat(Double bayar_obat) {
        this.bayar_obat = bayar_obat;
    }

    public Double getBayar_periksa() {
        return bayar_periksa;
    }

    public void setBayar_periksa(Double bayar_periksa) {
        this.bayar_periksa = bayar_periksa;
    }

    public Integer getIdpembayaran() {
        return idpembayaran;
    }

    public void setIdpembayaran(Integer idpembayaran) {
        this.idpembayaran = idpembayaran;
    }

    public DBPeriksa getPeriksa() {
        return periksa;
    }

    public void setPeriksa(DBPeriksa periksa) {
        this.periksa = periksa;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

   
}
