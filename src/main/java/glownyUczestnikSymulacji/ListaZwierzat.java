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
     *
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
     * @return ilosc krolikow na koncie
     */
    public Integer getIloscKrolikow() {
        return iloscKrolikow;
    }

    /**
     * zwraca ilosc owiec na koncie
     * @return ilosc owiec na koncie
     */
    public Integer getIloscOwiec() {
        return iloscOwiec;
    }

    /**
     * zwraca ilosc swin na koncie
     * @return ilosc swin na koncie
     */
    public Integer getIloscSwin() {
        return iloscSwin;
    }

    /**
     * zwraca ilosc krow na koncie
     * @return ilosc krow na koncie
     */
    public Integer getIloscKrow() {
        return iloscKrow;
    }

    /**
     * zwraca ilosc koni na koncie
     * @return ilosc koni na koncie
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
