package wykonujacyInterakcje;
import glownyUczestnikSymulacji.Gracz;

/**
 * wykonuje interkacje miedzy graczem a wilkiem i lisem
 */
public class WykonujacyInterakcje {
    private boolean czySpotkalWilka;
    private boolean czySpotkalLisa;
    private boolean czyZostalOkradziony;
    private final Gracz gracz;

    /**
     *
     * @param gracz referencja do obiektu gracza
     */
    public WykonujacyInterakcje( Gracz gracz){
        czySpotkalWilka=false;
        czySpotkalLisa=false;
        czyZostalOkradziony = false;
        this.gracz=gracz;
    }

    /**
     * usuwa kroliki z konta gracza
     */
    public void usuniecieKrolikowKonto(){
        if(!czySpotkalLisa) {
            gracz.zmnienIloscKonto("Krolik", 0);
            czySpotkalLisa = true;
        }
    }

    /**
     * zeruje konto gracza
     */
    public void wyczyszczenieKontaGracza(){
        if(!czySpotkalWilka) {
            gracz.zmnienIloscKonto("Krolik", 0);
            gracz.zmnienIloscKonto("Owca", 0);
            gracz.zmnienIloscKonto("Swinia", 0);
            gracz.zmnienIloscKonto("Krowa", 0);
            gracz.zmnienIloscKonto("Kon", 0);
            czySpotkalWilka = true;
        }
    }

    /**
     * Zabiera wszystkie zwierzeta z {@link WykonujacyInterakcje#gracz} do gracza przekazanego jako argument metody
     * @param gracz1 otrzymujacy zwierzeta z {@link WykonujacyInterakcje#gracz}
     */
    public void spotkanieZGraczem(Gracz gracz1){
            gracz1.zmnienIloscKonto("Krolik", gracz1.getStanKonta().getIloscKrolikow() + gracz.getStanKonta().getIloscKrolikow());
            gracz.zmnienIloscKonto("Krolik", 0);
            gracz1.zmnienIloscKonto("Owca", gracz1.getStanKonta().getIloscOwiec() + gracz.getStanKonta().getIloscOwiec());
            gracz.zmnienIloscKonto("Owca", 0);
            gracz1.zmnienIloscKonto("Swinia", gracz1.getStanKonta().getIloscSwin() + gracz.getStanKonta().getIloscSwin());
            gracz.zmnienIloscKonto("Swinia", 0);
            gracz1.zmnienIloscKonto("Krowa", gracz1.getStanKonta().getIloscKrow() + gracz.getStanKonta().getIloscSwin());
            gracz.zmnienIloscKonto("Krowa", 0);
            gracz1.zmnienIloscKonto("Kon", gracz1.getStanKonta().getIloscKoni() + gracz.getStanKonta().getIloscKrow());
            gracz.zmnienIloscKonto("Krowa", 0);
            czyZostalOkradziony = true;
    }

    /**
     *
     * @param czySpotkalWilka true jesli spotkal wilka, false jesli nie
     */
    public void setCzySpotkalWilka(boolean czySpotkalWilka) {
        this.czySpotkalWilka = czySpotkalWilka;
    }

    /**
     *
     * @param czySpotkalLisa true jesli spotkal lisa, false jesli nie
     */
    public void setCzySpotkalLisa(boolean czySpotkalLisa) {
        this.czySpotkalLisa = czySpotkalLisa;
    }

    /**
     *
     * @return czy gracz spotkal wilka w tej turze
     */
    public boolean isCzySpotkalWilka() {
        return czySpotkalWilka;
    }

    /**
     *
     * @return czy gracz spotkal lisa w tej turze
     */
    public boolean isCzySpotkalLisa() {
        return czySpotkalLisa;
    }

    /**
     *
     * @param czyZostalOkradziony true jesli zostal okradziony
     */
    public void setCzyZostalOkradziony(boolean czyZostalOkradziony) {
        this.czyZostalOkradziony = czyZostalOkradziony;
    }

    /**
     *
     * @return czy gracz zostal okradziony przez innego gracza
     */
    public boolean isCzyZostalOkradziony() {
        return czyZostalOkradziony;
    }
}
