import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        // Створюємо головне вікно
        JFrame f = new JFrame("Калькулятор");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(300, 400);

        // Панель для елементів
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());

        // Поле для вводу
        JTextField a = new JTextField();
        a.setHorizontalAlignment(JTextField.RIGHT);
        p.add(a, BorderLayout.NORTH);

        // Панель для кнопок
        JPanel b = new JPanel();
        b.setLayout(new GridLayout(4, 4, 5, 5));

        // Масив кнопок
        String[] c = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        // Змінні для обчислень
        final String[] firstNum = {""};
        final String[] operator = {""};
        final boolean[] isOperatorPressed = {false};

        for (String t : c) {
            JButton k = new JButton(t);
            b.add(k);

            k.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String text = k.getText();

                    if ("0123456789.".contains(text)) {
                        if (isOperatorPressed[0]) {
                            a.setText("");
                            isOperatorPressed[0] = false;
                        }
                        a.setText(a.getText() + text);
                    } else if ("/*-+".contains(text)) {
                        firstNum[0] = a.getText();
                        operator[0] = text;
                        isOperatorPressed[0] = true;
                    } else if ("=".equals(text)) {
                        String secondNum = a.getText();
                        double result = 0;

                        try {
                            double num1 = Double.parseDouble(firstNum[0]);
                            double num2 = Double.parseDouble(secondNum);

                            switch (operator[0]) {
                                case "+": result = num1 + num2; break;
                                case "-": result = num1 - num2; break;
                                case "*": result = num1 * num2; break;
                                case "/": result = num1 / num2; break;
                            }

                            a.setText(String.valueOf(result));
                        } catch (Exception ex) {
                            a.setText("Error");
                        }
                    }
                }
            });
        }

        p.add(b, BorderLayout.CENTER);
        f.add(p);

        f.setVisible(true);
    }
}
