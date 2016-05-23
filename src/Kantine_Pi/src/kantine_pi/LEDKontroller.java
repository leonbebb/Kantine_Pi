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

/**
 *
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
    
}
