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
        kayttaja = new Kayttaja("tunnus", "nimi", "salasana", new Tilasto(0, 0));
    }


     @Test
     public void lisaaKayttajaLisaaKayttajan() {
         kayttajat.lisaaKayttaja(kayttaja.getTunnus(), kayttaja);
         assertTrue(kayttajat.onKayttaja("tunnus", "salasana"));
     }
     
     @Test
     public void haeKayttajaHakeeKayttajan() {
         kayttajat.lisaaKayttaja(kayttaja.getTunnus(), kayttaja);
         assertEquals(kayttajat.haeKayttaja("tunnus", "salasana"), kayttaja);
     }
     
     @Test
     public void haeSasalanaHakeeSalasanan() {
         kayttajat.lisaaKayttaja(kayttaja.getTunnus(), kayttaja);
         assertEquals(kayttajat.haeSalasana("tunnus"), "salasana");
     }
}
