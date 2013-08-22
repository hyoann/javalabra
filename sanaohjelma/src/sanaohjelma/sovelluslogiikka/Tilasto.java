package sanaohjelma.sovelluslogiikka;

import java.text.DecimalFormat;

public class Tilasto {
    private int kysytytSanat;
    private int vaaratVastaukset;

    public Tilasto(int kysytytSanat, int vaaratVastaukset) {
        this.kysytytSanat = kysytytSanat;
        this.vaaratVastaukset = vaaratVastaukset;
    }
    
    public void kasvataSanamaaraa() {
        this.kysytytSanat++;
    }
    
    public int sanamaara() {
        return this.kysytytSanat;
    }
    
    public void kasvataMokattuja() {
        this.vaaratVastaukset++;
    }
    
    public int mokausmaara() {
        return this.vaaratVastaukset;
    }
    
    public double voittoprosentti() {
        int oikeinVastatutKerrat = sanamaara() - mokausmaara();
        
        double voitto = 100 * oikeinVastatutKerrat / (double)sanamaara();
    
        return voitto;
    }
    
    public String pyoristaDesimaali(double arvo) {
        DecimalFormat formatoija = new DecimalFormat("#.##");
        return formatoija.format(arvo);
    }
    
    public String toString() {
        String tilasto = "";
        
        tilasto += "Sanoja on kysytty yhteensä: " + this.kysytytSanat + " kpl\n";
        tilasto += "Oikeita vastauksia: " + (this.kysytytSanat - this.mokausmaara()) + " kpl\n";
        tilasto += "Voittoprosentti: " + pyoristaDesimaali(this.voittoprosentti()) + " %";
        
        return tilasto;
    }
}
