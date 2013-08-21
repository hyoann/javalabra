package sanaohjelma;

import java.io.File;
import java.util.ArrayList;
import sanaohjelma.sovelluslogiikka.Kayttaja;
import sanaohjelma.sovelluslogiikka.Kayttajat;
import sanaohjelma.sovelluslogiikka.Sanat;
import sanaohjelma.sovelluslogiikka.Sanavalitsin;
import sanaohjelma.sovelluslogiikka.Tarkistaja;
import sanaohjelma.sovelluslogiikka.Tiedostonlukija;
import sanaohjelma.sovelluslogiikka.TiedostoonKirjoittaja;
import sanaohjelma.sovelluslogiikka.Tilasto;

public class Sanaohjelma {
    private Tilasto tilasto;
    private Tiedostonlukija tiedostonlukija;
    private Sanat sanat;
    private Kayttajat kayttajat;
    private Kayttaja kayttaja;

    public Sanaohjelma(Tiedostonlukija tiedostonlukija) {
        this.tiedostonlukija = tiedostonlukija;
        this.sanat = tiedostonlukija.tuoSanat(new File("src/sanaohjelma/Sanatiedostot/sanat.txt"));
        this.kayttajat = tiedostonlukija.tuoKayttajat(new File("src/sanaohjelma/kayttajat.txt"));
        this.kayttaja = null;
        this.tilasto = null;
    }

    public boolean vastausOikein(String kysyttySana, String annettuVastaus, String kieli) {
        Tarkistaja tarkistaja = new Tarkistaja(this.sanat, kieli);
        tilasto.kasvataSanamaaraa();
        
        if (tarkistaja.vastausOikein(kysyttySana, annettuVastaus)) {
            //sana voidaan poistaa mokattujen sanojen listalta
            tilasto.annaMuistio(kieli).poistaSana(kysyttySana);
            return true;
        }
        tilasto.kasvataMokattuja();
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
    
    public Tilasto tilasto() {
        return this.kayttaja.getTilasto();
    }
    
    public void lisaaSanapari(String suomi, String vieras) {
        TiedostoonKirjoittaja kirjoittaja = new TiedostoonKirjoittaja();
        this.sanat.lisaa(suomi, vieras);
        kirjoittaja.tallennaSanapari("src/sanaohjelma/sanat.txt", suomi, vieras);
    }
    
    public Kayttaja haeKayttaja(String tunnus, String salasana) {
       this.kayttaja = this.kayttajat.haeKayttaja(tunnus, salasana);
       
       this.tilasto = this.kayttaja.getTilasto();
       return this.kayttaja;
       }
      
    public void poistaSana(String sana) {
        TiedostoonKirjoittaja kirjoittaja = new TiedostoonKirjoittaja();
        sanat.poista(sana);
        
        kirjoittaja.paivitaSanatTiedostossa(this.sanat);
    }
    
    public int sanojenMaara() {
        return this.sanat.sanojenMaara();
    }
    
    public ArrayList<String> tiedostojenNimet() {
        return this.tiedostonlukija.tiedostojenNimet();
    }
    
    public void valitseTiedostot(String nimi) {
       this.sanat = tiedostonlukija.tuoSanat(new File("src/sanaohjelma/Sanatiedostot/" + nimi));
    }
    
    public String kayttajanNimi() {
        return this.kayttaja.getNimi();
    }
    
    public String kayttajanTilasto() {
        return this.tilasto.toString();
    }
    
//    public void tallennaTilasto(String kayttaja, Tilasto tilasto) {
//        TiedostoonKirjoittaja kirjoittaja = new TiedostoonKirjoittaja();
//        kirjoittaja.tallennaTilasto(kayttaja, tilasto);
//    }
 
}
