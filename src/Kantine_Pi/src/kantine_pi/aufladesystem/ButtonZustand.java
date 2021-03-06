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
class ButtonZustand {

    public boolean KarteLesenEnabled;
    public boolean AufladeGruppeEnabled;
    public boolean AuszahlenEnabled;
    public boolean KarteLöschenEnabled;
    public boolean BearbeitenEnabled;
    public boolean VorgangAbbrechenEnabled;
    public boolean BearbeitenAktiv;

    public ButtonZustand() {
        KarteLesenEnabled = true;
        AufladeGruppeEnabled = false;
        AuszahlenEnabled = false;
        KarteLöschenEnabled = false;
        BearbeitenEnabled = false;
        VorgangAbbrechenEnabled = false;
        BearbeitenAktiv = false;
    }

}
