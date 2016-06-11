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

    int anzahlVonNummern;
    int nummer1;
    int nummer2;
    int nummer3;
    int artikelnummer;
    boolean hatArtikelnummer;
    boolean isKategorie;

    public ArtikelSelektor() {
        reset();
    }

    public void reset() {
        nummer1 = -1;
        nummer2 = -1;
        nummer3 = -1;
        anzahlVonNummern = 0;
        hatArtikelnummer = false;
        isKategorie = false;
    }

    public void enterZiffer(int i) {

        if (anzahlVonNummern < 3) {
            if (anzahlVonNummern == 0) {
                nummer1 = i;
                hatArtikelnummer = false;
                isKategorie = true;
            } else if (anzahlVonNummern == 1) {
                nummer2 = i;
                hatArtikelnummer = false;
                isKategorie = false;
            } else if (anzahlVonNummern == 2) {
                nummer3 = i;
                hatArtikelnummer = true;
                isKategorie = false;
            }
            anzahlVonNummern += 1;
        } else {
            reset();
            nummer1 = i;
            anzahlVonNummern = 1;
            hatArtikelnummer = false;
            isKategorie = true;
        }

    }

    public boolean isKategorie() {
        return this.isKategorie;
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

}
