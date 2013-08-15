package sanaohjelma;

import java.io.File;
import java.util.Scanner;
import sanaohjelma.kayttoliittyma.Tekstikayttoliittyma;
import sanaohjelma.sovelluslogiikka.Tiedostonlukija;

public class Main {

    public static void main(String args[]) {
        Scanner lukija = new Scanner(System.in, "UTF-8");
        Tiedostonlukija tiedostonlukija = new Tiedostonlukija(new File(("src/sanaohjelma/sanat.txt")));
        
        Tekstikayttoliittyma tekstikäli = new Tekstikayttoliittyma(tiedostonlukija, lukija);
       
        tekstikäli.kaynnista();
        
    }  
}
