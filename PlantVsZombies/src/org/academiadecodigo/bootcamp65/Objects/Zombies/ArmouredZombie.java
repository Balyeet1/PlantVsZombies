package org.academiadecodigo.bootcamp65.Objects.Zombies;

public class ArmouredZombie extends Zombie {

    private int armour;
    private int zombieWorth;

    public ArmouredZombie(int col, int row) {
        super(col, row);
        this.zombieWorth = 20;
        this.armour = 3;
        createImage(ZombiePictures.ArmouredZombie[(int) (Math.random() * ZombiePictures.ArmouredZombie.length)]);
    }

    public void armourDamage(int dmg) {
        armour -= dmg;
        if(armour <= 0) {
            super.damage(dmg);
        }
    }
    public int getZombieWorth(){
        return zombieWorth;
    }
}