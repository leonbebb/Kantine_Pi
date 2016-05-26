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

/**
 *Dem Produkt wird eine Produkt nummer, die Kategorie, der Name und der Preis zugeordnet
 * @author Leon Bebbington
 */
public class Produkt {

    private int nummer;
    private String katagorie;
    private String name;
    private double preis;

    public Produkt(int nummer, String katagorie, String name, double preis) {
        this.nummer = nummer;
        this.katagorie = katagorie;
        this.name = name;
        this.preis = preis;
    }

    /**
     * @return the nummer
     */
    public int getNummer() {
        return nummer;
    }

    /**
     * @return the katagorie
     */
    public String getKatagorie() {
        return katagorie;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the preis
     */
    public double getPreis() {
        return preis;
    }

    public boolean isEqual(Produkt andere) {
        return this.nummer == andere.nummer
                && this.katagorie.equalsIgnoreCase(andere.getKatagorie())
                && this.name.equalsIgnoreCase(andere.getName())
                && this.preis == andere.getPreis();
    }

}
