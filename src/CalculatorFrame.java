import javax.swing.*;
import java.awt.*;

public class CalculatorFrame extends JFrame {
    public CalculatorFrame() {
        setTitle("Calculator");
        setPreferredSize(new Dimension(300,400));
        CalculatorPanel panel = new CalculatorPanel();
        add(panel);
        pack();
    }
}
