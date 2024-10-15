package droids;

import java.util.List;

public class MassAttackDroid extends Droid {
    private static final int DAMAGE = 40;
    private static final int HEALTH = 150;

    public MassAttackDroid(String name) {
        super(name, HEALTH, DAMAGE);
    }

    @Override
    public void attack(List<Droid> allies, List<Droid> opponents) {
        if (!opponents.isEmpty()) {
            for (Droid opponent : opponents) {
                if (opponent.isAlive()) {
                    opponent.takeDamage(getDamage());
                }
            }
            System.out.println(getName() + " атакує всіх супротивників на " + getDamage() + " шкоди.");
        } else {
            System.out.println(getName() + " не може атакувати, немає супротивників.");
        }
    }
}
