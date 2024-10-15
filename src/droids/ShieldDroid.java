package droids;

import java.util.List;
import java.util.Random;

public class ShieldDroid extends Droid {
    private static final int SHIELD_AMOUNT = 20;
    private boolean hasShielded = false; // Стежимо, чи було вже використано щит
    private static final int HEALTH = 180;
    private Random random = new Random();

    public ShieldDroid(String name) {
        super(name, HEALTH, 10); // Мінімальна атака після надання щита
    }

    @Override
    public void attack(List<Droid> allies, List<Droid> opponents) {
        if (!hasShielded && !allies.isEmpty()) {
            Droid ally = allies.get(random.nextInt(allies.size()));
            ally.takeDamage(-SHIELD_AMOUNT); // Від’ємна шкода для надання щита
            System.out.println(getName() + " надає щит " + ally.getName() + ", захист: " + SHIELD_AMOUNT);
            hasShielded = true;
        } else {
            if (!opponents.isEmpty()) {
                Droid target = opponents.get(random.nextInt(opponents.size()));
                target.takeDamage(getDamage());
                System.out.println(getName() + " атакує " + target.getName() + " на " + getDamage() + " шкоди.");
            } else {
                System.out.println(getName() + " не може атакувати, немає супротивників.");
            }
        }
    }
}
