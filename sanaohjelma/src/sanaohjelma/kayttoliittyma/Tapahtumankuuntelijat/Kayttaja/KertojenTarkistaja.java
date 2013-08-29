package sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat.Kayttaja;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import sanaohjelma.sovelluslogiikka.Hallinta;
import sanaohjelma.kayttoliittyma.Kayttaja.YhdistaSanaparit;

public class KertojenTarkistaja implements ActionListener {

    private JFrame frame;
    private Hallinta ohjelma;
    private JDialog kysymys;
    private JComboBox maarat;

    public KertojenTarkistaja(JFrame frame, Hallinta ohjelma, JDialog kysymys, JComboBox maarat) {
        this.frame = frame;
        this.ohjelma = ohjelma;
        this.kysymys = kysymys;
        this.maarat = maarat;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Peruuta")) {
            this.kysymys.setVisible(false);
            return;
        }

        //tarkistetaan onko tiedosto valittu
        if (!this.ohjelma.onkoSanatAsetettu()) {
            JOptionPane.showMessageDialog(this.frame, "Valitse tiedosto!", "Virhe", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String maara = maarat.getSelectedItem().toString();

        if (maara == null) {
            JOptionPane.showMessageDialog(this.frame, "Valitse jokin luku!", "Virhe", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //jos ollaan päästy tänne asti, kaikki on ok joten dialogi voidaan hävittää
        this.kysymys.setVisible(false);

        if (this.ohjelma.sanojenMaara() == 0) {
            JOptionPane.showMessageDialog(this.frame, "Tiedosto on tyhjä!", "Virhe", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int kerrat = Integer.parseInt(maara);
        if (this.ohjelma.sanojenMaara() < kerrat) {
            kerrat = this.ohjelma.sanojenMaara();
            JOptionPane.showMessageDialog(this.frame, "Tiedostossa ei ole niin paljon sanoja. Kysytään " + kerrat + " sanaa", "Ilmoitus", JOptionPane.INFORMATION_MESSAGE);
        }
        System.out.println("Sanapareja " + kerrat + " kpl");

        //tyhjennetään valikon alapuoli, jos siellä on jotain
        Component[] komponentit = this.frame.getContentPane().getComponents();
        System.out.println("Komponenttien määrä (KertojenTarkistaja): " + this.frame.getContentPane().getComponentCount());

        if (komponentit.length > 7) {
            for (int i = 7; i < komponentit.length; i++) {
                this.frame.remove(komponentit[i]);
                this.frame.repaint();
            }
        }



        YhdistaSanaparit tehtava = new YhdistaSanaparit(this.frame, this.ohjelma, kerrat);

        this.frame.getContentPane().add(tehtava);
        this.frame.validate();
    }
}
