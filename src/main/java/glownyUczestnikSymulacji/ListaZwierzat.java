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
     * @exception IllegalArgumentException gdy dane startowe sa zbyt duze
     */
    public ListaZwierzat(int kroliki, int owce, int swinie, int krowy, int konie){
        if(konie >= 2 || krowy >= 4 || swinie >= 11 || owce >= 22 || kroliki >= 127) throw new IllegalArgumentException("Zbyt duże dane: koniec symulacji");
        if(kroliki < 0 || krowy < 0 || owce < 0 || swinie < 0 || konie < 0) throw new IllegalArgumentException("Dane nie moga byc ujemne");
        iloscKrolikow = kroliki;
        iloscOwiec = owce;
        iloscSwin = swinie;
        iloscKrow = krowy;
        iloscKoni = konie;
    }

    /**
     *
     * @return ilosc krolikow na koncie
     */
    public Integer getIloscKrolikow() {
        return iloscKrolikow;
    }

    /**
     *
     * @return ilosc owiec na koncie
     */
    public Integer getIloscOwiec() {
        return iloscOwiec;
    }

    /**
     *
     * @return ilosc swin na koncie
     */
    public Integer getIloscSwin() {
        return iloscSwin;
    }

    /**
     *
     * @return ilosc krow na koncie
     */
    public Integer getIloscKrow() {
        return iloscKrow;
    }

    /**
     *
     * @return ilosc koni na koncie
     */
    public Integer getIloscKoni() {
        return iloscKoni;
    }

    /**
     *
     * @param iloscKrolikow nowa liczba krolikow na koncie
     */
    protected void setIloscKrolikow(int iloscKrolikow) {
        this.iloscKrolikow = iloscKrolikow;
    }

    /**
     *
     * @param iloscOwiec nowa liczba owiec na koncie
     */
    protected void setIloscOwiec(int iloscOwiec) {
        this.iloscOwiec = iloscOwiec;
    }

    /**
     *
     * @param iloscSwin nowa liczba swin na koncie
     */
    protected void setIloscSwin(int iloscSwin) {
        this.iloscSwin = iloscSwin;
    }

    /**
     *
     * @param iloscKrow nowa liczba krow na koncie
     */
    protected void setIloscKrow(int iloscKrow) {
        this.iloscKrow = iloscKrow;
    }

    /**
     *
     * @param iloscKoni nowa liczba koni na koncie
     */
    protected void setIloscKoni(int iloscKoni) {
        this.iloscKoni = iloscKoni;
    }
}
