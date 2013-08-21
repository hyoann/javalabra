
package sanaohjelma.sovelluslogiikka;

public class Kayttaja {
    private String nimi;
    private String salasana; 
    private Tilasto tilasto;
    
    public Kayttaja(String nimi, String salasana, Tilasto tilasto) {      
        this.nimi = nimi;
        this.salasana = salasana;
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
