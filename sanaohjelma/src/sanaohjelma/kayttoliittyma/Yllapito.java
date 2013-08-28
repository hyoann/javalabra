package sanaohjelma.kayttoliittyma;

import java.util.Scanner;
import sanaohjelma.Sanaohjelma;
import sanaohjelma.sovelluslogiikka.Kielet;

public class Yllapito {

    private Scanner lukija;
    private Sanaohjelma ohjelma;

    public Yllapito(Sanaohjelma ohjelma) {
        this.lukija = new Scanner(System.in, "UTF-8");
        this.ohjelma = ohjelma;
    }

    public void kaynnista() {
        while (true) {
            System.out.println("Valitse toiminto:");
            System.out.println("=================");
            System.out.println("    1 - Listaa tiedostot");
            System.out.println("    2 - Muokkaa tiedostoa");
            System.out.println("    3 - Lisää tiedosto");
            System.out.println("    0 - Lopeta");

            String valinta = lukija.nextLine();
            System.out.println("");

            if (valinta.equals("1")) {
                this.tiedostotLuettelona();
            } else if (valinta.equals("2")) {
                this.muokkausvalikko();
            } else if (valinta.equals("3")) {
                this.lisaaTiedosto();
            } else if (valinta.equals("0")) {
                break;
            } else {
                System.out.println("Anna jotain toimintoa vastaava luku!");
                continue;
            }
            System.out.println("");
        }
    }

    public void tiedostotLuettelona() {
        int indeksi = 0;

        for (String nimi : this.ohjelma.tiedostojenNimet()) {
            System.out.println("    [" + indeksi + "] " + nimi);
            indeksi++;
        }
    }

    public void muokkausvalikko() {
        String tiedosto = this.valitseTiedosto();
        while (true) {
            if (tiedosto == null) {
                System.out.println("Tiedostoa ei löydy!");
                System.out.println("");
                continue;
            }

            System.out.println("    Muokkaa tiedostoa " + tiedosto);
            System.out.println("    ----------------------------------");
            System.out.println("        1 - Näytä tiedoston sisältö");
            System.out.println("        2 - Lisää sana");
            System.out.println("        3 - Poista sana");
            System.out.println("        4 - Poista tiedosto");
            System.out.println("        0 - Poistu");
            System.out.println("");

            System.out.print("Anna toiminnon numero: ");
            String valinta = lukija.nextLine();
            System.out.println("");

            if (valinta.equals("1")) {
                this.naytaSisalto(tiedosto);
            } else if (valinta.equals("2")) {
                this.lisaaSana(tiedosto);
            } else if (valinta.equals("3")) {
                this.poistaSana(tiedosto);
            } else if (valinta.equals("4")) {
                if (this.poistaTiedosto(tiedosto)) {
                    break;
                }
            } else if (valinta.equals("0")) {
                break;
            } else {
                System.out.println("Anna jotain toimintoa vastaava luku!");
            }
            System.out.println("");
        }
    }

    public String valitseTiedosto() {
        System.out.println("Valitse muokattava tiedosto:");
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

    public void naytaSisalto(String tiedostonNimi) {
        if (this.ohjelma.naytaSisalto(tiedostonNimi) == null) {
            System.out.println("Tiedosto on tyhjä tai sitä ei ole olemassa!");
        } else {
            System.out.println(this.ohjelma.naytaSisalto(tiedostonNimi));
        }
    }

    public void poistaSana(String tiedostonNimi) {
        System.out.print("Anna poistettava sana kielellä " + Kielet.kieli1 + ": ");
        String sana = lukija.nextLine();

        if (this.ohjelma.poistaSana(sana, tiedostonNimi)) {
            System.out.println("Sanan '" + sana + "' poisto onnistui tiedostostosta " + tiedostonNimi);
        } else {
            System.out.println("Sanaa '" + sana + "' ei löydy tiedostosta " + tiedostonNimi);
        }

    }

    public void lisaaSana(String tiedostonNimi) {
        System.out.print("Anna sana kielellä " + Kielet.kieli1 + ": ");
        String sana1 = lukija.nextLine();

        System.out.print("Anna sana kielellä " + Kielet.kieli2 + ": ");
        String sana2 = lukija.nextLine();

        System.out.println("");

        if (!sana1.isEmpty() && !sana2.isEmpty()) {
            this.ohjelma.lisaaSanapari(sana1, sana2, tiedostonNimi);
            System.out.println("Lisättiin sanapari '" + sana1 + " - " + sana2 + "' tiedostoon " + tiedostonNimi);
        } else {
            System.out.println("Lisättävä sana ei saa olla tyhjä!");
        }


    }

    private boolean poistaTiedosto(String tiedostonNimi) {
        System.out.println("Haluatko varmasto poista tiedoston " + tiedostonNimi + "? (K = Kyllä, E = Ei)");
        String vastaus = lukija.nextLine();

        if (vastaus.equals("K")) {
            if (this.ohjelma.poistaTiedosto(tiedostonNimi)) {
                System.out.println("Tiedoston poisto onnistui!");
                return true;
            } else {
                System.out.println("Tiedoston poisto epäonnistui.");
                return false;
            }
        } else {
            System.out.println("Tiedostoa " + tiedostonNimi + " ei poistettu!");
            return false;
        }
    }

    private void lisaaTiedosto() {
        System.out.print("Anna tiedoston sijainti: ");
        String sijainti = lukija.nextLine();

        System.out.println("");

        if (this.ohjelma.lisaaTiedosto(sijainti)) {
            System.out.println("Tiedoston lisäys onnistui!");
        } else {
            System.out.println("Tiedoston lisäys epäonnistui.");
        }
    }
}
