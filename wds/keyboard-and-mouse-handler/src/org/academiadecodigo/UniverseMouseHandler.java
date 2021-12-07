package org.academiadecodigo;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;

public class UniverseMouseHandler implements MouseHandler {

    private Rectangle rectangle;
    private boolean flag;

    public UniverseMouseHandler(Rectangle rectangle){

        this.rectangle = rectangle;

        Mouse mouse = new Mouse(this);
        mouse.addEventListener(MouseEventType.MOUSE_CLICKED);
        mouse.addEventListener(MouseEventType.MOUSE_MOVED);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        System.out.println(mouseEvent.getX() + " " + mouseEvent.getY());
        rectangle.setColor(flag ? Color.PINK : Color.BLUE);
        flag = !flag;
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
