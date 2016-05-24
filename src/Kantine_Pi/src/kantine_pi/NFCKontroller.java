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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TODO dont forget me
 *
 * @author Leon Bebbington
 */
public class NFCKontroller {

    private static final String GELESEN = "Karte gelesen okay";
    private static final String NICHTLESBAR = "Karte nicht lesbar";
    private static final String ZUVIELEKARTEN = "Mehr als eine Karte gelesen";

    private static final String CMD_SCANCARD = "NFC scan";//TODO richtige pi command finden

    public String scancard() {
        try {
            pisystemcall(CMD_SCANCARD);
        } catch (IOException ex) {
            Logger.getLogger(NFCKontroller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(NFCKontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public boolean writedata(String d) {
        return false;
    }

    public String readdata() {
        return null;
    }

    public boolean deletedata() {
        return false;
    }

    private Vector<String> pisystemcall(String cmd) throws IOException, InterruptedException {

        Vector<String> antwort = new Vector<String>();
        Runtime r = Runtime.getRuntime();
        Process p = r.exec(cmd);
        p.waitFor();
        BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = b.readLine()) != null) {
            antwort.add(line);
        }

        b.close();
        return antwort;
    }
}
