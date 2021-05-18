package glownyUczestnikSymulacji;

import org.junit.jupiter.api.Assertions;
import miejsceSymulacji.*;
import zwierzeta.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GraczTest {

    Kostka kostka = new Kostka(5,4,3,2,1);
    Plansza plansza = new Plansza(7, 7, kostka);

    @Test
    void PowinienStworzyc(){
        Gracz gracz = new Gracz(2, 3, 1, 1, 0, 2, 0, plansza);
    }

    @Test
    void PowinienBycWyjatek(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Gracz(3,3,144,0,0,0,0,plansza));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Gracz(3,3,0,23,0,0,0,plansza));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Gracz(3,3,0,0,12,0,0,plansza));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Gracz(3,3,0,0,0,5,0,plansza));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Gracz(3,3,0,0,12,0,3,plansza));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Gracz(0,-1,0,0,0,0,0,plansza));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Gracz(4,0,0,0,12,0,0,plansza));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Gracz(-1,4,0,0,12,0,0,plansza));
    }

    @Test
    void RuchWolnePole(){
        Gracz gracz = new Gracz(0,0,0,0,0,0,0,plansza);
        int stareX = gracz.getKoordynatX();
        int stareY = gracz.getKoordynatY();
        gracz.ruch();
        int nowyX = gracz.getKoordynatX();
        int nowyY = gracz.getKoordynatY();
        assertTrue(nowyX != stareX || nowyY != stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertEquals(gracz.getIloscRuchow(), 1);
        assertFalse(plansza.czyZajete(stareX, stareY));
        assertEquals(plansza.ktoJest(nowyX, nowyY), "Gracz");
    }

    @Test
    void RuchPoleZKrolikiem(){
        Gracz gracz = new Gracz(0,0,1,0,0,0,0,plansza);
        plansza.setPola(0,1,new Krolik(0,1, plansza));
        plansza.setPola(1,0,new Krolik(1,0, plansza));
        int stareX = gracz.getKoordynatX();
        int stareY = gracz.getKoordynatY();
        gracz.ruch();
        int nowyX = gracz.getKoordynatX();
        int nowyY = gracz.getKoordynatY();
        assertTrue(nowyX != stareX || nowyY != stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertEquals(gracz.getIloscRuchow(), 1);
        assertFalse(plansza.czyZajete(stareX, stareY));
        assertEquals(gracz.getStanKonta().getIloscKrolikow(), 2);
        assertEquals(plansza.ktoJest(nowyX, nowyY), "Gracz");
    }

    @Test
    void RuchPoleZOwca(){
        Gracz gracz = new Gracz(0,0,1,1,0,0,0,plansza);
        plansza.setPola(0,1,new Owca(0,1, plansza));
        plansza.setPola(1,0,new Owca(1,0, plansza));
        int stareX = gracz.getKoordynatX();
        int stareY = gracz.getKoordynatY();
        gracz.ruch();
        int nowyX = gracz.getKoordynatX();
        int nowyY = gracz.getKoordynatY();
        assertTrue(nowyX != stareX || nowyY != stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertEquals(gracz.getIloscRuchow(), 1);
        assertFalse(plansza.czyZajete(stareX, stareY));
        assertEquals(gracz.getStanKonta().getIloscOwiec(), 2);
        assertEquals(plansza.ktoJest(nowyX, nowyY), "Gracz");
    }

    @Test
    void RuchPoleZSwinia(){
        Gracz gracz = new Gracz(0,0,1,1,0,0,0,plansza);
        plansza.setPola(0,1,new Swinia(0,1, plansza));
        plansza.setPola(1,0,new Swinia(1,0, plansza));
        int stareX = gracz.getKoordynatX();
        int stareY = gracz.getKoordynatY();
        gracz.ruch();
        int nowyX = gracz.getKoordynatX();
        int nowyY = gracz.getKoordynatY();
        assertTrue(nowyX != stareX || nowyY != stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertEquals(gracz.getIloscRuchow(), 1);
        assertFalse(plansza.czyZajete(stareX, stareY));
        assertEquals(gracz.getStanKonta().getIloscSwin(), 1);
        assertEquals(plansza.ktoJest(nowyX, nowyY), "Gracz");
    }

    @Test
    void RuchPoleZKrowa(){
        Gracz gracz = new Gracz(0,0,1,1,0,1,0,plansza);
        plansza.setPola(0,1,new Krowa(0,1, plansza));
        plansza.setPola(1,0,new Krowa(1,0, plansza));
        int stareX = gracz.getKoordynatX();
        int stareY = gracz.getKoordynatY();
        gracz.ruch();
        int nowyX = gracz.getKoordynatX();
        int nowyY = gracz.getKoordynatY();
        assertTrue(nowyX != stareX || nowyY != stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertEquals(gracz.getIloscRuchow(), 1);
        assertFalse(plansza.czyZajete(stareX, stareY));
        assertEquals(gracz.getStanKonta().getIloscKrow(), 2);
        assertEquals(plansza.ktoJest(nowyX, nowyY), "Gracz");
    }

    @Test
    void RuchPoleZKoniem(){
        Gracz gracz = new Gracz(0,0,1,1,0,0,0,plansza);
        plansza.setPola(0,1,new Kon(0,1, plansza));
        plansza.setPola(1,0,new Kon(1,0, plansza));
        int stareX = gracz.getKoordynatX();
        int stareY = gracz.getKoordynatY();
        gracz.ruch();
        int nowyX = gracz.getKoordynatX();
        int nowyY = gracz.getKoordynatY();
        assertTrue(nowyX != stareX || nowyY != stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertEquals(gracz.getIloscRuchow(), 1);
        assertFalse(plansza.czyZajete(stareX, stareY));
        assertEquals(gracz.getStanKonta().getIloscKoni(), 1);
        assertEquals(plansza.ktoJest(nowyX, nowyY), "Gracz");
    }

    @Test
    void RuchNaWilka(){
        Gracz gracz = new Gracz(0,0,6,2,1,1,0,plansza);
        plansza.setPola(0,1, new Wilk(0,1,plansza, gracz.getHandler()));
        plansza.setPola(1,0, new Wilk(1,0,plansza, gracz.getHandler()));
        int stareX = gracz.getKoordynatX();
        int stareY = gracz.getKoordynatY();
        gracz.ruch();
        int nowyX = gracz.getKoordynatX();
        int nowyY = gracz.getKoordynatY();
        assertTrue(nowyX == stareX && nowyY == stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertTrue(plansza.czyZajete(stareX, stareY));
        assertTrue(gracz.getStanKonta().getIloscKrolikow() == 0 && gracz.getStanKonta().getIloscOwiec() == 0 && gracz.getStanKonta().getIloscSwin()==0 && gracz.getStanKonta().getIloscKrow()==0 && gracz.getStanKonta().getIloscKoni() == 0);
    }

    @Test
    void RuchNaLisa(){
        Gracz gracz = new Gracz(0,0,6,0,1,1,1,plansza);
        plansza.setPola(0,1, new Lis(0,1,plansza, gracz.getHandler()));
        plansza.setPola(1,0, new Lis(1,0,plansza, gracz.getHandler()));
        int stareX = gracz.getKoordynatX();
        int stareY = gracz.getKoordynatY();
        gracz.ruch();
        int nowyX = gracz.getKoordynatX();
        int nowyY = gracz.getKoordynatY();
        assertTrue(nowyX == stareX && nowyY == stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertTrue(plansza.czyZajete(stareX, stareY));
        assertTrue(gracz.getStanKonta().getIloscKrolikow() == 0 && gracz.getStanKonta().getIloscOwiec() == 0 && gracz.getStanKonta().getIloscSwin()!=0 && gracz.getStanKonta().getIloscKrow()!=0 && gracz.getStanKonta().getIloscKoni() != 0);
    }

    @Test
    void WymianaZwierzat(){
        Gracz gracz = new Gracz(0,0,120,0,0,0,0,plansza);
        gracz.wymiana();
        assertEquals(gracz.getStanKonta().getIloscKrolikow(), 0);
        assertEquals(gracz.getStanKonta().getIloscOwiec(), 0);
        assertEquals(gracz.getStanKonta().getIloscSwin(), 1);
        assertEquals(gracz.getStanKonta().getIloscKrow(), 1);
        assertEquals(gracz.getStanKonta().getIloscKoni(), 1);
    }

    @Test
    void PowinienZmnienicKonto(){
        Gracz gracz = new Gracz(0,0,118,1,0,0,0,plansza);
        gracz.zmnienIloscKonto("Krolik", 0);
        gracz.zmnienIloscKonto("Swinia", 1);
        gracz.zmnienIloscKonto("Krowa", 2);
        gracz.zmnienIloscKonto("Kon", 1);
        assertEquals(gracz.getStanKonta().getIloscKrolikow(), 0);
        assertEquals(gracz.getStanKonta().getIloscOwiec(), 1);
        assertEquals(gracz.getStanKonta().getIloscSwin(), 1);
        assertEquals(gracz.getStanKonta().getIloscKrow(), 2);
        assertEquals(gracz.getStanKonta().getIloscKoni(), 1);

    }

    @Test
    void PowinienBycKoniec(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Gracz(0,0,0,0,0,2,1,plansza));
        ;

    }

    @Test
    void NiePowinienBycKoniec(){
        Gracz gracz = new Gracz(0,0,2,0,0,0,1,plansza);
        assertFalse(gracz.czyKoniec());
    }

}