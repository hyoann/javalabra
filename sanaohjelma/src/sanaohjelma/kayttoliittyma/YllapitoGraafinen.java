package sanaohjelma.kayttoliittyma;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import sanaohjelma.Sanaohjelma;

public class YllapitoGraafinen extends JPanel {

    private JFrame frame;
    private Sanaohjelma ohjelma;

    public YllapitoGraafinen(JFrame frame, Sanaohjelma ohjelma) {
        this.frame = frame;
        this.ohjelma = ohjelma;
        this.luoKomponentit();
    }

    private void luoKomponentit() {
        JLabel lisaaTiedosto = new JLabel("Lisää tiedosto");
        JButton lisaa = new JButton("Valitse");

        lisaa.addActionListener(new ValitseTiedosto(this.frame, this.ohjelma));

        add(lisaaTiedosto);
        add(lisaa);
    }
}
