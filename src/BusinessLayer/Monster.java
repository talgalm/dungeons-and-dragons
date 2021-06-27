package BusinessLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Monster extends Enemy {

    private int visionRange;

    public Monster(char tile, String name, int healthCapacity, int attack, int defence, int experience_value, int vision_range) {
        super(tile, name, healthCapacity, attack, defence, experience_value);
        this.visionRange = vision_range;
    }


    public Position Move(Position pos, List<Enemy> bribedEnemies) {
        double distanceFromPlayer = position.Range(pos);
        return ((distanceFromPlayer < visionRange) ? moveToPlayer(pos) : SearchForBribeOrRandom(bribedEnemies));
    }

    private Position moveToPlayer(Position playerPos) {
        int dx = position.getX() - playerPos.getX();
        int dy = position.getY() - playerPos.getY();
        Character where = ((Math.abs(dx) > Math.abs(dy)) ? ((dx > 0) ? 'a' : 'd') : ((dy > 0) ? 'w' : 's'));
        return MoveTo(where);
    }

    private Position SearchForBribeOrRandom(List<Enemy> bribedEnemies){
        try {
            Position LocatedBribedEnemy = bribedEnemies.stream().filter(t -> t.GetPosition().Range(GetPosition()) < visionRange).findAny().get().GetPosition();
            return moveToPlayer(LocatedBribedEnemy);
        }
        catch(Exception e){
            return randomMove();
        }
    }
    private Position randomMove() {
        char[] moveArray = {'a', 's', 'd', 'w'};
        int rnd = new Random().nextInt(moveArray.length);
        return MoveTo(moveArray[rnd]);
    }

    public Position MoveAsBribed(ArrayList<Enemy> enemies, Player player) {
        Double bestRange = null;
        Enemy closestEnemy = null;
        for (Enemy enemy : enemies) {
            if (bestRange == null) {
                bestRange = enemy.GetPosition().Range(GetPosition());
                closestEnemy = enemy;
            } else {
                Double tmpRange = GetPosition().Range(enemy.GetPosition());
                if (tmpRange < bestRange && !enemy.IsTrap()) {
                    bestRange = tmpRange;
                    closestEnemy = enemy;
                }
            }
        }
        if (closestEnemy.GetPosition() == null)
            return player.GetPosition();
        else
            return moveToPlayer(closestEnemy.GetPosition());
    }

    public void AcceptBribe(){
        isBribed = true;
        character = 'H';
        name = "bribed " + name;
        visionRange += 3;
    }

    @Override
    public String getDescription() {
        return GetName() + "  Health: " +getHealth().GetResourceCurrent() + "/" +getHealth().GetResourceMax()
                + "  Attack:" +GetAttackPoints()
                + "  Defense:" +GetDefensePoints()
                + "  Experience Value: " + GetExperience()
                + "  Vision Range: " + visionRange;
    }

    @Override
    public void TickUp() { }
    public Boolean IsTrap(){ return false;}

}