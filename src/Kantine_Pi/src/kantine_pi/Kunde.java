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
 *Bei dieser Class wird einer Karten ID, der Name und die Klasse eines Schülers zugeschrieben 
 * @author Leon Bebbington
 */
public class Kunde {

    private long kartenID;
    private String name;
    private String klasse;

    public Kunde(long id, String name, String klasse) {
        this.kartenID = id;
        this.name = name;
        this.klasse = klasse;
    }

    /**
     * @return the id
     */
    public long getId() {
        return kartenID;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.kartenID = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the klasse
     */
    public String getKlasse() {
        return klasse;
    }

    /**
     * @param klasse the klasse to set
     */
    public void setKlasse(String klasse) {
        this.klasse = klasse;
    }

}
