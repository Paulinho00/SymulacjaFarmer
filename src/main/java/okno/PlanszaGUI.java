package okno;

import glownyUczestnikSymulacji.Gracz;
import miejsceSymulacji.Plansza;

import javax.swing.*;
import java.awt.*;

/**
 * tworzy okno, z graficznym przedstawieniem polozenia obiektow na planszy
 */
public class PlanszaGUI extends JPanel {
    private int kwadratX = 50;
    private int kwadratY = 50;
    private int kwadratW = 20;
    private int kwadratH = 20;
    private final Plansza plansza;
    private JLabel[][] pola;

    /**
     * tworzy nowe okno z podgladem przekazanej tablicy
     * @param plansza plansza ktora bedzie graficznie przedstawiana w oknie
     */
    public PlanszaGUI(Plansza plansza) {
        this.plansza = plansza;
        setLayout(new GridLayout(plansza.getRozmiarX(), plansza.getRozmiarY() ));
        setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        pola = new JLabel[plansza.getRozmiarX()][plansza.getRozmiarY()];
        for(int i = 0; i <plansza.getRozmiarX(); i++){
            for(int j = 0; j <plansza.getRozmiarY(); j++) {
                JLabel label = new JLabel("",SwingConstants.CENTER);
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                label.setOpaque(true);
                label.setBackground(Color.WHITE);
                pola[i][j] = label;
                add(label);
            }
        }
        }

    /**
     * aktualizuje polozenie wszystkich obiektow na podgladzie planszy
     */
    public void aktualizacjaGUI(){
         JLabel temp;
        for(int i = 0; i < plansza.getRozmiarX(); i++){
            for(int j = 0; j < plansza.getRozmiarY(); j++){
                temp = pola[i][j];
                if(!plansza.czyZajete(i,j)){
                    temp.setBackground(Color.WHITE);
                    temp.setText("");
                    temp.setForeground(Color.BLACK);
                } else{
                    String kto = plansza.ktoJest(i,j);
                    if(kto.equals("Gracz")){
                        Gracz gracz = (Gracz) plansza.getPola(i,j);
                        temp.setBackground(new Color(255, 0, 0));
                        temp.setText("G"+gracz.getKtory());
                        temp.setForeground(Color.BLACK);
                    }
                    if (kto.equals("Lis")){
                        temp.setBackground(new Color(255,101,0));
                        temp.setText("L");
                        temp.setForeground(Color.BLACK);
                    }
                    if(kto.equals("Wilk")){
                        temp.setBackground(new Color(0, 48, 255));
                        temp.setText("W");
                        temp.setForeground(Color.BLACK);
                    }
                    if(kto.equals("Krolik")){
                        temp.setBackground(new Color(99, 96, 96));
                        temp.setText("Krl");
                        temp.setForeground(Color.WHITE);
                    }
                    if(kto.equals("Owca")){
                        temp.setBackground(new Color(255, 244, 31));
                        temp.setText("O");
                        temp.setForeground(Color.BLACK);
                    }
                    if(kto.equals("Swinia")){
                        temp.setBackground(Color.PINK);
                        temp.setText("S");
                        temp.setForeground(Color.BLACK);
                    }
                    if(kto.equals("Krowa")){
                        temp.setBackground(new Color(145, 255, 20));
                        temp.setText("Krw");
                        temp.setForeground(Color.BLACK);
                    }
                    if(kto.equals("Kon")){
                        temp.setBackground(new Color(21, 21, 21, 255));
                        temp.setText("K");
                        temp.setForeground(Color.WHITE);
                    }
                }
            }
            }
        }

}
