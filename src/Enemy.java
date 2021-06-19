import Callbacks.DeathCallBack;
import Callbacks.MessageCallBack;

import java.util.ArrayList;
import java.util.List;
//import Callbacks.MoveCallBack;

public abstract class Enemy extends Unit {

    private int experienceValue;
    protected Boolean isBribed;
    private MessageCallBack messageCallBack;
    private DeathCallBack enemyDeathCallBack;

    public Enemy(Position position, char tile, String name, int healthCapacity, int attack, int defence,int experience_value) {
        super(position, tile, name, healthCapacity, attack, defence);
        this.experienceValue = experience_value;
        isBribed = false;
    }

    public int GetExperience(){ return experienceValue; }
    public void onKill(Player p){
        messageCallBack.Send("GAME OVER");
        p.SetCharacter('X');
    }
    public void onEnemyKillAsBribed(Enemy e){
        messageCallBack.Send("The bribed " + GetName() + "Killed " +e.GetName());
        e.onDeath();
    }

    public void onDeath(){
//        Empty newPlace = new Empty(this.GetPosition(), '.');
        enemyDeathCallBack.Call();
    }

    public void VisitedBy(Unit unit) {
        unit.accept(this);
    }

    public void accept(Player player) {
        if(!isBribed) {
            this.Combat(player);
            if (!player.IsAlive())
                onKill(player);
        }
    }
    public void accept(Enemy enemy) {
        if(this.isBribed & !enemy.isBribed | enemy.isBribed & !this.isBribed){
            Combat(enemy);
            if (!enemy.IsAlive()) {
                onEnemyKillAsBribed(enemy);
            }
        }
    }

    public abstract Position Move(Position pos, List<Enemy> bribedEnemies);

    public abstract Position MoveAsBribed(ArrayList<Enemy> enemies);

    public abstract void AcceptBribe();
}

