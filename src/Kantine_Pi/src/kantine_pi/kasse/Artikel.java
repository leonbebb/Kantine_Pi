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

import kantine_pi.Produkt;

/**
 *
 * @author Leon Bebbington
 */
public class Artikel extends Produkt {

    private int anzahl;
    private double summe;

    public Artikel(int nummer, String katagorie, String name, double preis) {
        super(nummer, katagorie, name, preis);
        this.anzahl = 1;
        this.summe = 0;
    }

    public Artikel(Produkt produkt) {
        super(produkt.getNummer(), produkt.getKatagorie(),produkt.getName(),produkt.getPreis());
        this.anzahl = 1;
        this.summe = 0;
    }
    
    /**
     * @return the Anzahl
     */
    public int getAnzahl() {
        return anzahl;
    }

    public void anzahlErhöhen() {
        this.setAnzahl(this.getAnzahl() + 1);

    }

    public void anzahlVerringern() {
        this.setAnzahl(this.getAnzahl() - 1);
    }

    /**
     * @param Anzahl the Anzahl to set
     */
    public void setAnzahl(int Anzahl) {
        this.anzahl = Anzahl;
    }

    /**
     * @return the summe
     */
    public double getSumme() {
        this.summe = this.anzahl * this.getPreis();
        return this.summe;
    }

}
