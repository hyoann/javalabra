
package sanaohjelma.sovelluslogiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TilastoTest {
    Tilasto tilasto;

    
    @Before
    public void setUp() {
        this.tilasto = new Tilasto(0, 0);
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
         this.tilasto.kasvataOikeita();
         
         assertTrue(this.tilasto.voittoprosentti() == 50.0);
         
     }
} 