package sanaohjelma.kayttoliittyma;

import java.util.ArrayList;
import java.util.Scanner;
import sanaohjelma.Hallinta;
import sanaohjelma.sovelluslogiikka.Kielet;

/**
 *
 * Sisältää käyttäjän tekstikäyttöliittymän
 */
public class Tekstikayttoliittyma {

    private Hallinta ohjelma;
    private Scanner lukija;

    public Tekstikayttoliittyma(Hallinta ohjelma) {
        this.ohjelma = ohjelma;
        this.lukija = new Scanner(System.in, "UTF-8");
    }

    public void kirjautuminen() {
        System.out.println("Tervetuloa");
        System.out.println("");

        while (true) {
            System.out.print("Kirjaudu sisään (1) tai luo tunnus (2): ");
            String valinta = lukija.nextLine();
            System.out.println("");

            if (valinta.equals("1")) {
                if (this.kirjauduSisaan()) {
                    //kayttaja pääsi sisään joten voidaan poistua silmukasta
                    break;
                }
            } else if (valinta.equals("2")) {
                this.luoTunnus();
            } else {
                System.out.println("Valitse 1 tai 2");
            }

            System.out.println("");
        }

    }

    private void luoTunnus() {
        String tunnus;
        String salasana;
        String nimi;

        while (true) {
            System.out.print("Anna käyttäjätunnus: ");
            tunnus = lukija.nextLine();
            System.out.print("Anna salasana: ");
            salasana = lukija.nextLine();
            System.out.print("Anna nimesi: ");
            nimi = lukija.nextLine();

            if (tunnus.isEmpty() || salasana.isEmpty()) {
                System.out.println("Tunnus ja salasanat eivät saa olla tyhjiä!");
                continue;
            }

            break;
        }

        if (this.ohjelma.lisaaKayttaja(tunnus, nimi, salasana)) {
            System.out.println("Tunnuksen luonti onnistui!");
        } else {
            System.out.println("Tunnus on jo olemassa!");
        }
    }

    private boolean kirjauduSisaan() {
        System.out.print("Anna käyttäjätunnus: ");
        String tunnus = lukija.nextLine();
        System.out.print("Anna salasana: ");
        String salasana = lukija.nextLine();
        System.out.println("");

        if (this.ohjelma.haeKayttaja(tunnus, salasana) != null) {
            String tervehdys = "Hei " + this.ohjelma.kayttajanNimi() + "!";
            System.out.println(tervehdys);
            tulostaMerkkeja('=', tervehdys.length());
            System.out.println("");
            this.kaynnista();
        } else {
            System.out.println("Väärä käyttäjätunnus tai salasana!");
            return false;
        }
        return true;
    }

    private void kaynnista() {
        while (true) {
            this.valikko();

            String valinta = lukija.nextLine();
            System.out.println("");

            if (valinta.equals("0")) {
                this.lopetus();
                break;
            }
            if (valinta.equals("1")) {
                this.tiedostotLuettelona();
            } else if (valinta.equals("2")) {
                this.tehtavaValikko();
            } else if (valinta.equals("3")) {
                this.naytaTiedot();
            } else {
                System.out.println("Anna jotain toimintoa vastaava luku!");
            }

            System.out.println("");
        }

        System.out.println("Kiitos hei!");
    }

    private void valikko() {
        System.out.println("Valitse seuraavista toiminnoista: ");
        System.out.println("    1 - Listaa tiedostot");
        System.out.println("    2 - Harjoittele sanoja");
        System.out.println("    3 - Näytä omat tiedot");
        System.out.println("    0 - Lopeta");
        System.out.println("");
    }

    private void tiedostotLuettelona() {
        int indeksi = 0;

        for (String nimi : this.ohjelma.tiedostojenNimet()) {
            System.out.println("    [" + indeksi + "] " + nimi);
            indeksi++;
        }
    }

    private String valitseTiedosto() {
        System.out.println("Valitse tiedosto:");
        this.tiedostotLuettelona();
        System.out.println("");

        int valinta = -99;

        while (true) {
            System.out.print("Anna tiedoston numero: ");
            try {
                valinta = Integer.parseInt(lukija.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Anna jokin luku!");
                System.out.println("");
            }
        }
        System.out.println("");

        String tiedostonNimi = this.ohjelma.haeTiedostonNimi(valinta);

        return tiedostonNimi;
    }

    private void naytaTiedot() {
        System.out.println("Tietosi:");
        System.out.println("");
        System.out.println(this.ohjelma.kayttajanTilasto());
        System.out.println("");
    }

    private void valitseHarjoittelualue() {
        String tiedostonNimi = this.valitseTiedosto();

        if (tiedostonNimi != null) {
            this.ohjelma.haeSanatTiedostosta(tiedostonNimi);
        } else {
            System.out.println("Tiedostoa ei löydy!");
        }

    }

    private void tehtavaValikko() {
        this.valitseHarjoittelualue();

        while (true) {
            System.out.println("Valitse seuraavista:");
            System.out.println("    1 - Näytä sanat");
            System.out.println("    2 - Käännä sanoja kielestä " + Kielet.getKieli1() + " kieleen " + Kielet.getKieli2());
            System.out.println("    3 - Käännä  sanoja kielestä " + Kielet.getKieli2() + " kieleen " + Kielet.getKieli1());
            System.out.println("    4 - Yhdistä oikeat sanaparit keskenään");
            System.out.println("    0 - Poistu");
            System.out.println("");

            String valinta = lukija.nextLine();


            if (valinta.equals("1")) {
                this.naytaSanat();
            } else if (valinta.equals("2")) {
                this.kaannosTehtava(Kielet.getKieli1());
            } else if (valinta.equals("3")) {
                this.kaannosTehtava(Kielet.getKieli2());
            } else if (valinta.equals("4")) {
                this.yhdistaSanat();
            } else if (valinta.equals("0")) {
                break;
            } else {
                System.out.println("Anna jokin luku valikosta!");
            }
            System.out.println("");
        }
    }

    private void naytaSanat() {
        String sanat = this.ohjelma.sanatMerkkijono();
        if (sanat == null) {
            System.out.println("Tiedostossa ei ole sanoja!");
            return;
        }
        System.out.println(sanat);
    }

    private void kaannosTehtava(String kaannettavaKieli) {
        if (!this.ohjelma.onkoSanatAsetettu()) {
            System.out.println("Tiedostossa ei ole sanoja!");
            return;
        }

        int kerrat;
        while (true) {
            System.out.print("Monta kertaa kysytään? ");


            try {
                kerrat = Integer.parseInt(lukija.nextLine());
                if (kerrat < 0) {
                    System.out.println("Anna jokin positiivinen luku!");
                    return;
                }
                break;
            } catch (Exception e) {
                System.out.println("");
                System.out.println("Anna jokin luku!");
            }
        }

        System.out.println("");

        kysySanat(kerrat, kaannettavaKieli);
    }

    private void kysySanat(int kerrat, String kaannettavaKieli) {
        int oikein = 0;

        for (int i = 0; i < kerrat; i++) {
            String kysyttavaSana = this.ohjelma.kysySana(kaannettavaKieli);
            if (kysyttavaSana == null) {
                System.out.println("Tiedostossa ei ole sanoja!");
                break;
            }
            System.out.print("Anna käännös sanalle " + kysyttavaSana + ": ");
            String annettuVastaus = lukija.nextLine();
            if (this.tarkistaAnnettuKaannos(kysyttavaSana, annettuVastaus, kaannettavaKieli)) {
                oikein++;
            }
            System.out.println("");
        }
        System.out.println("Sait oikein: " + oikein + "/" + kerrat);
    }

    private boolean tarkistaAnnettuKaannos(String kysyttySana, String annettuVastaus, String kieli) {
        if (this.ohjelma.vastausOikein(kysyttySana, annettuVastaus, kieli)) {
            System.out.println("Oikein!");
            return true;
        } else {
            System.out.println("Väärin! Oikea vastaus: " + this.ohjelma.haeOikeaVastaus(kysyttySana, kieli));
            return false;
        }
    }

    private void yhdistaSanat() {

        if (!this.ohjelma.onkoSanatAsetettu()) {
            System.out.println("Tiedostossa ei ole sanoja!");
            return;
        }
        int maara = -99;

        while (true) {
            System.out.println("Monta sanaparia annetaan? (Maksimi on 26)");
            try {
                maara = Integer.parseInt(lukija.nextLine());
                if (maara < 2 || maara > 26) {
                    System.out.println("Luvun pitää olla väliltä 2 - 26");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Anna jokin luku!");
            }
        }

        if (maara > this.ohjelma.sanojenMaara()) {
            System.out.println("Tiedostossa ei ole niin paljon sanapareja. Kysytään " + this.ohjelma.sanojenMaara() + " sanaa.");
            maara = this.ohjelma.sanojenMaara();
        }

        this.tulostaListat(maara);
        int oikein = this.kysyVastaukset(maara);
        this.tulos(oikein, maara);
    }

    private void tulostaListat(int maara) {
        //sanat-listassa on sanoja muodossa 1.kissa 2.koira 3.hevonen
        ArrayList<String> sanat = this.ohjelma.haeSanatNumerolla(maara);
        //kaannokset-listassa on sanat-listassa olevien sanojen käännöksiä muodossa a.cat b.horse c.dog
        ArrayList<String> kaannokset = this.ohjelma.haeKaannoksetKirjaimella();

        System.out.println("Yhdistä: ");
        System.out.println("");

        for (int i = 0; i < sanat.size(); i++) {
            System.out.println(sanat.get(i) + " " + kaannokset.get(i));
        }
        System.out.println("");
    }

    private int kysyVastaukset(int maara) {
        System.out.println("Anna vastaukset muodossa '1a':");
        int oikein = 0;

        for (int i = 0; i < maara; i++) {
            String vastaus = lukija.nextLine();

            if (vastaus.isEmpty()) {
                continue;
            }

            if (this.ohjelma.tarkistaYhdistaVastaus(vastaus)) {
                oikein++;
            }
        }
        return oikein;
    }

    private void tulos(int oikein, int maara) {
        System.out.println("Sait oikein " + oikein + "/" + maara);
        System.out.print("Oikea rivi: " + this.ohjelma.oikeaRivi());
        System.out.println("");
    }

    private void lopetus() {
        System.out.println("Tietosi:");
        System.out.println(this.ohjelma.kayttajanTilasto());
        this.ohjelma.tallennaTilasto();
    }

    private static void tulostaMerkkeja(char merkki, int kerrat) {
        for (int i = 0; i < kerrat; i++) {
            System.out.print(merkki);
        }
        System.out.println("");
    }
}
