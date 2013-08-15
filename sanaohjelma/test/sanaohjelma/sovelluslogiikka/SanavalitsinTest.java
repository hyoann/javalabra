
package sanaohjelma.sovelluslogiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SanavalitsinTest {
    Sanavalitsin valitsin;
    Tilasto tilasto;
    Sanat sanat;

    @Before
    public void setUp() {
        this.tilasto = new Tilasto();
        this.sanat = new Sanat();
        this.valitsin = new Sanavalitsin(4, this.tilasto, this.sanat);
    }

    @Test
    public void kysytaanSanaSanatLuokasta() {
        this.sanat.lisaa("am", "ma");
        assertEquals(this.valitsin.annaSana("suomi"), "am");
    }
    
    @Test
    public void kysytaanEiTiedettyaSanaaValitunValiajanPaasta() {
        this.sanat.lisaa("am", "ma");
        this.tilasto.annaMuistio("suomi").lisaaSana("am");
        this.tilasto.kasvataSanamaaraa();
        
        this.sanat.lisaa("ad", "fa");
        this.tilasto.kasvataSanamaaraa();
        
        this.sanat.lisaa("as", "fs");
        this.tilasto.kasvataSanamaaraa();
        
        assertEquals(this.valitsin.annaSana("suomi"), "am");
        
        
    }
}
