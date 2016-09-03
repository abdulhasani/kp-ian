/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.frame.panel;

import ian.RegisterRawatJalan.DatabaseHelper.DatabaseHelper;
import Nciz.RegisterRawatJalan.Entity.*;
import Nciz.RegisterRawatJalan.controller.PeriksaController;
import Nciz.RegisterRawatJalan.dao.DAODokter;
import Nciz.RegisterRawatJalan.dao.DAOObat;
import Nciz.RegisterRawatJalan.dao.DAOPeriksa;
import Nciz.RegisterRawatJalan.ex.DetailPeriksaException;
import Nciz.RegisterRawatJalan.ex.ObatException;
import Nciz.RegisterRawatJalan.ex.PeriksaException;
import Nciz.RegisterRawatJalan.frame.RegRwtJalan;
import Nciz.RegisterRawatJalan.frame.dialog.RekamMedis;
import Nciz.RegisterRawatJalan.listener.ListenerPeriksa;
import Nciz.RegisterRawatJalan.model.PeriksaModel;
import Nciz.RegisterRawatJalan.render.EditorTabelResepDokter;
import Nciz.RegisterRawatJalan.render.RenderCXombo;
import com.dbMIF.AutoNumber;
import com.stripbandunk.jwidget.model.DefaultPaginationModel;
import com.stripbandunk.jwidget.model.DynamicTableModel;
import com.toedter.calendar.JDateChooser;
import com.uiMIF.JTextEx;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Ncizh
 */
public class MenuPeriksa extends javax.swing.JPanel implements ListenerPeriksa,
        ListSelectionListener {

    private RegRwtJalan regRwtJalan;
    private MenuUtaman menuUtaman;
    private DynamicTableModel<DBObat> tabelModelObat;
    private DynamicTableModel<DBPeriksa> tabelModelPeriksa;
    private DynamicTableModel<DBResepDokter> tabelModelResepDokter;
    private Boolean deteksiCariObat;
    private Boolean deteksiCariPeriksa;
    private DefaultComboBoxModel<DBDokter> comboBoxModel;
    private PeriksaModel PeriksaModel;
    private PeriksaController periksaController;
    private Long detailId;
    private String oldCariObat;
    private String oldCariPeriksa;
    private Date oldCariTglPeriksa;
    private Date oldTglPeriksaSelected;

    /**
     * Creates new form MenuPasien
     */
    public MenuPeriksa() {
        initComponents();
        resetIdPeriksa();
        periksaController = new PeriksaController();
        PeriksaModel = new PeriksaModel();
        periksaController.setPeriksaModel(PeriksaModel);
        PeriksaModel.setListenerPeriksa(this);
        txtTanggalPeriksa.setDate(new Date());
        tabelModelResepDokter = new DynamicTableModel<>(DBResepDokter.class);
        tabelResepObat.setDynamicModel(tabelModelResepDokter);
        tabelResepObat.setShowGrid(true);
        tabelResepObat.setRowHeight(24);
        tabelPeriksa.getSelectionModel().addListSelectionListener(this);
    }

    public Long getDetailId() {
        return detailId;
    }

    public RegRwtJalan getRegRwtJalan() {
        return regRwtJalan;
    }

    public void setRegRwtJalan(RegRwtJalan regRwtJalan) {
        this.regRwtJalan = regRwtJalan;
    }

    public void setMenuUtaman(MenuUtaman menuUtaman) {
        this.menuUtaman = menuUtaman;
    }

    public JComboBox getComboDokter() {
        return comboDokter;
    }

    public JTextEx getTxtAlergiObat() {
        return txtAlergiObat;
    }

    public JTextEx getTxtDiagnosa() {
        return txtDiagnosa;
    }

    public JTextEx getTxtIdPasien() {
        return txtIdPasien;
    }

    public JTextEx getTxtIdPeriksa() {
        return txtIdPeriksa;
    }

    public JDateChooser getTxtTanggalPeriksa() {
        return txtTanggalPeriksa;
    }

    public DynamicTableModel<DBResepDokter> getTabelModelResepDokter() {
        return tabelModelResepDokter;
    }

    public void reloadDokter() {
        new SwingWorker<List<DBDokter>, Object>() {
            DAODokter dokter;

            @Override
            protected List<DBDokter> doInBackground() throws Exception {
                dokter = DatabaseHelper.getDokter();
                List<DBDokter> all;
                Thread.sleep(100);
                all = dokter.getAll();
                return all;
            }

            @Override
            protected void done() {
                try {
                    comboBoxModel = new DefaultComboBoxModel<>();
                    comboDokter.setModel(comboBoxModel);
                    comboBoxModel.removeAllElements();
                    for (DBDokter dk : get()) {
                        comboBoxModel.addElement(dk);
                    }
                    comboDokter.setRenderer(new RenderCXombo());
                } catch (InterruptedException | ExecutionException ex) {
                    Logger.getLogger(MenuPeriksa.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.execute();
    }

    public void reloadData() {
        new SwingWorker<List<DBObat>, Object>() {
            DAOObat obat;

            @Override
            protected List<DBObat> doInBackground() throws Exception {
                obat = DatabaseHelper.getObat();
                List<DBObat> all;
                Thread.sleep(200);
                all = obat.getAll(0, 6);
                return all;
            }

            @Override
            protected void done() {
                try {
                    tabelObat.setShowGrid(true);
                    tabelObat.setRowHeight(24);
                    tabelModelObat = new DynamicTableModel<>(DBObat.class);
                    tabelObat.setDynamicModel(tabelModelObat);
                    Integer total;
                    DefaultPaginationModel defaultPaginationModel;
                    total = new Long(obat.getCount()).intValue();
                    defaultPaginationModel = new DefaultPaginationModel(6, total);
                    pageDinamis.setModel(defaultPaginationModel);
                    tabelModelObat.clear();
                    deteksiCariObat = false;
                    for (DBObat db : get()) {
                        tabelModelObat.add(db);
                    }
                } catch (InterruptedException | ExecutionException | ObatException ex) {
                    Logger.getLogger(MenuObat.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.execute();

    }

    public void reloadDataPeriksa() {
        new SwingWorker<List<DBPeriksa>, Object>() {
            DAOPeriksa periksa;

            @Override
            protected List<DBPeriksa> doInBackground() throws Exception {
                periksa = DatabaseHelper.getPeriksaUtama();
                List<DBPeriksa> all;
                Thread.sleep(350);
                all = periksa.getAll(0, 6);
                return all;
            }

            @Override
            protected void done() {
                try {
                    tabelPeriksa.setShowGrid(true);
                    tabelPeriksa.setRowHeight(24);
                    tabelModelPeriksa = new DynamicTableModel<>(DBPeriksa.class);
                    tabelPeriksa.setDynamicModel(tabelModelPeriksa);
                    Integer total;
                    total = new Long(periksa.getCount()).intValue();
                    DefaultPaginationModel defaultPaginationModel;
                    defaultPaginationModel = new DefaultPaginationModel(6, total);
                    pagePeriksa.setModel(defaultPaginationModel);
                    tabelModelPeriksa.clear();
                    deteksiCariPeriksa = false;
                    try {
                        for (DBPeriksa db : get()) {
                            tabelModelPeriksa.add(db);
                        }
                    } catch (InterruptedException | ExecutionException ex) {
                        Logger.getLogger(MenuPeriksa.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (PeriksaException ex) {
                    Logger.getLogger(MenuPeriksa.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.execute();
    }

    private void cariDataObat(final String cari) {
        new SwingWorker<List<DBObat>, Object>() {
            DAOObat obat;

            @Override
            protected List<DBObat> doInBackground() throws Exception {
                obat = DatabaseHelper.getObat();
                List<DBObat> all;
                Thread.sleep(250);
                all = obat.getCari(cari, 0, 6);
                return all;
            }

            @Override
            protected void done() {
                try {
                    if (get().isEmpty()) {
                        regRwtJalan.getMessageComponent1().
                                show("Data tidak ditemukan", Color.darkGray, Color.RED);
                        txtCari.setText("");
                    } else {

                        tabelModelObat = new DynamicTableModel<>(DBObat.class);
                        tabelObat.setDynamicModel(tabelModelObat);
                        Integer total;
                        DefaultPaginationModel defaultPaginationModel;
                        total = new Long(obat.getCount(cari)).intValue();
                        defaultPaginationModel = new DefaultPaginationModel(6, total);
                        pageDinamis.setModel(defaultPaginationModel);
                        tabelModelObat.clear();
                        oldCariObat = cari;
                        deteksiCariObat = true;
                        for (DBObat db : get()) {
                            tabelModelObat.add(db);
                        }
                    }
                } catch (InterruptedException | ExecutionException | ObatException ex) {
                    Logger.getLogger(MenuObat.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.execute();
    }

    public void CariDataPeriksa(final String cari, final Date tgl) {
        new SwingWorker<List<DBPeriksa>, Object>() {
            DAOPeriksa Periksa;

            @Override
            protected List<DBPeriksa> doInBackground() throws Exception {
                Periksa = DatabaseHelper.getPeriksaUtama();
                List<DBPeriksa> all;
                Thread.sleep(250);
                all = Periksa.getAll(cari, tgl, 0, 6);
                return all;
            }

            @Override
            protected void done() {
                try {
                    if (get().isEmpty()) {
                        regRwtJalan.getMessageComponent1().
                                show("Data tidak ditemukan", Color.darkGray, Color.RED);
                        txtCari1.setText("");
                        txtTglCariPeriksa.setDate(null);
                    } else {
                        tabelModelPeriksa = new DynamicTableModel<>(DBPeriksa.class);
                        tabelPeriksa.setDynamicModel(tabelModelPeriksa);
                        Integer total;
                        DefaultPaginationModel defaultPaginationModel;
                        total = new Long(Periksa.getCount(cari, tgl)).intValue();
                        defaultPaginationModel = new DefaultPaginationModel(6, total);
                        pagePeriksa.setModel(defaultPaginationModel);
                        tabelModelPeriksa.clear();
                        deteksiCariPeriksa = true;
                        oldCariPeriksa = cari;
                        oldCariTglPeriksa = tgl;
                        for (DBPeriksa db : get()) {
                            tabelModelPeriksa.add(db);
                        }
                    }
                } catch (InterruptedException | ExecutionException | PeriksaException ex) {
                    Logger.getLogger(MenuPeriksa.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }.execute();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinner1 = new javax.swing.JSpinner();
        jToolBar1 = new javax.swing.JToolBar();
        renderMenuUtama1 = new Nciz.RegisterRawatJalan.frame.panel.RenderMenuUtama();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtIdPeriksa = new com.uiMIF.JTextEx();
        jLabel4 = new javax.swing.JLabel();
        txtIdPasien = new com.uiMIF.JTextEx();
        jLabel5 = new javax.swing.JLabel();
        txtTanggalPeriksa = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnSimpan = new Nciz.RegisterRawatJalan.frame.panel.RenderSimpan();
        btnUbah = new Nciz.RegisterRawatJalan.frame.panel.RenderUbah();
        btnHapus = new Nciz.RegisterRawatJalan.frame.panel.RenderHapus();
        renderSegarkan1 = new Nciz.RegisterRawatJalan.frame.panel.RenderSegarkan();
        btnViewDetail = new Nciz.RegisterRawatJalan.frame.panel.RenderViewDetail();
        comboDokter = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        txtDiagnosa = new com.uiMIF.JTextEx();
        txtAlergiObat = new com.uiMIF.JTextEx();
        lblObtatPerItem = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnTambahObat = new Nciz.RegisterRawatJalan.frame.panel.RenderTambahObat();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelObat = new com.stripbandunk.jwidget.JDynamicTable();
        pageDinamis = new com.stripbandunk.jwidget.JPagination();
        txtCari = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelPeriksa = new com.stripbandunk.jwidget.JDynamicTable();
        pagePeriksa = new com.stripbandunk.jwidget.JPagination();
        txtCari1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTglCariPeriksa = new com.toedter.calendar.JDateChooser();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelResepObat = new com.stripbandunk.jwidget.JDynamicTable();
        renderBatalSalahSatuObat1 = new Nciz.RegisterRawatJalan.frame.panel.RenderBatalSalahSatuObat();
        renderBatalSalahSatuObat2 = new Nciz.RegisterRawatJalan.frame.panel.RenderBatalSalahSatuObat();

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));
        jSpinner1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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
        jLabel1.setText("Puskesmas Winduaji Periksa");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("ID Periksa");

        txtIdPeriksa.setEditable(false);
        txtIdPeriksa.setBackground(new java.awt.Color(255, 153, 0));
        txtIdPeriksa.setForeground(new java.awt.Color(255, 255, 255));
        txtIdPeriksa.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("ID Pasien");

        txtIdPasien.setBackground(new java.awt.Color(255, 153, 0));
        txtIdPasien.setForeground(new java.awt.Color(255, 255, 255));
        txtIdPasien.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtIdPasien.setInputType(com.uiMIF.JTextEx.TypeText.Number);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Tanggal Periksa");

        txtTanggalPeriksa.setDateFormatString("dd MMMM yyy");
        txtTanggalPeriksa.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Dokter");

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        btnSimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnSimpan.setText("SIMPAN");
        btnSimpan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        jPanel2.add(btnSimpan);

        btnUbah.setForeground(new java.awt.Color(255, 255, 255));
        btnUbah.setText("UBAH");
        btnUbah.setEnabled(false);
        btnUbah.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });
        jPanel2.add(btnUbah);

        btnHapus.setForeground(new java.awt.Color(255, 255, 255));
        btnHapus.setText("HAPUS");
        btnHapus.setEnabled(false);
        btnHapus.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        jPanel2.add(btnHapus);

        renderSegarkan1.setForeground(new java.awt.Color(255, 255, 255));
        renderSegarkan1.setText("SEGARKAN");
        renderSegarkan1.setFocusable(false);
        renderSegarkan1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        renderSegarkan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                renderSegarkan1ActionPerformed(evt);
            }
        });
        jPanel2.add(renderSegarkan1);

        btnViewDetail.setForeground(new java.awt.Color(255, 255, 255));
        btnViewDetail.setToolTipText("Detail Riwayat");
        btnViewDetail.setEnabled(false);
        btnViewDetail.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnViewDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewDetailActionPerformed(evt);
            }
        });
        jPanel2.add(btnViewDetail);
        btnViewDetail.getAccessibleContext().setAccessibleDescription("Detail Riwayat");

        comboDokter.setBackground(new java.awt.Color(255, 153, 0));
        comboDokter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Diagnosa");

        txtDiagnosa.setBackground(new java.awt.Color(255, 153, 0));
        txtDiagnosa.setForeground(new java.awt.Color(255, 255, 255));
        txtDiagnosa.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N

        txtAlergiObat.setBackground(new java.awt.Color(255, 153, 0));
        txtAlergiObat.setForeground(new java.awt.Color(255, 255, 255));
        txtAlergiObat.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N

        lblObtatPerItem.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblObtatPerItem.setForeground(new java.awt.Color(255, 255, 255));
        lblObtatPerItem.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblObtatPerItem.setText("Tambahkan Resep");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Alergi Obat");

        btnTambahObat.setToolTipText("Sarankan Obat");
        btnTambahObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahObatActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tabel Obat", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 11), java.awt.Color.white)); // NOI18N
        jPanel3.setOpaque(false);

        jScrollPane1.setViewportView(tabelObat);

        pageDinamis.setOpaque(false);
        pageDinamis.addPaginationListener(new com.stripbandunk.jwidget.listener.PaginationListener() {
            public void onPageChange(com.stripbandunk.jwidget.event.PaginationEvent evt) {
                pageDinamisOnPageChange(evt);
            }
        });

        txtCari.setBackground(new java.awt.Color(255, 153, 0));
        txtCari.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtCari.setForeground(new java.awt.Color(255, 255, 255));
        txtCari.setOpaque(false);
        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCariKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Pencarian :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(pageDinamis, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pageDinamis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tabel Periksa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10), java.awt.Color.white)); // NOI18N
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel4.setOpaque(false);

        jScrollPane2.setViewportView(tabelPeriksa);

        pagePeriksa.setOpaque(false);
        pagePeriksa.addPaginationListener(new com.stripbandunk.jwidget.listener.PaginationListener() {
            public void onPageChange(com.stripbandunk.jwidget.event.PaginationEvent evt) {
                pagePeriksaOnPageChange(evt);
            }
        });

        txtCari1.setBackground(new java.awt.Color(255, 153, 0));
        txtCari1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtCari1.setForeground(new java.awt.Color(255, 255, 255));
        txtCari1.setOpaque(false);
        txtCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCari1KeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Pencarian :");

        txtTglCariPeriksa.setDateFormatString("dd MMMM yyyy");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pagePeriksa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTglCariPeriksa, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCari1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTglCariPeriksa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCari1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(pagePeriksa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resep Dokter", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), java.awt.Color.white)); // NOI18N
        jPanel5.setOpaque(false);

        tabelResepObat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tabelResepObat);

        renderBatalSalahSatuObat1.setForeground(new java.awt.Color(255, 255, 255));
        renderBatalSalahSatuObat1.setText("Batalkan Semua");
        renderBatalSalahSatuObat1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        renderBatalSalahSatuObat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                renderBatalSalahSatuObat1ActionPerformed(evt);
            }
        });

        renderBatalSalahSatuObat2.setForeground(new java.awt.Color(255, 255, 255));
        renderBatalSalahSatuObat2.setText("Batalkan");
        renderBatalSalahSatuObat2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        renderBatalSalahSatuObat2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                renderBatalSalahSatuObat2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(renderBatalSalahSatuObat2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(renderBatalSalahSatuObat1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(renderBatalSalahSatuObat1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(renderBatalSalahSatuObat2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdPeriksa, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTanggalPeriksa, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel7)
                            .addComponent(lblObtatPerItem))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDiagnosa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAlergiObat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTambahObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 739, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(156, 156, 156))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {comboDokter, txtIdPasien, txtIdPeriksa, txtTanggalPeriksa});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel7, lblObtatPerItem});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtAlergiObat, txtDiagnosa});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel3, jLabel4, jLabel5, jLabel8});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtIdPeriksa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel5))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(txtTanggalPeriksa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(3, 3, 3)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtIdPasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(comboDokter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtDiagnosa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtAlergiObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnTambahObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblObtatPerItem))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 94, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void renderMenuUtama1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_renderMenuUtama1ActionPerformed
        // TODO add your handling code here:
        menuUtaman.backMeuUtama();
    }//GEN-LAST:event_renderMenuUtama1ActionPerformed

    private void btnTambahObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahObatActionPerformed
        // TODO add your handling code here:
        if (tabelObat.getSelectedRowCount() > 0) {
            int[] selectedRows = tabelObat.getSelectedRows();
            DBResepDokter resepDokter;
            List<DBResepDokter> data = tabelModelResepDokter.getData();
            tabelResepObat.getColumnModel().getColumn(1).setCellEditor(new EditorTabelResepDokter(jSpinner1));
            if (data.isEmpty()) {
                for (int obat : selectedRows) {
                    resepDokter = new DBResepDokter();
                    DBObat get = tabelModelObat.get(obat);
                    resepDokter.setObat(get);
                    resepDokter.setJmlahItem(0);
                    tabelModelResepDokter.add(resepDokter);
                }
            } else {
                Boolean deteksiObat = true;
                for (DBResepDokter resep : data) {
                    for (int obat : selectedRows) {
                        DBObat get = tabelModelObat.get(obat);
                        if (resep.getObat().equals(get)) {
                            regRwtJalan.getMessageComponent1().show("Obat " + get.getNama_obat() + " sudah ditambahkan dalam resep",
                                    Color.darkGray, Color.RED);
                            deteksiObat = false;
                            break;
                        }
                    }
                }
                if (deteksiObat == true) {
                    for (int obat : selectedRows) {
                        resepDokter = new DBResepDokter();
                        DBObat get = tabelModelObat.get(obat);
                        resepDokter.setObat(get);
                        resepDokter.setJmlahItem(0);
                        tabelModelResepDokter.add(resepDokter);
                    }
                }
            }

        } else {
            regRwtJalan.getMessageComponent1().show("Seleksi obat dan tentukan obat per item",
                    Color.darkGray, Color.RED);
        }
    }//GEN-LAST:event_btnTambahObatActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        List<DBResepDokter> data = tabelModelResepDokter.getData();
        Boolean noZeroJmlItem = true;
        Boolean deteksiStok = true;
        String namaObatMinStok = null;
        for (DBResepDokter rsDokter : data) {
            if (rsDokter.getJmlahItem() == 0) {
                noZeroJmlItem = false;
                break;
            }
        }
        for (DBResepDokter resepDokter : data) {
            if (resepDokter.getObat().getSisa_stok() < resepDokter.getJmlahItem()) {
                deteksiStok = false;
                namaObatMinStok = resepDokter.getObat().getNama_obat();
            }
        }
        if (noZeroJmlItem == true) {
            if (deteksiStok == true) {
                periksaController.insert(this);
            } else {
                regRwtJalan.getMessageComponent1().show("Obat  " + namaObatMinStok + "  stok tidak mencukupi",
                        Color.darkGray, Color.RED);
            }
        } else {
            regRwtJalan.getMessageComponent1().show("Silahkan cekking jumlah item pada resep dokter",
                    Color.darkGray, Color.RED);
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        // TODO add your handling code here:
        if (tabelPeriksa.getSelectedRowCount() == 1) {
            int selectedRow = tabelPeriksa.getSelectedRow();
            int convertRowIndexToModel = tabelPeriksa.convertRowIndexToModel(selectedRow);
            DBPeriksa get = tabelModelPeriksa.get(convertRowIndexToModel);
            try {

                if (get instanceof DBPeriksa) {
                    if (get.getNamaPasien().getPasien().getIdpasien() == Integer.parseInt(txtIdPasien.getText().trim())
                            && get.getTgl_periksa().equals(txtTanggalPeriksa.getDate())
                            && get.getDokter().getDokter().equals(comboDokter.getSelectedItem())
                            && get.getDiagnosa().getDiagnosa().equals(txtDiagnosa.getText().trim())
                            && get.getDiagnosa().getAlergiObat().equals(txtAlergiObat.getText().trim())) {
                        reloadData();
                        reloadDataPeriksa();
                        txtCari.setText("");
                        txtCari1.setText("");
                        txtTglCariPeriksa.setDate(null);
                        resetIdPeriksa();
                        txtTanggalPeriksa.setDate(new Date());
                        txtIdPasien.setText("");
                        comboDokter.setSelectedIndex(0);
                        txtDiagnosa.setText("");
                        txtAlergiObat.setText("");
                        oldTglPeriksaSelected = null;
                        btnViewDetail.setEnabled(false);
                        btnTambahObat.setEnabled(true);
                        btnSimpan.setEnabled(true);
                        btnUbah.setEnabled(false);
                        btnUbah.requestFocus(false);
                        btnHapus.setEnabled(false);
                    } else {
                        periksaController.update(this);
                    }
                }
            } catch (NumberFormatException ex) {
                txtIdPasien.requestFocus();
            }
        }
    }//GEN-LAST:event_btnUbahActionPerformed

    private void renderSegarkan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_renderSegarkan1ActionPerformed
        // TODO add your handling code here:
        reloadData();
        reloadDataPeriksa();
        txtCari.setText("");
        txtCari1.setText("");
        txtTglCariPeriksa.setDate(null);
        resetIdPeriksa();
        txtTanggalPeriksa.setDate(new Date());
        txtIdPasien.setText("");
        comboDokter.setSelectedIndex(0);
        txtDiagnosa.setText("");
        txtAlergiObat.setText("");
        oldTglPeriksaSelected = null;
        btnViewDetail.setEnabled(false);
        btnTambahObat.setEnabled(true);
        btnSimpan.setEnabled(true);
        btnUbah.setEnabled(false);
        btnHapus.setEnabled(false);
        tabelModelResepDokter.clear();
    }//GEN-LAST:event_renderSegarkan1ActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        periksaController.detele(Integer.parseInt(txtIdPeriksa.getText().trim()));
    }//GEN-LAST:event_btnHapusActionPerformed

    private void pageDinamisOnPageChange(com.stripbandunk.jwidget.event.PaginationEvent evt) {//GEN-FIRST:event_pageDinamisOnPageChange
        // TODO add your handling code here:
        int skip;
        int max;
        skip = (evt.getCurrentPage() - 1) * evt.getPageSize();
        max = evt.getPageSize();
        if (deteksiCariObat == false) {
            try {
                List<DBObat> all = DatabaseHelper.getObat().getAll(skip, max);
                tabelModelObat.clear();
                for (DBObat d : all) {
                    tabelModelObat.add(d);
                }
            } catch (ObatException ex) {
                Logger.getLogger(MenuPeriksa.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (deteksiCariObat == true) {
            System.out.println("2");
            try {
                List<DBObat> all;
                all = DatabaseHelper.getObat().getCari(oldCariObat, skip, max);
                tabelModelObat.clear();
                for (DBObat ob : all) {
                    tabelModelObat.add(ob);
                }
            } catch (ObatException ex) {
                Logger.getLogger(MenuObat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_pageDinamisOnPageChange

    private void txtCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            if (txtCari.getText().trim().isEmpty()) {
                regRwtJalan.getMessageComponent1().show("Isi kolom pencarian", Color.darkGray, Color.RED);
            } else {
                cariDataObat(txtCari.getText().trim());
            }
        }
    }//GEN-LAST:event_txtCariKeyPressed

    private void pagePeriksaOnPageChange(com.stripbandunk.jwidget.event.PaginationEvent evt) {//GEN-FIRST:event_pagePeriksaOnPageChange
        // TODO add your handling code here:
        int skip;
        int max;
        skip = (evt.getCurrentPage() - 1) * evt.getPageSize();
        max = evt.getPageSize();
        if (deteksiCariPeriksa == false) {
            try {
                List<DBPeriksa> all = DatabaseHelper.getPeriksaUtama().getAll(skip, max);
                tabelModelPeriksa.clear();
                for (DBPeriksa dbP : all) {
                    tabelModelPeriksa.add(dbP);
                }
            } catch (PeriksaException ex) {
                Logger.getLogger(MenuPeriksa.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                List<DBPeriksa> all = DatabaseHelper.getPeriksaUtama().getAll(oldCariPeriksa, oldCariTglPeriksa, skip, max);
                tabelModelPeriksa.clear();
                for (DBPeriksa db : all) {
                    tabelModelPeriksa.add(db);
                }
            } catch (PeriksaException ex) {
                Logger.getLogger(MenuPeriksa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_pagePeriksaOnPageChange

    private void txtCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCari1KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            System.out.println(txtTglCariPeriksa.getDate());
            if (txtCari1.getText().trim().isEmpty() || txtTglCariPeriksa.getDate() == null) {
                regRwtJalan.getMessageComponent1().show("Isi kolom pencarian dan tgl pencarian", Color.darkGray, Color.RED);
            } else {
                CariDataPeriksa(txtCari1.getText().trim(), txtTglCariPeriksa.getDate());
            }
        }
    }//GEN-LAST:event_txtCari1KeyPressed

    private void renderBatalSalahSatuObat2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_renderBatalSalahSatuObat2ActionPerformed
        // TODO add your handling code here:
        if (tabelResepObat.getSelectedRowCount() > 0) {
            if (tabelResepObat.getSelectedRowCount() == 1) {
                int selectedRow = tabelResepObat.getSelectedRow();
                int convertRowIndexToModel = tabelResepObat.convertRowIndexToModel(selectedRow);
                tabelModelResepDokter.remove(convertRowIndexToModel);
            } else {
                int[] selectedRows = tabelResepObat.getSelectedRows();
                for (int i = 0; i < selectedRows.length; i++) {
                    for (int j = 1; i < selectedRows.length; i++) {
                        int min = selectedRows[j - 1];
                        int max = selectedRows[j];
                        if (max >= min) {
                            selectedRows[j - 1] = max;
                            selectedRows[j] = min;
                        }
                    }
                }
                for (int i : selectedRows) {
                    tabelModelResepDokter.remove(i);
                }
            }
        } else {
            if (!tabelModelResepDokter.isEmpty()) {
                regRwtJalan.getMessageComponent1().show("Seleksi resep untuk membatalkan", Color.darkGray, Color.RED);
            }
        }
    }//GEN-LAST:event_renderBatalSalahSatuObat2ActionPerformed

    private void renderBatalSalahSatuObat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_renderBatalSalahSatuObat1ActionPerformed
        // TODO add your handling code here:
        tabelModelResepDokter.clear();
    }//GEN-LAST:event_renderBatalSalahSatuObat1ActionPerformed

    private void btnViewDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewDetailActionPerformed
        try {
            // TODO add your handling code here:
            List<DBDetailPeriksa> idPeriksaAll;
            idPeriksaAll = DatabaseHelper.getPeriksa().getIdPeriksaAll(Integer.parseInt(txtIdPeriksa.getText().trim()), oldTglPeriksaSelected);
            DbVirtualRiwayatDokter virtualRiwayatDokter;
            RekamMedis viewObat = new RekamMedis();
            for (DBDetailPeriksa d : idPeriksaAll) {
                virtualRiwayatDokter = new DbVirtualRiwayatDokter();
                virtualRiwayatDokter.setTglPeriksa(oldTglPeriksaSelected);
                virtualRiwayatDokter.setDiagnosa(d.getDiagnosa());
                virtualRiwayatDokter.setNamaObat(d);
                virtualRiwayatDokter.setJumlahItem(d.getObatPerBijij());
                viewObat.getDynamicTableModel().add(virtualRiwayatDokter);
            }
            viewObat.getLblIdPeriksa().setText(idPeriksaAll.get(0).getPeriksa().getIdperiksa().toString());
            viewObat.getLblNamaPasien().setText(idPeriksaAll.get(0).getPasien().getNama_pasien());
            viewObat.getlblUmur().setText(idPeriksaAll.get(0).getPasien().getUmur());
            viewObat.getlblAlamat().setText(idPeriksaAll.get(0).getPasien().getAlamat_pasien());
            viewObat.setSize(450, 380);
            viewObat.setLocationRelativeTo(this);
            viewObat.setVisible(true);
        } catch (DetailPeriksaException ex) {
            Logger.getLogger(MenuPeriksa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnViewDetailActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Nciz.RegisterRawatJalan.frame.panel.RenderHapus btnHapus;
    private Nciz.RegisterRawatJalan.frame.panel.RenderSimpan btnSimpan;
    private Nciz.RegisterRawatJalan.frame.panel.RenderTambahObat btnTambahObat;
    private Nciz.RegisterRawatJalan.frame.panel.RenderUbah btnUbah;
    private Nciz.RegisterRawatJalan.frame.panel.RenderViewDetail btnViewDetail;
    private javax.swing.JComboBox comboDokter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblObtatPerItem;
    private com.stripbandunk.jwidget.JPagination pageDinamis;
    private com.stripbandunk.jwidget.JPagination pagePeriksa;
    private Nciz.RegisterRawatJalan.frame.panel.RenderBatalSalahSatuObat renderBatalSalahSatuObat1;
    private Nciz.RegisterRawatJalan.frame.panel.RenderBatalSalahSatuObat renderBatalSalahSatuObat2;
    private Nciz.RegisterRawatJalan.frame.panel.RenderMenuUtama renderMenuUtama1;
    private Nciz.RegisterRawatJalan.frame.panel.RenderSegarkan renderSegarkan1;
    private com.stripbandunk.jwidget.JDynamicTable tabelObat;
    private com.stripbandunk.jwidget.JDynamicTable tabelPeriksa;
    private com.stripbandunk.jwidget.JDynamicTable tabelResepObat;
    private com.uiMIF.JTextEx txtAlergiObat;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtCari1;
    private com.uiMIF.JTextEx txtDiagnosa;
    private com.uiMIF.JTextEx txtIdPasien;
    private com.uiMIF.JTextEx txtIdPeriksa;
    private com.toedter.calendar.JDateChooser txtTanggalPeriksa;
    private com.toedter.calendar.JDateChooser txtTglCariPeriksa;
    // End of variables declaration//GEN-END:variables

    private void resetIdPeriksa() {
        String autoNumberPeriksa = DatabaseHelper.getAutoNumberPeriksa(new AutoNumber());
        txtIdPeriksa.setText(autoNumberPeriksa);
    }

    @Override
    public void onInsert(DBPeriksa periksa) {
        resetIdPeriksa();
        txtTanggalPeriksa.setDate(new Date());
        txtIdPasien.setText("");
        comboDokter.setSelectedIndex(0);
        txtDiagnosa.setText("");
        txtAlergiObat.setText("");
        tabelModelResepDokter.clear();
        reloadDataPeriksa();
        regRwtJalan.getMessageComponent1().show("Tambah data periksa sukses", Color.darkGray, Color.GREEN);
    }

    @Override
    public void onUpdate(DBPeriksa periksa) {
        resetIdPeriksa();
        txtTanggalPeriksa.setDate(new Date());
        txtIdPasien.setText("");
        comboDokter.setSelectedIndex(0);
        txtDiagnosa.setText("");
        txtAlergiObat.setText("");
        tabelModelResepDokter.clear();
        reloadDataPeriksa();
        oldTglPeriksaSelected = null;
        btnViewDetail.setEnabled(false);
        btnTambahObat.setEnabled(true);
        btnSimpan.setEnabled(true);
        btnUbah.setEnabled(false);
        btnUbah.setPress(false);
        btnHapus.setEnabled(false);
        regRwtJalan.getMessageComponent1().show("Mengubah data periksa sukses", Color.darkGray, Color.GREEN);
    }

    @Override
    public void onDetele(Integer kdPeriksa) {
        resetIdPeriksa();
        txtTanggalPeriksa.setDate(new Date());
        txtIdPasien.setText("");
        comboDokter.setSelectedIndex(0);
        txtDiagnosa.setText("");
        txtAlergiObat.setText("");
        tabelModelResepDokter.clear();
        reloadDataPeriksa();
        oldTglPeriksaSelected = null;
        btnViewDetail.setEnabled(false);
        btnTambahObat.setEnabled(true);
        btnSimpan.setEnabled(true);
        btnUbah.setEnabled(false);
        btnUbah.setPress(false);
        btnHapus.setEnabled(false);
        regRwtJalan.getMessageComponent1().show("Menghapus data periksa sukses", Color.darkGray, Color.GREEN);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (tabelPeriksa.getSelectedRowCount() == 1) {
            int selectedRow = tabelPeriksa.getSelectedRow();
            int convertRowIndexToModel = tabelPeriksa.convertRowIndexToModel(selectedRow);
            DBPeriksa get = tabelModelPeriksa.get(convertRowIndexToModel);
            btnViewDetail.setEnabled(true);
            btnTambahObat.setEnabled(false);
            btnSimpan.setEnabled(false);
            btnUbah.setEnabled(true);
            btnHapus.setEnabled(true);
            txtIdPeriksa.setText(get.getIdperiksa().toString());
            txtTanggalPeriksa.setDate(get.getTgl_periksa());
            oldTglPeriksaSelected = get.getTgl_periksa();
            txtIdPasien.setText(get.getDiagnosa().getPasien().getIdpasien().toString());
            comboBoxModel.setSelectedItem(get.getDokter().getDokter());
            txtDiagnosa.setText(get.getDiagnosa().getDiagnosa());
            txtAlergiObat.setText(get.getDiagnosa().getAlergiObat());
        } else {

            resetIdPeriksa();
            txtTanggalPeriksa.setDate(new Date());
            txtIdPasien.setText("");
            comboDokter.setSelectedIndex(0);
            txtDiagnosa.setText("");
            txtAlergiObat.setText("");
            oldTglPeriksaSelected = null;
            btnViewDetail.setEnabled(false);
            btnTambahObat.setEnabled(true);
            btnSimpan.setEnabled(true);
            btnUbah.setEnabled(false);
            btnHapus.setEnabled(false);
        }
    }

}
