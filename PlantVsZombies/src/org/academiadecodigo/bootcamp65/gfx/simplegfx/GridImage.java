package org.academiadecodigo.bootcamp65.gfx.simplegfx;

import org.academiadecodigo.bootcamp65.Game.*;
import org.academiadecodigo.bootcamp65.pictures.Picture;
import org.academiadecodigo.bootcamp65.pictures.PictureCalc;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;

public class GridImage {
    private Picture pic;
    private Text text;
    private int gameSize = Game.gameSize;
    private int padding = Game.PADDING;
    private int imageReduction;
    private int reductionSafety = 25;
    private static int playerMoney = 150;
    private int plantPrice;
    private static Text showMoneyPlayer;

    /**
     * Makes a new image and places it in a col and row, image is resized to comply with gameSize
     * All images are resized equally (unless imageReduction is added), some may get distorted.
     *
     * babyboss can't make comments for shit (╯°□°）╯︵ ┻━┻
     *
     * @param col col where we want the image to appear
     * @param row row where we want the image to appear
     * @param source source of the image file
     */

    public GridImage(int imageReduction, int col, int row, String source, boolean bullet) {
        if(imageReduction != 0)
            this.imageReduction = imageReduction + reductionSafety;

        pic = new Picture(col*gameSize, row*gameSize, source); // Magic 0 as both of those parameters are overwritten with move(); straight away
        pic.grow(-PictureCalc.resizeX(pic.getWidth())-imageReduction, -PictureCalc.resizeY(pic.getHeight())-imageReduction);
        if(bullet) {
            moveBullet(col, row);
            return;
        }
        move(col, row);
        showMoney();
    }

    public void move(int col, int row) {
        //pic.translate(-pic.getMaxX()+(col*gameSize)+padding-imageReduction, -pic.getMaxY()+(row*gameSize)+padding);
        pic.translate(-pic.getMaxX()+(col*gameSize)+padding, -pic.getMaxY()+(row*gameSize)+padding);
        pic.draw();
    }

    public void moveBullet(int col, int row) {
        pic.translate(-pic.getMaxX()+(col*gameSize)+padding-imageReduction-(gameSize*0.125), -pic.getMaxY()+(row*gameSize)+padding-(gameSize*0.325));
        pic.draw();
    }

    public void moveShop(int col, int row, int width) {
        //pic.translate(-pic.getMaxX()+(col*gameSize)+(padding*2)-imageReduction+width, -pic.getMaxY()+(row*gameSize)+padding);
        pic.translate(-pic.getMaxX()+(col*gameSize)+(padding*2)+width, -pic.getMaxY()+(row*gameSize)+padding);
        pic.draw();
    }

    public void createText(int price) {
        this.plantPrice = price;
        text = new Text(pic.getX()+10, pic.getY()-(pic.getHeight()*0.2)+10, "Gold: " + this.plantPrice);
        text.setColor(Color.BLACK);
        text.grow(10,7);
        text.draw();
    }

    public void showMoney() {
        if(this.showMoneyPlayer != null){
            this.showMoneyPlayer.delete();
        }
        this.showMoneyPlayer = new Text(gameSize, gameSize,"Your Gold: "+getPlayerMoney());
        this.showMoneyPlayer.setColor(Color.BLACK);
        this.showMoneyPlayer.grow(10,7);
        this.showMoneyPlayer.draw();
    }

    public int getPlantPrice() {
        return plantPrice;
    }

    public static int getPlayerMoney() {
        return playerMoney;
    }

    public static void setPlayerMoney(int moneyTaken) {
        playerMoney += moneyTaken;
    }

    public void show() {
        pic.draw();
    }

    public void hide() {
        pic.delete();
    }

    public int getY() {
        return pic.getY();
    }

    public int getX() {
        return pic.getX();
    }

    public int getMaxY() {
        return pic.getMaxY();
    }

    public int getMaxX() {
        return pic.getMaxX();
    }
}
