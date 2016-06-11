package kantine_pi;

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

import java.net.URL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Leon Bebbington
 */
public class ProduktListeTest {

    URL url;
    ProduktListe produkte;

    public ProduktListeTest() {
    }

    @Before
    public void setUp() {
        url = this.getClass().getResource("Preisliste.txt");
        Assert.assertNotNull(url);
        produkte = new ProduktListe(url.getFile());
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getProdukt method, of class ProduktListe.
     */
    @Test
    public void testGetProdukt1() {
        int produkt_nummer = 1;
        Produkt expResult = new Produkt(1, "Süßigkeiten", "Duplo", 0.75);

        Produkt result = produkte.getProdukt(produkt_nummer);
        assertNotNull(result);
        boolean ok = result.isEqual(expResult);
        assertTrue(ok);
    }

    /**
     * Test of getProdukt method, of class ProduktListe.
     */
    @Test
    public void testGetProdukt100() {
        int produkt_nummer = 100;
        Produkt expResult = new Produkt(100, "Laugengebäck", "Brezel oder Knoten", 0.7);

        Produkt result = produkte.getProdukt(produkt_nummer);
        assertNotNull(result);
        boolean ok = result.isEqual(expResult);
        assertTrue(ok);

    }

    /**
     * Test of getProdukt method, of class ProduktListe.
     */
    @Test
    public void testGetProduktUnbekannt() {
        int produkt_nummer = 0;
        Produkt expResult = new Produkt(0, "Laugengebäck", "Brezel oder Knoten", 0.7);

        Produkt result = produkte.getProdukt(produkt_nummer);
        assertNull(result);

    }

}
