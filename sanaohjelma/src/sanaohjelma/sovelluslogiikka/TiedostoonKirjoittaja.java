package sanaohjelma.sovelluslogiikka;

import java.io.FileWriter;
import java.io.IOException;

/**
 *TiedostoonKirjoittaja-luokan avulla voidaan kirjoittaa tekstiä tiedostoon.
 */
public class TiedostoonKirjoittaja {

    /**
     *Metodi kirjoittaa tiedostoon halutun tekstin.
     * 
     * @param sijainti Tiedostopolku
     * @param teksti Tiedostoon kirjoitettava teksti
     */
    public void kirjoitaTiedostoon(String sijainti, String teksti) {
        try {
            FileWriter kirjoittaja = new FileWriter(sijainti);
            kirjoittaja.write(teksti);
            kirjoittaja.close();
        } catch (IOException ex) {
            System.out.println("Tiedostoon kirjoittaminen epäonnistui: " + ex.getMessage());
        }
    }

    /**
     * Metodi lisää tiedostoon halutun tekstin mutta ei poista siellä jo mahdollisesti olevaa sisältöä.
     *
     * @param sijainti Tiedostopolku
     * @param teksti Tiedostoon lisättävä teksti
     */
    public void lisaaTiedostoon(String sijainti, String teksti) {
        try {
            FileWriter kirjoittaja = new FileWriter(sijainti, true);
            kirjoittaja.append(teksti + "\n");
            kirjoittaja.close();
        } catch (IOException ex) {
            System.out.println("Tiedostoon lisäys epäonnistui: " + ex.getMessage());
        } 
    }
    
    /**
     * Metodi liittää kahden sanan väliin väliviivan, ja lisaa näin saadun uuden merkkijonon
     * haluttuun tiedostoon. Metodia käytetään uusien sanaparien tallentamiseen tiedostoon.
     *
     * @param tiedosto Tiedostopolku
     * @param sana1 Väliviivaa edeltävä sana
     * @param sana2 Väliviivan jälkeen liitettävä sana
     */
    public void tallennaSanapari(String tiedosto, String sana1, String sana2) {
        String tallennettava = sana1 + " - " + sana2;
        this.lisaaTiedostoon(tiedosto, tallennettava);
    }
}
