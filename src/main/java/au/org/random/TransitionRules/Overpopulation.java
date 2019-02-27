package au.org.random.TransitionRules;

import au.org.random.CellState;

public class Overpopulation {
  public static CellState checkForStateChange(CellState currentState, long numberOfLiveNeighbours) {
    return currentState.equals(CellState.ALIVE) && numberOfLiveNeighbours > 3
        ? CellState.DEAD
        : CellState.ALIVE;
  }
}
