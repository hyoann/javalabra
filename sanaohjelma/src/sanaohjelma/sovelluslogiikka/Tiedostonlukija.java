package sanaohjelma.sovelluslogiikka;

import java.io.File;
import java.util.Scanner;

public class Tiedostonlukija {
    private File sanatTiedostossa;
    private Sanat sanat;

    public Tiedostonlukija(File sanatTiedostossa) {
        this.sanatTiedostossa = sanatTiedostossa;
        this.sanat = new Sanat();
    }

    public Sanat lueTiedosto() {
        Scanner lukija = null;

        try {
            lukija = new Scanner(sanatTiedostossa, "UTF-8");
        } catch (Exception e) {
            System.out.println("Tiedoston lukeminen epäonnistui:" + e.getMessage());
            return null;
        }

        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();

            String[] sanapari = rivi.split(" - ");

            sanat.lisaa(sanapari[0], sanapari[1]);
        }

        lukija.close();
        return sanat;
    }
}
