import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Weapon basicWeapon = new Weapon("Basic Weapon", 10);
        BasicMonster basic = new BasicMonster("Jared", 100, basicWeapon);
        MultiAttackMonster multi = new MultiAttackMonster("Samantha", 100, basicWeapon, 4);
        CrazyMonster crazy = new CrazyMonster(100);

        ArrayList<Monster> monsters = new ArrayList<>();
        monsters.add(basic);
        monsters.add(multi);
        monsters.add(crazy);

        while (monsters.size() >= 2) {
            int currentIndex = (int) (Math.random() * monsters.size());

            Monster current = monsters.remove(currentIndex);

            int enemyIndex = (int) (Math.random() * monsters.size());

            Monster enemy = monsters.get(enemyIndex);

            monsters.add(current);

            System.out.println(current.attack(enemy));

            monsters.removeIf(monster -> monster.getHealth() <= 0);
        }

        System.out.printf("%s is the winner!", monsters.get(0).getName());
    }
}
