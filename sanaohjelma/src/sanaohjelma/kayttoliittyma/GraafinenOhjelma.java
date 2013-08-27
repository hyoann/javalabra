package sanaohjelma.kayttoliittyma;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import sanaohjelma.Sanaohjelma;
import sanaohjelma.sovelluslogiikka.Tiedostonlukija;

public class GraafinenOhjelma implements Runnable {

    private JFrame frame;
    private Sanaohjelma ohjelma;

    public GraafinenOhjelma() {
        this.ohjelma = new Sanaohjelma(new Tiedostonlukija());
    }

    @Override
    public void run() {

        frame = new JFrame("Sanaohjelma");
        frame.setPreferredSize(new Dimension(900, 500));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        container.add(new Kirjautuminen(this.frame, this.ohjelma));
        //container.add(new YllapitoGraafinen(this.frame, this.ohjelma));

    }

    public JFrame getFrame() {
        return frame;
    }
}