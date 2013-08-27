package sanaohjelma.kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import sanaohjelma.Sanaohjelma;

public class TiedostonLisays implements ActionListener {

    private JFrame frame;
    private Sanaohjelma ohjelma;
    private File tiedosto;
    private JPanel panel;

    public TiedostonLisays(JFrame frame, Sanaohjelma ohjelma, File tiedosto, JPanel panel) {
        this.frame = frame;
        this.ohjelma = ohjelma;
        this.tiedosto = tiedosto;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Ei")) {
            this.frame.setContentPane(new YllapitoGraafinen(this.frame, this.ohjelma));
            this.frame.validate();
            return;
        }
        if (this.ohjelma.lisaaTiedosto(this.tiedosto.getAbsolutePath())) {
        } else {
            JOptionPane.showMessageDialog(this.frame, "Tiedoston lisäys epäonnistui!", "Virhe", JOptionPane.ERROR_MESSAGE);
        }
    }
}
