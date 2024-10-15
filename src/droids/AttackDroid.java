package droids;

import java.util.List;
import java.util.Random;

public class AttackDroid extends Droid {
    private static final int DAMAGE = 50;
    private static final int HEALTH = 200;
    private Random random = new Random();

    public AttackDroid(String name) {
        super(name, HEALTH, DAMAGE);
    }

    @Override
    public void attack(List<Droid> allies, List<Droid> opponents) {
        if (!opponents.isEmpty()) {
            Droid target = opponents.get(random.nextInt(opponents.size()));
            target.takeDamage(getDamage());
            System.out.println(getName() + " атакує " + target.getName() + " на " + getDamage() + " шкоди.");
        } else {
            System.out.println(getName() + " не може атакувати, немає супротивників.");
        }
    }
}
