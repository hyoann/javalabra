package sanaohjelma.sovelluslogiikka;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Tiedostonlukija {
    private File tiedosto;

    public Tiedostonlukija(File tiedosto) {
        this.tiedosto = tiedosto;
    }

    public ArrayList<String> lueTiedosto() {
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
    
    public Sanat tuoSanat() {
        ArrayList<String> rivit = this.lueTiedosto();
        Sanat sanat = tallennaSanaparit(rivit);
        return sanat;
    }

}
