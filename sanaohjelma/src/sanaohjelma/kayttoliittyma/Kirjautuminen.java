package sanaohjelma.kayttoliittyma;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import sanaohjelma.Sanaohjelma;
import sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat.KirjautumisenTunnistaja;

public class Kirjautuminen extends JPanel {
    private JFrame frame;
    private Sanaohjelma ohjelma;

    public Kirjautuminen(JFrame frame, Sanaohjelma ohjelma) {
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
        
        this.setOpaque(false);
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        nappi.addActionListener(new KirjautumisenTunnistaja(this.frame, tunnus, salasana, this.ohjelma));
    }
}