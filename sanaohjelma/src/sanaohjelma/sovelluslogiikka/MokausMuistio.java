
package sanaohjelma.sovelluslogiikka;

import java.util.ArrayList;
import java.util.Random;

//MokausMuistio pitää kirjaa sanoista, joita ei ole osattu kääntää oikein

public class MokausMuistio {
    private ArrayList<String> vaarinArvatut;
    
    public MokausMuistio() {
        this.vaarinArvatut = new ArrayList<String>();
    }
    
    public void lisaaSana(String sana) {
        this.vaarinArvatut.add(sana);
    }
    
    public void poistaSana(String sana) {
        if (this.vaarinArvatut.contains(sana)) {
            this.vaarinArvatut.remove(sana);
        }
    }
    
    public String annaJokuSana() {
        if (this.vaarinArvatut.isEmpty()) {
            return null;
        }
        
        Random arpoja = new Random();
        int indeksi = arpoja.nextInt(this.vaarinArvatut.size());
        
        return this.vaarinArvatut.get(indeksi);        
    }
}
