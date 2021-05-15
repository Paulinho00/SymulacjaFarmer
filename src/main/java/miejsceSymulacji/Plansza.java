package miejsceSymulacji;
import postac.Postac;
import glownyUczestnikSymulacji.Gracz;
import zwierzeta.*;
import java.util.Random;

/**
 *  Przestrzen w ktorej odbywa sie symulacja, przechowuje informacje o zajetosci pol,
 *  polozeniu postaci, uzupelnianiu pol
 */

public class Plansza {
    private int zajetePola;
    private final int rozmiarY;
    private final int rozmiarX;
    private final Postac[][] pola;
    private final Kostka kostka;
    private final int iloscPol;

    /**
     *
     * @param y dlugosc planszy
     * @param x szerokosc planszy
     * @param kostka obiekt kostki uzywany do wylosowania rodzaju zwierzecia przy losowaniu
     * @exception IllegalArgumentException gdy plansza jest za mala lub nie jest kwadratowa
     */
    public Plansza(int x, int y, Kostka kostka){
        if(x <= 6 || y <= 6){
            throw new IllegalArgumentException("Zbyt mała plansza");
        }
        if(x != y){
            throw new IllegalArgumentException("Plansza nie jest kwadratowa");
        }
        rozmiarY = y;
        rozmiarX = x;
        iloscPol = x*y;
        zajetePola = 0;
        this.kostka = kostka;
        pola = new Postac[x][y];

    }

    /**
     * Ustawia wartosc danego pola
     * @param x koordynat X docelowego pola
     * @param y koordynat Y docelowego pola
     * @param postac nowa wartosc(zwierze) pola
     */
    public void setPola(int x, int y,Postac postac){
        pola[x][y] = postac;
        zajetePola++;
    }

    public Postac getPola(int x, int y){
        return pola[x][y];
    }
    /**
     *
     * @return zwraca dlugosc(rozmiar Y) planszy
     */
    public int getRozmiarY() {
        return rozmiarY;}

    /**
     *
     * @return zwraca szerokosc(rozmiar X) planszy
     */
    public int getRozmiarX() {
        return rozmiarX;}

    /**
     * przemieszcza obiekt na danym polu na inne pole
     * @param stareX obecny koordynat X docelowego obiektu
     * @param stareY obecny koordynat Y docelowego obiektu
     * @param noweX przyszly koordynat X docelowego obiektu
     * @param noweY przyszly koordynat Y docelowego obiektu
     */
    public void przemieszczenie(int stareX, int stareY, int noweX, int noweY){
        pola[noweX][noweY]= pola[stareX][stareY];
        pola[stareX][stareY] = null;
    }

    /**
     * sprawdza czy dane pole jest zajete
     * @param x koordynat X docelowego pola
     * @param y koordynat Y docelowego pola
     * @return true jesli zajete
     */
    public boolean czyZajete(int x, int y){
        if(pola[x][y] != null) return true;
        else return false;
    }

    /**
     *
     * @return ilosc zajetych pol
     */
    public int getZajetePola() {
        return zajetePola;
    }

    /**
     * uzupelnia tablice gdy jest za malo zajetych pol
     */
    public void uzupelnienie(){
        Random losowanie = new Random();
        int x;
        int y;
        while(getZajetePola() < (iloscPol/2)){
            do{
                x = losowanie.nextInt(getRozmiarX());
                y = losowanie.nextInt(getRozmiarY());
            }while(czyZajete(x,y));
           String kto = kostka.losowanie();
           if(kto.equals("Krolik")){
               new Krolik(x, y,this);
           }
           if(kto.equals("Owca")){
               new Owca(x,y,this);
           }
           if(kto.equals("Swinia")){
               new Swinia(x,y,this);
           }
           if(kto.equals("Krowa")){
              new Krowa(x,y,this);
           }
           if(kto.equals("Kon")){
               new Kon(x,y, this);
           }
        }

    }

    /**
     * zwraca typ obiektu znajdujacego sie na danym polu
     * @param x koordynat X docelowego pola
     * @param y koordynat Y docelowego pola
     * @return typ obiektu na danym polu
     */
    public String ktoJest(int x, int y){
       if (pola[x][y].getClass() == Gracz.class) return "Gracz";
       if (pola[x][y].getClass() == Wilk.class ) return "Wilk";
       if (pola[x][y].getClass() == Lis.class) return "Lis";
       if (pola[x][y].getClass() == Krolik.class) return "Krolik";
       if (pola[x][y].getClass() == Owca.class) return "Owca";
       if (pola[x][y].getClass() == Swinia.class) return "Swinia";
       if (pola[x][y].getClass() == Krowa.class) return "Krowa";
       if (pola[x][y].getClass() == Kon.class) return "Kon";
        return "Puste";
    }

    /**
     * ustawia docelowe pole jako puste
     * @param x koordynat X docelowego pola
     * @param y koordynat Y docelowego pola
     */
    public void wyczyscPole(int x, int y){
        pola[x][y] = null;
        zajetePola--;
    }

    /**
     * zarzadza ruchem {@link zwierzeta.Kon}, {@link zwierzeta.Krowa},
     * {@link zwierzeta.Swinia}, {@link zwierzeta.Owca}, {@link zwierzeta.Krolik},
     * na planszy i pilnuje aby żaden nie obiekt nie wykonał podwójnego ruchu
     */
    public void RuchyNaPlanszy(){
        String kto;
        Krolik krolik;
        Owca owca;
        Swinia swinia;
        Krowa krowa;
        Kon kon;
        for(int i = 0; i < rozmiarX; i++){
            for(int j = 0; j < rozmiarY; j++) {
                if (czyZajete(i, j)) {
                    kto = ktoJest(i, j);
                    if (kto.equals("Krolik")) {
                        krolik = (Krolik) getPola(i, j);
                        if (!krolik.isWykonalRuch()) {
                            krolik.ruch();
                        }
                    }
                    if (kto.equals("Owca")) {
                        owca = (Owca) getPola(i, j);
                        if (!owca.isWykonalRuch()) {
                            owca.ruch();
                        }

                    }
                    if (kto.equals("Swinia")) {
                        swinia = (Swinia) getPola(i, j);
                        if (!swinia.isWykonalRuch()) {
                            swinia.ruch();
                        }

                    }
                    if (kto.equals("Krowa")) {
                        krowa = (Krowa) getPola(i, j);
                        if (!krowa.isWykonalRuch()) {
                            krowa.ruch();
                        }
                    }
                    if (kto.equals("Kon")) {
                        kon = (Kon) getPola(i, j);
                        if (!kon.isWykonalRuch()) {
                            kon.ruch();
                        }
                    }
                }
            }
        }
    }

    /**
     * resetuje pole sprawdzajace czy dany obiekt wykonal ruch w tej turze, dla każdego zwierzęcia
     */
    public void ResetZwierzat(){
        String kto;
        Krolik krolik;
        Owca owca;
        Swinia swinia;
        Krowa krowa;
        Kon kon;
        for(int i = 0; i < rozmiarX; i++){
            for(int j = 0; j < rozmiarY; j++) {
                if (czyZajete(i, j)) {
                    kto = ktoJest(i, j);
                    if (kto.equals("Krolik")) {
                        krolik = (Krolik) getPola(i, j);
                        krolik.setWykonalRuch(false);
                    }
                    if (kto.equals("Owca")) {
                        owca = (Owca) getPola(i, j);
                        owca.setWykonalRuch(false);

                    }
                    if (kto.equals("Swinia")) {
                        swinia = (Swinia) getPola(i, j);
                        swinia.setWykonalRuch(false);

                    }
                    if (kto.equals("Krowa")) {
                        krowa = (Krowa) getPola(i, j);
                        krowa.setWykonalRuch(false);
                    }
                    if (kto.equals("Kon")) {
                        kon = (Kon) getPola(i, j);
                        kon.setWykonalRuch(false);
                    }
                }
            }

        }
    }

}
