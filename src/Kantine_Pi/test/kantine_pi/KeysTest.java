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

import java.net.URL;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Leon Bebbington
 */
public class KeysTest {

    public KeysTest() {
    }

    URL url;

    @Before
    public void setUp() {
        url = this.getClass().getResource("Keys.txt");
        Assert.assertNotNull(url);
    }

    @Test
    public void testParseBytes() {

        String bytesString = "Key1={ 0x23, 0x08, 0x00, 0x02, 0x00, 0x7F, 0x00,\n"
                + "            0x00, 0x01, 0x01, 0x01, 0x01, 0x01, 0x1B, 0x01, 0x01, 0x02, 0x02,\n"
                + "            0x02, 0x02, 0x02, 0x02, 0x02, 0x02 }\n";

        byte[] expResult = {0x23, 0x08, 0x00, 0x02, 0x00, 0x7F, 0x00,
            0x00, 0x01, 0x01, 0x01, 0x01, 0x01, 0x1B, 0x01, 0x01, 0x02, 0x02,
            0x02, 0x02, 0x02, 0x02, 0x02, 0x02};

        byte[] result = Keys.parseKeyString(bytesString);

        assertArrayEquals(expResult, result);

    }

    /**
     * Test of getKey1 method, of class Keys.
     */
    @Test
    public void testGetKey1() {

        Keys keys = new Keys(url.getFile());

        byte[] expResult = {0x23, 0x08, 0x00, 0x02, 0x00, 0x7F, 0x00,
            0x00, 0x01, 0x01, 0x01, 0x01, 0x01, 0x1B, 0x01, 0x01, 0x02, 0x02,
            0x02, 0x02, 0x02, 0x02, 0x02, 0x02};

        byte[] result = keys.getKey1();
        assertArrayEquals(expResult, result);

    }

    /**
     * Test of getKey2 method, of class Keys.
     */
    @Test
    public void testGetKey2() {

        Keys keys = new Keys(url.getFile());

        byte[] expResult = {0x00, 0x0F, 0x11, 0x03, 0x56, 0x00, 0x00, 0x00};

        byte[] result = keys.getKey2();
        assertArrayEquals(expResult, result);
    }

}
