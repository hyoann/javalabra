package sanaohjelma.sovelluslogiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SanatTest {   
    Sanat sanat;
    
    @Before
    public void setUp() {
        sanat = new Sanat();
    }
    
    @Test 
    public void luotuSanatOlioOlemassa() {
        assertNotNull(sanat);
    }
    
    @Test
    public void kaannosHakeeKaannoksenKunYksiSanapari() {
        sanat.lisaa("hevonen", "horse");
        assertEquals(sanat.kaannaVieraaseen("hevonen"), "horse");
}
    
    @Test
    public void kaannosHakeeOikeanKaannoksenKunMontaSanaparia() {
        sanat.lisaa("hevonen", "horse");
        sanat.lisaa("kukka", "flower");
        sanat.lisaa("eläin", "animal");
        
        assertEquals(sanat.kaannaVieraaseen("kukka"), "flower");
    }
    
    @Test
    public void kaannosPalauttaaNullKunSanaaEiOle() {
        sanat.lisaa("hevonen", "horse");
        assertNull(sanat.kaannaVieraaseen("koira"));
    }
}
