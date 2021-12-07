package org.academiadecodigo.bootcamp65.gfx.simplegfx;

import org.academiadecodigo.bootcamp65.grid.GridColor;
import org.academiadecodigo.bootcamp65.grid.GridDirection;
import org.academiadecodigo.bootcamp65.grid.position.AbstractGridPosition;
import org.academiadecodigo.bootcamp65.grid.position.GridPosition;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;


/**
 * Simple graphics position
 */
public class SimpleGfxGridPositionOld extends AbstractGridPosition {

    private Rectangle rectangle;
    private int cellSize;

    /**
     * Simple graphics position constructor
     * @param grid Simple graphics grid
     */
    public SimpleGfxGridPositionOld(SimpleGfxGrid grid){
        super((int) (Math.random() * grid.getCols()), (int) (Math.random() * grid.getRows()), grid);

        throw new UnsupportedOperationException();
    }

    /**
     * Simple graphics position constructor
     * @param col position column
     * @param row position row
     * @param grid Simple graphics grid
     */
    public SimpleGfxGridPositionOld(int col, int row, SimpleGfxGrid grid) {
        super(col, row, grid);
        cellSize = grid.getCellSize();
        rectangle = new Rectangle(getCol()*cellSize+cellSize, getRow()*cellSize+cellSize, cellSize,cellSize);
        rectangle.fill();


        //throw new UnsupportedOperationException();
    }

    /**
     * @see GridPosition#show()
     */
    @Override
    public void show() {
        rectangle.fill();
    }

    /**
     * @see GridPosition#hide()
     */
    @Override
    public void hide() {
        rectangle.delete();
    }

    /**
     * @see GridPosition#moveInDirection(GridDirection, int)
     */
    @Override
    public void moveInDirection(GridDirection direction, int distance) {
        //this.hide();
        super.moveInDirection(direction, distance);
        rectangle.delete();
        rectangle = new Rectangle(getCol()*cellSize+cellSize, getRow()*cellSize+cellSize, cellSize,cellSize);
        setColor(getColor());
    }

    /**
     * @see AbstractGridPosition#setColor(GridColor)
     */
    @Override
    public void setColor(GridColor color) {
        if (color != GridColor.NOCOLOR) {
            switch (color) {
                case RED:
                    rectangle.setColor(Color.RED);
                    break;
                case BLUE:
                    rectangle.setColor(Color.BLUE);
                    break;
                case GREEN:
                    rectangle.setColor(Color.GREEN);
                    break;
                case MAGENTA:
                    rectangle.setColor(Color.MAGENTA);
                    break;
            }
            super.setColor(color);
        }
    }
}
