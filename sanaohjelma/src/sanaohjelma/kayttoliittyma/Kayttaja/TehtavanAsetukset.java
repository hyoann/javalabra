
package sanaohjelma.kayttoliittyma.Kayttaja;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import sanaohjelma.Sanaohjelma;
import sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat.Kayttaja.AsetustenTarkistaja;
import sanaohjelma.sovelluslogiikka.Kielet;

public class TehtavanAsetukset extends JDialog{
    private JFrame frame;
    private Sanaohjelma ohjelma;
    private JList tiedostot;
    
    public TehtavanAsetukset(JFrame frame, Sanaohjelma ohjelma, JList tiedostot) {
        super(frame, "Käännostehtävä", true);
        this.frame = frame;
        this.ohjelma = ohjelma;
        this.tiedostot = tiedostot;
        this.luoKomponentit();
        
    }
    
    private void luoKomponentit() {
        JPanel paneeli = new JPanel();

        JLabel valitseKieli = new JLabel("Kummasta kielestä käännetään?");
        JRadioButton kieli1 = new JRadioButton(Kielet.getKieli1());
        JRadioButton kieli2 = new JRadioButton(Kielet.getKieli2());

        ButtonGroup bg = new ButtonGroup();
        bg.add(kieli1);
        bg.add(kieli2);

        JLabel valitseKerrat = new JLabel("Kuinka monta kertaa kysytään?");
        JTextField kerrat = new JTextField(3);
        JButton aloita = new JButton("Aloita");
        JButton peruuta = new JButton("Peruuta");

        AsetustenTarkistaja tarkistaja = new AsetustenTarkistaja(kerrat, bg, this.frame, this.ohjelma, this.tiedostot, this);
        aloita.addActionListener(tarkistaja);
        peruuta.addActionListener(tarkistaja);

        paneeli.add(valitseKieli);
        paneeli.add(kieli1);
        paneeli.add(kieli2);
        
        paneeli.add(valitseKerrat);
        paneeli.add(kerrat);
        paneeli.add(aloita);
        paneeli.add(peruuta);

        add(paneeli);
        
        pack();
    }
}
