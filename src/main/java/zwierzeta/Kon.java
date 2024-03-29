package zwierzeta;
import miejsceSymulacji.Plansza;

import java.util.Random;

/**
 *  zwierze rodzaju "Kon" ktore porusza sie po planszy
 */
public class Kon implements postac.Postac{
    private int koordynatX;
    private int koordynatY;
    private final Plansza plansza;
    private boolean wykonalRuch;

    /**
     * tworzy obiekt Konia
     * @param x koordynat startowy X na ktorym pojawi sie obiekt na planszy
     * @param y koordynat startowy Y na ktorym pojawi sie obiekt na planszy
     * @param plansza plansza na ktorej porusza sie obiekt
     */
    public Kon(int x, int y, Plansza plansza){
        koordynatX = x;
        koordynatY = y;
        this.plansza = plansza;
        plansza.setPola(x,y,this);
        wykonalRuch=false;
    }

    /**
     * wykonuje ruch obiektu, zmieniajac swoje koordynaty i przesylajac zmiany do planszy
     */
    @Override
    public void ruch(){
        boolean czyMozliwyX = false;
        boolean czyMozliwyY = false;
        int ruchX = 0;
        int ruchY = 0;

        Random losowanie = new Random();

        if(koordynatX+1 < plansza.getRozmiarX()){
            if(!plansza.czyZajete(koordynatX+1, koordynatY)) czyMozliwyX = true;}
        if(koordynatX-1 >= 0){
            if(!plansza.czyZajete(koordynatX-1, koordynatY)) czyMozliwyX = true;}
        if(koordynatY+1 < plansza.getRozmiarY()){
            if(!plansza.czyZajete(koordynatX, koordynatY+1)) czyMozliwyY = true;}
        if(koordynatY-1 >= 0){
            if(!plansza.czyZajete(koordynatX, koordynatY-1)) czyMozliwyY = true;}

        if(czyMozliwyX || czyMozliwyY){
            boolean dobryKord=false;
            char KtoryKoordynat = 0;
            while(!dobryKord) {
                KtoryKoordynat = (char) (losowanie.nextInt(2) + 120);
                if (KtoryKoordynat == 'x' && czyMozliwyX) dobryKord = true;
                if (KtoryKoordynat == 'y' && czyMozliwyY) dobryKord = true;
            }
            switch(KtoryKoordynat){
                case 'x': while(ruchX == 0 || koordynatX+ruchX >= plansza.getRozmiarX() || koordynatX+ruchX < 0 || plansza.czyZajete(koordynatX+ruchX, koordynatY)) ruchX = losowanie.nextInt(3)-1; break;
                case 'y': while(ruchY == 0 || koordynatY+ruchY >= plansza.getRozmiarY() || koordynatY+ruchY < 0 || plansza.czyZajete(koordynatX, koordynatY+ruchY)) ruchY = losowanie.nextInt(3)-1; break;
            }
            plansza.przemieszczenie(koordynatX, koordynatY, koordynatX+ruchX, koordynatY+ruchY);
            koordynatX += ruchX;
            koordynatY += ruchY;
        }
        wykonalRuch = true;
    }

    /**
     * zwraca aktualny koordynat X obiektu {@link Kon#koordynatX}
     * @return {@link Kon#koordynatX}
     */
    @Override
    public int getKoordynatX() {
        return koordynatX;
    }

    /**
     * zwraca aktualny koordynat Y obiektu
     * @return {@link Kon#koordynatY}
     */
    @Override
    public int getKoordynatY() {
        return koordynatY;
    }

    /**
     * zwraca czy obiekt wykonal ruch w tej turze
     * @return {@link Kon#wykonalRuch}
     */
    @Override
    public boolean isWykonalRuch() {
        return wykonalRuch;
    }

    /**
     * ustawia nowa wartosc pola {@link Kon#wykonalRuch}
     * @param wykonalRuch nowa wartosc pola {@link Kon#wykonalRuch}
     */
    @Override
    public void setWykonalRuch(boolean wykonalRuch) {
        this.wykonalRuch = wykonalRuch;
    }
}
