
package sanaohjelma.kayttoliittyma.Kayttaja;

import javax.swing.JFrame;
import sanaohjelma.Hallinta;

public class Lopetus  {
    private JFrame frame;
    private Hallinta ohjelma;
    
    public Lopetus(JFrame frame, Hallinta ohjelma) {
        this.frame = frame;
        this.ohjelma = ohjelma;
        this.lopetus();
    }
    
    private void lopetus() {
        this.ohjelma.tallennaTilasto();
        this.ohjelma.kirjaaKayttajaUlos();
        
        this.frame.setContentPane(new Kirjautuminen(this.frame, this.ohjelma));
    }
}
