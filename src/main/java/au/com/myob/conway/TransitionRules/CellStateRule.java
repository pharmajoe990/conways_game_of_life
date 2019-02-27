package au.com.myob.conway.TransitionRules;

import au.com.myob.conway.CellState;

public interface CellStateRule {
  CellState checkForStateChange(CellState state, long numberOfLivingNeighbours);
}
