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
package kantine_pi.kasse;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import kantine_pi.KartenDaten;
import kantine_pi.LEDKontroller;
import kantine_pi.NFCKontroller;
import kantine_pi.NFCVorgang;
import kantine_pi.Produkt;
import kantine_pi.ProduktListe;
import kantine_pi.aufladesystem.AufladeModell;

/**
 * TODO Beschreibung
 *
 * @author Leon Bebbington
 */
public class KasseModell implements Runnable {

    private KasseGUI gui;
    private ArtikelSelektor artikelselektor;
    private ArtikelListe artikelliste;
    private boolean alive = true;
    private Thread worker = new Thread(this);
    private ArrayBlockingQueue<String> commandQ;
    private KartenDaten aktuelle_kartendaten;
    private NFCVorgang karten_vorgang;
    private NumberFormat formatter = new DecimalFormat("#0.00 €");
    private final NFCKontroller nfc;
    private final LEDKontroller leds;
    private final ProduktListe produktliste;

    String vkEscape = "VK_ESCAPE";
    String vkEnter = "VK_ENTER";
    String vkF2 = "VK_F2";
    String vkF12 = "VK_F12";
    String vkMinus = "VK_MINUS";
    String vkPlus = "VK_PLUS";
    String vk0 = "0";
    String vk1 = "1";
    String vk2 = "2";
    String vk3 = "3";
    String vk4 = "4";
    String vk5 = "5";
    String vk6 = "6";
    String vk7 = "7";
    String vk8 = "8";
    String vk9 = "9";

    KasseModell(String keys, String preisliste) {
        artikelselektor = new ArtikelSelektor();
        artikelliste = new ArtikelListe();
        nfc = new NFCKontroller(keys);
        produktliste = new ProduktListe(preisliste);
        leds = LEDKontroller.getInstance();
        commandQ = new ArrayBlockingQueue<String>(20);
        worker.start();
    }

    public void sendKeyPress(String key) {
        try {
            commandQ.put(key);
        } catch (InterruptedException ex) {
            Logger.getLogger(AufladeModell.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void setGUI(KasseGUI gui) {
        this.gui = gui;
    }

    private void kartenLesenAufgabe() {

        leds.setGelesen_ok(true);
        leds.setLese_error(false);
        System.out.println("karte lesen");
        leds.leser_blinker.start();

        karten_vorgang = nfc.readEncryptedCard();

        leds.leser_blinker.stop();

        //     this.gui.setStatus(karten_vorgang.getStatus());
        if (karten_vorgang.getStatus().equalsIgnoreCase(NFCKontroller.STATUS_LESEN_OK)) {

            leds.setGelesen_ok(true);
            leds.setLese_error(false);

            aktuelle_kartendaten = karten_vorgang.getKd();

            if (this.gui != null) {
                this.gui.setGuthaben(formatter.format(aktuelle_kartendaten.getGuthaben()));
            }

        } else if (karten_vorgang.getStatus().equalsIgnoreCase(NFCKontroller.STATUS_SCAN_FEHLER)) {
            leds.setGelesen_ok(false);
            leds.setLese_error(true);

            if (this.gui != null) {
                this.gui.setGuthaben("--.-- €");
            }

        } else if (karten_vorgang.getStatus().equalsIgnoreCase(NFCKontroller.STATUS_DATEN_FEHLER)) {
            leds.setGelesen_ok(false);
            leds.setLese_error(true);

            if (this.gui != null) {
                this.gui.setGuthaben("--.-- €");
            }

        }

    }

    private void handleEsc(String key) {
        if (key.equalsIgnoreCase(vkEscape)) {
            artikelselektor.reset();
            artikelliste.reset();
            if (gui != null) {
                aktuelle_kartendaten = null;
                this.gui.setGuthaben("--.-- €");
                this.gui.setArtikelnummer(artikelselektor.getArtikelelnummerStr());
            }
        }
    }

    private void handleEnter(String key) {
        if (key.equalsIgnoreCase(vkEnter)) {
            if (artikelselektor.hatArtikelnummer()) {
                int artikelNummer;
                artikelNummer = artikelselektor.getArtikelelnummer();
                Produkt p = produktliste.getProdukt(artikelNummer);
                if (p != null) {
                    Artikel artikel = new Artikel(p);
                    artikelliste.addArtikel(artikel);
                    artikelselektor.reset();
                } else {
                    //status meldung kein Artikel da  
                }
            }
        }
    }

    private void handleF12(String key) {
        if (key.equalsIgnoreCase(vkF12)) {

        }
    }

    private void handleF2(String key) {
        if (key.equalsIgnoreCase(vkF2)) {
            kartenLesenAufgabe();
        }
    }

    private void handlePlusMinus(String key) {
        if (key.equalsIgnoreCase(vkPlus)) {
            artikelliste.artikelAnzahlErhöhen();
        } else if (key.equalsIgnoreCase(vkMinus)) {
            artikelliste.artikelAnzahlVerringern();
        }
    }

    private void handleZiffern(String key) {

        if (key.equalsIgnoreCase(vk0)
                || key.equalsIgnoreCase(vk1)
                || key.equalsIgnoreCase(vk2)
                || key.equalsIgnoreCase(vk3)
                || key.equalsIgnoreCase(vk4)
                || key.equalsIgnoreCase(vk5)
                || key.equalsIgnoreCase(vk6)
                || key.equalsIgnoreCase(vk7)
                || key.equalsIgnoreCase(vk8)
                || key.equalsIgnoreCase(vk9)) {

            int ziff = Integer.parseInt(key);
            artikelselektor.enterZiffer(ziff);

            if (gui != null) {
                gui.setArtikelnummer(artikelselektor.getArtikelelnummerStr());
            }

            System.out.println("handleZiffern :" + key);
        }
    }

    public void run() {
        while (alive) {
            try {
                String command = commandQ.take();

                handleZiffern(command);
                handleEsc(command);
                handleEnter(command);
                handlePlusMinus(command);
                handleF2(command);
                handleF12(command);
                
                if (this.gui != null){
                    this.gui.setArtikleListe(artikelliste.getArtikels());
                    this.gui.setEinkaufsSumme(
                            formatter.format(artikelliste.getGesamtSumme()));
                }

            } catch (InterruptedException ex) {
                Logger.getLogger(AufladeModell.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
}
