package au.org.random;

import java.util.ArrayList;

public class CellGrid {

  private ArrayList<ArrayList<Cell>> grid;

  public CellGrid(int length, int width) {
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

  public int getWidth() {
    return this.grid.size();
  }

  public int getLength() {
    return this.grid.get(0).size();
  }

  public Cell getCellAt(int xPoint, int yPoint) {
    return grid.get(xPoint).get(yPoint);
  }

  public ArrayList<Cell> getNeighbours(int xPoint, int yPoint) {
    ArrayList<Cell> neighbouringCells = new ArrayList<>(8);
    neighbouringCells.add(this.getCellAt(xPoint - 1, yPoint - 1));
    neighbouringCells.add(this.getCellAt(xPoint - 1, yPoint));
    neighbouringCells.add(this.getCellAt(xPoint - 1, yPoint + 1));
    neighbouringCells.add(this.getCellAt(xPoint, yPoint - 1));
    neighbouringCells.add(this.getCellAt(xPoint, yPoint + 1));
    neighbouringCells.add(this.getCellAt(xPoint + 1, yPoint - 1));
    neighbouringCells.add(this.getCellAt(xPoint + 1, yPoint));
    neighbouringCells.add(this.getCellAt(xPoint + 1, yPoint + 1));
    return neighbouringCells;
  }
}
