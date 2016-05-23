package kantine_pi.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import kantine_pi.Produkt;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Leon Bebbington, John Bebbington
 */
public class ProductTest {

    public ProductTest() {
    }

    @Test
    public void test_product_1() {

        Produkt p1 = new Produkt(1, "a", "b", 1.23);
        assertNotNull(p1);
    
        assertEquals(1, p1.getNummer());
        assertEquals(1.23, p1.getPreis(),0.0);  
        assertEquals("a", p1.getKatagorie());
        assertEquals("b", p1.getName());
        
    }
    
    
    @Test
    public void test_split_1() {

        String pl = "1,Süßigkeiten,Duplo,0.75";
        
        String[] spalten = pl.split(",");
        
        
        assertNotNull(spalten);
        assertEquals(4, spalten.length);
        assertEquals("1", spalten[0]);  
        assertEquals("Süßigkeiten", spalten[1]);  
        assertEquals("Duplo", spalten[2]);  
        assertEquals("0.75", spalten[3]);  
 
                   
      String s =  "145";
      String s2 = "234";
      
      String s3 = "" + (Integer.parseInt(s) + Integer.parseInt(s2));
      
      assertEquals("379", s3);  
              

      
      int i = Integer.parseInt(s);
        
    }
    
      @Test
    public void test_string_integer_addition() {

      String s1 = "145"; // nummer als String
      String s2 = "234"; // nummer als String
      
      String s3 = "" + (Integer.parseInt(s1) + Integer.parseInt(s2)); // addition von 2 Integer dann implicit String konversion.
      assertEquals("379", s3);        
      int i = Integer.parseInt(s3);
      assertEquals(379, i);    
      
      String s4 = s1 + s2; // addition von 2 Strings bedeutet Konkatination (kette).
      assertEquals("145234", s4);        
      i = Integer.parseInt(s4);
      assertEquals(145234, i);    
   
    }
    
    
}
