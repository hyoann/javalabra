package sanaohjelma.sovelluslogiikka;

import java.util.ArrayList;
import java.util.Random;

/**
 * MokausMuistio pitää kirjaa sanoista, joita ei ole osattu kääntää oikein.
 */
public class MokausMuistio {

    /**
     * Kieli1-listaan kerätään sanat, jotka ovat kielellä 1.
     */
    private ArrayList<String> kieli1;
    
    /**
     *Kieli2-listaan tallennetaan vuorostaan sanat, jotka ovat kielellä 2.
     */
    private ArrayList<String> kieli2;

    /**
     * Luo tyhjät listat.
     */
    public MokausMuistio() {
        this.kieli1 = new ArrayList<String>();
        this.kieli2 = new ArrayList<String>();
    }

    /**
     * Lisää sanan listaan. Tarkistaa ensin, millä kielellä tallennettava sana on,
     * jotta se lisätään oikeaan listaan.
     * 
     * @param sana Tallennettava sana
     * @param kieli Millä kielellä sana on
     */
    public void lisaaSana(String sana, String kieli) {
        if (kieli.equals(Kielet.getKieli1())) {
            this.kieli1.add(sana);
        } else {
            this.kieli2.add(sana);
        }
    }

    /**
     *Poistaa sanan listasta.
     * 
     * @param sana Poistettava sana
     * @param kieli Kieli jolla sana on
     */
    public void poistaSana(String sana, String kieli) {
        if (kieli.equals(Kielet.getKieli1())) {
            this.kieli1.remove(sana);
        } else {
            this.kieli2.remove(sana);
        }
    }

    /**
     * Palauttaa jonkin sanan halutulla kielellä. Metodissa arvotaan random-oliolla jokin luku
     * ja valitaan listasta tätä lukuva vastaavalla indeksillä oleva sana.
     * 
     * @param kieli Kieli jolla sanan halutaan olevan
     * @return Jokin sana halutulla kielellä, jos sellaisia on, muuten null
     */
    public String annaJokuSana(String kieli) {
        ArrayList<String> mokatutSanat = this.kieli1;

        if (kieli.equals(Kielet.getKieli2())) {
            mokatutSanat = this.kieli2;
        }

        if (mokatutSanat.isEmpty()) {
            return null;
        }

        Random arpoja = new Random();
        int indeksi = arpoja.nextInt(mokatutSanat.size());

        return mokatutSanat.get(indeksi);
    }
}
