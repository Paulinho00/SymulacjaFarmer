package miejsceSymulacji;

import glownyUczestnikSymulacji.ListaZwierzat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KostkaTest {

    @Test
    void PowinienBycWyjatek(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Kostka(0,0,0,0,0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Kostka(-1,0,0,0,2));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Kostka(0,-1,0,0,2));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Kostka(0,0,-1,0,2));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Kostka(0,0,0,-1,2));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Kostka(0,0,0,3,-1));
    }
    @Test
    void PowinienZwrocicKrolik(){
            Kostka kostka = new Kostka(1,0,0,0,0);
            String kto = kostka.losowanie();
            assertEquals(kto, "Krolik");
    }

    @Test
    void PowinienZwrocicOwce(){
        Kostka kostka = new Kostka(0,1,0,0,0);
        String kto = kostka.losowanie();
        assertEquals(kto, "Owca");
    }

    @Test
    void PowinienZwrocicSwinie(){
        Kostka kostka = new Kostka(0,0,1,0,0);
        String kto = kostka.losowanie();
        assertEquals(kto, "Swinia");
    }

    @Test
    void PowinienZwrocicKrowe(){
        Kostka kostka = new Kostka(0,0,0,1,0);
        String kto = kostka.losowanie();
        assertEquals(kto, "Krowa");
    }

    @Test
    void PowinienZwrocicKon(){
        Kostka kostka = new Kostka(0,0,0,0,1);
        String kto = kostka.losowanie();
        assertEquals(kto, "Kon");
    }

}