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
 * TODO beschreibung
 *
 * @author Leon Bebbington
 */
public class AufladeModell {

    private static final double MAXGUTHABEN_EURO = 70.00;
    private static final double MAXAUFLADUNG_EURO = 50.00;
    private double aufladebetrag = 0.00;

    public void betragaufladen(int betrag_in_euro) {
        System.out.println("betragaufladen : " + betrag_in_euro);
        aufladebetrag = aufladebetrag + betrag_in_euro;
        if (aufladebetrag >= MAXAUFLADUNG_EURO) {
            aufladebetrag = MAXAUFLADUNG_EURO;

        }
        System.out.println("aufladebetrag : " + aufladebetrag);

    }

    public void aufladen_stornieren() {
        aufladebetrag = 0.0;
    }
}
