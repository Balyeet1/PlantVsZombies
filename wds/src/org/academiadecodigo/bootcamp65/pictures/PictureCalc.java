package org.academiadecodigo.bootcamp65.pictures;
import org.academiadecodigo.bootcamp65.Game.*;

/**
 * picture calculations? idk what else to say about this
 * /tableflip
 *
 */

public class PictureCalc {
    static final double resizeTo = Game.gameSize;

    public static double resizeX(double x) {
        return (x - resizeTo) / 2;
    }

    public static double resizeY(double y) {
        return (y - resizeTo) / 2;
    }

    public static double calculatedMove(double initialPos) {
        return (initialPos - resizeTo) / 2;
    }

}
