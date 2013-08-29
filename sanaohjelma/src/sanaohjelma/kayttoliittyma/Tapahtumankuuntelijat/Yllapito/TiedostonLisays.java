package sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat.Yllapito;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import sanaohjelma.sovelluslogiikka.Hallinta;

public class TiedostonLisays implements ActionListener {

    private JFrame frame;
    private Hallinta ohjelma;
    private File tiedosto;
    private JPanel lisataanko;

    public TiedostonLisays(JFrame frame, Hallinta ohjelma, File tiedosto, JPanel lisataanko) {
        this.frame = frame;
        this.ohjelma = ohjelma;
        this.tiedosto = tiedosto;
        this.lisataanko = lisataanko;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Ei")) {
            System.out.println("Painettii ei");
            JOptionPane.showMessageDialog(this.frame, "Tiedostoa ei lisätty!", "Ilmoitus", JOptionPane.INFORMATION_MESSAGE);
        } else {
            System.out.println("painettiin kyllä");
            if (this.ohjelma.lisaaTiedosto(this.tiedosto.getAbsolutePath())) {
                JOptionPane.showMessageDialog(this.frame, "Tiedosto lisättiin!", "Ilmoitus", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Tiedosto lisätty");
            } else {
                JOptionPane.showMessageDialog(this.frame, "Tiedoston lisäys epäonnistui!", "Virhe", JOptionPane.ERROR_MESSAGE);
            }
        }
        this.frame.getContentPane().remove(lisataanko);
        this.frame.repaint();
    }
}
