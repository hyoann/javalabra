
package sanaohjelma.sovelluslogiikka;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class YhdistaSanatTest {
    Sanat sanat;
    YhdistaSanat yhdista;
    
    @Before
    public void setUp() {
        sanat = new Sanat();
        sanat.lisaa("talo", "house");
        sanat.lisaa("lehmä", "cow");
        sanat.lisaa("vesi", "water");
        yhdista = new YhdistaSanat(sanat);
        yhdista.taytaListat(3);
    }


     @Test
     public void listatTayttyvatOikein() {

     }
}
