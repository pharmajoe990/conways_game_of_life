package au.org.random;

import java.util.ArrayList;

class CellGrid {

  private ArrayList<ArrayList<Cell>> grid;

  CellGrid(int length, int width) {
    this.grid = new ArrayList<>(length);
    int widthCounter = width;
    do {
      int lengthCounter = length;
      ArrayList<Cell> line = new ArrayList<>(length);
      do {
        line.add(new Cell(CellState.DEAD));
        lengthCounter--;
      } while(lengthCounter != 0);
      grid.add(line);
      widthCounter--;
    } while(widthCounter != 0);
  }

  int getWidth() {
    return this.grid.size();
  }

  int getLength() {
    return this.grid.get(0).size();
  }

  Cell getCellAt(int xPoint, int yPoint) {
    return grid.get(xPoint).get(yPoint);
  }

  //todo refactor
  ArrayList<Cell> getNeighbours(int xPoint, int yPoint) {
    ArrayList<Cell> neighbouringCells = new ArrayList<>(8);
    this.tryAddNeighbouringCell(neighbouringCells, xPoint - 1, yPoint - 1);   //top left
    this.tryAddNeighbouringCell(neighbouringCells, xPoint - 1, yPoint);              //top
    this.tryAddNeighbouringCell(neighbouringCells, xPoint - 1, yPoint + 1);   //top right
    this.tryAddNeighbouringCell(neighbouringCells, xPoint, yPoint - 1);              //left
    this.tryAddNeighbouringCell(neighbouringCells, xPoint, yPoint + 1);              //right
    this.tryAddNeighbouringCell(neighbouringCells, xPoint + 1, yPoint - 1);   //bottom left
    this.tryAddNeighbouringCell(neighbouringCells, xPoint + 1, yPoint);              //bottom
    this.tryAddNeighbouringCell(neighbouringCells, xPoint + 1, yPoint + 1);   //bottom right
    return neighbouringCells;
  }

  private void tryAddNeighbouringCell(ArrayList<Cell> neighbouringCells, int xPoint, int yPoint) {
    if (!this.isPointOutOfBounds(xPoint, yPoint)) {
      neighbouringCells.add(this.getCellAt(xPoint, yPoint));
    }
  }

  boolean isPointOutOfBounds(int xPoint, int yPoint) {
    return (xPoint < 0 || xPoint > this.grid.size() - 1 )
        || (yPoint < 0 || yPoint > this.grid.get(0).size() - 1);
  }

}
