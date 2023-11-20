public class MultiAttackMonster implements Monster {
    private final String name;
    private int health;
    private final Weapon weapon;
    private final int numberOfAttacks;

    public MultiAttackMonster(String name, int health, Weapon weapon, int attacks) {
        this.name = name;
        this.health = health;
        this.weapon = weapon;
        this.numberOfAttacks = attacks;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public String attack(Monster enemy) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numberOfAttacks; i++) {
            int damage = (int) (Math.random() * weapon.getMaxDamage()) + 1;
            enemy.takeDamage(damage);
            result.append(String.format("%s attacks %s with %s doing %d damage\n", this.name, enemy.getName(), this.weapon.getName(), damage));
        }

        return result.toString();
    }

    public void takeDamage(int damage) {
        health -= damage;
    }
}
