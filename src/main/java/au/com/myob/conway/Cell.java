package au.com.myob.conway;

import au.com.myob.conway.TransitionRules.CellStateRule;

import java.util.ArrayList;
import java.util.List;

class Cell {

  private CellState state;
  private List<Cell> neighbours;

  Cell(CellState state) {
    this.state = state;
    this.neighbours = new ArrayList<>();
  }

  boolean isAlive() {
    return this.state == CellState.ALIVE;
  }

  void kill() {
    this.state = CellState.DEAD;
  }

  void addNeighbour(Cell neighbour) {
    this.neighbours.add(neighbour);
  }

  void checkAndApplyStateChange(CellStateRule rule) {
    this.state = rule.checkForStateChange(this.state, this.getNumberOfLivingNeighbours());
  }

  long getNumberOfLivingNeighbours() {
    return this.neighbours
        .stream()
        .filter(cell -> cell.state.equals(CellState.ALIVE))
        .count();
  }
}
