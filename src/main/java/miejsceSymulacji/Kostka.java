package miejsceSymulacji;
import java.util.Random;

/**
 * Przechowuje informacje o prawdopodobienstwie wylosowanie danego typu zwierzecia,
 * pelni role wirtualnej kostki
 */
public class Kostka {
    private final int iloscScian;
    private final int scianyKroliki;
    private final int scianyOwce;
    private final int scianySwinie;
    private final int scianyKrowy;
    private final int scianyKonie;

    /**
     *
     * @param kroliki liczba scian z krolikami
     * @param owce liczba scian z owcami
     * @param swinie liczba scian z swiniami
     * @param krowy liczba scian z krowami
     * @param konie liczba scian z konmi
     */
    public Kostka(int kroliki, int owce, int swinie, int krowy, int konie){
        scianyKroliki = kroliki;
        scianyOwce = owce;
        scianySwinie = swinie;
        scianyKrowy = krowy;
        scianyKonie = konie;
        iloscScian = kroliki + owce + swinie + krowy + konie;
        if(iloscScian <= 0 || scianyKroliki < 0 || scianyOwce < 0 || scianySwinie < 0 || scianyKonie < 0 || scianyKrowy < 0) throw new IllegalArgumentException("Bledne dane");
    }

    /**
     * wykonuje losowanie na podstawie prawdopodobienstwa przechowywanego w obiekcie
     * @return typ wylosowanego obiektu
     */
    protected String losowanie(){
        Random losowanie = new Random();
        int liczba = losowanie.nextInt(iloscScian)+1;
        if(liczba <= scianyKroliki ) return "Krolik";
        if(liczba > scianyKroliki && liczba <=(scianyKroliki+scianyOwce) ) return "Owca";
        if(liczba > (scianyKroliki+scianyOwce) && liczba <= (scianyKroliki+scianyOwce+scianySwinie) ) return "Swinia";
        if(liczba > (scianyKroliki+scianyOwce+scianySwinie) && liczba <= (scianyKroliki+scianyOwce+scianySwinie+scianyKrowy)) return "Krowa";
        if(liczba > (scianyKroliki+scianyOwce+scianySwinie+scianyKrowy) && liczba <=(scianyKroliki+scianyOwce+scianySwinie+scianyKrowy+scianyKonie)) return "Kon";
        return "Blad";
    }
}
