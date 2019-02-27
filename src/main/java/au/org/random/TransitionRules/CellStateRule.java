package au.org.random.TransitionRules;

import au.org.random.CellState;

public interface CellStateRule {
  CellState checkForStateChange(CellState state, long numberOfLivingNeighbours);
}
