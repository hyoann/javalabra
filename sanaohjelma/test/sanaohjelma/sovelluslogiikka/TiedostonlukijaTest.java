package sanaohjelma.sovelluslogiikka;


import java.io.File;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import sanaohjelma.sovelluslogiikka.Sanat;
import sanaohjelma.sovelluslogiikka.Tiedostonlukija;

public class TiedostonlukijaTest {
    Tiedostonlukija lukija;
    File tiedosto;

    @Before
    public void setUp() {
        lukija = new Tiedostonlukija();
        tiedosto = new File("test/sanaohjelma/sovelluslogiikka/testitiedosto.txt");
    }

    @Test
    public void lueTiedostoEiPalautaNullKunTiedostoEiTyhja() {
        assertNotNull(lukija.lueTiedosto(tiedosto));
    }

    @Test
    public void lueTiedostoPalauttaaSanatLuokanOlion() {
        assertTrue(lukija.tuoSanat(tiedosto) instanceof Sanat);
    }

    @Test
    public void lueTiedostoTallentaaSanaparitOikeinSanatOlioon() {
        assertEquals(lukija.tuoSanat(tiedosto).kaannaKielelle2("a"), "b");
        assertEquals(lukija.tuoSanat(tiedosto).kaannaKielelle2("ab"), "ba");
    }
}
