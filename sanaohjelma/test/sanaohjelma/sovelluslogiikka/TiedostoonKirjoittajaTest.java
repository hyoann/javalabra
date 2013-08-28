package sanaohjelma.sovelluslogiikka;

import java.io.File;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TiedostoonKirjoittajaTest {
    Tiedostonlukija lukija;
    TiedostoonKirjoittaja kirjoittaja;

    @Before
    public void setUp() {
        kirjoittaja = new TiedostoonKirjoittaja();
        lukija = new Tiedostonlukija();
    }

    @After
    public void tearDown() {
        kirjoittaja.kirjoitaTiedostoon("test/sanaohjelma/sovelluslogiikka/kirjoittajantesti.txt", "");
    }

    @Test
    public void kirjoittaaTiedostoonOikein() {
        kirjoittaja.kirjoitaTiedostoon("test/sanaohjelma/sovelluslogiikka/kirjoittajantesti.txt", "eka rivi\ntoka rivi");
        ArrayList<String> sisalto = lukija.lueTiedosto(new File("test/sanaohjelma/sovelluslogiikka/kirjoittajantesti.txt"));

        assertTrue(sisalto.get(0).equals("eka rivi"));
        assertTrue(sisalto.get(1).equals("toka rivi"));
    }

    @Test
    public void lisaaTiedostoonOikein() {
        kirjoittaja.lisaaTiedostoon("test/sanaohjelma/sovelluslogiikka/kirjoittajantesti.txt", "ensimmainen");
        kirjoittaja.lisaaTiedostoon("test/sanaohjelma/sovelluslogiikka/kirjoittajantesti.txt", "toinen");
        ArrayList<String> sisalto = lukija.lueTiedosto(new File("test/sanaohjelma/sovelluslogiikka/kirjoittajantesti.txt"));

        assertTrue(sisalto.get(0).equals("ensimmainen"));
        assertTrue(sisalto.get(1).equals("toinen"));
    }

    @Test
    public void tallennaSanaparitTallentaaOikein() {
        kirjoittaja.tallennaSanapari("test/sanaohjelma/sovelluslogiikka/kirjoittajantesti.txt", "sana1", "sana2");
        ArrayList<String> sisalto = lukija.lueTiedosto(new File("test/sanaohjelma/sovelluslogiikka/kirjoittajantesti.txt"));

        assertTrue(sisalto.get(0).equals("sana1 - sana2"));
    }
}
