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
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 * LEDKontroller steuert 3 LEDs und verwendet ein boolean um den zustand des
 * Lesevorgangs auszugeben
 *
 * @author Leon Bebbington, John Bebbington
 */
public class LEDKontroller {

    final static String LED_BLAU_GPIO_PIN = "4";
    final static String LED_GRUEN_GPIO_PIN = "17";
    final static String LED_ROT_GPIO_PIN = "22";

    final static boolean AN = true;
    final static boolean AUS = false;

    private boolean bereit;          // blau LED
    private boolean gelesen_ok;     // grün LED
    private boolean lese_error;     // rot LED

    public LeserLEDTimer    leser_blinker;
    public SchreiberLEDTimer schreiber_blinker;

    private static LEDKontroller instance = null;

    public class LeserLEDTimer implements ActionListener {

        private boolean toggle;
        private final Timer blinker;

        LeserLEDTimer(int delay) {
            this.toggle = false;
            blinker = new Timer(delay, this);
            blinker.setRepeats(true);
            blinker.setInitialDelay(0);
            blinker.setCoalesce(true);
        }

        public void start() {
            blinker.start();
        }

        public void stop() {
            blinker.stop();
        }

        public void actionPerformed(ActionEvent e) {
            toggle = !toggle;
            LEDKontroller.getInstance().setGelesen_ok(toggle);
        }

    }

    public class SchreiberLEDTimer implements ActionListener {

        private boolean toggle;
        private final Timer blinker;

        SchreiberLEDTimer(int delay) {
            this.toggle = false;
            blinker = new Timer(delay, this);
        }

        public void start() {
            blinker.start();
        }

        public void stop() {
            blinker.stop();
        }

        public void actionPerformed(ActionEvent e) {
            toggle = !toggle;
            LEDKontroller.getInstance().setLese_error(toggle);
        }

    }

    private LEDKontroller() {

        bereit = false;
        gelesen_ok = false;
        lese_error = false;

        pisystemcall(cmd_initializierLED(LED_BLAU_GPIO_PIN));
        pisystemcall(cmd_initializierLED(LED_GRUEN_GPIO_PIN));
        pisystemcall(cmd_initializierLED(LED_ROT_GPIO_PIN));

        // alles aus
        pisystemcall(cmd_LED(LED_BLAU_GPIO_PIN, AUS));
        pisystemcall(cmd_LED(LED_GRUEN_GPIO_PIN, AUS));
        pisystemcall(cmd_LED(LED_ROT_GPIO_PIN, AUS));

        // bau die blnker timers.
        leser_blinker = new LeserLEDTimer(250);
        schreiber_blinker = new SchreiberLEDTimer(250);
    }

    public static LEDKontroller getInstance() {
        if (instance == null) {
            instance = new LEDKontroller();
        }
        return instance;
    }

    protected void finalize() {
        // alles aus
        pisystemcall(cmd_LED(LED_BLAU_GPIO_PIN, AUS));
        pisystemcall(cmd_LED(LED_GRUEN_GPIO_PIN, AUS));
        pisystemcall(cmd_LED(LED_ROT_GPIO_PIN, AUS));
    }

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
        pisystemcall(cmd_LED(LED_BLAU_GPIO_PIN, this.bereit));
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
        pisystemcall(cmd_LED(LED_GRUEN_GPIO_PIN, this.gelesen_ok));
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
        pisystemcall(cmd_LED(LED_ROT_GPIO_PIN, this.lese_error));
    }

    private String cmd_initializierLED(String gpio_pin) {
        return "sudo gpio -g mode " + gpio_pin + " out";
    }

    private String cmd_LED(String gpio_pin, boolean an) {
        String cmd = "sudo gpio -g write " + gpio_pin;
        return an ? cmd + " 1" : cmd + " 0";
    }

    private ArrayList<String> pisystemcall(String cmd) {

        ArrayList<String> antwort = new ArrayList<String>();

        try {
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(cmd);
            p.waitFor();

        } catch (IOException ex) {
            Logger.getLogger(LEDKontroller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(LEDKontroller.class.getName()).log(Level.SEVERE, null, ex);
        }

        return antwort;
    }

    public static void main(String[] args) throws InterruptedException {

        LEDKontroller lc = new LEDKontroller();

        boolean toggle = false;
        for (;;) {
            toggle = !toggle;
            lc.pisystemcall(lc.cmd_LED(LED_BLAU_GPIO_PIN, toggle));
            lc.pisystemcall(lc.cmd_LED(LED_GRUEN_GPIO_PIN, toggle));
            lc.pisystemcall(lc.cmd_LED(LED_ROT_GPIO_PIN, toggle));
            Thread.sleep(5);

        }
    }

}
