
package sanaohjelma.sovelluslogiikka;

public class Kayttaja {
    private String salasana;
    private String nimi;
    private Tilasto tilasto;
    
    public Kayttaja(String salasana, String nimi, Tilasto tilasto) {
        this.salasana = salasana;
        this.nimi = nimi;
        this.tilasto = tilasto;        
    }
    
    public String getSalasana() {
        return this.salasana;
    }
    
    public String getNimi() {
        return this.nimi;
    }
    
    public Tilasto getTilasto() {
        return this.tilasto;
    }
    
    public String tilasto() {
        return this.tilasto.toString();
    }
}
