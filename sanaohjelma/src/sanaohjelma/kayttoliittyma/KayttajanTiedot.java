
package sanaohjelma.kayttoliittyma;

import javax.swing.JLabel;
import javax.swing.JPanel;
import sanaohjelma.Sanaohjelma;

public class KayttajanTiedot extends JPanel {
    private Sanaohjelma ohjelma;
    
    public KayttajanTiedot(Sanaohjelma ohjelma) {
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
