package sanaohjelma.sovelluslogiikka;

import java.io.File;
import java.util.ArrayList;

/**
 * Hallinta-luokka toimii tavallaan rajapintana käyttöliittymän ja
 * sovelluslogiikan välillä. Hallinta-luokka myös hoitaa käyttäjään liittyvän
 * tilaston päivittämisen tarvittaessa.
 */
public class Hallinta {

    /**
     * Sanat joita ohjelmassa halutaan käyttää
     */
    private Sanat sanat;
    /**
     *Tiedostonlukijaa tarvitaan sanojen ja käyttäjien lukemiseen tiedostosta
     */
    private Tiedostonlukija tiedostonlukija;
    /**
     *Ohjelmaan kirjautunut käyttäjä
     */
    private Kayttaja kayttaja;
    /**
     *Tiedostoon tallennetut käyttäjät
     */
    private Kayttajat kayttajat;
    /**
     *Mokausmuistio pitää kirjaa sanoista joihin on vastattu väärin
     */
    private MokausMuistio muistio;
    /**
     *YhdistaSanat luo tehtävän, jossa tarkoituksena on yhdistä oikeat sanatparit keskenään 
     */
    private YhdistaSanat yhdista;
    /**
     *toistotiheys määrittää, kuinka monen sanan välein kysytään väärin arvattuja sanoja
     * eli toisin sanoen kuinka usein haetaan sana MokausMuistiosta
     */
    private int toistotiheys;

    /**
     *Alustaa hallinta-olion. Ohjelman käynnistyessä siinä ei ole vielä mitään sanoja
     * ennen kuin käyttäjä valitsee, mitä sanoja haluaa harjoitella. Käyttäjä asetetaan sisäänkirjautmisvaiheessa.
     * YhdistaSanat-olio on olemassa vain tehtävän suorituksen ajan.
     */
    public Hallinta() {
        this.sanat = null;
        this.tiedostonlukija = new Tiedostonlukija();
        this.kayttaja = null;
        this.kayttajat = tiedostonlukija.tuoKayttajat(new File("src/sanaohjelma/Kayttajat/kayttajat.txt"));
        this.muistio = new MokausMuistio();
        this.yhdista = null;
        this.toistotiheys = 3;
    }

    /**
     * Lisää uuden käyttäjän järjestelmän. Tallentaa käyttäjän tiedot käyttäjät-olioon
     * ja päivittää tiedoston. Jos annetaan tyhjä tunnus tai salasana, käyttäjää ei luoda.
     * Jos käyttäjät.txt-tiedosto oli tyhjä, luodaan uusi käyttäjät-olio.
     *
     * @param tunnus Käyttäjätunnus jota tarvitaan sisäänkirjautumisessa
     * @param nimi Käyttäjän nimi
     * @param salasana Käyttäjän salasana.
     * @return Palauttaa totuusarvon sen peruustella, tallennettiinko käyttäjä vai ei
     */
    public boolean lisaaKayttaja(String tunnus, String nimi, String salasana) {
        if (tunnus.equals("") || salasana.equals("")) {
            return false;
        }

        Kayttaja kayttaja = new Kayttaja(tunnus, nimi, salasana);

        if (this.kayttajat == null) {
            this.kayttajat = new Kayttajat();
        }

        if (this.kayttajat.lisaaKayttaja(tunnus, kayttaja)) {
            TiedostoonKirjoittaja kirjoittaja = new TiedostoonKirjoittaja();
            kirjoittaja.kirjoitaTiedostoon("src/sanaohjelma/Kayttajat/kayttajat.txt", this.kayttajat.toString());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Hakee käyttäjän tunnuksen ja salasanan perusteella. Jos käyttäjiä ei ole
     * palautetaan null.
     *
     * @param tunnus Käyttäjätunnus
     * @param salasana Käyttäjän salasana
     * @return Tunnusta ja salasanaa vastaava käyttäjä. Jos käyttäjää ei ole, palautuu null.
     */
    public Kayttaja haeKayttaja(String tunnus, String salasana) {
        if (this.kayttajat == null) {
            return null;
        }
        Kayttaja haettuKayttaja = this.kayttajat.haeKayttaja(tunnus, salasana);
        this.kayttaja = haettuKayttaja;

        return this.kayttaja;
    }

    /**
     *Palauttaa käyttäjän nimen.
     */
    public String kayttajanNimi() {
        return this.kayttaja.getNimi();
    }

    /**
     *Palauttaa käyttäjän tilaston merkkijonoesityksen.
     */
    public String kayttajanTilasto() {
        return this.kayttaja.tilasto();
    }

    /**
     * Poistaa käyttäjän käyttäjät-oliosta sekä päivittää muutoksen tiedostoon.
     *
     * @param tunnus Poistettavan käyttäjän käyttäjätunnus
     * @return Palauttaa true, jos käyttäjä poistettiin ja false muuten.
     */
    public boolean poistaKayttaja(String tunnus) {
        if (this.kayttajat.poistaKayttaja(tunnus)) {
            TiedostoonKirjoittaja kirjoittaja = new TiedostoonKirjoittaja();
            kirjoittaja.kirjoitaTiedostoon("src/sanaohjelma/Kayttajat/kayttajat.txt", this.kayttajat.toString());
            return true;
        }
        return false;
    }

    /**
     * Metodi päivittää käyttäjän tilaston tiedostoon eli käytännössä kirjoittaa
     * kaikkien käyttäjien tiedot uudestaan tilastoon.
     */
    public void tallennaTilasto() {
        TiedostoonKirjoittaja kirjoittaja = new TiedostoonKirjoittaja();
        kirjoittaja.kirjoitaTiedostoon("src/sanaohjelma/Kayttajat/kayttajat.txt", this.kayttajat.toString());
    }

    /**
     * Tallentaa sanat-olioon sanaparit tiedostosta.
     *
     * @param tiedostonNimi Sen tiedoston nimi, josta sanat haetaan.
     */
    public void haeSanatTiedostosta(String tiedostonNimi) {
        this.sanat = this.tiedostonlukija.tuoSanat(tiedostonNimi);
    }

    /**
     * Tarjotaa toisen tavan tallentaa sanat ohjelmaan.
     * @param sanat Sanat-olio, jota halutaan käyttää ohjelman suorituksen aikana.
     */
    public void asetaSanatSuoraan(Sanat sanat) {
        this.sanat = sanat;
    }

    /**
     * Tarkistaa onko käyttäjän antama vastaus oikein tarkistaja-olion avulla. 
     * Metodi päivittää samalla käyttäjän tilastoa.
     *
     * @param kysyttySana Käyttäjältä kysytty sana
     * @param annettuVastaus Käyttäjän antama vastaus
     * @param kieli Kieli jolla sana kysyttiin
     * @return Oliko vastaus oikein vai ei
     */
    public boolean vastausOikein(String kysyttySana, String annettuVastaus, String kieli) {
        this.kayttaja.getTilasto().kasvataSanamaaraa();

        Tarkistaja tarkistaja = new Tarkistaja(this.sanat, kieli);
        if (tarkistaja.vastausOikein(kysyttySana, annettuVastaus)) {
            this.kayttaja.getTilasto().kasvataOikeita();
            return true;
        }
        return false;
    }

    /**
     * Tallenetaan muistiin, kuinka usein halutaan toistaa väärin arvattuja sanoja.
     * @param toistotiheys Kuinka mones kysyttävä sana on väärin arvattu sana (jos niitä on).
     */
    public void asetaToistojenTiheys(int toistotiheys) {
        this.toistotiheys = toistotiheys;
    }

    /**
     *Ohjaa sanan valinnan sanavalitsin-oliolle ja palauttaa sen antaman sanan.
     * 
     * @param kieli Kieli jolla halutaan sanan olevan
     * @return Käyttäjältä kysyttävä sana
     */
    public String kysySana(String kieli) {
        if (this.sanat == null) {
            return null;
        }
        Sanavalitsin valitsin = new Sanavalitsin(this.toistotiheys, this.kayttaja.getTilasto(), this.sanat, this.muistio);
        String kysyttavaSana = valitsin.annaSana(kieli);
        return kysyttavaSana;
    }

    /**
     *Palauttaa sanat-olion sisältämät sanaparit merkkijonossa rivinvaihtoineen.
     * 
     * @return Sanaparit muodossa 'sana1 - sana2\nsana2 - sana3\n' jne...
     */
    public String sanatMerkkijono() {
        if (this.sanat == null) {
            return null;
        }
        return this.sanat.toString();
    }

    /**
     *Palauttaa anat-olion sisältämien sanojen määrän.
     */
    public int sanojenMaara() {
        if (this.sanat == null) {
            return 0;
        }
        return this.sanat.sanojenMaara();
    }

    /**
     *Hakee sanan käännöksen tarkistaja-olion avulla.
     * 
     * @param sana Sana jolle halutaan käännös
     * @param kieli Kieli jolla sana on
     * @return käännös
     */
    public String haeOikeaVastaus(String sana, String kieli) {
        Tarkistaja tarkistaja = new Tarkistaja(this.sanat, kieli);
        return tarkistaja.haeOikeaVastaus(sana);
    }

    /**
     * Metodi lisää sanaparin haluttuun tiedostoon. Sanat haetaan ensin tiedostosta väliaikaiseen
     * sanat-olioon, jonka jälkeen sanat-olion merkkijonoesitys kirjoitetaan uudestaan tiedostoon.
     *
     * @param sana1 Sana kielellä 1
     * @param sana2 Sana kielellä 2
     * @param tiedostonNimi Sen tiedoston nimi, johon sanat halutaan lisätä
     */
    public void lisaaSanapari(String sana1, String sana2, String tiedostonNimi) {
        TiedostoonKirjoittaja kirjoittaja = new TiedostoonKirjoittaja();
        Sanat sanatTemp = this.tiedostonlukija.tuoSanat(tiedostonNimi);
        sanatTemp.lisaa(sana1, sana2);
        kirjoittaja.tallennaSanapari("src/sanaohjelma/Sanatiedostot/" + tiedostonNimi, sana1, sana2);
    }

    /**
     *Metodi poistaa sanan tiedostosta. Sanat luetaan ensin väliaikaiseen sanat-olioon, jonka jälkeen
     * oliosta poistetaan haluttu sanapari. Lopuksi sanat kirjoitetaan uudestaan tiedostoon.
     * 
     * @param sana Poistettava sana kielellä 1
     * @param tiedostonNimi Tiedosto josta sana halutaan poistaa
     * @return Onnistuiko sanan poisto vai ei
     */
    public boolean poistaSana(String sana, String tiedostonNimi) {
        TiedostoonKirjoittaja kirjoittaja = new TiedostoonKirjoittaja();
        Sanat sanatTemp = this.tiedostonlukija.tuoSanat(tiedostonNimi);

        if (!sanatTemp.poista(sana)) {
            return false;
        }
        kirjoittaja.kirjoitaTiedostoon("src/sanaohjelma/Sanatiedostot/" + tiedostonNimi, sanatTemp.toString());
        return true;
    }

    /**
     * Hakee Sanatiedostot-kansiossa olevien tiedostojen nimet.
     *
     * @return Nimet listassa
     */
    public ArrayList<String> tiedostojenNimet() {
        return this.tiedostonlukija.tiedostojenNimet("src/sanaohjelma/Sanatiedostot");
    }

    /**
     * Hakee tiedostojen nimet indeksin perusteella. Metodi käyttää hyväkseen
     * edellistä metodia, joka palauttaa listan ja hakee sitten tästä listasta
     * indeksiä vastaavan tiedoston nimen.
     * 
     * @param indeksi Etsittävän tiedoston indeksi
     * @return Tiedoston nimi
     */
    public String haeTiedostonNimi(int indeksi) {
        try {
            return this.tiedostojenNimet().get(indeksi);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Näyttää tiedoston sisällön tiedoston nimen perusteella. Käytännössä 
     * tiedostonlukija lukee tiedostonsisällön sanat-olioon ja palauttaa tämän
     * merkkijonoesityksen.
     *
     * @param tiedostonNimi Tiedoston jonka sisältö halutaan
     * @return Tiedoston sisältö merkkijonona
     */
    public String naytaSisalto(String tiedostonNimi) {
        try {
            return this.tiedostonlukija.tuoSanat(tiedostonNimi).toString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Poistaa Sanatiedostot-kansiosta tiedoston nimen peruustella.
     * 
     * @param tiedostonNimi Poistettavan tiedoston nimi
     * @return Paluattaa false, jos tiedoston poisto epäonnistui ja true muuten
     */
    public boolean poistaTiedosto(String tiedostonNimi) {
        File poistettava = new File("src/sanaohjelma/Sanatiedostot/" + tiedostonNimi);
        return poistettava.delete();
    }

    /**
     *  Siirtää tiedoston Sanatiedostot-kansioon halutusta sijainnista. Käytännössä
     *  metodi muuttaa tiedoston polkua.
     *  HUOM. Alkuperäinen tiedosto häviää.
     *
     * @param tiedostopolku Tiedosto joka halutaan siirtää
     * @return Palauttaa true, jos tiedoston lisäys onnistui ja false muuten
     */
    public boolean lisaaTiedosto(String tiedostopolku) {
        File alkuperainen = new File(tiedostopolku);
        String nimi = alkuperainen.getName();

        return alkuperainen.renameTo(new File("src/sanaohjelma/Sanatiedostot/" + nimi));
    }

    /**
     * Hakee sanaparien yhdistystehtävää varten listan sanoja, joissa sanojen
     * eteen on lisätty numero. Se, että metodia kutsutaan on merkki siitä, 
     * että tehtävä aloitetaan joten ensiksi tallenetaan YhdistaSanat-luokan ilmentymän yhdista-muuttujaan.
     *
     * @param maara Haluttu määrä sanoja
     * @return Lista jossa on sanoja muodossa '1.sana 2.toinensana' jne.
     */
    public ArrayList<String> haeSanatNumerolla(int maara) {
        if (this.sanat == null) {
            return null;
        }
        this.yhdista = new YhdistaSanat(this.sanat);
        this.yhdista.taytaListat(maara);

        return this.yhdista.palautaSanatNumerolla();
    }

    /**
     * Hakee sanaparien yhdistystehtävää varten toisen lista, jossa on edellisessä metodissa
     * haettujen sanojen käännökset kirjaimella merkittynä.
     *
     * @return Lista jossa sanoja muodossa 'a.word b.anotherword'
     */
    public ArrayList<String> haeKaannoksetKirjaimella() {
        if (this.yhdista == null) {
            return null;
        }
        return this.yhdista.palautaKaannoksetKirjaimella();
    }

    /**
     * Tarkistaa sanaparien yhdistystehvään annetun vastauksen. Metodi päivittää samalla
     * käyttäjän tilastoa.
     * 
     * @param vastaus Käyttäjän antama vastaus muodossa '1a'
     * @return True jos käyttäjän vastaus on oikein, false muuten
     */
    public boolean tarkistaYhdistaVastaus(String vastaus) {
        if (this.yhdista == null) {
            return false;
        }
        //vastauksia on yhtä paljon kuin kysymyksiä, joten kasvatetaan tässä kysyttyjen sanojen määrää
        this.kayttaja.getTilasto().kasvataSanamaaraa();

        boolean vastasikoOikein = this.yhdista.tarkista(vastaus);

        if (vastasikoOikein) {
            this.kayttaja.getTilasto().kasvataOikeita();
        }
        return vastasikoOikein;
    }

    /**
     * Metodi hakee yhdista-oliolta oikeat vastaukset sanaparien yhdistystehtävään.
     * Samalla metodi poistaa yhdista-olion muistista, koska tehtävä lopetettiin ja 
     * sitä ei enää tarvita.
     * 
     * @return Paluttaa tehtävän vastauksen muodossa '1a 2b 3c' jne.
     */
    public String oikeaRivi() {
        if (this.yhdista == null) {
            return null;
        }
        String oikeaRivi = this.yhdista.oikeatVastaukset();
        this.yhdista = null;

        return oikeaRivi;
    }

    /**
     * Poistaa käyttäjän muistista.
     *
     */
    public void kirjaaKayttajaUlos() {
        this.kayttaja = null;
    }

    /**
     * Tarkistaa onko sanat-olio olemassa.
     *
     * @return False, jos oliota ei ole, true muuten
     */
    public boolean onkoSanatAsetettu() {
        if (this.sanat == null) {
            return false;
        }
        return true;
    }
}
