package BusinessLayer;

import java.util.ArrayList;
import java.util.List;
//import Callbacks.MoveCallBack;

public abstract class Enemy extends Unit {

    private int experienceValue;
    protected Boolean isBribed;

    public Enemy(char tile, String name, int healthCapacity, int attack, int defence,int experience_value) {
        super(tile, name, healthCapacity, attack, defence);
        this.experienceValue = experience_value;
        isBribed = false;
    }

    public int GetExperience(){ return experienceValue; }

    public void onEnemyKillAsBribed(Enemy e){
        messageCallBack.Send("The bribed " + GetName() + "Killed " +e.GetName());
        e.onEnemyDeath();
    }

    public void onEnemyDeath(){
        deathCallBack.Call(); //where it will be replaced with other
    }

    public void VisitedBy(Unit unit) {
        unit.accept(this);
    }

    public void accept(Player player) {
        if(!isBribed) {
            this.Combat(player);
            if (!player.IsAlive())
                player.onDeath();
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

    public abstract Position MoveAsBribed(ArrayList<Enemy> enemies, Player player);

    public abstract void AcceptBribe();
    public abstract String getDescription();
    public abstract Boolean IsTrap();


}

