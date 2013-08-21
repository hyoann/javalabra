package sanaohjelma.kayttoliittyma;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import sanaohjelma.Sanaohjelma;
import sanaohjelma.sovelluslogiikka.Tiedostonlukija;

public class Tekstikayttoliittyma {

    private Sanaohjelma ohjelma;
    private Scanner lukija;

    public Tekstikayttoliittyma(Tiedostonlukija tiedostonlukija, Scanner lukija) {
        this.ohjelma = new Sanaohjelma(tiedostonlukija);
        this.lukija = lukija;
    }

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
    
    public void naytaTiedot() {
        System.out.println("Hei " + this.ohjelma.kayttajanNimi() + "!");
        System.out.println("Tietosi:");
        System.out.println("");
        System.out.println(this.ohjelma.kayttajanTilasto());
        System.out.println("");
    }
    
    public void valitseTiedostot() {
        System.out.println("Valitse seuraavista tiedostoista harjoittelualue:");
        System.out.println("");
        for (int i = 0; i < this.ohjelma.tiedostojenNimet().size(); i++) {
            System.out.println(i + " " + this.ohjelma.tiedostojenNimet().get(i));
        }
        System.out.println("");
        System.out.print("Anna tiedostoa vastaava luku. Kirjoita 'X' viimeisen tiedoston jälkeen. Tyhjällä harjoittelualueeksi otetaan kaikki tiedostot: ");
       
        int valinta = Integer.parseInt(lukija.nextLine());

        this.ohjelma.valitseTiedostot(this.ohjelma.tiedostojenNimet().get(valinta));
        System.out.println("");
        
    }

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
                kaannosTehtava("suomi");
            } else if (valinta.equals("3")) {
                kaannosTehtava("vieras");
            } else if (valinta.equals("4")) {
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

    public void valikko() {
        System.out.println("Valitse seuraavista toiminnoista: ");
        System.out.println("    1 - Näytä kaikki sanat");
        System.out.println("    2 - Käännöstehtävä suomesta venäjään");
        System.out.println("    3 - Käännöstehtävä venäjästä suomeen");
        System.out.println("    4 - Lisää sana");
        System.out.println("    5 - Poista sana");
        System.out.println("    6 - Yhdistä sanat");
        System.out.println("    0 - lopeta");

        System.out.println("");
    }

    public void kysySanat(int kerrat, String kaannettavaKieli) {
        for (int i = 0; i < kerrat; i++) {
            String kysyttavaSana = this.ohjelma.annaSana(kaannettavaKieli);
            System.out.print("Anna käännös sanalle " + kysyttavaSana + ": ");
            String annettuVastaus = lukija.nextLine();
            this.tarkistaAnnettuKaannos(kysyttavaSana, annettuVastaus, kaannettavaKieli);
        }
    }

    public void tarkistaAnnettuKaannos(String kysyttySana, String annettuVastaus, String kieli) {
        if (this.ohjelma.vastausOikein(kysyttySana, annettuVastaus, kieli)) {
            System.out.println("Oikein!");
        } else {
            System.out.println("Väärin! Oikea vastaus: " + this.ohjelma.haeOikeaVastaus(kysyttySana, kieli));
        }
        System.out.println("");
    }

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

    public void naytaSanat() {
        System.out.println(this.ohjelma.naytaSanat());
    }

    public void lisaaSana() {
        System.out.print("Anna sana suomeksi: ");
        String suomi = lukija.nextLine();
        System.out.print("Anna sana venäjäksi: ");
        String vieras = lukija.nextLine();

        this.ohjelma.lisaaSanapari(suomi, vieras);
    }

    public void poistaSana() {
        System.out.print("Anna poistettava sana suomeksi: ");
        String sana = lukija.nextLine();

        this.ohjelma.poistaSana(sana);
    }

    public void yhdistaSanat() {
        System.out.println("Monta sanaparia annetaan?");
        int maara = Integer.parseInt(lukija.nextLine());
        
        if (maara > this.ohjelma.sanojenMaara()) {
            System.out.println("Järjestelmässä ei ole niin paljon sanapareja. Kysytään " + this.ohjelma.sanojenMaara() + " sanaa." );
            maara = this.ohjelma.sanojenMaara();
        }

        ArrayList<String> sanat = new ArrayList<String>();
        ArrayList<Integer> indeksit = new ArrayList<Integer>();

        int kierros = 0;
        
         while(kierros < maara) {
            String sana = this.ohjelma.annaSana("suomi");
            
            if (sanat.contains(sana)) {
                continue;
            }
            sanat.add(sana);
            indeksit.add(kierros);
            kierros++;
        }

        Collections.shuffle(indeksit);

        System.out.println("Yhdistä: ");
        System.out.println("");
        
        for (int i = 0; i < indeksit.size(); i++) {
            String kaannos = this.ohjelma.haeOikeaVastaus(sanat.get(indeksit.get(i)), "suomi");
            System.out.println(i + 1 + "." + sanat.get(i) + "  " + (char) (i + 97) + "." + kaannos);
        }
        System.out.println("");
        System.out.println("Anna vastaukset muodossa '1a':");

        int oikein = 0;
        for (int i = 0; i < indeksit.size(); i++) {
            String vastaus = lukija.nextLine();
            if (vastaus.isEmpty()) {
                continue;
            }
            
            //jos vastaus muodossa 1a, kohdassa a olevan arvon pitää vastata nyt ykköstä jotta vastaus oikein
            int kohta = (int)vastaus.charAt(vastaus.length() - 1) - 97;
            int sananIndeksi = Integer.parseInt(vastaus.substring(0, vastaus.length() - 1)) - 1;
            
            if (sananIndeksi == indeksit.get(kohta)) {
                oikein++;
            }
            

        }
        System.out.println("");
        System.out.println("Sait oikein " + oikein + "/" + maara);
        System.out.print("Oikea rivi: ");
        for (int i = 0; i < indeksit.size(); i++) {
            System.out.print(indeksit.get(i) + 1 + "" + (char)(i + 97));
            System.out.print(" ");
        }
        System.out.println("");
    }
    
    public void lopetus() {
        System.out.println("Tietosi:");
        System.out.println(this.ohjelma.kayttajanTilasto());
        
//        this.ohjelma.tallennaTilasto();
    }
}
