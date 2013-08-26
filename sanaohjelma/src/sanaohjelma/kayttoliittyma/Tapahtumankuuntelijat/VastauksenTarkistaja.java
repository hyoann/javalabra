package sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import sanaohjelma.Sanaohjelma;

public class VastauksenTarkistaja implements ActionListener {

    private JFrame frame;
    private Sanaohjelma ohjelma;
    private JLabel kysyttySana;
    private JTextField vastaus;
    private String kieli;
    private int kerrat;
    private JLabel kysymysNro;
    private int kerta;
    private JLabel tulos;
    private int oikein;

    public VastauksenTarkistaja(JFrame frame, Sanaohjelma ohjelma, JLabel kysyttySana, JTextField vastaus, String kieli, int kerrat, JLabel kysymysNro) {
        this.frame = frame;
        this.ohjelma = ohjelma;
        this.kysyttySana = kysyttySana;
        this.vastaus = vastaus;
        this.kieli = kieli;
        this.kerrat = kerrat;
        this.kysymysNro = kysymysNro;
        this.kerta = Integer.parseInt(kysymysNro.getText());

        this.tulos = new JLabel();

        this.oikein = 0;


    }

    @Override
    public void actionPerformed(ActionEvent e) {      
        if (this.kerta > kerrat) {
            return;
        }

        System.out.println("Tarkistetaan");
        if (this.ohjelma.vastausOikein(kysyttySana.getText(), vastaus.getText(), this.kieli)) {
            System.out.println("OIKEIN");
            this.tulos.setText("Oikein!");
            this.oikein++;
        } else {
            System.out.println("Väärin");
            String oikeaVastaus = this.ohjelma.haeOikeaVastaus(kysyttySana.getText(), this.kieli);
            System.out.println(oikeaVastaus);
            tulos.setText("Väärin! Oikea vastaus: " + oikeaVastaus);

        }
            this.frame.add(tulos);

            this.kerta++;
            

        if (this.kerta <= kerrat) {   
            this.kysymysNro.setText(String.valueOf(this.kerta));
            this.vastaus.setText("");
            this.kysyttySana.setText(this.ohjelma.kysySana(this.kieli));
        } else if (kerta == kerrat + 1) {
            this.frame.remove(this.kysyttySana);
            this.frame.remove(this.vastaus);
            this.frame.add(new JLabel("Sait oikein " + oikein + "/" + kerrat));
        } 
        
        this.frame.validate();
    }
}
