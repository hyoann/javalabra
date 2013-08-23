
package sanaohjelma.sovelluslogiikka;

/**
 *Sisältää käyttäjän tiedot.
 */
public class Kayttaja {
    private String tunnus;
    private String nimi;
    private String salasana; 
    private Tilasto tilasto;
    
    /**
     * Alustaa käyttäjän arvot.
     *
     * @param tunnus Käyttäjätunnus. 
     * @param nimi Käyttäjän nimi.
     * @param salasana Käyttäjän salsana.
     * @param tilasto Käyttäjään liittyvä tilasto.
     */
    public Kayttaja(String tunnus, String nimi, String salasana, Tilasto tilasto) {      
        this.tunnus = tunnus;
        this.nimi = nimi;
        this.salasana = salasana;
        this.tilasto = tilasto;        
    }
    
    public Kayttaja(String tunnus, String salasana, String nimi) {
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
     *Metodi palauttaa tilaston merkkijonoesityksen.
     * 
     * @return Tilaston merkkijonoesitys.
     */
    public String tilasto() {
        return this.tilasto.toString();
    }
    
    public String toString() {
        return this.getTunnus() +", " + this.getNimi() + ", " + 
                this.getSalasana() + ", " + this.getTilasto().sanamaara() + ", " + this.getTilasto().oikeinVastattu();
    }
}
