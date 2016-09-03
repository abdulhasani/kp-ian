/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.frame.panel;

import ian.RegisterRawatJalan.DatabaseHelper.DatabaseHelper;
import Nciz.RegisterRawatJalan.Entity.DBDetailPeriksa;
import Nciz.RegisterRawatJalan.Entity.DBPembayaran;
import Nciz.RegisterRawatJalan.controller.PembayaranController;
import Nciz.RegisterRawatJalan.dao.DAOPembayaran;
import Nciz.RegisterRawatJalan.ex.DetailPeriksaException;
import Nciz.RegisterRawatJalan.ex.PembayaranException;
import Nciz.RegisterRawatJalan.frame.RegRwtJalan;
import Nciz.RegisterRawatJalan.listener.ListenerPembayaran;
import Nciz.RegisterRawatJalan.model.PembayaranModel;
import com.dbMIF.AutoNumber;
import com.stripbandunk.jwidget.model.DefaultPaginationModel;
import com.stripbandunk.jwidget.model.DynamicTableModel;
import com.uiMIF.JTextEx;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;

/**
 *
 * @author Ncizh
 */
public class MenuPembayaran extends javax.swing.JPanel implements ListenerPembayaran {

    private RegRwtJalan regRwtJalan;
    private MenuUtaman menuUtaman;
    private DynamicTableModel<DBPembayaran> tabelModel;
    private boolean deteksiCari;
    private PembayaranController pembayaranController;
    private PembayaranModel pembayaranModel;
    private String oldCari;
    private List<DBDetailPeriksa> list;

    /**
     * Creates new form MenuPasien
     */
    public MenuPembayaran() {
        initComponents();
        resetNumber();
        pembayaranController = new PembayaranController();
        pembayaranModel = new PembayaranModel();
        pembayaranController.setPembayaranModel(pembayaranModel);
        pembayaranModel.setListenerPembayaran(this);
    }

    public void setRegRwtJalan(RegRwtJalan regRwtJalan) {
        this.regRwtJalan = regRwtJalan;
    }

    public void setMenuUtaman(MenuUtaman menuUtaman) {
        this.menuUtaman = menuUtaman;
    }

    public void reloadData() {
        new SwingWorker<List<DBPembayaran>, Object>() {
            DAOPembayaran pembayaran;

            @Override
            protected List<DBPembayaran> doInBackground() throws Exception {
                pembayaran = (DAOPembayaran) DatabaseHelper.getPembayaran();;
                List<DBPembayaran> all;
                Thread.sleep(150);
                all = pembayaran.getAll(0, 8);
                return all;
            }

            @Override
            protected void done() {
                try {
                    tabelModel = new DynamicTableModel<>(DBPembayaran.class);
                    tabelPembayaran.setShowGrid(true);
                    tabelPembayaran.setRowHeight(24);
                    tabelPembayaran.setDynamicModel(tabelModel);
                    Integer total;
                    total = new Long(pembayaran.getCount()).intValue();
                    DefaultPaginationModel defaultPaginationModel;
                    defaultPaginationModel = new DefaultPaginationModel(8, total);
                    pagePembayaran.setModel(defaultPaginationModel);
                    tabelModel.clear();
                    deteksiCari = false;
                    for (DBPembayaran pem : get()) {
                        tabelModel.add(pem);
                    }
                } catch (InterruptedException | ExecutionException | PembayaranException ex) {
                    Logger.getLogger(MenuPembayaran.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.execute();
    }

    public void cariData(final String cari) {
        new SwingWorker<List<DBPembayaran>, Object>() {
            DAOPembayaran pembayaran;

            @Override
            protected List<DBPembayaran> doInBackground() throws Exception {
                pembayaran = (DAOPembayaran) DatabaseHelper.getPembayaran();;
                List<DBPembayaran> all;
                Thread.sleep(150);
                all = pembayaran.getCari(cari, 0, 8);
                return all;
            }

            @Override
            protected void done() {
                try {
                    if (get().isEmpty()) {
                        regRwtJalan.getMessageComponent1().show("Data tidak ditemukan", Color.darkGray, Color.red);
                    } else {
                        tabelModel = new DynamicTableModel<>(DBPembayaran.class);
                        tabelPembayaran.setShowGrid(true);
                        tabelPembayaran.setRowHeight(24);
                        tabelPembayaran.setDynamicModel(tabelModel);
                        Integer total;
                        total = new Long(pembayaran.getCount(cari)).intValue();
                        DefaultPaginationModel defaultPaginationModel;
                        defaultPaginationModel = new DefaultPaginationModel(7, total);
                        pagePembayaran.setModel(defaultPaginationModel);
                        tabelModel.clear();
                        oldCari = cari;
                        deteksiCari = true;
                        for (DBPembayaran pem : get()) {
                            tabelModel.add(pem);
                        }
                    }
                } catch (InterruptedException | ExecutionException | PembayaranException ex) {
                    Logger.getLogger(MenuPembayaran.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.execute();
    }

    public JTextEx getTxtBayarObat() {
        return txtBayarObat;
    }

    public void setTxtBayarObat(JTextEx txtBayarObat) {
        this.txtBayarObat = txtBayarObat;
    }

    public JTextEx getTxtBayarPeriksa() {
        return txtBayarPeriksa;
    }

    public void setTxtBayarPeriksa(JTextEx txtBayarPeriksa) {
        this.txtBayarPeriksa = txtBayarPeriksa;
    }

    public JTextEx getTxtIdPembayaran() {
        return txtIdPembayaran;
    }

    public void setTxtIdPembayaran(JTextEx txtIdPembayaran) {
        this.txtIdPembayaran = txtIdPembayaran;
    }

    public JTextEx getTxtIdPeriksa() {
        return txtIdPeriksa;
    }

    public void setTxtIdPeriksa(JTextEx txtIdPeriksa) {
        this.txtIdPeriksa = txtIdPeriksa;
    }

    public JTextEx getTxtTotal() {
        return txtTotal;
    }

    public void setTxtTotal(JTextEx txtTotal) {
        this.txtTotal = txtTotal;
    }

    public RegRwtJalan getRegRwtJalan() {
        return regRwtJalan;
    }

    private void resetNumber() {
        String autoNumberPembayaran = DatabaseHelper.getAutoNumberPembayaran(new AutoNumber());
        txtIdPembayaran.setText(autoNumberPembayaran);
    }

    public List<DBDetailPeriksa> getList() {
        return list;
    }

    private void hitung(Integer kodePeriksa) {
        try {
            List<DBDetailPeriksa> kode = DatabaseHelper.getPeriksa().getKode(kodePeriksa);
            list = kode;
            if (kode.isEmpty()) {
                regRwtJalan.getMessageComponent1().show("ID Periksa tidak terdatar", Color.darkGray, Color.red);
            } else {
                List<Double> listHargaObat = new ArrayList<>();
                for (DBDetailPeriksa d : kode) {
                    Double totalObat = d.getObatPerBijij() * d.getObat().getHarga_satuan();
                    listHargaObat.add(totalObat);
                }
                Double totObat = 0.0;
                Double pembayaranTotal;
                for (Double d : listHargaObat) {
                    totObat += d;
                }
                pembayaranTotal = totObat + kode.get(0).getDokter().getBiaya();
                txtBayarObat.setText(totObat.toString());
                txtBayarPeriksa.setText(kode.get(0).getDokter().getBiaya().toString());
                txtTotal.setText(pembayaranTotal.toString());
            }
        } catch (DetailPeriksaException ex) {
            Logger.getLogger(MenuPembayaran.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jToolBar1 = new javax.swing.JToolBar();
        renderMenuUtama1 = new Nciz.RegisterRawatJalan.frame.panel.RenderMenuUtama();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pagePembayaran = new com.stripbandunk.jwidget.JPagination();
        jPanel2 = new javax.swing.JPanel();
        btnSimpan = new Nciz.RegisterRawatJalan.frame.panel.RenderSimpan();
        btnSegarkan = new Nciz.RegisterRawatJalan.frame.panel.RenderSegarkan();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelPembayaran = new com.stripbandunk.jwidget.JDynamicTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        cariPembayaran = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtIdPeriksa = new com.uiMIF.JTextEx();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtBayarObat = new com.uiMIF.JTextEx();
        jLabel3 = new javax.swing.JLabel();
        txtBayarPeriksa = new com.uiMIF.JTextEx();
        txtIdPembayaran = new com.uiMIF.JTextEx();
        jLabel9 = new javax.swing.JLabel();
        txtTotal = new com.uiMIF.JTextEx();

        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        jToolBar1.setRollover(true);
        jToolBar1.setOpaque(false);

        renderMenuUtama1.setForeground(new java.awt.Color(255, 255, 255));
        renderMenuUtama1.setText("MENU UTAMA");
        renderMenuUtama1.setFocusable(false);
        renderMenuUtama1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        renderMenuUtama1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        renderMenuUtama1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                renderMenuUtama1ActionPerformed(evt);
            }
        });
        jToolBar1.add(renderMenuUtama1);

        add(jToolBar1, java.awt.BorderLayout.PAGE_START);

        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Puskesmas Winduaji Pembayaran");

        pagePembayaran.setOpaque(false);
        pagePembayaran.addPaginationListener(new com.stripbandunk.jwidget.listener.PaginationListener() {
            public void onPageChange(com.stripbandunk.jwidget.event.PaginationEvent evt) {
                pagePembayaranOnPageChange(evt);
            }
        });

        jPanel2.setOpaque(false);

        btnSimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnSimpan.setText("SIMPAN");
        btnSimpan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        jPanel2.add(btnSimpan);

        btnSegarkan.setForeground(new java.awt.Color(255, 255, 255));
        btnSegarkan.setText("SEGARKAN");
        btnSegarkan.setFocusable(false);
        btnSegarkan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSegarkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSegarkanActionPerformed(evt);
            }
        });
        jPanel2.add(btnSegarkan);

        jPanel4.setOpaque(false);

        jPanel3.setOpaque(false);

        jScrollPane1.setViewportView(tabelPembayaran);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 592, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 246, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanel4.add(jPanel3);

        jPanel6.setOpaque(false);

        jPanel5.setOpaque(false);

        cariPembayaran.setBackground(new java.awt.Color(255, 153, 0));
        cariPembayaran.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        cariPembayaran.setForeground(new java.awt.Color(255, 255, 255));
        cariPembayaran.setOpaque(false);
        cariPembayaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cariPembayaranKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Pencarian :");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 404, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(cariPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 41, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(8, 8, 8)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cariPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel6.add(jPanel5);

        jPanel8.setOpaque(false);

        jPanel7.setOpaque(false);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Total");

        txtIdPeriksa.setBackground(new java.awt.Color(255, 153, 0));
        txtIdPeriksa.setForeground(new java.awt.Color(255, 255, 255));
        txtIdPeriksa.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtIdPeriksa.setInputType(com.uiMIF.JTextEx.TypeText.Number);
        txtIdPeriksa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIdPeriksaKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("ID Periksa");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Bayar Obat");

        txtBayarObat.setEditable(false);
        txtBayarObat.setBackground(new java.awt.Color(255, 153, 0));
        txtBayarObat.setForeground(new java.awt.Color(255, 255, 255));
        txtBayarObat.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtBayarObat.setInputType(com.uiMIF.JTextEx.TypeText.Number);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("ID Pembayaran");

        txtBayarPeriksa.setEditable(false);
        txtBayarPeriksa.setBackground(new java.awt.Color(255, 153, 0));
        txtBayarPeriksa.setForeground(new java.awt.Color(255, 255, 255));
        txtBayarPeriksa.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtBayarPeriksa.setInputType(com.uiMIF.JTextEx.TypeText.Number);

        txtIdPembayaran.setEditable(false);
        txtIdPembayaran.setBackground(new java.awt.Color(255, 153, 0));
        txtIdPembayaran.setForeground(new java.awt.Color(255, 255, 255));
        txtIdPembayaran.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtIdPembayaran.setInputType(com.uiMIF.JTextEx.TypeText.Number);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Bayar Periksa");

        txtTotal.setEditable(false);
        txtTotal.setBackground(new java.awt.Color(255, 153, 0));
        txtTotal.setForeground(new java.awt.Color(255, 255, 255));
        txtTotal.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtTotal.setInputType(com.uiMIF.JTextEx.TypeText.Number);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 716, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtIdPeriksa, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtIdPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBayarObat, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBayarPeriksa, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel3, jLabel6});

        jPanel7Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtBayarPeriksa, txtTotal});

        jPanel7Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel7, jLabel9});

        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(txtIdPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(txtIdPeriksa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtBayarObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtBayarPeriksa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel8.add(jPanel7);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pagePembayaran, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 949, Short.MAX_VALUE))
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pagePembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void renderMenuUtama1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_renderMenuUtama1ActionPerformed
        // TODO add your handling code here:
        menuUtaman.backMeuUtama();
    }//GEN-LAST:event_renderMenuUtama1ActionPerformed

    private void txtIdPeriksaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdPeriksaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            String periksa = txtIdPeriksa.getText().trim();
            if (periksa.isEmpty()) {
                regRwtJalan.getMessageComponent1().show("Isi ID Periksa", Color.darkGray, Color.red);
            } else {
                try {
                    DBPembayaran kodePeriksa;
                    kodePeriksa = DatabaseHelper.getPembayaran().getkodePeriksa(Integer.parseInt(periksa));
                    if (kodePeriksa instanceof DBPembayaran) {
                        regRwtJalan.getMessageComponent1().show("ID Periksa tersebut sudah melakukan transaksi pembayaran", Color.darkGray, Color.red);
                    } else {
                        hitung(Integer.parseInt(periksa));
                    }
                } catch (PembayaranException ex) {
                    Logger.getLogger(MenuPembayaran.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_txtIdPeriksaKeyPressed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        pembayaranController.insert(this);
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnSegarkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSegarkanActionPerformed
        // TODO add your handling code here:
        reloadData();
        resetNumber();
        txtBayarObat.setText("");
        txtBayarPeriksa.setText("");
        txtIdPeriksa.setText("");
        txtTotal.setText("");
        cariPembayaran.setText("");
    }//GEN-LAST:event_btnSegarkanActionPerformed

    private void pagePembayaranOnPageChange(com.stripbandunk.jwidget.event.PaginationEvent evt) {//GEN-FIRST:event_pagePembayaranOnPageChange
        // TODO add your handling code here:
        int skip;
        int max;
        skip = ((evt.getCurrentPage() - 1) * evt.getPageSize());
        max = evt.getPageSize();
        if (deteksiCari == false) {
            try {
                List<DBPembayaran> all;
                all = DatabaseHelper.getPembayaran().getAll(skip, max);
                tabelModel.clear();
                for (DBPembayaran db : all) {
                    tabelModel.add(db);
                }
            } catch (PembayaranException ex) {
                Logger.getLogger(MenuPembayaran.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            try {
                List<DBPembayaran> all;
                all = DatabaseHelper.getPembayaran().getCari(oldCari, skip, max);
                tabelModel.clear();
                for (DBPembayaran db : all) {
                    tabelModel.add(db);
                }
            } catch (PembayaranException ex) {
                Logger.getLogger(MenuPembayaran.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_pagePembayaranOnPageChange

    private void cariPembayaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariPembayaranKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            if (cariPembayaran.getText().trim().isEmpty()) {
                regRwtJalan.getMessageComponent1().show("Isi kolom pencarian", Color.darkGray, Color.red);
            } else {
                cariData(cariPembayaran.getText().trim());
            }
        }
    }//GEN-LAST:event_cariPembayaranKeyPressed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Nciz.RegisterRawatJalan.frame.panel.RenderSegarkan btnSegarkan;
    private Nciz.RegisterRawatJalan.frame.panel.RenderSimpan btnSimpan;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField cariPembayaran;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private com.stripbandunk.jwidget.JPagination pagePembayaran;
    private Nciz.RegisterRawatJalan.frame.panel.RenderMenuUtama renderMenuUtama1;
    private com.stripbandunk.jwidget.JDynamicTable tabelPembayaran;
    private com.uiMIF.JTextEx txtBayarObat;
    private com.uiMIF.JTextEx txtBayarPeriksa;
    private com.uiMIF.JTextEx txtIdPembayaran;
    private com.uiMIF.JTextEx txtIdPeriksa;
    private com.uiMIF.JTextEx txtTotal;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onInsert(DBPembayaran pembayaran) {
        resetNumber();
        txtIdPeriksa.setText("0");
        txtBayarObat.setText("");
        txtBayarPeriksa.setText("0");
        txtTotal.setText("0");
        regRwtJalan.getMessageComponent1().show("Sukses menambah data", Color.darkGray, Color.GREEN);
    }

    @Override
    public void onUpdate(DBPembayaran pembayaran) {
    }

    @Override
    public void onDetele(Integer kode) {
    }
}
