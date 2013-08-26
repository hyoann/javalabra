package sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import sanaohjelma.Sanaohjelma;
import sanaohjelma.kayttoliittyma.KayttajanTiedot;
import sanaohjelma.kayttoliittyma.KysyKerrat;
import sanaohjelma.kayttoliittyma.Lopetus;
import sanaohjelma.kayttoliittyma.TehtavanAsetukset;


public class ValikonKuuntelija implements ActionListener {
    private Sanaohjelma ohjelma;
    private JFrame frame;
    private JList lista;

    public ValikonKuuntelija(Sanaohjelma ohjelma, JFrame frame, JList lista) {
        this.ohjelma = ohjelma;
        this.frame = frame;
        this.lista = lista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Käännöstehtävä")) {
            TehtavanAsetukset asetukset = new TehtavanAsetukset(this.frame, this.ohjelma, this.lista);
            asetukset.setVisible(true);
        } else if (e.getActionCommand().equals("Yhdistä sanaparit")) {
            KysyKerrat kerrat = new KysyKerrat(this.frame, this.ohjelma);
            kerrat.setVisible(true);
        } else if (e.getActionCommand().equals("Näytä omat tiedot")) {
           this.frame.add(new KayttajanTiedot(this.ohjelma));
            this.frame.validate();
        } else if (e.getActionCommand().equals("Kirjaudu ulos")) {
            Lopetus lopetus = new Lopetus(this.frame, this.ohjelma);
            this.frame.validate();
        }
    }

}
