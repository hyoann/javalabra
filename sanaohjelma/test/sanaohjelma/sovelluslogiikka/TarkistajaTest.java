
package sanaohjelma.sovelluslogiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TarkistajaTest {
    Tarkistaja tarkistaja;
    Sanat sanat;
    String kieli;
    
    @Before
    public void setUp() {
        Kielet.asetaKielet("suomi", "englanti");
        sanat = new Sanat();
        sanat.lisaa("kissa", "cat");
        sanat.lisaa("koira", "dog");
        kieli = "suomi";
        tarkistaja = new Tarkistaja(sanat, kieli);
    }

    @Test
    public void haeOikeaVastausHakeeOikeanVastauksen() {
        assertEquals(tarkistaja.haeOikeaVastaus("kissa"), "cat");
    }
    
    @Test
    public void haeOikeaVastausPalauttaaNullKunSanaaEiLoydy() {
        assertNull(tarkistaja.haeOikeaVastaus("tatasanaaeiole"));
    }
    
    @Test
    public void palauttaaTrueKunVastausOikein() {
        assertTrue(tarkistaja.vastausOikein("kissa", "cat"));
    }
    
    @Test
    public void palauttaaFalseKunVastausVaarin() {
        assertFalse(tarkistaja.vastausOikein("kissa", "dog"));
    }
    
    @Test
    public void palauttaaFalseKunKysyttyaSanaaEiEdesOle() {
        assertFalse(tarkistaja.vastausOikein("fdsa","cat"));
    }
}
