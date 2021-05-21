package zwierzeta;

import  glownyUczestnikSymulacji.Gracz;
import miejsceSymulacji.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class KrowaTest {
    Plansza plansza = null;
    Krowa krowa = null;
    @BeforeEach
    void init(){
        int[] kordX = new int[1];
        kordX[0] = 5;
        int[] kordY = new int[1];
        kordY[0] = 5;
        Kostka kostka = new Kostka(1,1,1,1,1);
        plansza = new Plansza(7,7,kordX, kordY,kostka);
        krowa = new Krowa(0,0,plansza);
    }
    @Test
    void RuchNaWolnePole(){
        plansza.setPola(0,1,null);
        plansza.setPola(1,0,null);
        int stareX = krowa.getKoordynatX();
        int stareY = krowa.getKoordynatY();
        krowa.ruch();
        int nowyX = krowa.getKoordynatX();
        int nowyY = krowa.getKoordynatY();
        assertTrue(nowyX != stareX || nowyY != stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertFalse(plansza.czyZajete(stareX, stareY));
        assertEquals(plansza.ktoJest(nowyX, nowyY), "Krowa");
        assertTrue(krowa.isWykonalRuch());
    }

    @Test
    void BrakRuchuNaPoleZLisemLubWilkiem(){
        Wilk wilk = new Wilk(0,1,plansza);
        Lis lis = new Lis(1,0,plansza);
        int stareX = krowa.getKoordynatX();
        int stareY = krowa.getKoordynatY();
        krowa.ruch();
        int nowyX = krowa.getKoordynatX();
        int nowyY = krowa.getKoordynatY();
        assertTrue(nowyX == stareX && nowyY == stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertEquals(plansza.ktoJest(nowyX, nowyY), "Krowa");
    }

    @Test
    void BrakRuchuNaPoleZGraczLubZwierzem(){
        plansza.przemieszczenie(5,5,0,1);
        new Krowa(1,0,plansza);
        int stareX = krowa.getKoordynatX();
        int stareY = krowa.getKoordynatY();
        krowa.ruch();
        int nowyX = krowa.getKoordynatX();
        int nowyY = krowa.getKoordynatY();
        assertTrue(nowyX == stareX && nowyY == stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertEquals(plansza.ktoJest(nowyX, nowyY), "Krowa");
    }



}