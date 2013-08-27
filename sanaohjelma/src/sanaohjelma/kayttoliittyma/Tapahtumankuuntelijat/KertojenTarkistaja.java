package sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import sanaohjelma.Sanaohjelma;
import sanaohjelma.kayttoliittyma.YhdistaSanaparit;

public class KertojenTarkistaja implements ActionListener {

    private JFrame frame;
    private Sanaohjelma ohjelma;
    private JDialog kysymys;
    private JComboBox maarat;

    public KertojenTarkistaja(JFrame frame, Sanaohjelma ohjelma, JDialog kysymys, JComboBox maarat) {
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

        String maara = maarat.getSelectedItem().toString();

        if (maara == null) {
            JOptionPane.showMessageDialog(this.frame, "Valitse jokin luku!", "Virhe", JOptionPane.ERROR_MESSAGE);
            return;
        }

        this.kysymys.setVisible(false);

        //tarkistetaan onko tiedosto valittu
        if (this.ohjelma.sanojenMaara() == 0) {
            JOptionPane.showMessageDialog(this.frame, "Valitse tiedosto!", "Virhe", JOptionPane.ERROR_MESSAGE);
            return;
        }


        int kerrat = Integer.parseInt(maara);
        System.out.println("Sanapareja " + kerrat + " kpl");

        //tyhjennetään valikon alapuoli, jos siellä on jotain
        Component[] komponentit = this.frame.getContentPane().getComponents();
        System.out.println("Komponenttien määrä (KertojenTarkistaja): " + this.frame.getContentPane().getComponentCount());
    
        if (komponentit.length > 7) {
            for (int i = 7; i < komponentit.length; i++) {
                this.frame.remove(komponentit[i]);
                this.frame.validate();
            }
        }
        this.frame.revalidate();

            YhdistaSanaparit tehtava = new YhdistaSanaparit(this.frame, this.ohjelma, kerrat);



            this.frame.add(tehtava);
            this.frame.validate();
        }
    }
