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

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leon Bebbington
 */
public class ProduktListe {

    private final static String PRODUKT_LISTE_FILENAME = "\\test\\kantine_pi\\test\\Preisliste.csv";

    private ArrayList<Produkt> produkt_liste;

    public ProduktListe() {
        leseProdukteAusDatei();
    }

    private void leseProdukteAusDatei() {
        produkt_liste = new ArrayList<Produkt>();
        
        try {
            List<String> lines = Files.readAllLines(Paths.get(PRODUKT_LISTE_FILENAME), Charset.defaultCharset());

            for (String line : lines) {

                String[] newproduct = line.split(",");
                try {
                    Produkt p1 = new Produkt(Integer.parseInt(newproduct[0]), newproduct[1], newproduct[2], Double.parseDouble(newproduct[3]));
                    produkt_liste.add(p1);

                } catch (Exception e) {
                    Logger.getLogger(ProduktListe.class.getName()).log(Level.SEVERE, null, e);
                }
        }

        } catch (IOException ex) {
            Logger.getLogger(ProduktListe.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
  
    /**
     * 
     * @param produkt_nummer eindeutige Produktnummer
     * @return ausgewähltes Produkt oder null wenn nicht gefunden.
     */
    public Produkt getProdukt(int produkt_nummer){
        Produkt ausgewähltes = produkt_liste.get(produkt_nummer);
        return ausgewähltes;
    }
}
