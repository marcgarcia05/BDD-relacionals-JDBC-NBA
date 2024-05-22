package Vista;

import java.util.Scanner;

public class MainView {
    private Scanner scanner;

    public MainView() {
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("Menú:");
        System.out.println("0. Salir");
        System.out.println("1. Listar jugadores por equipo");
        System.out.println("2. Mostrar Estadisticas de un Jugador");
        System.out.println("3. Listar partidos por equipo");
        System.out.println("4. -");
        System.out.println("5. -");
        System.out.println("6. -");
        System.out.println("7. -");
        System.out.println("8. -");
        System.out.println("9. -");
        // Aquí se añadirán más opciones en el futuro
    }

    public int getOption() {
        System.out.print("Elige una opción: ");
        return scanner.nextInt();
    }
}
