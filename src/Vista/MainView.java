package Vista;

import java.util.Scanner;

public class MainView {
    private Scanner scanner;

    public MainView() {
        this.scanner = new Scanner(System.in);
    }

    //Menu Principal
    public void displayMenu() {
        System.out.println("Menú:");
        System.out.println("0. Sortir");
        System.out.println("1. Llistar jugadors per equip");
        System.out.println("2. Mostrar estadistiques d'un jugador");
        System.out.println("3. Llistar partits per equip");
        System.out.println("4. Afegir un jugador nou a un equip");
        System.out.println("5. Traspassar jugador a un altre equip");
        System.out.println("6. Afegir partits/estadistiques a la BBDD");
        System.out.println("7. Modificar estadistica d'un jugador");
        System.out.println("8. Retirar Jugador");
        System.out.println("9. Modificar nom de franquicia");
    }

    public int getOption() {
        System.out.print("Escull una opció: ");
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Has d'introduir un numero!");
        }
        return 0;
    }

    public int getUpdateOption() {
        System.out.println("Opcions disponibles:");
        System.out.println("1. Actualitzar estadístiques de jugadors desde un fitxer.");
        System.out.println("2. Actualitzar estadístiques de partits desde un fitxer.");
        System.out.print("Escull una opció: ");
        return scanner.nextInt();
    }
}
