package wykonujacyInterakcje;
import glownyUczestnikSymulacji.Gracz;

/**
 * wykonuje interkacje miedzy graczem a wilkiem i lisem
 */
public class WykonujacyInterakcje {
    private boolean czySpotkalWilka;
    private boolean czySpotkalLisa;
    private final Gracz gracz;

    /**
     *
     * @param gracz referencja do obiektu gracza
     */
    public WykonujacyInterakcje( Gracz gracz){
        this.czySpotkalWilka=false;
        this.czySpotkalLisa=false;
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
}
