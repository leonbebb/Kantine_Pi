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

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import static javax.swing.Action.ACTION_COMMAND_KEY;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import kantine_pi.Katagorie;

/**
 *
 * @author Leon Bebbington
 */
public class KasseGUI extends javax.swing.JPanel {

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

        String vkEscape = "VK_ESCAPE";
        String vkEnter = "VK_ENTER";

        String vkF2 = "VK_F2";
        String vkF12 = "VK_F12";
        String vkMinus = "VK_MINUS";
        String vkPlus = "VK_PLUS";

        String vk0 = "VK_0";
        String vk1 = "VK_1";
        String vk2 = "VK_2";
        String vk3 = "VK_3";
        String vk4 = "VK_4";
        String vk5 = "VK_5";
        String vk6 = "VK_6";
        String vk7 = "VK_7";
        String vk8 = "VK_8";
        String vk9 = "VK_9";

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), vkEscape);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), vkEnter);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), vkF2);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), vkF12);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, 0), vkMinus);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, 0), vkPlus);

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ADD, 0), vkPlus);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT, 0), vkMinus);

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_0, 0), vk0);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0), vk1);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_2, 0), vk2);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_3, 0), vk3);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_4, 0), vk4);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_5, 0), vk5);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_6, 0), vk6);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_7, 0), vk7);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_8, 0), vk8);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_9, 0), vk9);

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD0, 0), vk0);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD1, 0), vk1);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD2, 0), vk2);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD3, 0), vk3);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD4, 0), vk4);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD5, 0), vk5);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD6, 0), vk6);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD7, 0), vk7);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD8, 0), vk8);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD9, 0), vk9);

        actionMap.put(vkEscape, new KeyAction(vkEscape));
        actionMap.put(vkEnter, new KeyAction(vkEnter));
        actionMap.put(vkF2, new KeyAction(vkF2));
        actionMap.put(vkF12, new KeyAction(vkF12));
        actionMap.put(vkMinus, new KeyAction(vkMinus));
        actionMap.put(vkPlus, new KeyAction(vkPlus));

        actionMap.put(vk0, new KeyAction(vk0));
        actionMap.put(vk1, new KeyAction(vk1));
        actionMap.put(vk2, new KeyAction(vk2));
        actionMap.put(vk3, new KeyAction(vk3));
        actionMap.put(vk4, new KeyAction(vk4));
        actionMap.put(vk5, new KeyAction(vk5));
        actionMap.put(vk6, new KeyAction(vk6));
        actionMap.put(vk7, new KeyAction(vk7));
        actionMap.put(vk8, new KeyAction(vk8));
        actionMap.put(vk9, new KeyAction(vk9));

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
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setMaximumSize(new java.awt.Dimension(1248, 1000));
        setMinimumSize(new java.awt.Dimension(1248, 1000));
        setPreferredSize(new java.awt.Dimension(1248, 1000));
        setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Guthaben", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel1.setName(""); // NOI18N
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel_Guthaben.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
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

        jLabel_Einkaufssumme.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
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

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Preislist Manager", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel4.setMaximumSize(new java.awt.Dimension(250, 168));
        jPanel4.setMinimumSize(new java.awt.Dimension(250, 168));
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        jTable_Preislist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Integer(100), "bla",  new Double(1.02)},
                { new Integer(101), "ble",  new Double(2.03)},
                { new Integer(102), "bli",  new Double(2.0)},
                { new Integer(103), "a",  new Double(3.0)},
                { new Integer(104), "s",  new Double(4.0)},
                { new Integer(201), "b",  new Double(5.0)},
                { new Integer(202), "g",  new Double(6.0)},
                { new Integer(203), "d",  new Double(7.0)},
                { new Integer(305), "s",  new Double(8.0)},
                { new Integer(306), null, null},
                { new Integer(307), null, null},
                { new Integer(308), null, null},
                { new Integer(309), null, null},
                { new Integer(400), null, null},
                { new Integer(410), null, null},
                { new Integer(411), null, null},
                { new Integer(412), null, null},
                { new Integer(500), null, null},
                { new Integer(501), null, null},
                { new Integer(502), null, null},
                { new Integer(602), null, null},
                { new Integer(603), null, null},
                { new Integer(604), null, null},
                { new Integer(605), null, null},
                {null, null, null}
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

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Artikel Liste", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel5.setMaximumSize(new java.awt.Dimension(285, 168));
        jPanel5.setMinimumSize(new java.awt.Dimension(250, 168));
        jPanel5.setLayout(new java.awt.GridLayout(1, 0));

        jTable_ArtikelListe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Integer(101), "ble",  new Double(2.03),  new Integer(3),  new Double(6.09)},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
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

        jLabel4.setText("Abrechen");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.6;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel6.add(jLabel4, gridBagConstraints);

        jLabel5.setText("Kauf bestätigen");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel6.add(jLabel5, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("+");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel6.add(jLabel6, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 26)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("-");
        jLabel7.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel6.add(jLabel7, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(jPanel6, gridBagConstraints);

        jPanel7.setLayout(new java.awt.GridBagLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kantine_pi/kasse/Logo.png"))); // NOI18N
        jLabel1.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel7.add(jLabel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(jPanel7, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    public void setGuthaben(final String name) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                jLabel_Guthaben.setText(name);
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

    public void setProduktCatagories(final Katagorie[] name) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //            jLabel_Guthaben.setText(name);
            }
        });
    }

//      public void setProduktCatagories(final Katagorie[] name) {
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                
////                DefaultTableModel tm = new javax.swing.table.DefaultTableModel(
////            new Object [][] {
////                { new Integer(100), "bla",  new Double(1.02)},
////                { new Integer(101), "ble",  new Double(2.03)},
////                { new Integer(102), "bli",  new Double(2.0)},
////                { new Integer(103), "a",  new Double(3.0)},
////                { new Integer(104), "s",  new Double(4.0)},
////                { new Integer(201), "b",  new Double(5.0)},
////                { new Integer(202), "g",  new Double(6.0)},
////                { new Integer(203), "d",  new Double(7.0)},
////                { new Integer(305), "s",  new Double(8.0)},
////                { new Integer(306), null, null},
////                { new Integer(307), null, null},
////                { new Integer(308), null, null},
////                { new Integer(309), null, null},
////                { new Integer(400), null, null},
////                { new Integer(410), null, null},
////                { new Integer(411), null, null},
////                { new Integer(412), null, null},
////                { new Integer(500), null, null},
////                { new Integer(501), null, null},
////                { new Integer(502), null, null},
////                { new Integer(602), null, null},
////                { new Integer(603), null, null},
////                { new Integer(604), null, null},
////                { new Integer(605), null, null},
////                {null, null, null}
////            },
////            new String [] {
////                "#", "Produkt", "Preis (€)"
////            }
////        ) {
////            Class[] types = new Class [] {
////                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class
////            };
////
////            public Class getColumnClass(int columnIndex) {
////                return types [columnIndex];
////            }
////        });                
////            }
////        });
////                
////                
//// jTable_Preislist.setModel(new javax.swing.table.DefaultTableModel(
////            new Object [][] {
////                { new Integer(100), "bla",  new Double(1.02)},
////                { new Integer(101), "ble",  new Double(2.03)},
////                { new Integer(102), "bli",  new Double(2.0)},
////                { new Integer(103), "a",  new Double(3.0)},
////                { new Integer(104), "s",  new Double(4.0)},
////                { new Integer(201), "b",  new Double(5.0)},
////                { new Integer(202), "g",  new Double(6.0)},
////                { new Integer(203), "d",  new Double(7.0)},
////                { new Integer(305), "s",  new Double(8.0)},
////                { new Integer(306), null, null},
////                { new Integer(307), null, null},
////                { new Integer(308), null, null},
////                { new Integer(309), null, null},
////                { new Integer(400), null, null},
////                { new Integer(410), null, null},
////                { new Integer(411), null, null},
////                { new Integer(412), null, null},
////                { new Integer(500), null, null},
////                { new Integer(501), null, null},
////                { new Integer(502), null, null},
////                { new Integer(602), null, null},
////                { new Integer(603), null, null},
////                { new Integer(604), null, null},
////                { new Integer(605), null, null},
////                {null, null, null}
////            },
////            new String [] {
////                "#", "Produkt", "Preis (€)"
////            }
////        ) {
////            Class[] types = new Class [] {
////                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class
////            };
////
////            public Class getColumnClass(int columnIndex) {
////                return types [columnIndex];
////            }
////        });                
////            }
////        });
//    }
//        }
//      }
//     
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel_Artikelnummer;
    private javax.swing.JLabel jLabel_Einkaufssumme;
    private javax.swing.JLabel jLabel_Guthaben;
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
