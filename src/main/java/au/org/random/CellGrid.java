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
        line.add(new Cell());
        lengthCounter--;
      } while(lengthCounter != 0);
      grid.add(line);
      widthCounter--;
    } while(widthCounter != 0);
  }

  public Cell getCellAt(int xPosition, int yPosition) {
    return this.grid.get(yPosition).get(xPosition);
  }

  public int getWidth() {
    return this.grid.size();
  }

  public int getLength() {
    return this.grid.get(0).size();
  }
}
