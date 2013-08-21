package sanaohjelma.sovelluslogiikka;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
    
    public void paivitaSanatTiedostossa(Sanat sanat) {
        this.kirjoitaTiedostoon("src/sanaohjelma/Sanatiedostot/sanat.txt", sanat.toString());    
    }
    
    public void kaannaSanatTiedostossa() {
        Tiedostonlukija lukija = new Tiedostonlukija();
        ArrayList<String> rivit = lukija.lueTiedosto(new File("src/sanaohjelma/Sanatiedostot/KPL1.txt"));
        for (String rivi : rivit) {
            String[] sanapari = rivi.split(" - ");
            String uusiRivi = sanapari[1] + " - " + sanapari[0];
            lisaaTiedostoon("src/sanaohjelma/Sanatiedostot/KPL1.txt", uusiRivi);
        }
    }
}
