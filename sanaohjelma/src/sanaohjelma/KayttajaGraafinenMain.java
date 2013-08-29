package sanaohjelma;

import javax.swing.SwingUtilities;
import sanaohjelma.kayttoliittyma.KayttajaGUI;
import sanaohjelma.sovelluslogiikka.Kielet;

public class KayttajaGraafinenMain {

    public static void main(String args[]) {
        Kielet.asetaKielet("suomi", "venäjä");
        KayttajaGUI kayttaja = new KayttajaGUI();
        SwingUtilities.invokeLater(kayttaja);
    }
}