package au.com.myob.conway;

import java.util.ArrayList;
import java.util.List;

class Cell {

  private CellState state;
  private CellState nextState;
  private List<Cell> neighbours;

  Cell(CellState state) {
    this.state = state;
    this.nextState = CellState.DEAD;
    this.neighbours = new ArrayList<>();
  }

  boolean isAlive() {
    return this.state == CellState.ALIVE;
  }

  void kill() {
    this.state = CellState.DEAD;
  }

  void revive() {
    this.state = CellState.ALIVE;
  }

  void addNeighbour(Cell neighbour) {
    this.neighbours.add(neighbour);
  }

  long getNumberOfLivingNeighbours() {
    return this.neighbours
        .stream()
        .filter(cell -> cell.state.equals(CellState.ALIVE))
        .count();
  }

  boolean doesStateEqual(CellState stateToCompare) {
    return this.state.equals(stateToCompare);
  }

  CellState getState() {
    return this.state;
  }

  void applyStateChange() {
    this.state = this.nextState;
  }

  void applyRules() {
    //UnderPopulation
    if(this.state.equals(CellState.ALIVE) && this.getNumberOfLivingNeighbours() < 2) {
      this.nextState = CellState.DEAD;
    }
    //Survive to next generation
    if(this.state.equals(CellState.ALIVE)
        && (this.getNumberOfLivingNeighbours() == 2 || this.getNumberOfLivingNeighbours() == 3)) {
      this.nextState = CellState.ALIVE;
    }
    //Overpopulation
    if(this.state.equals(CellState.ALIVE) && this.getNumberOfLivingNeighbours() > 3) {
      this.nextState = CellState.DEAD;
    }
    //Reproduction
    if(this.state.equals(CellState.DEAD) && this.getNumberOfLivingNeighbours() == 3) {
      this.nextState = CellState.ALIVE;
    }
  }

  public String toString() {
    return this.isAlive()
        ? "O"
        : "x";
  }
}
