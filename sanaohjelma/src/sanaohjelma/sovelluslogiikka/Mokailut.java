package sanaohjelma.sovelluslogiikka;

/**
 * Mokailut pitää sisällään mokoausmuistiot, joissa on väärin arvattuja sanoja.
 * Väärin arvatut sanat pitää tallettaa omiin muistioihinsa sen perusteella,
 * millä kielellä sana on.
 */

public class Mokailut {
    private MokausMuistio kieli1;
    private MokausMuistio kieli2;

    /**
     *Luodaan tyhjät mokausmuistiot.
     */
    public Mokailut() {
        this.kieli1 = new MokausMuistio();
        this.kieli2 = new MokausMuistio();
    }

    /**
     * Tallentaa sanan muistioon.
     *
     * @param kieli Kieli jolla sana on
     * @param sana Lisättä sana
     */
    public void lisaaSana(String kieli, String sana) {
        if (kieli.equals(Kielet.kieli1)) {
            this.kieli1.lisaaSana(sana);
        } else {
            this.kieli2.lisaaSana(sana);
        }
    }

    /**
     * Metodi pyytää muistioita poistamaan sanan.
     *
     * @param kieli Kieli jolla poistettava sana on
     * @param sana Poistettava sana
     */
    public void poistaSana(String kieli, String sana) {
        if (kieli.equals(Kielet.kieli1)) {
            this.kieli1.poistaSana(sana);
        } else {
            this.kieli2.poistaSana(sana);
        }
    }

    /**
     *Metodi pyytää muistiota antamaan jonkun sanaa.
     * 
     * @param kieli Kieli jolla sana halutaan
     * @return Väärin arvattu sana
     */
    public String annaJokuSana(String kieli) {
        if (kieli.equals(Kielet.kieli1)) {
            return this.kieli1.annaJokuSana();
        } else {
            return this.kieli2.annaJokuSana();
        }
    }
}
