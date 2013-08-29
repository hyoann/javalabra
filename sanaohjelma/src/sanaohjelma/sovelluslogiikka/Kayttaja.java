package sanaohjelma.sovelluslogiikka;

/**
 * Sisältää käyttäjän tiedot.
 */
public class Kayttaja {

    /**
     * Käyttäjätunnus jota käytetään käyttäjän yksilöimiseen. Ei saa olla null.
     */
    private String tunnus;
    /**
     * Käyttäjän nimi.
     */
    private String nimi;
    /**
     * Käyttäjän salasana. Ei saa olla null.
     */
    private String salasana;
    /**
     * Käyttäjään liittyvä tilasto.
     */
    private Tilasto tilasto;

    /**
     * Metodia käytetään käyttäjä-olioiden luomiseen, kun käyttäjien tiedot
     * haetaan tiedostosta.
     *
     * @param tunnus Tiedostosta haettu tunnus.
     * @param nimi Tiedostosta haettu nimi.
     * @param salasana Tiedostosta haettu salasana.
     * @param tilasto Tiedostosta haettu tilasto.
     */
    public Kayttaja(String tunnus, String nimi, String salasana, Tilasto tilasto) {
        if (tunnus == null || salasana == null) {
            return;
        }
        if (tilasto == null) {
            tilasto = new Tilasto();
        }

        this.tunnus = tunnus;
        this.nimi = nimi;
        this.salasana = salasana;
        this.tilasto = tilasto;
    }

    /**
     * Metodia käytetään uuden käyttäjän luomiseen. Tilastoa ei tarvitse antaa
     * parametrina vaan luodaan uusi tilasto automaattisesti.
     *
     * @param tunnus Käyttäjän valitsema käyttäjätunnus
     * @param nimi Käyttäjän valitsema nimi
     * @param salasana Käyttäjän antama salasana
     */
    public Kayttaja(String tunnus, String nimi, String salasana) {
        if (tunnus == null || salasana == null) {
            return;
        }

        this.tunnus = tunnus;
        this.nimi = nimi;
        this.salasana = salasana;
        this.tilasto = new Tilasto();
    }

    public String getTunnus() {
        return this.tunnus;
    }

    public String getSalasana() {
        return this.salasana;
    }

    public String getNimi() {
        return this.nimi;
    }

    public Tilasto getTilasto() {
        return this.tilasto;
    }

    /**
     * Metodi palauttaa tilaston merkkijonoesityksen.
     *
     */
    public String tilasto() {
        return this.tilasto.toString();
    }

    /**
     * Käyttäjän merkkijonoesitys on muotoa 'tunnus, nimi, salasana, kysyttyjen sanojen määrä, oikeiden vastausten määrä'
     *
     */
    public String toString() {
        return this.getTunnus() + ", " + this.getNimi() + ", "
                + this.getSalasana() + ", " + this.getTilasto().sanamaara() + ", " + this.getTilasto().oikeinVastattu();
    }
}
