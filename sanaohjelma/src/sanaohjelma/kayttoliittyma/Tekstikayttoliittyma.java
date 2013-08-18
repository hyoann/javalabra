package sanaohjelma.kayttoliittyma;

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

    public void kaynnista() {
        System.out.println("Tervetuloa");
        System.out.println("");

        while (true) {
            this.valikko();
            
            String valinta = lukija.nextLine();
            System.out.println("");

            if (valinta.equals("0")) {
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

        System.out.println(this.ohjelma.tilasto());
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
}
