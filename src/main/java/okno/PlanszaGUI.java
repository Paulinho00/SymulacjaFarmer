package okno;

import miejsceSymulacji.Plansza;

import javax.swing.*;
import java.awt.*;

public class PlanszaGUI extends JPanel {
    private int kwadratX = 50;
    private int kwadratY = 50;
    private int kwadratW = 20;
    private int kwadratH = 20;
    private final Plansza plansza;
    private JLabel[][] pola;


    public PlanszaGUI(Plansza plansza) {
        this.plansza = plansza;
        setLayout(new GridLayout(plansza.getRozmiarX(), plansza.getRozmiarY() ));
        setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        pola = new JLabel[plansza.getRozmiarX()][plansza.getRozmiarY()];
        for(int i = 0; i <plansza.getRozmiarX(); i++){
            for(int j = 0; j <plansza.getRozmiarY(); j++) {
                JLabel label = new JLabel("");
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                label.setOpaque(true);
                label.setBackground(Color.WHITE);
                pola[i][j] = label;
                add(label);
            }
        }
        }
        public void aktualizacjaGUI(){
         JLabel temp;
        for(int i = 0; i < plansza.getRozmiarX(); i++){
            for(int j = 0; j < plansza.getRozmiarY(); j++){
                temp = pola[i][j];
                if(!plansza.czyZajete(i,j)){
                    temp.setBackground(Color.WHITE);
                } else{
                    String kto = plansza.ktoJest(i,j);
                    if(kto.equals("Gracz")){
                        temp.setBackground(Color.BLACK);
                    }
                    if (kto.equals("Lis")){
                        temp.setBackground(new Color(255,101,0));
                    }
                    if(kto.equals("Wilk")){
                        temp.setBackground(Color.BLUE);
                    }
                    if(kto.equals("Krolik")){
                        temp.setBackground(Color.DARK_GRAY);
                    }
                    if(kto.equals("Owca")){
                        temp.setBackground(Color.YELLOW);
                    }
                    if(kto.equals("Swinia")){
                        temp.setBackground(Color.PINK);
                    }
                    if(kto.equals("Krowa")){
                        temp.setBackground(Color.MAGENTA);
                    }
                    if(kto.equals("Kon")){
                        temp.setBackground(Color.GREEN);
                    }
                }
            }
            }
        }

}
