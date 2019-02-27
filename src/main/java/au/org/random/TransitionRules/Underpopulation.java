package au.org.random.TransitionRules;

import au.org.random.CellState;

public class Underpopulation {
  public static CellState checkForStateChange(CellState currentState, long numberOfLiveNeighbours) {
    return currentState.equals(CellState.ALIVE) && numberOfLiveNeighbours < 2
        ? CellState.DEAD
        : CellState.ALIVE;
  }
}
