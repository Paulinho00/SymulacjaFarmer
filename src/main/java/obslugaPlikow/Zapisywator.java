package obslugaPlikow;
import glownyUczestnikSymulacji.Gracz;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * zapisuje dane odczytane z planszy do pliku .csv
 */
public class Zapisywator {

    private File nowyPlik;
    private FileWriter plik;
    private Integer tury;

    /**
     * tworzy obiekt odpowiedzialny za utworzenie pliku .csv i zapisywanie odczytanych danych do niego
     */
    public Zapisywator() {
        nowyPlik = new File("SymulacjaFarmer.csv");
        tury = 0;
        try {
            plik = new FileWriter(nowyPlik);
            plik.append("Tura");
            plik.append(";");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * zapisuje stany konta, ilosc ruchow oraz informacje czy: spotkal Wilka, Lisa lub zostal okradziony po wykonaniu tury, do pliku, dla kazdego gracza
     * @param gracz
     */
    public void KolejnaTura(Gracz[] gracz){
            tury++;
        try {
            plik.append(tury.toString());
            plik.append(";");
            for(int i =0; i < gracz.length; i++) {
                dodajLinie(gracz[i]);
            }
            plik.append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * tworzy naglowek tabeli w pliku
     */
    public void naglowek(){
        try {
            for(int i = 0; i < 3; i++) {
                plik.append(";");
                plik.append("Ilosc ruchow");
                plik.append(";");
                plik.append("Liczba krolikow");
                plik.append(";");
                plik.append("Liczba owiec");
                plik.append(";");
                plik.append("Liczba swin");
                plik.append(";");
                plik.append("Liczba krow");
                plik.append(";");
                plik.append("Liczba kon");
                plik.append(";");
                plik.append("Czy spotkal lisa?");
                plik.append(";");
                plik.append("Czy spotkal wilka?");
                plik.append(";");
                plik.append("Czy zostal okradziony?");
                plik.append(";");
            }
            plik.append("\n");

        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }


    /**
     * odczytuje z planszy stany konta, ilosc ruchow oraz informacje czy: spotkal Wilka, Lisa lub zostal okradziony danego gracza
     * @param gracz obiekt z ktorego metoda odczytuje dane i zapisuje je do pliku
     */
    private void dodajLinie(Gracz gracz) {
        try {
            plik.append(";");
            String s = Integer.toString(gracz.getIloscRuchow());
            plik.append(s);
            plik.append(";");
            s = Integer.toString(gracz.getStanKonta().getIloscKrolikow());
            plik.append(s);
            plik.append(";");
            s = Integer.toString(gracz.getStanKonta().getIloscOwiec());
            plik.append(s);
            plik.append(";");
            s = Integer.toString(gracz.getStanKonta().getIloscSwin());
            plik.append(s);
            plik.append(";");
            s = Integer.toString(gracz.getStanKonta().getIloscKrow());
            plik.append(s);
            plik.append(";");
            s = Integer.toString(gracz.getStanKonta().getIloscKoni());
            plik.append(s);
            plik.append(";");
            if(gracz.getHandler().isCzySpotkalLisa()) plik.append("TAK");
            else plik.append("NIE");
            plik.append(";");
            if(gracz.getHandler().isCzySpotkalWilka()) plik.append("TAK");
            else plik.append("NIE");
            plik.append(";");
            if(gracz.getHandler().isCzyZostalOkradziony()) plik.append("TAK");
            else plik.append("NIE");
            plik.append(";");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * zamyka strumien zapisujacy do pliku
     */
    public void zamnkniecie(){
        try {
            plik.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
