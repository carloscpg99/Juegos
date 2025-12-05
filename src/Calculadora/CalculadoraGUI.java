package Calculadora;

import javax.swing.*;
import java.awt.*;

public class CalculadoraGUI extends JFrame {

    private final JTextField pantalla = new JTextField("0");
    private final Calculadora calc = new Calculadora();

    private boolean nuevaEntrada = true;

    private boolean unaryPending = false;
    private String unaryOp = "";

    public CalculadoraGUI() {
        super("Calculadora");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(320, 440);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        pantalla.setEditable(false);
        pantalla.setFont(new Font("Arial", Font.BOLD, 28));
        pantalla.setHorizontalAlignment(SwingConstants.RIGHT);
        add(pantalla, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(6, 4, 5, 5));

        String[] botones = {
                "7", "8", "9", "/",
                "4", "5", "6", "x",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "%", "√", "C", "AC",
                "Salir", "", "", ""
        };

        for (String b : botones) {
            if (b.equals("")) {
                panelBotones.add(new JLabel());
                continue;
            }
            JButton btn = new JButton(b);
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.addActionListener(e -> procesar(b));
            panelBotones.add(btn);
        }

        add(panelBotones, BorderLayout.CENTER);
        setVisible(true);
    }

    private void procesar(String tecla) {

        if (tecla.matches("[0-9]")) {
            if (nuevaEntrada || pantalla.getText().equals("0")) {
                pantalla.setText(tecla);
            } else {
                pantalla.setText(pantalla.getText() + tecla);
            }
            nuevaEntrada = false;
            return;
        }

        if (tecla.equals(".")) {
            if (!pantalla.getText().contains(".")) {
                pantalla.setText(pantalla.getText() + ".");
                nuevaEntrada = false;
            }
            return;
        }

        if (tecla.equals("C")) {
            pantalla.setText("0");
            nuevaEntrada = true;
            unaryPending = false;
            unaryOp = "";
            return;
        }

        if (tecla.equals("AC")) {
            calc.limpiarTodo();
            pantalla.setText("0");
            nuevaEntrada = true;
            unaryPending = false;
            unaryOp = "";
            return;
        }

        if (tecla.equals("Salir")) {
            dispose();
            return;
        }

        if (tecla.equals("%")) {
            try {
                double valorPantalla = Double.parseDouble(pantalla.getText());
                if (!calc.hayOperador()) {
                    pantalla.setText("0");
                } else {
                    double base = calc.getValorActual();
                    double resultado = base * (valorPantalla / 100.0);
                    pantalla.setText(formatear(resultado));
                }
                nuevaEntrada = true;
                unaryPending = false;
                unaryOp = "";
            } catch (Exception e) {
                pantalla.setText("ERROR");
            }
            return;
        }

        if (tecla.equals("√")) {

            if (nuevaEntrada) {
                unaryPending = true;
                unaryOp = "√";
                pantalla.setText("0");
                nuevaEntrada = false;
                return;
            } else {

                try {
                    double valor = Double.parseDouble(pantalla.getText());
                    if (valor < 0) {
                        pantalla.setText("ERROR");
                        nuevaEntrada = true;
                        unaryPending = false;
                        unaryOp = "";
                        return;
                    }
                    double resultado = Math.sqrt(valor);
                    pantalla.setText(formatear(resultado));

                    if (!calc.hayOperador()) {
                        calc.setValorActual(resultado);
                    }

                    nuevaEntrada = true;
                    unaryPending = false;
                    unaryOp = "";

                } catch (Exception e) {
                    pantalla.setText("ERROR");
                    unaryPending = false;
                    unaryOp = "";
                }
                return;
            }
        }

        if ("+-x/".contains(tecla)) {
            try {
                double pantallaValor = Double.parseDouble(pantalla.getText());

                if (unaryPending) {
                    pantallaValor = aplicarUnary(unaryOp, pantallaValor);
                    unaryPending = false;
                    unaryOp = "";
                    pantalla.setText(formatear(pantallaValor));
                }

                if (calc.hayOperador()) {
                    calc.operar(pantallaValor);
                } else {
                    calc.setValorActual(pantallaValor);
                }

                calc.setOperador(tecla);

                pantalla.setText(formatear(calc.getValorActual()));
                nuevaEntrada = true;

            } catch (Exception e) {
                pantalla.setText("ERROR");
            }
            return;
        }

        if (tecla.equals("=")) {
            try {
                double entrada = Double.parseDouble(pantalla.getText());

                if (unaryPending) {
                    entrada = aplicarUnary(unaryOp, entrada);
                    unaryPending = false;
                    unaryOp = "";
                    pantalla.setText(formatear(entrada));
                }

                if (calc.hayOperador()) {
                    calc.operar(entrada);
                    pantalla.setText(formatear(calc.getValorActual()));
                } else {
                    calc.setValorActual(entrada);
                    pantalla.setText(formatear(calc.getValorActual()));
                }

                nuevaEntrada = true;

            } catch (Exception e) {
                pantalla.setText("ERROR");
            }
        }
    }

    private double aplicarUnary(String op, double valor) {
        return switch (op) {
            case "√" -> Math.sqrt(valor);
            default -> valor;
        };
    }

    private String formatear(double n) {
        return (Double.isNaN(n)) ? "NaN" : ((n == (long) n) ? String.valueOf((long) n) : String.valueOf(n));
    }
}