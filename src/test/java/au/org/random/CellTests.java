package au.org.random;

import au.org.random.TransitionRules.Overpopulation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CellTests {

    @Test void toggleAliveOrDead() {
        Cell cell = new Cell(CellState.ALIVE);
        cell.kill();
        assertFalse(cell.isAlive());
    }

    @Test void cellShouldReturnNumberOfLivingNeighbours() {
        Cell cell = new Cell(CellState.ALIVE);
        cell.addNeighbour(new Cell(CellState.ALIVE));
        cell.addNeighbour(new Cell(CellState.ALIVE));
        cell.addNeighbour(new Cell(CellState.DEAD));
        cell.addNeighbour(new Cell(CellState.DEAD));
        assertEquals(2, cell.getNumberOfLivingNeighbours());
    }

    @Test void liveCellDiesDueToOverPopulation() {
        Cell cell = new Cell(CellState.ALIVE);
        cell.addNeighbour(new Cell(CellState.ALIVE));
        cell.addNeighbour(new Cell(CellState.ALIVE));
        cell.addNeighbour(new Cell(CellState.ALIVE));
        cell.addNeighbour(new Cell(CellState.ALIVE));
        cell.checkAndApplyStateChange(Overpopulation::checkForStateChange);
        assertFalse(cell.isAlive());
    }
}
