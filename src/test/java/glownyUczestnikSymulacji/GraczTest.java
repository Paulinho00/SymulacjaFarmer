package glownyUczestnikSymulacji;

import org.junit.jupiter.api.*;
import miejsceSymulacji.*;
import zwierzeta.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GraczTest {
    Plansza plansza = null;
    @BeforeEach
    void init(){
        int[] kordX = new int[1];
        int[] kordY = new int[1];
        kordX[0] = 0;
        kordY[0] = 0;
        Kostka kostka = new Kostka(5, 4, 3, 2, 1);
        plansza = new Plansza(7, 7, kordX, kordY, kostka);
    }

    @Test
    void PowinienStworzyc(){
    }

    @Test
    void PowinienBycWyjatek(){

        Assertions.assertThrows(IllegalArgumentException.class, () -> new Gracz(-1,0,plansza,1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Gracz(0,-1,plansza,1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Gracz(-1,-2,plansza,1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Gracz(8,5,plansza,1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Gracz(5,8,plansza,1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Gracz(10,10,plansza,1));

    }

    @Test
    void RuchWolnePole(){
        plansza.setPola(0,1,null);
        plansza.setPola(1,0,null);
        Gracz gracz = (Gracz) plansza.getPola(0,0);
        int stareX = gracz.getKoordynatX();
        int stareY = gracz.getKoordynatY();
        gracz.ruch();
        int nowyX = gracz.getKoordynatX();
        int nowyY = gracz.getKoordynatY();
        assertTrue(nowyX != stareX || nowyY != stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertEquals(gracz.getIloscRuchow(), 1);
        assertFalse(plansza.czyZajete(stareX, stareY));
        assertEquals(plansza.ktoJest(nowyX, nowyY), "Gracz");
        assertTrue(gracz.isWykonalRuch());
    }

    @Test
    void RuchPoleZKrolikiem(){
        plansza.setPola(0,1,new Krolik(0,1, plansza));
        plansza.setPola(1,0,new Krolik(1,0, plansza));
        Gracz gracz = (Gracz) plansza.getPola(0,0);
        int stareX = gracz.getKoordynatX();
        int stareY = gracz.getKoordynatY();
        gracz.ruch();
        int nowyX = gracz.getKoordynatX();
        int nowyY = gracz.getKoordynatY();
        assertTrue(nowyX != stareX || nowyY != stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertEquals(gracz.getIloscRuchow(), 1);
        assertFalse(plansza.czyZajete(stareX, stareY));
        assertEquals(gracz.getStanKonta().getIloscKrolikow(), 1);
        assertEquals(plansza.ktoJest(nowyX, nowyY), "Gracz");
        assertTrue(gracz.isWykonalRuch());
    }

    @Test
    void RuchPoleZOwca(){
        plansza.setPola(0,1,new Owca(0,1, plansza));
        plansza.setPola(1,0,new Owca(1,0, plansza));
        Gracz gracz = (Gracz) plansza.getPola(0,0);
        int stareX = gracz.getKoordynatX();
        int stareY = gracz.getKoordynatY();
        gracz.ruch();
        int nowyX = gracz.getKoordynatX();
        int nowyY = gracz.getKoordynatY();
        assertTrue(nowyX != stareX || nowyY != stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertEquals(gracz.getIloscRuchow(), 1);
        assertFalse(plansza.czyZajete(stareX, stareY));
        assertEquals(gracz.getStanKonta().getIloscOwiec(), 1);
        assertEquals(plansza.ktoJest(nowyX, nowyY), "Gracz");
        assertTrue(gracz.isWykonalRuch());
    }

    @Test
    void RuchPoleZSwinia(){
        Gracz gracz = (Gracz) plansza.getPola(0,0);
        plansza.setPola(0,1,new Swinia(0,1, plansza));
        plansza.setPola(1,0,new Swinia(1,0, plansza));
        int stareX = gracz.getKoordynatX();
        int stareY = gracz.getKoordynatY();
        gracz.ruch();
        int nowyX = gracz.getKoordynatX();
        int nowyY = gracz.getKoordynatY();
        assertTrue(nowyX != stareX || nowyY != stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertEquals(gracz.getIloscRuchow(), 1);
        assertFalse(plansza.czyZajete(stareX, stareY));
        assertEquals(gracz.getStanKonta().getIloscSwin(), 1);
        assertEquals(plansza.ktoJest(nowyX, nowyY), "Gracz");
        assertTrue(gracz.isWykonalRuch());
    }

    @Test
    void RuchPoleZKrowa(){
        Gracz gracz = (Gracz) plansza.getPola(0,0);
        plansza.setPola(0,1,new Krowa(0,1, plansza));
        plansza.setPola(1,0,new Krowa(1,0, plansza));
        int stareX = gracz.getKoordynatX();
        int stareY = gracz.getKoordynatY();
        gracz.ruch();
        int nowyX = gracz.getKoordynatX();
        int nowyY = gracz.getKoordynatY();
        assertTrue(nowyX != stareX || nowyY != stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertEquals(gracz.getIloscRuchow(), 1);
        assertFalse(plansza.czyZajete(stareX, stareY));
        assertEquals(gracz.getStanKonta().getIloscKrow(), 1);
        assertEquals(plansza.ktoJest(nowyX, nowyY), "Gracz");
        assertTrue(gracz.isWykonalRuch());
    }

    @Test
    void RuchPoleZKoniem(){
        Gracz gracz = (Gracz) plansza.getPola(0,0);
        plansza.setPola(0,1,new Kon(0,1, plansza));
        plansza.setPola(1,0,new Kon(1,0, plansza));
        int stareX = gracz.getKoordynatX();
        int stareY = gracz.getKoordynatY();
        gracz.ruch();
        int nowyX = gracz.getKoordynatX();
        int nowyY = gracz.getKoordynatY();
        assertTrue(nowyX != stareX || nowyY != stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertEquals(gracz.getIloscRuchow(), 1);
        assertFalse(plansza.czyZajete(stareX, stareY));
        assertEquals(gracz.getStanKonta().getIloscKoni(), 1);
        assertEquals(plansza.ktoJest(nowyX, nowyY), "Gracz");
        assertTrue(gracz.isWykonalRuch());
    }

    @Test
    void RuchNaWilka(){
        Gracz gracz = (Gracz) plansza.getPola(0,0);
        gracz.zmnienIloscKonto("Krolik", 1);
        gracz.zmnienIloscKonto("Owca", 1);
        gracz.zmnienIloscKonto("Swinia", 1);
        gracz.zmnienIloscKonto("Krowa", 1);
        gracz.zmnienIloscKonto("Kon", 1);
        new Wilk(0,1,plansza);
        new Wilk(1,0,plansza);
        int stareX = gracz.getKoordynatX();
        int stareY = gracz.getKoordynatY();
        gracz.ruch();
        int nowyX = gracz.getKoordynatX();
        int nowyY = gracz.getKoordynatY();
        assertTrue(nowyX == stareX && nowyY == stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertTrue(plansza.czyZajete(stareX, stareY));
        assertTrue(gracz.getStanKonta().getIloscKrolikow() == 0 && gracz.getStanKonta().getIloscOwiec() == 0 && gracz.getStanKonta().getIloscSwin()==0 && gracz.getStanKonta().getIloscKrow()==0 && gracz.getStanKonta().getIloscKoni() == 0);
        assertTrue(gracz.isWykonalRuch());
    }

    @Test
    void RuchNaLisa(){
        Gracz gracz = (Gracz) plansza.getPola(0,0);
        gracz.zmnienIloscKonto("Krolik", 1);
        gracz.zmnienIloscKonto("Owca", 1);
        gracz.zmnienIloscKonto("Swinia", 1);
        gracz.zmnienIloscKonto("Krowa", 1);
        gracz.zmnienIloscKonto("Kon", 1);
        new Lis(0,1,plansza);
        new Lis(1,0,plansza);
        int stareX = gracz.getKoordynatX();
        int stareY = gracz.getKoordynatY();
        gracz.ruch();
        int nowyX = gracz.getKoordynatX();
        int nowyY = gracz.getKoordynatY();
        assertTrue(nowyX == stareX && nowyY == stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertTrue(plansza.czyZajete(stareX, stareY));
        assertTrue(gracz.getStanKonta().getIloscKrolikow() == 0 && gracz.getStanKonta().getIloscOwiec() != 0 && gracz.getStanKonta().getIloscSwin()!=0 && gracz.getStanKonta().getIloscKrow()!=0 && gracz.getStanKonta().getIloscKoni() != 0);
        assertTrue(gracz.isWykonalRuch());
    }

    @Test
    void RuchNaGracza(){
        int[] kordX = new int[3];
        int[] kordY = new int[3];
        kordX[0] = 0;
        kordX[1] = 1;
        kordX[2] = 0;
        kordY[0] = 0;
        kordY[1] = 0;
        kordY[2] = 1;
        Kostka kostka = new Kostka(5, 4, 3, 2, 1);
        Plansza plansza = new Plansza(7, 7, kordX, kordY, kostka);
        Gracz gracz1 = (Gracz) plansza.getPola(0,0);
        Gracz gracz2 = (Gracz) plansza.getPola(1,0);
        Gracz gracz3 = (Gracz) plansza.getPola(0,1);
        gracz2.zmnienIloscKonto("Krolik", 1);
        gracz2.zmnienIloscKonto("Owca", 1);
        gracz2.zmnienIloscKonto("Swinia", 1);
        gracz2.zmnienIloscKonto("Krowa", 1);
        gracz2.zmnienIloscKonto("Kon", 1);
        gracz3.zmnienIloscKonto("Krolik", 1);
        gracz3.zmnienIloscKonto("Owca", 1);
        gracz3.zmnienIloscKonto("Swinia", 1);
        gracz3.zmnienIloscKonto("Krowa", 1);
        gracz3.zmnienIloscKonto("Kon", 1);
        int stareX = gracz1.getKoordynatX();
        int stareY = gracz1.getKoordynatY();
        gracz1.ruch();
        int nowyX = gracz1.getKoordynatX();
        int nowyY = gracz1.getKoordynatY();
        assertTrue(nowyX == stareX && nowyY == stareY);
        assertTrue(plansza.czyZajete(nowyX, nowyY));
        assertTrue(plansza.czyZajete(stareX, stareY));
        assertEquals(gracz1.wartoscKonta(),127);
        assertTrue(gracz2.wartoscKonta() == 0 || gracz3.wartoscKonta() == 0);
        assertTrue(gracz1.isWykonalRuch());

    }

    @Test
    void WymianaZwierzat(){
        Gracz gracz = (Gracz) plansza.getPola(0,0);
        gracz.zmnienIloscKonto("Krolik",114);
        gracz.zmnienIloscKonto("Owca", 1);
        gracz.wymiana();
        assertEquals(gracz.getStanKonta().getIloscKrolikow(), 0);
        assertEquals(gracz.getStanKonta().getIloscOwiec(), 0);
        assertEquals(gracz.getStanKonta().getIloscSwin(), 1);
        assertEquals(gracz.getStanKonta().getIloscKrow(), 1);
        assertEquals(gracz.getStanKonta().getIloscKoni(), 1);
    }

    @Test
    void PowinienZmnienicKonto(){
        Gracz gracz = (Gracz) plansza.getPola(0,0);
        gracz.zmnienIloscKonto("Krolik", 0);
        gracz.zmnienIloscKonto("Owca", 1);
        gracz.zmnienIloscKonto("Swinia", 1);
        gracz.zmnienIloscKonto("Krowa", 2);
        gracz.zmnienIloscKonto("Kon", 1);
        assertEquals(gracz.getStanKonta().getIloscKrolikow(), 0);
        assertEquals(gracz.getStanKonta().getIloscOwiec(), 1);
        assertEquals(gracz.getStanKonta().getIloscSwin(), 1);
        assertEquals(gracz.getStanKonta().getIloscKrow(), 2);
        assertEquals(gracz.getStanKonta().getIloscKoni(), 1);

    }

    @Test
    void ZwracanieWartosciKonta(){
        Gracz gracz = (Gracz) plansza.getPola(0,0);
        gracz.zmnienIloscKonto("Kon",1);
        gracz.zmnienIloscKonto("Krowa", 1);
        gracz.zmnienIloscKonto("Swinia", 1);
        assertEquals(gracz.wartoscKonta(),120);
    }

}