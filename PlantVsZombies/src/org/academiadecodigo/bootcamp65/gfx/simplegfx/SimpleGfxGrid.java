package org.academiadecodigo.bootcamp65.gfx.simplegfx;

import org.academiadecodigo.bootcamp65.Game.Game;
import org.academiadecodigo.bootcamp65.grid.Grid;
import org.academiadecodigo.bootcamp65.grid.position.GridPosition;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class SimpleGfxGrid implements Grid {
    private int cols;
    private int rows;
    private int width;
    private int height;
    private int cellSize;
    private int PADDING = Game.PADDING;
    Rectangle rect;
    Rectangle shopRect;

    public SimpleGfxGrid(int cols, int rows, int cellSize){
        this.cols = cols;
        this.rows = rows;
        this.cellSize = cellSize;
        width = (rows * cellSize);
        height = (cols * cellSize);
    }

    /**
     * @see Grid#init()
     */
    @Override
    public void init() {
        //Por imagem de fundo : Luís
        rect = new Rectangle(PADDING, PADDING, width, height);
        rect.draw();

        shopRect = rect = new Rectangle(width+(PADDING*2), PADDING, cellSize*2, cellSize*cols);
        shopRect.draw();

    }

    /**
     * @see Grid#getCols()
     */
    @Override
    public int getCols() {
        return cols;
    }

    /**
     * @see Grid#getRows()
     */
    @Override
    public int getRows() {
        return rows;
    }

    /**
     * Obtains the width of the grid in pixels
     * @return the width of the grid
     */
    public int getWidth() {
        return width;
    }

    /**
     * Obtains the height of the grid in pixels
     * @return the height of the grid
     */
    public int getHeight() {
        return height;
    }

    /**
     * Obtains the grid X position in the SimpleGFX canvas
     * @return the x position of the grid
     */
    public int getX() {
        return rect.getX();
    }

    /**
     * Obtains the grid Y position in the SimpleGFX canvas
     * @return the y position of the grid
     */
    public int getY() {
        return rect.getY();
    }

    /**
     * Obtains the pixel width and height of a grid position
     * @return
     */
    public int getCellSize() {
        return cellSize;
    }

    /**
     * @see Grid#makeGridPosition()
     */
    @Override
    public GridPosition makeGridPosition() {
        //return new SimpleGfxGridPosition(PADDING + (int) (Math.random() * (width-PADDING)), (int) (Math.random() * (height+PADDING)) - PADDING, this);
        //return new SimpleGfxGridPosition(PADDING + (int) (Math.random() * (cols-PADDING)), (int) (Math.random() * (rows+PADDING)) - PADDING, this);
        return new SimpleGfxGridPositionOld((int) (Math.random() * (cols)), (int) (Math.random() * (rows)) , this);
    }

    /**
     * @see Grid#makeGridPosition(int, int)
     */
    @Override
    public GridPosition makeGridPosition(int col, int row) {
        return new SimpleGfxGridPositionOld(col, row, this);
    }

    /**
     * Auxiliary method to compute the y value that corresponds to a specific row
     * @param row index
     * @return y pixel value
     */
    public int rowToY(int row) {
        throw new UnsupportedOperationException();
    }

    /**
     * Auxiliary method to compute the x value that corresponds to a specific column
     * @param column index
     * @return x pixel value
     */
    public int columnToX(int column) {
        throw new UnsupportedOperationException();
    }
}
