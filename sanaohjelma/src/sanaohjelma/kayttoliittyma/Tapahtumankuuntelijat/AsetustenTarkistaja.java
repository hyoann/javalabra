package sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import sanaohjelma.Sanaohjelma;
import sanaohjelma.kayttoliittyma.Kaannostehtava;

public class AsetustenTarkistaja implements ActionListener {

    private JTextField kerrat;
    private ButtonGroup vaihtoehdot;
    private JFrame frame;
    private Sanaohjelma ohjelma;
    private JList lista;
    private JDialog dialogi;

    public AsetustenTarkistaja(JTextField kerrat, ButtonGroup vaihtoehdot, JFrame frame, Sanaohjelma ohjelma, JList lista, JDialog dialogi) {
        this.kerrat = kerrat;
        this.vaihtoehdot = vaihtoehdot;
        this.frame = frame;
        this.ohjelma = ohjelma;
        this.lista = lista;
        this.dialogi = dialogi;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Peruuta")) {
            dialogi.setVisible(false);
            return;
        }

        String kieli = this.tarkistaKieli();

        if (kieli == null) {
            return;
        }
        System.out.println(kieli);
        int annetutKerrat = this.tarkistaKerrat();

        if (annetutKerrat == -99) {
            return;
        }

        dialogi.setVisible(false);

        if (this.lista.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(this.frame, "Valitse tiedosto!", "Virhe", JOptionPane.ERROR_MESSAGE);
            return;
        }

        System.out.println("lista: " + lista);

        //tyhjennetään valikon alapuoli, jos siellä on jotain
        Component[] komponentit = this.frame.getContentPane().getComponents();
        System.out.println("Komponenttien määrä (AsetustenTarkistaja): " + this.frame.getContentPane().getComponentCount());
    
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
 
        Kaannostehtava tehtava = new Kaannostehtava(this.frame, this.ohjelma, kieli, annetutKerrat);
        
        this.frame.getContentPane().add(tehtava);
        this.frame.validate();
    }

    private String tarkistaKieli() {
        if (this.vaihtoehdot.getSelection() == null) {
            JOptionPane.showMessageDialog(this.frame, "Valitse kieli!");
            return null;
        }

        Enumeration<AbstractButton> kielet = this.vaihtoehdot.getElements();
        String kieli = "";

        while (kielet.hasMoreElements()) {
            JRadioButton nappi = (JRadioButton) kielet.nextElement();
            if (nappi.isSelected()) {
                kieli = nappi.getText();
            }
        }
        return kieli;
    }

    private int tarkistaKerrat() {
        int annetutKerrat = 0;

        try {
            annetutKerrat = Integer.parseInt(this.kerrat.getText());
            if (annetutKerrat < 0) {
                JOptionPane.showMessageDialog(this.frame, "Luvun pitää olla positiivinen!");
                return -99;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this.frame, "Anna jokin luku!");
            return -99;
        }
        return annetutKerrat;
    }
}
