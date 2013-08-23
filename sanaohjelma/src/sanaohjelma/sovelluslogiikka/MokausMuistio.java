package sanaohjelma.sovelluslogiikka;

import java.util.ArrayList;
import java.util.Random;

//MokausMuistio pitää kirjaa sanoista, joita ei ole osattu kääntää oikein
public class MokausMuistio {
    private ArrayList<String> kieli1;
    private ArrayList<String> kieli2;

    public MokausMuistio() {
        this.kieli1 = new ArrayList<String>();
        this.kieli2 = new ArrayList<String>();
    }

    public void lisaaSana(String sana, String kieli) {
        if (kieli.equals(Kielet.kieli1)) {
            this.kieli1.add(sana);
        } else {
            this.kieli2.add(sana);
        }
    }

    public void poistaSana(String sana, String kieli) {
        if (kieli.equals(Kielet.kieli1)) {
            this.kieli1.remove(sana);
        } else {
            this.kieli2.remove(sana);
        }
    }

    public String annaJokuSana(String kieli) {
        ArrayList<String> lista = this.kieli1;
        
        if (kieli.equals(Kielet.kieli2)) {
            lista = this.kieli2;
        }
        
        if (lista.isEmpty()) {
            return null;
        }


        Random arpoja = new Random();
        int indeksi = arpoja.nextInt(lista.size());

        return lista.get(indeksi);
    }
}
