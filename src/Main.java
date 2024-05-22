import Controlador.MainController;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        MainController mainController = new MainController();
        mainController.start();
    }
}


