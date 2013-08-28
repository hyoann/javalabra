package sanaohjelma.kayttoliittyma.Yllapito;

import sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat.Yllapito.ValitseTiedosto;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import sanaohjelma.Hallinta;
import sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat.Yllapito.TiedostojenKuuntelija;
import sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat.Yllapito.TiedostonPoisto;

public class YllapitoGraafinen extends JPanel {

    private JFrame frame;
    private Hallinta ohjelma;

    public YllapitoGraafinen(JFrame frame, Hallinta ohjelma) {
        this.frame = frame;
        this.ohjelma = ohjelma;
        this.luoKomponentit();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    private void luoKomponentit() {
        JLabel muokkaa = new JLabel("Muokkaa tiedostoa");

        JList tiedostot = new JList(this.ohjelma.tiedostojenNimet().toArray());
        tiedostot.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(tiedostot);
        tiedostot.addListSelectionListener(new TiedostojenKuuntelija(this.frame, this.ohjelma, tiedostot));

        JLabel lisaaTiedosto = new JLabel("Lisää tiedosto");
        JButton lisaa = new JButton("Valitse");

        JLabel poista = new JLabel("Poista valittu tiedosto");
        JButton poistaNappi = new JButton("Poista");


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
