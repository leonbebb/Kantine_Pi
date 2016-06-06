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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 * NFCKontroller überprüft ob die karte gelesen werden kann und wenn nicht ob
 * vielleicht 2 karten auf dem lesegerät sind NFCKontroller kann die NFCKarten
 * lesen/schreiben/löschen
 *
 * @author John Bebbington
 */
public class NFCKontroller {

    public static final String STATUS_LESEN_OK = "LESEN OK";
    public static final String STATUS_SCAN_FEHLER = "SCAN FEHLER";
    public static final String STATUS_DATEN_FEHLER = "DATEN FEHLER";


    

    
    private static final String CMD_SCANCARD = "nfc-list";
    private static final String CMD_READCARD = "nfc-mfclassic r a ";
    private static final String CMD_WRITECARD = "nfc-mfclassic w a ";
    
    private static final String ALT_KARTEN_DATEN = "alt.mfd";
    private static final String NEU_KARTEN_DATEN = "neu.mfd";
    
    
    
    private static final int BLINK_DELAY = 250; // 1/4 Sekunde
    private int anzahl_karten;
    private long karten_id;
    private Keys keys;
    
    public NFCKontroller(String key_file) {
        keys = new Keys(key_file);
        DES3_Verschlüsselung.keys_laden(keys.getKey1(), keys.getKey2());
        
    }
    
    public boolean scancard() {
        boolean scan_ok = false;
        anzahl_karten = 0;
        karten_id = 0;
        
        try {
            ArrayList<String> antwort = pisystemcall(CMD_SCANCARD);
            
            for (String s : antwort) {
                
                if (s.contains("ISO14443A")) {
                    String anzahl = s.substring(0, s.indexOf("ISO14443A"));
                    anzahl = anzahl.trim();
                    anzahl_karten = Integer.parseInt(anzahl);
                }
                if (s.contains("UID (NFCID1):")) {
                    String id = s.substring(s.indexOf("UID (NFCID1):") + 13, s.length());
                    id = id.trim();
                    id = id.replaceAll(" ", "");
                    karten_id = Long.parseLong(id, 16);
                }
            }
            
            scan_ok = (anzahl_karten == 1 && karten_id != 0);
            
        } catch (IOException ex) {
            Logger.getLogger(NFCKontroller.class
                    .getName()).log(Level.SEVERE, null, ex);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(NFCKontroller.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return scan_ok;
        
    }
    
    public boolean writedata(String new_daten_file, String old_daten_file) {
        boolean write_ok = false;
        boolean write_done = false;
        long id_read = 0;
        
        try {
            ArrayList<String> antwort = pisystemcall(CMD_WRITECARD + new_daten_file + " " + old_daten_file);
            for (String s : antwort) {
                if (s.contains("Done, 63 of 64 blocks written.")) {
                    write_done = true;
                }
                
                if (s.contains("UID (NFCID1):")) {
                    String id = s.substring(s.indexOf("UID (NFCID1):") + 13, s.length());
                    id = id.trim();
                    id = id.replaceAll(" ", "");
                    id_read = Long.parseLong(id, 16);
                    
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(NFCKontroller.class
                    .getName()).log(Level.SEVERE, null, ex);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(NFCKontroller.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        
        write_ok = write_done && (id_read == this.karten_id);
        
        return write_ok;
    }
    
    public boolean readdata(String daten_file) {
        boolean read_ok = false;
        boolean read_done = false;
        boolean file_written = false;
        long id_read = 0;
        try {
            ArrayList<String> antwort = pisystemcall(CMD_READCARD + daten_file);
            for (String s : antwort) {
                
                if (s.contains("Done, 64 of 64 blocks read.")) {
                    read_done = true;
                }
                if (s.contains("Writing data to file:") && s.contains("Done.")) {
                    file_written = true;
                }
                if (s.contains("UID (NFCID1):")) {
                    String id = s.substring(s.indexOf("UID (NFCID1):") + 13, s.length());
                    id = id.trim();
                    id = id.replaceAll(" ", "");
                    id_read = Long.parseLong(id, 16);
                }
            }
            
            read_ok = read_done && file_written && (id_read == this.karten_id);
            
        } catch (IOException ex) {
            Logger.getLogger(NFCKontroller.class
                    .getName()).log(Level.SEVERE, null, ex);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(NFCKontroller.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        
        return read_ok;
    }
    
    private ArrayList<String> pisystemcall(String cmd) throws IOException, InterruptedException {
        
        ArrayList<String> antwort = new ArrayList<String>();
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

    /**
     * @return the anzahl_karten
     */
    public int getAnzahl_karten() {
        return anzahl_karten;
    }

    /**
     * @return the karten_id
     */
    public long getKarten_id() {
        return karten_id;
    }
    
    public static void main(String[] args) throws InterruptedException {
        
        NFCKontroller nfc = new NFCKontroller(args[0]);
        
        NFCVorgang readEncryptedCard = nfc.readEncryptedCard();
        
        readEncryptedCard.getKd().setGuthaben(37.05);
        
        nfc.writeEncryptedCard(readEncryptedCard.getKd());
    }
    
    public void clearCard() {
        
        boolean ok = scancard();
        ok = readdata(ALT_KARTEN_DATEN);
        
        MiFare_1KDaten kartendaten = new MiFare_1KDaten(new File(ALT_KARTEN_DATEN));
        byte[] gus = kartendaten.getUsableSpace();
        byte zero = 0;
        Arrays.fill(gus, zero);
        kartendaten.setUsableSpace(gus);
        kartendaten.writeMiFare_1KDaten(new File(NEU_KARTEN_DATEN));
        
        ok = writedata(NEU_KARTEN_DATEN, ALT_KARTEN_DATEN);
        
    }
    
    public void writeCard() {
        
        boolean ok = scancard();
        ok = readdata(ALT_KARTEN_DATEN);
        
        MiFare_1KDaten kartendaten = new MiFare_1KDaten(new File(ALT_KARTEN_DATEN));
        
        byte[] gus = kartendaten.getUsableSpace();
        for (int i = 0; i < gus.length; i++) {
            gus[i] = (byte) (i % 100);
        }
        
        kartendaten.setUsableSpace(gus);
        kartendaten.writeMiFare_1KDaten(new File(NEU_KARTEN_DATEN));
        ok = writedata(NEU_KARTEN_DATEN, ALT_KARTEN_DATEN);
    }
    
    public KartenDaten writeEncryptedCard(KartenDaten kd) {
        
        boolean ok = scancard();
        
        if (!ok) {
            return null; // keine karte gefunden.
        }
        
        ok = readdata(ALT_KARTEN_DATEN);
        
        if (!ok) {
            return null; // keine karte gefunden.
        }
        MiFare_1KDaten kartendaten = new MiFare_1KDaten(new File(ALT_KARTEN_DATEN));
        
        long id = getKarten_id();
        
        if (id != kd.getKunden_daten().getId()) {
            return null; // kann nicht daten an Karte mit andere ID.
        }
        
        LocalDateTime ts = LocalDateTime.now();
        kd.setDatum_zuletzt_beschrieben(ts);
        
        String encoded = DES3_Verschlüsselung.encode(kd.to_string());
        byte[] encoded_bytes = encoded.getBytes();
        byte[] encoded_byte_und_länge = new byte[encoded_bytes.length + 4];
        ByteBuffer bb = ByteBuffer.wrap(encoded_byte_und_länge);
        bb.putInt(encoded_bytes.length);
        bb.put(encoded_bytes);
        
        kartendaten.setUsableSpace(encoded_byte_und_länge);
        kartendaten.writeMiFare_1KDaten(new File(NEU_KARTEN_DATEN));
        ok = writedata(NEU_KARTEN_DATEN, ALT_KARTEN_DATEN);
        
        if (!ok) {
            return null;
        }
        
        return kd;
    }
    
    public NFCVorgang readEncryptedCard() {
        
        NFCVorgang vorgang = new NFCVorgang();
        
        boolean ok = scancard();
        if (!ok) {
            vorgang.setStatus(STATUS_SCAN_FEHLER);
            return vorgang; // keine karte gefunden.
        }
        ok = readdata(ALT_KARTEN_DATEN);
        if (!ok) {
            vorgang.setStatus(STATUS_DATEN_FEHLER);
            return vorgang; // keine karte gefunden.
        }
        
        long id = getKarten_id();
        
        MiFare_1KDaten kartendaten = new MiFare_1KDaten(new File(ALT_KARTEN_DATEN));
        
        byte[] encoded_byte_und_länge = kartendaten.getUsableSpace();
        ByteBuffer bb = ByteBuffer.wrap(encoded_byte_und_länge);
        bb.rewind();
        int data_length = bb.getInt();
        if (data_length > encoded_byte_und_länge.length) {
            vorgang.setStatus(STATUS_DATEN_FEHLER);
            return vorgang; // keine karte gefunden.
        }
        byte[] encoded_bytes = new byte[data_length];
        bb.get(encoded_bytes);
        String encoded_string = new String(encoded_bytes);
        
        String decoded_string = DES3_Verschlüsselung.decode(encoded_string);
        
        try {
        KartenDaten kd = new KartenDaten(decoded_string);
        vorgang.setStatus(STATUS_LESEN_OK);
        vorgang.setKd(kd);
        
        } catch (Exception e){
            vorgang.setStatus(STATUS_DATEN_FEHLER);            
        }
       
        return vorgang;
    }
    
    public void readCard() {
        boolean ok = scancard();
        ok = readdata(ALT_KARTEN_DATEN);
    }
    
}
