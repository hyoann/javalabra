package sanaohjelma;

import javax.swing.SwingUtilities;
import sanaohjelma.kayttoliittyma.YllapitoGUI;
import sanaohjelma.sovelluslogiikka.Kielet;

public class YllapitoGraafinenMain {

    public static void main(String args[]) {
        Kielet.asetaKielet("suomi", "venäjä");
        YllapitoGUI yllapito = new YllapitoGUI();
        SwingUtilities.invokeLater(yllapito);
    }
}
