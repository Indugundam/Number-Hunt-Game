import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;


public class NumberGame {
    static int choice;
    static JTextField jtfInput = new JTextField();
    static JTextField jtfOutput = new JTextField();
    static JFrame jfMain = new JFrame();
    static JTextField jtfHint = new JTextField();
    static JTextField jtfAttempts = new JTextField();
    static JLabel jlHint = new JLabel();
    static JLabel jlAttempts = new JLabel();
    static int randomNumber;
    static int count;

    public static void main(String[] args) {

        JFrame jfAbout = new JFrame();
        jfAbout.setTitle("Number Hunt 1.0 - Indu");
        jfAbout.setResizable(false);
        jfAbout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfAbout.setLayout(null);
        jfAbout.setSize(400, 400);

        JLabel jlAbout = new JLabel("About the Game");
        jlAbout.setBounds(130, 10, 150, 40);
        jlAbout.setForeground(Color.MAGENTA);
        jfAbout.add(jlAbout);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(20, 40, 350, 80);
        textArea.setText("Number Hunt is a fun game where you try to guess " +
                "a random number between 1 and 50. You have five chances to find the number." +
                " After each guess, you'll get a hint if your guess is too     high or too low. " +
                "It's a great way to test your luck and guessing    skills, whether playing alone " +
                "or with friends.");
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(null);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        jfAbout.add(scrollPane, BorderLayout.CENTER);
        jfAbout.add(textArea);

        JLabel jlHints = new JLabel("Instructions");
        jlHints.setBounds(130, 120, 150, 40);
        jlHints.setForeground(Color.GREEN);
        jfAbout.add(jlHints);


        JTextArea jtaHints = new JTextArea();
        jtaHints.setSize(320, 150);
        jtaHints.setLocation(20, 160);
        jtaHints.setEditable(false);
        jtaHints.setText("\tFeedBack Messages\n\n\"Correct guess!!!\" for an exact match.\n" +
                "\"You are almost there\" if the guess is within 5 units of the number.\n" +
                "\"Too low\" for guesses below the number by more than 5 units.\n" +
                "\"Too high\" for guesses above the number by more than 5 units.");
        jtaHints.setLineWrap(true);
        jtaHints.setWrapStyleWord(true);
        JScrollPane scrollPane2 = new JScrollPane(jtaHints);
        jfAbout.add(scrollPane2, BorderLayout.CENTER);
        jfAbout.add(jtaHints);

        JButton jbStart = new JButton("Start");
        jbStart.setSize(80, 25);
        jbStart.setLocation(140, 320);
        jfAbout.add(jbStart);

        jfAbout.setVisible(true);
        jfMain.setVisible(false);

        jbStart.addActionListener((e) -> {
            jfAbout.setVisible(false);
            jfMain.setVisible(true);
        });

        jfMain.setTitle("Number Hunt 1.0 - Indu");
        jfMain.setResizable(false);
        jfMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfMain.setLayout(null);
        jfMain.setSize(400, 400);

        JLabel jlInput = new JLabel("Guess the number");
        jlInput.setBounds(20, 30, 120, 30);
        jfMain.add(jlInput);

        jtfInput.setSize(95, 25);
        jtfInput.setLocation(140, 35);
        jfMain.add(jtfInput);

        JLabel jlOutput = new JLabel("Number to hunt");
        jlOutput.setBounds(20, 80, 100, 30);
        jfMain.add(jlOutput);

        jlAttempts.setBounds(20, 125, 100, 30);
        jlAttempts.setText("No.of Attempts");
        jfMain.add(jlAttempts);

        jtfOutput.setSize(95, 25);
        jtfOutput.setLocation(140, 85);
        jtfOutput.setEnabled(false);
        jfMain.add(jtfOutput);

        jtfAttempts.setSize(30, 25);
        jtfAttempts.setLocation(140, 130);
        jtfAttempts.setEnabled(false);
        jfMain.add(jtfAttempts);

        jtfHint.setSize(200, 25);
        jtfHint.setLocation(140, 170);
        jtfHint.setEnabled(false);
        jfMain.add(jtfHint);

        jlHint.setBounds(20, 170, 100, 30);
        ImageIcon icon = new ImageIcon("C:\\Users\\indug\\OneDrive\\Pictures\\hint.png");
        Image originalImage = icon.getImage();
        Image resizedImage = originalImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        jlHint.setIcon(resizedIcon);
        jlHint.setText("Hint");
        jfMain.add(jlHint);


        JButton button = new JButton("Retry");
        button.setSize(80, 25);
        button.setLocation(100, 240);
        jfMain.add(button);
        //jfMain.setVisible(true);

        button.addActionListener((e) -> {
            count = 0;
            jtfHint.setText("");
            jtfInput.setText("");
            jtfOutput.setText("");
            jtfAttempts.setText("");
            retryGame();
        });

        jtfInput.addActionListener((e) -> {
            if (count == 0) {
                startGame();
            } else {
                process();
            }
        });

    }

    private static void startGame() {
        Random random = new Random();
        randomNumber = random.nextInt(50) + 1;
        count = 0;
        process();
    }

    private static void process() {
        try {
            int number = Integer.parseInt(jtfInput.getText());
            if (number >= 0 && number <= 50) {
                if (number == randomNumber) {
                    jtfHint.setText("Correct guess!!!");
                } else if ((number > randomNumber - 5) && (number < randomNumber + 5)) {
                    jtfHint.setText("You are almost there");
                } else if (number < randomNumber - 5) {
                    jtfHint.setText("Too low");
                } else if (number > randomNumber + 5) {
                    jtfInput.setText("");
                    jtfHint.setText("Too high");
                }
                count += 1;
                if (count >= 5 || number == randomNumber) {
                    jtfOutput.setText(String.valueOf(randomNumber));
                    jtfInput.setText("");
                    jtfInput.setEnabled(false);
                    generateScore(count, number, randomNumber);
                }
            } else {
                JOptionPane.showMessageDialog(jfMain, "Please enter a number in the range of 1 to 50");
            }
        } catch (Exception ee) {
            JOptionPane.showMessageDialog(jfMain, "Give proper number");
        }
        jtfAttempts.setText(String.valueOf(count));

    }

    private static void retryGame() {
        //count = 0;
        jtfInput.setEnabled(true);
        jtfInput.addActionListener((e) -> {
            startGame();
        });
    }

    private static void generateScore(int count, int number, int randomNumber) {
        if (count == 1) {
            JOptionPane.showMessageDialog(jfMain, "Well done!!!\nYour score:10");
        } else if (count == 2) {
            JOptionPane.showMessageDialog(jfMain, "Your score: 9");
        } else if (count == 3) {
            JOptionPane.showMessageDialog(jfMain, "Your score: 8");
        } else if (count == 4) {
            JOptionPane.showMessageDialog(jfMain, "Your score: 6");
        } else if (count == 5) {
            if (number == randomNumber) {
                JOptionPane.showMessageDialog(jfMain, "Your score: 5");
            } else {
                jtfOutput.setText(String.valueOf(randomNumber));
                JOptionPane.showMessageDialog(jfMain, "Too many attempts\nYour score: 0\n Better luck next time");
            }
        }
    }
}


