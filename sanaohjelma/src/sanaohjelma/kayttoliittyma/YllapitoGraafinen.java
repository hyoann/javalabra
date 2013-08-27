package sanaohjelma.kayttoliittyma;

import sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat.ValitseTiedosto;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import sanaohjelma.Sanaohjelma;
import sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat.TiedostojenKuuntelija;
import sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat.TiedostonPoisto;

public class YllapitoGraafinen extends JPanel {

    private JFrame frame;
    private Sanaohjelma ohjelma;

    public YllapitoGraafinen(JFrame frame, Sanaohjelma ohjelma) {
        this.frame = frame;
        this.ohjelma = ohjelma;
        this.luoKomponentit();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    private void luoKomponentit() {
        JLabel muokkaa = new JLabel("Muokkaa tiedostoa");
        
        JList tiedostot = new JList(this.ohjelma.tiedostojenNimet().toArray());
        tiedostot.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tiedostot.setSize(100, 100);
        JScrollPane scroll = new JScrollPane(tiedostot);
        scroll.setSize(100, 200);
        tiedostot.addListSelectionListener(new TiedostojenKuuntelija(this.frame, this.ohjelma, tiedostot));
        
        JLabel poista = new JLabel("Poista valittu tiedosto");
        JButton poistaNappi = new JButton("Poista");
        JLabel lisaaTiedosto = new JLabel("Lisää tiedosto");
        JButton lisaa = new JButton("Valitse");

        lisaa.addActionListener(new ValitseTiedosto(this.frame, this.ohjelma));
        poistaNappi.addActionListener(new TiedostonPoisto(this.frame, this.ohjelma, tiedostot));

        add(muokkaa);
        add(scroll);
        add(poista);
        add(poistaNappi);
        add(lisaaTiedosto);
        add(lisaa);
    }
}
