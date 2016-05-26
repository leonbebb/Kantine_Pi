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

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *LEDKontroller steuert 3 LEDs und verwendet ein boolean um den zustand des Lesevorgangs auszugeben
 * @author Leon Bebbington
 */
public class LEDKontroller {

    private boolean bereit;          // blau LED
    private boolean gelesen_ok;     // grün LED
    private boolean lese_error;     // rot LED

    /**
     * @return the bereit
     */
    public boolean isBereit() {
        return bereit;
    }

    /**
     * @param bereit the bereit to set
     */
    public void setBereit(boolean bereit) {
        this.bereit = bereit;
    }

    /**
     * @return the gelesen_ok
     */
    public boolean isGelesen_ok() {
        return gelesen_ok;
    }

    /**
     * @param gelesen_ok the gelesen_ok to set
     */
    public void setGelesen_ok(boolean gelesen_ok) {
        this.gelesen_ok = gelesen_ok;
    }

    /**
     * @return the lese_error
     */
    public boolean isLese_error() {
        return lese_error;
    }

    /**
     * @param lese_error the lese_error to set
     */
    public void setLese_error(boolean lese_error) {
        this.lese_error = lese_error;
    }

    private Vector<String> pisystemcall(String cmd) throws IOException, InterruptedException {

        Vector<String> antwort = new Vector<String>();
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

    public static void main(String[] args) throws InterruptedException {
        
        LEDKontroller lc = new LEDKontroller();
        
        try {
            lc.pisystemcall("sudo gpio -g mode 4 out");
            
            lc.pisystemcall("sudo gpio -g write 4 1");
            
            // keep program running until user aborts (CTRL-C)
            for (;;) {
                lc.pisystemcall("sudo gpio -g write 4 0");
                Thread.sleep(500);
                lc.pisystemcall("sudo gpio -g write 4 1");
                Thread.sleep(500);
    //            System.out.println("toggle 4"); 
                        
                        }
            
//
//        // START SNIPPET: usage-create-controller-snippet
//        // create gpio controller instance
//        final GpioController gpio = GpioFactory.getInstance();
//        // END SNIPPET: usage-create-controller-snippet
//
//       
//        // START SNIPPET: usage-provision-output-pin-snippet
//        // provision gpio pins #04 as an output pin and make sure is is set to LOW at startup
//        GpioPinDigitalOutput myLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04,   // PIN NUMBER
//                                                                   "My LED",           // PIN FRIENDLY NAME (optional)
//                                                                   PinState.LOW);      // PIN STARTUP STATE (optional)
//        // END SNIPPET: usage-provision-output-pin-snippet
//
//        // START SNIPPET: usage-shutdown-pin-snippet
//        // configure the pin shutdown behavior; these settings will be 
//        // automatically applied to the pin when the application is terminated
//        // ensure that the LED is turned OFF when the application is shutdown
//        myLed.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
//        // END SNIPPET: usage-shutdown-pin-snippet
//
//    
//        // use convenience wrapper method to set state on the pin object
//        myLed.low();
//        myLed.high();
//
//        // use toggle method to apply inverse state on the pin object
//        myLed.toggle();
//
//        // use pulse method to set the pin to the HIGH state for
//        // an explicit length of time in milliseconds
//        myLed.pulse(1000);
//        // END SNIPPET: usage-control-pin-snippet
//
//      
//        // keep program running until user aborts (CTRL-C)
//        for (;;) {
//                    myLed.toggle();
//                    System.out.println("toggle 4");
//
//            Thread.sleep(500);
//        }
//        
//        // stop all GPIO activity/threads by shutting down the GPIO controller
//        // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
//        // gpio.shutdown();   <--- implement this method call if you wish to terminate the Pi4J GPIO controller                
        } catch (IOException ex) {
            Logger.getLogger(LEDKontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
