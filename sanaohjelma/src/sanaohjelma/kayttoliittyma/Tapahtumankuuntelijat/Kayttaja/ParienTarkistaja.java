package sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat.Kayttaja;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import sanaohjelma.Hallinta;

public class ParienTarkistaja implements ActionListener {

    private JFrame frame;
    private Hallinta ohjelma;
    private JTextField vastausAlue;
    private int oikein;
    private int vastaus;
    private int sanapareja;
    private JLabel vastauksesi;

    public ParienTarkistaja(JFrame frame, Hallinta ohjelma, JTextField vastausAlue, int sanapareja) {
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
        
        if (this.vastaus == 1) {
            this.frame.add(new JLabel("Vastauksesi: "));
        }

        JLabel vastattu = new JLabel();
        vastattu.setText(this.vastausAlue.getText());

        if (this.vastaus > this.sanapareja) {
            return;
        }

        if (this.ohjelma.tarkistaVastaus(this.vastausAlue.getText())) {
            this.oikein++;
        }

        this.frame.add(vastattu);


        if (this.vastaus == this.sanapareja) {
            JLabel tulos = new JLabel("<html>Sait oikein " + this.oikein + "/" + this.sanapareja + "<br/>" + 
                    "Oikea rivi: " + this.ohjelma.oikeaRivi() + "</html>");
            this.frame.add(tulos);
        }

        this.vastausAlue.setText("");
        this.frame.validate();
    }
}
