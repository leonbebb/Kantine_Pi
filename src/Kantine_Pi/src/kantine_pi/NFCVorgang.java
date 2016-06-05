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
public class NFCVorgang {
        private String status;
        private KartenDaten kd ;
 
        NFCVorgang(){
            this.status = "kein Status";
            this.kd = kd;
        }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @return the kd
     */
    public KartenDaten getKd() {
        return kd;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @param kd the kd to set
     */
    public void setKd(KartenDaten kd) {
        this.kd = kd;
    }
        
        
}
