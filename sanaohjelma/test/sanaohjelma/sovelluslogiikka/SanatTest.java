package sanaohjelma.sovelluslogiikka;


import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import sanaohjelma.sovelluslogiikka.Sanat;

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
        assertEquals(sanat.kaannos("hevonen"), "horse");
}
    
    @Test
    public void kaannosHakeeOikeanKaannoksenKunMontaSanaparia() {
        sanat.lisaa("hevonen", "horse");
        sanat.lisaa("kukka", "flower");
        sanat.lisaa("eläin", "animal");
        
        assertEquals(sanat.kaannos("kukka"), "flower");
    }
    
    @Test
    public void kaannosPalauttaaNullKunSanaaEiOle() {
        sanat.lisaa("hevonen", "horse");
        assertNull(sanat.kaannos("koira"));
    }
    
    @Test
    public void suomennosPalauttaaSuomennoksenKunYksiSanapari() {
        sanat.lisaa("hevonen", "horse");
        Set heppa = new HashSet();
        heppa.add("hevonen");
        
        assertEquals(sanat.suomennokset(), heppa);
    }
    
    @Test
    public void suomennosPalauttaaSuomennoksetKunMontaSanaparia() {
        sanat.lisaa("hevonen", "horse");
        sanat.lisaa("kukka", "flower");
        sanat.lisaa("eläin", "animal");
        
        Set suomeksi = new HashSet();
        suomeksi.add("hevonen");
        suomeksi.add("kukka");
        suomeksi.add("eläin");
        
        assertEquals(sanat.suomennokset(), suomeksi);       
    }
    
    @Test
    public void suomennosPalauttaaTyhjanJoukonKunEiSanapareja() {
        assertTrue(sanat.suomennokset().isEmpty());
    }
}
