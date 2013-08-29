package sanaohjelma;

import javax.swing.SwingUtilities;
import sanaohjelma.kayttoliittyma.GraafinenOhjelma;
import sanaohjelma.sovelluslogiikka.Kielet;

public class GraafinenKayttoliittymaMain {

    public static void main(String args[]) {
        Kielet.asetaKielet("suomi", "venäjä");
        GraafinenOhjelma ohjelmaGraafinen = new GraafinenOhjelma();
        SwingUtilities.invokeLater(ohjelmaGraafinen);
    }
}