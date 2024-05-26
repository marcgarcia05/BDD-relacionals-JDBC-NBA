package Vista;

import java.util.Scanner;


public class TeamView {

    private static Scanner scanner;

    public TeamView() {
        this.scanner = new Scanner(System.in);
    }
    public static String getNewPlayerTeam(){
        System.out.print("Introdueix el nom de l'equip del nou jugador: ");
        return scanner.nextLine();
    }


    public String getTeamName() {
        System.out.print("Introdueix el nom del equip: ");
        return scanner.nextLine();
    }

    public String getNewCityName() {
        System.out.print("Introdueix el nou nom de la ciutat: ");
        return scanner.nextLine();
    }

    public static String getNewTeam() {
        System.out.print("Introdueix el nom del nou equip: ");
        return scanner.nextLine();
    }

}

