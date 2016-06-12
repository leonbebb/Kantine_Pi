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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Leon Bebbington
 */
public class ArtikelSelektorTest {

    public ArtikelSelektorTest() {
    }

    /**
     * Test of reset method, of class ArtikelSelektor.
     */
    @Test
    public void testReset() {
        ArtikelSelektor instance = new ArtikelSelektor();
        instance.enterZiffer(1);
        assertEquals("1__", instance.getArtikelelnummerStr());
        instance.reset();
        assertEquals("___", instance.getArtikelelnummerStr());
    }

    /**
     * Test of isKategorie method, of class ArtikelSelektor.
     */
    @Test
    public void testIsKategorie() {
        ArtikelSelektor instance = new ArtikelSelektor();
        boolean result = instance.isKategorie();
        assertEquals(false, result);
        instance.enterZiffer(5);
        result = instance.isKategorie();
        assertEquals(true, result);
        instance.enterZiffer(4);
        result = instance.isKategorie();
        assertEquals(false, result);
        instance.enterZiffer(3);
        result = instance.isKategorie();
        assertEquals(false, result);
        instance.enterZiffer(2);
        result = instance.isKategorie();
        assertEquals(true, result);

    }

    /**
     * Test of hatArtikelnummer method, of class ArtikelSelektor.
     */
    @Test
    public void testHatArtikelnummer() {
        ArtikelSelektor instance = new ArtikelSelektor();
        boolean result = instance.hatArtikelnummer();
        assertEquals(false, result);
        
        instance.enterZiffer(5);
        result = instance.hatArtikelnummer();
        assertEquals(false, result);
        
        instance.enterZiffer(4);
        result = instance.hatArtikelnummer();
        assertEquals(false, result);
        
        instance.enterZiffer(3);
        result = instance.hatArtikelnummer();
        assertEquals(true, result);
        
        instance.enterZiffer(2);
        result = instance.hatArtikelnummer();
        assertEquals(false, result);
        
    
    }

    /**
     * Test of getArtikelelnummer method, of class ArtikelSelektor.
     */
    @Test
    public void testGetArtikelelnummer() {
        ArtikelSelektor instance = new ArtikelSelektor();
        instance.enterZiffer(1);
        instance.enterZiffer(2);
        instance.enterZiffer(3);

        int expResult = 123;
        int result = instance.getArtikelelnummer();
        assertEquals(expResult, result);
    }

    /**
     * Test of getKategorienummer method, of class ArtikelSelektor.
     */
    @Test
    public void testGetKategorienummer() {
        System.out.println("getKategorienummer");
        ArtikelSelektor instance = new ArtikelSelektor();
        instance.enterZiffer(5);
        instance.enterZiffer(4);
        instance.enterZiffer(3);
        int expResult = 5;
        int result = instance.getKategorienummer();
        assertEquals(expResult, result);
    }

}
