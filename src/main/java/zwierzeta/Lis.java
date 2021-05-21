package zwierzeta;
import glownyUczestnikSymulacji.Gracz;
import miejsceSymulacji.Plansza;
import wykonujacyInterakcje.WykonujacyInterakcje;
import java.util.Random;

/**
 * przemieszcza sie po planszy, czysci konto gracza krolikow przy spotkaniu,
 * czysci pole z krolikow
 */
public class Lis implements postac.Postac{
    private int koordynatX;
    private int koordynatY;
    private final Plansza plansza;
    private boolean wykonalRuch;

    /**
     * tworzy obiekt Lis
     * @param x startowy koordynat X na planszy
     * @param y startowy koordynat Y na planszy
     * @param plansza plansza po ktorej bedzie sie poruszal
     */
    public Lis(int x, int y, Plansza plansza){
        koordynatX = x;
        koordynatY = y;
        this.plansza = plansza;
        plansza.setPola(x,y,this);
    }

    /**
     * wykonuje ruch obiektu, zmieniajac koordynaty i wykonujac potrzebne interakcje przy spotkaniu z innymi obiektami
     */
    @Override
    public void ruch() {
        boolean czyMozliwyX = false;
        boolean czyMozliwyY = false;
        int ruchX = 0;
        int ruchY = 0;
        Random losowanie = new Random();

        if (koordynatX + 1 < plansza.getRozmiarX()) {
            if (!plansza.czyZajete(koordynatX + 1, koordynatY)) czyMozliwyX = true;
            else if ((plansza.ktoJest(koordynatX + 1, koordynatY).equals("Gracz")) || (plansza.ktoJest(koordynatX+1, koordynatY).equals("Krolik"))) czyMozliwyX = true;

        }
        if (koordynatX - 1 >= 0) {
            if (!plansza.czyZajete(koordynatX - 1, koordynatY)) czyMozliwyX = true;
            else if ((plansza.ktoJest(koordynatX - 1, koordynatY ).equals("Gracz")) || (plansza.ktoJest(koordynatX - 1, koordynatY).equals("Krolik"))) czyMozliwyX = true;

        }
        if (koordynatY + 1 < plansza.getRozmiarY()) {
            if (!plansza.czyZajete(koordynatX, koordynatY + 1)) czyMozliwyY = true;
            else if ((plansza.ktoJest(koordynatX, koordynatY + 1).equals("Gracz")) || (plansza.ktoJest(koordynatX, koordynatY + 1).equals("Krolik"))) czyMozliwyY = true;

        }
        if (koordynatY - 1 >= 0) {
            if (!plansza.czyZajete(koordynatX, koordynatY - 1)) czyMozliwyY = true;
            else if ((plansza.ktoJest(koordynatX, koordynatY - 1).equals("Gracz")) || (plansza.ktoJest(koordynatX, koordynatY - 1).equals("Krolik")))
                czyMozliwyY = true;
        }

        if (czyMozliwyY || czyMozliwyX) {
            boolean NadajeSie = false;
            boolean dobryKord = false;
            char KtoryKoordynat = 0;
            while(!dobryKord) {
                KtoryKoordynat = (char) (losowanie.nextInt(2) + 120);
                if(KtoryKoordynat == 'x' && czyMozliwyX) dobryKord = true;
                if(KtoryKoordynat == 'y' && czyMozliwyY) dobryKord = true;
            }
            switch (KtoryKoordynat) {
                case 'x':
                    while (!NadajeSie) {
                        ruchX = losowanie.nextInt(3) - 1;
                        if (koordynatX + ruchX < plansza.getRozmiarX() && koordynatX + ruchX >= 0) {
                            if (!plansza.czyZajete(koordynatX + ruchX, koordynatY + ruchY)){ NadajeSie = true;}
                            else if ((plansza.ktoJest(koordynatX + ruchX, koordynatY + ruchY).equals("Gracz")) || (plansza.ktoJest(koordynatX + ruchX, koordynatY + ruchY).equals("Krolik"))) {
                                NadajeSie = true;
                            }
                        }
                    }
                    break;
                case 'y':
                    while (!NadajeSie) {
                        ruchY = losowanie.nextInt(3) - 1;
                        if (koordynatY + ruchY < plansza.getRozmiarY() && koordynatY + ruchY >= 0) {
                            if (!plansza.czyZajete(koordynatX + ruchX, koordynatY + ruchY)){ NadajeSie = true;}
                            else if ((plansza.ktoJest(koordynatX + ruchX, koordynatY + ruchY).equals("Gracz")) || (plansza.ktoJest(koordynatX + ruchX, koordynatY + ruchY).equals("Krolik"))) {
                                NadajeSie = true;
                            }
                        }

                    }
                    break;
            }

            if (plansza.czyZajete(koordynatX + ruchX, koordynatY + ruchY)) {
                String Kto = plansza.ktoJest(koordynatX + ruchX, koordynatY + ruchY);
                if (Kto.equals("Gracz")) {
                    Gracz gracz = (Gracz) plansza.getPola(koordynatX+ruchX, koordynatY+ruchY);
                    gracz.getHandler().usuniecieKrolikowKonto();
                    wykonalRuch=true;
                    return;
                }
                if (Kto.equals("Krolik")) {
                    plansza.wyczyscPole(koordynatX + ruchX, koordynatY + ruchY);
                    plansza.przemieszczenie(koordynatX, koordynatY, koordynatX + ruchX, koordynatY + ruchY);
                    koordynatX += ruchX;
                    koordynatY += ruchY;
                    wykonalRuch = true;
                }
            } else {
                plansza.przemieszczenie(koordynatX, koordynatY, koordynatX + ruchX, koordynatY + ruchY);
                koordynatX += ruchX;
                koordynatY += ruchY;
                wykonalRuch = true;
            }

        }
    }

    /**
     * zwraca aktualny koordynat X
     * @return {@link Lis#koordynatX}
     */
    @Override
    public int getKoordynatX() {
        return koordynatX;
    }

    /**
     * zwraca obecny koordynat Y na planszy
     * @return {@link Lis#koordynatY}
     */
    @Override
    public int getKoordynatY() {
        return koordynatY;
    }

    /**
     * zwraca czy obiekt wykonal ruch
     * @return {@link Lis#wykonalRuch}
     */
    public boolean isWykonalRuch() {
        return wykonalRuch;
    }

    /**
     * ustawia nowa wartosc pola {@link Lis#wykonalRuch}
     * @param wykonalRuch nowa wartosc pola {@link Lis#wykonalRuch}
     */
    public void setWykonalRuch(boolean wykonalRuch) {
        this.wykonalRuch = wykonalRuch;
    }
}
