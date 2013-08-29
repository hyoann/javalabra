package sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat.Yllapito;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import sanaohjelma.sovelluslogiikka.Hallinta;

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
        if (this.tiedostot.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(this.frame, "Valitse tiedosto!", "Virhe!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String tiedostonNimi = this.tiedostot.getSelectedValue().toString();
        if (tiedostonNimi != null) {
            if (!this.ohjelma.poistaTiedosto(tiedostonNimi)) {
                JOptionPane.showMessageDialog(this.frame, "Tiedoston poisto epäonnistui!", "Virhe!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            this.tiedostot.setListData(this.ohjelma.tiedostojenNimet().toArray());
            JOptionPane.showMessageDialog(this.frame, "Tiedoston poisto onnistui!", "Ilmoitus!", JOptionPane.INFORMATION_MESSAGE);
        }

    }
}
