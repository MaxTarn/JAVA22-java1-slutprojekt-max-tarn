import java.time.LocalDate;

import javax.swing.JFrame;


public class JavaSlut{

    public static void main(String[] args) {
        JFrame frame = new JFrame("Kalender");
        System.out.print("\033[H\033[2J"); //clears console


        int x = 1500;
        int y = 700;
        LocalDate date = LocalDate.now();
        frame = Interface.printWeek(x, y, date, frame);
        frame.setVisible(true);
    }
}