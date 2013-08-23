package sanaohjelma.kayttoliittyma;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import sanaohjelma.Sanaohjelma;
import sanaohjelma.sovelluslogiikka.Kielet;

public class Tekstikayttoliittyma {

    private Sanaohjelma ohjelma;
    private Scanner lukija;

    public Tekstikayttoliittyma(Scanner lukija, Sanaohjelma ohjelma) {
        this.ohjelma = ohjelma;
        this.lukija = lukija;
    }

    public void kirjautuminen() {
        System.out.println("Tervetuloa");
        System.out.println("");

        while (true) {
            System.out.print("Kirjaudu sisään (1) tai luo tunnus (2): ");
            String valinta = lukija.nextLine();
            if (valinta.equals("1")) {
                this.kirjauduSisaan();
                break;
            } else if (valinta.equals("2")) {
                this.luoTunnus();
            } else {
                System.out.println("Valitse 1 tai 2");
            }
        }

    }

    public void luoTunnus() {
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

        if (this.ohjelma.lisaaKayttaja(tunnus, salasana, nimi)) {
            System.out.println("Tunnuksen luonti onnistui!");
        } else {
            System.out.println("Tunnus on jo olemassa!");
        }
    }

    public void kirjauduSisaan() {
        System.out.print("Anna käyttäjätunnus: ");
        String tunnus = lukija.nextLine();
        System.out.print("Anna salasana: ");
        String salasana = lukija.nextLine();
        System.out.println("");

        if (this.ohjelma.haeKayttaja(tunnus, salasana) != null) {
            System.out.println("Hei " + this.ohjelma.kayttajanNimi() + "!");
            System.out.println("======================");
            System.out.println("");
            this.kaynnista();
        } else {
            System.out.println("Väärä käyttäjätunnus tai salasana!");
        }
    }

    public void kaynnista() {
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

    public void valikko() {
        System.out.println("Valitse seuraavista toiminnoista: ");
        System.out.println("    1 - Listaa tiedostot");
        System.out.println("    2 - Harjoittele sanoja");
        System.out.println("    3 - Näytä omat tiedot");
        System.out.println("    0 - Lopeta");
        System.out.println("");
    }

    public void tiedostotLuettelona() {
        int indeksi = 0;

        for (String nimi : this.ohjelma.tiedostojenNimet()) {
            System.out.println("    [" + indeksi + "] " + nimi);
            indeksi++;
        }
    }

    public String valitseTiedosto() {
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

    /**
     *
     */
    public void naytaTiedot() {
        System.out.println("Tietosi:");
        System.out.println("");
        System.out.println(this.ohjelma.kayttajanTilasto());
        System.out.println("");
    }

    public void valitseHarjoittelualue() {
        String tiedostonNimi = this.valitseTiedosto();

        if (tiedostonNimi != null) {
            this.ohjelma.asetaSanat(tiedostonNimi);
        } else {
            System.out.println("Tiedostoa ei löydy!");
        }

    }

    public void tehtavaValikko() {
        this.valitseHarjoittelualue();

        while (true) {
            System.out.println("Valitse seuraavista:");
            System.out.println("    1 - Näytä sanat");
            System.out.println("    2 - Käännä sanoja kielestä " + Kielet.kieli1 + " kieleen " + Kielet.kieli2);
            System.out.println("    3 - Käännä  sanoja kielestä " + Kielet.kieli2 + " kieleen " + Kielet.kieli1);
            System.out.println("    4 - Yhdistä oikeat sanaparit keskenään");
            System.out.println("    0 - Poistu");
            System.out.println("");

            String valinta = lukija.nextLine();


            if (valinta.equals("1")) {
                this.naytaSanat();
            } else if (valinta.equals("2")) {
                this.kaannosTehtava(Kielet.kieli1);
            } else if (valinta.equals("3")) {
                this.kaannosTehtava(Kielet.kieli2);
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

    public void naytaSanat() {
        System.out.println(this.ohjelma.sanatMerkkijono());
    }

    /**
     *
     * @param kerrat
     * @param kaannettavaKieli
     */
    public void kysySanat(int kerrat, String kaannettavaKieli) {
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

    /**
     *
     * @param kysyttySana
     * @param annettuVastaus
     * @param kieli
     * @return
     */
    public boolean tarkistaAnnettuKaannos(String kysyttySana, String annettuVastaus, String kieli) {
        if (this.ohjelma.vastausOikein(kysyttySana, annettuVastaus, kieli)) {
            System.out.println("Oikein!");
            return true;
        } else {
            System.out.println("Väärin! Oikea vastaus: " + this.ohjelma.haeOikeaVastaus(kysyttySana, kieli));
            return false;
        }
    }

    /**
     *
     * @param kaannettavaKieli
     */
    public void kaannosTehtava(String kaannettavaKieli) {
        int kerrat;

        while (true) {
            System.out.print("Monta kertaa kysytään? ");


            try {
                kerrat = Integer.parseInt(lukija.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("");
                System.out.println("Anna jokin luku!");
            }
        }

        System.out.println("");

        kysySanat(kerrat, kaannettavaKieli);
    }

    /**
     * Metodi luo tehtävän, jossa pitäisi osata yhdistää oikeat sanaparit
     * keskenään. Tehtävä voisi näyttää esimerkiksi seuraavalta:
     *
     * 1.kissa a.dog 2.koira b.bird 3.lintu c.cat
     *
     * Nyt oikeat vastaukset olisivat: 1c, 2a, 3b.
     *
     * Metodi hakee ensiksi sanoja ja tallentaa ne sanat-listaan. Samalla metodi
     * tallentaa kaannokset-listaan sanat-listassa olevien sanojen indeksit.
     * Kaannokset-lista näyttäisi siis esimerkin tapauksessa seuraavalta: [0, 1,
     * 2]. Sekoitetaan kaannokset-lista, jolloin siitä tulisi esim. [1, 2, 0].
     * Nämä luvut kuvaavat siis sanat-listassa olevien sanojen indeksejä. Näin
     * sanojen käännökset saadaan tulostettua oikean puoleiseen listaan
     * sekoitetussa järjestyksessä, kun valitaan indeksiä vastaava sana
     * sanat-listasta ja sitten haetaan sen käännös. Samalla saadaan myös
     * tulostettu sanan eteen oikea kirjain, kun muutetaan kaannokset-listan
     * indeksit merkeiksi alkaen esim. a:sta (a:ta vastaa merkkikoodi on 97,
     * b:tä 98 jne.). Nyt saadaan tulostettu siis ylläolevan näköinen taulukko.
     *
     * Vastauksia tarkistettaessa verrataan annettua lukua ja kirjainta
     * keskenään. Jos vastaus on esimerkiksi 1c, haetaan kaanokset-taulukosta
     * "kohdassa c" oleva "käännös". Koska c:tä vastaa merkkikoodi 99,
     * tiedetään, että käyttäjä viittaa "käännökseen", joka sijaitsee
     * kaannokset-listan indeksissä 2 (99-97 = 2). Tältä kohdalta löydetään
     * sanat-listan indeksi, eli sanan indeksi, jonka perusteella käyttäjälle
     * näytettiin käännetty sana. Jos tämä indeksi vastaa käyttäjän vastauksessa
     * antamaa ekaa lukua, on vastaus oikein.
     */
    public void yhdistaSanat() {
        int maara = -99;

        while (true) {
            System.out.println("Monta sanaparia annetaan? (Maksimi on 26)");
            try {
                maara = Integer.parseInt(lukija.nextLine());
                if (maara < 2 ||  maara > 26) {
                    System.out.println("Luvun pitää olal väliltä 2 - 26");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Anna jokin luku!");
            }
        }

        if (maara > this.ohjelma.sanojenMaara()) {
            System.out.println("Järjestelmässä ei ole niin paljon sanapareja. Kysytään " + this.ohjelma.sanojenMaara() + " sanaa.");
            maara = this.ohjelma.sanojenMaara();
        }

        ArrayList<String> sanat = new ArrayList<String>();
        ArrayList<Integer> kaannokset = new ArrayList<Integer>();


        int kierros = 0;

        while (kierros < maara) {
            String sana = this.ohjelma.kysySana(Kielet.kieli1);

            if (sanat.contains(sana)) {
                continue;
            }
            sanat.add(sana);
            kaannokset.add(kierros);
            kierros++;
        }

        Collections.shuffle(kaannokset);

        System.out.println("Yhdistä: ");
        System.out.println("");
        tulostaListat(kaannokset, sanat);
        System.out.println("");

        System.out.println("Anna vastaukset muodossa '1a':");
        int oikein = vastaukset(kaannokset);

        tulos(oikein, maara, kaannokset);
    }

    /**
     *
     */
    public void tulostaListat(ArrayList<Integer> kaannokset, ArrayList<String> sanat) {
        for (int i = 0; i < kaannokset.size(); i++) {
            String kaannos = this.ohjelma.haeOikeaVastaus(sanat.get(kaannokset.get(i)), Kielet.kieli1);
            System.out.println(i + 1 + "." + sanat.get(i) + "  " + (char) (i + 97) + "." + kaannos);
        }
    }

    public int vastaukset(ArrayList<Integer> kaannokset) {
        int oikein = 0;
        for (int i = 0; i < kaannokset.size(); i++) {
            String vastaus = lukija.nextLine();
            if (vastaus.isEmpty()) {
                continue;
            }
            //jos vastaus muodossa 1a, kohdassa a olevan arvon pitää vastata nyt ykköstä jotta vastaus oikein
            int kohta = (int) vastaus.charAt(vastaus.length() - 1) - 97;
            int sananIndeksi = -99;

            try {
                sananIndeksi = Integer.parseInt(vastaus.substring(0, vastaus.length() - 1)) - 1;
                if (sananIndeksi == kaannokset.get(kohta)) {
                    oikein++;
                    this.ohjelma.kasvataOikeinVastattuja(oikein);
                }
            } catch (Exception e) {
            }
        }
        return oikein;
    }

    public void tulos(int oikein, int maara, ArrayList<Integer> kaannokset) {
        System.out.println("Sait oikein " + oikein + "/" + maara);
        System.out.print("Oikea rivi: ");
        for (int i = 0; i < kaannokset.size(); i++) {
            System.out.print(kaannokset.get(i) + 1 + "" + (char) (i + 97));
            System.out.print(" ");
        }
        System.out.println("");
    }

    public void lopetus() {
        System.out.println("Tietosi:");
        System.out.println(this.ohjelma.kayttajanTilasto());

        this.ohjelma.tallennaTilasto();
    }
}
