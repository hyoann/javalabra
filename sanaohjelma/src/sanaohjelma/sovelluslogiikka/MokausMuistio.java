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
        if (kieli.equals(Kielet.getKieli1())) {
            this.kieli1.add(sana);
        } else {
            this.kieli2.add(sana);
        }
    }

    public void poistaSana(String sana, String kieli) {
        if (kieli.equals(Kielet.getKieli1())) {
            this.kieli1.remove(sana);
        } else {
            this.kieli2.remove(sana);
        }
    }

    public String annaJokuSana(String kieli) {
        ArrayList<String> mokatutSanat = this.kieli1;
        
        if (kieli.equals(Kielet.getKieli2())) {
            mokatutSanat = this.kieli2;
        }
        
        if (mokatutSanat.isEmpty()) {
            return null;
        }


        Random arpoja = new Random();
        int indeksi = arpoja.nextInt(mokatutSanat.size());

        return mokatutSanat.get(indeksi);
    }
}
