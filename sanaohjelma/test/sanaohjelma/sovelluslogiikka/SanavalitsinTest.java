
package sanaohjelma.sovelluslogiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SanavalitsinTest {
    Sanavalitsin valitsin;
    Tilasto tilasto;
    Sanat sanat;
    Mokailut mokailut;

    @Before
    public void setUp() {
        tilasto = new Tilasto(0, 0);
        sanat = new Sanat();
        mokailut = new Mokailut();
        valitsin = new Sanavalitsin(3, tilasto, sanat, mokailut);
        Kielet.asetaKielet("suomi", "englanti");
    }

    @Test
    public void kysytaanSanaSanatLuokasta() {
        sanat.lisaa("am", "ma");
        assertEquals(valitsin.annaSana("suomi"), "am");
    }
    
    @Test
    public void kysytaanEiTiedettyaSanaaValitunValiajanPaasta() {
        sanat.lisaa("am", "ma");
        tilasto.kasvataSanamaaraa();
        //vastataan väärin
        mokailut.lisaaSana(Kielet.kieli1, "am");
        
        sanat.lisaa("ad", "fa");
        tilasto.kasvataSanamaaraa();
        
        sanat.lisaa("as", "fs");
        tilasto.kasvataSanamaaraa();
        
        assertEquals(valitsin.annaSana(Kielet.kieli1), "am");
        
        
    }
}
