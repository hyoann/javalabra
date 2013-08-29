package sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat.Kayttaja;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import sanaohjelma.sovelluslogiikka.Hallinta;
import sanaohjelma.kayttoliittyma.Kayttaja.Valikko;

public class KirjautumisenTunnistaja implements ActionListener {
    private JFrame frame;
    private JTextField tunnus;
    private JPasswordField salasana;
    private Hallinta ohjelma;

    public KirjautumisenTunnistaja(JFrame frame, JTextField tunnus, JPasswordField salasana, Hallinta ohjelma) {
        this.frame = frame;
        this.tunnus = tunnus;
        this.salasana = salasana;
        this.ohjelma = ohjelma;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.ohjelma.haeKayttaja(this.tunnus.getText(), this.salasana.getText()) != null) {
            this.frame.setContentPane(new Valikko(this.frame, this.ohjelma));
            this.frame.validate();
        } else {
            JOptionPane.showMessageDialog(this.frame, "Väärä käyttäjätunnus tai salasana!", "Virhe", JOptionPane.ERROR_MESSAGE);
        }
    }
}
