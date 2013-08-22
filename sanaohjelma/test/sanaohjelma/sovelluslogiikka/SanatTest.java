package sanaohjelma.sovelluslogiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SanatTest {

    Sanat sanat;

    @Before
    public void setUp() {
        sanat = new Sanat();
        Kielet.asetaKielet("suomi", "englanti");
    }

    @Test
    public void sanojenMaaraOnOikein() {
        assertTrue(sanat.sanojenMaara() == 0);

        sanat.lisaa("kissa", "cat");

        assertTrue(sanat.sanojenMaara() == 1);
    }

    @Test
    public void kaannosPalauttaaNullKunSanaaEiOle() {
        sanat.lisaa("hevonen", "horse");

        assertNull(sanat.kaannaKielelle1("dog"));
        assertNull(sanat.kaannaKielelle2("koira"));
    }

    @Test
    public void poistaPoistaaSanan() {
        sanat.lisaa("kissa", "cat");
        sanat.poista("kissa");

        assertTrue(sanat.sanojenMaara() == 0);
        assertNull(sanat.kaannaKielelle2("kissa"));
    }

    @Test
    public void kaannosHakeeKaannoksenKunYksiSanapari() {
        sanat.lisaa("hevonen", "horse");
        assertEquals(sanat.kaannaKielelle1("horse"), "hevonen");
        assertEquals(sanat.kaannaKielelle2("hevonen"), "horse");
    }

    @Test
    public void kaannosHakeeOikeanKaannoksenKunMontaSanaparia() {
        sanat.lisaa("hevonen", "horse");
        sanat.lisaa("kukka", "flower");
        sanat.lisaa("eläin", "animal");

        assertEquals(sanat.kaannaKielelle1("flower"), "kukka");
        assertEquals(sanat.kaannaKielelle2("kukka"), "flower");
    }

    @Test
    public void annaJokuSanaPalauttaaSanan() {
        sanat.lisaa("hevonen", "horse");
        assertEquals(sanat.annaJokuSana("suomi"), "hevonen");
    }

    @Test
    public void sanatTulostuvatOikein() {
        sanat.lisaa("hevonen", "horse");
        sanat.lisaa("kukka", "flower");
        assertEquals(sanat.toString(), "hevonen - horse\nkukka - flower\n");
    }
}
