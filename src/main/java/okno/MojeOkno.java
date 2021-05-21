package okno;

import glownyUczestnikSymulacji.Gracz;
import miejsceSymulacji.Kostka;
import miejsceSymulacji.Plansza;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Okno z GUI, w ktorym uzytkownik wpisuje startowe dane
 */

public class MojeOkno extends JFrame implements ActionListener {

    private final JLabel lStartoweTytul1, lStartKord1X, lStartKord1Y, lStartoweTytul2, lStartKord2X, lStartKord2Y;
    private final JLabel lRozmiaryTytul, lPlanszaRozmiar, lKoordynatyTytul, lStartKord3X, lStartKord3Y, lScianyTytul, lScianyKroliki, lScianyOwce, lScianySwinie, lScianyKrowy, lScianyKonie;
    private final JTextField tStartKord1X, tStartKord1Y, tStartKord2X, tStartKord2Y, tScianyKroliki, tScianyOwce, tScianySwinie, tScianyKrowy, tScianyKonie;
    private final JTextField tPlanszaRozmiar,tStartKord3X, tStartKord3Y;
    private final JButton potwierdz;
    private int x,y,scianyKroliki, scianyOwce, scianyKonie, scianySwinie, scianyKrowy;
    private int[] koordynatyX,koordynatyY;
    private Timer timer;
    private JFrame planszaRamka;
    private Plansza plansza;
    private PlanszaGUI planszaGUI;


    /**
     * Tworzenie wygladu GUI, odpowiedzialnego za wpisywane danych startowych
     */
    public MojeOkno(){
        setSize(1000,300);
        setTitle("Symulacja Farmer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        lStartoweTytul1 = new JLabel("Startowe polozenie pierwszego gracza");
        lStartoweTytul1.setBounds(20,10,220,20);
        add(lStartoweTytul1);

        lStartKord1X = new JLabel("Polozenie X (od 1)");
        lStartKord1X.setBounds(20,40,160,20);
        add(lStartKord1X);
        tStartKord1X = new JTextField("");
        tStartKord1X.setBounds(180,40,30,20);
        add(tStartKord1X);

        lStartKord1Y = new JLabel("Polozenie Y (od 1)");
        lStartKord1Y.setBounds(20,70,160,20);
        add(lStartKord1Y);
        tStartKord1Y = new JTextField("");
        tStartKord1Y.setBounds(180,70,30,20);
        add(tStartKord1Y);

        lStartoweTytul2 = new JLabel("Startowa  polozenie drugiego gracza");
        lStartoweTytul2.setBounds(20,100,220,20);
        add(lStartoweTytul2);

        lStartKord2X = new JLabel("Polozenie X (od 1)");
        lStartKord2X.setBounds(20,130,160,20);
        add(lStartKord2X);
        tStartKord2X = new JTextField("");
        tStartKord2X.setBounds(180,130,30,20);
        add(tStartKord2X);

        lStartKord2Y = new JLabel("Polozenie Y (od 1)");
        lStartKord2Y.setBounds(20,160,160,20);
        add(lStartKord2Y);
        tStartKord2Y = new JTextField("");
        tStartKord2Y.setBounds(180,160,30,20);
        add(tStartKord2Y);

        lRozmiaryTytul = new JLabel("Rozmiary planszy(musi byc kwadratowa, wieksza niz 6x6)");
        lRozmiaryTytul.setBounds(280, 10, 350, 20);
        add(lRozmiaryTytul);

        lPlanszaRozmiar = new JLabel("Szerokosc/Dlugosc planszy:");
        lPlanszaRozmiar.setBounds(320, 50,160,20);
        add(lPlanszaRozmiar);
        tPlanszaRozmiar= new JTextField("");
        tPlanszaRozmiar.setBounds(490, 50, 30, 20);
        add(tPlanszaRozmiar);


        lKoordynatyTytul = new JLabel("Startowe polozenie trzeciego gracza");
        lKoordynatyTytul.setBounds(330, 100, 220, 20);
        add(lKoordynatyTytul);

        lStartKord3X = new JLabel("Polozenie X (od 1):");
        lStartKord3X.setBounds(370, 130, 140,20);
        add(lStartKord3X);
        tStartKord3X = new JTextField("");
        tStartKord3X.setBounds(490,130,30,20);
        add(tStartKord3X);

        lStartKord3Y = new JLabel("Polozenie Y (od 1):");
        lStartKord3Y.setBounds(370,160,110,20);
        add(lStartKord3Y);
        tStartKord3Y = new JTextField("");
        tStartKord3Y.setBounds(490,160,30,20);
        add(tStartKord3Y);

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

        timer = new Timer(3000, new ActionListener() {
            int i = 0;
            @Override
            public void actionPerformed(ActionEvent z) {
                plansza.WykonajTure();
                planszaGUI.aktualizacjaGUI();
                i++;
                if(i == 50)
                {
                    plansza.getZapis().zamnkniecie();
                    JOptionPane.showMessageDialog(null,"Symulacja zakonczona sukcesem. Wygral gracz: " + ktoWygral(plansza.getGracze())+".");
                    timer.stop();
                }
            }
        });
    }


    /**
     * ActionListener przypisany do przycisku{@link MojeOkno#potwierdz}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == potwierdz) {
            try{
                    Parsowanie();
                    Kostka kostka = new Kostka(scianyKroliki, scianyOwce, scianySwinie, scianyKrowy, scianyKonie);
                    plansza = new Plansza(x, y, koordynatyX, koordynatyY, kostka);
                    tworzenieRamkiZPlansza();
                    planszaGUI = new PlanszaGUI(plansza);
                    planszaRamka.add(planszaGUI);
                    timer.start();
                } catch(NumberFormatException f){JOptionPane.showMessageDialog(null,"Bledne dane. Sprobuj jeszcze raz", "Bledne dane", JOptionPane.ERROR_MESSAGE);}
                catch(IllegalArgumentException z)  {JOptionPane.showMessageDialog(null,"Bledne dane. Sprobuj jeszcze raz", "Bledne dane",JOptionPane.ERROR_MESSAGE);}
            }




        }


    public static void main(String[] args){
        MojeOkno okno = new MojeOkno();
        okno.setVisible(true);


    }

    /**
     * Parsowanie danych wprowadzonych przez uzytkownika
     */
    private void Parsowanie(){
        x = Integer.parseInt(tPlanszaRozmiar.getText());
        y = Integer.parseInt(tPlanszaRozmiar.getText());
        koordynatyX = new int[3];
        koordynatyY = new int[3];
        koordynatyX[0] = Integer.parseInt(tStartKord1X.getText())-1;
        koordynatyY[0] = Integer.parseInt(tStartKord1Y.getText())-1;
        koordynatyX[1] = Integer.parseInt(tStartKord2X.getText())-1;
        koordynatyY[1] = Integer.parseInt(tStartKord2Y.getText())-1;
        koordynatyX[2] = Integer.parseInt(tStartKord3X.getText())-1;
        koordynatyY[2] = Integer.parseInt(tStartKord3Y.getText())-1;

        scianyKroliki = Integer.parseInt(tScianyKroliki.getText());
        scianyOwce = Integer.parseInt(tScianyOwce.getText());
        scianySwinie = Integer.parseInt(tScianySwinie.getText());
        scianyKrowy = Integer.parseInt(tScianyKrowy.getText());
        scianyKonie = Integer.parseInt(tScianyKonie.getText());
    }


    /**
     * Sprawdza ktory gracz ma konto o najwiekszej wartosci
     * @param gracz tablica z wszystkimi graczami na planszy
     * @return numery graczy
     */
    private String ktoWygral(Gracz[] gracz){
        String kto = "";
        int  max = 0;
      for(int i =0 ; i < gracz.length; i++){
          if(gracz[i].wartoscKonta() >= max) max = gracz[i].wartoscKonta();
      }
      for(int i=0; i < gracz.length; i++ ) {
          if(max == gracz[i].wartoscKonta()) kto = kto + i + " ";
      }
      return kto;
    }

    /**
     * tworzy okno z wygladem planszy
     */
    private void tworzenieRamkiZPlansza(){
        planszaRamka = new JFrame("Plansza");
        planszaRamka.setSize(4000,4000);
        planszaRamka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        planszaRamka.setVisible(true);
    }

}
