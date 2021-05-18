package glownyUczestnikSymulacji;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class ListaZwierzatTest {

    @Test
    void PowinnoUtworzyc() {
        ListaZwierzat stanKonta = new ListaZwierzat(2, 3, 0, 1, 0);
    }

    @Test
    void PowinnienBycWyjatek() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> new ListaZwierzat(0,0,0,0,3));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ListaZwierzat(0,0,0,4,0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ListaZwierzat(0,0,0,5,0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ListaZwierzat(0,0,11,0,0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ListaZwierzat(0,0,12,0,3));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ListaZwierzat(0,22,0,0,0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ListaZwierzat(0,23,0,0,0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ListaZwierzat(127,0,0,0,0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ListaZwierzat(128,0,0,0,0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ListaZwierzat(145,24,2,6,0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ListaZwierzat(2,-1,2,6,0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ListaZwierzat(-1,-1,2,6,0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ListaZwierzat(0,0,2,-2,0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ListaZwierzat(0,0,0,0,-1));
    }

}