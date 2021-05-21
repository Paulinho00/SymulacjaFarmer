package zwierzeta;

import glownyUczestnikSymulacji.Gracz;
import miejsceSymulacji.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class KonTest {
    Plansza plansza = null;
    Kon kon = null;
    @BeforeEach
    void init(){
        int[] kordX = new int[1];
        kordX[0] = 5;
        int[] kordY = new int[1];
        kordY[0] = 5;
        Kostka kostka = new Kostka(1,1,1,1,1);
        plansza = new Plansza(7,7,kordX, kordY,kostka);
        kon = new Kon(0,0,plansza);
    }

    @Test
    void RuchNaWolnePole(){
        plansza.setPola(0,1,null);
        plansza.setPola(1,0,null);
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
        new Wilk(0,1,plansza);
        new Lis(1,0,plansza);
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
        plansza.przemieszczenie(5,5,0,1);
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