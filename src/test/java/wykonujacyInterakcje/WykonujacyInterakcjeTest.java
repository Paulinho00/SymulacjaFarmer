package wykonujacyInterakcje;
import glownyUczestnikSymulacji.Gracz;
import miejsceSymulacji.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WykonujacyInterakcjeTest {
    Kostka kostka = new Kostka(1,1,1,1,1);
    Plansza plansza = new Plansza(4,4, kostka);
    Gracz gracz = new Gracz(0,0,4,3,2,1,1, plansza);

    @Test
    void PowinienUsunacKroliki(){
        gracz.getHandler().usuniecieKrolikowKonto();
        assertEquals(gracz.getStanKonta().getIloscKrolikow(), 0);
        assertEquals(gracz.getStanKonta().getIloscOwiec(), 3);
        assertEquals(gracz.getStanKonta().getIloscSwin(), 2);
        assertEquals(gracz.getStanKonta().getIloscKrow(), 1);
        assertEquals(gracz.getStanKonta().getIloscKoni(), 1);
    }

    @Test
    void NiePowinienUsunacKroliki(){
        gracz.getHandler().setCzySpotkalLisa(true);
        gracz.getHandler().usuniecieKrolikowKonto();
        assertEquals(gracz.getStanKonta().getIloscKrolikow(), 4);
        assertEquals(gracz.getStanKonta().getIloscOwiec(), 3);
        assertEquals(gracz.getStanKonta().getIloscSwin(), 2);
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
        assertEquals(gracz.getStanKonta().getIloscKrolikow(), 4);
        assertEquals(gracz.getStanKonta().getIloscOwiec(), 3);
        assertEquals(gracz.getStanKonta().getIloscSwin(), 2);
        assertEquals(gracz.getStanKonta().getIloscKrow(), 1);
        assertEquals(gracz.getStanKonta().getIloscKoni(), 1);
    }

}