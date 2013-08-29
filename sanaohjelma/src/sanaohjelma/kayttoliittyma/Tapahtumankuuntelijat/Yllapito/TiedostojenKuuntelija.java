package sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat.Yllapito;

import sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat.Yllapito.TiedostonMuokkaaja;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
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
            //Poistetaan ylimääräiset komponentit, jos niitä on
            Component[] komponentit = this.frame.getContentPane().getComponents();
            if (komponentit.length > 1) {
               for (int i = 2; i < komponentit.length; i++) {
                   this.frame.remove(komponentit[i]);
               }
            }
            this.frame.repaint();

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

            JPanel tehtavat = new JPanel();
            tehtavat.setLayout(new BoxLayout(tehtavat, BoxLayout.Y_AXIS));
            
            JLabel lisaa = new JLabel("Lisää sana");
            JLabel sana1 = new JLabel("Anna sana kielellä " + Kielet.getKieli1());
            JTextField sanaKielella1 = new JTextField(10);
            JLabel sana2 = new JLabel("Anna sana kielellä " + Kielet.getKieli2());
            JTextField sanaKielella2 = new JTextField(10);
            JButton lisaaSana = new JButton("Lisää");
            
            JLabel poista = new JLabel("Anna poistettava sana kielellä " + Kielet.getKieli1());
            JTextField poistettavaSana = new JTextField(10);
            JButton poistaSana = new JButton("Poista");

            TiedostonMuokkaaja muokkaaja = new TiedostonMuokkaaja(this.frame, this.ohjelma, this.tiedostot, sanaKielella1, sanaKielella2, poistettavaSana);
            lisaaSana.addActionListener(muokkaaja);
            poistaSana.addActionListener(muokkaaja);
            
            tehtavat.add(lisaa);
            tehtavat.add(sanaKielella1);
            tehtavat.add(sana1);
            tehtavat.add(sanaKielella2);
            tehtavat.add(sana2);
            tehtavat.add(lisaaSana);
            
            tehtavat.add(poista);
            tehtavat.add(poistettavaSana);
            tehtavat.add(poistaSana);

            this.frame.getContentPane().add(skrollattava, BorderLayout.EAST);
            this.frame.getContentPane().add(tehtavat);

            this.frame.validate();
        }
    }
}
