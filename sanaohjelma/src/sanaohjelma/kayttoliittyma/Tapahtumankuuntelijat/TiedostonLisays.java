package sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import sanaohjelma.Sanaohjelma;


public class TiedostonLisays implements ActionListener {

    private JFrame frame;
    private Sanaohjelma ohjelma;
    private File tiedosto;


    public TiedostonLisays(JFrame frame, Sanaohjelma ohjelma, File tiedosto) {
        this.frame = frame;
        this.ohjelma = ohjelma;
        this.tiedosto = tiedosto;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Ei")) {
            return;
        }
        System.out.println("painettiin kyllä");
        if (this.ohjelma.lisaaTiedosto(this.tiedosto.getAbsolutePath())) {
            System.out.println("Tiedosto lisätty");
        } else {
            JOptionPane.showMessageDialog(this.frame, "Tiedoston lisäys epäonnistui!", "Virhe", JOptionPane.ERROR_MESSAGE);
        }
    }
}
