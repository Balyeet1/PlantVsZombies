package org.academiadecodigo.bootcamp65.Objects.Plants;

import org.academiadecodigo.bootcamp65.Game.Game;
import org.academiadecodigo.bootcamp65.Objects.Bullet;
import org.academiadecodigo.bootcamp65.Objects.Characters;
import org.academiadecodigo.bootcamp65.gfx.simplegfx.GridImage;

public class  PlantNut extends Plant {
    private int health;
    private boolean dead;
    private int col;
    private int row;
    private double imageReduction;
    private float acc;
    private float bulletAcc = 0.3f;
    private int maxBullets = 3;

    private Bullet bullets[];
    private GridImage plantImage;

    public PlantNut(int col, int row) {
        super(col, row);
        health = 3;
        imageReduction = Game.gameSize * 0.15;
        this.col = col;
        this.row = row;
        bullets = new Bullet[3];
        createImage(PlantPictures.BasicPlant[1]);
    }
    @Override
    public void shoot() {
    }

    private int accumulator() {
        this.acc += bulletAcc;
        return (int) acc;
    }

    public void damage(int dmg) {
        if(!isDead())
            health -= dmg;

        if(health <= 0) {
            health = 0;
            setDead();
        }
    }

    @Override
    public void createImage(String source) {
        plantImage = new GridImage((int) imageReduction, row, col, source, false);
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
        plantImage.hide();
        for (int i = 0; i < bullets.length; i++) {
            if(bullets[i] != null) {
                bullets[i].destroy();
                bullets[i] = null;
            }
        }
        dead = true;
    }

    @Override
    public int getCol() {
        return col;
    }

    @Override
    public int getRow() {
        return row;
    }

    public Bullet[] getBullets() {
        return bullets;
    }

}
