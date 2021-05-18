package zwierzeta;

import glownyUczestnikSymulacji.Gracz;
import miejsceSymulacji.Kostka;
import miejsceSymulacji.Plansza;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LisTest {
    Kostka kostka = new Kostka(1,1,1,1,1);
    Plansza plansza = new Plansza(7,7,kostka);
    Gracz gracz = new Gracz(2,2,1,0,0,1,1,plansza);
    Lis lis = new Lis(0,0,plansza,gracz.getHandler());

    @Test
    void RuchNaWolnePole(){
        int stareX = lis.getKoordynatX();
        int stareY = lis.getKoordynatY();
        lis.ruch();
        int nowyX = lis.getKoordynatX();
        int nowyY = lis.getKoordynatY();
        assertTrue(nowyX != stareX || nowyY != stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertFalse(plansza.czyZajete(stareX, stareY));
        assertEquals(plansza.ktoJest(nowyX, nowyY), "Lis");
    }

    @Test
    void RuchNaPoleZKrolikiem(){
        plansza.setPola(0,1,new Krolik(0,1,plansza));
        plansza.setPola(1,0, new Krolik(1,0,plansza));
        int stareX = lis.getKoordynatX();
        int stareY = lis.getKoordynatY();
        lis.ruch();
        int nowyX = lis.getKoordynatX();
        int nowyY = lis.getKoordynatY();
        assertTrue(nowyX != stareX || nowyY != stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertFalse(plansza.czyZajete(stareX, stareY));
        assertEquals(plansza.ktoJest(nowyX, nowyY), "Lis");
    }

    @Test
    void BrakRuchuNaPoleZeZwierze(){
        plansza.setPola(0,1,new Owca(0,1,plansza));
        plansza.setPola(1,0, new Owca(1,0,plansza));
        int stareX = lis.getKoordynatX();
        int stareY = lis.getKoordynatY();
        lis.ruch();
        int nowyX = lis.getKoordynatX();
        int nowyY = lis.getKoordynatY();
        assertTrue(nowyX == stareX && nowyY == stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertEquals(plansza.ktoJest(nowyX, nowyY), "Lis");
    }

    @Test
    void BrakRuchuNaPoleZWilkiem(){
        plansza.setPola(0,1,new Wilk(0,1,plansza,gracz.getHandler()));
        plansza.setPola(1,0, new Wilk(1,0,plansza,gracz.getHandler()));
        int stareX = lis.getKoordynatX();
        int stareY = lis.getKoordynatY();
        lis.ruch();
        int nowyX = lis.getKoordynatX();
        int nowyY = lis.getKoordynatY();
        assertTrue(nowyX == stareX && nowyY == stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertEquals(plansza.ktoJest(nowyX, nowyY), "Lis");
    }
}
