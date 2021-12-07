package org.academiadecodigo.bootcamp65.Game;

import org.academiadecodigo.bootcamp65.Objects.Bullet;
import org.academiadecodigo.bootcamp65.Objects.ObjectTypes;
import org.academiadecodigo.bootcamp65.Objects.Plants.*;
import org.academiadecodigo.bootcamp65.Objects.Zombies.Zombie;
import org.academiadecodigo.bootcamp65.gfx.simplegfx.SimpleGfxGrid;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;

public class Game {
    public final static int gameSize = 150;
    public final static int PADDING = 10;
    private int cols;
    private int rows;
    private int threadSleep;
    private int sleepCount;
    private int buyableLand;
    private float acc;
    private float damageAccumulator = 0.3f;
    //private int zombieCounter = 5;

    private SimpleGfxGrid grid;
    private Menu intro;
    private GameOver gameOver;
    private Zombie[] zombies;
    private Plant[] plants;

    private float zombieSpawnChance = 0.005f;


    public Game(int cols, int rows, int threadSleep) throws InterruptedException {
        this.cols = cols;
        this.rows = rows;

        buyableLand = (int) Math.ceil((double) rows / 2) + 1;
        plants = new Plant[buyableLand * rows];

        zombies = new Zombie[(cols * rows) - (rows * 2)];
        this.threadSleep = threadSleep;

        intro = new Menu();
        gameOver = new GameOver();
        intro.intro();

        Thread.sleep(threadSleep);

        grid = new SimpleGfxGrid(cols, rows, gameSize);
        grid.init();

        Shop shop = new Shop(this, grid, grid.getWidth(), cols, rows);

        start();
    }

    /**
     * All this does so far is create 5 images of baljeets
     * Make all baljeets go from col 5 to 1
     * babyboss
     * <p>
     * TIP: Each object, for example MC or Zombie
     * will have a GridImage object inside of it
     *
     * @throws InterruptedException
     */

    public void start() throws InterruptedException {
        /*
        text = new Text(pic.getX()+10, pic.getY()-(pic.getHeight()*0.2)+10, "Gold: " + this.plantPrice);
        text.setColor(Color.BLACK);
        text.grow(10,7);
        text.draw();
         */

        while (!gameOver.isOver()) {

            Thread.sleep(200);

            difficultyIncrease();
            spawnZombies();
            checkCollision();
            plantsShoot();
            bulletCollision();
            moveBullets();
            moveZombies();

        }

    }

    private void difficultyIncrease() {
        sleepCount += 200;
        if (sleepCount >= 10000) {
            sleepCount = 0;
            zombieSpawnChance += 0.025f;
            System.out.println("zombie spawn chance - " + zombieSpawnChance);
        }
    }

    private void spawnZombies() {
        for (int i = 1; i <= cols; i++) {
            boolean occupied = false;

            for (int j = 0; j < zombies.length; j++) {
                if (zombies[j] != null) {
                    if (zombies[j].getCol() == i && zombies[j].getRow() == rows) {
                        occupied = true;
                        continue;
                    }
                }
            }

            if (!occupied) {
                if (zombieSpawnChance > Math.random()) {
                    for (int k = 0; k < zombies.length; k++) {
                        if (zombies[k] == null || zombies[k].isDead()) {
                            zombies[k] = new Zombie(i, rows);
                            return;
                        }
                    }
                }
            }
        }
    }
    /*
    private void spawnZombies() {
        for (int i = 1; i <= this.cols; i++) {
            boolean occupied = false;

            for (int j = 0; j < this.zombies.length; j++) {
                if (this.zombies[j] != null) {
                    if (this.zombies[j].getCol() == i && this.zombies[j].getRow() == rows) {
                        occupied = true;
                        continue;
                    }
                }
            }

            if (!occupied && zombieSpawnChance < 0.1) {
                for (int k = 0; k < this.zombies.length; k++) {
                    if (this.zombies[k] == null || this.zombies[k].isDead()) {
                        this.zombies[k] = new Zombie(i, rows);
                        System.out.println("if1 zombie");
                        return;
                    }
                }

            } else if (!occupied && zombieSpawnChance >= 0.1) {
                double random = Math.random();
                for (int l = 0; l < this.zombies.length; l++)
                    if (random <= 0.5) {
                        this.zombies[l] = new Zombie(i, this.rows);
                        System.out.println("if2 zombie");
                    } else {
                        this.zombies[l] = new ArmouredZombie(i, this.rows);
                        System.out.println("armoured zombie");
                        return;
                    }
            }
        }
    }
*/


    private void moveZombies() {
        for (int i = 0; i < zombies.length; i++) {
            if (zombies[i] != null) {
                if (!zombies[i].isDead()) {
                    zombies[i].move();
                    if (zombies[i].getRow() == 0) {
                        gameOver.over();
                        gameOver.setOver();
                    }
                }
            }
        }
    }

    private void checkCollision() {
        for (int i = 0; i < plants.length; i++) {
            if (plants[i] != null) {
                if (plants[i].isDead()) {
                    plants[i] = null;
                    continue;
                }

                for (int j = 0; j < zombies.length; j++) {
                    if (zombies[j] != null) {
                        if (zombies[j].isDead()) {
                            zombies[j] = null;
                            continue;
                        }
                        if (plants[i].getCol() == zombies[j].getCol() && plants[i].getRow() == zombies[j].getRow() - 1) {
                            if ((int) acc >= 1) {
                                zombies[j].setAllowedToMove(false);
                                acc -= 1;
                                plants[i].damage(1);
                                if (plants[i].isDead())
                                    zombies[j].setAllowedToMove(true);
                            } else {
                                zombies[j].setAllowedToMove(false);
                                acc += damageAccumulator;
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    private void plantsShoot() {
        for (int i = 0; i < plants.length; i++) {
            if (plants[i] != null) {
                plants[i].shoot();
            }
        }
    }


    public void moveBullets() {
        Bullet[] bullets;
        for (int i = 0; i < plants.length; i++) {
            if (plants[i] != null) {
                bullets = plants[i].getBullets();
                for (int j = 0; j < bullets.length; j++) {
                    if (bullets[j] != null) {
                        if (bullets[j].isDestroyed()) {
                            bullets[j] = null;
                            continue;
                        }
                        bullets[j].move(rows);
                    }
                }
            }
        }
    }

    public void bulletCollision() {
        Bullet[] bullets;
        for (int i = 0; i < plants.length; i++) {
            if (plants[i] != null) {
                bullets = plants[i].getBullets();
                for (int j = 0; j < bullets.length; j++) {
                    if (bullets[j] != null) {
                        for (int k = 0; k < zombies.length; k++) {
                            if (zombies[k] != null) {
                                if (bullets[j].getCol() == zombies[k].getCol() && bullets[j].getRow() == zombies[k].getRow()) {
                                    zombies[k].damage(bullets[j].getDmg());
                                    /*if(zombies[k].isDead())
                                        zombieCounter--;
                                    if(zombieCounter == 0)
                                        gameOver.over();*/
                                    bullets[j].destroy();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean newPlant(int plantType, int col, int row) {
        for (int i = 0; i < plants.length; i++) {
            if (plants[i] == null) {
                switch (plantType) {
                    case 0:
                        plants[i] = new Plant(row, col);
                        System.out.println("new plant, col " + col + " row " + row);
                        return true;
                    case 1:
                        plants[i] = new PlantNut(row, col);
                        System.out.println("new plant, col " + col + " row " + row);
                        return true;
                    default:
                        return true;
                }
            }
        }
        return false;
    }

    public int getBuyableLand() {
        System.out.println(buyableLand);
        return buyableLand;
    }
}
