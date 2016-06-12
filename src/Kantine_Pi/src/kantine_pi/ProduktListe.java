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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ProduktListe liest einen .txt file welcher durch kommas getrennte Wörter
 * beinhaltet und konvertiert ihn zu einem array von class Produkt
 *
 * @author John Bebbington
 */
public class ProduktListe {

    private ArrayList<Produkt> produkt_liste;
    private ArrayList<Katagorie> katagorie_liste;

    public ProduktListe(String preislist_file) {
        leseProdukteAusDatei(preislist_file);
        katagorieListeBauen();
    }

    private void leseProdukteAusDatei(String preislist_file) {
        produkt_liste = new ArrayList<Produkt>();

        try {
            List<String> lines = Files.readAllLines(Paths.get(preislist_file), StandardCharsets.UTF_16);

            for (String line : lines) {
                if (line.startsWith("#")) {
                    continue;
                }
                String[] newproduct = line.trim().split(",");
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

    private void katagorieListeBauen() {

        katagorie_liste = new ArrayList<Katagorie>();

        for (Produkt p : produkt_liste) {

            String kat_number = String.valueOf(p.getNummer() / 100); // erste ziffer in produkt nummer

            boolean gefunden = false;
            for (Katagorie k : katagorie_liste) {
                if (k.getName().equalsIgnoreCase(p.getKatagorie())) {
                    gefunden = true;
                    break;
                }
            }

            if (!gefunden) {
                Katagorie k = new Katagorie(kat_number, p.getKatagorie());
                katagorie_liste.add(k);
            }
        }
    }

    /**
     *
     * @param produkt_nummer eindeutige Produktnummer
     * @return ausgewähltes Produkt oder null wenn nicht gefunden.
     */
    public Produkt getProdukt(int produkt_nummer) {
    
        Produkt gesuchte_product = null;
        
        for (Produkt p : produkt_liste) {
            if (p.getNummer() == produkt_nummer) {
                gesuchte_product =  p;
            }
        }
        return gesuchte_product;
    }

    public ArrayList<Katagorie> getKatagorien() {
        return katagorie_liste;
    }

    public ArrayList<Produkt> getProdukteAusKatagorie(int kategorienummer) {
        ArrayList<Produkt> produkte = new ArrayList<Produkt>();

        for (Produkt p : produkt_liste) {
            if (p.getNummer()/100 == kategorienummer) {
                produkte.add(p);
            }
        }
        
        return produkte;
    }

}
