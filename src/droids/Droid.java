package droids;

import java.util.List;

public abstract class Droid {
    private String name;
    private int health;
    private int damage;

    public Droid(String name, int health, int damage) {
        this.name = name;
        this.health = health;
        this.damage = damage;
    }

    // Абстрактний метод для атаки, який буде реалізований у дочірніх класах
    public abstract void attack(List<Droid> allies, List<Droid> opponents);

    // Метод для отримання шкоди
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;  // Не дозволяємо здоров'ю бути менше 0
        }
        System.out.println(name + " отримав " + damage + " шкоди. Залишилося здоров'я: " + health);
    }

    public boolean isAlive() {
        return health > 0;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }
}

