
package sanaohjelma.sovelluslogiikka;

/**
 *Tarkistaja-luokka huolehtii käännöstehtävän vastausten tarkistamisesta.
 */
public class Tarkistaja {
    
    /**
     * Järjestelmään tuodut sanat
     */
    private Sanat sanat;
    
    /**
     * Minkä kielisiä sanoja taristetaan
     */
    private String kieli;
    
    /**
     *
     * @param sanat Järjestelmän sanat
     * @param kieli Kieli jolla tarkastettava sana on
     */
    public Tarkistaja(Sanat sanat, String kieli) {
        this.sanat = sanat;
        this.kieli = kieli;
    }
    
    /**
     *Metodi hakee kännöksen sanalle. Ensiksi metodi selvittää, minkäkielistä
     * käännöstä halutaan.
     * 
     * @param kysyttySana Käyttäjältä kysytty sana, jolle halutaan oikea käännös
     * @return Kysytyn sanna käännös
     */
    public String haeOikeaVastaus(String kysyttySana) {
        String oikeaVastaus;
        
        if (this.kieli.equals(Kielet.getKieli1())) {
            oikeaVastaus = this.sanat.kaannaKielelle2(kysyttySana);
        } else {
            oikeaVastaus = this.sanat.kaannaKielelle1(kysyttySana);
        }
        return oikeaVastaus;
    }
    
    /**
     *Tarkistaa oliko käyttäjän antama vastaus oikein.
     * 
     * @param kysyttySana Käyttäjältä kysytty sana
     * @param kayttajanVastaus Käyttäjän antama vastaus
     * @return Paluttaa true, jos käyttäjä vastasi oikein ja false, jos väärin.
     */
    public boolean vastausOikein(String kysyttySana, String kayttajanVastaus) {
        String oikeaVastaus = haeOikeaVastaus(kysyttySana);
        
        if (kayttajanVastaus.equals(oikeaVastaus)) {
            return true;
        }
        return false;
    }
    
}
