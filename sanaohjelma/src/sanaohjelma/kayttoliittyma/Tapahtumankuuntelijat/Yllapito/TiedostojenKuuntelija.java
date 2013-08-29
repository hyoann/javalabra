package sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat.Yllapito;

import sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat.Yllapito.TiedostonMuokkaaja;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import sanaohjelma.sovelluslogiikka.Hallinta;
import sanaohjelma.sovelluslogiikka.Kielet;

public class TiedostojenKuuntelija implements ListSelectionListener {

    private JFrame frame;
    private Hallinta ohjelma;
    private JList tiedostot;

    public TiedostojenKuuntelija(JFrame frame, Hallinta ohjelma, JList tiedostot) {
        this.frame = frame;
        this.ohjelma = ohjelma;
        this.tiedostot = tiedostot;

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            this.naytaSanat();
        }
    }

    public void naytaSanat() {
        //Poistetaan ylimääräiset komponentit, jos niitä on
        Component[] komponentit = this.frame.getContentPane().getComponents();
        if (komponentit.length > 1) {
            this.frame.remove(komponentit[1]);
        }
        this.frame.repaint();

        if (tiedostot.getSelectedValue() == null) {
            return;
        }

        System.out.println(tiedostot.getSelectedValue().toString());


        String[] sanaparit = this.ohjelma.naytaSisalto(tiedostot.getSelectedValue().toString()).split("\n");

        String rivit = "";
        for (String rivi : sanaparit) {
            rivit += rivi + "<br/>";
        }

        JPanel panel = new JPanel();
        panel.add(new JLabel("<html>" + rivit + "</html>"));

        JScrollPane skrollattava = new JScrollPane(panel);
        skrollattava.setPreferredSize(new Dimension(400, 500));
        skrollattava.setBorder(BorderFactory.createEmptyBorder());


        this.frame.getContentPane().add(skrollattava, BorderLayout.EAST);


        this.frame.validate();
    }
}
