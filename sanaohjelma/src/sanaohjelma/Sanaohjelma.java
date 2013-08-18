package sanaohjelma;

import sanaohjelma.sovelluslogiikka.Sanat;
import sanaohjelma.sovelluslogiikka.Sanavalitsin;
import sanaohjelma.sovelluslogiikka.Tarkistaja;
import sanaohjelma.sovelluslogiikka.Tiedostonlukija;
import sanaohjelma.sovelluslogiikka.TiedostoonKirjoittaja;
import sanaohjelma.sovelluslogiikka.Tilasto;

public class Sanaohjelma {
    private Tiedostonlukija tiedostonlukija;
    private Sanat sanat;
    private Tilasto tilasto;

    public Sanaohjelma(Tiedostonlukija tiedostonlukija) {
        this.tiedostonlukija = tiedostonlukija;
        this.sanat = tiedostonlukija.tuoSanat();
        this.tilasto = new Tilasto();
    }

    public boolean vastausOikein(String kysyttySana, String annettuVastaus, String kieli) {
        Tarkistaja tarkistaja = new Tarkistaja(this.sanat, kieli);
        
        if (tarkistaja.vastausOikein(kysyttySana, annettuVastaus)) {
            //sana voidaan poistaa mokattujen sanojen listalta
            tilasto.annaMuistio(kieli).poistaSana(kysyttySana);
            return true;
        }
        tilasto.annaMuistio(kieli).lisaaSana(kysyttySana);
        return false;
    }

    public String annaSana(String kieli) {
        tilasto.kasvataSanamaaraa();
        Sanavalitsin valitsin = new Sanavalitsin(3, tilasto, sanat);
        return valitsin.annaSana(kieli);
    }

    public String naytaSanat() {
        return this.sanat.toString();
    }
    
    public String haeOikeaVastaus(String sana, String kieli) {
        Tarkistaja tarkistaja = new Tarkistaja(this.sanat, kieli);
        return tarkistaja.haeOikeaVastaus(sana);
    }
    
    public String tilasto() {
        return tilasto.toString();
    }
    
    public void lisaaSanapari(String suomi, String vieras) {
        TiedostoonKirjoittaja kirjoittaja = new TiedostoonKirjoittaja();
        this.sanat.lisaa(suomi, vieras);
        kirjoittaja.tallennaSanapari("src/sanaohjelma/sanat.txt", suomi, vieras);
    }
}
