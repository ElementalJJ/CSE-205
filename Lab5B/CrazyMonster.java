public class CrazyMonster implements Monster {
    private int health;
    private int attackStrength;
    private int damageCount = 0;

    public CrazyMonster(int health) {
        this.health = health;
        this.attackStrength = 1;
    }

    public String getName() {
        String[] chars = {"!", "@", "#", "$", "%", "^", "&", "*", "?"};
        StringBuilder name = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            int index = (int) (Math.random() * 8);
            name.append(chars[index]);
        }

        return name.toString();
    }

    public int getHealth() {
        return health;
    }

    public String attack(Monster enemy) {
        int damage = (int) (Math.random() * this.attackStrength) + 1;
        enemy.takeDamage(damage);

        return String.format("%s attacks %s doing %d damage\n", this.getName(), enemy.getName(), damage);
    }

    public void takeDamage(int damage) {
        health -= damage;
        damageCount++;

        if (damageCount % 2 == 0) {
            attackStrength += damage;
        }
    }
}
