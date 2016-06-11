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
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of reset method, of class ArtikelSelektor.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        ArtikelSelektor instance = new ArtikelSelektor();
        instance.reset();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isKategorie method, of class ArtikelSelektor.
     */
    @Test
    public void testIsKategorie() {
        System.out.println("isKategorie");
        ArtikelSelektor instance = new ArtikelSelektor();
        boolean expResult = false;
        boolean result = instance.isKategorie();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hatArtikelnummer method, of class ArtikelSelektor.
     */
    @Test
    public void testHatArtikelnummer() {
        System.out.println("hatArtikelnummer");
        ArtikelSelektor instance = new ArtikelSelektor();
        boolean expResult = false;
        boolean result = instance.hatArtikelnummer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArtikelelnummer method, of class ArtikelSelektor.
     */
    @Test
    public void testGetArtikelelnummer() {
        System.out.println("getArtikelelnummer");
        ArtikelSelektor instance = new ArtikelSelektor();
        int expResult = 0;
        int result = instance.getArtikelelnummer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getKategorienummer method, of class ArtikelSelektor.
     */
    @Test
    public void testGetKategorienummer() {
        System.out.println("getKategorienummer");
        ArtikelSelektor instance = new ArtikelSelektor();
        int expResult = 0;
        int result = instance.getKategorienummer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
