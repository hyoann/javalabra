package sanaohjelma.sovelluslogiikka;


import java.io.File;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import sanaohjelma.sovelluslogiikka.Sanat;
import sanaohjelma.sovelluslogiikka.Tiedostonlukija;

public class TiedostonlukijaTest {

    Tiedostonlukija lukija;

    @Before
    public void setUp() {
        lukija = new Tiedostonlukija(new File("test/sanaohjelma/sovelluslogiikka/testitiedosto"));
    }

    @Test
    public void lueTiedostoEiPalautaNullKunTiedostoEiTyhja() {
        assertNotNull(lukija.lueTiedosto());
    }

    @Test
    public void lueTiedostoPalauttaaSanatLuokanOlion() {
        assertTrue(lukija.lueTiedosto() instanceof Sanat);
    }

    @Test
    public void lueTiedostoTallentaaSanaparitOikeinSanatOlioon() {
        assertEquals(lukija.lueTiedosto().kaannaVieraaseen("a"), "b");
        assertEquals(lukija.lueTiedosto().kaannaVieraaseen("ab"), "ba");
    }
}
