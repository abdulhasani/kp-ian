/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.frame.panel;

import ian.RegisterRawatJalan.DatabaseHelper.DatabaseHelper;
import Nciz.RegisterRawatJalan.Entity.DBObat;
import Nciz.RegisterRawatJalan.controller.ObatController;
import Nciz.RegisterRawatJalan.dao.DAOObat;
import Nciz.RegisterRawatJalan.ex.ObatException;
import Nciz.RegisterRawatJalan.frame.RegRwtJalan;
import Nciz.RegisterRawatJalan.listener.ListenerObat;
import Nciz.RegisterRawatJalan.model.ObatModel;
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
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Ncizh
 */
public class MenuObat extends javax.swing.JPanel
        implements ListenerObat, ListSelectionListener {
    
    private RegRwtJalan regRwtJalan;
    private ObatModel obatModel;
    private ObatController obatController;
    private DynamicTableModel<DBObat> tabelModel;
    private Boolean detekesiCari;
    private String oldNama;
    private Date oldTgl;
    private Double oldHargaSatuan;
    private Integer oldStok_Awal;
    private Integer oldPenerimaan;
    private String oldCari;
    private MenuUtaman menuUtaman;

    /**
     * Creates new form MenuPasien
     */
    public MenuObat() {
        initComponents();
        resetNumber();
        obatController = new ObatController();
        obatModel = new ObatModel();
        obatController.setObatModel(obatModel);
        obatModel.setListenerObat(this);
        tabelObat.getSelectionModel().addListSelectionListener(this);
    }
    
    public JTextEx getTxtpenerimaan() {
        return txtpenerimaan;
    }
    
    public void setTxtpenerimaan(JTextEx txtpenerimaan) {
        this.txtpenerimaan = txtpenerimaan;
    }
    
    public void setTxtstok_awal(JTextEx txtstok_awal) {
        this.txtstok_awal = txtstok_awal;
    }
    
    public JTextField getTxtCariObat() {
        return txtCariObat;
    }
    
    public void setTxtCariObat(JTextField txtCariObat) {
        this.txtCariObat = txtCariObat;
    }
    
    public JTextEx getTxtNamaObat1() {
        return txtNamaObat1;
    }
    
    public JTextEx getTxtstok_awal() {
        return txtstok_awal;
    }
    
    public Integer getOldPenerimaan() {
        return oldPenerimaan;
    }
    
    public void setOldPenerimaan(Integer oldPenerimaan) {
        this.oldPenerimaan = oldPenerimaan;
    }
    
    public Integer getOldStok_Awal() {
        return oldStok_Awal;
    }
    
    public void setOldStok_Awal(Integer oldStok_Awal) {
        this.oldStok_Awal = oldStok_Awal;
    }
    
    public void setMenuUtaman(MenuUtaman menuUtaman) {
        this.menuUtaman = menuUtaman;
    }
    
    public void setRegRwtJalan(RegRwtJalan regRwtJalan) {
        this.regRwtJalan = regRwtJalan;
    }
    
    public RegRwtJalan getRegRwtJalan() {
        return regRwtJalan;
    }
    
    public JTextEx getTxtHargaSatuan() {
        return txtHargaSatuan;
    }
    
    public JTextEx getTxtIdObat() {
        return txtIdObat;
    }
    
    public JDateChooser getTxtTanggalExp() {
        return txtTanggalExp;
    }
    
    private void resetNumber() {
        String autoNumberObat = DatabaseHelper.getAutoNumberObat(new AutoNumber());
        txtIdObat.setText(autoNumberObat);
    }
    
    public void reloadData() {
        new SwingWorker<List<DBObat>, Object>() {
            DAOObat obat;
            
            @Override
            protected List<DBObat> doInBackground() throws Exception {
                obat = DatabaseHelper.getObat();
                List<DBObat> all;
                Thread.sleep(150);
                all = obat.getAll(0, 8);
                return all;
            }
            
            @Override
            protected void done() {
                try {
                    tabelModel = new DynamicTableModel<>(DBObat.class);
                    tabelObat.setDynamicModel(tabelModel);
                    tabelObat.setShowGrid(true);
                    tabelObat.setRowHeight(24);
                    Integer total;
                    DefaultPaginationModel defaultPaginationModel;
                    total = new Long(obat.getCount()).intValue();
                    defaultPaginationModel = new DefaultPaginationModel(8, total);
                    pageObat.setModel(defaultPaginationModel);
                    tabelModel.clear();
                    detekesiCari = false;
                    for (DBObat db : get()) {
                        tabelModel.add(db);
                    }
                } catch (InterruptedException | ExecutionException | ObatException ex) {
                    Logger.getLogger(MenuObat.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.execute();
        
    }
    
    private void cariData(final String cari) {
        new SwingWorker<List<DBObat>, Object>() {
            DAOObat obat;
            
            @Override
            protected List<DBObat> doInBackground() throws Exception {
                obat = DatabaseHelper.getObat();
                List<DBObat> all;
                Thread.sleep(150);
                all = obat.getCari(cari, 0, 8);
                return all;
            }
            
            @Override
            protected void done() {
                try {
                    if (get().isEmpty()) {
                        regRwtJalan.getMessageComponent1().
                                show("Data tidak ditemukan", Color.darkGray, Color.RED);
                        txtCariObat.setText("");
                    } else {
                        
                        tabelModel = new DynamicTableModel<>(DBObat.class);
                        tabelObat.setDynamicModel(tabelModel);
                        tabelObat.setShowGrid(true);
                        tabelObat.setRowHeight(24);
                        Integer total;
                        DefaultPaginationModel defaultPaginationModel;
                        total = new Long(obat.getCount(cari)).intValue();
                        defaultPaginationModel = new DefaultPaginationModel(8, total);
                        pageObat.setModel(defaultPaginationModel);
                        tabelModel.clear();
                        oldCari = cari;
                        detekesiCari = true;
                        for (DBObat db : get()) {
                            tabelModel.add(db);
                        }
                    }
                } catch (InterruptedException | ExecutionException | ObatException ex) {
                    Logger.getLogger(MenuObat.class.getName()).log(Level.SEVERE, null, ex);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelObat = new com.stripbandunk.jwidget.JDynamicTable();
        pageObat = new com.stripbandunk.jwidget.JPagination();
        jPanel2 = new javax.swing.JPanel();
        btnSimpan = new Nciz.RegisterRawatJalan.frame.panel.RenderSimpan();
        btnUbah = new Nciz.RegisterRawatJalan.frame.panel.RenderUbah();
        btnHapus = new Nciz.RegisterRawatJalan.frame.panel.RenderHapus();
        btnSegarkan = new Nciz.RegisterRawatJalan.frame.panel.RenderSegarkan();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtNamaObat1 = new com.uiMIF.JTextEx();
        jLabel9 = new javax.swing.JLabel();
        txtHargaSatuan = new com.uiMIF.JTextEx();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtstok_awal = new com.uiMIF.JTextEx();
        txtpenerimaan = new com.uiMIF.JTextEx();
        txtTanggalExp = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtIdObat = new com.uiMIF.JTextEx();
        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txtCariObat = new javax.swing.JTextField();
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
        jLabel1.setText("Puskesmas Winduaji Obat");

        jScrollPane1.setViewportView(tabelObat);

        pageObat.setOpaque(false);
        pageObat.addPaginationListener(new com.stripbandunk.jwidget.listener.PaginationListener() {
            public void onPageChange(com.stripbandunk.jwidget.event.PaginationEvent evt) {
                pageObatOnPageChange(evt);
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

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Stok Awal");

        txtNamaObat1.setBackground(new java.awt.Color(255, 153, 0));
        txtNamaObat1.setForeground(new java.awt.Color(255, 255, 255));
        txtNamaObat1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtNamaObat1.setMaxlength(25);
        txtNamaObat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaObat1ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Harga Satuan");

        txtHargaSatuan.setBackground(new java.awt.Color(255, 153, 0));
        txtHargaSatuan.setForeground(new java.awt.Color(255, 255, 255));
        txtHargaSatuan.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtHargaSatuan.setInputType(com.uiMIF.JTextEx.TypeText.Number);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("ID Obat");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Penerimaan");

        txtstok_awal.setBackground(new java.awt.Color(255, 153, 0));
        txtstok_awal.setForeground(new java.awt.Color(255, 255, 255));
        txtstok_awal.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtstok_awal.setInputType(com.uiMIF.JTextEx.TypeText.Number);
        txtstok_awal.setMaxlength(25);

        txtpenerimaan.setEditable(false);
        txtpenerimaan.setBackground(new java.awt.Color(255, 153, 0));
        txtpenerimaan.setForeground(new java.awt.Color(255, 255, 255));
        txtpenerimaan.setText("0");
        txtpenerimaan.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtpenerimaan.setInputType(com.uiMIF.JTextEx.TypeText.Number);
        txtpenerimaan.setMaxlength(25);

        txtTanggalExp.setDateFormatString("dd MMMM yyyy");
        txtTanggalExp.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Nama obat");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Tanggal Exp");

        txtIdObat.setEditable(false);
        txtIdObat.setBackground(new java.awt.Color(255, 153, 0));
        txtIdObat.setForeground(new java.awt.Color(255, 255, 255));
        txtIdObat.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtIdObat.setMaxlength(11);
        txtIdObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdObatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 784, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtstok_awal, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNamaObat1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtIdObat, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtpenerimaan, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(36, 36, 36)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtTanggalExp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtHargaSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap()))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel3, jLabel6, jLabel8});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtIdObat, txtNamaObat1, txtpenerimaan, txtstok_awal});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel5, jLabel9});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 125, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(txtIdObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtNamaObat1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtstok_awal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8)))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(txtHargaSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(txtTanggalExp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGap(3, 3, 3)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtpenerimaan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel4.add(jPanel3);

        jPanel6.setOpaque(false);

        jPanel5.setOpaque(false);

        txtCariObat.setBackground(new java.awt.Color(255, 153, 0));
        txtCariObat.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtCariObat.setForeground(new java.awt.Color(255, 255, 255));
        txtCariObat.setOpaque(false);
        txtCariObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCariObatKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Pencarian :");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtCariObat, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCariObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel6.add(jPanel5);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pageObat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 2, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1333, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pageObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(91, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        obatController.insert(this);
    }//GEN-LAST:event_btnSimpanActionPerformed
    
    private void btnSegarkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSegarkanActionPerformed
        // TODO add your handling code here:
        reloadData();
        resetNumber();
        
        txtstok_awal.setText("");
        txtpenerimaan.setText("");
        txtHargaSatuan.setText("");
        txtTanggalExp.setDate(null);
        tabelObat.clearSelection();
        btnUbah.setEnabled(false);
        btnHapus.setEnabled(false);
        btnSimpan.setEnabled(true);
        txtstok_awal.setEditable(true);
        txtpenerimaan.setText("0");
        txtpenerimaan.setEditable(false);
    }//GEN-LAST:event_btnSegarkanActionPerformed
    
    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        // TODO add your handling code here:
        try{
            
        if (oldNama.equals(txtNamaObat1.getText().trim())
                && oldHargaSatuan == Double.parseDouble(txtHargaSatuan.getText().trim())
                && oldTgl.equals(txtTanggalExp.getDate())
                && oldPenerimaan == Integer.parseInt(txtpenerimaan.getText())
                && oldStok_Awal == Integer.parseInt(txtstok_awal.getText())) {
            
            resetNumber();
            txtstok_awal.setText("");
            txtpenerimaan.setText("");
            txtTanggalExp.setDate(null);
            tabelObat.clearSelection();
            btnUbah.requestFocus(false);
            btnUbah.setEnabled(false);
            btnHapus.setEnabled(false);
            btnSimpan.setEnabled(true);
        } else {
            
            obatController.update(this);
        }
        }catch(NumberFormatException e){
            
            obatController.update(this);
        }
    }//GEN-LAST:event_btnUbahActionPerformed
    
    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        obatController.delete(Integer.parseInt(txtIdObat.getText().trim()), this);
        btnUbah.requestFocus(false);
        btnHapus.requestFocus(false);
    }//GEN-LAST:event_btnHapusActionPerformed
    
    private void txtCariObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariObatKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            if (txtCariObat.getText().trim().isEmpty()) {
                regRwtJalan.getMessageComponent1().
                        show("Isi kolom pencarian", Color.darkGray, Color.RED);
            } else {
                cariData(txtCariObat.getText().trim());
            }
        }
    }//GEN-LAST:event_txtCariObatKeyPressed
    
    private void pageObatOnPageChange(com.stripbandunk.jwidget.event.PaginationEvent evt) {//GEN-FIRST:event_pageObatOnPageChange
        // TODO add your handling code here:
        int skip;
        int max;
        skip = (evt.getCurrentPage() - 1) * evt.getPageSize();
        max = evt.getPageSize();
        if (detekesiCari == false) {
            try {
                List<DBObat> all;
                all = DatabaseHelper.getObat().getAll(skip, max);
                tabelModel.clear();
                for (DBObat ob : all) {
                    tabelModel.add(ob);
                }
            } catch (ObatException ex) {
                Logger.getLogger(MenuObat.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                List<DBObat> all;
                all = DatabaseHelper.getObat().getCari(oldCari, skip, max);
                tabelModel.clear();
                for (DBObat ob : all) {
                    tabelModel.add(ob);
                }
            } catch (ObatException ex) {
                Logger.getLogger(MenuObat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_pageObatOnPageChange
    
    private void renderMenuUtama1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_renderMenuUtama1ActionPerformed
        // TODO add your handling code here:
        menuUtaman.backMeuUtama();
    }//GEN-LAST:event_renderMenuUtama1ActionPerformed
    
    private void txtNamaObat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaObat1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaObat1ActionPerformed
    
    private void txtIdObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdObatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdObatActionPerformed
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private com.stripbandunk.jwidget.JPagination pageObat;
    private Nciz.RegisterRawatJalan.frame.panel.RenderMenuUtama renderMenuUtama1;
    private com.stripbandunk.jwidget.JDynamicTable tabelObat;
    private javax.swing.JTextField txtCariObat;
    private com.uiMIF.JTextEx txtHargaSatuan;
    private com.uiMIF.JTextEx txtIdObat;
    private com.uiMIF.JTextEx txtNamaObat1;
    private com.toedter.calendar.JDateChooser txtTanggalExp;
    private com.uiMIF.JTextEx txtpenerimaan;
    private com.uiMIF.JTextEx txtstok_awal;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onInsert(DBObat obat) {
        resetNumber();
        txtstok_awal.setText("");
        txtpenerimaan.setText("");
        txtHargaSatuan.setText("");
        txtTanggalExp.setDate(null);
        txtpenerimaan.setText("0");
        regRwtJalan.getMessageComponent1().
                show("Sukses menambah data", Color.darkGray, Color.GREEN);
    }
    
    @Override
    public void onUpdate(DBObat obat) {
        resetNumber();
        txtstok_awal.setText("");
        txtpenerimaan.setText("");
        txtHargaSatuan.setText("");
        txtTanggalExp.setDate(null);
        txtstok_awal.setEditable(true);
        txtpenerimaan.setText("0");
        txtpenerimaan.setEditable(false);
        regRwtJalan.getMessageComponent1().
                show("Sukses menambah data", Color.darkGray, Color.GREEN);
    }
    
    @Override
    public void onDetele(Integer kode) {
        resetNumber();
        txtstok_awal.setText("");
        txtpenerimaan.setText("");
        txtHargaSatuan.setText("");
        txtTanggalExp.setDate(null);
        txtstok_awal.setEditable(true);
        txtpenerimaan.setText("0");
        txtpenerimaan.setEditable(false);
        regRwtJalan.getMessageComponent1().
                show("Sukses menghapus data", Color.darkGray, Color.GREEN);
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (tabelObat.getSelectedRowCount() == 1) {
            int selectedRow = tabelObat.getSelectedRow();
            int convertRowIndexToModel = tabelObat.convertRowIndexToModel(selectedRow);
            DBObat get = tabelModel.get(convertRowIndexToModel);
            txtIdObat.setText(get.getIdobat().toString());
            txtNamaObat1.setText(get.getNama_obat());
            txtstok_awal.setText(get.getStok_awal().toString());
            txtpenerimaan.setText("0");
            txtHargaSatuan.setText(get.getHarga_satuan().toString());
            txtTanggalExp.setDate(get.getTgl());
            if (get.getStok_awal() == 0) {
                txtpenerimaan.setEditable(false);
                txtstok_awal.setEditable(true);
            } else {
                txtpenerimaan.setEditable(true);
                txtstok_awal.setEditable(false);
            }
            oldStok_Awal = get.getStok_awal();
            oldNama = get.getNama_obat();
            oldTgl = get.getTgl();
            oldHargaSatuan = get.getHarga_satuan();
            oldPenerimaan = 0;
            btnUbah.setEnabled(true);
            btnHapus.setEnabled(true);
            btnSimpan.setEnabled(false);
        } else {
            resetNumber();
            txtstok_awal.setText("");
            txtpenerimaan.setText("");
            txtHargaSatuan.setText("");
            txtNamaObat1.setText("");
            txtstok_awal.setEditable(true);
            txtpenerimaan.setText("0");
            txtpenerimaan.setEditable(false);
            txtTanggalExp.setDate(null);
            tabelObat.clearSelection();
            btnUbah.setEnabled(false);
            btnHapus.setEnabled(false);
            btnSimpan.setEnabled(true);
        }
    }
}
