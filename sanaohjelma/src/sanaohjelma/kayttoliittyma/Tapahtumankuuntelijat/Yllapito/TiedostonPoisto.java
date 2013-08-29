
package sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat.Yllapito;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import sanaohjelma.sovelluslogiikka.Hallinta;
import sanaohjelma.kayttoliittyma.Yllapito.YllapitoGraafinen;

public class TiedostonPoisto implements ActionListener {
    
    private JFrame frame;
    private Hallinta ohjelma;
    private JList tiedostot;


    public TiedostonPoisto(JFrame frame, Hallinta ohjelma, JList tiedostot) {
        this.frame = frame;
        this.ohjelma = ohjelma;
        this.tiedostot = tiedostot;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String tiedostonNimi = this.tiedostot.getSelectedValue().toString();
        if (tiedostonNimi != null) {
           if(!this.ohjelma.poistaTiedosto(tiedostonNimi)) {
               JOptionPane.showMessageDialog(this.frame, "Tiedoston poisto epäonnistui!", "Virhe!", JOptionPane.ERROR_MESSAGE);
           }
           JOptionPane.showMessageDialog(this.frame, "Tiedoston poisto onnistui!", "Ilmoitus!", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    
    
}
