package sanaohjelma.sovelluslogiikka;

import java.io.FileWriter;
import java.io.IOException;

public class TiedostoonKirjoittaja {

    public void kirjoitaTiedostoon(String sijainti, String teksti) {
        try {
            FileWriter kirjoittaja = new FileWriter(sijainti);
            kirjoittaja.write(teksti);
            kirjoittaja.close();
        } catch (IOException ex) {
            System.out.println("Tiedostoon kirjoittaminen epäonnistui: " + ex.getMessage());
        }
    }

    public void lisaaTiedostoon(String sijainti, String teksti) {
        try {
            FileWriter kirjoittaja = new FileWriter(sijainti, true);
            kirjoittaja.append(teksti + "\n");
            kirjoittaja.close();
        } catch (IOException ex) {
            System.out.println("Tiedostoon lisäys epäonnistui: " + ex.getMessage());
        } 
    }
    
    public void tallennaSanapari(String tiedosto, String suomi, String vieras) {
        String tallennettava = suomi + " - " + vieras;
        this.lisaaTiedostoon(tiedosto, tallennettava);
    }
}
