package postac;

public interface Postac {
    /**
     * metoda umozliwia wykonanie ruchu na planszy
     */
    void ruch();

    /**
     * metoda zwracajaca koordynatX obiektu
     * @return koordynatX
     */
    int getKoordynatX();

    /**
     * metoda zwracajaca koordynatY obiektu
     * @return koordynatY
     */
    int getKoordynatY();

    /**
     * metoda zwracajaca informacje czy dany obiekt wykonal ruch, potrzebna do komunikacji z plansza
     * @return true jesli wykonal
     */
    boolean isWykonalRuch();

    /**
     * metoda ustawiajaca nowa wartosc pola przechowujacego informacje o wykonaniu ruchu, potrzebna do odpowiedniego
     * zarzadzania ruchem przez plansze
     * @param WykonalRuch nowa wartosc pola
     */
    void setWykonalRuch(boolean WykonalRuch);
}
