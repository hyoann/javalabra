package sanaohjelma.kayttoliittyma.Kayttaja;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import sanaohjelma.sovelluslogiikka.Hallinta;
import sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat.Kayttaja.KirjautumisenTunnistaja;

public class Kirjautuminen extends JPanel {
    private JFrame frame;
    private Hallinta ohjelma;

    public Kirjautuminen(JFrame frame, Hallinta ohjelma) {
        this.frame = frame;
        this.ohjelma = ohjelma;
        luoKomponentit();
    }
    
    private void luoKomponentit() {
        JLabel annaTunnus = new JLabel("Anna käyttäjätunnus:");
        JTextField tunnus = new JTextField(10);
        JLabel annaSalasana = new JLabel("Anna salasana:");
        JPasswordField salasana = new JPasswordField(10);
        
        JButton nappi = new JButton("Kirjaudu sisään");
        
        this.add(annaTunnus);
        this.add(tunnus);
        this.add(annaSalasana);
        this.add(salasana);
        this.add(nappi);

        nappi.addActionListener(new KirjautumisenTunnistaja(this.frame, tunnus, salasana, this.ohjelma));
    }
}