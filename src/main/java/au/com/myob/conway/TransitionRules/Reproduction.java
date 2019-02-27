package au.com.myob.conway.TransitionRules;

import au.com.myob.conway.CellState;

public class Reproduction {
  public static CellState checkForStateChange(CellState currentState, long numberOfLiveNeighbours) {
    return currentState.equals(CellState.DEAD) && numberOfLiveNeighbours == 3
        ? CellState.ALIVE
        : CellState.DEAD;
  }
}
