package sanaohjelma.sovelluslogiikka;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class HallintaTest {

    Hallinta hallinta;
    Sanat sanat;

    @Before
    public void setUp() {
        Kielet.asetaKielet("suomi", "englanti");
        hallinta = new Hallinta();
        hallinta.asetaToistojenTiheys(3);
        hallinta.lisaaKayttaja("testi", "testaaja", "testi1");
        sanat = new Sanat();
        sanat.lisaa("puu", "tree");
        sanat.lisaa("hiiri", "mouse");
        hallinta.asetaSanatSuoraan(sanat);
    }

    @After
    public void tearDown() {
        hallinta.poistaKayttaja("testi");
    }

    @Test
    public void lisaaKayttajaLisaaKayttajan() {
        assertTrue(hallinta.haeKayttaja("testi", "testi1") != null);
    }

    @Test
    public void lisaaKayttajaEiLisaaTyhjaaKayttajaa() {
        assertFalse(hallinta.lisaaKayttaja("", "", ""));
    }

    @Test
    public void haeKayttaja() {
        assertTrue(hallinta.haeKayttaja("gfdf.ounadg", "gd") == null);
    }

    @Test
    public void tallennaTilastoTallentaaTilastonTiedostoon() {
        assertTrue(hallinta.haeKayttaja("testi", "testi1").getTilasto().sanamaara() == 0);
        hallinta.haeKayttaja("testi", "testi1").getTilasto().kasvataSanamaaraa();
        assertTrue(hallinta.haeKayttaja("testi", "testi1").getTilasto().sanamaara() == 1);
        //tilasto kirjoitetaan tiedostoon
        hallinta.tallennaTilasto();

        //Kaynnistetaan uusi hallinta, jollin käyttäjien tiedot haetaan tiedostosta
        //muutoksen pitäisi olla tallentunut tiedostoon
        Hallinta uusi = new Hallinta();
        assertTrue(uusi.haeKayttaja("testi", "testi1").getTilasto().sanamaara() == 1);
    }

    @Test
    public void vastausOikeinKasvattaaKayttajanTilastoaOikeinKunVastausVaarin() {
        assertNotNull(hallinta.haeKayttaja("testi", "testi1").getTilasto());
        assertFalse(hallinta.vastausOikein("puu", "mouse", Kielet.getKieli1()));
        assertTrue(hallinta.haeKayttaja("testi", "testi1").getTilasto().sanamaara() == 1);
        assertTrue(hallinta.haeKayttaja("testi", "testi1").getTilasto().oikeinVastattu() == 0);
    }

    @Test
    public void vastausOikeinKasvattaaKayttajanTilastoaOikeinKunVastausOikein() {
        assertNotNull(hallinta.haeKayttaja("testi", "testi1").getTilasto());
        assertTrue(hallinta.vastausOikein("puu", "tree", Kielet.getKieli1()));
        assertTrue(hallinta.haeKayttaja("testi", "testi1").getTilasto().sanamaara() == 1);
        assertTrue(hallinta.haeKayttaja("testi", "testi1").getTilasto().oikeinVastattu() == 1);
    }

    @Test
    public void haeOikeaVastausPalauttaaOikeanVastauksen() {
        assertEquals(hallinta.haeOikeaVastaus("puu", Kielet.getKieli1()), "tree");
        assertEquals(hallinta.haeOikeaVastaus("tree", Kielet.getKieli2()), "puu");
    }
}
