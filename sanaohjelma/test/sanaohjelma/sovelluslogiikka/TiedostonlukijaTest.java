package sanaohjelma.sovelluslogiikka;

import java.io.File;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import sanaohjelma.sovelluslogiikka.Sanat;
import sanaohjelma.sovelluslogiikka.Tiedostonlukija;

public class TiedostonlukijaTest {

    Tiedostonlukija lukija;
    File tiedosto;
    File testikayttajat;

    @Before
    public void setUp() {
        lukija = new Tiedostonlukija();
        tiedosto = new File("test/sanaohjelma/sovelluslogiikka/Testikansio/testitiedosto.txt");
        testikayttajat = new File("test/sanaohjelma/sovelluslogiikka/Testikansio/testikayttajat.txt");
    }

    @Test
    public void lueTiedostoEiPalautaNullKunTiedostoEiTyhja() {
        assertNotNull(lukija.lueTiedosto(tiedosto));
    }

    @Test
    public void palautetaanNullJaTulostetaanVirheviestiKunTiedostoaEiPystytaLukemaan() {
        assertNull(lukija.lueTiedosto(new File("tata_tiedostoa_ei_ole_olemassa")));
    }

    @Test
    public void tallennaSanapariTulostaaVirheenJaEiTallennaMitaanKunTiedostossaSanatOudosti() {
        ArrayList<String> rivit = new ArrayList<String>();
        rivit.add("ihan väärin . tama on");
        rivit.add("niin-tamakin");
        Sanat sanat = lukija.tallennaSanaparit(rivit);
        assertTrue(sanat.sanojenMaara() == 0);
    }

    @Test
    public void tallennaSanaparitTallentaaSanatJotkaOikeaMuodossa() {
        ArrayList<String> rivit = new ArrayList<String>();
        rivit.add("koti - home");
        rivit.add("errror");
        rivit.add("juusto - cheese");
        Sanat sanat = lukija.tallennaSanaparit(rivit);

        assertEquals(sanat.toString(), "juusto - cheese\nkoti - home\n");

    }

    @Test
    public void haeKayttajatHakeeKayttajat() {
        ArrayList<String> rivit = new ArrayList<String>();
        rivit.add("uusi, kayttaja, salasana, 100, 50");
        rivit.add("toinen, tyyppi, salattu2, 0, 0");
        Kayttajat kayttajat = lukija.haeKayttajat(rivit);

        assertEquals(kayttajat.toString(), "uusi, kayttaja, salasana, 100, 50\ntoinen, tyyppi, salattu2, 0, 0\n");
    }

    @Test
    public void haeKayttajatEiTallennaVirheellisiaRivejaJaTulostaaVirheviestin() {
        ArrayList<String> rivit = new ArrayList<String>();
        rivit.add("uusi, kayttaja, salasana");
        rivit.add("toinen, tyyppi, salattu2, 0");
        Kayttajat kayttajat = lukija.haeKayttajat(rivit);

        assertEquals(kayttajat.toString(), "");
    }

    @Test
    public void haeKayttajatOhittaaVirheellisetRivitJaTallentaaMuut() {
        ArrayList<String> rivit = new ArrayList<String>();
        rivit.add("uusi, kayttaja, salasana, 100, 50");
        rivit.add("gflifgaf");
        rivit.add("toinen, tyyppi, salattu2, 0, 0");
        Kayttajat kayttajat = lukija.haeKayttajat(rivit);

        assertEquals(kayttajat.toString(), "uusi, kayttaja, salasana, 100, 50\ntoinen, tyyppi, salattu2, 0, 0\n");
    }

    @Test
    public void tuoSanatTuoSanatTiedostostaOikein() {
        Sanat sanat = lukija.tuoSanat(tiedosto);
        assertEquals(sanat.toString(), "kuu - moon\naurinko - sun\n");
    }

    @Test
    public void tuoSanatPalauttaaNullKunTiedostoaEiOle() {
        assertNull(lukija.tuoSanat(new File("imaginary_file")));
    }

    @Test
    public void tuoKayttajatTuoKayttajatTiedostostaOikein() {
        Kayttajat kayttajat = lukija.tuoKayttajat(testikayttajat);
        assertEquals(kayttajat.toString(), "nimi, tunnus, salasana, 0, 0\npekka, pek1, ppp, 2, 1\n");
    }

    @Test
    public void tuoKayttajatPalauttaaNullKunTiedostoaEiOle() {
        assertNull(lukija.tuoKayttajat(new File("imaginary_file")));
    }
    
    @Test
    public void tiedostojenNimetPalautetaanOikeinKunMontaTiedostoa() {
        ArrayList<String> nimet = lukija.tiedostojenNimet("test/sanaohjelma/sovelluslogiikka/Testikansio");
        assertTrue(nimet.get(0).equals("testikayttajat.txt"));
        assertTrue(nimet.get(1).equals("testitiedosto.txt"));
    }
    
    @Test
    public void kunKansiotaEiOlePalautetaanNull() {
       ArrayList<String> olematon = lukija.tiedostojenNimet(""); 
       assertNull(olematon);
    }
    
    @Test
    public void kunKansioTyhjaPalautetaanTyhjaLista() {
        ArrayList<String> tyhja = lukija.tiedostojenNimet("test/sanaohjelma/sovelluslogiikka/Testikansio/tyhjakansio");
        assertTrue(tyhja.isEmpty());
    }
}
