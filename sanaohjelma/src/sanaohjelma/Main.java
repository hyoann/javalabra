package sanaohjelma;

import sanaohjelma.sovelluslogiikka.Sanat;
import sanaohjelma.sovelluslogiikka.Tiedostonlukija;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        Scanner lukija = new Scanner(System.in);
        Tiedostonlukija tiedostonlukija = new Tiedostonlukija(new File("src/sanaohjelma/sanat.txt"));
        
        Sanat sanat = tiedostonlukija.lueTiedosto();

        System.out.println("Tervetuloa!");
        System.out.println("");

        for (String sana : sanat.suomennokset()) {
            System.out.print("Anna käännös sanalle " + sana + ": ");

            String kaannos = lukija.nextLine();

            if (kaannos.equals(sanat.kaannos(sana))) {
                System.out.println("Oikein!");
            } else {
                System.out.println("Väärin! Oikea vastaus: " + sanat.kaannos(sana));
            }
            System.out.println("");
        }
        System.out.println("Kiitos hei!");

    }

    
}
