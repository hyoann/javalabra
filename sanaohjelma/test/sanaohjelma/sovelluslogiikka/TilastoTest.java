
package sanaohjelma.sovelluslogiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TilastoTest {
    Tilasto tilasto;

    
    @Before
    public void setUp() {
        this.tilasto = new Tilasto();
    }

    @Test
    public void alustettuOikein() {
        assertEquals(this.tilasto.sanamaara(), 0);
    }
    
    @Test
    public void tilastoKasvattaaSanamaaraaOikein() {
        this.tilasto.kasvataSanamaaraa();
        this.tilasto.kasvataSanamaaraa();
        
        assertEquals(this.tilasto.sanamaara(), 2);
    }
     
     @Test
     public void voittoprosenttiLasketaanOikein() {
         this.tilasto.kasvataSanamaaraa();
         this.tilasto.kasvataSanamaaraa();
         
         this.tilasto.annaMuistio("suomi").lisaaSana("fas");
         
         assertTrue(this.tilasto.voittoprosentti() == 50.0);
         
     }
} 