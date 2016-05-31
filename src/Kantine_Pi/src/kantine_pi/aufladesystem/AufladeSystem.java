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
package kantine_pi.aufladesystem;

/**
 *
 * @author Leon Bebbington
 */
public class AufladeSystem {

     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Falsche anzahl Programme Argumente : (sollwert) AuflageSystem key_file ");
        } else {
            StartApplication(args[0]);
        }
    }
    
    
    private static void StartApplication(String key_file){
           /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            private AufladeSystemGUI gui;
            public void run() {
                
                javax.swing.JFrame frame = new javax.swing.JFrame("AufladeSystemGUI");
                frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                
        
                AufladeModell model = new AufladeModell();
                gui = new AufladeSystemGUI(model);
                model.setGUI(gui);
                
                
                frame.getContentPane().add(gui);
                frame.pack();
                frame.setVisible(true);
            }
        });
        
        
    }
    
}
