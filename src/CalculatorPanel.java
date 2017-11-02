import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class CalculatorPanel extends JPanel {
    public CalculatorPanel() {
        setLayout(new BorderLayout());

        result = 0;
        lastCommand = "=";
        start=true;

        display = new JButton("0");
        display.setEnabled(false);
        add(display, BorderLayout.NORTH);

        ActionListener insert = new InsertAction();
        ActionListener command = new CommandAction();

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4,5,10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        for (int i=0;i<4;i++){
            JButton button=new JButton();
            button.setVisible(false);
            panel.add(button);
        }

        addButton("7", insert);
        addButton("8", insert);
        addButton("9", insert);
        addButton("/", command);

        addButton("4", insert);
        addButton("5", insert);
        addButton("6", insert);
        addButton("*", command);

        addButton("1", insert);
        addButton("2", insert);
        addButton("3", insert);
        addButton("-", command);

        addButton("0", insert);
        addButton(".", insert);
        addButton("=", command);
        addButton("+", command);

        add(panel, BorderLayout.CENTER);

    }



    private void addButton(String label, ActionListener listener) {
        JButton button = new JButton(label);
        button.addActionListener(listener);

        if(listener instanceof InsertAction){
            button.setBackground(Color.BLACK);
            button.setForeground(Color.WHITE);
            button.setFont(new Font("default",Font.BOLD,24));

        }
        else {
            button.setBackground(Color.pink);
            button.setForeground(Color.WHITE);
            button.setFont(new Font("default",Font.BOLD,28));
        }
        panel.add(button);
    }



    private class InsertAction implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            String input = event.getActionCommand();
            if(start) {
                display.setText("");
                start = false;
            }
            display.setText(display.getText() + input);
        }
    }



    private class CommandAction implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            String command = event.getActionCommand();
            if(start)
            {
                if(command.equals("-"))
                {
                    display.setText(command);
                    start = false;
                }
                else lastCommand = command;
            }
            else
            {
                try {
                    calculate(Double.parseDouble(display.getText()));
                    lastCommand = command;
                    start = true;
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Неккоректный ввод числа");
                }

            }
        }
    }



    public void calculate(double value)
    {
        switch (lastCommand) {
            case "+":
                result += value;
                break;
            case "-":
                result -= value;
                break;
            case "*":
                result *= value;
                break;
            case "/":
                result /= value;
                break;
            case "=":
                result = value;
                break;
        }
        display.setText("" + result);
    }


    private JButton display;
    private JPanel panel;
    private double result;
    private String lastCommand;
    private boolean start;
}

