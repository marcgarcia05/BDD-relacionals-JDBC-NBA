package Controlador;

import DAO.MatchDAO;
import DAO.TeamDAO;
import Model.Match;
import Vista.MatchView;

import java.sql.SQLException;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class MatchController {
    private MatchView matchView;
    private MatchDAO matchDAO;

    public MatchController() {
        this.matchView = new MatchView();
        this.matchDAO = new MatchDAO();
    }

    public void listMatchesByTeam(){
        String teamName = matchView.getTeamName();
        String seasonYear = matchView.getSeasonYear();

        // Verificar si l'equip existeix
        if (!matchDAO.isValidTeam(teamName)) {
            System.out.println("El equip " + teamName + " no existeix.");
            return;
        }

        // Verificar si l'any es vàlid
        if (!isValidSeasonYear(seasonYear)) {
            System.out.println("L'any de la temporada no és vàlid.");
            return;
        }

            List<Match> matches = matchDAO.listMatchesByTeamAndSeason(teamName, seasonYear);
            matchView.showMatches(matches);
    }

    private boolean isValidSeasonYear(String seasonYear) {
        // Lista de años de temporada válidos
        String[] anysValids = {"2018-19", "2019-20", "2020-21", "2021-22", "2022-23", "2023-24"};

        // Verificar si l'any està en la llista
        for (String year : anysValids) {
            if (year.equals(seasonYear)) {
                return true;
            }
        }
        return false;
    }


    public void updateMatchesFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 5) {
                    String gameId = parts[0];
                    String team1 = parts[1];
                    String points1Str = parts[2];
                    String team2 = parts[3];
                    String points2Str = parts[4];

                    if (matchDAO.matchExists(gameId)) {
                        System.out.println("El partit amb l'ID " + gameId + " ja existeix.");
                        continue;
                    }

                    try {
                        int points1 = Integer.parseInt(points1Str);
                        int points2 = Integer.parseInt(points2Str);

                        if (!matchDAO.isValidTeam(team1)) {
                            System.out.println("El equip " + team1 + " no existeix.");
                            continue;
                        }

                        if (!matchDAO.isValidTeam(team2)) {
                            System.out.println("El equip " + team2 + " no existeix.");
                            continue;
                        }

                        matchDAO.updateMatchData(gameId, team1, points1);
                        matchDAO.updateMatchData(gameId, team2, points2);
                        System.out.println("Partit " + gameId + " actualitzat correctament.");

                    } catch (NumberFormatException e) {
                        System.out.println("Error en els punts del partit: " + gameId + ": no són valors vàlids.");
                    }

                } else {
                    System.out.println("Format de linea incorrecte: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

