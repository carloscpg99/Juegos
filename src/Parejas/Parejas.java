package Parejas;
import java.util.*;

public class Parejas {

    private int[] cartas = new int[16];
    private boolean[] descubiertas = new boolean[16];

    private int parejasEncontradas = 0;
    private int intentos = 0;

    private int jugadorActual = 1;
    private int puntosJ1 = 0;
    private int puntosJ2 = 0;

    public Parejas() {
        Integer[] base = new Integer[16];
        int k = 0;
        for (int i = 1; i <= 8; i++) {
            base[k++] = i;
            base[k++] = i;
        }

        List<Integer> lista = Arrays.asList(base);
        Collections.shuffle(lista);

        for (int i = 0; i < 16; i++) {
            cartas[i] = lista.get(i);
            descubiertas[i] = false;
        }
    }

    public int getJugadorActual() {
        return jugadorActual;
    }

    public void cambiarTurno() {
        jugadorActual = (jugadorActual == 1) ? 2 : 1;
    }

    public int getValor(int pos) {
        return cartas[pos];
    }

    public boolean estaDescubierta(int pos) {
        return descubiertas[pos];
    }

    public void descubrir(int pos) {
        descubiertas[pos] = true;
    }

    public void ocultar(int pos) {
        descubiertas[pos] = false;
    }

    public boolean sonPareja(int p1, int p2) {
        return cartas[p1] == cartas[p2];
    }

    public void sumarIntento() {
        intentos++;
    }

    public int getIntentos() {
        return intentos;
    }

    public void sumarParejaJugadorActual() {
        if (jugadorActual == 1)
            puntosJ1++;
        else
            puntosJ2++;

        parejasEncontradas++;
    }

    public int getPuntosJ1() {
        return puntosJ1;
    }

    public int getPuntosJ2() {
        return puntosJ2;
    }

    public boolean juegoTerminado() {
        return parejasEncontradas == 8;
    }

}


