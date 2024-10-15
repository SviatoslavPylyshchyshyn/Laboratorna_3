package Main;

import battle.Battle;
import battle.TeamBattle;
import droids.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<Droid> droids = new ArrayList<>(); // Список доступних дроїдів

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeDroids(); // Ініціалізуємо список дроїдів

        while (true) {
            System.out.println("Виберіть опцію:");
            System.out.println("1. Бій 1 на 1");
            System.out.println("2. Командний бій");
            System.out.println("3. Переглянути результати боїв");
            System.out.println("4. Вихід");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Скидаємо буфер

            switch (choice) {
                case 1:
                    startSingleBattle(scanner);
                    break;
                case 2:
                    startTeamBattle(scanner);
                    break;
                case 3:
                    displayBattleResults();
                    break;
                case 4:
                    System.out.println("Вихід з програми.");
                    return;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }

    // Метод для ініціалізації дроїдів
    private static void initializeDroids() {
        droids.add(new AttackDroid("Атакуючий Дроїд 1"));
        droids.add(new AttackDroid("Атакуючий Дроїд 2"));
        droids.add(new MassAttackDroid("Масовий Атакуючий Дроїд 1"));
        droids.add(new MassAttackDroid("Масовий Атакуючий Дроїд 2"));
        droids.add(new HealingDroid("Дроїд Зцілення 1"));
        droids.add(new ShieldDroid("Щитовий Дроїд 1"));
    }

    // Метод для проведення бою 1 на 1
    private static void startSingleBattle(Scanner scanner) {
        System.out.println("Виберіть двох дроїдів для бою (не вибирайте дроїда зцілення):");
        displayDroidOptions();

        System.out.print("Введіть номер першого дроїда: ");
        int index1 = scanner.nextInt() - 1; // Зменшуємо на 1 для доступу до масиву
        System.out.print("Введіть номер другого дроїда: ");
        int index2 = scanner.nextInt() - 1;

        Droid droid1 = droids.get(index1);
        Droid droid2 = droids.get(index2);

        if (droid1 instanceof HealingDroid || droid2 instanceof HealingDroid) {
            System.out.println("Не можна вибрати дроїда зцілення для бою.");
            return;
        }

        Battle battle = new Battle(droid1, droid2);
        battle.start();

        // Запит на збереження результату бою у файл
        saveBattleResult(droid1, droid2);
    }

    // Метод для проведення командного бою
    private static void startTeamBattle(Scanner scanner) {
        System.out.print("Введіть кількість дроїдів у команді: ");
        int teamSize = scanner.nextInt();
        scanner.nextLine(); // Скидаємо буфер

        List<Droid> team1 = new ArrayList<>();
        List<Droid> team2 = new ArrayList<>();

        System.out.println("Створіть команду 1:");
        for (int i = 0; i < teamSize; i++) {
            System.out.println("Виберіть дроїда для команди 1:");
            displayDroidOptions();
            int index = scanner.nextInt() - 1;
            team1.add(droids.get(index));
        }

        System.out.println("Створіть команду 2:");
        for (int i = 0; i < teamSize; i++) {
            System.out.println("Виберіть дроїда для команди 2:");
            displayDroidOptions();
            int index = scanner.nextInt() - 1;
            team2.add(droids.get(index));
        }

        TeamBattle teamBattle = new TeamBattle(team1, team2);
        teamBattle.start();

        // Запит на збереження результату командного бою у файл
        saveTeamBattleResult(team1, team2);
    }

    // Метод для відображення доступних дроїдів
    private static void displayDroidOptions() {
        for (int i = 0; i < droids.size(); i++) {
            System.out.println((i + 1) + ". " + droids.get(i));
        }
    }

    // Метод для збереження результату бою 1 на 1 у файл
    private static void saveBattleResult(Droid droid1, Droid droid2) {
        String result = droid1.isAlive() ? droid1.getName() + " wins!" : droid2.getName() + " wins!";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("battle_result.txt", true))) {
            writer.write(result);
            writer.newLine();
            System.out.println("Результат бою збережено у battle_result.txt");
        } catch (IOException e) {
            System.out.println("Помилка при збереженні результату бою: " + e.getMessage());
        }
    }

    // Метод для збереження результату командного бою у файл
    private static void saveTeamBattleResult(List<Droid> team1, List<Droid> team2) {
        StringBuilder result = new StringBuilder();
        result.append("Команда 1: ");
        for (Droid droid : team1) {
            result.append(droid.getName()).append(", ");
        }
        result.append(" vs. ");
        result.append("Команда 2: ");
        for (Droid droid : team2) {
            result.append(droid.getName()).append(", ");
        }

        result.append(team1.get(0).isAlive() ? "Команда 1 wins!" : "Команда 2 wins!");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("team_battle_result.txt", true))) {
            writer.write(result.toString());
            writer.newLine();
            System.out.println("Результат командного бою збережено у team_battle_result.txt");
        } catch (IOException e) {
            System.out.println("Помилка при збереженні результату командного бою: " + e.getMessage());
        }
    }

    // Метод для відображення результатів бою з файлу
    private static void displayBattleResults() {
        System.out.println("Виберіть файл для перегляду результатів:");
        System.out.println("1. Результати боїв 1 на 1");
        System.out.println("2. Результати командних боїв");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine(); // Скидаємо буфер

        String fileName;
        if (choice == 1) {
            fileName = "battle_result.txt";
        } else if (choice == 2) {
            fileName = "team_battle_result.txt";
        } else {
            System.out.println("Невірний вибір.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println("Результати боїв:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Помилка при читанні файлу: " + e.getMessage());
        }
    }
}
