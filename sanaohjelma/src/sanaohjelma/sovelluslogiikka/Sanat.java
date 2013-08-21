package sanaohjelma.sovelluslogiikka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class Sanat {
    private HashMap<String, String> suomiVieras;
    private HashMap<String, String> vierasSuomi;
    
    public Sanat() {
        this.suomiVieras = new HashMap<String, String>();
        this.vierasSuomi = new HashMap<String, String>();
    }
    
    public void lisaa(String suomi, String vieras) {
        suomiVieras.put(suomi, vieras);
        vierasSuomi.put(vieras, suomi);
    }
    
    public void poista(String sanaSuomeksi) {
        String sanaVieraaksi = this.kaannaVieraaseen(sanaSuomeksi);
        if (sanaVieraaksi != null) {
            suomiVieras.remove(sanaSuomeksi);
            vierasSuomi.remove(sanaVieraaksi);
        }
   }
    
    public String kaannaVieraaseen(String suomi) {
        return suomiVieras.get(suomi);
    }
    
    public String kaannaSuomeen(String vieras) {
        return vierasSuomi.get(vieras);
    }
    
    public String annaJokuSana(String kieli) {
        Random arpoja = new Random();
        int indeksi = arpoja.nextInt(suomiVieras.size());
        
        Set avaimet = null;
        
        if (kieli.equals("suomi")) {
            avaimet = this.suomiVieras.keySet();
        } else {
            avaimet = this.vierasSuomi.keySet();
        }
        
        ArrayList<String> avaimetListassa = new ArrayList<String>(avaimet);
        
        return avaimetListassa.get(indeksi);
    }
    
    public int sanojenMaara() {
        return this.suomiVieras.size();
    }
    
    public String toString() {
        String sanatJonossa = "";
        
        for (String sana : this.suomiVieras.keySet()) {
            sanatJonossa += sana + " - " + this.kaannaVieraaseen(sana) + "\n";
        }
        
        return sanatJonossa;
    }
}
