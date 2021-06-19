package BusinessLayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {
    private Warrior w;
    private Mage m ;
    private Rogue r;
    @BeforeEach
    void setUp() {
          w = new Warrior('@', "The Hound", 400, 20, 6, 5);
          m = new Mage('@', "Melisandre", 100, 5, 1, 300, 30, 15, 5, 6);
          r = new Rogue('@', "Bronn", 250, 35, 3, 50);
    }

    @Test
    void levelUpHealthPoints() {
        w.LevelUpHealthPoints(1);
        assertEquals(w.health.GetResourceCurrent(),410);
        m.LevelUpHealthPoints(1);
        assertEquals(m.health.GetResourceCurrent(),110);
        r.LevelUpHealthPoints(1);
        assertEquals(r.health.GetResourceCurrent(),260);
    }

    @Test
    void levelUpAttackPoints() {
        w.LevelUpAttackPoints(1);
        assertEquals(w.GetAttackPoints(),24);
        m.LevelUpAttackPoints(1);
        assertEquals(m.GetAttackPoints(),9);
        r.LevelUpAttackPoints(1);
        assertEquals(r.GetAttackPoints(),39);
    }

    @Test
    void levelUpDefensePoints() {
        w.LevelUpDefensePoints(1);
        assertEquals(w.GetDefensePoints(),7);
        m.LevelUpDefensePoints(1);
        assertEquals(m.GetAttackPoints(),5);
        r.LevelUpDefensePoints(1);
        assertEquals(r.GetAttackPoints(),35);
    }

    @Test
    void levelUp() {
        w.LevelUp();
        m.LevelUp();
        r.LevelUp();
        assertEquals(w.playerLevel,2);
        assertEquals(m.playerLevel,2);
        assertEquals(r.playerLevel,2);
    }
}