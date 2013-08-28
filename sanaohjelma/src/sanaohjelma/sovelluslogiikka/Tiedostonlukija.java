package sanaohjelma.sovelluslogiikka;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Tiedostonlukija {

    public ArrayList<String> lueTiedosto(File tiedosto) {
        Scanner lukija = null;

        try {
            lukija = new Scanner(tiedosto, "UTF-8");
        } catch (Exception e) {
            System.out.println("Tiedoston lukeminen epäonnistui:" + e.getMessage());
            return null;
        }

        ArrayList<String> rivit = new ArrayList<String>();

        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();
            rivit.add(rivi);
        }

        lukija.close();

        return rivit;
    }

    public Sanat tallennaSanaparit(ArrayList<String> rivit) {
        Sanat sanat = new Sanat();

        for (String rivi : rivit) {
            try {
                String[] sanapari = rivi.split(" - ");
                sanat.lisaa(sanapari[0], sanapari[1]);
            } catch (Exception e) {
                System.out.println("Tiedosto joko on tyhjä tai sanat ovat tallennettu väärässä muodossa.");
                System.out.println("");
            }
        }
        return sanat;
    }

    public Kayttajat haeKayttajat(ArrayList<String> rivit) {
        Kayttajat kayttajat = new Kayttajat();

        String tunnus = "";
        String nimi = "";
        String salasana = "";
        int kysytytSanat = 0;
        int mokatut = 0;

        for (String rivi : rivit) {
            try {
                String[] tiedot = rivi.split(", ");
                tunnus = tiedot[0];
                nimi = tiedot[1];
                salasana = tiedot[2];
                //Tiedot ovat tiedostossa muodossa 'tunnus, nimi, salasana, kysytytSanat, oikeat vastaukset'
                kysytytSanat = Integer.parseInt(tiedot[3]);
                mokatut = Integer.parseInt(tiedot[4]);
            } catch (Exception e) {
                //Käyttäjiä ei ole tai tiedot ovat tallennettu väärässä muodossa.
                return null;
            }
            Tilasto tilasto = new Tilasto(kysytytSanat, mokatut);

            Kayttaja kayttaja = new Kayttaja(tunnus, nimi, salasana, tilasto);

            kayttajat.lisaaKayttaja(tunnus, kayttaja);
        }
        return kayttajat;
    }

    public Sanat tuoSanat(String tiedostonNimi) {
        File tiedosto = new File("src/sanaohjelma/Sanatiedostot/" + tiedostonNimi);
        ArrayList<String> rivit = this.lueTiedosto(tiedosto);
        Sanat sanat = tallennaSanaparit(rivit);
        return sanat;
    }

    public Sanat tuoSanat(File tiedosto) {
        ArrayList<String> rivit = this.lueTiedosto(tiedosto);
        Sanat sanat = tallennaSanaparit(rivit);
        return sanat;
    }

    public Kayttajat tuoKayttajat(File tiedosto) {
        ArrayList<String> rivit = this.lueTiedosto(tiedosto);
        Kayttajat kayttajat = haeKayttajat(rivit);
        return kayttajat;
    }

    public ArrayList<String> tiedostojenNimet() {
        File kansio = new File("src/sanaohjelma/Sanatiedostot");
        File[] tiedostot = kansio.listFiles();

        ArrayList<String> nimet = new ArrayList<String>();

        for (File tiedosto : tiedostot) {
            nimet.add(tiedosto.getName());
        }

        return nimet;
    }
}
