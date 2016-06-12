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

/**
 *
 * @author Leon Bebbington
 */
public class ArtikelSelektor {

    private int anzahlVonNummern;
    private int nummer1;
    private int nummer2;
    private int nummer3;
    private int artikelnummer;
    private boolean hatArtikelnummer;
    private boolean isKategorie;

    public ArtikelSelektor() {
        reset();
    }

    public void reset() {
        nummer1 = -1;
        nummer2 = -1;
        nummer3 = -1;
        anzahlVonNummern = 0;
        hatArtikelnummer = false;
        setIsKategorie(false);
    }

    public void enterZiffer(int i) {

        if (getAnzahlVonNummern() < 3) {
            if (getAnzahlVonNummern() == 0) {
                nummer1 = i;
                hatArtikelnummer = false;
                setIsKategorie(true);
            } else if (getAnzahlVonNummern() == 1) {
                nummer2 = i;
                hatArtikelnummer = false;
                setIsKategorie(false);
            } else if (getAnzahlVonNummern() == 2) {
                nummer3 = i;
                hatArtikelnummer = true;
                setIsKategorie(false);
            }
            anzahlVonNummern += 1;
        } else {
            reset();
            nummer1 = i;
            anzahlVonNummern = 1;
            hatArtikelnummer = false;
            setIsKategorie(true);
        }

    }

    public boolean isKategorie() {
        return this.isIsKategorie();
    }

    public boolean hatArtikelnummer() {
        return this.hatArtikelnummer;
    }

    public int getArtikelelnummer() {
        return (nummer3 + nummer2 * 10 + nummer1 * 100);
    }

    public String getArtikelelnummerStr() {
        String ans = "";
        ans += (nummer1 < 0) ? "_" : "" + nummer1;
        ans += (nummer2 < 0) ? "_" : "" + nummer2;
        ans += (nummer3 < 0) ? "_" : "" + nummer3;

        return ans;
    }

    public int getKategorienummer() {
        return this.nummer1;
    }

    /**
     * @return the anzahlVonNummern
     */
    public int getAnzahlVonNummern() {
        return anzahlVonNummern;
    }

    /**
     * @return the isKategorie
     */
    public boolean isIsKategorie() {
        return isKategorie;
    }

    /**
     * @param isKategorie the isKategorie to set
     */
    public void setIsKategorie(boolean isKategorie) {
        this.isKategorie = isKategorie;
    }

    void deleteZiffer() {
         if (getAnzahlVonNummern() > 0) {
            anzahlVonNummern -= 1;
                         
            if (getAnzahlVonNummern() == 0) {
                nummer1 = -1;
                hatArtikelnummer = false;
                setIsKategorie(true);
            } else if (getAnzahlVonNummern() == 1) {
                nummer2 = -1;
                hatArtikelnummer = false;
                setIsKategorie(false);
            } else if (getAnzahlVonNummern() == 2) {
                nummer3 = -1;
                hatArtikelnummer = true;
                setIsKategorie(false);
            }
        } 

    }

}
