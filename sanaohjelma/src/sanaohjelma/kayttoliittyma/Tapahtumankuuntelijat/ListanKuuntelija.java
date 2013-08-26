
package sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import sanaohjelma.Sanaohjelma;

public class ListanKuuntelija implements ListSelectionListener{
    private JFrame frame;
    private Sanaohjelma ohjelma;
    private JList tiedostot;
    
    public ListanKuuntelija(JFrame frame, Sanaohjelma ohjelma, JList tiedostot) {
        this.frame = frame;
        this.ohjelma = ohjelma;
        this.tiedostot = tiedostot;
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            System.out.println(tiedostot.getSelectedValue().toString());
            this.ohjelma.asetaSanat(tiedostot.getSelectedValue().toString());
            
            String[] sanaparit = this.ohjelma.sanatMerkkijono().split("\n");
            
            String rivit = "";
            for (String rivi : sanaparit) {
                rivit += rivi + "<br/>";
            }
            JPanel panel = new JPanel();
            panel.add(new JLabel("<html>" + rivit + "</html>"));
            
            JScrollPane skrollattava = new JScrollPane(panel);
            skrollattava.setPreferredSize(new Dimension(300, 400));
            this.frame.getContentPane().add(skrollattava, BorderLayout.CENTER);
            
            this.frame.validate();
        }
        
    }
    
    
}
