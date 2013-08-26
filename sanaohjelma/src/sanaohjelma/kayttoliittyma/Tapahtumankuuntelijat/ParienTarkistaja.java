package sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import sanaohjelma.Sanaohjelma;

public class ParienTarkistaja implements ActionListener {

    private JFrame frame;
    private Sanaohjelma ohjelma;
    private JTextField vastausAlue;
    private int oikein;
    private int vastaus;
    private int sanapareja;

    public ParienTarkistaja(JFrame frame, Sanaohjelma ohjelma, JTextField vastausAlue, int sanapareja) {
        this.frame = frame;
        this.ohjelma = ohjelma;
        this.vastausAlue = vastausAlue;
        this.oikein = 0;
        this.vastaus = 0;
        this.sanapareja = sanapareja;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.vastaus++;
        
        if (this.vastaus > this.sanapareja) {
            return;
        }
 
        if (this.ohjelma.tarkistaVastaus(this.vastausAlue.getText())) {
            this.oikein++;
        }
        
        JLabel vastattu = new JLabel();
        vastattu.setText(this.vastausAlue.getText());
        
        this.vastausAlue.setText("");
        
        if (this.vastaus == this.sanapareja) {
            JLabel tulos = new JLabel("Sait oikein " + this.oikein + "/" + this.sanapareja);
            JLabel oikeaRivi = new JLabel("Oikea rivi: " + this.ohjelma.oikeaRivi());
            this.frame.add(tulos);
            this.frame.add(oikeaRivi);
        }
        
        this.frame.add(vastattu);
        this.frame.validate();
    }
}
