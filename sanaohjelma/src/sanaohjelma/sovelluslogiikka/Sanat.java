package sanaohjelma.sovelluslogiikka;

import java.util.HashMap;
import java.util.Set;

public class Sanat {
    private HashMap<String, String> sanat;
    
    public Sanat() {
        this.sanat = new HashMap<String, String>();
    }
    
    public void lisaa(String suomennos, String kaannos) {
        sanat.put(suomennos, kaannos);
    }
    
    public Set<String> suomennokset() {
        return sanat.keySet();
    }
    
    public String kaannos(String suomennos) {
        return sanat.get(suomennos);
    }
    
}
