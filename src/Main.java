import java.util.Scanner;
import javax.swing.*;
public class Main{
    public static void main(String[] args){
        int opcion=0;
        Scanner leer = new Scanner(System.in);

        do{
            System.out.println("      Bienvenido al menú      ");
            System.out.println("Elige una opción");
            System.out.println("1). Triqui");
            System.out.println("2). Parejas");
            System.out.println("3). Ahorcado");
            System.out.println("4). Calculadora");
            System.out.println("5). Salir del menú");
            opcion = leer.nextInt();

            switch(opcion){
                case 1:
                    new Triqui.TriquiGUI();
                    break;
                case 2: System.out.println(" Has elegido: Parejas");

                    break;
                case 3:
                    new Ahorcado.AhorcadoGUI();
                    break;
                case 4: System.out.println(" Has elegido: Calculadora");

                    break;

            }
        }while(opcion != 5);
        System.out.println("Hasta luego");
    }
}