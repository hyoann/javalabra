
package sanaohjelma.kayttoliittyma.Kayttaja;

import javax.swing.JLabel;
import javax.swing.JPanel;
import sanaohjelma.Hallinta;

public class KayttajanTiedot extends JPanel {
    private Hallinta ohjelma;
    
    public KayttajanTiedot(Hallinta ohjelma) {
        this.ohjelma = ohjelma;
        this.luoKomponentit();
    }
    
    private void luoKomponentit() {
        JLabel tiedot = new JLabel();
        
        String[] rivit = this.ohjelma.kayttajanTilasto().split("\n");
        String teksti = "";

        for (String rivi : rivit) {
            teksti += rivi + "<br/>";
        }      
        
        tiedot.setText("<html>" + teksti + "</html>");     
        
        add(tiedot);
    }
}
