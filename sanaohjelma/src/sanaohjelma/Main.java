package sanaohjelma;

import java.util.Scanner;
import javax.swing.SwingUtilities;
import sanaohjelma.kayttoliittyma.GraafinenOhjelma;
import sanaohjelma.kayttoliittyma.Tekstikayttoliittyma;
import sanaohjelma.kayttoliittyma.Yllapito;
import sanaohjelma.sovelluslogiikka.Kielet;
import sanaohjelma.sovelluslogiikka.Tiedostonlukija;

public class Main {

    public static void main(String args[]) {
        Kielet.asetaKielet("suomi", "venäjä");

//        Scanner lukija = new Scanner(System.in, "UTF-8");
//        Tiedostonlukija tiedostonlukija = new Tiedostonlukija();
//
//        Sanaohjelma ohjelma = new Sanaohjelma(tiedostonlukija);
//
//        while (true) {
//            System.out.println("Oletko (1) käyttäjä vai (2) ylläpito?");
//            String valinta = lukija.nextLine();
//            if (valinta.equals("1")) {
//                Tekstikayttoliittyma tekstikäli = new Tekstikayttoliittyma(lukija, ohjelma);
//                tekstikäli.kirjautuminen();
//                break;
//            } else if (valinta.equals("2")) {
//                Yllapito pito = new Yllapito(lukija, tiedostonlukija, ohjelma);
//                pito.kaynnista();
//                break;
//            }
//        }
        
        GraafinenOhjelma ohjelma = new GraafinenOhjelma();
        SwingUtilities.invokeLater(ohjelma);
    }
}