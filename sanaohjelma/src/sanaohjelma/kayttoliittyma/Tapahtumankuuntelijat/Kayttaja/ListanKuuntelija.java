package sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat.Kayttaja;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import sanaohjelma.sovelluslogiikka.Hallinta;

public class ListanKuuntelija implements ListSelectionListener {

    private JFrame frame;
    private Hallinta ohjelma;
    private JList tiedostot;

    public ListanKuuntelija(JFrame frame, Hallinta ohjelma, JList tiedostot) {
        this.frame = frame;
        this.ohjelma = ohjelma;
        this.tiedostot = tiedostot;

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            System.out.println(tiedostot.getSelectedValue().toString());
            this.ohjelma.haeSanatTiedostosta(tiedostot.getSelectedValue().toString());

            String[] sanaparit = this.ohjelma.sanatMerkkijono().split("\n");

            String rivit = "";
            for (String rivi : sanaparit) {
                rivit += rivi + "<br/>";
            }
            JPanel panel = new JPanel();
            panel.add(new JLabel("<html>" + rivit + "</html>"));

            JScrollPane skrollattava = new JScrollPane(panel);
            skrollattava.setPreferredSize(new Dimension(300, 300));
            skrollattava.setBorder(BorderFactory.createEmptyBorder());

            Component[] komponentit = this.frame.getContentPane().getComponents();
            System.out.println("Komponenttien määrä (ListanKuuntelija): " + this.frame.getContentPane().getComponentCount());

            if (komponentit.length > 7) {
                for (int i = 7; i < komponentit.length; i++) {
                    this.frame.remove(komponentit[i]);

                    this.frame.repaint();
                }
            }

            if (this.ohjelma.sanojenMaara() == 0) {
                JOptionPane.showMessageDialog(this.frame, "Tiedosto on tyhjä!", "Virhe", JOptionPane.ERROR_MESSAGE);
                return;
            }


            this.frame.getContentPane().add(skrollattava, BorderLayout.EAST);
            this.frame.invalidate();
            this.frame.validate();
        }
    }
}
