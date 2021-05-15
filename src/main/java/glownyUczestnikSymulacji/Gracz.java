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

    /**
     *
     * @param x poczatkowy koordynat X gracza na planszy
     * @param y poczatkowy koordynat Y gracza na planszy
     * @param kroliki startowa liczba krolikow na koncie
     * @param owce startowa liczba owiec na koncie
     * @param swinie startowa liczba swin na koncie
     * @param krowy startowa liczba krow na koncie
     * @param konie startowa liczba koni na koncie
     * @param plansza referencja do planszy na ktorej znajduje sie gracz
     * @exception IllegalArgumentException gdy podane koordynaty sa bledne
     */
    public Gracz(int x, int y, int kroliki, int owce, int swinie, int krowy, int konie, Plansza plansza) {
        if(x < 0 ||  y < 0 || y >= plansza.getRozmiarY() || x >= plansza.getRozmiarX()){
            throw new IllegalArgumentException("Błędne koordynaty");
        }
        koordynatX = x;
        koordynatY = y;
        iloscRuchow = 0;
        stanKonta = new ListaZwierzat(kroliki, owce, swinie, krowy, konie);
        this.plansza = plansza;
        handler = new WykonujacyInterakcje(this);
        plansza.setPola(x,y,this);
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
        }
        else {
            String kto = plansza.ktoJest(koordynatX + ruchX, koordynatY + ruchY);
            if (kto.equals("Krolik") || kto.equals("Owca") || kto.equals("Swinia") || kto.equals("Krowa") || kto.equals("Kon")) {
                zebranieZwierzecia(kto, ruchX, ruchY);
                plansza.przemieszczenie(koordynatX, koordynatY, koordynatX + ruchX, koordynatY + ruchY);
                koordynatX += ruchX;
                koordynatY += ruchY;
                iloscRuchow++;
            }
            if (kto.equals("Wilk")) handler.wyczyszczenieKontaGracza();
            if (kto.equals("Lis")) handler.usuniecieKrolikowKonto();
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
     *
     * @return zwraca aktualny koordynat X gracza na planszy
     */
    @Override
    public int getKoordynatX() {
        return koordynatX;
    }

    /**
     *
     * @return zwraca aktualny koordynat Y gracza na planszy
     */
    @Override
    public int getKoordynatY() {
        return koordynatY;
    }

    /**
     *
     * @return zwraca ilosc wykonanych ruchow wykonanych do pory wywolania, od poczatku symulacji
     */
    public Integer getIloscRuchow() {
        return iloscRuchow;}

    /**
     *
     * @return zwraca referencje do obiektu wykonujacego interakcje
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

        public int czyKoniec(){
            int krowy = (stanKonta.getIloscKoni()*Przeliczniki.getKrowyZaKonie())+ stanKonta.getIloscKrow();
            int swinie = (krowy*Przeliczniki.getSwinieZaKrowy())+ stanKonta.getIloscSwin();
            int owce = (swinie*Przeliczniki.getOwceZaSwinie())+ stanKonta.getIloscOwiec();
            int kroliki = (owce*Przeliczniki.getKrolikiZaOwce())+ stanKonta.getIloscKrolikow();
            return kroliki;

        }
    /**
     *
     * @return zwraca referencje do obiektu stanu konta
     */
    public ListaZwierzat getStanKonta() {
        return stanKonta;
    }
}