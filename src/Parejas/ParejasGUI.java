package Parejas;

import javax.swing.*;
import java.awt.*;

public class ParejasGUI extends JFrame {

    private JButton[] botones = new JButton[16];
    private Parejas juego = new Parejas();

    private int primera = -1;
    private int segunda = -1;
    private boolean bloqueado = false;

    private JLabel lblTurno = new JLabel("Turno: Jugador 1");
    private JLabel lblJ1 = new JLabel("Puntos J1: 0");
    private JLabel lblJ2 = new JLabel("Puntos J2: 0");
    private JLabel lblIntentos = new JLabel("Intentos: 0");

    public ParejasGUI() {

        setTitle("Parejas - 2 Jugadores");
        setSize(550, 620);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelTop = new JPanel(new GridLayout(2, 2));
        panelTop.add(lblTurno);
        panelTop.add(lblIntentos);
        panelTop.add(lblJ1);
        panelTop.add(lblJ2);

        JPanel panelGrid = new JPanel(new GridLayout(4, 4));

        for (int i = 0; i < 16; i++) {
            botones[i] = new JButton("?");
            int pos = i;
            botones[i].addActionListener(e -> manejarClick(pos));
            panelGrid.add(botones[i]);
        }

        setLayout(new BorderLayout());
        add(panelTop, BorderLayout.NORTH);
        add(panelGrid, BorderLayout.CENTER);
    }

    private void manejarClick(int pos) {
        if (bloqueado) return;
        if (juego.estaDescubierta(pos)) return;

        botones[pos].setText(String.valueOf(juego.getValor(pos)));

        if (primera == -1) {
            primera = pos;
            return;
        }

        if (segunda == -1 && pos != primera) {
            segunda = pos;
            bloquear();

            Timer t = new Timer(800, e -> verificarPareja());
            t.setRepeats(false);
            t.start();
        }
    }

    private void bloquear() {
        bloqueado = true;
    }

    private void desbloquear() {
        bloqueado = false;
    }

    private void verificarPareja() {

        juego.sumarIntento();

        if (juego.sonPareja(primera, segunda)) {

            juego.descubrir(primera);
            juego.descubrir(segunda);
            juego.sumarParejaJugadorActual();

            actualizarMarcadores();

            JOptionPane.showMessageDialog(this,
                    "¡Jugador " + juego.getJugadorActual() + " encontró una pareja!");

            // Jugador mantiene turno
        } else {

            // Se voltean
            botones[primera].setText("?");
            botones[segunda].setText("?");

            // Cambiar turno
            juego.cambiarTurno();
            lblTurno.setText("Turno: Jugador " + juego.getJugadorActual());
        }

        primera = -1;
        segunda = -1;
        desbloquear();

        if (juego.juegoTerminado()) {
            anunciarGanador();
        }
    }

    private void actualizarMarcadores() {
        lblIntentos.setText("Intentos: " + juego.getIntentos());
        lblJ1.setText("Puntos J1: " + juego.getPuntosJ1());
        lblJ2.setText("Puntos J2: " + juego.getPuntosJ2());
    }

    private void anunciarGanador() {

        int j1 = juego.getPuntosJ1();
        int j2 = juego.getPuntosJ2();

        String msg =
                "Puntos J1: " + j1 + "\n" +
                        "Puntos J2: " + j2 + "\n\n";

        if (j1 > j2) msg += "¡GANADOR: Jugador 1!";
        else if (j2 > j1) msg += "¡GANADOR: Jugador 2!";
        else msg += "¡EMPATE!";

        JOptionPane.showMessageDialog(this, msg);
    }
}



