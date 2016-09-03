/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.frame.panel;

import ian.RegisterRawatJalan.DatabaseHelper.DatabaseHelper;
import Nciz.RegisterRawatJalan.Entity.DBPasien;
import Nciz.RegisterRawatJalan.controller.PasienController;
import Nciz.RegisterRawatJalan.dao.DAOPasien;
import Nciz.RegisterRawatJalan.ex.PasienException;
import Nciz.RegisterRawatJalan.frame.RegRwtJalan;
import Nciz.RegisterRawatJalan.listener.ListenerPasien;
import Nciz.RegisterRawatJalan.model.PasienModel;
import com.dbMIF.AutoNumber;
import com.stripbandunk.jwidget.model.DefaultPaginationModel;
import com.stripbandunk.jwidget.model.DynamicTableModel;
import com.uiMIF.JTextEx;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JRadioButton;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Ncizh
 */
public class MenuPasien extends javax.swing.JPanel implements ListenerPasien, ListSelectionListener {

    private RegRwtJalan regRwtJalan;
    private PasienController pasienController;
    private PasienModel pasienModel;
    private DynamicTableModel<DBPasien> tabelModel;
    private Boolean deteksiCari;
    private String oldCariText;
    private String deteksiGender = null;
    private String detekJenisPelayanan=null;
    private String deteksiKunjungan=null;
    private String deteksiUnit=null;
    private String kunjungan=null;
    private String unitTujuan=null;
    private String oldNama;
    private String oldUmur;
    private String oldNama_kk;
    private String oldAlamat;
    private String oldGender;
    private String oldJenis_pelayanan;
    private String oldKunjungan;
    private String oldUnit_tujuan;
    private MenuUtaman menuUtaman;

    /**
     * Creates new form MenuPasien
     */
    public MenuPasien() {
        initComponents();
        resetNumber();

        pasienController = new PasienController();
        pasienModel = new PasienModel();
        pasienController.setPasienModel(pasienModel);
        pasienModel.setListenerPasien(this);
        tabelPasien.getSelectionModel().addListSelectionListener(this);
    }
    public String getOldJenis_pelayanan() {
        return oldJenis_pelayanan;
    }

    public void setOldJenis_pelayanan(String oldJenis_pelayanan) {
        this.oldJenis_pelayanan = oldJenis_pelayanan;
    }

    public String getOldKunjungan() {
        return oldKunjungan;
    }

    public void setOldKunjungan(String oldKunjungan) {
        this.oldKunjungan = oldKunjungan;
    }

    public String getOldUnit_tujuan() {
        return oldUnit_tujuan;
    }

    public void setOldUnit_tujuan(String oldUnit_tujuan) {
        this.oldUnit_tujuan = oldUnit_tujuan;
    }

    public JTextEx getTxtNama_kk() {
        return txtNama_kk;
    }

    public void setTxtNama_kk(JTextEx txtNama_kk) {
        this.txtNama_kk = txtNama_kk;
    }

    public JTextEx getTxtUmur() {
        return txtUmur;
    }

    public void setTxtUmur(JTextEx txtUmur) {
        this.txtUmur = txtUmur;
    }

    public String getOldUmur() {
        return oldUmur;
    }

    public void setOldUmur(String oldUmur) {
        this.oldUmur = oldUmur;
    }

    public JTextEx getTxtAlamat() {
        return txtAlamat;
    }

    public JTextEx getTxtIdPasien() {
        return txtIdPasien;
    }

    public JTextEx getTxtNama() {
        return txtNama;
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

//   
    public JRadioButton getRdBPU() {
        return rdBPU;
    }

    public JRadioButton getRdCewe() {
        return rdCewe;
    }

    public JRadioButton getRdCowo() {
        return rdCowo;
    }

    public JRadioButton getRdGIGI() {
        return rdGIGI;
    }

    public JRadioButton getRdHI() {
        return rdHI;
    }

    public JRadioButton getRdKIA() {
        return rdKIA;
    }

    public JRadioButton getRdaskes() {
        return rdaskes;
    }

    public JRadioButton getRdbaru() {
        return rdbaru;
    }

    public JRadioButton getRdbayar() {
        return rdbayar;
    }

    public JRadioButton getRdgratis() {
        return rdgratis;
    }

    public JRadioButton getRdjamsostek() {
        return rdjamsostek;
    }

    public JRadioButton getRdjps() {
        return rdjps;
    }

    public JRadioButton getRdlama() {
        return rdlama;
    }

    private void resetNumber() {
        String autoNumberPasien = DatabaseHelper.getAutoNumberPasien(new AutoNumber());
        txtIdPasien.setText(autoNumberPasien);
    }

    public void reloadDate() {
        new SwingWorker<List<DBPasien>, Object>() {

            DAOPasien pasien;

            @Override
            protected List<DBPasien> doInBackground() throws Exception {
                pasien = DatabaseHelper.getPasien();
                List<DBPasien> all;
                Thread.sleep(150);
                all = pasien.getAll(0, 7);
                return all;
            }

            @Override
            protected void done() {
                try {
                    tabelModel = new DynamicTableModel<>(DBPasien.class);
                    tabelPasien.setDynamicModel(tabelModel);
                    tabelPasien.setShowGrid(true);
                    tabelPasien.setRowHeight(24);
                    Integer total;
                    DefaultPaginationModel defaultPaginationModel;
                    total = new Long(pasien.getCount()).intValue();
                    defaultPaginationModel = new DefaultPaginationModel(7, total);
                    pagePasien.setModel(defaultPaginationModel);
                    tabelModel.clear();
                    deteksiCari = false;
                    for (DBPasien pa : get()) {
                        tabelModel.add(pa);
                    }
                } catch (InterruptedException | ExecutionException | PasienException ex) {
                    Logger.getLogger(MenuPasien.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }.execute();
    }

    public void cariDate(final String cari) {
        new SwingWorker<List<DBPasien>, Object>() {

            DAOPasien pasien;

            @Override
            protected List<DBPasien> doInBackground() throws Exception {
                pasien = DatabaseHelper.getPasien();
                List<DBPasien> all;
                Thread.sleep(150);
                all = pasien.getCari(cari, 0, 7);
                return all;
            }

            @Override
            protected void done() {

                try {
                    if (get().isEmpty()) {
                        regRwtJalan.getMessageComponent1().show("Data tidak ditemukan", Color.darkGray, Color.red);
                        cariPasien.setText("");
                        reloadDate();
                    } else {

                        tabelModel = new DynamicTableModel<>(DBPasien.class);
                        tabelPasien.setDynamicModel(tabelModel);
                        tabelPasien.setShowGrid(true);
                        tabelPasien.setRowHeight(24);
                        Integer total;
                        DefaultPaginationModel defaultPaginationModel;
                        total = new Long(pasien.getCount(cari)).intValue();
                        defaultPaginationModel = new DefaultPaginationModel(7, total);
                        pagePasien.setModel(defaultPaginationModel);
                        tabelModel.clear();
                        deteksiCari = true;
                        oldCariText = cari;
                        for (DBPasien pa : get()) {
                            tabelModel.add(pa);
                        }
                    }
                } catch (InterruptedException | ExecutionException | PasienException ex) {
                    Logger.getLogger(MenuPasien.class.getName()).log(Level.SEVERE, null, ex);
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
        btnGroupJnsPelayanan = new javax.swing.ButtonGroup();
        btnGroupKunjungan = new javax.swing.ButtonGroup();
        btnGroupUnit = new javax.swing.ButtonGroup();
        jToolBar1 = new javax.swing.JToolBar();
        renderMenuUtama1 = new Nciz.RegisterRawatJalan.frame.panel.RenderMenuUtama();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cariPasien = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelPasien = new com.stripbandunk.jwidget.JDynamicTable();
        pagePasien = new com.stripbandunk.jwidget.JPagination();
        jPanel2 = new javax.swing.JPanel();
        btnSimpan = new Nciz.RegisterRawatJalan.frame.panel.RenderSimpan();
        btnUbah = new Nciz.RegisterRawatJalan.frame.panel.RenderUbah();
        btnHapus = new Nciz.RegisterRawatJalan.frame.panel.RenderHapus();
        btnSegarkan = new Nciz.RegisterRawatJalan.frame.panel.RenderSegarkan();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        rdjamsostek = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        txtNama = new com.uiMIF.JTextEx();
        rdHI = new javax.swing.JRadioButton();
        txtIdPasien = new com.uiMIF.JTextEx();
        txtAlamat = new com.uiMIF.JTextEx();
        txtUmur = new com.uiMIF.JTextEx();
        txtNama_kk = new com.uiMIF.JTextEx();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        rdbaru = new javax.swing.JRadioButton();
        rdlama = new javax.swing.JRadioButton();
        rdgratis = new javax.swing.JRadioButton();
        rdbayar = new javax.swing.JRadioButton();
        rdaskes = new javax.swing.JRadioButton();
        rdjps = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        rdCewe = new javax.swing.JRadioButton();
        rdCowo = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        rdBPU = new javax.swing.JRadioButton();
        rdGIGI = new javax.swing.JRadioButton();
        rdKIA = new javax.swing.JRadioButton();

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
        jLabel1.setText("Puskesmas Winduaji Pasien");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Pencarian :");

        cariPasien.setBackground(new java.awt.Color(255, 153, 0));
        cariPasien.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        cariPasien.setForeground(new java.awt.Color(255, 255, 255));
        cariPasien.setOpaque(false);
        cariPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cariPasienKeyPressed(evt);
            }
        });

        jScrollPane1.setViewportView(tabelPasien);

        pagePasien.setOpaque(false);
        pagePasien.addPaginationListener(new com.stripbandunk.jwidget.listener.PaginationListener() {
            public void onPageChange(com.stripbandunk.jwidget.event.PaginationEvent evt) {
                pagePasienOnPageChange(evt);
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

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Umur");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Alamat");

        btnGroupJnsPelayanan.add(rdjamsostek);
        rdjamsostek.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        rdjamsostek.setForeground(new java.awt.Color(255, 255, 255));
        rdjamsostek.setText("JAMSOSTEK");
        rdjamsostek.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdjamsostekItemStateChanged(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Nama");

        txtNama.setBackground(new java.awt.Color(255, 153, 0));
        txtNama.setForeground(new java.awt.Color(255, 255, 255));
        txtNama.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtNama.setMaxlength(30);

        btnGroupJnsPelayanan.add(rdHI);
        rdHI.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        rdHI.setForeground(new java.awt.Color(255, 255, 255));
        rdHI.setText("HI");
        rdHI.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdHIItemStateChanged(evt);
            }
        });

        txtIdPasien.setEditable(false);
        txtIdPasien.setBackground(new java.awt.Color(255, 153, 0));
        txtIdPasien.setForeground(new java.awt.Color(255, 255, 255));
        txtIdPasien.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtIdPasien.setMaxlength(11);

        txtAlamat.setBackground(new java.awt.Color(255, 153, 0));
        txtAlamat.setForeground(new java.awt.Color(255, 255, 255));
        txtAlamat.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtAlamat.setMaxlength(60);

        txtUmur.setBackground(new java.awt.Color(255, 153, 0));
        txtUmur.setForeground(new java.awt.Color(255, 255, 255));
        txtUmur.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtUmur.setInputType(com.uiMIF.JTextEx.TypeText.Number);
        txtUmur.setMaxlength(15);

        txtNama_kk.setBackground(new java.awt.Color(255, 153, 0));
        txtNama_kk.setForeground(new java.awt.Color(255, 255, 255));
        txtNama_kk.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtNama_kk.setMaxlength(15);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Nama_KK");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("ID Pasien");

        btnGroupKunjungan.add(rdbaru);
        rdbaru.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        rdbaru.setForeground(new java.awt.Color(255, 255, 255));
        rdbaru.setText("Baru");
        rdbaru.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdbaruItemStateChanged(evt);
            }
        });

        btnGroupKunjungan.add(rdlama);
        rdlama.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        rdlama.setForeground(new java.awt.Color(255, 255, 255));
        rdlama.setText("Lama");
        rdlama.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdlamaItemStateChanged(evt);
            }
        });

        btnGroupJnsPelayanan.add(rdgratis);
        rdgratis.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        rdgratis.setForeground(new java.awt.Color(255, 255, 255));
        rdgratis.setText("GRATIS");
        rdgratis.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdgratisItemStateChanged(evt);
            }
        });

        btnGroupJnsPelayanan.add(rdbayar);
        rdbayar.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        rdbayar.setForeground(new java.awt.Color(255, 255, 255));
        rdbayar.setText("BAYAR");
        rdbayar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdbayarItemStateChanged(evt);
            }
        });

        btnGroupJnsPelayanan.add(rdaskes);
        rdaskes.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        rdaskes.setForeground(new java.awt.Color(255, 255, 255));
        rdaskes.setText("ASKES");
        rdaskes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdaskesItemStateChanged(evt);
            }
        });

        btnGroupJnsPelayanan.add(rdjps);
        rdjps.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        rdjps.setForeground(new java.awt.Color(255, 255, 255));
        rdjps.setText("BPJS");
        rdjps.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdjpsItemStateChanged(evt);
            }
        });
        rdjps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdjpsActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Jenis Pelayanan");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("KUNJUNGAN");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Jenis Kelamin");

        buttonGroup1.add(rdCewe);
        rdCewe.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        rdCewe.setForeground(new java.awt.Color(255, 255, 255));
        rdCewe.setText("Perempuan");
        rdCewe.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdCeweItemStateChanged(evt);
            }
        });

        buttonGroup1.add(rdCowo);
        rdCowo.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        rdCowo.setForeground(new java.awt.Color(255, 255, 255));
        rdCowo.setText("Laki-laki");
        rdCowo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdCowoItemStateChanged(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Unit Tujuan");

        btnGroupUnit.add(rdBPU);
        rdBPU.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        rdBPU.setForeground(new java.awt.Color(255, 255, 255));
        rdBPU.setText("BP.UMUM");
        rdBPU.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdBPUItemStateChanged(evt);
            }
        });

        btnGroupUnit.add(rdGIGI);
        rdGIGI.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        rdGIGI.setForeground(new java.awt.Color(255, 255, 255));
        rdGIGI.setText("POLI GIGI");
        rdGIGI.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdGIGIItemStateChanged(evt);
            }
        });

        btnGroupUnit.add(rdKIA);
        rdKIA.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        rdKIA.setForeground(new java.awt.Color(255, 255, 255));
        rdKIA.setText("KIA");
        rdKIA.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdKIAItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel9)
                        .addComponent(jLabel8)
                        .addComponent(jLabel6)
                        .addComponent(jLabel3))
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNama_kk, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUmur, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(rdCowo)
                            .addComponent(rdCewe))))
                .addGap(66, 66, 66)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdbayar)
                            .addComponent(rdgratis)
                            .addComponent(rdHI))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdjamsostek)
                            .addComponent(rdjps)
                            .addComponent(rdaskes))))
                .addGap(53, 53, 53)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdbaru)
                    .addComponent(jLabel12)
                    .addComponent(rdlama))
                .addGap(43, 43, 43)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel10))
                    .addComponent(rdBPU)
                    .addComponent(rdGIGI)
                    .addComponent(rdKIA))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel3, jLabel4, jLabel6, jLabel7});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtIdPasien, txtNama});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtAlamat, txtUmur});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdHI))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(rdjamsostek)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdjps)
                            .addComponent(rdgratis))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdaskes)
                            .addComponent(rdbayar)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdPasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtUmur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNama_kk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(rdCowo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdCewe))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdlama)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdbaru))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(4, 4, 4)
                        .addComponent(rdBPU)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdGIGI)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdKIA)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cariPasien)))
                .addGap(0, 10, 10))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pagePasien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cariPasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pagePasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        pasienController.insert(this);
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnSegarkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSegarkanActionPerformed
        // TODO add your handling code here:
        reloadDate();
        resetNumber();
        txtNama.setText("");
        txtAlamat.setText("");
        txtUmur.setText("");
        txtNama_kk.setText("");

        buttonGroup1.clearSelection();
        tabelPasien.clearSelection();
        btnSimpan.setEnabled(true);
        btnHapus.setEnabled(false);
        btnUbah.setEnabled(false);

    }//GEN-LAST:event_btnSegarkanActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        // TODO add your handling code here:
        if (oldNama.equals(txtNama.getText().trim())
                && oldAlamat.equals(txtAlamat.getText().trim())
                && oldUmur.equals(txtUmur.getText().trim())
                && oldNama_kk.equals(txtNama_kk.getText().trim())
                && oldGender.equals(deteksiGender)
                && oldJenis_pelayanan.equals(detekJenisPelayanan)
                && oldKunjungan.equals(deteksiKunjungan)
                && oldUnit_tujuan.equals(deteksiUnit)) {

            resetNumber();
            txtNama.setText("");
            txtAlamat.setText("");
            txtUmur.setText("");
            txtNama_kk.setText("");

            buttonGroup1.clearSelection();
            btnGroupJnsPelayanan.clearSelection();
            btnGroupKunjungan.clearSelection();
            btnGroupUnit.clearSelection();
            tabelPasien.clearSelection();
            btnSimpan.setEnabled(true);
            btnUbah.requestFocus(false);
            btnHapus.setEnabled(false);
            btnUbah.setEnabled(false);
        } else {
            pasienController.update(this);
        }
    }//GEN-LAST:event_btnUbahActionPerformed

    private void rdbaruItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdbaruItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            deteksiKunjungan="Baru";
        }
    }//GEN-LAST:event_rdbaruItemStateChanged

    private void rdlamaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdlamaItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            deteksiKunjungan="Lama";
        }
    }//GEN-LAST:event_rdlamaItemStateChanged

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        pasienController.delete(Integer.parseInt(txtIdPasien.getText().trim()), this);
    }//GEN-LAST:event_btnHapusActionPerformed

    private void pagePasienOnPageChange(com.stripbandunk.jwidget.event.PaginationEvent evt) {//GEN-FIRST:event_pagePasienOnPageChange
        // TODO add your handling code here:
        int skip;
        int max;
        skip = (evt.getCurrentPage() - 1) * evt.getPageSize();
        max = evt.getPageSize();
        if (deteksiCari == false) {
            try {
                List<DBPasien> all = DatabaseHelper.getPasien().getAll(skip, max);
                tabelModel.clear();
                for (DBPasien e : all) {
                    tabelModel.add(e);
                }
            } catch (PasienException ex) {
                Logger.getLogger(MenuPasien.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                List<DBPasien> all = DatabaseHelper.getPasien().getCari(oldCariText, skip, max);
                tabelModel.clear();
                for (DBPasien e : all) {
                    tabelModel.add(e);
                }
            } catch (PasienException ex) {
                Logger.getLogger(MenuPasien.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_pagePasienOnPageChange

    private void cariPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariPasienKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            if (cariPasien.getText().trim().isEmpty()) {
                regRwtJalan.getMessageComponent1().
                        show("Isi kolonm pencarian", Color.darkGray, Color.red);
            } else {
                cariDate(cariPasien.getText().trim());
            }
        }
    }//GEN-LAST:event_cariPasienKeyPressed

    private void renderMenuUtama1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_renderMenuUtama1ActionPerformed
        // TODO add your handling code here:
        menuUtaman.backMeuUtama();
    }//GEN-LAST:event_renderMenuUtama1ActionPerformed

    private void rdgratisItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdgratisItemStateChanged
        // TODO add your handling code here:
        if(evt.getStateChange()==ItemEvent.SELECTED){
            detekJenisPelayanan="Gratis";
        }
    }//GEN-LAST:event_rdgratisItemStateChanged

    private void rdaskesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdaskesItemStateChanged
        // TODO add your handling code here:
        if(evt.getStateChange()==ItemEvent.SELECTED){
            detekJenisPelayanan="Askes";
        }
    }//GEN-LAST:event_rdaskesItemStateChanged

    private void rdbayarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdbayarItemStateChanged
        // TODO add your handling code here:
        if(evt.getStateChange()==ItemEvent.SELECTED){
            detekJenisPelayanan="Bayar";
        }
    }//GEN-LAST:event_rdbayarItemStateChanged

    private void rdjpsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdjpsItemStateChanged
        // TODO add your handling code here:
        if(evt.getStateChange()==ItemEvent.SELECTED){
            detekJenisPelayanan="BJPS";
        }
    }//GEN-LAST:event_rdjpsItemStateChanged

    private void rdHIItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdHIItemStateChanged
        // TODO add your handling code here:
        if(evt.getStateChange()==ItemEvent.SELECTED){
            detekJenisPelayanan="HI";
        }
    }//GEN-LAST:event_rdHIItemStateChanged

    private void rdjamsostekItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdjamsostekItemStateChanged
        // TODO add your handling code here:
        if(evt.getStateChange()==ItemEvent.SELECTED){
            detekJenisPelayanan="Jamsostek";
        }
    }//GEN-LAST:event_rdjamsostekItemStateChanged

    private void rdGIGIItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdGIGIItemStateChanged
        // TODO add your handling code here:
        if(evt.getStateChange()==ItemEvent.SELECTED){
            deteksiUnit="GIGI";
        }
    }//GEN-LAST:event_rdGIGIItemStateChanged

    private void rdKIAItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdKIAItemStateChanged
        // TODO add your handling code here:
        if(evt.getStateChange()==ItemEvent.SELECTED){
            deteksiUnit="KIA";
        }
    }//GEN-LAST:event_rdKIAItemStateChanged

    private void rdBPUItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdBPUItemStateChanged
        // TODO add your handling code here:
        if(evt.getStateChange()==ItemEvent.SELECTED){
            deteksiUnit="BPU";
        }
    }//GEN-LAST:event_rdBPUItemStateChanged

    private void rdCowoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdCowoItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            deteksiGender = "Laki-laki";
        }
    }//GEN-LAST:event_rdCowoItemStateChanged

    private void rdCeweItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdCeweItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            deteksiGender = "Perempuan";
        }
    }//GEN-LAST:event_rdCeweItemStateChanged

    private void rdjpsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdjpsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdjpsActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGroupJnsPelayanan;
    private javax.swing.ButtonGroup btnGroupKunjungan;
    private javax.swing.ButtonGroup btnGroupUnit;
    private Nciz.RegisterRawatJalan.frame.panel.RenderHapus btnHapus;
    private Nciz.RegisterRawatJalan.frame.panel.RenderSegarkan btnSegarkan;
    private Nciz.RegisterRawatJalan.frame.panel.RenderSimpan btnSimpan;
    private Nciz.RegisterRawatJalan.frame.panel.RenderUbah btnUbah;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField cariPasien;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private com.stripbandunk.jwidget.JPagination pagePasien;
    private javax.swing.JRadioButton rdBPU;
    private javax.swing.JRadioButton rdCewe;
    private javax.swing.JRadioButton rdCowo;
    private javax.swing.JRadioButton rdGIGI;
    private javax.swing.JRadioButton rdHI;
    private javax.swing.JRadioButton rdKIA;
    private javax.swing.JRadioButton rdaskes;
    private javax.swing.JRadioButton rdbaru;
    private javax.swing.JRadioButton rdbayar;
    private javax.swing.JRadioButton rdgratis;
    private javax.swing.JRadioButton rdjamsostek;
    private javax.swing.JRadioButton rdjps;
    private javax.swing.JRadioButton rdlama;
    private Nciz.RegisterRawatJalan.frame.panel.RenderMenuUtama renderMenuUtama1;
    private com.stripbandunk.jwidget.JDynamicTable tabelPasien;
    private com.uiMIF.JTextEx txtAlamat;
    private com.uiMIF.JTextEx txtIdPasien;
    private com.uiMIF.JTextEx txtNama;
    private com.uiMIF.JTextEx txtNama_kk;
    private com.uiMIF.JTextEx txtUmur;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onInsert(DBPasien pasien) {

        regRwtJalan.getMessageComponent1().show("Sukses menyimpan data", Color.darkGray, Color.GREEN);
        resetNumber();
        txtNama.setText("");
        txtAlamat.setText("");
        txtUmur.setText("");
        txtNama_kk.setText("");
        buttonGroup1.clearSelection();
        btnGroupJnsPelayanan.clearSelection();
        btnGroupKunjungan.clearSelection();
        btnGroupUnit.clearSelection();
    }

    @Override
    public void onUpdate(DBPasien pasien) {
        regRwtJalan.getMessageComponent1().show("Sukses menyimpan data", Color.darkGray, Color.GREEN);
        resetNumber();
        txtNama.setText("");
        txtAlamat.setText("");
        txtUmur.setText("");
        txtNama_kk.setText("");
        buttonGroup1.clearSelection();
        btnGroupJnsPelayanan.clearSelection();
        btnGroupKunjungan.clearSelection();
        btnGroupUnit.clearSelection();
    }

    @Override
    public void onDetele(Integer kode) {
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (tabelPasien.getSelectedRowCount() == 1) {
            int selectedRow = tabelPasien.getSelectedRow();
            int convertRowIndexToModel = tabelPasien.convertRowIndexToModel(selectedRow);
            DBPasien get = tabelModel.get(convertRowIndexToModel);
            txtIdPasien.setText(get.getIdpasien().toString());
            txtNama.setText(get.getNama_pasien());
            txtNama_kk.setText(get.getNama_kk());
            txtUmur.setText(get.getUmur());
            txtAlamat.setText(get.getAlamat_pasien());

            if (get.getJenis_kelamin().equals("Laki-laki")) {
                rdCewe.setSelected(true);
                oldGender = "Laki-laki";
            } else {
                rdCewe.setSelected(true);
                oldGender = "Perempuan";
            }
            if (get.getKunjungan().equals("Baru")) {
                rdbaru.setSelected(true);
                oldKunjungan="Baru";
            } else {
                rdlama.setSelected(true);
                oldKunjungan="Lama";
            }
            switch (get.getUnit_tujuan()) {
                case "BPU":
                    rdBPU.setSelected(true);
                    oldUnit_tujuan="BPU";
                    break;
                case "GIGI":
                    rdGIGI.setSelected(true);
                    oldUnit_tujuan="GIGI";
                    break;
                case "KIA":
                    rdKIA.setSelected(true);
                    oldUnit_tujuan="KIA";
                    break;
            }
            switch (get.getJenis_pelayanan()) {
                case "Bayar":
                    rdbayar.setSelected(true);
                    oldJenis_pelayanan="Bayar";
                    break;
                case "Gratis":
                    rdbayar.setSelected(true);
                    oldJenis_pelayanan="Gratis";
                    break;
                case "HI":
                    rdHI.setSelected(true);
                    oldJenis_pelayanan="HI";
                    break;
                case "JPS":
                    rdjps.setSelected(true);
                    oldJenis_pelayanan="BPJS";
                    break;
                case "Askes":
                    rdaskes.setSelected(true);
                    oldJenis_pelayanan="Akses";
                    break;
                case "Jamsostek":
                    rdjamsostek.setSelected(true);
                    oldJenis_pelayanan="Jamsostek";
                    break;
            }
            oldNama = get.getNama_pasien();
            oldAlamat = get.getAlamat_pasien();
            oldUmur = get.getUmur();
            oldNama_kk = get.getNama_kk();
            btnSimpan.setEnabled(false);
            btnHapus.setEnabled(true);
            btnUbah.setEnabled(true);

        } else {
            resetNumber();
            txtNama.setText("");
            txtAlamat.setText("");
            txtUmur.setText("");
            txtNama_kk.setText("");
            System.out.println("Tian ganteng");
            tabelPasien.clearSelection();
            buttonGroup1.clearSelection();
            btnGroupJnsPelayanan.clearSelection();
            btnGroupKunjungan.clearSelection();
            btnGroupUnit.clearSelection();
            btnHapus.requestFocus(false);
            btnUbah.requestFocus(false);
            btnSimpan.setEnabled(true);
            btnHapus.setEnabled(false);
            btnUbah.setEnabled(false);
        }
    }
}
