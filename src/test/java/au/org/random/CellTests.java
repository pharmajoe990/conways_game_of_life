package au.org.random;

import au.org.random.TransitionRules.Overpopulation;
import au.org.random.TransitionRules.Reproduction;
import au.org.random.TransitionRules.SurviveGeneration;
import au.org.random.TransitionRules.Underpopulation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test void liveCellDiesDueToUnderPopulation() {
        Cell cell = new Cell(CellState.ALIVE);
        cell.addNeighbour(new Cell(CellState.ALIVE));
        cell.checkAndApplyStateChange(Underpopulation::checkForStateChange);
        assertFalse(cell.isAlive());
    }

    @Test void liveCellWithTwoNeighboursSurvives() {
        Cell cell = new Cell(CellState.ALIVE);
        cell.addNeighbour(new Cell(CellState.ALIVE));
        cell.addNeighbour(new Cell(CellState.ALIVE));
        cell.checkAndApplyStateChange(SurviveGeneration::checkForStateChange);
        assertTrue(cell.isAlive());
    }

    @Test void liveCellWithThreeNeighboursSurvives() {
        Cell cell = new Cell(CellState.ALIVE);
        cell.addNeighbour(new Cell(CellState.ALIVE));
        cell.addNeighbour(new Cell(CellState.ALIVE));
        cell.addNeighbour(new Cell(CellState.ALIVE));
        cell.checkAndApplyStateChange(SurviveGeneration::checkForStateChange);
        assertTrue(cell.isAlive());
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

    @Test void deadCellWithThreeNeighboursBecomesAlive() {
        Cell cell = new Cell(CellState.DEAD);
        cell.addNeighbour(new Cell(CellState.ALIVE));
        cell.addNeighbour(new Cell(CellState.ALIVE));
        cell.addNeighbour(new Cell(CellState.ALIVE));
        cell.checkAndApplyStateChange(Reproduction::checkForStateChange);
        assertTrue(cell.isAlive());
    }
}
