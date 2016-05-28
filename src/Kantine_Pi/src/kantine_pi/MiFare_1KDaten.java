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
package kantine_pi;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leon Bebbington
 */
public class MiFare_1KDaten {
        private byte[] daten_von_karte;
        
        /** 
         * Laut speicher plan, {von,bis} sind user space.
         */
         private int[][] user_daten_anfang_ende  = {
             {64,111},
             {128,175},
             {192,239},
             {256,303},
             {320,367},
             {384,413},
             {448,495},
             {512,559},
             {576,623},
             {640,687},
             {704,751},
             {768,815},
             {832,879},
             {896,943},
             {960,1007}
         };
         
        
         
        
        MiFare_1KDaten(File daten_file){

            try {
                daten_von_karte = Files.readAllBytes(daten_file.toPath());
            } catch (IOException ex) {
                Logger.getLogger(MiFare_1KDaten.class.getName()).log(Level.SEVERE, null, ex);
            }

            
        }


        
        public byte[] getUsableSpace(){
            byte[] user_memory = null;
            
            for (int[] von_bis : user_daten_anfang_ende) {
                
            }
            
            return user_memory;
            
        }
        
        public void setUsableSpace(byte[] user_daten){
            
        }
        
        
}
