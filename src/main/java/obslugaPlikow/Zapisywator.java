package obslugaPlikow;
import glownyUczestnikSymulacji.Gracz;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Zapisywator {

    private File nowyPlik;
    private FileWriter plik;
    private Gracz gracz;
    private Integer tury;

    public Zapisywator(Gracz gracz){
        nowyPlik = new File("SymulacjaFarmer.csv");
        this.gracz = gracz;
        tury = 0;
        try {
            plik = new FileWriter(nowyPlik);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void naglowek(){
        try {
            plik.append("Tura");
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
            plik.append("\n");
        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void dodajLinie() {
        try {
            tury++;

            plik.append(tury.toString());
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
            plik.append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void zamnkniecie(){
        try {
            plik.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
