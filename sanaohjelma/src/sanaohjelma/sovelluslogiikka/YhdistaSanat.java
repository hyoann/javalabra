package sanaohjelma.sovelluslogiikka;

import java.util.ArrayList;
import java.util.Collections;

public class YhdistaSanat {

    private Sanat sanat;
    private ArrayList<String> arvotutSanat;
    private ArrayList<Integer> kaannokset;

    public YhdistaSanat(Sanat sanat) {
        this.arvotutSanat = new ArrayList<String>();
        this.kaannokset = new ArrayList<Integer>();
        this.sanat = sanat;
    }

    public void taytaListat(int maara) {
        int sanamaara = 0;

        while (sanamaara < maara) {
            String sana = this.sanat.annaJokuSana(Kielet.kieli1);

            if (!this.arvotutSanat.contains(sana)) {
                this.arvotutSanat.add(sana);
                this.kaannokset.add(sanamaara);
                sanamaara++;
            }
        }
        Collections.shuffle(this.kaannokset);
    }

    public ArrayList<String> palautaSanatNumerolla() {
        //tallennetaan sanat listaan muodossa '1.kissa 2.koira' jne...
        ArrayList<String> sanatNumeroituna = new ArrayList<String>();

        for (int i = 0; i < this.arvotutSanat.size(); i++) {
            sanatNumeroituna.add((i + 1) + "." + this.arvotutSanat.get(i));
        }
        return sanatNumeroituna;
    }

    public ArrayList<String> palautaKaannoksetKirjaimella() {
        //tallennetaan käännökset listaan muodossa 'a.dog b.cat' jne...
        ArrayList<String> kaannoksetKirjaimella = new ArrayList<String>();

        for (int i = 0; i < this.kaannokset.size(); i++) {
            String kaannos = this.sanat.kaannaKielelle2(this.arvotutSanat.get(this.kaannokset.get(i)));
            kaannoksetKirjaimella.add((char) (i + 97) + "." + kaannos);
        }
        return kaannoksetKirjaimella;
    }

    public boolean tarkista(String vastaus) {
        //vastaukset muodossa '1a', viimeisellä paikalla siis kirjain
        if (vastaus.length() < 2) {
            return false;
        }

        try {
            char kirjain = vastaus.charAt(vastaus.length() - 1);
            //ensimmäisessä osassa luku, jossa voi olla kaksi numeroa
            String luku = vastaus.substring(0, vastaus.length() - 1);
            //muutetaan luvuiksi
            int kaannos = (int) kirjain - 97;
            int sana = Integer.parseInt(luku) - 1;

            if (sana == this.kaannokset.get(kaannos)) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public String oikeatVastaukset() {
        String oikeaRivi = "";

        for (int i = 0; i < this.kaannokset.size(); i++) {
            oikeaRivi += this.kaannokset.get(i) + "" + (char) (i + 97) + " ";
        }
        return oikeaRivi;
    }
}
