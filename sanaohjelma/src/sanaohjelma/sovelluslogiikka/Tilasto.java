package sanaohjelma.sovelluslogiikka;

import java.util.ArrayList;
import java.util.Random;

public class Tilasto {
    private MokausMuistio suomi;
    private MokausMuistio vieras;
    private int kysytytSanat;
    private int vaaratVastaukset;

    public Tilasto(int kysytytSanat, int vaaratVastaukset) {
        this.suomi = new MokausMuistio();
        this.vieras = new MokausMuistio();
        this.vaaratVastaukset = kysytytSanat;
        this.kysytytSanat = vaaratVastaukset;
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
    
    public int mokatut() {
        return this.vaaratVastaukset;
    }
    
    public int mokauskerrat() {        
        return this.suomi.mokauskerrat() + this.vieras.mokauskerrat();
    }
    
    public double voittoprosentti() {
        int oikeinVastatutKerrat = sanamaara() - mokatut();
        
        double voitto = 100 * oikeinVastatutKerrat / (double)sanamaara();
    
        return voitto;
    }
    
    public MokausMuistio annaMuistio(String kieli) {
        if (kieli.equals("suomi")) {
            return this.suomi;
        }
        return this.vieras;
    }
    
    public String toString() {
        String tilasto = "";
        
        tilasto += "Sanoja on kysytty yhteensä: " + this.kysytytSanat + " kpl \n";
        tilasto += "Oikeita vastauksia: " + (this.kysytytSanat - this.mokatut()) + " kpl \n";
        tilasto += "Voittoprosentti: " + this.voittoprosentti() + " %";
        
        return tilasto;
    }
}
