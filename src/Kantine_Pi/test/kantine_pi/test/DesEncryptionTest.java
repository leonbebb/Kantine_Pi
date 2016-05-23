package kantine_pi.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import kantine_pi.DES3_Verschlüsselung;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author John Bebbington
 */
public class DesEncryptionTest {
    
  
     @Test
     public void test_encryption1() {
         String encoded = DES3_Verschlüsselung.encode("this is a test");
         String decoded = DES3_Verschlüsselung.decode(encoded);
         assertEquals("this is a test",decoded);
     }
     
     
     @Test
     public void test_encryption2() {
         
         String chip_data = "ID=123456789,Name=Leon Bebbington,Class=25A,Account_Balance=24.56,Date=15.05.2016";
         
         System.out.println("Chip Data==> " + chip_data);

         String encoded = DES3_Verschlüsselung.encode(chip_data);
         
         System.out.println("Encoded==> " + encoded);
              
         String decoded = DES3_Verschlüsselung.decode(encoded);
         
         System.out.println("Decoded==> " + decoded);
           
         assertEquals(81,chip_data.length());
         assertEquals(152,encoded.length());
         
         assertEquals(chip_data,decoded);
     }
     
}
