package au.com.myob.conway;

import java.util.ArrayList;
import java.util.List;

/*
TODO Refactor separate grid iteration to be generic
 */
class CellGrid {

  private List<List<Cell>> grid;

  CellGrid(int length, int width) {
    initializeGrid(length, width);
    initializeNeighbourhood();
  }

  CellGrid(List<List<Cell>> gridRows) {
    this.grid = gridRows;
    initializeNeighbourhood();
  }

  private void initializeGrid(int length, int width) {
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

  private void initializeNeighbourhood() {
    for (int i = 0; i < this.grid.size(); i++) {
      List<Cell> currentRow = this.grid.get(i);
      for (int j = 0; j < currentRow.size(); j++) {
        Cell cellAtPoint = this.grid.get(i).get(j);
        List<Cell> neighbours = getNeighbours(i, j);
        neighbours.forEach(cellAtPoint::addNeighbour);
      }
    }
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

  //todo bounds checking
  boolean isRowStateEqual(int rowNumber, CellState[] rowState) {
    List<Cell> rowToCheck = this.grid.get(rowNumber);
    for (int i = 0; i < rowToCheck.size(); i++) {
      if(!rowToCheck.get(i).doesStateEqual(rowState[i])) {
        return false;
      }
    }
    return true;
  }

  void reviveCellAt(int xPoint, int yPoint) {
    this.getCellAt(xPoint, yPoint).revive();
  }

  void cycle() {
    for (List<Cell> currentRow : this.grid) {
      for (Cell cell : currentRow) {
        cell.applyRules();
      }
    }
    for (List<Cell> currentRow : this.grid) {
      for (Cell cell : currentRow) {
        cell.applyStateChange();
      }
    }
  }

  boolean equals(CellGrid otherGrid) {
    return this.gridSizesAreEqual(otherGrid) && this.gridStatesAreEqual(otherGrid);
  }

  private boolean gridSizesAreEqual(CellGrid otherGrid) {
    if(this.getRowCount() != otherGrid.getRowCount()) {
      return false;
    }
    for (int i = 0; i < this.grid.size(); i++) {
      if(this.grid.get(i).size() != otherGrid.getRow(i).size()) return false;
    }
    return true;
  }

  private List<Cell> getRow(int rowNumber) {
    return this.grid.get(rowNumber);
  }

  private boolean gridStatesAreEqual(CellGrid otherGrid) {
    for (int i = 0; i < this.grid.size(); i++) {
      List<Cell> currentRow = this.grid.get(i);
      for (int j = 0; j < currentRow.size(); j++) {
        if(otherGrid.getCellAt(i, j).getState() != currentRow.get(j).getState()) return false;
      }
    }
    return true;
  }

  private long getRowCount() {
    return this.grid.size();
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (List<Cell> currentRow : this.grid) {
      for (Cell cell : currentRow) {
        sb.append(cell.toString());
        sb.append(" ");
      }
      sb.append("\n");
    }
    return sb.toString();
  }
}
