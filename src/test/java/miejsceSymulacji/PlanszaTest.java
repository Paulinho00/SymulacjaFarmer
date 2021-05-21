package miejsceSymulacji;


import glownyUczestnikSymulacji.Gracz;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import zwierzeta.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PlanszaTest {
    Kostka kostka = new Kostka(1,1,1,1,1);
    int[] kordX = null;
    int[] kordY = null;

    @BeforeAll
    void init(){
      kordX = new int[3];
      kordY = new int[3];
      kordX[0] = 0;
      kordX[1] = 4;
      kordX[2] = 6;
      kordY[0] = 0;
      kordY[1] = 4;
      kordY[2] = 6;
    }

    @Test
    void PowinnoUtworzyc() {
        Plansza plansza = new Plansza(8,8,kordX, kordY, kostka);
        int licznik = 0;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (plansza.czyZajete(x, y)) licznik++;
            }
        }
        assertEquals(plansza.getZajetePola(), licznik);
    }

    @Test
    void PowinnienBycWyjatek() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Plansza(2,2, kordX, kordY, kostka));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Plansza(7,8, kordX, kordY, kostka));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Plansza(9,8, kordX, kordY, kostka));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Plansza(5,4, kordX, kordY, kostka));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Plansza(-1,9, kordX, kordY, kostka));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Plansza(-1,-1, kordX, kordY, kostka));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Plansza(8,-1, kordX, kordY, kostka));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Plansza(0,8, kordX, kordY, kostka));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Plansza(8,0, kordX, kordY, kostka));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Plansza(0,0, kordX, kordY, kostka));
    }

    @Test
    void PowinienZwrocicCzyZajete(){
        Plansza plansza = new Plansza(7,7,kordX, kordY, kostka);
        plansza.setPola(1,1, null);
        assertTrue(plansza.czyZajete(0,0));
        assertFalse(plansza.czyZajete(1,1));
    }

    @Test
    void PowinienUzupelnic() {
        Plansza plansza = new Plansza(8, 8, kordX, kordY, kostka);
    }

    @Test
    void PowinienPrzemiescic(){
        kordX[0] = 7;
        kordY[0] = 7;
        Plansza plansza = new Plansza(8,8,kordX, kordY, kostka);
        Krolik krolik = new Krolik(0,0,plansza);
        plansza.przemieszczenie(0,0,1,1);
        assertFalse(plansza.czyZajete(0,0));
        assertEquals(plansza.ktoJest(1,1), "Krolik");

    }

    @Test
    void PowinienZwrocicKtoJestNaPolu() {
        Plansza plansza = new Plansza(7, 7, kordX, kordY, kostka);
        new Lis(1, 1, plansza);
        new Wilk(2, 2, plansza);
        new Krolik(1, 0, plansza);
        new Owca(3, 3, plansza);
        new Swinia(1, 2, plansza);
        new Krowa(2, 0, plansza);
        new Kon(2, 1, plansza);
        assertEquals(plansza.ktoJest(0, 0), "Gracz");
        assertEquals(plansza.ktoJest(1, 1), "Lis");
        assertEquals(plansza.ktoJest(2, 2), "Wilk");
        assertEquals(plansza.ktoJest(1,0), "Krolik");
        assertEquals(plansza.ktoJest(3,3), "Owca");
        assertEquals(plansza.ktoJest(1,2), "Swinia");
        assertEquals(plansza.ktoJest(2,0), "Krowa");
        assertEquals(plansza.ktoJest(2,1), "Kon");
    }

    @Test
    void PowinienWyczyscicPole(){
        Plansza plansza = new Plansza(8, 8, kordX, kordY, kostka);
        new Krolik(1, 0, plansza);
        assertEquals(plansza.getZajetePola(), 33);
        plansza.wyczyscPole(1,0);
        assertFalse(plansza.czyZajete(1,0));
        assertEquals(plansza.getZajetePola(), 32);
    }

    @Test
    void WykonanieTury(){
        Plansza plansza = new Plansza(7, 7, kordX, kordY, kostka);
        plansza.WykonajTure();
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
                    if(kto.equals("Gracz")) {
                        Gracz gracz = (Gracz) plansza.getPola(i,j);
                        assertFalse(gracz.isWykonalRuch());
                    }
                    if(kto.equals("Wilk")) {
                        Wilk wilk = (Wilk) plansza.getPola(i,j);
                        assertFalse(wilk.isWykonalRuch());
                    }
                    if(kto.equals("Lis")) {
                        Lis lis = (Lis) plansza.getPola(i,j);
                        assertFalse(lis.isWykonalRuch());
                    }
                }
            }
        }
        int licznik = 0;
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 7; y++) {
                if (plansza.czyZajete(x, y)) licznik++;
            }
        }
        assertEquals(plansza.getZajetePola(), licznik);
    }





}