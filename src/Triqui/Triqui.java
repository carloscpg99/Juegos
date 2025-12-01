package Triqui;
public class Triqui {
    private String[] tablero = new String[9];
    private boolean turnoX = true;

    public Triqui() {
        for (int i = 0; i < 9; i++)
            tablero[i] = "";
    }

    public boolean jugar(int pos) {
        if (!tablero[pos].equals(""))
            return false;

        tablero[pos] = turnoX ? "X" : "O";
        turnoX = !turnoX;
        return true;
    }

    public String getValor(int pos) {
        return tablero[pos];
    }

    public String verificarGanador() {
        int[][] lineas = {
                {0,1,2}, {3,4,5}, {6,7,8},
                {0,3,6}, {1,4,7}, {2,5,8},
                {0,4,8}, {2,4,6}
        };

        for (int[] l : lineas) {
            if (!tablero[l[0]].equals("") &&
                    tablero[l[0]].equals(tablero[l[1]]) &&
                    tablero[l[1]].equals(tablero[l[2]])) {
                return tablero[l[0]]; // ganador
            }
        }

        return null; // sin ganador
    }

    public boolean tableroLleno() {
        for (String s : tablero)
            if (s.equals("")) return false;
        return true;
    }
}
