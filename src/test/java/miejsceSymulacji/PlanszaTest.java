package miejsceSymulacji;


import glownyUczestnikSymulacji.Gracz;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import zwierzeta.*;

import static org.junit.jupiter.api.Assertions.*;

class PlanszaTest {
    Kostka kostka = new Kostka(1,1,1,1,1);

    @Test
    void PowinnoUtworzyc() {
        Plansza plansza = new Plansza(7,7,kostka);
    }

    @Test
    void PowinnienBycWyjatek() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Plansza(2,2,kostka));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Plansza(1,4,kostka));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Plansza(6,2,kostka));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Plansza(5,4,kostka));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Plansza(-1,2,kostka));
    }

    @Test
    void PowinienZwrocicCzyZajete(){
        Plansza plansza = new Plansza(7,7, kostka);
        Gracz gracz = new Gracz(0,0,0,0,0,0,0,plansza);
        assertTrue(plansza.czyZajete(0,0));
        assertFalse(plansza.czyZajete(1,1));
    }

    @Test
    void PowinienUzupelnic() {
        Plansza plansza = new Plansza(7, 7, kostka);
        Gracz gracz = new Gracz(0, 0, 0, 0, 0, 0, 0, plansza);
        new Lis(1, 1, plansza, gracz.getHandler());
        new Wilk(2, 2, plansza, gracz.getHandler());
        plansza.uzupelnienie();
        int licznik = 0;
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 7; y++) {
                if (plansza.czyZajete(x, y)) licznik++;
            }
        }
        assertEquals(plansza.getZajetePola(), licznik);
    }

    @Test
    void PowinienPrzemiescic(){
        Plansza plansza = new Plansza(7,7,kostka);
        Krolik krolik = new Krolik(0,0,plansza);
        plansza.setPola(0,0,krolik);
        plansza.przemieszczenie(0,0,1,1);
        assertFalse(plansza.czyZajete(0,0));
        assertEquals(plansza.ktoJest(1,1), "Krolik");

    }

    @Test
    void PowinienZwrocicKtoJestNaPolu() {
        Plansza plansza = new Plansza(7, 7, kostka);
        Gracz gracz = new Gracz(0, 0, 0, 0, 0, 0, 0, plansza);
        plansza.setPola(0, 1, new Lis(1, 1, plansza, gracz.getHandler()));
        plansza.setPola(0, 2, new Wilk(2, 2, plansza, gracz.getHandler()));
        plansza.setPola(1, 0, new Krolik(1, 0, plansza));
        plansza.setPola(1, 1, new Owca(1, 1, plansza));
        plansza.setPola(1, 2, new Swinia(1, 2, plansza));
        plansza.setPola(2, 0, new Krowa(2, 0, plansza));
        plansza.setPola(2, 1, new Kon(2, 1, plansza));
        assertEquals(plansza.ktoJest(0, 0), "Gracz");
        assertEquals(plansza.ktoJest(0, 1), "Lis");
        assertEquals(plansza.ktoJest(0, 2), "Wilk");
        assertEquals(plansza.ktoJest(1,0), "Krolik");
        assertEquals(plansza.ktoJest(1,1), "Owca");
        assertEquals(plansza.ktoJest(1,2), "Swinia");
        assertEquals(plansza.ktoJest(2,0), "Krowa");
        assertEquals(plansza.ktoJest(2,1), "Kon");
    }

    @Test
    void PowinienWyczyscicPole(){
        Plansza plansza = new Plansza(7, 7, kostka);
        new Krolik(1, 0, plansza);
        assertEquals(plansza.getZajetePola(), 1);
        plansza.wyczyscPole(1,0);
        assertFalse(plansza.czyZajete(1,0));
        assertEquals(plansza.getZajetePola(), 0);
    }

    @Test
    void ZarzadzanieRuchem(){
        Plansza plansza = new Plansza(7, 7, kostka);
        Gracz gracz = new Gracz(0,0,0,0,0,0,0,plansza);
        Wilk wilk = new Wilk(0,1,plansza, gracz.getHandler());
        Lis lis = new Lis(1,0, plansza, gracz.getHandler());
        plansza.uzupelnienie();
        plansza.RuchyNaPlanszy();
        for(int i = 0; i < plansza.getRozmiarX(); i++){
            for(int j = 0; j < plansza.getRozmiarY(); j++) {
                if (plansza.czyZajete(i, j)) {
                    String kto = plansza.ktoJest(i, j);
                    if (kto.equals("Krolik")) {
                        Krolik krolik = (Krolik) plansza.getPola(i, j);
                        assertTrue(krolik.isWykonalRuch());
                    }
                    if (kto.equals("Owca")) {
                        Owca owca = (Owca) plansza.getPola(i, j);
                        assertTrue(owca.isWykonalRuch());

                    }
                    if (kto.equals("Swinia")) {
                        Swinia swinia = (Swinia) plansza.getPola(i, j);
                        assertTrue(swinia.isWykonalRuch());

                    }
                    if (kto.equals("Krowa")) {
                        Krowa krowa = (Krowa) plansza.getPola(i, j);
                        assertTrue(krowa.isWykonalRuch());
                    }
                    if (kto.equals("Kon")) {
                        Kon kon = (Kon) plansza.getPola(i, j);
                        assertTrue(kon.isWykonalRuch());
                    }
                }
            }

        }
        plansza.ResetZwierzat();
        for(int i = 0; i < plansza.getRozmiarX(); i++){
            for(int j = 0; j < plansza.getRozmiarY(); j++) {
                if (plansza.czyZajete(i, j)) {
                    String kto = plansza.ktoJest(i, j);
                    if (kto.equals("Krolik")) {
                        Krolik krolik = (Krolik) plansza.getPola(i, j);
                        assertFalse(krolik.isWykonalRuch());
                    }
                    if (kto.equals("Owca")) {
                        Owca owca = (Owca) plansza.getPola(i, j);
                        assertFalse(owca.isWykonalRuch());

                    }
                    if (kto.equals("Swinia")) {
                        Swinia swinia = (Swinia) plansza.getPola(i, j);
                        assertFalse(swinia.isWykonalRuch());

                    }
                    if (kto.equals("Krowa")) {
                        Krowa krowa = (Krowa) plansza.getPola(i, j);
                        assertFalse(krowa.isWykonalRuch());
                    }
                    if (kto.equals("Kon")) {
                        Kon kon = (Kon) plansza.getPola(i, j);
                        assertFalse(kon.isWykonalRuch());
                    }
                }
            }

        }
    }





}