package sanaohjelma.kayttoliittyma;

import sanaohjelma.kayttoliittyma.Kayttaja.Kirjautuminen;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import sanaohjelma.kayttoliittyma.Yllapito.YllapitoGraafinen;
import sanaohjelma.sovelluslogiikka.Hallinta;

public class YllapitoGUI implements Runnable {

    private JFrame frame;
    private Hallinta ohjelma;

    public YllapitoGUI() {
        this.ohjelma = new Hallinta();
        this.ohjelma.asetaToistojenTiheys(2);
    }

    @Override
    public void run() {

        frame = new JFrame("Ylläpito");
        frame.setPreferredSize(new Dimension(800, 500));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        container.add(new YllapitoGraafinen(this.frame, this.ohjelma));
        container.setLayout(new GridLayout(1, 2));
    }

    public JFrame getFrame() {
        return frame;
    }
}
