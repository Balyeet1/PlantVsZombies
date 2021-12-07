package org.academiadecodigo.bootcamp65.Game;

import org.academiadecodigo.bootcamp65.pictures.Picture;
import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;

public class Menu implements MouseHandler {
    private String introSource = "src/PictureFiles/Intro.png";
    private Picture pic;
    private Mouse mouse;
    private boolean mouseClicked;

    /**
     * Our intro, image file needs to be changed for something that really suits MCs vs Zombies
     * We need to click the mouse once to skip it!
     * Could MAYBE be changed for a menu, somehow, I have no patience to do it
     * babyboss 2021
     *
     * @throws InterruptedException
     */


    public void intro() throws InterruptedException {
        mouse = new Mouse(this);
        mouse.addEventListener(MouseEventType.MOUSE_CLICKED);

        pic = new Picture(0, 0, introSource);
        pic.draw();

        while(!mouseClicked) {
            Thread.sleep(1);
        }
    }
    /**
     * removeEventListener results in ConcurrentModificationException, ignore it?
     * lol
     *
     * @param mouseEvent
     */

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        pic.delete();
        mouseClicked = true;
        //mouse.removeEventListener(MouseEventType.MOUSE_CLICKED);
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
