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
import java.net.URL;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author John Bebbington
 */
public class MiFare_1KDatenTest {
    
    public MiFare_1KDatenTest() {
    }
    
    URL url;

    @Before
    public void setUp() {
        url = this.getClass().getResource("empty.mfd");
        Assert.assertNotNull(url);
    }
    
   

    /**
     * Test of getKarteID method, of class MiFare_1KDaten.
     */
    @Test
    public void testGetKarteID() {
        System.out.println("getKarteID");
        MiFare_1KDaten instance = new MiFare_1KDaten(new File(url.getFile()));
        long expResult = 0xFCBFB430;
        long result = instance.getKarteID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUsableSpace method, of class MiFare_1KDaten.
     */
    @Test
    public void testGetUsableSpace() {
        System.out.println("getUsableSpace");
        MiFare_1KDaten instance = new MiFare_1KDaten(new File(url.getFile()));
        byte[] result = instance.getUsableSpace();
        assertEquals(720, result.length);
    }

    /**
     * Test of setUsableSpace method, of class MiFare_1KDaten.
     */
    @Test
    public void testSetUsableSpace1() {
        System.out.println("setUsableSpace1");
       
        byte[] user_daten = new byte[720]; 
        MiFare_1KDaten instance = new MiFare_1KDaten(new File(url.getFile()));
        instance.setUsableSpace(user_daten);
 
        byte[] result = instance.getUsableSpace();
        assertEquals(720, result.length);
        assertArrayEquals(user_daten, result);
        
    }
    
      /**
     * Test of setUsableSpace method, of class MiFare_1KDaten.
     */
    @Test
    public void testSetUsableSpace2() {
        System.out.println("setUsableSpace2");
       
        String s1 = "Hallo Test 123456789";
        byte[] user_daten = s1.getBytes(); 
        
        MiFare_1KDaten instance = new MiFare_1KDaten(new File(url.getFile()));
        instance.setUsableSpace(user_daten);
 
        byte[] result = instance.getUsableSpace();
        assertEquals(720, result.length);
        
        String s2 = new String(user_daten);
        assertEquals(s1, s2);
        
    }
    
    
}
