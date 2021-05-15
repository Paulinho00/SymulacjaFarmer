package zwierzeta;

import glownyUczestnikSymulacji.Gracz;
import miejsceSymulacji.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OwcaTest {
    Kostka kostka = new Kostka(1,1,1,1,1);
    Plansza plansza = new Plansza(4,4,kostka);
    Gracz gracz = new Gracz(2,2,1,1,1,1,1,plansza);
    Owca owca = new Owca(0,0,plansza);

    @Test
    void RuchNaWolnePole(){
        int stareX = owca.getKoordynatX();
        int stareY = owca.getKoordynatY();
        owca.ruch();
        int nowyX = owca.getKoordynatX();
        int nowyY = owca.getKoordynatY();
        assertTrue(nowyX != stareX || nowyY != stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertFalse(plansza.czyZajete(stareX, stareY));
        assertEquals(plansza.ktoJest(nowyX, nowyY), "Owca");
        assertTrue(owca.isWykonalRuch());
    }

    @Test
    void BrakRuchuNaPoleZLisemLubWilkiem(){
        Wilk wilk = new Wilk(0,1,plansza,gracz.getHandler());
        Lis lis = new Lis(1,0,plansza,gracz.getHandler());
        int stareX = owca.getKoordynatX();
        int stareY = owca.getKoordynatY();
        owca.ruch();
        int nowyX = owca.getKoordynatX();
        int nowyY = owca.getKoordynatY();
        assertTrue(nowyX == stareX && nowyY == stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertEquals(plansza.ktoJest(nowyX, nowyY), "Owca");
    }

    @Test
    void BrakRuchuNaPoleZGraczLubZwierzem(){
        plansza.przemieszczenie(2,2,0,1);
        new Owca(1,0,plansza);
        int stareX = owca.getKoordynatX();
        int stareY = owca.getKoordynatY();
        owca.ruch();
        int nowyX = owca.getKoordynatX();
        int nowyY = owca.getKoordynatY();
        assertTrue(nowyX == stareX && nowyY == stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertEquals(plansza.ktoJest(nowyX, nowyY), "Owca");
    }



}