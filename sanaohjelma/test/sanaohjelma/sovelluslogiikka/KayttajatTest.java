/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sanaohjelma.sovelluslogiikka;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KayttajatTest {
    Kayttajat kayttajat;
    Kayttaja kayttaja;
    
    @Before
    public void setUp() {
        kayttajat = new Kayttajat();
        kayttaja = new Kayttaja("tunnus", "nimi", "salasana");
        kayttajat.lisaaKayttaja(kayttaja.getTunnus(), kayttaja);
    }
     
     @Test
     public void lisaaKayttajaEiLisaaKayttajaaJosTunnusJoOlemassa() {
         assertFalse(kayttajat.lisaaKayttaja("tunnus", new Kayttaja("tunnus", "fad", "gsad")));
     }
     
     @Test
     public void lisaaKayttajaEiLisaaTyhjaaKayttajaa() {
         assertFalse(kayttajat.lisaaKayttaja("taegd",null));
     }
     
     @Test
     public void haeKayttajaHakeeKayttajan() {
         assertEquals(kayttajat.haeKayttaja("tunnus", "salasana"), kayttaja);
     }
     
     @Test
     public void haeKayttajaPalauttaaNullKunKayttajaEiOle() {
         assertNull(kayttajat.haeKayttaja("vnop", "hsfadei"));
     }
     
     @Test
     public void haeSasalanaHakeeSalasanan() {
         assertEquals(kayttajat.haeSalasana("tunnus"), "salasana");
     }
     
     @Test
     public void haeSalasanaPalauttaaNullKunKayttajaaEiOle() {
         assertNull(kayttajat.haeSalasana("fgggheshs"));
     }
     
     @Test
     public void merkkijonoEsitysOikein() {
         kayttajat.lisaaKayttaja("eka", new Kayttaja("eka", "toka", "kolmas"));
         String tulostuu = "eka, toka, kolmas, 0, 0\ntunnus, nimi, salasana, 0, 0\n";
         assertEquals(kayttajat.toString(), tulostuu);
     }
}
