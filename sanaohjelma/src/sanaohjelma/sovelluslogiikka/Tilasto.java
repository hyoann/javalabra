package sanaohjelma.sovelluslogiikka;

import java.text.DecimalFormat;

public class Tilasto {
    private int kysytytSanat;
    private int oikeatVastaukset;

    public Tilasto(int kysytytSanat, int oikeatVastaukset) {
        if (kysytytSanat < 0 || oikeatVastaukset < 0) {
            kysytytSanat = 0;
            oikeatVastaukset = 0;
        }
        this.kysytytSanat = kysytytSanat;
        this.oikeatVastaukset = oikeatVastaukset;
    }
    
    public Tilasto() {
        this.kysytytSanat = 0;
        this.oikeatVastaukset = 0;
    }
    
    public void kasvataSanamaaraa() {
        this.kysytytSanat++;
    }
    
    public int sanamaara() {
        return this.kysytytSanat;
    }
    
    public void kasvataOikeita() {
        this.oikeatVastaukset++;
    }
    
    public int oikeinVastattu() {
        return this.oikeatVastaukset;
    }
    
    public double voittoprosentti() {     
        double voitto = 100 * oikeinVastattu() / (double)sanamaara();
    
        return voitto;
    }
    
    public String pyoristaDesimaali(double arvo) {
        DecimalFormat formatoija = new DecimalFormat("#.##");
        return formatoija.format(arvo);
    }
    
    public String toString() {
        String tilasto = "";
        
        tilasto += "Sanoja on kysytty yhteensä: " + this.kysytytSanat + " kpl\n";
        tilasto += "Oikeita vastauksia: " + this.oikeinVastattu() + " kpl\n";
        tilasto += "Voittoprosentti: " + pyoristaDesimaali(this.voittoprosentti()) + " %\n";
        
        return tilasto;
    }
}
