package wykonujacyInterakcje;
import glownyUczestnikSymulacji.Gracz;
import miejsceSymulacji.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WykonujacyInterakcjeTest {

    int[] kordX;
    int[] kordY;
    Plansza plansza = null;
    Gracz gracz;

    @BeforeEach
    void init(){
        kordX = new int[2];
        kordY = new int[2];
        kordX[0] = 0;
        kordX[1] = 4;
        kordY[0] = 0;
        kordY[1] = 4;
        Kostka kostka = new Kostka(1,1,1,1,1);
        plansza = new Plansza(8, 8, kordX, kordY, kostka);
        gracz = (Gracz) plansza.getPola(0,0);
        gracz.zmnienIloscKonto("Krolik",7);
        gracz.zmnienIloscKonto("Owca", 0);
        gracz.zmnienIloscKonto("Swinia", 0);
        gracz.zmnienIloscKonto("Krowa", 1);
        gracz.zmnienIloscKonto("Kon", 1);
    }

    @Test
    void PowinienUsunacKroliki(){
        gracz.getHandler().usuniecieKrolikowKonto();
        assertEquals(gracz.getStanKonta().getIloscKrolikow(), 0);
        assertEquals(gracz.getStanKonta().getIloscOwiec(), 0);
        assertEquals(gracz.getStanKonta().getIloscSwin(), 0);
        assertEquals(gracz.getStanKonta().getIloscKrow(), 1);
        assertEquals(gracz.getStanKonta().getIloscKoni(), 1);
    }

    @Test
    void NiePowinienUsunacKroliki(){
        gracz.getHandler().setCzySpotkalLisa(true);
        gracz.getHandler().usuniecieKrolikowKonto();
        assertEquals(gracz.getStanKonta().getIloscKrolikow(), 7);
        assertEquals(gracz.getStanKonta().getIloscOwiec(), 0);
        assertEquals(gracz.getStanKonta().getIloscSwin(), 0);
        assertEquals(gracz.getStanKonta().getIloscKrow(), 1);
        assertEquals(gracz.getStanKonta().getIloscKoni(), 1);
    }

    @Test
    void PowinienUsunacWszystko(){
        gracz.getHandler().wyczyszczenieKontaGracza();
        assertEquals(gracz.getStanKonta().getIloscKrolikow(), 0);
        assertEquals(gracz.getStanKonta().getIloscOwiec(), 0);
        assertEquals(gracz.getStanKonta().getIloscSwin(), 0);
        assertEquals(gracz.getStanKonta().getIloscKrow(), 0);
        assertEquals(gracz.getStanKonta().getIloscKoni(), 0);
    }

    @Test
    void NiePowinienUsunacWszystko(){
        gracz.getHandler().setCzySpotkalWilka(true);
        gracz.getHandler().wyczyszczenieKontaGracza();
        assertEquals(gracz.getStanKonta().getIloscKrolikow(), 7);
        assertEquals(gracz.getStanKonta().getIloscOwiec(), 0);
        assertEquals(gracz.getStanKonta().getIloscSwin(), 0);
        assertEquals(gracz.getStanKonta().getIloscKrow(), 1);
        assertEquals(gracz.getStanKonta().getIloscKoni(), 1);
    }

    @Test
    void PowinienOkrasc(){
        gracz = (Gracz) plansza.getPola(0,0);
        Gracz gracz2 = (Gracz) plansza.getPola(4,4);
        gracz2.zmnienIloscKonto("Krolik", 1);
        gracz2.zmnienIloscKonto("Owca", 1);
        gracz2.zmnienIloscKonto("Swinia", 1);
        gracz2.zmnienIloscKonto("Krowa", 1);
        gracz2.zmnienIloscKonto("Kon", 1);

        gracz2.getHandler().spotkanieZGraczem(gracz);
        assertTrue(gracz2.wartoscKonta() == 0);
        assertEquals(gracz.wartoscKonta(),242);
        assertTrue(gracz2.getHandler().isCzyZostalOkradziony());
    }

}