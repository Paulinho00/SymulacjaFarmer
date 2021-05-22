package zwierzeta;

import glownyUczestnikSymulacji.Gracz;
import miejsceSymulacji.Plansza;

import java.util.Random;

/**
 *  przemieszcza sie po planszy, czysci konto gracza,
 *  czysci pole ze zwierzetami
 */
public class Wilk implements postac.Postac {
    private int koordynatX;
    private int koordynatY;
    private final Plansza plansza;
    private boolean wykonalRuch;

    /**
     * tworzy obiekt Wilk
     * @param x startowy koordynat X na planszy
     * @param y startowy koordynat Y na planszy
     * @param plansza obiekt planszy na ktorym bedzie sie poruszac
     */
    public Wilk(int x, int y, Plansza plansza){
        koordynatX = x;
        koordynatY = y;
        wykonalRuch = false;
        this.plansza = plansza;
        plansza.setPola(x,y,this);
    }

    /**
     * wykonuje ruch obiektu, zmieniajac koordynaty i wykonujac potrzebne interakcje przy spotkaniu z innymi obiektami
     */
    @Override
    public void ruch() {
        int ruchX = 0;
        int ruchY = 0;
        Random losowanie = new Random();
        char KtoryKoordynat = (char) (losowanie.nextInt(2) + 120);
        switch (KtoryKoordynat) {
            case 'x':
                while (ruchX == 0 || koordynatX + ruchX >= plansza.getRozmiarX() || koordynatX + ruchX < 0)
                    ruchX = losowanie.nextInt(3) - 1;
                break;
            case 'y':
                while (ruchY == 0 || koordynatY + ruchY >= plansza.getRozmiarY() || koordynatY + ruchY < 0)
                    ruchY = losowanie.nextInt(3) - 1;
                break;
        }
        if (!plansza.czyZajete(koordynatX + ruchX, koordynatY + ruchY)) {
            plansza.przemieszczenie(koordynatX, koordynatY, koordynatX + ruchX, koordynatY + ruchY);
            koordynatX += ruchX;
            koordynatY += ruchY;
            wykonalRuch = true;
        } else {
            String Kto = plansza.ktoJest(koordynatX + ruchX, koordynatY + ruchY);

            if(Kto.equals("Gracz")) {
                Gracz gracz = (Gracz) plansza.getPola(koordynatX+ruchX,koordynatY+ruchY);
                gracz.getHandler().wyczyszczenieKontaGracza();
                wykonalRuch = true;
                return;
            }
            if (Kto.equals("Krolik") || Kto.equals("Owca") || Kto.equals("Swinia") || Kto.equals("Krowa") || Kto.equals("Kon")) {
                plansza.wyczyscPole(koordynatX + ruchX, koordynatY + ruchY);
                plansza.przemieszczenie(koordynatX, koordynatY, koordynatX + ruchX, koordynatY + ruchY);
                koordynatX += ruchX;
                koordynatY += ruchY;
                wykonalRuch = true;
            }

        }
    }

    /**
     * zwraca aktualny koordynat X
     * @return {@link Wilk#koordynatX}
     */
    @Override
    public int getKoordynatX() {
        return koordynatX;
    }

    /**
     * zwraca aktualny koordynat Y
     * @return {@link Wilk#koordynatY}
     */
    @Override
    public int getKoordynatY() {
        return koordynatY;
    }

    /**
     * zwraca czy obiekt wykonal ruch
     * @return {@link Wilk#wykonalRuch}
     */
    public boolean isWykonalRuch() {
        return wykonalRuch;
    }

    /**
     * ustawia nowa wartosc pola {@link Wilk#wykonalRuch}
     * @param wykonalRuch nowa wartosc pola {@link Wilk#wykonalRuch}
     */
    public void setWykonalRuch(boolean wykonalRuch) {
        this.wykonalRuch = wykonalRuch;
    }
}
