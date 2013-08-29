package sanaohjelma.sovelluslogiikka;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Tiedostonlukija sisältää tiedostonlukemiseen tarvittavia metodeja
 */
public class Tiedostonlukija {

    /**
     *Metodi käy läpi tiedoston rivi riviltä ja tallentaa rivit ArrayList-rakenteeseen
     * 
     * @param tiedosto Tiedosto jota halutaan tarkastella
     * @return Tiedoston sisältö listassa
     */
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

    /**
     * Metodi muuttaa parametrina saadun listan sisällön sanapareiksi ja tallentaa parit sanat-olioon.
     * Jos tiedostossa on rivejä, jotka eivät ole halutussa muodossa, niitä ei tallenneta.
     * 
     * @param rivit Lista sanapareista, jotka halutaan tallentaa sanat-olioon
     * @return Sanat-luokan olio
     */
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

    /**
     * Metodi purkaa parametrina annetun listan arvot käyttäjä-olioksi ja tallentaa ne käyttäjät-olioon.
     * 
     * @param rivit Lista käyttäjiä
     * @return Käyttäjät-luokan ilmentymä
     */
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

    /**
     * Metodi lukee Sanatiedostot-kansiossa olevan tiedoston ja palauttaa Sanat-luokan olion.
     *
     * @param tiedostonNimi Sanatiedostot-kansiossa oleva tiedosto
     * @return Sanat-luokan ilmentymä, jossa tiedostossa oleva sanat
     */
    public Sanat tuoSanat(String tiedostonNimi) {
        File tiedosto = new File("src/sanaohjelma/Sanatiedostot/" + tiedostonNimi);
        ArrayList<String> rivit = this.lueTiedosto(tiedosto);
        Sanat sanat = tallennaSanaparit(rivit);
        return sanat;
    }

    /**
     * Metodi tekee saman kuin edeltävä metodi, mutta nyt parametrina voi antaa tiedoston,
     * jonka sijainti on vapaa. 

     */
    public Sanat tuoSanat(File tiedosto) {
        ArrayList<String> rivit = this.lueTiedosto(tiedosto);

        if (rivit == null) {
            return null;
        }
        Sanat sanat = tallennaSanaparit(rivit);
        return sanat;
    }

    /**
     * Metodi lukee tiedoston ja tuo tiedostosta löydetyt käyttäjät käyttäjät-luokkaan tallennettuina.
     *
     * @param tiedosto Tiedosto jossa käyttäjiä
     * @return Käyttäjät-luokan ilemntymä, joka sisältää tiedostosta löydetyt käyttäjät
     */
    public Kayttajat tuoKayttajat(File tiedosto) {
        ArrayList<String> rivit = this.lueTiedosto(tiedosto);

        if (rivit == null) {
            return null;
        }

        Kayttajat kayttajat = haeKayttajat(rivit);
        return kayttajat;
    }

    /**
     * Hakee kansiossa olevien tiedostojen nimet.
     *
     * @param polku Kansion sijainti
     * @return Tiedostojen nimet listassa
     */
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
