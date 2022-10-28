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
        DayOfWeek dayOfWeek = printThisDay.getDayOfWeek();
        String stringDayOfWeek = dayOfWeek.name();
        Month month = printThisDay.getMonth();
        String stringMonth = month.name();
        int dayOfMonth = printThisDay.getDayOfMonth();
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(200,500));
        JLabel topLabel = new JLabel(stringDayOfWeek);
        JLabel dateLabel = new JLabel(stringMonth + " " + dayOfMonth);
        JTextArea contentArea = new JTextArea("TO-DO:");
        contentArea.setPreferredSize(new Dimension(150,400));
        contentArea.setEditable(false);
        JTextField textField = new JTextField("");
        textField.setColumns(14);
        JButton btn = new JButton("Add this activity");
        addBtnListener(textField, btn, contentArea);
        panel.add(topLabel);
        panel.add(dateLabel);
        panel.add(contentArea);
        panel.add(textField);
        panel.add(btn);
        if(printThisDay.getDayOfWeek() == date.getDayOfWeek()){
            Border border = BorderFactory.createLineBorder(Color.BLUE);
            panel.setBorder(border);
        }
        
        return panel;
    }
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
    public static JFrame printWeek(int x, int y, LocalDate date, JFrame frame){
        frame.setSize(x,y);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FlowLayout flowLay = new FlowLayout();
        JPanel panel = new JPanel();
        boolean isMonday = false;
        int daysToSubtract = 0;
        LocalDate monday = null;
        while(isMonday == false){
            monday = date.minusDays(daysToSubtract);
            daysToSubtract++;
            if (monday.getDayOfWeek() == DayOfWeek.MONDAY){
                isMonday = true;
            }
        }
        for(int i = 1; i <= 7; i++){
            panel.add(printDay(monday, date));
            
            monday = monday.plusDays(1);
        }
        
        panel.setLayout(flowLay);
        frame.add(panel);
        return frame;
    } 
}
