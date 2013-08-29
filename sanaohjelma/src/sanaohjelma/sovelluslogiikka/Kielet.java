package sanaohjelma.sovelluslogiikka;

/**
 * Kielet-luokassa määritellään ohjelmassa kysyttävien kielten nimet. Metodit ovat staattisia
 * jotta niitä voisi kutsua helposti muualta ohjelmasta.
 */
public class Kielet {

    private static String kieli1;
    private static String kieli2;

    public static void asetaKielet(String kieli1, String kieli2) {
        if (kieli1 == null || kieli2 == null) {
            return;
        }
        Kielet.kieli1 = kieli1;
        Kielet.kieli2 = kieli2;
    }

    public static String getKieli1() {
        return Kielet.kieli1;
    }

    public static String getKieli2() {
        return Kielet.kieli2;
    }
}
