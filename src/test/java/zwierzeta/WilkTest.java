package zwierzeta;
import glownyUczestnikSymulacji.Gracz;
import miejsceSymulacji.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class WilkTest {
    Kostka kostka = new Kostka(1,1,1,1,1);
    Plansza plansza = new Plansza(7,7,kostka);
    Gracz gracz = new Gracz(2,2,1,0,0,1,1,plansza);
    Wilk wilk = new Wilk(0,0,plansza, gracz.getHandler());

    @Test
    void RuchNaWolnePole(){
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
        assertEquals(plansza.getZajetePola(), 3);
    }

    @Test
    void BrakRuchuNaPoleZLisem(){
        plansza.setPola(0,1,new Lis(0,1,plansza,gracz.getHandler()));
        plansza.setPola(1,0, new Lis(1,0,plansza, gracz.getHandler()));
        int stareX = wilk.getKoordynatX();
        int stareY = wilk.getKoordynatY();
        wilk.ruch();
        int nowyX = wilk.getKoordynatX();
        int nowyY = wilk.getKoordynatY();
        assertTrue(nowyX == stareX && nowyY == stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertEquals(plansza.ktoJest(nowyX, nowyY), "Wilk");
    }


}