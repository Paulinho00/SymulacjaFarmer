package glownyUczestnikSymulacji;
import miejsceSymulacji.Plansza;
import java.util.Random;
import wykonujacyInterakcje.WykonujacyInterakcje;

/**
 * Glowny uczestnik symulacji, przechowuje dane o polozeniu na planszy,
 * ilosci wykonanych ruchow w calej symulacji
 * oraz o liczbie zebranych zwierzat
 */
public class Gracz implements postac.Postac {
    private int koordynatX;
    private int koordynatY;
    private Integer iloscRuchow;
    private final ListaZwierzat stanKonta;
    private final Plansza plansza;
    private final WykonujacyInterakcje handler;
    private int ktory;
    private boolean wykonalRuch;

    /**
     * tworzy obiekt gracza
     * @param x poczatkowy koordynat X gracza na planszy
     * @param y poczatkowy koordynat Y gracza na planszy
     * @param plansza referencja do planszy na ktorej znajduje sie gracz
     * @exception IllegalArgumentException gdy podane koordynaty sa bledne lub dane miejsce jest juz zajete
     */
    public Gracz(int x, int y, Plansza plansza, int ktory) {
        if(x < 0 ||  y < 0 || y >= plansza.getRozmiarY() || x >= plansza.getRozmiarX() || plansza.czyZajete(x,y)){
            throw new IllegalArgumentException("Bledne koordynaty");
        }
        koordynatX = x;
        koordynatY = y;
        iloscRuchow = 0;
        stanKonta = new ListaZwierzat(0,0,0,0,0);
        this.plansza = plansza;
        handler = new WykonujacyInterakcje(this);
        plansza.setPola(x,y,this);
        this.ktory = ktory;
        wykonalRuch = false;
    }

    /**
     * wykonuje ruch gracza, zmieniajac swoje koordynaty i przesylajac zmiany do planszy
     */
    @Override
    public void ruch(){
        int ruchX = 0;
        int ruchY = 0;
        Random losowanie = new Random();
        char KtoryKoordynat = (char) (losowanie.nextInt(2)+120);
        switch(KtoryKoordynat){
            case 'x': while(ruchX == 0 || koordynatX+ruchX >= plansza.getRozmiarX() || koordynatX+ruchX < 0) ruchX = losowanie.nextInt(3)-1; break;
            case 'y': while(ruchY == 0 || koordynatY+ruchY >= plansza.getRozmiarY() || koordynatY+ruchY < 0) ruchY = losowanie.nextInt(3)-1; break;
        }
        if(!plansza.czyZajete(koordynatX+ruchX, koordynatY+ruchY)){
            plansza.przemieszczenie(koordynatX, koordynatY, koordynatX+ruchX, koordynatY+ruchY);
            koordynatX += ruchX;
            koordynatY += ruchY;
            iloscRuchow++;
            wykonalRuch = true;
        }
        else {
            String kto = plansza.ktoJest(koordynatX + ruchX, koordynatY + ruchY);
            if (kto.equals("Krolik") || kto.equals("Owca") || kto.equals("Swinia") || kto.equals("Krowa") || kto.equals("Kon")) {
                zebranieZwierzecia(kto, ruchX, ruchY);
                plansza.przemieszczenie(koordynatX, koordynatY, koordynatX + ruchX, koordynatY + ruchY);
                koordynatX += ruchX;
                koordynatY += ruchY;
                iloscRuchow++;
                wykonalRuch = true;
            }
            if (kto.equals("Gracz")){
                Gracz gracz1 = (Gracz) plansza.getPola(koordynatX+ruchX, koordynatY+ruchY);
                gracz1.getHandler().spotkanieZGraczem(this);
                wykonalRuch = true;
            }
            if (kto.equals("Wilk")){
                handler.wyczyszczenieKontaGracza();
                wykonalRuch = true;
            }
            if (kto.equals("Lis")) {
                handler.usuniecieKrolikowKonto();
                wykonalRuch = true;
            }
        }

    }

    /**
     *  wykonuje automatyczne wymiany zwierzat, zmniejszajac liczbe zwierzat o nizszej wartosci na koncie na te o wyzszej
     */
    public void wymiana(){
        int ile;

        if(stanKonta.getIloscKrolikow() >= Przeliczniki.getKrolikiZaOwce() ){
            ile = stanKonta.getIloscKrolikow()/Przeliczniki.getKrolikiZaOwce();
            stanKonta.setIloscOwiec(stanKonta.getIloscOwiec()+ile);
            stanKonta.setIloscKrolikow(stanKonta.getIloscKrolikow()-(ile*Przeliczniki.getKrolikiZaOwce()));
        }

        if(stanKonta.getIloscOwiec() >= Przeliczniki.getOwceZaSwinie()){
            ile = stanKonta.getIloscOwiec()/Przeliczniki.getOwceZaSwinie();
            stanKonta.setIloscSwin(stanKonta.getIloscSwin()+ile);
            stanKonta.setIloscOwiec(stanKonta.getIloscOwiec()-(ile*Przeliczniki.getOwceZaSwinie()));
        }

        if(stanKonta.getIloscSwin() >= Przeliczniki.getSwinieZaKrowy()){
            ile = stanKonta.getIloscSwin()/Przeliczniki.getSwinieZaKrowy();
            stanKonta.setIloscKrow(stanKonta.getIloscKrow()+ile);
            stanKonta.setIloscSwin(stanKonta.getIloscSwin()-(ile*Przeliczniki.getSwinieZaKrowy()));
        }

        if(stanKonta.getIloscKrow() >= Przeliczniki.getKrowyZaKonie()){
            ile = stanKonta.getIloscKrow()/Przeliczniki.getKrowyZaKonie();
            stanKonta.setIloscKoni(stanKonta.getIloscKoni()+ile);
            stanKonta.setIloscKrow(stanKonta.getIloscKrow()-(ile*Przeliczniki.getKrowyZaKonie()));

        }

    }

    /**
     * zbiera zwierze z pola, na ktore rusza sie gracz, usuwajac je z planszy i powiekszajac stan konta gracza
     * @param kto rodzaj zwierzecia na danym polu
     * @param ruchX wartosc ruchu wzdluz osi X w danej turze
     * @param ruchY wartosc ruchu wzdluz osi Y w danej turze
     */
    private void zebranieZwierzecia(String kto, int ruchX, int ruchY){
        plansza.wyczyscPole(getKoordynatX() + ruchX, getKoordynatY() + ruchY);
        if (kto.equals("Krolik")) stanKonta.setIloscKrolikow(stanKonta.getIloscKrolikow() + 1);
        if (kto.equals("Owca")) stanKonta.setIloscOwiec(stanKonta.getIloscOwiec() + 1);
        if (kto.equals("Swinia")) stanKonta.setIloscSwin(stanKonta.getIloscSwin() + 1);
        if (kto.equals("Krowa")) stanKonta.setIloscKrow(stanKonta.getIloscKrow() + 1);
        if (kto.equals("Kon")) stanKonta.setIloscKoni(stanKonta.getIloscKoni() + 1);
    }

    /**
     * zwraca koordynat X gracza
     * @return {@link Gracz#koordynatX}
     */
    @Override
    public int getKoordynatX() {
        return koordynatX;
    }

    /**
     * zwraca koordynat Y gracza
     * @return {@link Gracz#koordynatY}
     */
    @Override
    public int getKoordynatY() {
        return koordynatY;
    }

    /**
     * zwraca ilosc wykonanych ruchow przez gracza
     * @return {@link Gracz#iloscRuchow}
     */
    public Integer getIloscRuchow() {
        return iloscRuchow;}

    /**
     * zwraca referencje do przypisanego obiektu wykonujacego interakcje miedzy graczem a drapieznikami
     * @return {@link Gracz#handler}
     */
    public WykonujacyInterakcje getHandler() {
        return handler;
    }

    /**
     * zmienia liczbe danego zwierzecia na koncie
     * @param zwierze rodzaj zwierzecia do ktorego beda zastosowane zmiany na koncie
     * @param doIlu nowa ilosc danego zwierzecia na koncie
     */
        public void zmnienIloscKonto(String zwierze, int doIlu) {
            if(zwierze.equals("Krolik")) stanKonta.setIloscKrolikow(doIlu);
            if(zwierze.equals("Owca")) stanKonta.setIloscOwiec(doIlu);
            if(zwierze.equals("Swinia")) stanKonta.setIloscSwin(doIlu);
            if(zwierze.equals("Krowa")) stanKonta.setIloscKrow(doIlu);
            if(zwierze.equals("Kon")) stanKonta.setIloscKoni(doIlu);
        }

    /**
     * zwraca referencje do obiektu stanu konta gracza
     * @return {@link Gracz#stanKonta}
     */
    public ListaZwierzat getStanKonta() {
        return stanKonta;
    }

    /**
     * oblicza i zwraca wartosc konta gracza
     * @return wartosc konta gracza wyrazona w krolikach
     */
    public int wartoscKonta(){
        int krowy = (stanKonta.getIloscKoni()*Przeliczniki.getKrowyZaKonie())+ stanKonta.getIloscKrow();
        int swinie = (krowy*Przeliczniki.getSwinieZaKrowy())+ stanKonta.getIloscSwin();
        int owce = (swinie*Przeliczniki.getOwceZaSwinie())+ stanKonta.getIloscOwiec();
        int kroliki = ((owce*Przeliczniki.getKrolikiZaOwce())+ stanKonta.getIloscKrolikow());
        return kroliki;
    }

    /**
     * zwraca czy dany gracz wykonal ruch w tej turze
     * @return {@link Gracz#wykonalRuch}
     */
    public boolean isWykonalRuch() {
        return wykonalRuch;
    }

    /**
     * ustawia nowa wartosc pola {@link Gracz#wykonalRuch}
     * @param wykonalRuch nowa wartosc pola {@link Gracz#wykonalRuch}
     */
    public void setWykonalRuch(boolean wykonalRuch) {
        this.wykonalRuch = wykonalRuch;
    }

    /**
     * zwraca numer identyfikujacy gracza
     * @return {@link Gracz#ktory}
     */
    public int getKtory() {
        return ktory;
    }
}
