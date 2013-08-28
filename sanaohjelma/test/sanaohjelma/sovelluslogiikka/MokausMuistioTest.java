
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
        muistio.lisaaSana("kissa", Kielet.getKieli1());
        assertEquals(muistio.annaJokuSana(Kielet.getKieli1()), "kissa");
    }
    
     @Test
     public void poistaSanaPoistaaSananKunYksiSana() {
         muistio.lisaaSana("kissa", Kielet.getKieli1());
         muistio.poistaSana("kissa", Kielet.getKieli1());
         assertNull(muistio.annaJokuSana(Kielet.getKieli1()));
     }
     
     @Test
     public void poistaSanaEiTeeMitaanKunPoistettavaaSanaaEiOle() {
         muistio.lisaaSana("kissa", Kielet.getKieli1());
         muistio.poistaSana("elain", Kielet.getKieli1());
         
         assertEquals(muistio.annaJokuSana(Kielet.getKieli1()), "kissa");
     }
     
     @Test
     public void poistaSanaPoistaaSananKunMontaSanaa() {
         muistio.lisaaSana("kissa", Kielet.getKieli1());
         muistio.lisaaSana("koira", Kielet.getKieli1());        
         muistio.poistaSana("kissa", Kielet.getKieli1());
         
         assertEquals(muistio.annaJokuSana(Kielet.getKieli1()), "koira");
     }
}
