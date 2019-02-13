package au.org.random;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CellGridTests {

    @Test
    public void createsGridWithDimensions() {
        CellGrid grid = new CellGrid(10,10);
        assertEquals(10, grid.getLength());
        assertEquals(10, grid.getWidth());
    }

    @Test
    public void returnsCellAtPoint() {
        CellGrid grid = new CellGrid(2,2);
        Cell cellToCheck = grid.getCellAt(0,0);
        assertSame(cellToCheck, grid.getCellAt(0,0));
        assertNotSame(cellToCheck, grid.getCellAt(0,1));
        assertNotSame(cellToCheck, grid.getCellAt(1,0));
        assertNotSame(cellToCheck, grid.getCellAt(1,1));
    }

    @Test
    public void returnsNeighbouringCells() {
        CellGrid grid = new CellGrid(3,3);
        assertTrue(grid.getNeighbours(1, 1).contains(grid.getCellAt(0, 0)));
        assertTrue(grid.getNeighbours(1, 1).contains(grid.getCellAt(0, 1)));
        assertTrue(grid.getNeighbours(1, 1).contains(grid.getCellAt(0, 2)));
        assertTrue(grid.getNeighbours(1, 1).contains(grid.getCellAt(1, 0)));
        assertTrue(grid.getNeighbours(1, 1).contains(grid.getCellAt(1, 2)));
        assertTrue(grid.getNeighbours(1, 1).contains(grid.getCellAt(2, 0)));
        assertTrue(grid.getNeighbours(1, 1).contains(grid.getCellAt(2, 1)));
        assertTrue(grid.getNeighbours(1, 1).contains(grid.getCellAt(2, 2)));
        assertFalse(grid.getNeighbours(1,1 ).contains(grid.getCellAt(1,1)));
    }

    @Test
    public void doesNotReturnNonNeighbouringCells() {
        //todo refactor out of stream
        CellGrid grid = new CellGrid(5,5);
        ArrayList<Cell> expectedNeighbouringCells = (ArrayList<Cell>) Stream.of(
            grid.getCellAt(0, 0),
            grid.getCellAt(0, 1),
            grid.getCellAt(0, 2),
            grid.getCellAt(0, 3),
            grid.getCellAt(0, 4),
            grid.getCellAt(1, 0),
            grid.getCellAt(1, 4),
            grid.getCellAt(2, 0),
            grid.getCellAt(2, 4),
            grid.getCellAt(3, 0),
            grid.getCellAt(3, 4),
            grid.getCellAt(4, 0),
            grid.getCellAt(4, 1),
            grid.getCellAt(4, 2),
            grid.getCellAt(4, 3),
            grid.getCellAt(4, 4)
        ).collect(Collectors.toList());
        expectedNeighbouringCells.forEach(cell ->
            assertFalse(grid.getNeighbours(2, 2).contains(cell))
        );
    }

    @Test void returnsTrueWhenCellIsOutOfBounds() {
        CellGrid grid = new CellGrid(2,2);
        assertTrue(grid.isPointOutOfBounds(-1,0));
        assertTrue(grid.isPointOutOfBounds(0,2));
    }

    @Test void returnsFalseWhenCellIsNotOutOfBounds() {
        CellGrid grid = new CellGrid(2,2);
        assertFalse(grid.isPointOutOfBounds(0,0));
        assertFalse(grid.isPointOutOfBounds(0,1));
        assertFalse(grid.isPointOutOfBounds(1,0));
        assertFalse(grid.isPointOutOfBounds(1,1));
    }

    @Test void returnsNeighbouringCellsWhenCellIsOnEdge() {
        CellGrid grid = new CellGrid(5,5);
        ArrayList<Cell> neighbours = grid.getNeighbours(0,2);
        assertTrue(neighbours.contains(grid.getCellAt(0, 1)));
        assertTrue(neighbours.contains(grid.getCellAt(0, 3)));
        assertTrue(neighbours.contains(grid.getCellAt(1, 1)));
        assertTrue(neighbours.contains(grid.getCellAt(1, 2)));
        assertTrue(neighbours.contains(grid.getCellAt(1, 3)));
        assertFalse(grid.getNeighbours(0,0).contains(grid.getCellAt(0,0)));
    }

    @Test void returnsNeighbouringCellsWhenCellIsACorner() {

    }
}
