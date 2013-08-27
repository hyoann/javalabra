package sanaohjelma;

import java.io.File;
import java.util.ArrayList;
import sanaohjelma.sovelluslogiikka.Kayttaja;
import sanaohjelma.sovelluslogiikka.Kayttajat;
import sanaohjelma.sovelluslogiikka.MokausMuistio;
import sanaohjelma.sovelluslogiikka.Sanat;
import sanaohjelma.sovelluslogiikka.Sanavalitsin;
import sanaohjelma.sovelluslogiikka.Tarkistaja;
import sanaohjelma.sovelluslogiikka.Tiedostonlukija;
import sanaohjelma.sovelluslogiikka.TiedostoonKirjoittaja;
import sanaohjelma.sovelluslogiikka.YhdistaSanat;

public class Sanaohjelma {

    private Sanat sanat;
    private Tiedostonlukija tiedostonlukija;
    private Kayttaja kayttaja;
    private Kayttajat kayttajat;
    private MokausMuistio muistio;
    private YhdistaSanat yhdista;

    public Sanaohjelma(Tiedostonlukija tiedostonlukija) {
        this.sanat = null;
        this.tiedostonlukija = tiedostonlukija;
        this.kayttaja = null;
        this.kayttajat = tiedostonlukija.tuoKayttajat(new File("src/sanaohjelma/kayttajat.txt"));
        this.muistio = new MokausMuistio();
        this.yhdista = null;
    }

    public boolean lisaaKayttaja(String tunnus, String salasana, String nimi) {
        Kayttaja kayttaja = new Kayttaja(tunnus, salasana, nimi);
        
        if (this.kayttajat.lisaaKayttaja(tunnus, kayttaja)) {
            TiedostoonKirjoittaja kirjoittaja = new TiedostoonKirjoittaja();
            kirjoittaja.paivitaKayttajatTiedostoon(this.kayttajat);
            return true;
        } else {
            return false;
        }
    }

    public Kayttaja haeKayttaja(String tunnus, String salasana) {
        Kayttaja kayttaja = kayttajat.haeKayttaja(tunnus, salasana);

        if (kayttaja != null) {
            this.kayttaja = kayttaja;
        }

        return this.kayttaja;
    }

    public String kayttajanNimi() {
        return this.kayttaja.getNimi();
    }

    public String kayttajanTilasto() {
        return this.kayttaja.tilasto();
    }

    public void tallennaTilasto() {
        TiedostoonKirjoittaja kirjoittaja = new TiedostoonKirjoittaja();
        kirjoittaja.paivitaKayttajatTiedostoon(this.kayttajat);
    }
    
    public void kasvataOikeinVastattuja(int maara) {
        this.kayttaja.getTilasto().kasvataOikeita();
    }

    public void asetaSanat(String tiedostonNimi) {
        this.sanat = this.tiedostonlukija.tuoSanat(tiedostonNimi);
    }

    public boolean vastausOikein(String kysyttySana, String annettuVastaus, String kieli) {
        Tarkistaja tarkistaja = new Tarkistaja(this.sanat, kieli);
        if (tarkistaja.vastausOikein(kysyttySana, annettuVastaus)) {
            this.kayttaja.getTilasto().kasvataOikeita();
            return true;
        }
        return false;
    }

    public String kysySana(String kieli) {
        Sanavalitsin valitsin = new Sanavalitsin(2, this.kayttaja.getTilasto(), this.sanat, this.muistio);
        String kysyttavaSana = valitsin.annaSana(kieli);
        if (kysyttavaSana != null) {
            this.kayttaja.getTilasto().kasvataSanamaaraa();
        }
        return kysyttavaSana;
    }

    public String sanatMerkkijono() {
        return this.sanat.toString();
    }

    public int sanojenMaara() {
        return this.sanat.sanojenMaara();
    }

    public String haeOikeaVastaus(String sana, String kieli) {
        Tarkistaja tarkistaja = new Tarkistaja(this.sanat, kieli);
        return tarkistaja.haeOikeaVastaus(sana);
    }

    public void lisaaSanapari(String sana1, String sana2, String tiedostonNimi) {
        TiedostoonKirjoittaja kirjoittaja = new TiedostoonKirjoittaja();
        Sanat sanatTemp = this.tiedostonlukija.tuoSanat(tiedostonNimi);
        sanatTemp.lisaa(sana1, sana2);
        kirjoittaja.tallennaSanapari("src/sanaohjelma/Sanatiedostot/" + tiedostonNimi, sana1, sana2);
    }

    public boolean poistaSana(String sana, String tiedostonNimi) {
        TiedostoonKirjoittaja kirjoittaja = new TiedostoonKirjoittaja();
        Sanat sanatTemp = this.tiedostonlukija.tuoSanat(tiedostonNimi);

        if (!sanatTemp.poista(sana)) {
            return false;
        }
        kirjoittaja.paivitaSanatTiedostossa(sanatTemp);
        return true;
    }

    public ArrayList<String> tiedostojenNimet() {
        return this.tiedostonlukija.tiedostojenNimet();
    }

    public String haeTiedostonNimi(int indeksi) {
        try {
            return this.tiedostojenNimet().get(indeksi);
        } catch (Exception e) {
            return null;
        }
    }

    public String naytaSisalto(String tiedostonNimi) {
        try {
            return this.tiedostonlukija.tuoSanat(tiedostonNimi).toString();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean poistaTiedosto(String tiedostonNimi) {
        File poistettava = new File("src/sanaohjelma/Sanatiedostot/" + tiedostonNimi);
        return poistettava.delete();
    }

    public boolean lisaaTiedosto(String tiedostopolku) {
        File alkuperainen = new File(tiedostopolku);
        String nimi = alkuperainen.getName();

        return alkuperainen.renameTo(new File("src/sanaohjelma/Sanatiedostot/" + nimi));
    }
    
    public ArrayList<String> haeSanatNumerolla(int maara) {
        //aloitetaan tehtävä joten luodaan tehtävä-olio
        this.yhdista = new YhdistaSanat(this.sanat);
        this.yhdista.taytaListat(maara);
        System.out.println(this.yhdista.palautaSanatNumerolla());
        return this.yhdista.palautaSanatNumerolla();
    }
    
    public ArrayList<String> haeKaannoksetKirjaimella() {
        System.out.println(this.yhdista.palautaKaannoksetKirjaimella());
       return this.yhdista.palautaKaannoksetKirjaimella();
    }
    
    public boolean tarkistaVastaus(String vastaus) {
        return this.yhdista.tarkista(vastaus);
    }
    
    public String oikeaRivi() {
        String oikeaRivi = this.yhdista.oikeatVastaukset();
        //haettiin oikea rivi, joten tehtävä lopetetetaan ja olio voidaan nollata
        this.yhdista = null;
        
        return oikeaRivi;
    }
    
    public void kirjaaKayttajaUlos() {
        this.kayttaja = null;
    }
}
