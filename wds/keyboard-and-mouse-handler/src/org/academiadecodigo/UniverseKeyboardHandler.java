package org.academiadecodigo;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;


public class UniverseKeyboardHandler implements KeyboardHandler {

    private Rectangle rectangle;

    public UniverseKeyboardHandler(Rectangle rectangle){
        this.rectangle = rectangle;

        //keyboard is deaf
        Keyboard keyboard = new Keyboard(this);

        //create event
        KeyboardEvent left = new KeyboardEvent();
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        left.setKey(KeyboardEvent.KEY_LEFT);

        KeyboardEvent right = new KeyboardEvent();
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        right.setKey(KeyboardEvent.KEY_RIGHT);

        //keyboard listen to left
        keyboard.addEventListener(left);
        keyboard.addEventListener(right);
    }

    @Override
    public void keyPressed(KeyboardEvent e) {
        System.out.println("chieeeeeeeyyyyyyyy");

        if(e.getKey() == KeyboardEvent.KEY_LEFT){
            rectangle.translate(-100, 0);
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
