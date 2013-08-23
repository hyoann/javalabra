package sanaohjelma.sovelluslogiikka;

/**
 * Sanavalitsin huolehtii sitä, mistä käyttäjältä kysytyt sanat valitaan.
 */
public class Sanavalitsin {

    private int toistovali;
    private Sanat sanat;
    private Tilasto tilasto;
    private MokausMuistio muistio;

    /**
     *
     * @param vali Kuinka monen sanan välein kysytään väärin arvattuja sanoja
     * @param tilasto Käyttäjään liittyvä tilasto
     * @param sanat Järjestelmän sanat
     * @param mokailut Väärin arvatut sanat
     */
    public Sanavalitsin(int vali, Tilasto tilasto, Sanat sanat, MokausMuistio muistio) {
        this.toistovali = vali;
        this.sanat = sanat;
        this.tilasto = tilasto;
        this.muistio = muistio;
    }

    /**
     * Metodi palauttaa sanan Sanat- tai Mokailut-luokasta. Ensiksi se
     * selvittää, onko aika kysyä sanaa mokattujen sanojen listalta. Jos
     * kysyttyjen sanojen maara on jaollinen toistovälillä, annetaan sana sanaa,
     * jota on jo kysytty aiemmin mutta jota ei ole tiedetty.
     *
     * @param kieli Kieli jolla sana halutaan
     * @return Satunnainen sana
     */
    public String annaSana(String kieli) {
        //onko aika  kysyä sanaa mokattujen sanojen listalta
        if (tilasto.sanamaara() != 0 && tilasto.sanamaara() % this.toistovali == 0) {
            String kysyttavaSana = this.muistio.annaJokuSana(kieli);
            if (kysyttavaSana != null) {
                return kysyttavaSana;
            }
        }
        return this.sanat.annaJokuSana(kieli);
    }
}