package sanaohjelma.sovelluslogiikka;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Luokkaa käytetään sanaparien yhdistämistehtävän luomisessa.
 * Tehtävä näyttää seuraavanlaiselta:
 *          1.kissa    a.dog
 *          2.koira    b.rabbit
 *          3.jänis    c.cat
 * 
 * Käyttäjä antaa vastaukset yhdistämällä luvun ja kirjaimen. Oikea rivi tässä siis: 1c, 2a, 3b
 */
public class YhdistaSanat {

    /**
     * Järjestelmässä olevat sanat
     */
    private Sanat sanat;
    
    /**
     * Sanoja kielellä 1
     */
    private ArrayList<String> arvotutSanat;
    
     /**
      * arovtutSanat-listassa olevia sanoja vastaavat indeksit
      */
    private ArrayList<Integer> kaannokset;

    /**
     * Alustaa olion
     */
    public YhdistaSanat(Sanat sanat) {
        this.arvotutSanat = new ArrayList<String>();
        this.kaannokset = new ArrayList<Integer>();
        this.sanat = sanat;
    }

    /**
     * Hakee arvotutSanat-listaan halutun määrän eri sanoja. Samalla lisätään kaannokset-listaan
     * vastaavat indeksit. Lopuksi kaannokset-lista sekoitetaan.
     * Esimerkin tapauksessa listat olisivat seuraavat
     * arvotutSanat: [kissa, koira jänis] ja kaannokset: [1, 2, 0]
     * 
     * @param maara Kuinka monta sanaparia halutaan
     */
    public void taytaListat(int maara) {
        int sanamaara = 0;

        while (sanamaara < maara) {
            String sana = this.sanat.annaJokuSana(Kielet.getKieli1());

            if (!this.arvotutSanat.contains(sana)) {
                this.arvotutSanat.add(sana);
                this.kaannokset.add(sanamaara);
                sanamaara++;
            }
        }
        Collections.shuffle(this.kaannokset);
    }

    /**
     * Luo uuden listan johon siirretään arvotutSanat-listassa olevat sanat
     * niin, että niiden eteen lisätään numero ja piste.
     * 
     * @return Sanat listassa. Esimerkin tapauksessa saataisiin [1.kissa, 2.koira 3.jänis]
     */
    public ArrayList<String> palautaSanatNumerolla() {
        ArrayList<String> sanatNumeroituna = new ArrayList<String>();

        for (int i = 0; i < this.arvotutSanat.size(); i++) {
            sanatNumeroituna.add((i + 1) + "." + this.arvotutSanat.get(i));
        }
        return sanatNumeroituna;
    }

    /**
     * Tallentaa uuteen listaan käännökset, jotka vastaavat sanoja arvotutSanat-listassa ja lisää käännökseen eteen kirjaimen.
     * Kaannokset-listassa on arvotutSanat-listassa olevien sanojen indeksit sekoitetussa järjestyksessä.
     * Käydään läpit kaannokset-listaa ja haetaan indeksiä vastaava sana arvotutSanat-listasta. Käänetään
     * sana toiselle kielelle ja lisätään sanan eteen kirjain ja piste. Aloitetaan kirjaimet a:sta, jota
     * vastaa merkkikoodi 97. Kirjaimet saadaan oikeassa järjestyksessä a, b, c kun lisätään aina indeksiin 
     * jota ollaan käymässä läpi luku 97.
     * 
     * @return Käännökset listassa. Esimerkin tapauksessa listasta [1, 2, 0] tulisi [a.dog b.rabbit c.cat], koska
     * arvotutSanat-lista oli seuraava: [kissa, koira jänis]
     * 
     */
    public ArrayList<String> palautaKaannoksetKirjaimella() {
        ArrayList<String> kaannoksetKirjaimella = new ArrayList<String>();

        for (int i = 0; i < this.kaannokset.size(); i++) {
            int sananIndeksi = this.kaannokset.get(i);
            String indeksiaVastaavaSana = this.arvotutSanat.get(sananIndeksi);
            
            String kaannos = this.sanat.kaannaKielelle2(indeksiaVastaavaSana);
            kaannoksetKirjaimella.add((char) (i + 97) + "." + kaannos);
        }
        return kaannoksetKirjaimella;
    }

    /**
     * Tarkistaa käyttäjän antaman vastauksen. Jos vastaus ei ole muodossa luku + kirjain ( eli muodossa '1a'), palautetaan false.
     *
     * @param vastaus
     * @return
     */
    public boolean tarkista(String vastaus) {
        if (vastaus.length() < 2) {
            return false;
        }

        try {
            //kirjain on vastauksen viimeinen merkki
            char kirjain = vastaus.charAt(vastaus.length() - 1);
            //ensimmäisessä osassa luku, jossa voi olla kaksi numeroa (esim. '11a')
            String luku = vastaus.substring(0, vastaus.length() - 1);
            
            //Koska muutettaessa indeksiä kirjaimeksi lisättiin indeksiin aina luku 97, vähennetään
            // tämä nyt pois niin saadaan selville indeksi, jota kirjain vastaa.
            int kaannos = (int) kirjain - 97;
            
            //arvotutSanat-listassa olevaa sanaa vastaava indeksi
            int sana = Integer.parseInt(luku) - 1;

            //Kaannokset-listaan oli siis tallennettu arvotutSanat-listassa olevien sanojen indeksit sekaisin.
            //Jos käyttäjä antoi vastauksen 1c, yhdisti hän arvotutSanat-listassa indeksissä 0 olevan sanan
            // ja kaannokset-listassa olevan käännöksen 'paikalla c' (merkkikoodi 99) yhteen. 
            //Puretaan merkkikoodi luvuksi jolloin saadan kaannokset-listan indeksi, tässä tapauksessa 2 (koska 99 - 97).
            //Seuraavaksi katsotaan kaannokset-listasta, mikä luku käyttäjän antaman kirjaimen eli indeksin kohdalta löydetään.
            //Jotta vastaus olisi oikein, täytyy tämän luvun vastata käyttäjän antamaa lukua. Esimerkin tapauksessa vastaus olisi
            //oikein, jos kaannokset-listan indeksistä 2 löytyisi luku 0.
            
            if (sana == this.kaannokset.get(kaannos)) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    /**
     * Metodi tulostaa oikean vastauksen tehtävään.
     * Oikeat vastaukset löydetään suoraan kaannokset-listasta. Esimerkissä
     * kaannokset-lista oli [1, 2, 0], josta saadaan rivi: 2a 3b 1c. Tämä siksi, koska
     * kaannokset-listan arvot vastaavat arvotutSanat-listan indeksejä ja kaannokset-listan indeksi
     * vastaa kirjainta.
     * 
     * @return Vastaukset tehtävään.
     */
    public String oikeatVastaukset() {
        String oikeaRivi = "";

        for (int i = 0; i < this.kaannokset.size(); i++) {
            oikeaRivi += this.kaannokset.get(i) + "" + (char) (i + 97) + " ";
        }
        return oikeaRivi;
    }
}
