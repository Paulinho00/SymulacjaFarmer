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
     *
     * @return staly przelicznik wymiany krolikow na owce
     */
    protected static int getKrolikiZaOwce() {
        return krolikiZaOwce;
    }

    /**
     *
     * @return staly przelicznik wymiany owiec na swinie
     */
    protected static int getOwceZaSwinie() {
        return owceZaSwinie;
    }

    /**
     *
     * @return staly przelicznik wymiany swin na krowy
     */
    protected static int getSwinieZaKrowy() {
        return swinieZaKrowy;
    }

    /**
     *
     * @return staly przelicznik wymiany krow na konie
     */
    protected static int getKrowyZaKonie() {
        return krowyZaKonie;
    }
}
