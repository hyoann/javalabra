package sanaohjelma.kayttoliittyma;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import sanaohjelma.Sanaohjelma;
import sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat.ListanKuuntelija;

public class YllapitoGraafinen extends JPanel {

    private JFrame frame;
    private Sanaohjelma ohjelma;

    public YllapitoGraafinen(JFrame frame, Sanaohjelma ohjelma) {
        this.frame = frame;
        this.ohjelma = ohjelma;
        this.luoKomponentit();
    }

    private void luoKomponentit() {
        JLabel muokkaa = new JLabel("Muokkaa tiedostoa");
        JList tiedostot = new JList(this.ohjelma.tiedostojenNimet().toArray());
        tiedostot.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tiedostot.addListSelectionListener(new ListanKuuntelija(this.frame, this.ohjelma, tiedostot));
        JLabel poista = new JLabel("Poista valittu tiedosto");
        JButton poistaNappi = new JButton("Poista");
        JLabel lisaaTiedosto = new JLabel("Lisää tiedosto");
        JButton lisaa = new JButton("Valitse");

        lisaa.addActionListener(new ValitseTiedosto(this.frame, this.ohjelma));

        add(muokkaa);
        add(tiedostot);
        add(poista);
        add(poistaNappi);
        add(lisaaTiedosto);
        add(lisaa);
    }
}
