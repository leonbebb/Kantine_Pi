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

import java.util.Date;

/**
 *
 * @author Leon Bebbington
 */
public class KartenDaten {

    private Kunde kunden_daten;
    private double guthaben;
    private Date datum_initializiert;
    private Date datum_zuletzt_beschrieben;

    public KartenDaten(Kunde kunde_daten, double guthaben, Date initializiert, Date zuletzt_beschrieben) {
        this.kunden_daten = kunden_daten;
        this.guthaben = guthaben;
        this.datum_initializiert = initializiert;
        this.datum_zuletzt_beschrieben = datum_zuletzt_beschrieben;

    }

    /**
     * @return the kunden_daten
     */
    public Kunde getKunden_daten() {
        return kunden_daten;
    }

    /**
     * @param kunden_daten the kunden_daten to set
     */
    public void setKunden_daten(Kunde kunden_daten) {
        this.kunden_daten = kunden_daten;
    }

    /**
     * @return the guthaben
     */
    public double getGuthaben() {
        return guthaben;
    }

    /**
     * @param guthaben the guthaben to set
     */
    public void setGuthaben(double guthaben) {
        this.guthaben = guthaben;
    }

    /**
     * @return the datum_initializiert
     */
    public Date getDatum_initializiert() {
        return datum_initializiert;
    }

    /**
     * @param datum_initializiert the datum_initializiert to set
     */
    public void setDatum_initializiert(Date datum_initializiert) {
        this.datum_initializiert = datum_initializiert;
    }

    /**
     * @return the datum_zuletzt_beschrieben
     */
    public Date getDatum_zuletzt_beschrieben() {
        return datum_zuletzt_beschrieben;
    }

    /**
     * @param datum_zuletzt_beschrieben the datum_zuletzt_beschrieben to set
     */
    public void setDatum_zuletzt_beschrieben(Date datum_zuletzt_beschrieben) {
        this.datum_zuletzt_beschrieben = datum_zuletzt_beschrieben;
    }
}
