package org.academiadecodigo.bootcamp65.Objects;

import org.academiadecodigo.bootcamp65.Game.Game;
import org.academiadecodigo.bootcamp65.Objects.Zombies.ZombiePictures;
import org.academiadecodigo.bootcamp65.gfx.simplegfx.GridImage;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Bullet {
    private String source = "src/PictureFiles/pea.png";
    private int dmg;
    private int col;
    private int row;
    private double imageReduction = Game.gameSize * 0.375;
    private boolean destroyed;

    private GridImage bulletImage;

    public Bullet(int col, int row) {
        dmg = 1;

        this.col = col;
        this.row = row;
        createImage(source);
    }

    public void move(int rows) {
        row++;
        if(row >= rows+1) {
            destroy();
            return;
        }
        bulletImage.moveBullet(row, col);
    }

    public void createImage(String source) {
        bulletImage = new GridImage((int) imageReduction, row, col, source, true);

    }

    public int getCol() {
        return col;
    }

    public int getRow () {
        return row;
    }

    public int getDmg () {
        return dmg;
    }

    public void destroy() {
        bulletImage.hide();
        destroyed = true;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

}
