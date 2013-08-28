
package sanaohjelma.sovelluslogiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MokausMuistioTest {
    MokausMuistio muistio;
    Kielet kielet;
    
    @Before
    public void setUp() {
        Kielet.asetaKielet("suomi", "englanti");
        muistio = new MokausMuistio();
        muistio.lisaaSana("kissa", Kielet.getKieli1());
    }
    
    @Test
    public void kunMuistiossaYksiSanaSeAnnetaan() {
        assertEquals(muistio.annaJokuSana(Kielet.getKieli1()), "kissa");
    }
    
    @Test
    public void mokausMuistioAntaaSananOikeastaListasta() {
        muistio.lisaaSana("dog", Kielet.getKieli2());
        assertEquals(muistio.annaJokuSana(Kielet.getKieli2()), "dog");
    }
    
     @Test
     public void poistaSanaPoistaaSananKunYksiSana() {
         muistio.poistaSana("kissa", Kielet.getKieli1());
         assertNull(muistio.annaJokuSana(Kielet.getKieli1()));
     }
     
     @Test
     public void poistaSanaEiTeeMitaanKunPoistettavaaSanaaEiOle() {
         muistio.poistaSana("elain", Kielet.getKieli1());
         
         assertEquals(muistio.annaJokuSana(Kielet.getKieli1()), "kissa");
     }
     
     @Test
     public void poistaSanaPoistaaSananKunMontaSanaa() {
         muistio.lisaaSana("koira", Kielet.getKieli1());        
         muistio.poistaSana("kissa", Kielet.getKieli1());
         
         assertEquals(muistio.annaJokuSana(Kielet.getKieli1()), "koira");
     }
     
     @Test
     public void poistaSanaEiPoistaKaikkiaSamojaSanojaVaanVainYhden() {
         muistio.lisaaSana("kissa", Kielet.getKieli1()); //muistiossa nyt kaksi kissaa
         muistio.poistaSana("kissa", Kielet.getKieli1()); //poistetaan toinen
         assertEquals(muistio.annaJokuSana( Kielet.getKieli1()), "kissa");
     }
     
     @Test
     public void poistaSanaEiPoistaSanaaJosKieliVaara() {
         muistio.poistaSana("kissa", Kielet.getKieli2());
         assertEquals(muistio.annaJokuSana( Kielet.getKieli1()), "kissa");
     }
     
     @Test
     public void kunSanojaEiOlePalautetaanNull() {
         muistio.poistaSana("kissa", Kielet.getKieli1());
         assertNull(muistio.annaJokuSana(Kielet.getKieli1()));
     }
}
