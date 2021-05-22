package zwierzeta;

import miejsceSymulacji.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class KrolikTest {
    Plansza plansza = null;
    Krolik krolik = null;
    @BeforeEach
    void init(){
        int[] kordX = new int[1];
        kordX[0] = 5;
        int[] kordY = new int[1];
        kordY[0] = 5;
        Kostka kostka = new Kostka(1,1,1,1,1);
        plansza = new Plansza(7,7,kordX, kordY,kostka);
        krolik = new Krolik(0,0,plansza);
    }

    @Test
    void RuchNaWolnePole(){
        plansza.setPola(0,1,null);
        plansza.setPola(1,0,null);
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
        new Wilk(0,1,plansza);
        new Lis(1,0,plansza);
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
        plansza.przemieszczenie(5,5,0,1);
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