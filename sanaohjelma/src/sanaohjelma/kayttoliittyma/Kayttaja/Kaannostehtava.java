package sanaohjelma.kayttoliittyma.Kayttaja;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import sanaohjelma.Sanaohjelma;
import sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat.Kayttaja.VastauksenTarkistaja;

public class Kaannostehtava extends JPanel {

    private JFrame frame;
    private Sanaohjelma ohjelma;
    private String kieli;
    private int kerrat;

    public Kaannostehtava(JFrame frame, Sanaohjelma ohjelma, String kieli, int kerrat) {
        this.frame = frame;
        this.ohjelma = ohjelma;
        this.kieli = kieli;
        this.kerrat = kerrat;
        this.luoKomponentit();
    }

    private void luoKomponentit() {
        JLabel kysymys = new JLabel("Kysymys ");
        JLabel kerta = new JLabel("1");
        JLabel kaikki = new JLabel("/ " + kerrat + " ");
        JLabel teksti = new JLabel("Anna käännös sanalle");
        JLabel sana = new JLabel();
        JTextField vastaus = new JTextField(10);

        VastauksenTarkistaja tarkistaja = new VastauksenTarkistaja(this.frame, this.ohjelma, sana, vastaus, this.kieli, this.kerrat, kerta);

        vastaus.addActionListener(tarkistaja);

        add(kysymys);
        add(kerta);
        add(kaikki);
        add(teksti);
        add(sana);
        add(vastaus);
        sana.setText(this.ohjelma.kysySana(this.kieli));
    }
}
