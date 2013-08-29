package sanaohjelma.sovelluslogiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TilastoTest {

    Tilasto tyhjatilasto;
    Tilasto tilasto;

    @Before
    public void setUp() {
        this.tyhjatilasto = new Tilasto();
        this.tilasto = new Tilasto(100, 50);
    }

    @Test
    public void parametritonTilastoLuoTyhjanTilaston() {
        assertTrue(tyhjatilasto.sanamaara() == 0);
        assertTrue(tyhjatilasto.oikeinVastattu() == 0);
    }

    @Test
    public void parametrillinenTilastoAlustaaTilastonOikein() {
        assertTrue(tilasto.sanamaara() == 100);
        assertTrue(tilasto.oikeinVastattu() == 50);
    }

    @Test
    public void josNegatiivisetArvotLuodaanTyhjaTilasto() {
        Tilasto nega = new Tilasto(-3, 4);
        assertTrue(nega.sanamaara() == 0);
        assertTrue(nega.oikeinVastattu() == 0);
    }

    @Test
    public void tilastoKasvattaaSanamaaraaOikein() {
        this.tilasto.kasvataSanamaaraa();
        this.tilasto.kasvataSanamaaraa();

        assertEquals(this.tilasto.sanamaara(), 102);
    }

    @Test
    public void voittoprosenttiLasketaanOikein() {
        this.tyhjatilasto.kasvataSanamaaraa();
        this.tyhjatilasto.kasvataSanamaaraa();
        this.tyhjatilasto.kasvataOikeita();

        assertTrue(this.tyhjatilasto.voittoprosentti() == 50.0);

    }
    
    @Test
    public void merkkijonoEsitytsOikein() {
        String tulos = "Sanoja on kysytty yhteensä: 100 kpl\n";
        tulos += "Oikeita vastauksia: 50 kpl\n";
        tulos += "Voittoprosentti: 50 %\n";
        
        assertEquals(tilasto.toString(), tulos);
    }
}
