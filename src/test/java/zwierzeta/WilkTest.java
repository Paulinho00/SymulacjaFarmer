package zwierzeta;

import glownyUczestnikSymulacji.Gracz;
import miejsceSymulacji.Kostka;
import miejsceSymulacji.Plansza;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WilkTest {
    Plansza plansza = null;
    Wilk wilk = null;
    @BeforeEach
    void init() {
        int[] kordX = new int[1];
        kordX[0] = 5;
        int[] kordY = new int[1];
        kordY[0] = 5;
        Kostka kostka = new Kostka(1, 1, 1, 1, 1);
        plansza = new Plansza(8, 8, kordX, kordY, kostka);
        wilk = new Wilk(0, 0, plansza);
    }

    @Test
    void RuchNaWolnePole(){
        plansza.setPola(0,1,null);
        plansza.setPola(1,0,null);
        int stareX = wilk.getKoordynatX();
        int stareY = wilk.getKoordynatY();
        wilk.ruch();
        int nowyX = wilk.getKoordynatX();
        int nowyY = wilk.getKoordynatY();
        assertTrue(nowyX != stareX || nowyY != stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertFalse(plansza.czyZajete(stareX, stareY));
        assertEquals(plansza.ktoJest(nowyX, nowyY), "Wilk");

    }

    @Test
    void RuchNaPoleZeZwierze(){
        new Krolik(0,1,plansza);
        new Owca(1,0,plansza);
        int stareX = wilk.getKoordynatX();
        int stareY = wilk.getKoordynatY();
        wilk.ruch();
        int nowyX = wilk.getKoordynatX();
        int nowyY = wilk.getKoordynatY();
        assertTrue(nowyX != stareX || nowyY != stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertFalse(plansza.czyZajete(stareX, stareY));
        assertEquals(plansza.ktoJest(nowyX, nowyY), "Wilk");
        assertEquals(plansza.getZajetePola(),34 );
    }

    @Test
    void BrakRuchuNaPoleZLisem(){
        new Lis(0,1,plansza);
        new Lis(1,0,plansza);
        int stareX = wilk.getKoordynatX();
        int stareY = wilk.getKoordynatY();
        wilk.ruch();
        int nowyX = wilk.getKoordynatX();
        int nowyY = wilk.getKoordynatY();
        assertTrue(nowyX == stareX && nowyY == stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertEquals(plansza.ktoJest(nowyX, nowyY), "Wilk");
    }

     @Test
     void RuchNaPoleZGraczem(){
         boolean valid = false;
         plansza.setPola(0,1,null);
         plansza.setPola(1,0,null);
         plansza.przemieszczenie(5,5,0,1);
         Gracz gracz1 = new Gracz(1,0,plansza,2);
         Gracz gracz2 = (Gracz) plansza.getPola(0,1);
         gracz1.zmnienIloscKonto("Krolik", 1);
         gracz1.zmnienIloscKonto("Owca", 1);
         gracz1.zmnienIloscKonto("Swinia", 1);
         gracz1.zmnienIloscKonto("Krowa", 1);
         gracz1.zmnienIloscKonto("Kon", 1);
         gracz2.zmnienIloscKonto("Krolik", 1);
         gracz2.zmnienIloscKonto("Owca", 1);
         gracz2.zmnienIloscKonto("Swinia", 1);
         gracz2.zmnienIloscKonto("Krowa", 1);
         gracz2.zmnienIloscKonto("Kon", 1);
         wilk.ruch();
         if(gracz1.getStanKonta().getIloscKrolikow() == 0 && gracz1.getStanKonta().getIloscOwiec() == 0 && gracz1.getStanKonta().getIloscSwin() == 0 && gracz1.getStanKonta().getIloscKrow() == 0 && gracz1.getStanKonta().getIloscKoni() == 0) valid = true;
         if(gracz2.getStanKonta().getIloscKrolikow() == 0 && gracz2.getStanKonta().getIloscOwiec() == 0 && gracz2.getStanKonta().getIloscSwin() == 0 && gracz2.getStanKonta().getIloscKrow() == 0 && gracz2.getStanKonta().getIloscKoni() == 0) valid = true;
         assertTrue(valid);
     }


}