package sanaohjelma;

import java.io.File;
import java.util.Scanner;
import sanaohjelma.kayttoliittyma.Tekstikayttoliittyma;
import sanaohjelma.sovelluslogiikka.Kielet;
import sanaohjelma.sovelluslogiikka.Tiedostonlukija;

public class Main {

    public static void main(String args[]) {    
        Kielet.asetaKielet("suomi", "venäjä");
        
        Scanner lukija = new Scanner(System.in, "UTF-8");
        Tiedostonlukija tiedostonlukija = new Tiedostonlukija();
        
        Sanaohjelma ohjelma = new Sanaohjelma(tiedostonlukija);
        ohjelma.asetaToistotiheys(3);
        
        Tekstikayttoliittyma tekstikäli = new Tekstikayttoliittyma(lukija, ohjelma);
       
        tekstikäli.kirjautuminen();
        
    }  
}
