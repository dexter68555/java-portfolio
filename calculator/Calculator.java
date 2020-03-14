import java.awt.*;
import java.util.regex.Pattern;
import java.awt.Color;
import javax.swing.*;
import java.lang.Math;

public class Calculator {

    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_HEIGHT = 600;
    private static final int BUTTON_WIDTH = 80; // Button width
    private static final int BUTTON_HEIGHT = 70; // Button height
    private static final int MARGIN_X = 20;
    private static final int MARGIN_Y = 60;

    private JFrame window; // Jframe window for calculator
    private JTextField inText; // Main display panel
	//All the button for calculator
    private JButton btnC, btnSquare, btnMod, btnDiv, btn7, btn8, btn9,
            btnMul, btn4, btn5, btn6, btnSub, btn1, btn2, btn3, btnAdd, btnPoint, btn0, btnEqual, btnRoot, btnPower, btnLog, btnSwitch;
    private char opt = ' '; // Save the operator
    private boolean go = true; // For calculate with Opt != (=)
    private boolean addWrite = true; // Racordé des Nombres dans l'Affichage
    private double val = 0; // Save value typed for calculation
    private boolean isScientificMode = false;


    private Calculator() {

    	window = new JFrame("Calculator");
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT); // Set the width and the Height of the window
        window.setLocationRelativeTo(null); // Move Window To Center

        // Button fonts set to be Times New Roman
        Font btnFont = new Font("Times New Roman", Font.PLAIN, 28);
        Font smallTxtBtnFont = new Font("Times New Roman", Font.PLAIN, 24);

        btnSwitch = new JButton();
        btnSwitch.setBounds(20, 20, 140, 30);
        btnSwitch.setText("More Function");
        btnSwitch.setBackground(Color.BLUE.darker());
        btnSwitch.setForeground(Color.WHITE);
        btnSwitch.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSwitch.addActionListener(event -> onShowScientificMode());
        window.add(btnSwitch);

		int j = -1;
        int k = -1;
        int[] x = {MARGIN_X, MARGIN_X + 90, 200, 290, 380};
        int[] y = {MARGIN_Y, MARGIN_Y + 100, MARGIN_Y + 180, MARGIN_Y + 260, MARGIN_Y + 340, MARGIN_Y + 420};

        inText = new JTextField("0");
        inText.setBounds(x[0], y[0], 350, 70);
        inText.setEditable(false);
        inText.setBackground(Color.WHITE);
        inText.setFont(new Font("Times New Roman", Font.PLAIN, 33));
        window.add(inText);

		//set the reset button
        btnC = new JButton("C");
        btnC.setBounds(x[0], y[1], BUTTON_WIDTH, BUTTON_HEIGHT);
        btnC.setFont(btnFont);
        btnC.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnC.addActionListener(event -> {
            repaintFont();
            inText.setText("0");
            opt = ' ';
            val = 0;
        });
        window.add(btnC);

		//set the square button
        btnSquare = new JButton("x\u00B2");
        btnSquare.setBounds(x[1], y[1], BUTTON_WIDTH, BUTTON_HEIGHT);
        btnSquare.setFont(btnFont);
        btnSquare.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSquare.addActionListener(event -> {
            repaintFont();
            String str = inText.getText();
			int str_to_int = Integer.parseInt(str);
			int square = str_to_int * str_to_int;
			inText.setText("" + square);
        });
        window.add(btnSquare);

		//set modulus button
        btnMod = new JButton("%");
        btnMod.setBounds(x[2], y[1], BUTTON_WIDTH, BUTTON_HEIGHT);
        btnMod.setFont(btnFont);
        btnMod.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnMod.addActionListener(event -> {
            repaintFont();
            if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                if (go) {
                    val = calc(val, inText.getText(), opt);
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    opt = '%';
                    go = false;
                    addWrite = false;
                }
        });
        window.add(btnMod);

		//set divide button
        btnDiv = new JButton("/");
        btnDiv.setBounds(x[3], y[1], BUTTON_WIDTH, BUTTON_HEIGHT);
        btnDiv.setFont(btnFont);
        btnDiv.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDiv.addActionListener(event -> {
            repaintFont();
            if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                if (go) {
                    val = calc(val, inText.getText(), opt);
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    opt = '/';
                    go = false;
                    addWrite = false;
                } else {
                    opt = '/';
                }
        });
        window.add(btnDiv);

		//set 7 button
        btn7 = new JButton("7");
        btn7.setBounds(x[0], y[2], BUTTON_WIDTH, BUTTON_HEIGHT);
        btn7.setFont(btnFont);
        btn7.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn7.addActionListener(event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("7");
                } else {
                    inText.setText(inText.getText() + "7");
                }
            } else {
                inText.setText("7");
                addWrite = true;
            }
            go = true;
        });
        window.add(btn7);

		//set 8 button
        btn8 = new JButton("8");
        btn8.setBounds(x[1], y[2], BUTTON_WIDTH, BUTTON_HEIGHT);
        btn8.setFont(btnFont);
        btn8.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn8.addActionListener(event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("8");
                } else {
                    inText.setText(inText.getText() + "8");
                }
            } else {
                inText.setText("8");
                addWrite = true;
            }
            go = true;
        });
        window.add(btn8);

		//set 9 button
        btn9 = new JButton("9");
        btn9.setBounds(x[2], y[2], BUTTON_WIDTH, BUTTON_HEIGHT);
        btn9.setFont(btnFont);
        btn9.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn9.addActionListener(event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("9");
                } else {
                    inText.setText(inText.getText() + "9");
                }
            } else {
                inText.setText("9");
                addWrite = true;
            }
            go = true;
        });
        window.add(btn9);

		//set multiply button
        btnMul = new JButton("*");
        btnMul.setBounds(x[3], y[2], BUTTON_WIDTH, BUTTON_HEIGHT);
        btnMul.setFont(btnFont);
        btnMul.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnMul.addActionListener(event -> {
            repaintFont();
            if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                if (go) {
                    val = calc(val, inText.getText(), opt);
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    opt = '*';
                    go = false;
                    addWrite = false;
                } else {
                    opt = '*';
                }
        });
        window.add(btnMul);

		//set 4 button
        btn4 = new JButton("4");
        btn4.setBounds(x[0], y[3], BUTTON_WIDTH, BUTTON_HEIGHT);
        btn4.setFont(btnFont);
        btn4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn4.addActionListener(event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("4");
                } else {
                    inText.setText(inText.getText() + "4");
                }
            } else {
                inText.setText("4");
                addWrite = true;
            }
            go = true;
        });
        window.add(btn4);

		//set 5 button
        btn5 = new JButton("5");
        btn5.setBounds(x[1], y[3], BUTTON_WIDTH, BUTTON_HEIGHT);
        btn5.setFont(btnFont);
        btn5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn5.addActionListener(event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("5");
                } else {
                    inText.setText(inText.getText() + "5");
                }
            } else {
                inText.setText("5");
                addWrite = true;
            }
            go = true;
        });
        window.add(btn5);

		//set 0 button
        btn6 = new JButton("6");
        btn6.setBounds(x[2], y[3], BUTTON_WIDTH, BUTTON_HEIGHT);
        btn6.setFont(btnFont);
        btn6.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn6.addActionListener(event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("6");
                } else {
                    inText.setText(inText.getText() + "6");
                }
            } else {
                inText.setText("6");
                addWrite = true;
            }
            go = true;
        });
        window.add(btn6);

		//set minus button
        btnSub = new JButton("-");
        btnSub.setBounds(x[3], y[3], BUTTON_WIDTH, BUTTON_HEIGHT);
        btnSub.setFont(btnFont);
        btnSub.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSub.addActionListener(event -> {
            repaintFont();
            if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                if (go) {
                    val = calc(val, inText.getText(), opt);
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }

                    opt = '-';
                    go = false;
                    addWrite = false;
                } else {
                    opt = '-';
                }
        });
        window.add(btnSub);

		//set 1 button
        btn1 = new JButton("1");
        btn1.setBounds(x[0], y[4], BUTTON_WIDTH, BUTTON_HEIGHT);
        btn1.setFont(btnFont);
        btn1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn1.addActionListener(event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("1");
                } else {
                    inText.setText(inText.getText() + "1");
                }
            } else {
                inText.setText("1");
                addWrite = true;
            }
            go = true;
        });
        window.add(btn1);

		//set 2 button
        btn2 = new JButton("2");
        btn2.setBounds(x[1], y[4], BUTTON_WIDTH, BUTTON_HEIGHT);
        btn2.setFont(btnFont);
        btn2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn2.addActionListener(event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("2");
                } else {
                    inText.setText(inText.getText() + "2");
                }
            } else {
                inText.setText("2");
                addWrite = true;
            }
            go = true;
        });
        window.add(btn2);

		//set 3 button
        btn3 = new JButton("3");
        btn3.setBounds(x[2], y[4], BUTTON_WIDTH, BUTTON_HEIGHT);
        btn3.setFont(btnFont);
        btn3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn3.addActionListener(event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("3");
                } else {
                    inText.setText(inText.getText() + "3");
                }
            } else {
                inText.setText("3");
                addWrite = true;
            }
            go = true;
        });
        window.add(btn3);

		//set plus button
        btnAdd = new JButton("+");
        btnAdd.setBounds(x[3], y[4], BUTTON_WIDTH, BUTTON_HEIGHT);
        btnAdd.setFont(btnFont);
        btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAdd.addActionListener(event -> {
            repaintFont();
            if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                if (go) {
                    val = calc(val, inText.getText(), opt);
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    opt = '+';
                    go = false;
                    addWrite = false;
                } else {
                    opt = '+';
                }
        });
        window.add(btnAdd);

		//set dot button for float number
        btnPoint = new JButton(".");
        btnPoint.setBounds(x[0], y[5], BUTTON_WIDTH, BUTTON_HEIGHT);
        btnPoint.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
        btnPoint.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPoint.addActionListener(event -> {
            repaintFont();
            if (addWrite) {
                inText.setText(inText.getText() + ".");
            } else {
                inText.setText("0.");
                addWrite = true;
            }
            go = true;
        });
        window.add(btnPoint);

		//set 0 button
        btn0 = new JButton("0");
        btn0.setBounds(x[1], y[5], BUTTON_WIDTH, BUTTON_HEIGHT);
        btn0.setFont(btnFont);
        btn0.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn0.addActionListener(event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("0");
                } else {
                    inText.setText(inText.getText() + "0");
                }
            } else {
                inText.setText("0");
                addWrite = true;
            }
            go = true;
        });
        window.add(btn0);

		//set = button
        btnEqual = new JButton("=");
        btnEqual.setBounds(x[2], y[5], 2 * BUTTON_WIDTH + 10, BUTTON_HEIGHT);
        btnEqual.setFont(btnFont);
        btnEqual.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEqual.addActionListener(event -> {
            if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                if (go) {
                    val = calc(val, inText.getText(), opt);
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    opt = '=';
                    addWrite = false;
                }
        });
        window.add(btnEqual);

		//set square root button
        btnRoot = new JButton("√");
        btnRoot.setBounds(x[4], y[1], BUTTON_WIDTH, BUTTON_HEIGHT);
        btnRoot.setFont(btnFont);
        btnRoot.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRoot.addActionListener(event -> {
            if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                if (go) {
                    val = Math.sqrt(Double.parseDouble(inText.getText()));
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    opt = '√';
                    addWrite = false;
                }
        });
        btnRoot.setVisible(false);
        window.add(btnRoot);

		//set power button
        btnPower = new JButton("x\u207f");
        btnPower.setBounds(x[4], y[2], BUTTON_WIDTH, BUTTON_HEIGHT);
        btnPower.setFont(smallTxtBtnFont);
        btnPower.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPower.addActionListener(event -> {
            repaintFont();
            if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                if (go) {
                    val = calc(val, inText.getText(), opt);
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    opt = '^';
                    go = false;
                    addWrite = false;
                } else {
                    opt = '^';
                }
        });
        btnPower.setVisible(false);
        window.add(btnPower);

		//set log button
        btnLog = new JButton("log");
        btnLog.setBounds(x[4], y[3], BUTTON_WIDTH, BUTTON_HEIGHT);
        btnLog.setFont(smallTxtBtnFont);
        btnLog.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLog.addActionListener(event -> {
            if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                if (go) {
                    val = Math.log(Double.parseDouble(inText.getText()));
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    opt = 'l';
                    addWrite = false;
                }
        });
        btnLog.setVisible(false);
        window.add(btnLog);

		//remove java logo with a blank logo
		//change the image path to be your image path
		ImageIcon icon = new ImageIcon("icon.png");
        Image image = icon.getImage();
        window.setIconImage(image);
		
        window.setLayout(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // If Click into The Red Button => End The Processus
        window.setVisible(true);
        
    }
    
	//calculate function for +, -, *, %, ^
    private double calc(double x, String input, char opt) {
        inText.setFont(inText.getFont().deriveFont(Font.BOLD));
        double y = Double.parseDouble(input);
        if (opt == '+') {
            return x + y;
        } else if (opt == '-') {
            return x - y;
        } else if (opt == '*') {
            return x * y;
        } else if (opt == '/') {
            return x / y;
        } else if (opt == '%') {
            return x % y;
        } else if (opt == '^') {
            return Math.pow(x, y);
        }
        inText.setFont(inText.getFont().deriveFont(Font.PLAIN));
        return y;
    }

	//reset font
    private void repaintFont() {
        inText.setFont(inText.getFont().deriveFont(Font.PLAIN));
    }

	//function to toggle extra button for scientific mode
    private void onShowScientificMode() {
        if (isScientificMode) {
            window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
            btnRoot.setVisible(false);
            btnPower.setVisible(false);
            btnLog.setVisible(false);
            isScientificMode = false;
        } else {
            window.setSize(WINDOW_WIDTH + 80, WINDOW_HEIGHT);
            btnRoot.setVisible(true);
            btnPower.setVisible(true);
            btnLog.setVisible(true);
            isScientificMode = true;
        }
    }

        

	//main function to call calculator
    public static void main(String[] args) {
    	
    	new Calculator();
    }
}
