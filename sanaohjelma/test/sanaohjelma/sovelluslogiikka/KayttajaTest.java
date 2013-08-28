
package sanaohjelma.sovelluslogiikka;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KayttajaTest {
    Kayttaja kayttaja;
    Tilasto tilasto;
    
    @Before
    public void setUp() {
        tilasto = new Tilasto();
        kayttaja = new Kayttaja("tunnus", "nimi", "salasana", tilasto);
    }

     @Test
     public void getteritToimivatOikein() {
        assertEquals(kayttaja.getTunnus(), "tunnus");
        assertEquals(kayttaja.getNimi(), "nimi");
        assertEquals(kayttaja.getSalasana(), "salasana");
        assertEquals(kayttaja.getTilasto(), tilasto);     
    }
     
     @Test
     public void kayttajanMerkkijonoEsitysOikein() {
         assertEquals(kayttaja.toString(), "tunnus, nimi, salasana, 0, 0");
     }
}
