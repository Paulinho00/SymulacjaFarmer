package okno;

import glownyUczestnikSymulacji.Gracz;
import miejsceSymulacji.Kostka;
import miejsceSymulacji.Plansza;
import obslugaPlikow.Zapisywator;
import zwierzeta.Lis;
import zwierzeta.Wilk;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class MojeOkno extends JFrame implements ActionListener {

    private final JLabel lStartoweTytul, lStartKrolik, lStartOwca, lStartSwinia, lStartKrowa, lStartKon;
    private final JLabel lRozmiaryTytul, lPlanszaRozmiar, lKoordynatyTytul, lKordynatyX, lKoordynatyY, lScianyTytul, lScianyKroliki, lScianyOwce, lScianySwinie, lScianyKrowy, lScianyKonie;
    private final JTextField tStartKrolik, tStartOwca, tStartSwinia, tStartKrowa, tStartKon, tScianyKroliki, tScianyOwce, tScianySwinie, tScianyKrowy, tScianyKonie;
    private final JTextField tPlanszaRozmiar,tKoordynatyX, tKoordynatyY;
    private final JButton potwierdz;
    private int x,y,kordX,kordY,kroliki,owce,swinie,krowy,konie, scianyKroliki, scianyOwce, scianyKonie, scianySwinie, scianyKrowy;
    public MojeOkno(){
        setSize(1000,300);
        setTitle("Symulacja Farmer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        lStartoweTytul = new JLabel("Startowe stany konta");
        lStartoweTytul.setBounds(60,10,160,20);
        add(lStartoweTytul);

        lStartKrolik = new JLabel("Startowa liczba krolikow:");
        lStartKrolik.setBounds(20,50,160,20);
        add(lStartKrolik);
        tStartKrolik = new JTextField("");
        tStartKrolik.setBounds(180,50,30,20);
        add(tStartKrolik);

        lStartOwca = new JLabel("Startowa liczba owiec:");
        lStartOwca.setBounds(20,80,160,20);
        add(lStartOwca);
        tStartOwca = new JTextField("");
        tStartOwca.setBounds(180,80,30,20);
        add(tStartOwca);

        lStartSwinia = new JLabel("Startowa liczba swin:");
        lStartSwinia.setBounds(20,110,160,20);
        add(lStartSwinia);
        tStartSwinia = new JTextField("");
        tStartSwinia.setBounds(180,110,30,20);
        add(tStartSwinia);

        lStartKrowa = new JLabel("Startowa liczba krow:");
        lStartKrowa.setBounds(20,140,160,20);
        add(lStartKrowa);
        tStartKrowa = new JTextField("");
        tStartKrowa.setBounds(180,140,30,20);
        add(tStartKrowa);

        lStartKon = new JLabel("Startowa liczba koni:");
        lStartKon.setBounds(20,170,160,20);
        add(lStartKon);
        tStartKon = new JTextField("");
        tStartKon.setBounds(180,170,30,20);
        add(tStartKon);

        lRozmiaryTytul = new JLabel("Rozmiary planszy(musi byc kwadratowa, wieksza niz 6x6)");
        lRozmiaryTytul.setBounds(280, 10, 350, 20);
        add(lRozmiaryTytul);

        lPlanszaRozmiar = new JLabel("Szerokosc/Dlugosc planszy:");
        lPlanszaRozmiar.setBounds(320, 50,160,20);
        add(lPlanszaRozmiar);
        tPlanszaRozmiar= new JTextField("");
        tPlanszaRozmiar.setBounds(490, 50, 30, 20);
        add(tPlanszaRozmiar);


        lKoordynatyTytul = new JLabel("Startowe polozenie gracza");
        lKoordynatyTytul.setBounds(330, 90, 160, 20);
        add(lKoordynatyTytul);

        lKordynatyX = new JLabel("Polozenie X (od 1):");
        lKordynatyX.setBounds(370, 120, 140,20);
        add(lKordynatyX);
        tKoordynatyX = new JTextField("");
        tKoordynatyX.setBounds(490,120,30,20);
        add(tKoordynatyX);

        lKoordynatyY = new JLabel("Polozenie Y (od 1):");
        lKoordynatyY.setBounds(370,150,110,20);
        add(lKoordynatyY);
        tKoordynatyY = new JTextField("");
        tKoordynatyY.setBounds(490,150,30,20);
        add(tKoordynatyY);

        lScianyTytul = new JLabel("Sciany wirtualnej kostki");
        lScianyTytul.setBounds(720, 10, 140, 20);
        add(lScianyTytul);

        lScianyKroliki = new JLabel("Sciany z krolikami:");
        lScianyKroliki.setBounds(690,50,120,20);
        add(lScianyKroliki);
        tScianyKroliki = new JTextField("");
        tScianyKroliki.setBounds(810,50,30,20);
        add(tScianyKroliki);

        lScianyOwce = new JLabel("Sciany z owcami:");
        lScianyOwce.setBounds(690,80,120,20);
        add(lScianyOwce);
        tScianyOwce = new JTextField("");
        tScianyOwce.setBounds(810,80,30,20);
        add(tScianyOwce);

        lScianySwinie = new JLabel("Sciany ze swiniami:");
        lScianySwinie.setBounds(690,110,120,20);
        add(lScianySwinie);
        tScianySwinie = new JTextField("");
        tScianySwinie.setBounds(810,110,30,20);
        add(tScianySwinie);

        lScianyKrowy = new JLabel("Sciany z krowami:");
        lScianyKrowy.setBounds(690,140,120,20);
        add(lScianyKrowy);
        tScianyKrowy = new JTextField("");
        tScianyKrowy.setBounds(810,140,30,20);
        add(tScianyKrowy);

        lScianyKonie = new JLabel("Sciany z koniami:");
        lScianyKonie.setBounds(690,170,120,20);
        add(lScianyKonie);
        tScianyKonie = new JTextField("");
        tScianyKonie.setBounds(810,170,30,20);
        add(tScianyKonie);

        potwierdz = new JButton("Wprowadz");
        potwierdz.setBounds(400,220,100,20);
        add(potwierdz);
        potwierdz.addActionListener(this);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == potwierdz) {
            try{
                kroliki = Integer.parseInt(tStartKrolik.getText());
                owce = Integer.parseInt(tStartOwca.getText());
                swinie = Integer.parseInt(tStartSwinia.getText());
                krowy = Integer.parseInt(tStartKrowa.getText());
                konie = Integer.parseInt(tStartKon.getText());
                x = Integer.parseInt(tPlanszaRozmiar.getText());
                y = Integer.parseInt(tPlanszaRozmiar.getText());
                kordX = Integer.parseInt(tKoordynatyX.getText())-1;
                kordY = Integer.parseInt(tKoordynatyY.getText())-1;
                scianyKroliki = Integer.parseInt(tScianyKroliki.getText());
                scianyOwce = Integer.parseInt(tScianyOwce.getText());
                scianySwinie = Integer.parseInt(tScianySwinie.getText());
                scianyKrowy = Integer.parseInt(tScianyKrowy.getText());
                scianyKonie = Integer.parseInt(tScianyKonie.getText());
                    Kostka kostka = new Kostka(scianyKroliki, scianyOwce, scianySwinie, scianyKrowy, scianyKonie);
                    Plansza plansza = new Plansza(x, y, kostka);
                    Gracz gracz = new Gracz(kordX, kordY, kroliki, owce, swinie, krowy, konie, plansza);
                    Zapisywator zapis = new Zapisywator(gracz);
                    zapis.naglowek();
                    Random losowanie = new Random();
                    do {
                        x = losowanie.nextInt(plansza.getRozmiarX());
                        y = losowanie.nextInt(plansza.getRozmiarY());
                    } while (plansza.czyZajete(x, y));
                    Wilk wilk = new Wilk(x, y, plansza, gracz.getHandler());
                    do {
                        x = losowanie.nextInt(plansza.getRozmiarX());
                        y = losowanie.nextInt(plansza.getRozmiarY());
                    } while (plansza.czyZajete(x, y));
                    Lis lis = new Lis(x, y, plansza, gracz.getHandler());
                    plansza.uzupelnienie();
                    do {
                        gracz.ruch();
                        wilk.ruch();
                        lis.ruch();
                        plansza.RuchyNaPlanszy();
                        plansza.ResetZwierzat();
                        gracz.wymiana();
                        plansza.uzupelnienie();
                        zapis.dodajLinie();
                        gracz.getHandler().setCzySpotkalWilka(false);
                        gracz.getHandler().setCzySpotkalLisa(false);
                    } while (gracz.czyKoniec() < 127);
                    zapis.zamnkniecie();
                    JOptionPane.showMessageDialog(null,"Symulacja zakonczona sukcesem po "+gracz.getIloscRuchow()+" ruchach.");
                } catch(NumberFormatException f){JOptionPane.showMessageDialog(null,"Bledne dane", "Bledne dane", JOptionPane.ERROR_MESSAGE);}
                catch(IllegalArgumentException z)  {JOptionPane.showMessageDialog(null,"Bledne dane", "Bledne dane",JOptionPane.ERROR_MESSAGE);}
        }
        }

    public static void main(String[] args){
        MojeOkno okno = new MojeOkno();
        okno.setVisible(true);
    }


}
