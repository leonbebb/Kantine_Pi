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
package kantine_pi.aufladesystem;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import kantine_pi.KartenDaten;
import kantine_pi.LEDKontroller;
import kantine_pi.NFCKontroller;
import kantine_pi.NFCVorgang;

/**
 * TODO beschreibung
 *
 * @author Leon Bebbington, John Bebbington
 */
public class AufladeModell implements Runnable {

    private static final double MAXGUTHABEN_EURO = 70.00;
    private static final double MAXAUFLADUNG_EURO = 50.00;
    private double aufladebetrag = 0.00;

    private KartenDaten aktuelle_kartendaten;

    private NFCVorgang karten_vorgang;
    private AufladeSystemGUI gui;
    private ButtonZustand bz;

    private NFCKontroller nfc;
    private LEDKontroller leds;
    private boolean alive = true;

    private Thread worker = new Thread(this);
    private ArrayBlockingQueue<String> commandQ;

    AufladeModell(String key_filename) {

        aktuelle_kartendaten = null;

        nfc = new NFCKontroller(key_filename);
        leds = LEDKontroller.getInstance();
        commandQ = new ArrayBlockingQueue<String>(20);
        worker.start();

    }

    void setGUI(AufladeSystemGUI gui) {
        this.gui = gui;

        this.gui.setKartenID("Unbekannt");
        this.gui.setKarteName("Unbekannt");
        this.gui.setKlasse("Unbekannt");
        bz = new ButtonZustand();
        this.gui.setButtonZustand(bz);

    }

    void betragaufladen(int betrag_in_euro) {
        System.out.println("betragaufladen : " + betrag_in_euro);
        aufladebetrag = aufladebetrag + betrag_in_euro;
        if (aufladebetrag >= MAXAUFLADUNG_EURO) {
            aufladebetrag = MAXAUFLADUNG_EURO;
        }
        System.out.println("aufladebetrag : " + aufladebetrag);

        gui.setKarteName("" + aufladebetrag);
    }

    void aufladen_stornieren() {
        aufladebetrag = 0.0;
    }

    void auszahlen() {
        try {
            commandQ.put("AUSZAHLEN");
        } catch (InterruptedException ex) {
            Logger.getLogger(AufladeModell.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void karteLesen() {
        try {
            commandQ.put("LESEN");
        } catch (InterruptedException ex) {
            Logger.getLogger(AufladeModell.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void auszahlenAufgabe() {

        leds.setGelesen_ok(false);
        leds.setLese_error(false);

        if (aktuelle_kartendaten != null) {

            double alte_guthaben = aktuelle_kartendaten.getGuthaben();
            long alte_id = aktuelle_kartendaten.getKunden_daten().getId();

            aktuelle_kartendaten.setGuthaben(0.0);

            leds.schreiber_blinker.start();
            KartenDaten neu_kartendaten = nfc.writeEncryptedCard(aktuelle_kartendaten);
            karten_vorgang = nfc.readEncryptedCard();
            leds.schreiber_blinker.stop();

            if (karten_vorgang.getStatus().equalsIgnoreCase(NFCKontroller.STATUS_LESEN_OK)) {

                aktuelle_kartendaten = karten_vorgang.getKd();

                if (alte_id == aktuelle_kartendaten.getKunden_daten().getId()) {
                    leds.setGelesen_ok(true);
                    leds.setLese_error(false);
                } else {
                    leds.setGelesen_ok(false);
                    leds.setLese_error(true);
                }

            } else {
                aktuelle_kartendaten.setGuthaben(alte_guthaben);
                leds.setGelesen_ok(false);
                leds.setLese_error(true);
            }

        }
    }

    private void kartenLesenAufgabe() {

        leds.setGelesen_ok(true);
        leds.setLese_error(false);

        NumberFormat formatter = new DecimalFormat("#0.00 €");

        System.out.println("karte lesen");

        leds.leser_blinker.start();

        karten_vorgang = nfc.readEncryptedCard();

        leds.leser_blinker.stop();

        this.gui.setStatus(karten_vorgang.getStatus());

        if (karten_vorgang.getStatus().equalsIgnoreCase(NFCKontroller.STATUS_LESEN_OK)) {

            leds.setGelesen_ok(true);
            leds.setLese_error(false);

            aktuelle_kartendaten = karten_vorgang.getKd();

            this.gui.setGuthaben(formatter.format(aktuelle_kartendaten.getGuthaben()));
            this.gui.setKartenID(aktuelle_kartendaten.getKunden_daten().getId() + "");
            this.gui.setKarteName(aktuelle_kartendaten.getKunden_daten().getName());
            this.gui.setKlasse(aktuelle_kartendaten.getKunden_daten().getKlasse());

            bz.AufladeGruppeEnabled = true;
            bz.AufladenStornierenEnabled = true;
            bz.AuszahlenEnabled = true;
            bz.BearbeitenEnabled = true;
            bz.KarteLöschenEnabled = true;

        } else if (karten_vorgang.getStatus().equalsIgnoreCase(NFCKontroller.STATUS_SCAN_FEHLER)) {
            leds.setGelesen_ok(false);
            leds.setLese_error(true);

            this.gui.setGuthaben("--.-- €");
            this.gui.setKartenID(KartenDaten.INHALT_UNBEKANNT);
            this.gui.setKarteName(KartenDaten.INHALT_UNBEKANNT);
            this.gui.setKlasse(KartenDaten.INHALT_UNBEKANNT);

            bz.AufladeGruppeEnabled = false;
            bz.AufladenStornierenEnabled = false;
            bz.AuszahlenEnabled = false;
            bz.BearbeitenEnabled = false;
            bz.KarteLöschenEnabled = false;

        } else if (karten_vorgang.getStatus().equalsIgnoreCase(NFCKontroller.STATUS_DATEN_FEHLER)) {
            leds.setGelesen_ok(false);
            leds.setLese_error(true);

            this.gui.setGuthaben("--.-- €");
            this.gui.setKartenID(KartenDaten.INHALT_UNGÜLTIG);
            this.gui.setKarteName(KartenDaten.INHALT_UNGÜLTIG);
            this.gui.setKlasse(KartenDaten.INHALT_UNGÜLTIG);

            bz.AufladeGruppeEnabled = false;
            bz.AufladenStornierenEnabled = false;
            bz.AuszahlenEnabled = false;
            bz.BearbeitenEnabled = false;
            bz.KarteLöschenEnabled = true;

        }

        this.gui.setButtonZustand(bz);

    }

    public void run() {

        while (alive) {
            try {
                String command = commandQ.take();
                if (command.equalsIgnoreCase("LESEN")) {
                    this.kartenLesenAufgabe();
                } else if (command.equalsIgnoreCase("AUSZAHLEN")) {
                    this.auszahlenAufgabe();
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(AufladeModell.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
