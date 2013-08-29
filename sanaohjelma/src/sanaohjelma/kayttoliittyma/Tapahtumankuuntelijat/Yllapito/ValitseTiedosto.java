
package sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat.Yllapito;

import sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat.Yllapito.TiedostonLisays;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import sanaohjelma.sovelluslogiikka.Hallinta;

public class ValitseTiedosto implements ActionListener {
    private JFrame frame;
    private Hallinta ohjelma;
    private JFileChooser tiedostonValitsija;
    private JList tiedostot;
    
    public ValitseTiedosto(JFrame frame, Hallinta ohjelma, JList tiedostot) {
        this.frame = frame;
        this.ohjelma = ohjelma;
        this.tiedostonValitsija = new JFileChooser();
        this.tiedostot = tiedostot;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         int returnVal = tiedostonValitsija.showOpenDialog(this.frame);
         
         if (returnVal == JFileChooser.APPROVE_OPTION) {
            File tiedosto = tiedostonValitsija.getSelectedFile();
             System.out.println(tiedosto.getName());
             
            JPanel kyllaVaiEi = new JPanel();
            JLabel valittu = new JLabel(tiedosto.getName());
            JLabel lisataanko = new JLabel("Lisätäänkö tiedosto?");
            JButton kylla = new JButton("Kyllä");
            JButton ei = new JButton("Ei");
           
            TiedostonLisays lisays = new TiedostonLisays(this.frame, this.ohjelma, tiedosto, kyllaVaiEi, tiedostot);
            kylla.addActionListener(lisays);
            ei.addActionListener(lisays);
            
            kyllaVaiEi.add(valittu);
            kyllaVaiEi.add(lisataanko);
            kyllaVaiEi.add(kylla);
            kyllaVaiEi.add(ei);
            
            this.frame.getContentPane().add(kyllaVaiEi);
            this.frame.validate();

        } else {
             System.out.println("Cancel");
        }
    }
    
}
