package sanaohjelma.kayttoliittyma;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import sanaohjelma.Sanaohjelma;
import sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat.ParienTarkistaja;

public class YhdistaSanaparit extends JPanel {
    private JFrame frame;
    private Sanaohjelma ohjelma;
    private int maara;

    public YhdistaSanaparit(JFrame frame, Sanaohjelma ohjelma, int maara) {
        this.frame = frame;
        this.ohjelma = ohjelma;
        this.maara = maara;
        this.luoKomponentit();
    }
    
    private void luoKomponentit() {
        ArrayList<String> sanat = this.ohjelma.haeSanatNumerolla(this.maara);
        ArrayList<String> kaannokset = this.ohjelma.haeKaannoksetKirjaimella();
        
        JLabel listat = new JLabel();
        String rivit = "";
        
        for (int i = 0; i < sanat.size(); i++) {
            rivit += sanat.get(i) + "" + kaannokset.get(i) + "<br/"; 
        }
        listat.setText("<html>" + rivit  +"</html>");
        
        JTextField vastausAlue = new JTextField(3);
        
        ParienTarkistaja tarkistaja = new ParienTarkistaja(this.frame, this.ohjelma, vastausAlue, sanat.size());
        vastausAlue.addActionListener(tarkistaja);
        
        add(listat);
        add(vastausAlue);
    }
}
