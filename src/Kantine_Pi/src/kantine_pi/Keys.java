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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Keys nehmen files und benutzen 2 keys um sie zu entschlüsseln (z.B. ID ,Name, Klasse von NFC Karte)
 * @author Leon Bebbington
 */
public class Keys {

    private byte[] key1;
    private byte[] key2;

    public Keys(String key_file) {

        try {
            List<String> lines = Files.readAllLines(Paths.get(key_file), StandardCharsets.UTF_16);

            for (String line : lines) {
                if (line.startsWith("Key1")) {
                    key1 = parseKeyString(line);
                }
                if (line.startsWith("Key2")) {
                    key2 = parseKeyString(line);
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(Keys.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static byte[] parseKeyString(String ps) {
        byte[] keydaten = null;

        if (ps.startsWith("Key")) {
            String daten = ps.substring(ps.indexOf("{") + 1, ps.indexOf("}"));
            String[] bytesString = daten.split(",");

            keydaten = new byte[bytesString.length];
            int i = 0;
            for (String bs : bytesString) {
                bs = bs.replaceAll("0x", "").trim();

                byte b = Byte.parseByte(bs, 16);
                keydaten[i++] = b;
            }

        }

        return keydaten;
    }

    /**
     * @return the key1
     */
    public byte[] getKey1() {
        return key1;
    }

    /**
     * @return the key2
     */
    public byte[] getKey2() {
        return key2;
    }

}
