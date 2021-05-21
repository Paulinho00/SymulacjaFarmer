package zwierzeta;

import miejsceSymulacji.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SwiniaTest {
    Plansza plansza = null;
    Swinia swinia = null;
    @BeforeEach
    void init(){
        int[] kordX = new int[1];
        kordX[0] = 5;
        int[] kordY = new int[1];
        kordY[0] = 5;
        Kostka kostka = new Kostka(1,1,1,1,1);
        plansza = new Plansza(7,7,kordX, kordY,kostka);
        swinia = new Swinia(0,0,plansza);

    }

    @Test
    void RuchNaWolnePole(){
        plansza.setPola(0,1,null);
        plansza.setPola(1,0,null);
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
        new Wilk(0,1,plansza);
        new Lis(1,0,plansza);
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
        plansza.przemieszczenie(5,5,0,1);
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