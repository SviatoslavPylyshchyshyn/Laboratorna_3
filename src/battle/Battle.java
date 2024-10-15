package battle;

import droids.Droid;

import java.util.Arrays;
import java.util.List;

public class Battle {
    private Droid droid1;
    private Droid droid2;

    public Battle(Droid droid1, Droid droid2) {
        this.droid1 = droid1;
        this.droid2 = droid2;
    }

    public void start() {
        System.out.println("Починається бій між " + droid1.getName() + " та " + droid2.getName());

        while (droid1.isAlive() && droid2.isAlive()) {
            // Двоє дроїдів б'ються по черзі
            droid1.attack(List.of(droid1), List.of(droid2)); // droid1 атакує droid2
            if (!droid2.isAlive()) {
                break;
            }

            droid2.attack(List.of(droid2), List.of(droid1)); // droid2 атакує droid1
        }

        // Визначаємо переможця
        if (droid1.isAlive()) {
            System.out.println(droid1.getName() + " переміг!");
        } else {
            System.out.println(droid2.getName() + " переміг!");
        }
    }
}
