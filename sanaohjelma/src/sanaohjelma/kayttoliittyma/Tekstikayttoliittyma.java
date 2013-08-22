package sanaohjelma.kayttoliittyma;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import sanaohjelma.Sanaohjelma;
import sanaohjelma.sovelluslogiikka.Kielet;

public class Tekstikayttoliittyma {
    private Sanaohjelma ohjelma;
    private Scanner lukija;

    /**
     *
     * @param lukija
     * @param ohjelma
     */
    public Tekstikayttoliittyma(Scanner lukija, Sanaohjelma ohjelma) {
        this.ohjelma = ohjelma;
        this.lukija = lukija;
    }

    /**
     *
     */
    public void kirjautuminen() {
        System.out.println("Tervetuloa");
        System.out.println("");
        System.out.print("Anna käyttäjätunnus: ");
        String tunnus = lukija.nextLine();
        System.out.print("Anna salasana: ");
        String salasana = lukija.nextLine();
        System.out.println("");
        if (this.ohjelma.haeKayttaja(tunnus, salasana) != null) {
            this.naytaTiedot();
            this.valitseTiedostot();
            this.kaynnista();
        } else {
            System.out.println("Väärä käyttäjätunnus tai salasana!");
        }
    }
    
    /**
     *
     */
    public void naytaTiedot() {
        System.out.println("Hei " + this.ohjelma.kayttajanNimi() + "!");
        System.out.println("Tietosi:");
        System.out.println("");
        System.out.println(this.ohjelma.kayttajanTilasto());
        System.out.println("");
    }
    
    /**
     *
     */
    public void valitseTiedostot() {
        System.out.println("Valitse seuraavista tiedostoista harjoittelualue:");
        System.out.println("");
        for (int i = 0; i < this.ohjelma.tiedostojenNimet().size(); i++) {
            System.out.println(i + " " + this.ohjelma.tiedostojenNimet().get(i));
        }
        System.out.println("");
        System.out.print("Anna tiedostoa vastaava luku:");
       
        int valinta = Integer.parseInt(lukija.nextLine());

        this.ohjelma.valitseTiedostot(this.ohjelma.tiedostojenNimet().get(valinta));
        System.out.println("");
        
    }

    /**
     *
     */
    public void kaynnista() {
        while (true) {
            this.valikko();

            String valinta = lukija.nextLine();
            System.out.println("");

            if (valinta.equals("0")) {
                lopetus();
                break;
            }
            if (valinta.equals("1")) {
                naytaSanat();
            } else if (valinta.equals("2")) {
                kaannosTehtava(Kielet.kieli1);
            } else if (valinta.equals("3")){
                kaannosTehtava(Kielet.kieli2);
            }else if (valinta.equals("4")) {
                lisaaSana();
            } else if (valinta.equals("5")) {
                poistaSana();
            } else if (valinta.equals("6")) {
                yhdistaSanat();
            } else {
                valikko();
            }

            System.out.println("");
        }

        System.out.println("Kiitos hei!");
    }

    /**
     *
     */
    public void valikko() {
        System.out.println("Valitse seuraavista toiminnoista: ");
        System.out.println("    1 - Näytä kaikki sanat");
        System.out.println("    2 - Käännöstehtävä " + Kielet.kieli1 + " - " + Kielet.kieli2 );
        System.out.println("    3 - Käännöstehtävä " + Kielet.kieli2 + " - " + Kielet.kieli1);
        System.out.println("    4 - Lisää sana");
        System.out.println("    5 - Poista sana");
        System.out.println("    6 - Yhdistä sanat");
        System.out.println("    0 - lopeta");

        System.out.println("");
    }

    /**
     *
     * @param kerrat
     * @param kaannettavaKieli
     */
    public void kysySanat(int kerrat, String kaannettavaKieli) {
        int oikein = 0;
        
        for (int i = 0; i < kerrat; i++) {
            String kysyttavaSana = this.ohjelma.annaSana(kaannettavaKieli);
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
     *
     */
    public void naytaSanat() {
        System.out.println(this.ohjelma.naytaSanat());
    }

    /**
     *
     */
    public void lisaaSana() {
        System.out.print("Anna sana kielellä " + Kielet.kieli1 + ": " );
        String kieli1 = lukija.nextLine();
        System.out.print("Anna sana kielellä " + Kielet.kieli2 + ": ");
        String kieli2 = lukija.nextLine();

        this.ohjelma.lisaaSanapari(kieli1, kieli2);
    }

    /**
     *
     */
    public void poistaSana() {
        System.out.print("Anna poistettava sana kielellä " + Kielet.kieli1 + ": ");
        String sana = lukija.nextLine();

        this.ohjelma.poistaSana(sana);
    }

    /**
     *Metodi luo tehtävän, jossa pitäisi osata yhdistää oikeat sanaparit keskenään.
     * Tehtävä voisi näyttää esimerkiksi seuraavalta:
     * 
     *      1.kissa  a.dog
     *      2.koira  b.bird
     *      3.lintu  c.cat
     * 
     * Nyt oikeat vastaukset olisivat: 1c, 2a, 3b.
     * 
     * Metodi hakee ensiksi sanoja ja tallentaa ne sanat-listaan. Samalla metodi
     * tallentaa kaannokset-listaan sanat-listassa olevien sanojen indeksit. Kaannokset-lista näyttäisi siis
     * esimerkin tapauksessa seuraavalta: [0, 1, 2]. Sekoitetaan kaannokset-lista, jolloin siitä tulisi esim.
     * [1, 2, 0]. Nämä luvut kuvaavat siis sanat-listassa olevien sanojen indeksejä. Näin sanojen käännökset saadaan
     * tulostettua oikean puoleiseen listaan sekoitetussa järjestyksessä, kun valitaan indeksiä vastaava sana sanat-listasta
     * ja sitten haetaan sen käännös. Samalla saadaan myös tulostettu sanan eteen oikea kirjain, kun muutetaan
     * kaannokset-listan indeksit merkeiksi alkaen esim. a:sta (a:ta vastaa merkkikoodi on 97, b:tä 98 jne.).
     * Nyt saadaan tulostettu siis ylläolevan näköinen taulukko.
     * 
     * Vastauksia tarkistettaessa verrataan annettua lukua ja kirjainta keskenään. Jos vastaus on esimerkiksi 1c,
     * haetaan kaanokset-taulukosta "kohdassa c" oleva "käännös". Koska c:tä vastaa merkkikoodi 99, tiedetään, että
     * käyttäjä viittaa "käännökseen", joka sijaitsee kaannokset-listan indeksissä 2 (99-97 = 2). Tältä kohdalta
     * löydetään sanat-listan indeksi, eli sanan indeksi, jonka perusteella käyttäjälle näytettiin käännetty sana.
     * Jos tämä indeksi vastaa käyttäjän vastauksessa antamaa ekaa lukua, on vastaus oikein.
     */
    public void yhdistaSanat() {
        System.out.println("Monta sanaparia annetaan?");
        int maara = Integer.parseInt(lukija.nextLine());
        
        if (maara > this.ohjelma.sanojenMaara()) {
            System.out.println("Järjestelmässä ei ole niin paljon sanapareja. Kysytään " + this.ohjelma.sanojenMaara() + " sanaa." );
            maara = this.ohjelma.sanojenMaara();
        }

        ArrayList<String> sanat = new ArrayList<String>();
        ArrayList<Integer> kaannokset = new ArrayList<Integer>();

        int kierros = 0;
        
         while(kierros < maara) {
            String sana = this.ohjelma.annaSana(Kielet.kieli1);
            
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
        
        for (int i = 0; i < kaannokset.size(); i++) {
            String kaannos = this.ohjelma.haeOikeaVastaus(sanat.get(kaannokset.get(i)), Kielet.kieli1);
            System.out.println(i + 1 + "." + sanat.get(i) + "  " + (char) (i + 97) + "." + kaannos);
        }
        System.out.println("");
        System.out.println("Anna vastaukset muodossa '1a':");

        int oikein = 0;
        for (int i = 0; i < kaannokset.size(); i++) {
            String vastaus = lukija.nextLine();
            if (vastaus.isEmpty()) {
                continue;
            }
            
            //jos vastaus muodossa 1a, kohdassa a olevan arvon pitää vastata nyt ykköstä jotta vastaus oikein
            int kohta = (int)vastaus.charAt(vastaus.length() - 1) - 97;
            int sananIndeksi = Integer.parseInt(vastaus.substring(0, vastaus.length() - 1)) - 1;
            
            if (sananIndeksi == kaannokset.get(kohta)) {
                oikein++;
            }
            

        }
        System.out.println("");
        System.out.println("Sait oikein " + oikein + "/" + maara);
        System.out.print("Oikea rivi: ");
        for (int i = 0; i < kaannokset.size(); i++) {
            System.out.print(kaannokset.get(i) + 1 + "" + (char)(i + 97));
            System.out.print(" ");
        }
        System.out.println("");
    }
    
    /**
     *
     */
    public void lopetus() {
        System.out.println("Tietosi:");
        System.out.println(this.ohjelma.kayttajanTilasto());
        
        this.ohjelma.tallennaTilasto();
    }
}
