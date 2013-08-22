
package sanaohjelma.sovelluslogiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MokausMuistioTest {
    MokausMuistio muistio;
    
    @Before
    public void setUp() {
        muistio = new MokausMuistio();
    }
    
    @Test
    public void kunMuistiossaYksiSanaSeAnnetaan() {
        muistio.lisaaSana("kissa");
        assertEquals(muistio.annaJokuSana(), "kissa");
    }
    
     @Test
     public void poistaSanaPoistaaSananKunYksiSana() {
         muistio.lisaaSana("kissa");
         muistio.poistaSana("kissa");
         assertNull(muistio.annaJokuSana());
     }
     
     @Test
     public void poistaSanaEiTeeMitaanKunPoistettavaaSanaaEiOle() {
         muistio.lisaaSana("kissa");
         muistio.poistaSana("elain");
         
         assertEquals(muistio.annaJokuSana(), "kissa");
     }
     
     @Test
     public void poistaSanaPoistaaSananKunMontaSanaa() {
         muistio.lisaaSana("kissa");
         muistio.lisaaSana("koira");        
         muistio.poistaSana("kissa");
         
         assertEquals(muistio.annaJokuSana(), "koira");
     }
}
