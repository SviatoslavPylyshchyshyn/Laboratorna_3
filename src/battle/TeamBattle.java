package battle;

import droids.Droid;

import java.util.List;
import java.util.Random;

public class TeamBattle {
    private List<Droid> team1;
    private List<Droid> team2;
    private Random random = new Random();

    public TeamBattle(List<Droid> team1, List<Droid> team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    public void start() {
        System.out.println("Починається бій між командами!");

        while (isTeamAlive(team1) && isTeamAlive(team2)) {
            for (Droid droid : team1) {
                if (droid.isAlive()) {
                    droid.attack(team1, team2); // Атакують ворогів або зцілюють союзників
                }
            }

            if (!isTeamAlive(team2)) {
                break;
            }

            for (Droid droid : team2) {
                if (droid.isAlive()) {
                    droid.attack(team2, team1); // Атакують ворогів або зцілюють союзників
                }
            }
        }

        if (isTeamAlive(team1)) {
            System.out.println("Команда 1 перемогла!");
        } else {
            System.out.println("Команда 2 перемогла!");
        }
    }

    // Метод для перевірки, чи жива команда
    private boolean isTeamAlive(List<Droid> team) {
        for (Droid droid : team) {
            if (droid.isAlive()) {
                return true;
            }
        }
        return false;
    }
}
