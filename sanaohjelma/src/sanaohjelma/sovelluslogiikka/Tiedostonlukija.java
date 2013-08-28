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

        for (String rivi : rivit) {
            try {
                //Tiedot ovat tiedostossa muodossa 'tunnus, nimi, salasana, kysyttyjen sanojen maara, oikeiden vastausten maara'
                String[] tiedot = rivi.split(", ");
                String tunnus = tiedot[0];
                String nimi = tiedot[1];
                String salasana = tiedot[2];
                int kysytytSanat = Integer.parseInt(tiedot[3]);
                int mokatut = Integer.parseInt(tiedot[4]);

                Tilasto tilasto = new Tilasto(kysytytSanat, mokatut);
                Kayttaja kayttaja = new Kayttaja(tunnus, nimi, salasana, tilasto);
                kayttajat.lisaaKayttaja(tunnus, kayttaja);

            } catch (Exception e) {
                //Käyttäjiä ei ole tai tiedot ovat tallennettu väärässä muodossa.
                System.out.println("Tiedotossa on käyttäjiä, joiden tiedot väärässä muodossa tai käyttäjiä ei ole");
            }
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

        if (rivit == null) {
            return null;
        }
        Sanat sanat = tallennaSanaparit(rivit);
        return sanat;
    }

    public Kayttajat tuoKayttajat(File tiedosto) {
        ArrayList<String> rivit = this.lueTiedosto(tiedosto);

        if (rivit == null) {
            return null;
        }

        Kayttajat kayttajat = haeKayttajat(rivit);
        return kayttajat;
    }

    public ArrayList<String> tiedostojenNimet(String polku) {
        File kansio = new File(polku);
        File[] tiedostot = kansio.listFiles();

        if (tiedostot == null) {
            return null;
        }
        
        ArrayList<String> nimet = new ArrayList<String>();

        for (File tiedosto : tiedostot) {
            nimet.add(tiedosto.getName());
        }

        return nimet;
    }
}
