package sanaohjelma;

import java.util.Scanner;
import javax.swing.SwingUtilities;
import sanaohjelma.kayttoliittyma.GraafinenOhjelma;
import sanaohjelma.kayttoliittyma.Tekstikayttoliittyma;
import sanaohjelma.kayttoliittyma.YllapitoTeksti;
import sanaohjelma.sovelluslogiikka.Kielet;

public class Main {

    public static void main(String args[]) {
        Kielet.asetaKielet("suomi", "venäjä");

        Sanaohjelma ohjelma = new Sanaohjelma();
        ohjelma.asetaToistojenTiheys(2);
        while (true) {
            System.out.println("Oletko (1) käyttäjä vai (2) ylläpito?");
            String valinta = (new Scanner(System.in)).nextLine();
            if (valinta.equals("1")) {
                Tekstikayttoliittyma tekstikäli = new Tekstikayttoliittyma(ohjelma);
                tekstikäli.kirjautuminen();
                break;
            } else if (valinta.equals("2")) {
                YllapitoTeksti pito = new YllapitoTeksti(ohjelma);
                pito.kaynnista();
                break;
            }
        }
        
//        GraafinenOhjelma ohjelmaGraafinen = new GraafinenOhjelma();
//        SwingUtilities.invokeLater(ohjelmaGraafinen);
    }
}