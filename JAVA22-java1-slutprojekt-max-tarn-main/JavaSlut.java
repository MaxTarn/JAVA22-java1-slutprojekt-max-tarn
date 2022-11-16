import java.time.LocalDate;

import javax.swing.JFrame;


public class JavaSlut{

    public static void main(String[] args) {
        JFrame frame = new JFrame("Kalender");
        System.out.print("\033[H\033[2J"); //clears console

        //size of the frame
        int x = 1500;
        int y = 700;

        //the date that will be the basis of the printWeek 
        LocalDate date = LocalDate.now();

        //frame now contains the week, printed out
        frame = Interface.printWeek(x, y, date, frame);

        //why is it default invisible ????
        frame.setVisible(true);
    }
}