package sanaohjelma.sovelluslogiikka;

import java.util.ArrayList;
import java.util.Random;

public class Tilasto {
    private MokausMuistio suomi;
    private MokausMuistio vieras;
    private int kysytytSanat;

    public Tilasto() {
        this.suomi = new MokausMuistio();
        this.vieras = new MokausMuistio();
        this.kysytytSanat = 0;
    }
    
    public void kasvataSanamaaraa() {
        this.kysytytSanat++;
    }
    
    public int sanamaara() {
        return this.kysytytSanat;
    }
    
    public int mokauskerrat() {
        return this.suomi.mokauskerrat() + this.vieras.mokauskerrat();
    }
    
    public double voittoprosentti() {
        int oikeinVastatutKerrat = sanamaara() - mokauskerrat();
        
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
        tilasto += "Oikeita vastauksia: " + (this.kysytytSanat - this.mokauskerrat()) + " kpl \n";
        tilasto += "Voittoprosentti: " + this.voittoprosentti() + " %";
        
        return tilasto;
    }
}
