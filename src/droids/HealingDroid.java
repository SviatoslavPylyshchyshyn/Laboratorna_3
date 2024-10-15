
package droids;

import java.util.List;

public class HealingDroid extends Droid {
    private static final int HEAL_AMOUNT = 30;
    private static final int HEALTH = 120;

    public HealingDroid(String name) {
        super(name, HEALTH, 0); // Не має завдавати шкоди, тому damage = 0
    }

    @Override
    public void attack(List<Droid> allies, List<Droid> opponents) {
        for (Droid ally : allies) {
            if (ally.isAlive()) {
                ally.takeDamage(-HEAL_AMOUNT); // Від’ємна шкода для зцілення
                System.out.println(getName() + " зцілює " + ally.getName() + " на " + HEAL_AMOUNT + " здоров'я.");
            }
        }
    }
}
