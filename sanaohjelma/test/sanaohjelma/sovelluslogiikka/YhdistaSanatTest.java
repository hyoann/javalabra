package sanaohjelma.sovelluslogiikka;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class YhdistaSanatTest {

    Sanat sanat;
    YhdistaSanat yhdista;

    @Before
    public void setUp() {
        Kielet.asetaKielet("suomi", "englanti");
        sanat = new Sanat();
        sanat.lisaa("talo", "house");
        sanat.lisaa("lehmä", "cow");
        sanat.lisaa("vesi", "water");
        yhdista = new YhdistaSanat(sanat);
        yhdista.taytaListat(3);
    }

    @Test
    public void listatTayttyvatOikein() {
        assertTrue(yhdista.palautaSanatNumerolla().size() == 3);
        assertTrue(yhdista.palautaKaannoksetKirjaimella().size() == 3);

        //sanojen paikat listassa arvotaan joten voidaan testata että lista sisältää
        //halutun määrän eri sanoja joillain paikoilla
        assertTrue(yhdista.palautaSanatNumerolla().contains("1.talo")
                || yhdista.palautaSanatNumerolla().contains("2.talo")
                || yhdista.palautaSanatNumerolla().contains("3.talo"));
        assertTrue(yhdista.palautaSanatNumerolla().contains("1.lehmä")
                || yhdista.palautaSanatNumerolla().contains("2.lehmä")
                || yhdista.palautaSanatNumerolla().contains("3.lehmä"));
        assertTrue(yhdista.palautaSanatNumerolla().contains("1.vesi")
                || yhdista.palautaSanatNumerolla().contains("2.vesi")
                || yhdista.palautaSanatNumerolla().contains("3.vesi"));

        assertTrue(yhdista.palautaKaannoksetKirjaimella().contains("a.house")
                || yhdista.palautaKaannoksetKirjaimella().contains("b.house")
                || yhdista.palautaKaannoksetKirjaimella().contains("c.house"));
        assertTrue(yhdista.palautaKaannoksetKirjaimella().contains("a.cow")
                || yhdista.palautaKaannoksetKirjaimella().contains("b.cow")
                || yhdista.palautaKaannoksetKirjaimella().contains("c.cow"));
        assertTrue(yhdista.palautaKaannoksetKirjaimella().contains("a.water")
                || yhdista.palautaKaannoksetKirjaimella().contains("b.water")
                || yhdista.palautaKaannoksetKirjaimella().contains("c.water"));

    }

    @Test
    public void tarkistusPalauttaaTrueKunOikeaVastausAnnettu() {
        String sanaJaNumero = yhdista.palautaSanatNumerolla().get(0);
        String sana = sanaJaNumero.substring(2, sanaJaNumero.length());

        String kaannos = sanat.kaannaKielelle2(sana);

        ArrayList<String> kaannokset = yhdista.palautaKaannoksetKirjaimella();
        String kaannosJaKirjain = "";

        //etsitään sanaa vastaava käännös käännöslistasta
        for (int i = 0; i < kaannokset.size(); i++) {
            if (kaannokset.get(i).contains(kaannos)) {
                kaannosJaKirjain = kaannokset.get(i);
                break;
            }
        }

        //tiedetään nyt siis, missä kohtaa ensimmäisen sanan pari on
        //kaannosJaKirjain on esim. 'b.cow', ensimmäinen kirjain on siis käännöksen yksilöivä tunnus
        //jonka käyttäjä antaa
        assertTrue(yhdista.tarkista("1" + kaannosJaKirjain.charAt(0)));

    }

    @Test
    public void vaarassaMuodossaAnnettuVastausPalauttaaFalse() {
        assertFalse(yhdista.tarkista("1 a"));
    }
}
