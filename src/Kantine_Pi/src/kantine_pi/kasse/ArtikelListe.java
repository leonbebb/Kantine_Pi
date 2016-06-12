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

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/**
 *
 * @author John Bebbington
 */
class ArtikelListe {

    Deque<Artikel> stapel;

    ArtikelListe() {
        stapel = new ArrayDeque<Artikel>();
    }

    void artikelAnzahlErhöhen() {
        if (!stapel.isEmpty()) {
            Artikel aktuellerArtikel = stapel.peek();
            aktuellerArtikel.anzahlErhöhen();
        }
    }

    void artikelAnzahlVerringern() {
        if (!stapel.isEmpty()) {
            Artikel aktuellerArtikel = stapel.peek();
            if (aktuellerArtikel.getAnzahl() == 0) {
                stapel.pop();
            } else {
                aktuellerArtikel.anzahlVerringern();
            }
        }
    }

    void reset() {
        stapel.clear();
    }

    void addArtikel(Artikel artikel) {
        stapel.push(artikel);
    }

    Artikel[] getArtikels() {
        Artikel[] al = new Artikel[stapel.size()];
        stapel.toArray(al);
        return al;
    }

    double getGesamtSumme() {
        double gs= 0.0;

        Iterator<Artikel> itr = stapel.iterator();
        while (itr.hasNext()) {
            Artikel a = itr.next();
            gs += a.getSumme();
        }
        return gs;
    }

}
