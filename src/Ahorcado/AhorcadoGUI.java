package Ahorcado;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

public class AhorcadoGUI extends JFrame {

    private Ahorcado juego;
    private HashSet<Character> usadas = new HashSet<>();

    private final JLabel palabraLabel = new JLabel("");
    private final JLabel intentosLabel = new JLabel("Intentos restantes: 6");
    private final JLabel usadasLabel = new JLabel("Letras usadas: -");

    private final JTextField letraField = new JTextField(2);
    private final JButton adivinarBtn = new JButton("Adivinar");

    public AhorcadoGUI() {
        super("Ahorcado");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1));

        palabraLabel.setFont(new Font("Arial", Font.BOLD, 28));
        palabraLabel.setHorizontalAlignment(SwingConstants.CENTER);
        intentosLabel.setHorizontalAlignment(SwingConstants.CENTER);
        usadasLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panelEntrada = new JPanel();
        panelEntrada.add(new JLabel("Letra:"));
        panelEntrada.add(letraField);
        panelEntrada.add(adivinarBtn);

        add(palabraLabel);
        add(intentosLabel);
        add(usadasLabel);
        add(panelEntrada);

        adivinarBtn.addActionListener(e -> intentarLetra());
        letraField.addActionListener(e -> intentarLetra());

        setVisible(true);

        SwingUtilities.invokeLater(this::solicitarPalabra);
    }

    private void solicitarPalabra() {

        while (true) {

            String palabra = JOptionPane.showInputDialog(this, "Ingresa la palabra secreta:");

            if (palabra == null) {
                JOptionPane.showMessageDialog(this, "Debes ingresar una palabra para jugar.");
                continue;
            }

            palabra = palabra.trim();

            if (palabra.isEmpty()) {
                JOptionPane.showMessageDialog(this, "La palabra no puede estar vacia.");
                continue;
            }

            if (!palabra.matches("[a-zA-Z]+")) {
                JOptionPane.showMessageDialog(this, "Solo se permiten letras (A–Z), sin numeros ni símbolos.");
                continue;
            }

            juego = new Ahorcado(palabra);
            actualizarPantalla();
            break;
        }
    }

    private void intentarLetra() {
        String txt = letraField.getText().trim().toUpperCase();
        letraField.setText("");

        if (txt.isEmpty()) return;
        char c = txt.charAt(0);

        if (usadas.contains(c)) return;
        usadas.add(c);

        juego.intentar(c);
        actualizarPantalla();

        if (juego.estaGanado()) {
            JOptionPane.showMessageDialog(this, "¡Ganaste! La palabra era: " + juego.getPalabraSecreta());
            dispose();
        } else if (juego.estaPerdido()) {
            JOptionPane.showMessageDialog(this, "Perdiste. La palabra era: " + juego.getPalabraSecreta());
            dispose();
        }
    }

    private void actualizarPantalla() {
        palabraLabel.setText(juego.getProgreso());
        intentosLabel.setText("Intentos restantes: " + juego.getIntentos());
        usadasLabel.setText("Letras usadas: " + usadas.toString());
    }
}