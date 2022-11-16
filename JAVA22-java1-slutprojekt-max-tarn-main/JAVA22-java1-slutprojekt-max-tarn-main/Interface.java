import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;


class Interface {
    
    private static JPanel printDay(LocalDate printThisDay, LocalDate date){

        //To get the current days name
        DayOfWeek dayOfWeek = printThisDay.getDayOfWeek();
        String stringDayOfWeek = dayOfWeek.name();

        //To get the current month
        Month month = printThisDay.getMonth();
        String stringMonth = month.name();

        //to get the current dayofmonth, current date of the current month
        int dayOfMonth = printThisDay.getDayOfMonth();

        //panel for all of the components of the day
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(200,550));

        //label for the day of week (monday, tuesday, wed... etc)
        JLabel topLabel = new JLabel(stringDayOfWeek);

        //label for the month and for the date of the month (November 12, December 18 etc)
        JLabel dateLabel = new JLabel(stringMonth + " " + dayOfMonth);

        //the activity list of the day
        JTextArea contentArea = new JTextArea("TO-DO:");
        //sets the size of the activity list
        contentArea.setPreferredSize(new Dimension(150,400));
        //textArea are by default editable, this makes it not editable
        contentArea.setEditable(false);

        //textfield for the input of the activity
        JTextField textField = new JTextField("");
        //sets size
        textField.setColumns(14);

        //with button listener this adds the text written in textField to the textArea
        JButton btn = new JButton("Add this activity");

        //edit text buttons, on and off
        JButton btnEditOn = new JButton("Edit On");
        JButton btnEditOff = new JButton("Edit Off");


        //add new change to txt area
        addBtnListener(textField, btn, contentArea);

        //change edit state of txtarea
        addBtnListener(btnEditOn, contentArea);
        addBtnListener(contentArea, btnEditOff);

        //adds all elements of the day to the JPanel
        panel.add(topLabel);
        panel.add(dateLabel);
        panel.add(contentArea);
        panel.add(textField);
        panel.add(btn);
        panel.add(btnEditOn);
        panel.add(btnEditOff);

        //Makes blue border around current day, only around current day
        if(printThisDay.getDayOfWeek() == date.getDayOfWeek()){
            Border border = BorderFactory.createLineBorder(Color.BLUE);
            panel.setBorder(border);
        }
        
        //the whole day is now complete
        //this returns the day panel, where everything is 
        return panel;
    }

    //actionlistener for adding activity to the textarea
    private static void addBtnListener(JTextField textField, JButton btn, JTextArea txtArea){
        ActionListener btnListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String labelContains =  txtArea.getText() + "\n";
                txtArea.setText(labelContains + textField.getText());
                textField.setText("");
            }
        };
        btn.addActionListener(btnListener);
    }

    //actionListener for edit-on button
    private static void addBtnListener(JButton btn, JTextArea txtArea){
        ActionListener btnListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){    
                txtArea.setEditable(true); 
            }
        };
        btn.addActionListener(btnListener);
    }

    //actionlistener for edit-off button
    private static void addBtnListener(JTextArea txtArea,JButton btn){
        ActionListener btnListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){    
                txtArea.setEditable(false);
            }
        };
        btn.addActionListener(btnListener);
    }
    
    //prints all the days fo the week
    public static JFrame printWeek(int x, int y, LocalDate date, JFrame frame){
        //sets size of the JFrame
        frame.setSize(x,y);
        //JFrame closes on exit
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //to make look pretty
        FlowLayout flowLay = new FlowLayout();
        //panel where all the days are in
        JPanel panel = new JPanel();

        //isMonday is true if LocalDate monday is monday, else == false
        boolean isMonday = false;

        //how many days are needed to get to monday when compared to LocalDate date
        int daysToSubtract = 0;

        //this will be the monday of the same week of LocalDate date
        LocalDate monday = null;

        //makes LocalDate monday the monday of the same week of LocalDate date
        while(isMonday == false){
            monday = date.minusDays(daysToSubtract);
            daysToSubtract++;
            if (monday.getDayOfWeek() == DayOfWeek.MONDAY){
                isMonday = true;
            }
        }
        //LocalDate monday's date is now monday of the same week of the LocalDate date
        //this is done to print out the days in the correct order, starting from monday


        //prints out all the days, starting from monday of the same week as LocalDate date , ==> LocalDate monday
        for(int i = 1; i <= 7; i++){
            panel.add(printDay(monday, date));
            
            //every time panel.add() is run LocalDate monday get cycled to the next day, seven times ==> a week
            monday = monday.plusDays(1);
        }
        

        //sets layout
        panel.setLayout(flowLay);

        //adds panel of all the days to the frame
        frame.add(panel);

        //returns the frame to main 
        return frame;
    } 
}
