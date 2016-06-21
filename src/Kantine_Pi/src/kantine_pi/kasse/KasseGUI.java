/*
 *  
 * Copyright 2016 Leon Bebbington Licensed under the * 	
 * 	Educational Community License, Version 2.0 (the "License"); you may * 	
 * 	not use this file except in compliance with the License. You may * 	
 * 	obtain a copy of the License at * 	
 * 	 * 	
 * 	http://www.osedu.org/licenses/ECL-2.0 * 	
 * 
 * 	Unless required by applicable law or agreed to in writing, * 	
 * 	software distributed under the License is distributed on an "AS IS" * 	
 * 	BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express * 	
 * 	or implied. See the License for the specific language governing * 	
 * 	permissions and limitations under the License. * 	
 */
package kantine_pi.kasse;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import static javax.swing.Action.ACTION_COMMAND_KEY;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import kantine_pi.Katagorie;
import kantine_pi.Produkt;

/**
 *
 * @author Leon Bebbington
 */
public class KasseGUI extends javax.swing.JPanel {

    private NumberFormat formatter = new DecimalFormat("#0.00 €");
    private NumberFormat produktformat = new DecimalFormat("000");

    private final KasseModell model;

    private class KeyAction extends AbstractAction {

        public KeyAction(String actionCommand) {
            putValue(ACTION_COMMAND_KEY, actionCommand);
        }

        @Override
        public void actionPerformed(ActionEvent actionEvt) {
            if (model != null) {
                model.sendKeyPress(actionEvt.getActionCommand());
            }
        }
    }

    private void setKeyBindings() {
        ActionMap actionMap = getActionMap();
        int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
        InputMap inputMap = getInputMap(condition);

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), model.vkBackspace);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), model.vkEscape);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), model.vkEnter);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), model.vkF2);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), model.vkF12);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, 0), model.vkMinus);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, 0), model.vkPlus);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ADD, 0), model.vkPlus);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT, 0), model.vkMinus);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_0, 0), model.vk0);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0), model.vk1);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_2, 0), model.vk2);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_3, 0), model.vk3);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_4, 0), model.vk4);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_5, 0), model.vk5);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_6, 0), model.vk6);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_7, 0), model.vk7);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_8, 0), model.vk8);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_9, 0), model.vk9);

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD0, 0), model.vk0);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD1, 0), model.vk1);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD2, 0), model.vk2);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD3, 0), model.vk3);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD4, 0), model.vk4);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD5, 0), model.vk5);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD6, 0), model.vk6);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD7, 0), model.vk7);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD8, 0), model.vk8);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD9, 0), model.vk9);

        actionMap.put(model.vkBackspace, new KeyAction(model.vkBackspace));
        actionMap.put(model.vkEscape, new KeyAction(model.vkEscape));
        actionMap.put(model.vkEnter, new KeyAction(model.vkEnter));
        actionMap.put(model.vkF2, new KeyAction(model.vkF2));
        actionMap.put(model.vkF12, new KeyAction(model.vkF12));
        actionMap.put(model.vkMinus, new KeyAction(model.vkMinus));
        actionMap.put(model.vkPlus, new KeyAction(model.vkPlus));

        actionMap.put(model.vk0, new KeyAction(model.vk0));
        actionMap.put(model.vk1, new KeyAction(model.vk1));
        actionMap.put(model.vk2, new KeyAction(model.vk2));
        actionMap.put(model.vk3, new KeyAction(model.vk3));
        actionMap.put(model.vk4, new KeyAction(model.vk4));
        actionMap.put(model.vk5, new KeyAction(model.vk5));
        actionMap.put(model.vk6, new KeyAction(model.vk6));
        actionMap.put(model.vk7, new KeyAction(model.vk7));
        actionMap.put(model.vk8, new KeyAction(model.vk8));
        actionMap.put(model.vk9, new KeyAction(model.vk9));

    }

    /**
     * Creates new form KasseGUI
     *
     * @param model KasseModell
     */
    public KasseGUI(KasseModell model) {
        this.model = model;
        initComponents();
        setKeyBindings();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel_Guthaben = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel_Einkaufssumme = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel_Artikelnummer = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Preislist = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_ArtikelListe = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel_Esc = new javax.swing.JLabel();
        jLabel_Kaufen = new javax.swing.JLabel();
        jLabel_Plus = new javax.swing.JLabel();
        jLabel_Minus = new javax.swing.JLabel();
        jLabel_Ziffern = new javax.swing.JLabel();
        jLabel_F12 = new javax.swing.JLabel();
        jLabel_F2 = new javax.swing.JLabel();
        jLabel_Löschen = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        Logo = new javax.swing.JLabel();
        jLabel_Status = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setToolTipText("");
        setMaximumSize(new java.awt.Dimension(1248, 1000));
        setMinimumSize(new java.awt.Dimension(1248, 1000));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1248, 1000));
        setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Guthaben", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel1.setName(""); // NOI18N
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel_Guthaben.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel_Guthaben.setText("00,00 €");
        jLabel_Guthaben.setMinimumSize(new java.awt.Dimension(130, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(jLabel_Guthaben, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(jPanel1, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Einkaufs Summe", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel2.setMaximumSize(new java.awt.Dimension(150, 120));
        jPanel2.setMinimumSize(new java.awt.Dimension(150, 120));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel_Einkaufssumme.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel_Einkaufssumme.setText("00,00 €");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel2.add(jLabel_Einkaufssumme, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(jPanel2, gridBagConstraints);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Artikelnummer", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel3.setFocusTraversalPolicyProvider(true);
        jPanel3.setFocusable(false);
        jPanel3.setName(""); // NOI18N
        jPanel3.setRequestFocusEnabled(false);
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel_Artikelnummer.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        jLabel_Artikelnummer.setText("000");
        jLabel_Artikelnummer.setToolTipText("");
        jPanel3.add(jLabel_Artikelnummer, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 76;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(jPanel3, gridBagConstraints);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Preisliste Manager", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel4.setToolTipText("");
        jPanel4.setMaximumSize(new java.awt.Dimension(250, 168));
        jPanel4.setMinimumSize(new java.awt.Dimension(250, 168));
        jPanel4.setName(""); // NOI18N
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        jTable_Preislist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Produkt", "Preis (€)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable_Preislist.setEnabled(false);
        jTable_Preislist.setFillsViewportHeight(true);
        jTable_Preislist.setFocusable(false);
        jScrollPane1.setViewportView(jTable_Preislist);

        jPanel4.add(jScrollPane1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(jPanel4, gridBagConstraints);
        jPanel4.getAccessibleContext().setAccessibleName("Preisliste Manager");
        jPanel4.getAccessibleContext().setAccessibleDescription("");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Artikel Liste", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel5.setMaximumSize(new java.awt.Dimension(285, 168));
        jPanel5.setMinimumSize(new java.awt.Dimension(250, 168));
        jPanel5.setLayout(new java.awt.GridLayout(1, 0));

        jTable_ArtikelListe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Produkt", "Preis (€)", "Anzahl", "Summe (€)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable_ArtikelListe.setFillsViewportHeight(true);
        jTable_ArtikelListe.setFocusable(false);
        jScrollPane2.setViewportView(jTable_ArtikelListe);

        jPanel5.add(jScrollPane2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(jPanel5, gridBagConstraints);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tastatur Legende", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel6.setToolTipText("");
        jPanel6.setMaximumSize(new java.awt.Dimension(555, 126));
        jPanel6.setMinimumSize(new java.awt.Dimension(250, 126));
        jPanel6.setName(""); // NOI18N
        jPanel6.setLayout(new java.awt.GridBagLayout());

        jLabel_Esc.setText("Esc=Abbrechen");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 36;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 2);
        jPanel6.add(jLabel_Esc, gridBagConstraints);

        jLabel_Kaufen.setText("Enter=Artikel in den Einkaufswagen");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 36;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 2);
        jPanel6.add(jLabel_Kaufen, gridBagConstraints);

        jLabel_Plus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Plus.setText("+=Artikel +1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 36;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 2);
        jPanel6.add(jLabel_Plus, gridBagConstraints);

        jLabel_Minus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Minus.setText("-=Artikel -1");
        jLabel_Minus.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 36;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 2);
        jPanel6.add(jLabel_Minus, gridBagConstraints);

        jLabel_Ziffern.setText("Ziffern(0...9)=Artikelnummer");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 36;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 2);
        jPanel6.add(jLabel_Ziffern, gridBagConstraints);

        jLabel_F12.setText("F12=Einkauf abschließen");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 36;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 2);
        jPanel6.add(jLabel_F12, gridBagConstraints);

        jLabel_F2.setText("F2=Karte lesen");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 36;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 2);
        jPanel6.add(jLabel_F2, gridBagConstraints);

        jLabel_Löschen.setText("Löschtaste= Zahl Löschen");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 36;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 2);
        jPanel6.add(jLabel_Löschen, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(jPanel6, gridBagConstraints);

        jPanel7.setLayout(new java.awt.GridBagLayout());

        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kantine_pi/kasse/Logo.png"))); // NOI18N
        Logo.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel7.add(Logo, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(jPanel7, gridBagConstraints);

        jLabel_Status.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_Status.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        add(jLabel_Status, gridBagConstraints);

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    public void setGuthaben(final String name) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                jLabel_Guthaben.setText(name);
            }
        });
    }

      public void setStatus(final String name) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                jLabel_Status.setText(name);
            }
        });
    }
    public void setArtikelnummer(final String name) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                jLabel_Artikelnummer.setText(name);
            }
        });
    }

    public void setEinkaufsSumme(final String name) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                jLabel_Einkaufssumme.setText(name);
            }
        });
    }

    public void setProdukteAusCatagorie(final ArrayList<Produkt> produkte) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                String spalte1 = "Produkt";
                if (produkte != null && produkte.size() > 0) {
                    if (produkte.get(0).getKatagorie() != null) {
                        spalte1 = produkte.get(0).getKatagorie();
                    }
                }

                DefaultTableModel nm = new javax.swing.table.DefaultTableModel(
                        new String[]{"#", spalte1, "Preis (€)"}, 0);

                for (Produkt p : produkte) {
                    if (p != null) {
                        Object[] row = new Object[]{
                            produktformat.format(p.getNummer()),
                            p.getName(),
                            formatter.format(p.getPreis())
                        };

                        nm.addRow(row);
                    }
                }

                DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
                rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

                jTable_Preislist.setModel(nm);
                jTable_Preislist.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                jTable_Preislist.getColumnModel().getColumn(0).setPreferredWidth(30);
                jTable_Preislist.getColumnModel().getColumn(1).setPreferredWidth(360);
                jTable_Preislist.getColumnModel().getColumn(2).setPreferredWidth(60);
                jTable_Preislist.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
                jTable_Preislist.updateUI();
            }

        });
    }

    public void setProduktCatagories(final ArrayList<Katagorie> kats) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                DefaultTableModel nm = new javax.swing.table.DefaultTableModel(
                        new String[]{"#", "Produkt Katagorie"}, 0);

                for (Katagorie k : kats) {
                    if (k != null) {
                        Object[] row = new Object[]{
                            new Integer(k.getNummer()),
                            k.getName()};

                        nm.addRow(row);
                    }

                }

                DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
                rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

                jTable_Preislist.setModel(nm);
                jTable_Preislist.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                jTable_Preislist.getColumnModel().getColumn(0).setPreferredWidth(30);
                jTable_Preislist.getColumnModel().getColumn(1).setPreferredWidth(230);

                jTable_Preislist.updateUI();
            }

        });
    }

    public void setArtikleListe(final Artikel[] list) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                DefaultTableModel nm = new javax.swing.table.DefaultTableModel(
                        new String[]{"#", "Produkt", "Preis (€)", "Anzahl", "Summe (€)"}, 0);

                for (Artikel a : list) {
                    if (a != null) {
                        Object[] row = new Object[]{
                            produktformat.format(a.getNummer()),
                            a.getName(),
                            formatter.format(a.getPreis()),
                            new Integer(a.getAnzahl()),
                            formatter.format(a.getSumme())};

                        nm.addRow(row);
                    }

                }

                DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
                rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

                jTable_ArtikelListe.setModel(nm);
                jTable_ArtikelListe.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                jTable_ArtikelListe.getColumnModel().getColumn(0).setPreferredWidth(30);
                jTable_ArtikelListe.getColumnModel().getColumn(1).setPreferredWidth(235);
                jTable_ArtikelListe.getColumnModel().getColumn(2).setPreferredWidth(60);
                jTable_ArtikelListe.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
                jTable_ArtikelListe.getColumnModel().getColumn(3).setPreferredWidth(50);
                jTable_ArtikelListe.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
                jTable_ArtikelListe.getColumnModel().getColumn(4).setPreferredWidth(75);
                jTable_ArtikelListe.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);

                jTable_ArtikelListe.updateUI();
            }

        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(final String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            javax.swing.UIManager.LookAndFeelInfo[] installedLookAndFeels = javax.swing.UIManager.getInstalledLookAndFeels();
            for (int idx = 0; idx < installedLookAndFeels.length; idx++) {
                if ("Nimbus".equals(installedLookAndFeels[idx].getName())) {
                    javax.swing.UIManager.setLookAndFeel(installedLookAndFeels[idx].getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(KasseGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KasseGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KasseGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KasseGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                javax.swing.JFrame frame = new javax.swing.JFrame("Kasse");
                frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                KasseModell model = new KasseModell(args[0], args[1]);
                KasseGUI gui = new KasseGUI(model);
                model.setGUI(gui);
                frame.getContentPane().add(gui);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Logo;
    private javax.swing.JLabel jLabel_Artikelnummer;
    private javax.swing.JLabel jLabel_Einkaufssumme;
    private javax.swing.JLabel jLabel_Esc;
    private javax.swing.JLabel jLabel_F12;
    private javax.swing.JLabel jLabel_F2;
    private javax.swing.JLabel jLabel_Guthaben;
    private javax.swing.JLabel jLabel_Kaufen;
    private javax.swing.JLabel jLabel_Löschen;
    private javax.swing.JLabel jLabel_Minus;
    private javax.swing.JLabel jLabel_Plus;
    private javax.swing.JLabel jLabel_Status;
    private javax.swing.JLabel jLabel_Ziffern;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable_ArtikelListe;
    private javax.swing.JTable jTable_Preislist;
    // End of variables declaration//GEN-END:variables
}
