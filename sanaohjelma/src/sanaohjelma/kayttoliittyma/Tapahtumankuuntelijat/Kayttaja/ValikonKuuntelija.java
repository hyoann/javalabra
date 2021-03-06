package sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat.Kayttaja;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import sanaohjelma.sovelluslogiikka.Hallinta;
import sanaohjelma.kayttoliittyma.Kayttaja.KayttajanTiedot;
import sanaohjelma.kayttoliittyma.Kayttaja.KysyKerrat;
import sanaohjelma.kayttoliittyma.Kayttaja.Lopetus;
import sanaohjelma.kayttoliittyma.Kayttaja.TehtavanAsetukset;

public class ValikonKuuntelija implements ActionListener {

    private Hallinta ohjelma;
    private JFrame frame;
    private JList lista;

    public ValikonKuuntelija(Hallinta ohjelma, JFrame frame, JList lista) {
        this.ohjelma = ohjelma;
        this.frame = frame;
        this.lista = lista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //tyhjennetään valikon alapuoli, jos siellä on jotain
        Component[] komponentit = this.frame.getContentPane().getComponents();
        System.out.println("Komponenttien määrä (ValikonKuuntelija): " + this.frame.getContentPane().getComponentCount());

        if (komponentit.length > 7) {
            for (int i = 7; i < komponentit.length; i++) {
                this.frame.remove(komponentit[i]);
                this.frame.repaint();         
            }
        }


        if (e.getActionCommand().equals("Käännöstehtävä")) {
            TehtavanAsetukset asetukset = new TehtavanAsetukset(this.frame, this.ohjelma, this.lista);
            asetukset.setVisible(true);

        } else if (e.getActionCommand().equals("Yhdistä sanaparit")) {
            KysyKerrat kerrat = new KysyKerrat(this.frame, this.ohjelma);
            kerrat.setVisible(true);

        } else if (e.getActionCommand().equals("Näytä omat tiedot")) {
            KayttajanTiedot tiedot = new KayttajanTiedot(this.ohjelma);
            this.frame.getContentPane().add(tiedot);
            this.frame.validate();

        } else if (e.getActionCommand().equals("Kirjaudu ulos")) {
            Lopetus lopetus = new Lopetus(this.frame, this.ohjelma);
            this.frame.validate();
        }
    }
}
