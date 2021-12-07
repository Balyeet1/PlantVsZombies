package org.academiadecodigo.bootcamp65.Objects.Zombies;

import org.academiadecodigo.bootcamp65.Objects.Characters;
import org.academiadecodigo.bootcamp65.gfx.simplegfx.GridImage;

public class Zombie implements Characters {
    private int health = 8;
    private boolean dead = false;
    private float acc;
    private float moveAcc = 0.1f;
    private int col;
    private int row;
    private int startingCol;
    private int startingRow;
    boolean allowedToMove = true;
    private int zombieWorth;

    GridImage zombieImage;

    public Zombie(int col, int row) {
        this.zombieWorth = 15;
        this.col = col;
        this.row = row;
        startingCol = col;
        startingRow = row;
        createImage(ZombiePictures.BasicZombie[(int) (Math.random() * ZombiePictures.BasicZombie.length)]);
    }


    public void move() {
        if(allowedToMove) {
        if(row > 0) {
            if ((int) acc >= 1) {
                row -= 1;
                zombieImage.move(row, col);
                acc = acc - 1.0f;
                //MOVE
            } else accumulator();
        }
    }
    }

    private int accumulator() {
        this.acc += moveAcc;
        return (int) acc;
    }


    public void damage(int dmg) {
        if((health -= dmg) >= 0) {
            health -= dmg;
            return;
        }

        health = 0;
        setDead();
    }

    @Override
    public void createImage(String source) {
        zombieImage = new GridImage(0, row, col, source, false);
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public boolean isDead() {
        return dead;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void setImage(String source) {

    }


    @Override
    public void setDead() {
        zombieImage.hide();
        dead = true;
        GridImage.setPlayerMoney(getZombieWorth());
    }

    public int getZombieWorth(){
        return zombieWorth;
    }

    @Override
    public int getCol() {
        return col;
    }

    @Override
    public int getRow() {
        return row;
    }

    public boolean atSpawn() {
        return startingRow == row;
    }

    public void setAllowedToMove(boolean allowance) {
        allowedToMove = allowance;
    }
    public boolean canMove() {
        return allowedToMove;
    }
}
