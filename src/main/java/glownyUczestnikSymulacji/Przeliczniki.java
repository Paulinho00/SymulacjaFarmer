package glownyUczestnikSymulacji;

/**
 * Przechowuje stale przeliczniki wymiany zwierzat
 */
public class Przeliczniki {

    private static int krolikiZaOwce = 6;
    private static int owceZaSwinie = 2;
    private static int swinieZaKrowy = 3;
    private static int krowyZaKonie = 2;

    /**
     * zwraca przelicznik wymiany krolikow za owce
     * @return {@link Przeliczniki#krolikiZaOwce}
     */
    protected static int getKrolikiZaOwce() {
        return krolikiZaOwce;
    }

    /**
     * zwraca przelicznik wymiany owiec za swinie
     * @return {@link Przeliczniki#owceZaSwinie}
     */
    protected static int getOwceZaSwinie() {
        return owceZaSwinie;
    }

    /**
     * zwraca przelicznik wymiany swin za krowy
     * @return {@link Przeliczniki#swinieZaKrowy}
     */
    protected static int getSwinieZaKrowy() {
        return swinieZaKrowy;
    }

    /**
     * zwraca przelicznik wymiany krow za konie
     * @return {@link Przeliczniki#krowyZaKonie}
     */
    protected static int getKrowyZaKonie() {
        return krowyZaKonie;
    }
}
