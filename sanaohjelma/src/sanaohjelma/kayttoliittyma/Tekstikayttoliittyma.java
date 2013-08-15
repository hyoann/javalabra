package sanaohjelma.kayttoliittyma;

import java.util.Scanner;
import sanaohjelma.sovelluslogiikka.Sanat;
import sanaohjelma.sovelluslogiikka.Sanavalitsin;
import sanaohjelma.sovelluslogiikka.Tarkistaja;
import sanaohjelma.sovelluslogiikka.Tiedostonlukija;
import sanaohjelma.sovelluslogiikka.Tilasto;

public class Tekstikayttoliittyma {
    private Tiedostonlukija tiedostonlukija;
    private Scanner lukija;
    private Sanat sanat;
    private Tilasto tilasto;

    public Tekstikayttoliittyma(Tiedostonlukija tiedostonlukija, Scanner lukija) {
        this.tiedostonlukija = tiedostonlukija;
        this.lukija = lukija;
        this.sanat = tiedostonlukija.lueTiedosto();
        this.tilasto = new Tilasto();
    }

    public void kaynnista() {
        System.out.println("Tervetuloa");
        System.out.println("");


        while (true) {
            valikko();
            String valinta = lukija.nextLine();
            System.out.println("");

            if (valinta.equals("0")) {
                break;
            }
            if (!valinta.equals("1") && !valinta.equals("2") && !valinta.equals("3")) {
                continue;
            }

            valinta(valinta);
            System.out.println("");
        }


        System.out.println("Kiitos hei!");
    }

    public void valikko() {
        System.out.println("Valitse seuraavista toiminnoista: ");
        System.out.println("    1 - Näytä kaikki sanat");
        System.out.println("    2 - Käännöstehtävä suomesta venäjään");
        System.out.println("    3 - Käännöstehtävä venäjästä suomeen");
        System.out.println("    0 - lopeta");

        System.out.println("");
    }

    public void valinta(String valinta) {
        if (valinta.equals("1")) {
            naytaSanat();
        } else if (valinta.equals("2")) {
            kaannosTehtava("suomi");
        } else if (valinta.equals("3")) {
            kaannosTehtava("vieras");
        } else {
            valikko();
        }
    }

    public void kysySanat(int kerrat, String kaannettavaKieli) {
        //väärin arvattujen sanojen toistotiheys toistaiseksi kovakoodattu
        Sanavalitsin valitsin = new Sanavalitsin(3, this.tilasto, this.sanat);
        
        for (int i = 0; i < kerrat; i++) {
            String kysyttavaSana = valitsin.annaSana(kaannettavaKieli);
            System.out.print("Anna käännös sanalle " + kysyttavaSana + ": ");
            String annettuVastaus = lukija.nextLine();
            tarkistaAnnettuKaannos(kysyttavaSana, annettuVastaus, kaannettavaKieli);
     
        }
    }

    public void tarkistaAnnettuKaannos(String kysyttySana, String annettuVastaus, String kieli) {
        Tarkistaja tarkistaja = new Tarkistaja(this.sanat, kieli);  
        
        if (tarkistaja.vastausOikein(kysyttySana, annettuVastaus)) {
            System.out.println("Oikein!");
            //sana voidaan poistaa mokattujen sanojen listalta
            tilasto.annaMuistio(kieli).poistaSana(kysyttySana);
        } else {
            tilasto.annaMuistio(kieli).lisaaSana(kysyttySana);
            System.out.println("Väärin! Oikea vastaus: " + tarkistaja.haeOikeaVastaus(kysyttySana));
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

        System.out.println(this.tilasto);
    }

    public void naytaSanat() {
        System.out.println(this.sanat);
    }
}
