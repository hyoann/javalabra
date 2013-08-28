package sanaohjelma.sovelluslogiikka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class Sanat {
    private HashMap<String, String> ekaToka;
    private HashMap<String, String> tokaEka;
    
    /**
     *Sanat-luokka pitää sisällään ohjelman suorituskerralle valitut sanat.
     * Sanaparit talletetaan kahteen HashMappiin, jolloin
     * sanan ja sitä vastaavan käännöksen hakeminen on helppoa molemmilla kielillä.
     */
    public Sanat() {
        this.ekaToka = new HashMap<String, String>();
        this.tokaEka = new HashMap<String, String>();
    }
    
    /**
     *Metodi lisää sanat HashMappeihin.
     * 
     * @param kieli1 Lisättävä sana halutulla kielellä
     * @param kieli2 Sanan käännös halutulla kielellä
     */
    public void lisaa(String kieli1, String kieli2) {
        if (kieli1 == null || kieli2 == null) {
            return;
        }
        ekaToka.put(kieli1, kieli2);
        tokaEka.put(kieli2, kieli1);
    }
    
    /**
     *Metodi kääntää poistettavan sanan kielelle 2, jonka jälkeen poistaa
     * sanat vastaavista HashMap-rakenteista.
     * 
     * @param sanaKielella1 Sana jonka käyttäjä haluaa poistaa.
     */
    public boolean poista(String sanaKielella1) {
        String sanaKielella2 = this.kaannaKielelle2(sanaKielella1);
        
        if (sanaKielella1!= null && sanaKielella2 != null) {
            ekaToka.remove(sanaKielella1);
            tokaEka.remove(sanaKielella2);
            return true;
        }
        return false;
   }
    
    /**
     *Metodi hakee kielella 2 annetun sanan käännöksen.
     * 
     * @param kieli2 Sana joka halutaan kääntää.
     * @return Kielellä 2 annetun sanan käännös kielellä 1.
     */
    public String kaannaKielelle1(String kieli2) {
        return tokaEka.get(kieli2);
    }
    
    /**
     *Metodi hakee kielellä 1 annetun sanan käännöksen.
     * 
     * @param kieli1 Sana jolle halutaan käännös.
     * @return Sanan kieli1 käännös kielellä 2.
     */
    public String kaannaKielelle2(String kieli1) {
        return ekaToka.get(kieli1);
    }
    
    /**
     *Metodi arpoo satunnaisesti jonkun sanan halutulla kielellä.
     * Ensiksi valitaan oikea HashMap, ja sitten sen avaimet.
     * Avaimet siirretään ArrayList-rakenteeseen, jolloin voidaan valita
     * sana satunnaisesti arvotun indeksin perusteella.
     * 
     * @param kieli Kertoo millä kielellä sanan pitää olla.
     * @return Satunnaisesti arvottu sana.
     */
    public String annaJokuSana(String kieli) {
        if (this.sanojenMaara() == 0) {
            return null;
        }
        
        Random arpoja = new Random();
        int indeksi = arpoja.nextInt(ekaToka.size());
        
        Set avaimet = null;
        
        if (kieli.equals(Kielet.getKieli1())) {
            avaimet = this.ekaToka.keySet();
        } else {
            avaimet = this.tokaEka.keySet();
        }
        
        ArrayList<String> avaimetListassa = new ArrayList<String>(avaimet);
        
        return avaimetListassa.get(indeksi);
    }
    
    /**
     *Metodi kertoo talletettujen sanojen maaran. Koska sanapareja on HashMapeissa
     * yhtä paljon, riittää valita vain toisen koko.
     * @return Ohjelmassa sen suorituskerralla olevien sanojen maara.
     */
    public int sanojenMaara() {
        return this.ekaToka.size();
    }
    
    public String toString() {
        String sanatJonossa = "";
        
        for (String sana : this.ekaToka.keySet()) {
            sanatJonossa += sana + " - " + this.kaannaKielelle2(sana) + "\n";
        }
        
        return sanatJonossa;
    }
}
