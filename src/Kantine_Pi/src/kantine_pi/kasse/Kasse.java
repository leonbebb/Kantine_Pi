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

import javax.swing.JFrame;
import kantine_pi.LEDKontroller;

/**
 *
 * @author Leon Bebbington
 */
public class Kasse {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Falsche anzahl Programme Argumente : (sollwert) Kasse preislist_file key_file ");
        } else {
            StartApplication(args[0],args[1]);
        }
    }
    
    
    private static void StartApplication(final String preislist_file, final String key_file){
              /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            private KasseGUI gui;
            public void run() {
                
                javax.swing.JFrame frame = new javax.swing.JFrame("Kasse, V1.00");
                frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                
                KasseModell model = new KasseModell(key_file,preislist_file);
                gui = new KasseGUI(model);
                model.setGUI(gui);
                
                frame.getContentPane().add(gui);
                frame.pack();
                frame.setVisible(true);
                frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

                
                LEDKontroller.getInstance().setBereit(true);
            }
        });
    }

}
