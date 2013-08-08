package sanaohjelma.kayttoliittyma;

import java.util.Scanner;
import sanaohjelma.sovelluslogiikka.Sanat;
import sanaohjelma.sovelluslogiikka.Tiedostonlukija;

public class Tekstikayttoliittyma {
    private Tiedostonlukija tiedostonlukija;
    private Scanner lukija;
    private Sanat sanat;

    public Tekstikayttoliittyma(Tiedostonlukija tiedostonlukija, Scanner lukija) {
        this.tiedostonlukija = tiedostonlukija;
        this.lukija = lukija;
        this.sanat = tiedostonlukija.lueTiedosto();
    }

    public void kaynnista() {
        System.out.println("Tervetuloa");
        System.out.println("");

        kysySanat();

        System.out.println("Kiitos hei!");

    }

    public void kysySanat() {
        for (String sana : sanat.suomennokset()) {
            System.out.print("Anna käännös sanalle " + sana + ": ");
            String kaannos = lukija.nextLine();

            tarkistaAnnettuKaannos(kaannos, sana);
        }
    }   

    public void tarkistaAnnettuKaannos(String kaannos, String sana) {
        if (kaannos.equals(sanat.kaannos(sana))) {
            System.out.println("Oikein!");
        } else {
            System.out.println("Väärin! Oikea vastaus: " + sanat.kaannos(sana));
        }
        System.out.println("");
    }
}

