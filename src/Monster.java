public class Monster extends Enemy{

    private int vision_range;
    public Monster(Position position, char tile, String name, int healthCapacity, int attack, int defence, int experience_value, int vision_range) {
        super(position, tile, name, healthCapacity, attack, defence, experience_value);
        this.vision_range=vision_range;
    }

    public int getVision_range() {
        return vision_range;
    }

    public void setVision_range(int vision_range) {
        this.vision_range = vision_range;
    }
    public Position Move(Position pos)
    {
        int movement = -1;
        if (GetPosition().Range(pos) < vision_range)
        {

            int dx = (GetPosition().getX()-pos.getX());
            int dy = (GetPosition().getY()-pos.getY());
            if (dx > dy)
            {
                if (dx > 0)
                {
                    movement = 1;
                }
                else  movement = 2;

            }
            else
            {
                if (dy > 0)
                {
                    movement = 3;
                }
                else  movement = 4;
            }

        }
        else{
            movement = (int)(Math.random()*3);
        }
        return switch (movement) {
            case 0 -> new Position(GetPosition().getX() - 1, GetPosition().getY());
            case 1 -> new Position(GetPosition().getX() + 1, GetPosition().getY());
            case 2 -> new Position(GetPosition().getX(), GetPosition().getY() + 1);
            case 3 -> new Position(GetPosition().getX(), GetPosition().getY() - 1);
            default -> null;
        };
    }
}
