package sanaohjelma.sovelluslogiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SanavalitsinTest {

    Sanavalitsin valitsin;
    Tilasto tilasto;
    Sanat sanat;
    MokausMuistio mokailut;

    @Before
    public void setUp() {
        tilasto = new Tilasto();
        sanat = new Sanat();
        mokailut = new MokausMuistio();
        valitsin = new Sanavalitsin(3, tilasto, sanat, mokailut);
        Kielet.asetaKielet("suomi", "englanti");
    }

    @Test
    public void kysytaanSanaSanatLuokasta() {
        sanat.lisaa("am", "ma");
        assertEquals(valitsin.annaSana("suomi"), "am");
    }

    @Test
    public void kysytaanEiTiedettyaSanaaValitunValiajanPaasta() {
        sanat.lisaa("emt", "idk");
        sanat.lisaa("tama", "tiedetaan");
        sanat.lisaa("niin", "tamakin");
        sanat.lisaa("ja", "tama");

        tilasto.kasvataSanamaaraa();
        //vastataan väärin jolloin sana tallennetaan mokausmuistoon
        mokailut.lisaaSana("emt", Kielet.getKieli1());
        tilasto.kasvataSanamaaraa();
        tilasto.kasvataSanamaaraa();

        //nyt on  kolmas kysymys, joten aika kysyä mokattua sanaa
        assertEquals(valitsin.annaSana(Kielet.getKieli1()), "emt");
        assertEquals(valitsin.annaSana(Kielet.getKieli1()), "emt");
        assertEquals(valitsin.annaSana(Kielet.getKieli1()), "emt");

    }

    @Test
    public void kunToistovaliYksiKysytaanAinaMokattuaSanaa() {
        MokausMuistio uusiMuistio = new MokausMuistio();
        Sanavalitsin palauttaaAinaEmt = new Sanavalitsin(1, tilasto, sanat, uusiMuistio);
        sanat.lisaa("tama", "tiedetaan");

        uusiMuistio.lisaaSana("emt", Kielet.getKieli1());

        
        tilasto.kasvataSanamaaraa();
        assertEquals(palauttaaAinaEmt.annaSana(Kielet.getKieli1()), "emt");

        tilasto.kasvataSanamaaraa();
        assertEquals(palauttaaAinaEmt.annaSana(Kielet.getKieli1()), "emt");

        tilasto.kasvataSanamaaraa();
        assertEquals(palauttaaAinaEmt.annaSana(Kielet.getKieli1()), "emt");
    }

    @Test
    public void kysytaanSanaSanatLuokastaKunMuistiossaEiOleSanoja() {
        Sanat uudetSanat = new Sanat();
        Sanavalitsin uusiValitsin = new Sanavalitsin(3, tilasto, uudetSanat, new MokausMuistio());
        uudetSanat.lisaa("sana", "word");

        tilasto.kasvataSanamaaraa();
        tilasto.kasvataSanamaaraa();
        tilasto.kasvataSanamaaraa();

        assertEquals(uusiValitsin.annaSana(Kielet.getKieli1()), "sana");
        assertEquals(uusiValitsin.annaSana(Kielet.getKieli2()), "word");
    }

    @Test
    public void negatiivinenValiMuuttuuYkkoseksi() {
        Sanat uudetSanat = new Sanat();
        MokausMuistio uusiMuistio = new MokausMuistio();
        Sanavalitsin uusiValitsin = new Sanavalitsin(-999, tilasto, uudetSanat, uusiMuistio);

        uusiMuistio.lisaaSana("kylla", Kielet.getKieli1());

        tilasto.kasvataSanamaaraa();
        assertEquals(uusiValitsin.annaSana(Kielet.getKieli1()), "kylla");
        
        tilasto.kasvataSanamaaraa();
        assertEquals(uusiValitsin.annaSana(Kielet.getKieli1()), "kylla");
        
        tilasto.kasvataSanamaaraa();
        assertEquals(uusiValitsin.annaSana(Kielet.getKieli1()), "kylla");
    }
}
