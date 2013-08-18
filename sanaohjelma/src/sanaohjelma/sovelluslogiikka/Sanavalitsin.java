package sanaohjelma.sovelluslogiikka;

public class Sanavalitsin {
    private int toistovali; //kuinka monen sanan välein kysytään väärin arvattuja sanoja
    private Sanat sanat;
    private Tilasto tilasto;

    public Sanavalitsin(int vali, Tilasto tilasto, Sanat sanat) {
        this.toistovali = vali;
        this.sanat = sanat;
        this.tilasto = tilasto;
    }

    public String annaSana(String kieli) {
        
    
        //onko aika  kysyä sanaa mokattujen sanojen listalta
        if (tilasto.sanamaara() != 0 && tilasto.sanamaara() % this.toistovali == 0) {
            String kysyttavaSana = tilasto.annaMuistio(kieli).annaJokuSana();
            if (kysyttavaSana != null) {
                    return kysyttavaSana;
            }
        }
            return this.sanat.annaJokuSana(kieli);
    }
}
