package sanaohjelma.sovelluslogiikka;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Tiedostonlukija {

    public ArrayList<String> lueTiedosto(File tiedosto) {
        Scanner lukija = null;

        try {
            lukija = new Scanner(tiedosto, "UTF-8");
        } catch (Exception e) {
            System.out.println("Tiedoston lukeminen epäonnistui:" + e.getMessage());
            return null;
        }

        ArrayList<String> rivit = new ArrayList<String>();

        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();
            rivit.add(rivi);
        }

        lukija.close();

        return rivit;
    }

    public Sanat tallennaSanaparit(ArrayList<String> rivit) {
        Sanat sanat = new Sanat();

        for (String rivi : rivit) {
            String[] sanapari = rivi.split(" - ");
            sanat.lisaa(sanapari[0], sanapari[1]);
        }
        return sanat;
    }

    public Kayttajat haeKayttajat(ArrayList<String> rivit) {
        Kayttajat kayttajat = new Kayttajat();      

        for (String rivi : rivit) {           
            String[] tiedot = rivi.split(", ");
            
            //Tiedot ovat tiedostossa muodossa 'tunnus, nimi, salasana, kysytytSanat, oikeat vastaukset'
            int kysytytSanat = Integer.parseInt(tiedot[3]);
            int mokatut = Integer.parseInt(tiedot[4]);
            
            Tilasto tilasto = new Tilasto(kysytytSanat, mokatut);
            
            Kayttaja kayttaja = new Kayttaja(tiedot[0], tiedot[1], tiedot[2], tilasto);
            
            kayttajat.lisaaKayttaja(tiedot[0], kayttaja);
        }
        return kayttajat;
    }

    public Sanat tuoSanat(String tiedostonNimi) {
        File tiedosto = new File("src/sanaohjelma/Sanatiedostot/" + tiedostonNimi);
        ArrayList<String> rivit = this.lueTiedosto(tiedosto);
        Sanat sanat = tallennaSanaparit(rivit);
        return sanat;
    }

    public Kayttajat tuoKayttajat(File tiedosto) {
        ArrayList<String> rivit = this.lueTiedosto(tiedosto);
        Kayttajat kayttajat = haeKayttajat(rivit);
        return kayttajat;
    }
    
    public ArrayList<String> tiedostojenNimet() {
        File kansio = new File("src/sanaohjelma/Sanatiedostot");
        File[] tiedostot = kansio.listFiles();
        
        ArrayList<String> nimet = new ArrayList<String>();
        
        for (File tiedosto : tiedostot) {
            nimet.add(tiedosto.getName());
        }
        
        return nimet;
    }

}
