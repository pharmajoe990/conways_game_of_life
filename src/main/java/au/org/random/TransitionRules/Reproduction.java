package au.org.random.TransitionRules;

import au.org.random.CellState;

public class Reproduction {
  public static CellState checkForStateChange(CellState currentState, long numberOfLiveNeighbours) {
    return currentState.equals(CellState.DEAD) && numberOfLiveNeighbours == 3
        ? CellState.ALIVE
        : CellState.DEAD;
  }
}
