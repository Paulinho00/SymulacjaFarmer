package zwierzeta;

import glownyUczestnikSymulacji.Gracz;
import miejsceSymulacji.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KonTest {
    Kostka kostka = new Kostka(1,1,1,1,1);
    Plansza plansza = new Plansza(4,4,kostka);
    Gracz gracz = new Gracz(2,2,1,1,1,1,1,plansza);
    Kon kon = new Kon(0,0,plansza);

    @Test
    void RuchNaWolnePole(){
        int stareX = kon.getKoordynatX();
        int stareY = kon.getKoordynatY();
        kon.ruch();
        int nowyX = kon.getKoordynatX();
        int nowyY = kon.getKoordynatY();
        assertTrue(nowyX != stareX || nowyY != stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertFalse(plansza.czyZajete(stareX, stareY));
        assertEquals(plansza.ktoJest(nowyX, nowyY), "Kon");
        assertTrue(kon.isWykonalRuch());
    }

    @Test
    void BrakRuchuNaPoleZLisemLubWilkiem(){
        Wilk wilk = new Wilk(0,1,plansza,gracz.getHandler());
        Lis lis = new Lis(1,0,plansza,gracz.getHandler());
        int stareX = kon.getKoordynatX();
        int stareY = kon.getKoordynatY();
        kon.ruch();
        int nowyX = kon.getKoordynatX();
        int nowyY = kon.getKoordynatY();
        assertTrue(nowyX == stareX && nowyY == stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertEquals(plansza.ktoJest(nowyX, nowyY), "Kon");
    }

    @Test
    void BrakRuchuNaPoleZGraczLubZwierzem(){
        plansza.przemieszczenie(2,2,0,1);
        new Kon(1,0,plansza);
        int stareX = kon.getKoordynatX();
        int stareY = kon.getKoordynatY();
        kon.ruch();
        int nowyX = kon.getKoordynatX();
        int nowyY = kon.getKoordynatY();
        assertTrue(nowyX == stareX && nowyY == stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertEquals(plansza.ktoJest(nowyX, nowyY), "Kon");
    }




}