package Calculadora;

public class Calculadora {

    private double valorActual = 0.0;
    private String operadorPendiente = "";
    private boolean hayOperador = false;

    public double getValorActual() {
        return valorActual;
    }

    public void setValorActual(double v) {
        valorActual = v;
    }

    public void setOperador(String op) {
        operadorPendiente = op;
        hayOperador = true;
    }

    public boolean hayOperador() {
        return hayOperador;
    }

    /**
     * Realiza la operación binaria pendiente usando 'entrada' como segundo operando.
     * Si no hay operador pendiente, guarda 'entrada' en valorActual.
     */
    public void operar(double entrada) {
        if (!hayOperador) {
            // no hay operador: simplemente guardar la entrada
            valorActual = entrada;
        } else {
            switch (operadorPendiente) {
                case "+" -> valorActual = valorActual + entrada;
                case "-" -> valorActual = valorActual - entrada;
                case "x", "*" -> valorActual = valorActual * entrada;
                case "/" -> valorActual = entrada == 0 ? Double.NaN : valorActual / entrada;
                default -> valorActual = entrada;
            }
            // limpiar operador pendiente después de operar
            operadorPendiente = "";
            hayOperador = false;
        }
    }

    public void limpiarTodo() {
        valorActual = 0.0;
        operadorPendiente = "";
        hayOperador = false;
    }
}