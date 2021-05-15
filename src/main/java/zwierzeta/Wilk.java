package zwierzeta;
import miejsceSymulacji.Plansza;
import wykonujacyInterakcje.WykonujacyInterakcje;

import java.util.Random;

/**
 *  przemieszcza sie po planszy, czysci konto gracza,
 *  czysci pole ze zwierzetami
 */
public class Wilk implements postac.Postac {
    private int koordynatX;
    private int koordynatY;
    private final Plansza plansza;
    private final WykonujacyInterakcje handler;

    /**
     *
     * @param x startowy koordynat X na planszy
     * @param y startowy koordynat Y na planszy
     * @param plansza obiekt planszy na ktorym bedzie sie poruszac
     * @param handler referencje do obiektu wykonujacego interakcje z graczem
     */
    public Wilk(int x, int y, Plansza plansza, WykonujacyInterakcje handler ){
        koordynatX = x;
        koordynatY = y;
        this.plansza = plansza;
        this.handler = handler;
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
        } else {
            String Kto = plansza.ktoJest(koordynatX + ruchX, koordynatY + ruchY);

            if(Kto.equals("Gracz")) {
                handler.wyczyszczenieKontaGracza();
                return;
            }
            if (Kto.equals("Krolik") || Kto.equals("Owca") || Kto.equals("Swinia") || Kto.equals("Krowa") || Kto.equals("Kon")) {
                plansza.wyczyscPole(koordynatX + ruchX, koordynatY + ruchY);
                plansza.przemieszczenie(koordynatX, koordynatY, koordynatX + ruchX, koordynatY + ruchY);
                koordynatX += ruchX;
                koordynatY += ruchY;
            }

        }
    }

    /**
     *
     * @return aktualny koordynat X
     */
    @Override
    public int getKoordynatX() {
        return koordynatX;
    }

    /**
     *
     * @return aktualny koordynat Y
     */
    @Override
    public int getKoordynatY() {
        return koordynatY;
    }
}