package sanaohjelma.kayttoliittyma.Yllapito;

import sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat.Yllapito.ValitseTiedosto;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import sanaohjelma.sovelluslogiikka.Hallinta;
import sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat.Yllapito.TiedostojenKuuntelija;
import sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat.Yllapito.TiedostonMuokkaaja;
import sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat.Yllapito.TiedostonPoisto;
import sanaohjelma.sovelluslogiikka.Kielet;

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
        JButton lisaaTiedostoNappi = new JButton("Valitse");

        JLabel poista = new JLabel("Poista valittu tiedosto");
        JButton poistaNappi = new JButton("Poista");


        lisaaTiedostoNappi.addActionListener(new ValitseTiedosto(this.frame, this.ohjelma, tiedostot));
        poistaNappi.addActionListener(new TiedostonPoisto(this.frame, this.ohjelma, tiedostot));

        add(muokkaa);
        add(scroll);
        add(poista);
        add(poistaNappi);
        add(lisaaTiedosto);
        add(lisaaTiedostoNappi);

        JPanel apupaneeli = new JPanel();
        apupaneeli.setLayout(new BoxLayout(apupaneeli, BoxLayout.Y_AXIS ));

        JLabel lisaa = new JLabel("Lisää sana");
        JLabel sana1 = new JLabel("Anna sana kielellä " + Kielet.getKieli1());
        JTextField sanaKielella1 = new JTextField(10);
        JLabel sana2 = new JLabel("Anna sana kielellä " + Kielet.getKieli2());
        JTextField sanaKielella2 = new JTextField(10);
        JButton lisaaSana = new JButton("Lisää");

        JLabel poistaSana = new JLabel("Anna poistettava sana kielellä " + Kielet.getKieli1());
        JTextField poistettavaSana = new JTextField(10);
        JButton poistaSanaNappi = new JButton("Poista");

        TiedostonMuokkaaja muokkaaja = new TiedostonMuokkaaja(this.frame, this.ohjelma, tiedostot, sanaKielella1, sanaKielella2, poistettavaSana);
        lisaaSana.addActionListener(muokkaaja);
        poistaSanaNappi.addActionListener(muokkaaja);

        apupaneeli.add(lisaa);
        apupaneeli.add(sana1);
        apupaneeli.add(sanaKielella1);
        apupaneeli.add(sana2);
        apupaneeli.add(sanaKielella2);
        apupaneeli.add(lisaaSana);
        
        add(apupaneeli);

        add(poistaSana);
        add(poistettavaSana);
        add(poistaSanaNappi);



    }
}
