package sanaohjelma.sovelluslogiikka;

import java.util.HashMap;

/**
 * Käyttäjät luokka sisältää käyttäjät.
 */
public class Kayttajat {

    /**
     * Käyttäjät tallennetaan HashMap-tietorakenteeseen {käyttäjätunnus, käyttäjä} -pareina.
     */
    private HashMap<String, Kayttaja> kayttajat;

    /**
     * Alustaa käyttäjät-olion
     */
    public Kayttajat() {
        this.kayttajat = new HashMap<String, Kayttaja>();
    }

    /**
     * Lisää uuden käyttäjän.
     * 
     * @param tunnus Lisättävän käyttäjän tunnus
     * @param kayttaja Lisättävä käyttäjä
     * @return Palauttaa false, jos käyttäjätunnus on jo tallennettu, muuten true ja tallentaa käyttäjän
     */
    public boolean lisaaKayttaja(String tunnus, Kayttaja kayttaja) {
        if (this.kayttajat.containsKey(tunnus) || kayttaja == null || tunnus == null) {
            return false;
        } else {
            this.kayttajat.put(tunnus, kayttaja);
            return true;
        }
    }

    /**
     * Hakee käyttäjän salasanan tunnuksen perusteella.
     * 
     * @param kayttajatunnus Käyttäjän tunnus.
     * @return Käyttäjän salasana.
     */
    public String haeSalasana(String kayttajatunnus) {
        if (this.kayttajat.containsKey(kayttajatunnus)) {
            return this.kayttajat.get(kayttajatunnus).getSalasana();
        }
        return null;
    }

    /**
     * Tarkistaa liittyvätkö annettu tunnus ja salasana samaan käyttäjään.
     *
     * @param tunnus Käyttäjän tunnus
     * @param salasana Käyttäjän salasana
     * @return True jos vastaa käyttäjää, false muuten
     */
    public boolean onKayttaja(String tunnus, String salasana) {
        if (this.haeSalasana(tunnus) != null) {
            return this.haeSalasana(tunnus).equals(salasana);
        }
        return false;
    }

    /**
     * Palauttaa annettua tunnusta ja salasanaa vastaavan käyttäjän
     * 
     * @param tunnus Käyttäjän tunnus
     * @param salasana Käyttäjän salasana
     * @return Käyttäjä jolla annettu tunnus ja salasana
     */
    public Kayttaja haeKayttaja(String tunnus, String salasana) {
        if (this.onKayttaja(tunnus, salasana)) {
            return this.kayttajat.get(tunnus);
        }
        return null;
    }

    /**
     * Poistaa käyttäjän HashMapista.
     *
     * @param tunnus Poistettava käyttäjän tunnus
     * @return Onnistuiko käyttäjän poisto
     */
    public boolean poistaKayttaja(String tunnus) {
        if (this.kayttajat.containsKey(tunnus)) {
            this.kayttajat.remove(tunnus);
            return true;
        }
        return false;
    }
    
    /**
     * Palauttaa käyttäjien merkkijonoesityksen muodossa 'tunnus, nimi, salasana, kysytyt sanat, oikeat vastaukset\ntoinen, nimi, ss, 0, 0\n'
     */
    public String toString() {
        String mj = "";
        for (Kayttaja kayttaja : this.kayttajat.values()) {
            mj += kayttaja.toString();
            mj += "\n";
        }
        return mj;
    }
}
