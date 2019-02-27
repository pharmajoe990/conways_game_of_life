package au.com.myob.conway.TransitionRules;

import au.com.myob.conway.CellState;

public class Underpopulation {
  public static CellState checkForStateChange(CellState currentState, long numberOfLiveNeighbours) {
    return currentState.equals(CellState.ALIVE) && numberOfLiveNeighbours < 2
        ? CellState.DEAD
        : CellState.ALIVE;
  }
}
