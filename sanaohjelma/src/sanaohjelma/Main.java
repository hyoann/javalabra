package sanaohjelma;

import sanaohjelma.kayttoliittyma.Tekstikayttoliittyma;
import sanaohjelma.sovelluslogiikka.Tiedostonlukija;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        Scanner lukija = new Scanner(System.in);
        Tiedostonlukija tiedostonlukija = new Tiedostonlukija(new File(("src/sanaohjelma/sanat.txt")));
        
        Tekstikayttoliittyma tekstikäli = new Tekstikayttoliittyma(tiedostonlukija, lukija);
       
        tekstikäli.kaynnista();
        
      

    }  
}
