
package sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import sanaohjelma.Sanaohjelma;

public class TiedostonMuokkaaja implements ActionListener {
    private JFrame frame;
    private Sanaohjelma ohjelma;
    private JList tiedostot;
    private JTextField lisattavaKielella1;
    private JTextField lisattavaKielella2;
    private JTextField poistettavaKiella1;
    
    public TiedostonMuokkaaja(JFrame frame, Sanaohjelma ohjelma, JList tiedostot, JTextField lisattavaKielella1, JTextField lisattavaKielella2, JTextField poistettavaKiella1 ) {
        this.frame = frame;
        this.ohjelma = ohjelma;
        this.tiedostot = tiedostot;
        this.lisattavaKielella1 = lisattavaKielella1;
        this.lisattavaKielella2 = lisattavaKielella2;
        this.poistettavaKiella1 = poistettavaKiella1;
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String tiedostonNimi = this.tiedostot.getSelectedValue().toString();
        
        if (e.getActionCommand().equals("Lisaa")) {
            if (this.lisattavaKielella1.getText() == null || this.lisattavaKielella2 == null) {
                JOptionPane.showMessageDialog(this.frame, "Sana ei voi olla tyhjä!", "Virhe", JOptionPane.ERROR_MESSAGE);
            } else {
                this.ohjelma.lisaaSanapari(this.lisattavaKielella1.getText(), this.lisattavaKielella1.getText(), tiedostonNimi);
            }
        
        } else if (e.getActionCommand().equals("Poista")) {
            if (this.poistettavaKiella1 == null) {
               JOptionPane.showMessageDialog(this.frame, "Sana ei voi olla tyhjä!", "Virhe", JOptionPane.ERROR_MESSAGE); 
               return;
            }
            this.ohjelma.poistaSana(this.poistettavaKiella1.getText(), tiedostonNimi);
        }
    }
    
}
