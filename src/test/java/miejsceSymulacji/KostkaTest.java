package miejsceSymulacji;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KostkaTest {


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