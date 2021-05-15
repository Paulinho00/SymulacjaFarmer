package zwierzeta;

import glownyUczestnikSymulacji.Gracz;
import miejsceSymulacji.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwiniaTest {
    Kostka kostka = new Kostka(1,1,1,1,1);
    Plansza plansza = new Plansza(4,4,kostka);
    Gracz gracz = new Gracz(2,2,1,1,1,1,1,plansza);
    Swinia swinia = new Swinia(0,0,plansza);

    @Test
    void RuchNaWolnePole(){
        int stareX = swinia.getKoordynatX();
        int stareY = swinia.getKoordynatY();
        swinia.ruch();
        int nowyX = swinia.getKoordynatX();
        int nowyY = swinia.getKoordynatY();
        assertTrue(nowyX != stareX || nowyY != stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertFalse(plansza.czyZajete(stareX, stareY));
        assertEquals(plansza.ktoJest(nowyX, nowyY), "Swinia");
        assertTrue(swinia.isWykonalRuch());
    }

    @Test
    void BrakRuchuNaPoleZLisemLubWilkiem(){
        Wilk wilk = new Wilk(0,1,plansza,gracz.getHandler());
        Lis lis = new Lis(1,0,plansza,gracz.getHandler());
        int stareX = swinia.getKoordynatX();
        int stareY = swinia.getKoordynatY();
        swinia.ruch();
        int nowyX = swinia.getKoordynatX();
        int nowyY = swinia.getKoordynatY();
        assertTrue(nowyX == stareX && nowyY == stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertEquals(plansza.ktoJest(nowyX, nowyY), "Swinia");
    }

    @Test
    void BrakRuchuNaPoleZGraczLubZwierzem(){
        plansza.przemieszczenie(2,2,0,1);
        new Swinia(1,0,plansza);
        int stareX = swinia.getKoordynatX();
        int stareY = swinia.getKoordynatY();
        swinia.ruch();
        int nowyX = swinia.getKoordynatX();
        int nowyY = swinia.getKoordynatY();
        assertTrue(nowyX == stareX && nowyY == stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertEquals(plansza.ktoJest(nowyX, nowyY), "Swinia");
    }


}