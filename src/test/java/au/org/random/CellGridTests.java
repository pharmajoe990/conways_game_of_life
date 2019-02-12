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
        ArrayList<Cell> expectedNeighbouringCells = (ArrayList<Cell>) Stream.of(
          grid.getCellAt(0, 0),
          grid.getCellAt(0, 1),
          grid.getCellAt(0, 2),
          grid.getCellAt(1, 0),
          grid.getCellAt(1, 2),
          grid.getCellAt(2, 0),
          grid.getCellAt(2, 1),
          grid.getCellAt(2, 2)
        ).collect(Collectors.toList());
        expectedNeighbouringCells.forEach(cell ->
            assertTrue(grid.getNeighbours(1, 1).contains(cell))
        );
        assertFalse(expectedNeighbouringCells.contains(grid.getCellAt(1,1)));
    }

    //todo
    @Test
    public void doesNotReturnNonNeighbouringCells() {

    }

    @Test void returnsNeighbouringCellsWhenCellIsOnEdge() {

    }

    @Test void returnsNeighbouringCellsWhenCellIsACorner() {

    }
}
