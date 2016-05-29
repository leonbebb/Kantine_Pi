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
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author Leon Bebbington
 */
public class KartenTest {

    public KartenTest() {
    }

    private URL url;

    @Before
    public void setUp() {
        url = this.getClass().getResource("Keys.txt");
        Assert.assertNotNull(url);
        Keys keys = new Keys(url.getFile());
        DES3_Verschlüsselung.keys_laden(keys.getKey1(), keys.getKey2());

    }

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void KartenWrite() {

        URL kartenurl = this.getClass().getResource("empty.mfd");
        Assert.assertNotNull(kartenurl);
        MiFare_1KDaten kartendaten = new MiFare_1KDaten(new File(kartenurl.getFile()));

        long id = kartendaten.getKarteID();
        Kunde k = new Kunde(id, "Leon Bebbington", "25A");
        KartenDaten kd = new KartenDaten(k, 25.54, new Date(), new Date());

        String encoded = DES3_Verschlüsselung.encode(kd.to_string());
        byte[] encoded_bytes = encoded.getBytes();
        byte[] encoded_byte_und_länge = new byte[encoded_bytes.length + 4];
        ByteBuffer bb = ByteBuffer.wrap(encoded_byte_und_länge);
        bb.putInt(encoded_bytes.length);
        bb.put(encoded_bytes);

        kartendaten.setUsableSpace(encoded_byte_und_länge);

        try {
            // Create a temporary file.
            final File tempFile = tempFolder.newFile("new_karten_daten.mfd");
            kartendaten.writeMiFare_1KDaten(tempFile);

            MiFare_1KDaten new_kartendaten = new MiFare_1KDaten(tempFile);
            byte[] encrpted_data_und_länge = new_kartendaten.getUsableSpace();
            ByteBuffer edbb = ByteBuffer.wrap(encrpted_data_und_länge);
            int length = edbb.getInt();

            byte[] encrpyted_bytes = new byte[length];
            edbb.get(encrpyted_bytes, 0, length);
            String encrypted_string = new String(encrpyted_bytes);

            String decoded = DES3_Verschlüsselung.decode(encrypted_string);

            KartenDaten nkd = new KartenDaten(decoded);

        } catch (IOException ex) {
            Logger.getLogger(KartenTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Test
    public void KartenRead() {

        URL kartenurl = this.getClass().getResource("encrypted.mfd");
        Assert.assertNotNull(kartenurl);
        MiFare_1KDaten kartendaten = new MiFare_1KDaten(new File(kartenurl.getFile()));

        byte[] encrpted_data_und_länge = kartendaten.getUsableSpace();
        ByteBuffer edbb = ByteBuffer.wrap(encrpted_data_und_länge);
        int length = edbb.getInt();

        byte[] encrpyted_bytes = new byte[length];
        edbb.get(encrpyted_bytes, 0, length);
        String encrypted_string = new String(encrpyted_bytes);

        String decoded = DES3_Verschlüsselung.decode(encrypted_string);

        KartenDaten nkd = new KartenDaten(decoded);

    }

}
