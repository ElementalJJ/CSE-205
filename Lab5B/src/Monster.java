public interface Monster {
    String getName();

    int getHealth();

    String attack(Monster other);

    void takeDamage(int damage);
}