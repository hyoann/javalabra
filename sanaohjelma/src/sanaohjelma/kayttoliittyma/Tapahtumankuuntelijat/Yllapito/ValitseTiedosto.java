
package sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat.Yllapito;

import sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat.Yllapito.TiedostonLisays;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import sanaohjelma.Sanaohjelma;

public class ValitseTiedosto implements ActionListener {
    private JFrame frame;
    private Sanaohjelma ohjelma;
    private JFileChooser tiedostot;
    
    public ValitseTiedosto(JFrame frame, Sanaohjelma ohjelma) {
        this.frame = frame;
        this.ohjelma = ohjelma;
        this.tiedostot = new JFileChooser();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         int returnVal = tiedostot.showOpenDialog(this.frame);
         
         if (returnVal == JFileChooser.APPROVE_OPTION) {
            File tiedosto = tiedostot.getSelectedFile();
             System.out.println(tiedosto.getName());
             
            JPanel kyllaVaiEi = new JPanel();
            JLabel valittu = new JLabel(tiedosto.getName());
            JLabel lisataanko = new JLabel("Lisätäänkö tiedosto?");
            JButton kylla = new JButton("Kyllä");
            JButton ei = new JButton("Ei");
           
            TiedostonLisays lisays = new TiedostonLisays(this.frame, this.ohjelma, tiedosto, kyllaVaiEi);
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
