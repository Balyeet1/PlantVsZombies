package org.academiadecodigo.bootcamp65.Game;

import org.academiadecodigo.bootcamp65.pictures.Picture;
import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;

public class GameOver implements MouseHandler {
    private String introSource = "src/PictureFiles/DarkSoulsDed.png";
    private Picture pic;
    private Mouse mouse;
    private boolean mouseClicked;

    private boolean isOver;

    /**
     * Game Over page, pretty self explanoty
     * If you click it the program kills itself
     * Add a retry button, some texts saying points and stuff
     *
     * @throws InterruptedException
     */

    public void over() {
        mouse = new Mouse(this);
        mouse.addEventListener(MouseEventType.MOUSE_CLICKED);

        pic = new Picture(0, 0, introSource);
        pic.draw();
    }

    public boolean isOver() {
        return isOver;
    }

    public void setOver() {
        isOver = true;
    }

    /**
     * removeEventListener results in ConcurrentModificationException, ignore it?
     * lol
     *
     * @param mouseEvent
     */

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        System.exit(1);
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
