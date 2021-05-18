package zwierzeta;

import glownyUczestnikSymulacji.Gracz;
import miejsceSymulacji.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KrolikTest {
    Kostka kostka = new Kostka(1,1,1,1,1);
    Plansza plansza = new Plansza(7,7,kostka);
    Gracz gracz = new Gracz(2,2,1,0,0,1,1,plansza);
    Krolik krolik = new Krolik(0,0,plansza);

    @Test
    void RuchNaWolnePole(){
        int stareX = krolik.getKoordynatX();
        int stareY = krolik.getKoordynatY();
        krolik.ruch();
        int nowyX = krolik.getKoordynatX();
        int nowyY = krolik.getKoordynatY();
        assertTrue(nowyX != stareX || nowyY != stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertFalse(plansza.czyZajete(stareX, stareY));
        assertEquals(plansza.ktoJest(nowyX, nowyY), "Krolik");
    }

    @Test
    void BrakRuchuNaPoleZLisemLubWilkiem(){
        Wilk wilk = new Wilk(0,1,plansza,gracz.getHandler());
        Lis lis = new Lis(1,0,plansza,gracz.getHandler());
        int stareX = krolik.getKoordynatX();
        int stareY = krolik.getKoordynatY();
        krolik.ruch();
        int nowyX = krolik.getKoordynatX();
        int nowyY = krolik.getKoordynatY();
        assertTrue(nowyX == stareX && nowyY == stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertEquals(plansza.ktoJest(nowyX, nowyY), "Krolik");
        assertTrue(krolik.isWykonalRuch());
    }

    @Test
    void BrakRuchuNaPoleZGraczLubZwierzem(){
        plansza.przemieszczenie(2,2,0,1);
        new Krolik(1,0,plansza);
        int stareX = krolik.getKoordynatX();
        int stareY = krolik.getKoordynatY();
        krolik.ruch();
        int nowyX = krolik.getKoordynatX();
        int nowyY = krolik.getKoordynatY();
        assertTrue(nowyX == stareX && nowyY == stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertEquals(plansza.ktoJest(nowyX, nowyY), "Krolik");
    }




}