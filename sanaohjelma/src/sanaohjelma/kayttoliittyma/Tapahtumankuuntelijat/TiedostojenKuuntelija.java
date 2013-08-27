
package sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import sanaohjelma.Sanaohjelma;
import sanaohjelma.sovelluslogiikka.Kielet;

public class TiedostojenKuuntelija implements ListSelectionListener{
    private JFrame frame;
    private Sanaohjelma ohjelma;
    private JList tiedostot;
    
    public TiedostojenKuuntelija(JFrame frame, Sanaohjelma ohjelma, JList tiedostot) {
        this.frame = frame;
        this.ohjelma = ohjelma;
        this.tiedostot = tiedostot;
       
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
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
            
            JLabel lisaa = new JLabel("Lisää sana");
            JLabel sana1 = new JLabel("Anna sana kielellä " + Kielet.kieli1);
            JTextField sanaKielella1 = new JTextField(10);
            JLabel sana2 = new JLabel("Anna sana kielellä " + Kielet.kieli2);
            JButton lisaaSana = new JButton("Lisää");
            
            JTextField sanaKielella2 = new JTextField(10);
            JLabel poista = new JLabel("Anna poistettava sana kielellä " + Kielet.kieli1);
            JTextField poistettavaSana = new JTextField(10);
            JButton poistaSana = new JButton("Poista");
            
            
            
            tehtavat.add(lisaa);
            tehtavat.add(sana1);
            tehtavat.add(sanaKielella1);
            tehtavat.add(sana2);
            tehtavat.add(lisaaSana);
            
            tehtavat.add(sanaKielella2);
            tehtavat.add(poista);
            tehtavat.add(poistettavaSana);
            tehtavat.add(poistaSana);
            
            this.frame.add(tehtavat);
            this.frame.getContentPane().add(skrollattava, BorderLayout.EAST);
            
            this.frame.validate();
        }
        
    }
    
    
}