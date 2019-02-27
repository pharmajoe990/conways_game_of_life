package au.org.random.TransitionRules;

import au.org.random.CellState;

public class SurviveGeneration {
  public static CellState checkForStateChange(CellState currentState, long numberOfLiveNeighbours) {
    return currentState.equals(CellState.ALIVE) && (numberOfLiveNeighbours == 2 || numberOfLiveNeighbours == 3)
        ? CellState.ALIVE
        : CellState.DEAD;
  }
}
