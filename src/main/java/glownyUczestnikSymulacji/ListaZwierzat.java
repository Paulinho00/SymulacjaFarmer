package glownyUczestnikSymulacji;

/**
 * Pelni role stanu konta gracza, przechowuje wszystkie zebrane przez niego zwierzeta
 */

public class ListaZwierzat {

    private int iloscKrolikow;
    private int iloscOwiec;
    private int iloscSwin;
    private int iloscKrow;
    private int iloscKoni;

    /**
     * tworzy obiekt ListaZwierzat
     * @param kroliki przekazuje startowa liczbe krolikow na koncie
     * @param owce przekazuje startowa liczbe
     * @param swinie przekazuje startowa liczbe swin
     * @param krowy przekazuje startowa liczbe krow
     * @param konie przekazuje startowa liczbe koni
     */
    public ListaZwierzat(int kroliki, int owce, int swinie, int krowy, int konie){
        iloscKrolikow = kroliki;
        iloscOwiec = owce;
        iloscSwin = swinie;
        iloscKrow = krowy;
        iloscKoni = konie;
    }

    /**
     * zwraca ilosc krolikow na koncie
     * @return {@link ListaZwierzat#iloscKrolikow}
     */
    public Integer getIloscKrolikow() {
        return iloscKrolikow;
    }

    /**
     * zwraca ilosc owiec na koncie
     * @return {@link ListaZwierzat#iloscOwiec}
     */
    public Integer getIloscOwiec() {
        return iloscOwiec;
    }

    /**
     * zwraca ilosc swin na koncie
     * @return {@link ListaZwierzat#iloscSwin}
     */
    public Integer getIloscSwin() {
        return iloscSwin;
    }

    /**
     * zwraca ilosc krow na koncie
     * @return {@link ListaZwierzat#iloscKrow}
     */
    public Integer getIloscKrow() {
        return iloscKrow;
    }

    /**
     * zwraca ilosc koni na koncie
     * @return {@link ListaZwierzat#iloscKoni}
     */
    public Integer getIloscKoni() {
        return iloscKoni;
    }

    /**
     * ustawia nowa ilosc krolikow na koncie
     * @param iloscKrolikow nowa liczba krolikow na koncie
     */
    protected void setIloscKrolikow(int iloscKrolikow) {
        this.iloscKrolikow = iloscKrolikow;
    }

    /**
     * ustawia nowa ilosc owiec na koncie
     * @param iloscOwiec nowa liczba owiec na koncie
     */
    protected void setIloscOwiec(int iloscOwiec) {
        this.iloscOwiec = iloscOwiec;
    }

    /**
     * ustawia nowa ilosc swin na koncie
     * @param iloscSwin nowa liczba swin na koncie
     */
    protected void setIloscSwin(int iloscSwin) {
        this.iloscSwin = iloscSwin;
    }

    /**
     * ustawia nowa ilosc krow na koncie
     * @param iloscKrow nowa liczba krow na koncie
     */
    protected void setIloscKrow(int iloscKrow) {
        this.iloscKrow = iloscKrow;
    }

    /**
     * ustawia nowa ilosc koni na koncie
     * @param iloscKoni nowa liczba koni na koncie
     */
    protected void setIloscKoni(int iloscKoni) {
        this.iloscKoni = iloscKoni;
    }
}
