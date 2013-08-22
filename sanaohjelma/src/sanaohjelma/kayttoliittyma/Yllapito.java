
package sanaohjelma.kayttoliittyma;

import java.util.Scanner;
import sanaohjelma.sovelluslogiikka.Tiedostonlukija;

public class Yllapito {
    private Scanner lukija;
    private Tiedostonlukija tiedostonLukija;
    
    public Yllapito(Scanner lukija, Tiedostonlukija tiedostonlukija) {
        this.lukija = lukija;
        this.tiedostonLukija = tiedostonlukija;
    }
    
    public void kaynnista() {
        System.out.println("Järjestelmässä olevat tiedostot:");
        System.out.println("Valitse toiminto:");
        System.out.println("    1 - Poista sanoja");
        System.out.println("    2 - Lisää sanoja");
        System.out.println("    3 - Poista tiedosto");
        System.out.println("    4 - Lisää tiedosto");
        System.out.println("    5 - Näytä tiedoston sisältö");
        System.out.println("    0 - Lopeta");
    
        String valinta = lukija.nextLine();
        
        if (valinta.equals("1")) {
            this.poistaSana();
        } else if (valinta.equals("2")) {
            this.lisaaSana();
        } else if (valinta.equals("3")) {
            this.poistaTiedosto();
        } else if (valinta.equals("4")) {
            this.lisaaTiedosto();
        }
    }

    private void poistaSana() {
        
    }

    private void lisaaSana() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void poistaTiedosto() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void lisaaTiedosto() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    private void valitseTiedosto() {
        
    }
    
}
