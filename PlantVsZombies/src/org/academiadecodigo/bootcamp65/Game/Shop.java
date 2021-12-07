package org.academiadecodigo.bootcamp65.Game;

import org.academiadecodigo.bootcamp65.gfx.simplegfx.GridImage;
import org.academiadecodigo.bootcamp65.gfx.simplegfx.SimpleGfxGrid;
import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;

public class Shop implements MouseHandler {
    private double mouseX;
    private double mouseY;
    private int cols;
    private int rows;
    private int buyableRows;

    private Game game;
    private GridImage[] shopImages = new GridImage[8];
    private double imageReduction = Game.gameSize * 0.15;

    private int selectedPlant = -1;

    private String[] sources = {
            "src/PictureFiles/plant.png",
            "src/PictureFiles/NutPlant.png"
    };

    Mouse m;

    public Shop(Game game, SimpleGfxGrid simpleGfxGrid, int width, int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        this.game = game;
        createPictures(width);
        m = new Mouse(this);
        m.addEventListener(MouseEventType.MOUSE_CLICKED);
        m.addEventListener(MouseEventType.MOUSE_MOVED);
    }


    private int mouseClicks = 0;

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        System.out.println(mouseClicks++);
        for (int i = 0; i < shopImages.length; i++) {
            if (shopImages[i] != null) {
                if (mouseX >= shopImages[i].getX() && mouseY >= shopImages[i].getY()
                        && mouseX <= shopImages[i].getMaxX() && mouseY <= shopImages[i].getMaxY()) {
                    if(GridImage.getPlayerMoney() >= shopImages[i].getPlantPrice() ) {
                        GridImage.setPlayerMoney(-shopImages[i].getPlantPrice());
                        selectedPlant = i;
                        System.out.println("plant selected");
                        return;
                    }else{
                        System.out.println("Not enough money");
                    }
                }
            }
        }

        if(selectedPlant != -1) {
            for (int i = 1; i < game.getBuyableLand(); i++) {
                for (int j = 1; j < cols+1; j++) {
                    if (mouseX <= i * Game.gameSize + Game.PADDING && mouseY <= j * Game.gameSize + Game.PADDING) {
                        while(!game.newPlant(selectedPlant, i, j));
                        selectedPlant = -1;
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        String[] split;
        String[] secondSplit;
        String[] thirdSplit;

        split = e.toString().split("=");
        secondSplit = split[1].split(",");
        mouseX = Double.parseDouble(secondSplit[0]);
        thirdSplit = split[2].split(",");
        mouseY = Double.parseDouble(thirdSplit[0]);


        // mouse corrections
        mouseX -= 8;
        mouseY -= 31;
        //System.out.println(mouseX);

    }

    /**
     * use a for when we have multiple images, accompany it with an array of prices >:)
     *
     * @param width
     */

    public void createPictures(int width) {
        shopImages[0] = new GridImage((int) imageReduction, 1, 1, sources[0], false);
        shopImages[0].moveShop(1, 1, width);
        shopImages[0].createText(50);
        shopImages[1] = new GridImage((int) imageReduction, 2, 1, sources[1], false);
        shopImages[1].moveShop(1, 2, width);
        shopImages[1].createText(25);
    }
}
