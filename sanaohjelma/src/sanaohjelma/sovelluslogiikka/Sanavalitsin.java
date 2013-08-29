package sanaohjelma.sovelluslogiikka;

/**
 * Sanavalitsin huolehtii sitä, mistä käyttäjältä kysytyt sanat valitaan.
 */
public class Sanavalitsin {

    /**
     * Kuinka monen sanan välein halutaan, että väärin arvattuja sanoja kysytään
     */
    private int toistovali;
    
    /**
     * Järjestelmän sanat
     */
    private Sanat sanat;
    
    /**
     * Käyttäjään liittyvä tilasto
     */
    private Tilasto tilasto;
    
    /**
     * Väärin arvatut sanat ovat muistiossa
     */
    private MokausMuistio muistio;

    /**
     *
     * @param vali Kuinka monen sanan välein kysytään väärin arvattuja sanoja
     * @param tilasto Käyttäjään liittyvä tilasto
     * @param sanat Järjestelmän sanat
     * @param mokailut Väärin arvatut sanat
     */
    public Sanavalitsin(int vali, Tilasto tilasto, Sanat sanat, MokausMuistio muistio) {
        if (vali < 0) {
            vali = 1;
        }
        this.toistovali = vali;
        this.sanat = sanat;
        this.tilasto = tilasto;
        this.muistio = muistio;
    }

    /**
     * Metodi palauttaa sanan Sanat- tai MokausMuistio-luokan oliosta. Ensiksi se
     * selvittää, onko aika kysyä sanaa mokattujen sanojen listalta. Jos
     * kysyttyjen sanojen määrä on jaollinen toistovälillä, annetaan sana muistiosta. Jos muistio on tyhjä,
     * haetaan sana sanat-oliosta.
     *
     * @param kieli Kieli jolla sana halutaan
     * @return Satunnainen sana
     */
    public String annaSana(String kieli) {
        if (tilasto.sanamaara() != 0 && tilasto.sanamaara() % this.toistovali == 0) {
            String kysyttavaSana = this.muistio.annaJokuSana(kieli);
            if (kysyttavaSana != null) {
                return kysyttavaSana;
            }
        }
        return this.sanat.annaJokuSana(kieli);
    }
}