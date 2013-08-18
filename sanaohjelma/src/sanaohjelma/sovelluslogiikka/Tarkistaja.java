
package sanaohjelma.sovelluslogiikka;

public class Tarkistaja {
    private Sanat sanat;
    private String kieli;
    
    public Tarkistaja(Sanat sanat, String kieli) {
        this.sanat = sanat;
        this.kieli = kieli;
    }
    
    public String haeOikeaVastaus(String kysyttySana) {
        String oikeaVastaus;
        
        if (this.kieli.equals("suomi")) {
            oikeaVastaus = this.sanat.kaannaVieraaseen(kysyttySana);
        } else {
            oikeaVastaus = this.sanat.kaannaSuomeen(kysyttySana);
        }
        return oikeaVastaus;
    }
    
    public boolean vastausOikein(String kysyttySana, String kayttajanVastaus) {
        String oikeaVastaus = haeOikeaVastaus(kysyttySana);
        
        if (kayttajanVastaus.equals(oikeaVastaus)) {
            return true;
        }
        return false;
    }
    
}
