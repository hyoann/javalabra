
package sanaohjelma.kayttoliittyma.Kayttaja;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import sanaohjelma.Sanaohjelma;
import sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat.Kayttaja.ListanKuuntelija;
import sanaohjelma.kayttoliittyma.Tapahtumankuuntelijat.Kayttaja.ValikonKuuntelija;

public class Valikko extends JPanel{
    private JFrame frame;
    private Sanaohjelma ohjelma;
    
    public Valikko(JFrame frame, Sanaohjelma ohjelma) {
        this.frame = frame;
        this.ohjelma = ohjelma;
        this.luoKomponentit();
    }

    private void luoKomponentit() {
        JLabel tervehdys = new JLabel("Hei " + this.ohjelma.kayttajanNimi() + "!");     
        JButton kaanna = new JButton("Käännöstehtävä");
        JButton yhdista = new JButton("Yhdistä sanaparit");
        JButton omatTiedot = new JButton("Näytä omat tiedot");
        JButton ulos = new JButton("Kirjaudu ulos");     
        JLabel tiedostot = new JLabel("Tiedostot");
        JList lista = new JList(this.ohjelma.tiedostojenNimet().toArray());
        
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        
        ValikonKuuntelija kuuntelija = new ValikonKuuntelija(this.ohjelma, this.frame, lista);
        
        lista.addListSelectionListener(new ListanKuuntelija(this.frame, this.ohjelma, lista));

        kaanna.addActionListener(kuuntelija);
        yhdista.addActionListener(kuuntelija);
        omatTiedot.addActionListener(kuuntelija);
        ulos.addActionListener(kuuntelija);
        
        add(tervehdys);
        add(kaanna);
        add(yhdista);
        add(omatTiedot);
        add(ulos);
        add(tiedostot);
        add(lista);
    }
    
}
