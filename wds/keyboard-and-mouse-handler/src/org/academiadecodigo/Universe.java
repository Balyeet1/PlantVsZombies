package org.academiadecodigo;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;


public class Universe {

    public static void main(String[] args) throws InterruptedException {
        Rectangle slide = new Rectangle(10, 10, 1000, 100);
        slide.setColor(Color.LIGHT_GRAY);
        slide.fill();

        Rectangle rectangle = new Rectangle(10, 10, 100, 100);
        rectangle.setColor(Color.BLUE);
        rectangle.fill();

        UniverseKeyboardHandler ukh = new UniverseKeyboardHandler(rectangle);
        UniverseMouseHandler umh = new UniverseMouseHandler(rectangle);

        for (int i = 0; i < 99; i++) {
            rectangle.translate(10,0);
            Thread.sleep(100);
        }

    }

}
