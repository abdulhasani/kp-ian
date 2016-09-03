/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nciz.RegisterRawatJalan.frame.panel;

import ian.RegisterRawatJalan.DatabaseHelper.DatabaseHelper;
import Nciz.RegisterRawatJalan.Entity.DBAdministrator;
import Nciz.RegisterRawatJalan.controller.AdminController;
import Nciz.RegisterRawatJalan.dao.DAOAdministrator;
import Nciz.RegisterRawatJalan.ex.AdministratorException;
import Nciz.RegisterRawatJalan.frame.RegRwtJalan;
import Nciz.RegisterRawatJalan.listener.ListenerAdmin;
import Nciz.RegisterRawatJalan.model.AdminMode;
import com.dbMIF.AutoNumber;
import com.stripbandunk.jwidget.model.DefaultPaginationModel;
import com.stripbandunk.jwidget.model.DynamicTableModel;
import com.uiMIF.JPasswordEx;
import com.uiMIF.JTextEx;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Ncizh
 */
public class MenuAdmin extends javax.swing.JPanel implements ListenerAdmin, ListSelectionListener {
    
    private RegRwtJalan regRwtJalan;
    private AdminMode adminMode;
    private AdminController adminController;
    private DynamicTableModel<DBAdministrator> tabelModel;
    private String oldId;
    private String oldNama;
    private String oldAlamat;
    private String oldTelp;
    private String oldUserName;
    private String oldPassword;
    private Boolean deteksiCari;
    private String oldCariText;
    private MenuUtaman menuUtaman;
    private String oldBagian;

    /**
     * Creates new form MenuPasien
     */
    public MenuAdmin() {
        initComponents();
        setNumber();
        adminController = new AdminController();
        adminMode = new AdminMode();
        adminController.setAdminMode(adminMode);
        adminMode.setListenerAdmin(this);
        tabelAdmin.getSelectionModel().addListSelectionListener(this);
    }
    
    public void setMenuUtaman(MenuUtaman menuUtaman) {
        this.menuUtaman = menuUtaman;
    }
    
    public void reload() {
        new SwingWorker<List<DBAdministrator>, Object>() {
            DAOAdministrator administrator;
            
            @Override
            protected List<DBAdministrator> doInBackground() throws Exception {
                administrator = DatabaseHelper.getAdministrator();
                List<DBAdministrator> all;
                Thread.sleep(250);
                all = administrator.getAll(0, 7);
                return all;
            }
            
            @Override
            protected void done() {
                try {
                    if (!get().isEmpty()) {
                        tabelModel = new DynamicTableModel<>(DBAdministrator.class);
                        tabelAdmin.setDynamicModel(tabelModel);
                        tabelAdmin.setShowGrid(true);
                        tabelAdmin.setRowHeight(24);
                        txtCariAdmin.setText("");
                        Integer total;
                        DefaultPaginationModel defaultPaginationModel;
                        total = new Long(administrator.getCount()).intValue();
                        defaultPaginationModel = new DefaultPaginationModel(7, total);
                        pageAdmin.setModel(defaultPaginationModel);
                        tabelModel.clear();
                        deteksiCari = false;
                        for (DBAdministrator en : get()) {
                            tabelModel.add(en);
                        }
                    }
                } catch (AdministratorException | InterruptedException | ExecutionException ex) {
                    Logger.getLogger(MenuAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.execute();
    }
    
    private void cari(final String cari) {
        new SwingWorker<List<DBAdministrator>, Object>() {
            DAOAdministrator administrator;
            
            @Override
            protected List<DBAdministrator> doInBackground() throws Exception {
                administrator = DatabaseHelper.getAdministrator();
                List<DBAdministrator> car;
                Thread.sleep(150);
                car = administrator.getCari(cari, 0, 7);
                return car;
            }
            
            @Override
            protected void done() {
                try {
                    if (!get().isEmpty()) {
                        
                        tabelModel = new DynamicTableModel<>(DBAdministrator.class);
                        tabelAdmin.setDynamicModel(tabelModel);
                        tabelAdmin.setShowGrid(true);
                        tabelAdmin.setRowHeight(24);
                        Integer total;
                        DefaultPaginationModel defaultPaginationModel;
                        total = new Long(administrator.getCount(cari)).intValue();
                        defaultPaginationModel = new DefaultPaginationModel(7, total);
                        pageAdmin.setModel(defaultPaginationModel);
                        tabelModel.clear();
                        deteksiCari = true;
                        oldCariText = cari;
                        for (DBAdministrator en : get()) {
                            tabelModel.add(en);
                        }
                    } else {
                        regRwtJalan.getMessageComponent1().show("Data tidak ditemukan", Color.darkGray, Color.red);
                        reload();
                    }
                } catch (AdministratorException | InterruptedException | ExecutionException ex) {
                    Logger.getLogger(MenuAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.execute();
    }
    
    public JComboBox getComboBagian() {
        return comboBagian;
    }
    
    public void setRegRwtJalan(RegRwtJalan regRwtJalan) {
        this.regRwtJalan = regRwtJalan;
    }
    
    public RegRwtJalan getRegRwtJalan() {
        return regRwtJalan;
    }
    
    public JTextEx getTxtAlamatAdmin() {
        return txtAlamatAdmin;
    }
    
    public JTextEx getTxtIdAdmin() {
        return txtIdAdmin;
    }
    
    public JTextEx getTxtNamaAdmin() {
        return txtNamaAdmin;
    }
    
    public JPasswordEx getTxtPwdAdmin() {
        return txtPwdAdmin;
    }
    
    public JTextEx getTxtTelpAdmin() {
        return txtTelpAdmin;
    }
    
    public JTextEx getTxtUserNameAdmin() {
        return txtUserNameAdmin;
    }
    
    private void setNumber() {
        String autoNumber = DatabaseHelper.getAutoNumber(new AutoNumber());
        txtIdAdmin.setText(autoNumber);
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
        pageAdmin = new com.stripbandunk.jwidget.JPagination();
        jPanel2 = new javax.swing.JPanel();
        btnSimpan = new Nciz.RegisterRawatJalan.frame.panel.RenderSimpan();
        btnUbah = new Nciz.RegisterRawatJalan.frame.panel.RenderUbah();
        btnDelete = new Nciz.RegisterRawatJalan.frame.panel.RenderHapus();
        renderSegarkan1 = new Nciz.RegisterRawatJalan.frame.panel.RenderSegarkan();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelAdmin = new com.stripbandunk.jwidget.JDynamicTable();
        panelPapanCrud = new javax.swing.JPanel();
        panelCrud = new javax.swing.JPanel();
        txtUserNameAdmin = new com.uiMIF.JTextEx();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtPwdAdmin = new com.uiMIF.JPasswordEx();
        comboBagian = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        txtTelpAdmin = new com.uiMIF.JTextEx();
        txtAlamatAdmin = new com.uiMIF.JTextEx();
        txtNamaAdmin = new com.uiMIF.JTextEx();
        txtIdAdmin = new com.uiMIF.JTextEx();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtCariAdmin = new javax.swing.JTextField();

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
        jLabel1.setText("Puskesmas Winduaji Administrator");

        pageAdmin.setOpaque(false);
        pageAdmin.addPaginationListener(new com.stripbandunk.jwidget.listener.PaginationListener() {
            public void onPageChange(com.stripbandunk.jwidget.event.PaginationEvent evt) {
                pageAdminOnPageChange(evt);
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

        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("HAPUS");
        btnDelete.setEnabled(false);
        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel2.add(btnDelete);

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

        jPanel3.setOpaque(false);

        jPanel4.setOpaque(false);

        tabelAdmin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabelAdmin);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1014, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel4);

        panelPapanCrud.setOpaque(false);

        panelCrud.setOpaque(false);

        txtUserNameAdmin.setBackground(new java.awt.Color(255, 153, 0));
        txtUserNameAdmin.setForeground(new java.awt.Color(255, 255, 255));
        txtUserNameAdmin.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtUserNameAdmin.setMaxlength(30);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("User Name");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Password");

        txtPwdAdmin.setBackground(new java.awt.Color(255, 153, 0));
        txtPwdAdmin.setForeground(new java.awt.Color(255, 255, 255));
        txtPwdAdmin.setMaxlength(60);

        comboBagian.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        comboBagian.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Admin", "Loket", "Periksa", "Apotek" }));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Bagian");

        txtTelpAdmin.setBackground(new java.awt.Color(255, 153, 0));
        txtTelpAdmin.setForeground(new java.awt.Color(255, 255, 255));
        txtTelpAdmin.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtTelpAdmin.setInputType(com.uiMIF.JTextEx.TypeText.Number);
        txtTelpAdmin.setMaxlength(15);

        txtAlamatAdmin.setBackground(new java.awt.Color(255, 153, 0));
        txtAlamatAdmin.setForeground(new java.awt.Color(255, 255, 255));
        txtAlamatAdmin.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtAlamatAdmin.setMaxlength(60);

        txtNamaAdmin.setBackground(new java.awt.Color(255, 153, 0));
        txtNamaAdmin.setForeground(new java.awt.Color(255, 255, 255));
        txtNamaAdmin.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtNamaAdmin.setMaxlength(30);

        txtIdAdmin.setEditable(false);
        txtIdAdmin.setBackground(new java.awt.Color(255, 153, 0));
        txtIdAdmin.setForeground(new java.awt.Color(255, 255, 255));
        txtIdAdmin.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtIdAdmin.setInputType(com.uiMIF.JTextEx.TypeText.Number);
        txtIdAdmin.setMaxlength(11);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("ID Admin");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Nama");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Alamat");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Telepon");

        javax.swing.GroupLayout panelCrudLayout = new javax.swing.GroupLayout(panelCrud);
        panelCrud.setLayout(panelCrudLayout);
        panelCrudLayout.setHorizontalGroup(
            panelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 686, Short.MAX_VALUE)
            .addGroup(panelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCrudLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelCrudLayout.createSequentialGroup()
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtTelpAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelCrudLayout.createSequentialGroup()
                            .addGroup(panelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelCrudLayout.createSequentialGroup()
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtIdAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panelCrudLayout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtNamaAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelCrudLayout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtAlamatAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGap(35, 35, 35)
                            .addGroup(panelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(panelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtUserNameAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtPwdAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(comboBagian, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap()))
        );

        panelCrudLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel3, jLabel6, jLabel7, jLabel9});

        panelCrudLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel11, jLabel12});

        panelCrudLayout.setVerticalGroup(
            panelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 128, Short.MAX_VALUE)
            .addGroup(panelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCrudLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelCrudLayout.createSequentialGroup()
                            .addGroup(panelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(txtIdAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(txtNamaAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(txtAlamatAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(txtTelpAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(panelCrudLayout.createSequentialGroup()
                            .addGroup(panelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtUserNameAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11)
                                .addComponent(txtPwdAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(comboBagian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        panelPapanCrud.add(panelCrud);

        jPanel6.setOpaque(false);

        jPanel5.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Pencarian :");

        txtCariAdmin.setBackground(new java.awt.Color(255, 153, 0));
        txtCariAdmin.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtCariAdmin.setForeground(new java.awt.Color(255, 255, 255));
        txtCariAdmin.setOpaque(false);
        txtCariAdmin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCariAdminKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(txtCariAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCariAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        jPanel6.add(jPanel5);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pageAdmin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelPapanCrud, javax.swing.GroupLayout.PREFERRED_SIZE, 1268, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(0, 21, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pageAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelPapanCrud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        adminController.insertAdmin(this);
    }//GEN-LAST:event_btnSimpanActionPerformed
    
    private void renderSegarkan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_renderSegarkan1ActionPerformed
        // TODO add your handling code here:
        reload();
        setNumber();
        txtNamaAdmin.setText("");
        txtAlamatAdmin.setText("");
        txtTelpAdmin.setText("");
        txtUserNameAdmin.setText("");
        txtPwdAdmin.setText("");
        
        tabelAdmin.clearSelection();
        btnSimpan.setEnabled(true);
        btnUbah.setEnabled(false);
        btnDelete.setEnabled(false);
        comboBagian.setSelectedIndex(0);
    }//GEN-LAST:event_renderSegarkan1ActionPerformed
    
    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        // TODO add your handling code here:
        char[] password = txtPwdAdmin.getPassword();
        String pass = new String(password);
        if (Integer.parseInt(txtIdAdmin.getText()) == Integer.parseInt(oldId)
                && txtNamaAdmin.getText().trim().equals(oldNama)
                && txtAlamatAdmin.getText().trim().equals(oldAlamat)
                && txtTelpAdmin.getText().trim().equals(oldTelp)
                && txtUserNameAdmin.getText().trim().equals(oldUserName)
                && pass.equals(oldPassword)
                && oldBagian.equals(comboBagian.getSelectedItem().toString())) {
            tabelAdmin.clearSelection();
            setNumber();
            txtNamaAdmin.setText("");
            txtAlamatAdmin.setText("");
            txtTelpAdmin.setText("");
            txtUserNameAdmin.setText("");
            txtPwdAdmin.setText("");
            btnUbah.requestFocus(false);
            btnUbah.setEnabled(false);
            btnDelete.setEnabled(false);
        } else {
            adminController.update(this);
        }
    }//GEN-LAST:event_btnUbahActionPerformed
    
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        adminController.delete(Integer.parseInt(txtIdAdmin.getText().trim()), this);
    }//GEN-LAST:event_btnDeleteActionPerformed
    
    private void txtCariAdminKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariAdminKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            if (txtCariAdmin.getText().trim().isEmpty()) {
                regRwtJalan.getMessageComponent1().show("Isi kolom pencarian", Color.darkGray, Color.red);
            } else {
                cari(txtCariAdmin.getText().trim());
            }
        }
    }//GEN-LAST:event_txtCariAdminKeyPressed
    
    private void pageAdminOnPageChange(com.stripbandunk.jwidget.event.PaginationEvent evt) {//GEN-FIRST:event_pageAdminOnPageChange
        // TODO add your handling code here:
        int skip;
        int max;
        skip = (evt.getCurrentPage() - 1) * evt.getPageSize();
        max = evt.getPageSize();
        if (deteksiCari == false) {
            try {
                List<DBAdministrator> all;
                all = DatabaseHelper.getAdministrator().getAll(skip, max);
                tabelModel.clear();
                for (DBAdministrator en : all) {
                    tabelModel.add(en);
                }
            } catch (AdministratorException ex) {
                Logger.getLogger(MenuAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                DatabaseHelper.getAdministrator().getCari(oldCariText, skip, max);
            } catch (AdministratorException ex) {
                Logger.getLogger(MenuAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_pageAdminOnPageChange
    
    private void renderMenuUtama1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_renderMenuUtama1ActionPerformed
        // TODO add your handling code here:
        menuUtaman.backMeuUtama();
    }//GEN-LAST:event_renderMenuUtama1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Nciz.RegisterRawatJalan.frame.panel.RenderHapus btnDelete;
    private Nciz.RegisterRawatJalan.frame.panel.RenderSimpan btnSimpan;
    private Nciz.RegisterRawatJalan.frame.panel.RenderUbah btnUbah;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox comboBagian;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private com.stripbandunk.jwidget.JPagination pageAdmin;
    private javax.swing.JPanel panelCrud;
    private javax.swing.JPanel panelPapanCrud;
    private Nciz.RegisterRawatJalan.frame.panel.RenderMenuUtama renderMenuUtama1;
    private Nciz.RegisterRawatJalan.frame.panel.RenderSegarkan renderSegarkan1;
    private com.stripbandunk.jwidget.JDynamicTable tabelAdmin;
    private com.uiMIF.JTextEx txtAlamatAdmin;
    private javax.swing.JTextField txtCariAdmin;
    private com.uiMIF.JTextEx txtIdAdmin;
    private com.uiMIF.JTextEx txtNamaAdmin;
    private com.uiMIF.JPasswordEx txtPwdAdmin;
    private com.uiMIF.JTextEx txtTelpAdmin;
    private com.uiMIF.JTextEx txtUserNameAdmin;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onInsert(DBAdministrator administrator) {
        regRwtJalan.getMessageComponent1().show("Sukses menyimpan data", Color.darkGray, Color.GREEN);
        setNumber();
        txtNamaAdmin.setText("");
        txtAlamatAdmin.setText("");
        txtTelpAdmin.setText("");
        txtUserNameAdmin.setText("");
        txtPwdAdmin.setText("");
        comboBagian.setSelectedIndex(0);
    }
    
    @Override
    public void onUpdate(DBAdministrator administrator) {
        regRwtJalan.getMessageComponent1().show("Sukses mengubah data", Color.darkGray, Color.GREEN);
        setNumber();
        txtNamaAdmin.setText("");
        txtAlamatAdmin.setText("");
        txtTelpAdmin.setText("");
        txtUserNameAdmin.setText("");
        txtPwdAdmin.setText("");
        comboBagian.setSelectedIndex(0);
    }
    
    @Override
    public void onDetele(Integer kode) {
        regRwtJalan.getMessageComponent1().show("Sukses hapus data", Color.darkGray, Color.GREEN);
        setNumber();
        txtNamaAdmin.setText("");
        txtAlamatAdmin.setText("");
        txtTelpAdmin.setText("");
        txtUserNameAdmin.setText("");
        txtPwdAdmin.setText("");
        comboBagian.setSelectedIndex(0);
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        
        if (tabelAdmin.getSelectedRowCount() == 1) {
            btnSimpan.setEnabled(false);
            btnUbah.setEnabled(true);
            btnDelete.setEnabled(true);
            int selectedRow = tabelAdmin.getSelectedRow();
            int convertRowIndexToModel = tabelAdmin.convertRowIndexToModel(selectedRow);
            DBAdministrator get = tabelModel.get(convertRowIndexToModel);
            txtIdAdmin.setText(get.getIdadministrator().toString());
            txtNamaAdmin.setText(get.getNama());
            txtAlamatAdmin.setText(get.getAlamat());
            txtTelpAdmin.setText(get.getTelp());
            txtUserNameAdmin.setText(get.getUsername());
            txtPwdAdmin.setText(get.getPassword());
            comboBagian.setSelectedItem(get.getBagian());
            oldId = get.getIdadministrator().toString();
            oldNama = get.getNama();
            oldAlamat = get.getAlamat();
            oldTelp = get.getTelp();
            oldUserName = get.getUsername();
            oldPassword = get.getPassword().toString();
            oldBagian=get.getBagian();
        } else {
            
            tabelAdmin.clearSelection();
            setNumber();
            txtNamaAdmin.setText("");
            txtAlamatAdmin.setText("");
            txtTelpAdmin.setText("");
            txtUserNameAdmin.setText("");
            txtPwdAdmin.setText("");
            
            btnDelete.requestFocus(false);
            btnUbah.requestFocus(false);
            btnSimpan.setEnabled(true);
            btnUbah.setEnabled(false);
            btnDelete.setEnabled(false);
        }
    }
}
