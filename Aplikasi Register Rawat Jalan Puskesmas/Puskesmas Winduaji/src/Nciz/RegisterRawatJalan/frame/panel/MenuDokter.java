/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.frame.panel;

import ian.RegisterRawatJalan.DatabaseHelper.DatabaseHelper;
import Nciz.RegisterRawatJalan.Entity.DBDokter;
import Nciz.RegisterRawatJalan.controller.DokterController;
import Nciz.RegisterRawatJalan.dao.DAODokter;
import Nciz.RegisterRawatJalan.ex.DokterException;
import Nciz.RegisterRawatJalan.frame.RegRwtJalan;
import Nciz.RegisterRawatJalan.listener.ListenerDokter;
import Nciz.RegisterRawatJalan.model.DokterMode;
import com.dbMIF.AutoNumber;
import com.stripbandunk.jwidget.model.DefaultPaginationModel;
import com.stripbandunk.jwidget.model.DynamicTableModel;
import com.uiMIF.JTextEx;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Ncizh
 */
public class MenuDokter extends javax.swing.JPanel implements ListenerDokter,
        ListSelectionListener {

    private RegRwtJalan regRwtJalan;
    private DokterMode dokterMode;
    private DokterController dokterController;
    private DynamicTableModel<DBDokter> tabelModel;
    private Boolean cariDokter;
    private String oldNama;
    private String oldAlamat;
    private String oldTelp;
    private String oldBiaya;
    private String oldCari;
    private MenuUtaman menuUtaman;
    /**
     * Creates new form MenuPasien
     */
    public MenuDokter() {
        initComponents();
        resetNumber();
        dokterController = new DokterController();
        dokterMode = new DokterMode();
        dokterController.setDokterModel(dokterMode);
        dokterMode.setListenerDokter(this);
        tabelDokter.getSelectionModel().addListSelectionListener(this);
    }

    public void setRegRwtJalan(RegRwtJalan regRwtJalan) {
        this.regRwtJalan = regRwtJalan;
    }

    public void setMenuUtaman(MenuUtaman menuUtaman) {
        this.menuUtaman = menuUtaman;
    }

    public RegRwtJalan getRegRwtJalan() {
        return regRwtJalan;
    }

    public JTextEx getTxtAlamat() {
        return txtAlamat;
    }

    public JTextEx getTxtBiaya() {
        return txtBiaya;
    }

    public JTextEx getTxtIdDokter() {
        return txtIdDokter;
    }

    public JTextEx getTxtNama() {
        return txtNama;
    }

    public JTextEx getTxtTelepon() {
        return txtTelepon;
    }

    private void resetNumber() {
        String autoNumberDokter = DatabaseHelper.getAutoNumberDokter(new AutoNumber());
        txtIdDokter.setText(autoNumberDokter);
    }

    public void reloadData() {
        new SwingWorker<List<DBDokter>, Object>() {

            DAODokter dokter;

            @Override
            protected List<DBDokter> doInBackground() throws Exception {
                dokter = DatabaseHelper.getDokter();
                List<DBDokter> all;
                Thread.sleep(150);
                all = dokter.getAll(0, 7);
                return all;
            }

            @Override
            protected void done() {
                try {
                    tabelModel = new DynamicTableModel<>(DBDokter.class);
                    tabelDokter.setDynamicModel(tabelModel);
                    tabelDokter.setShowGrid(true);
                    tabelDokter.setRowHeight(24);
                    Integer total;
                    DefaultPaginationModel defaultPaginationModel;
                    total = new Long(dokter.getCount()).intValue();
                    defaultPaginationModel = new DefaultPaginationModel(7, total);
                    pageDokter.setModel(defaultPaginationModel);
                    tabelModel.clear();
                    cariDokter = false;
                    try {
                        for (DBDokter db : get()) {
                            tabelModel.add(db);
                        }
                    } catch (InterruptedException | ExecutionException ex) {
                        Logger.getLogger(MenuDokter.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (DokterException ex) {
                    Logger.getLogger(MenuDokter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.execute();
    }

    public void CariData(final String cari) {
        new SwingWorker<List<DBDokter>, Object>() {

            DAODokter dokter;

            @Override
            protected List<DBDokter> doInBackground() throws Exception {
                dokter = DatabaseHelper.getDokter();
                List<DBDokter> all;
                Thread.sleep(200);
                all = dokter.getCari(cari, 0, 7);
                return all;
            }

            @Override
            protected void done() {
                try {
                    if (!get().isEmpty()) {
                        try {
                            tabelModel = new DynamicTableModel<>(DBDokter.class);
                            tabelDokter.setDynamicModel(tabelModel);
                            tabelDokter.setShowGrid(true);
                            tabelDokter.setRowHeight(24);
                            Integer total;
                            DefaultPaginationModel defaultPaginationModel;
                            total = new Long(dokter.getCount(cari)).intValue();
                            defaultPaginationModel = new DefaultPaginationModel(7, total);
                            pageDokter.setModel(defaultPaginationModel);
                            tabelModel.clear();
                            cariDokter = true;
                            oldCari = cari;
                            for (DBDokter db : get()) {
                                tabelModel.add(db);

                            }
                        } catch (DokterException ex) {
                            Logger.getLogger(MenuDokter.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        regRwtJalan.getMessageComponent1().show("Data tidak ditemukan", Color.darkGray, Color.RED);
                        reloadData();
                        txtCariDokter.setText("");
                    }
                } catch (InterruptedException | ExecutionException ex) {
                    Logger.getLogger(MenuDokter.class.getName()).log(Level.SEVERE, null, ex);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jToolBar1 = new javax.swing.JToolBar();
        renderMenuUtama1 = new Nciz.RegisterRawatJalan.frame.panel.RenderMenuUtama();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pageDokter = new com.stripbandunk.jwidget.JPagination();
        jPanel2 = new javax.swing.JPanel();
        btnSimpan = new Nciz.RegisterRawatJalan.frame.panel.RenderSimpan();
        btnUbah = new Nciz.RegisterRawatJalan.frame.panel.RenderUbah();
        btnHapus = new Nciz.RegisterRawatJalan.frame.panel.RenderHapus();
        btnSegarkan = new Nciz.RegisterRawatJalan.frame.panel.RenderSegarkan();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelDokter = new com.stripbandunk.jwidget.JDynamicTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtNama = new com.uiMIF.JTextEx();
        txtIdDokter = new com.uiMIF.JTextEx();
        jLabel10 = new javax.swing.JLabel();
        txtBiaya = new com.uiMIF.JTextEx();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTelepon = new com.uiMIF.JTextEx();
        txtAlamat = new com.uiMIF.JTextEx();
        jLabel3 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        txtCariDokter = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

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
        jLabel1.setText("Puskesmas Winduaji Dokter");

        pageDokter.setOpaque(false);
        pageDokter.addPaginationListener(new com.stripbandunk.jwidget.listener.PaginationListener() {
            public void onPageChange(com.stripbandunk.jwidget.event.PaginationEvent evt) {
                pageDokterOnPageChange(evt);
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

        jScrollPane1.setViewportView(tabelDokter);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 775, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(219, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel3);

        jPanel6.setOpaque(false);

        jPanel5.setOpaque(false);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Nama");

        txtNama.setBackground(new java.awt.Color(255, 153, 0));
        txtNama.setForeground(new java.awt.Color(255, 255, 255));
        txtNama.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtNama.setMaxlength(30);

        txtIdDokter.setEditable(false);
        txtIdDokter.setBackground(new java.awt.Color(255, 153, 0));
        txtIdDokter.setForeground(new java.awt.Color(255, 255, 255));
        txtIdDokter.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtIdDokter.setInputType(com.uiMIF.JTextEx.TypeText.Number);
        txtIdDokter.setMaxlength(11);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Biaya");

        txtBiaya.setBackground(new java.awt.Color(255, 153, 0));
        txtBiaya.setForeground(new java.awt.Color(255, 255, 255));
        txtBiaya.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtBiaya.setInputType(com.uiMIF.JTextEx.TypeText.Number);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Telepon");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Alamat");

        txtTelepon.setBackground(new java.awt.Color(255, 153, 0));
        txtTelepon.setForeground(new java.awt.Color(255, 255, 255));
        txtTelepon.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtTelepon.setInputType(com.uiMIF.JTextEx.TypeText.Number);
        txtTelepon.setMaxlength(15);

        txtAlamat.setBackground(new java.awt.Color(255, 153, 0));
        txtAlamat.setForeground(new java.awt.Color(255, 255, 255));
        txtAlamat.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtAlamat.setMaxlength(60);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("ID Dokter");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtIdDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBiaya, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 20, Short.MAX_VALUE))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel3, jLabel6, jLabel7});

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel9});

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtBiaya, txtTelepon});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtIdDokter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtBiaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.add(jPanel5);

        jPanel8.setOpaque(false);

        jPanel7.setOpaque(false);

        txtCariDokter.setBackground(new java.awt.Color(255, 153, 0));
        txtCariDokter.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtCariDokter.setForeground(new java.awt.Color(255, 255, 255));
        txtCariDokter.setOpaque(false);
        txtCariDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCariDokterKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Pencarian :");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCariDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCariDokter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel8.add(jPanel7);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pageDokter, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 1105, Short.MAX_VALUE)
                .addGap(10, 10, 10))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pageDokter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        dokterController.insertAdmin(this);
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnSegarkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSegarkanActionPerformed
        // TODO add your handling code here:
        resetNumber();
        reloadData();
        txtNama.setText("");
        txtAlamat.setText("");
        txtTelepon.setText("");
        txtBiaya.setText("");
        txtCariDokter.setText("");
        btnHapus.setEnabled(false);
        btnUbah.setEnabled(false);
        btnSimpan.setEnabled(true);
        tabelDokter.clearSelection();
    }//GEN-LAST:event_btnSegarkanActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        // TODO add your handling code here:
        if (oldNama.equals(txtNama.getText().trim())
                && oldAlamat.equals(txtAlamat.getText().trim())
                && oldTelp.equals(txtTelepon.getText().trim())
                && Double.parseDouble(oldBiaya) == Double.parseDouble(txtBiaya.getText().trim())) {
            reloadData();
            resetNumber();
            txtNama.setText("");
            txtAlamat.setText("");
            txtTelepon.setText("");
            txtBiaya.setText("");
            txtCariDokter.setText("");
            btnUbah.requestFocus(false);
            btnHapus.setEnabled(false);
            btnUbah.setEnabled(false);
            btnSimpan.setEnabled(true);
            tabelDokter.clearSelection();
        } else {
            dokterController.update(this);
        }
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        dokterController.delete(Integer.parseInt(txtIdDokter.getText().trim()), this);

    }//GEN-LAST:event_btnHapusActionPerformed

    private void pageDokterOnPageChange(com.stripbandunk.jwidget.event.PaginationEvent evt) {//GEN-FIRST:event_pageDokterOnPageChange
        // TODO add your handling code here:
        int skip;
        int max;
        skip = (evt.getCurrentPage() - 1) * evt.getPageSize();
        max = evt.getPageSize();
        if (cariDokter == false) {
            try {
                List<DBDokter> all;
                all = DatabaseHelper.getDokter().getAll(skip, max);
                tabelModel.clear();
                for (DBDokter en : all) {
                    tabelModel.add(en);
                }
            } catch (DokterException ex) {
                Logger.getLogger(MenuDokter.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                List<DBDokter> cari;
                cari = DatabaseHelper.getDokter().getCari(oldCari, skip, max);
                tabelModel.clear();
                for (DBDokter en : cari) {
                    tabelModel.add(en);
                }
            } catch (DokterException ex) {
                Logger.getLogger(MenuDokter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_pageDokterOnPageChange

    private void txtCariDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariDokterKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            if (txtCariDokter.getText().trim().isEmpty()) {
                regRwtJalan.getMessageComponent1().show("Isi kolom pencarian", Color.darkGray, Color.RED);
            } else {
                CariData(txtCariDokter.getText().trim());
            }

        }
    }//GEN-LAST:event_txtCariDokterKeyPressed

    private void renderMenuUtama1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_renderMenuUtama1ActionPerformed
        // TODO add your handling code here:
        menuUtaman.backMeuUtama();
    }//GEN-LAST:event_renderMenuUtama1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Nciz.RegisterRawatJalan.frame.panel.RenderHapus btnHapus;
    private Nciz.RegisterRawatJalan.frame.panel.RenderSegarkan btnSegarkan;
    private Nciz.RegisterRawatJalan.frame.panel.RenderSimpan btnSimpan;
    private Nciz.RegisterRawatJalan.frame.panel.RenderUbah btnUbah;
    private javax.swing.ButtonGroup buttonGroup1;
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
    private com.stripbandunk.jwidget.JPagination pageDokter;
    private Nciz.RegisterRawatJalan.frame.panel.RenderMenuUtama renderMenuUtama1;
    private com.stripbandunk.jwidget.JDynamicTable tabelDokter;
    private com.uiMIF.JTextEx txtAlamat;
    private com.uiMIF.JTextEx txtBiaya;
    private javax.swing.JTextField txtCariDokter;
    private com.uiMIF.JTextEx txtIdDokter;
    private com.uiMIF.JTextEx txtNama;
    private com.uiMIF.JTextEx txtTelepon;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onInsert(DBDokter dokter) {
        resetNumber();
        txtNama.setText("");
        txtAlamat.setText("");
        txtTelepon.setText("");
        txtBiaya.setText("");
        regRwtJalan.getMessageComponent1().show("Sukses menyimpan data", Color.darkGray, Color.GREEN);
    }

    @Override
    public void onUpdate(DBDokter dokter) {
        resetNumber();
        txtNama.setText("");
        txtAlamat.setText("");
        txtTelepon.setText("");
        txtBiaya.setText("");
        regRwtJalan.getMessageComponent1().show("Sukses ubah data", Color.darkGray, Color.GREEN);
    }

    @Override
    public void onDetele(Integer kode) {
        resetNumber();
        txtNama.setText("");
        txtAlamat.setText("");
        txtTelepon.setText("");
        txtBiaya.setText("");
        txtCariDokter.setText("");
        btnHapus.setEnabled(false);
        btnUbah.setEnabled(false);
        btnSimpan.setEnabled(true);
        tabelDokter.clearSelection();
        regRwtJalan.getMessageComponent1().show("Sukses menghapus data", Color.darkGray, Color.GREEN);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (tabelDokter.getSelectedRowCount() == 1) {
            int selectedRow = tabelDokter.getSelectedRow();
            int convertRowIndexToModel = tabelDokter.convertRowIndexToModel(selectedRow);
            DBDokter get = tabelModel.get(convertRowIndexToModel);
            txtIdDokter.setText(get.getIddokter().toString());
            txtNama.setText(get.getNama_dokter());
            txtAlamat.setText(get.getAlamat_dokter());
            txtTelepon.setText(get.getTelp_dokter());
            txtBiaya.setText(get.getBiaya().toString());
            oldNama = get.getNama_dokter();
            oldAlamat = get.getAlamat_dokter();
            oldTelp = get.getTelp_dokter();
            oldBiaya = get.getBiaya().toString();
            btnHapus.requestFocus(false);
            btnUbah.requestFocus(false);
            btnSimpan.setEnabled(false);
            btnHapus.setEnabled(true);
            btnUbah.setEnabled(true);
        } else {
            resetNumber();
            txtNama.setText("");
            txtAlamat.setText("");
            txtTelepon.setText("");
            txtBiaya.setText("");
            tabelDokter.clearSelection();
            btnHapus.setEnabled(false);
            btnUbah.setEnabled(false);
            btnSimpan.setEnabled(true);
        }
    }
}
