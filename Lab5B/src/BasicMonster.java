public class BasicMonster implements Monster {
    private final String name;
    private int health;
    private final Weapon weapon;

    public BasicMonster(String name, int health, Weapon weapon) {
        this.name = name;
        this.health = health;
        this.weapon = weapon;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public String attack(Monster enemy) {
        int damage = (int) (Math.random() * weapon.getMaxDamage()) + 1;
        enemy.takeDamage(damage);

        return String.format("%s attacks %s with %s doing %d damage\n", this.name, enemy.getName(), this.weapon.getName(), damage);
    }

    public void takeDamage(int damage) {
        health -= damage;
    }
}
