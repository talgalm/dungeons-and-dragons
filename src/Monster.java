public class Monster extends Enemy{

    private int vision_range;
    public Monster(Position position, char tile, String name, int healthCapacity, int attack, int defence, int experience_value, int vision_range) {
        super(position, tile, name, healthCapacity, attack, defence, experience_value);
        this.vision_range=vision_range;
    }
}
