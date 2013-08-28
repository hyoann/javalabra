package sanaohjelma.sovelluslogiikka;

import java.util.HashMap;

public class Kayttajat {

    private HashMap<String, Kayttaja> kayttajat;

    public Kayttajat() {
        this.kayttajat = new HashMap<String, Kayttaja>();
    }

    public boolean lisaaKayttaja(String tunnus, Kayttaja kayttaja) {
        if (this.kayttajat.containsKey(tunnus) || kayttaja == null || tunnus == null) {
            return false;
        } else {
            this.kayttajat.put(tunnus, kayttaja);
            return true;
        }
    }

    public String haeSalasana(String kayttajatunnus) {
        if (this.kayttajat.containsKey(kayttajatunnus)) {
            return this.kayttajat.get(kayttajatunnus).getSalasana();
        }
        return null;
    }

    public boolean onKayttaja(String tunnus, String salasana) {
        if (this.haeSalasana(tunnus) != null) {
            return this.haeSalasana(tunnus).equals(salasana);
        }
        return false;
    }

    public Kayttaja haeKayttaja(String tunnus, String salasana) {
        if (this.onKayttaja(tunnus, salasana)) {
            return this.kayttajat.get(tunnus);
        }
        return null;
    }

    public String toString() {
        String mj = "";
        for (Kayttaja kayttaja : this.kayttajat.values()) {
            mj += kayttaja.toString();
            mj += "\n";
        }
        return mj;
    }
}
