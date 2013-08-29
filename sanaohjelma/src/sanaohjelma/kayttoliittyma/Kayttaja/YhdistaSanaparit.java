package sanaohjelma.kayttoliittyma.Kayttaja;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import sanaohjelma.sovelluslogiikka.Hallinta;
import sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat.Kayttaja.ParienTarkistaja;

public class YhdistaSanaparit extends JPanel {

    private JFrame frame;
    private Hallinta ohjelma;
    private int maara;

    public YhdistaSanaparit(JFrame frame, Hallinta ohjelma, int maara) {
        this.frame = frame;
        this.ohjelma = ohjelma;
        this.maara = maara;
        this.luoKomponentit();
    }

    private void luoKomponentit() {
        ArrayList<String> sanat = this.ohjelma.haeSanatNumerolla(this.maara);
        ArrayList<String> kaannokset = this.ohjelma.haeKaannoksetKirjaimella();

        JLabel sanatGUI = new JLabel();
        JLabel kaannoksetGUI = new JLabel();
        
         String sanatTekstina = "";
         String kaannoksetTekstina = "";

        for (int i = 0; i < sanat.size(); i++) {
            sanatTekstina += sanat.get(i) + "<br/>";
            kaannoksetTekstina += kaannokset.get(i) + "<br/>";
        }

        sanatGUI.setText("<html>" + sanatTekstina + "</html>");
        kaannoksetGUI.setText("<html>" + kaannoksetTekstina + "</html>");

        JTextField vastausAlue = new JTextField(3);

        ParienTarkistaja tarkistaja = new ParienTarkistaja(this.frame, this.ohjelma, vastausAlue, sanat.size());
        vastausAlue.addActionListener(tarkistaja);

        add(sanatGUI);
        add(kaannoksetGUI);
        add(vastausAlue);

    }
}
