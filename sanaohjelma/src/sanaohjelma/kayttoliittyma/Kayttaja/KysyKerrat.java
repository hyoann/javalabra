package sanaohjelma.kayttoliittyma.Kayttaja;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import sanaohjelma.Hallinta;
import sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat.Kayttaja.KertojenTarkistaja;

public class KysyKerrat extends JDialog {
    private JFrame frame;
    private Hallinta ohjelma;

    public KysyKerrat(JFrame frame, Hallinta ohjelma) {
        super(frame, "Yhdistä sanaparit", true);
        this.frame = frame;
        this.ohjelma = ohjelma;
        this.luoKomponentit();
    }

    private void luoKomponentit() {
        JPanel panel = new JPanel();

        JLabel monta = new JLabel("Monta sanaparia kysytään?");

        String[] maarat = new String[25];

        for (int i = 2; i < 27; i++) {
            maarat[i - 2] = i + "";
        }

        JComboBox maara = new JComboBox(maarat);

        JButton ok = new JButton("OK");
        JButton peruuta = new JButton("Peruuta");

        KertojenTarkistaja tarkistaja = new KertojenTarkistaja(this.frame, this.ohjelma, this, maara);
        
        ok.addActionListener(tarkistaja);
        peruuta.addActionListener(tarkistaja);
        
        panel.add(monta);
        panel.add(maara);
        panel.add(ok);
        panel.add(peruuta);

        this.add(panel);

        pack();
    }
}
