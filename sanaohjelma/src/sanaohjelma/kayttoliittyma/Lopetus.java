
package sanaohjelma.kayttoliittyma;

import javax.swing.JFrame;
import sanaohjelma.Sanaohjelma;

public class Lopetus  {
    private JFrame frame;
    private Sanaohjelma ohjelma;
    
    public Lopetus(JFrame frame, Sanaohjelma ohjelma) {
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
