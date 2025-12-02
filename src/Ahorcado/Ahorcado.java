package Ahorcado;

public class Ahorcado {
    private String palabraSecreta;
    private StringBuilder progreso;
    private int intentos;

    public Ahorcado(String palabraSecreta) {
        this.palabraSecreta = palabraSecreta.toUpperCase();
        progreso = new StringBuilder();
        for (int i = 0; i < palabraSecreta.length(); i++) {
            progreso.append("_");
        }
        intentos = 6;
    }

    public boolean intentar(char letra) {
        letra = Character.toUpperCase(letra);
        boolean acierto = false;

        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.charAt(i) == letra) {
                progreso.setCharAt(i, letra);
                acierto = true;
            }
        }

        if (!acierto) intentos--;
        return acierto;
    }

    public String getProgreso() {
        return progreso.toString();
    }

    public int getIntentos() {
        return intentos;
    }

    public boolean estaGanado() {
        return progreso.toString().equals(palabraSecreta);
    }

    public boolean estaPerdido() {
        return intentos <= 0;
    }

    public String getPalabraSecreta() {
        return palabraSecreta;
    }
}