package Triqui;

import javax.swing.*;
import java.awt.*;

public class TriquiGUI extends JFrame {

    private JButton[] botones = new JButton[9];
    private Triqui juego = new Triqui();

    public TriquiGUI() {
        setTitle("Triqui");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            botones[i] = new JButton("");
            int pos = i;

            botones[i].addActionListener(e -> {
                if (juego.jugar(pos)) {
                    botones[pos].setText(juego.getValor(pos));

                    String ganador = juego.verificarGanador();
                    if (ganador != null) {
                        JOptionPane.showMessageDialog(this, "Ganó: " + ganador);
                        dispose();
                    } else if (juego.tableroLleno()) {
                        JOptionPane.showMessageDialog(this, "Empate");
                        dispose();
                    }
                }
            });

            add(botones[i]);
        }

        setVisible(true);
    }
}
