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

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author John Bebbington
 */
public class MiFare_1KDaten {

    private byte[] karten_daten;

    private final static int SEKTOR_USER_SPACE = 48; // bytes. 
    private final static int GESAMT_USER_SPACE = SEKTOR_USER_SPACE * 15; // 720 bytes. 
    /**
     * Laut speicher plan, {von,bis} sind user space.
     */
    private int[][] user_daten_anfang_ende = {
        {64, 111},
        {128, 175},
        {192, 239},
        {256, 303},
        {320, 367},
        {384, 413},
        {448, 495},
        {512, 559},
        {576, 623},
        {640, 687},
        {704, 751},
        {768, 815},
        {832, 879},
        {896, 943},
        {960, 1007}
    };

    MiFare_1KDaten(File daten_file) {
        try {
            karten_daten = Files.readAllBytes(daten_file.toPath());
        } catch (IOException ex) {
            Logger.getLogger(MiFare_1KDaten.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @return 4byte Integer für Karte ID
     */
    public long getKarteID() {
        byte[] arr = Arrays.copyOfRange(karten_daten, 0, 4);
        ByteBuffer bb = ByteBuffer.wrap(arr);
        return bb.getInt();
    }

    public byte[] getUsableSpace() {
        byte[] sektor_user_memory = new byte[SEKTOR_USER_SPACE];

        byte[] gesamt_user_memory = new byte[GESAMT_USER_SPACE];
        ByteBuffer bb = ByteBuffer.wrap(gesamt_user_memory);

        for (int[] von_bis : user_daten_anfang_ende) {
            sektor_user_memory = Arrays.copyOfRange(karten_daten, von_bis[0], von_bis[1] + 1);
            bb.put(sektor_user_memory);
        }

        return bb.array();

    }

    public void setUsableSpace(byte[] user_daten) {

        ByteBuffer bb = ByteBuffer.wrap(user_daten);
        ByteBuffer kb = ByteBuffer.wrap(karten_daten);

        for (int[] von_bis : user_daten_anfang_ende) {
            
            byte[] sektor_user_memory = new byte[SEKTOR_USER_SPACE];
            
            if (bb.remaining()>= SEKTOR_USER_SPACE) bb.get(sektor_user_memory);
            else bb.get(sektor_user_memory,0,bb.remaining());
            
            kb.position(von_bis[0]);
            kb.put(sektor_user_memory);
        }

    }

    public void writeMiFare_1KDaten(File daten_file) {
        try {
            Files.write(daten_file.toPath(), karten_daten);
        } catch (IOException ex) {
            Logger.getLogger(MiFare_1KDaten.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
