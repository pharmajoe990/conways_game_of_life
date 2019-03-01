package au.com.myob.conway;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CellGridTests {

    @Test void createsGridWithDimensions() {
        CellGrid grid = new CellGrid(10,10);
        assertEquals(10, grid.getLength());
        assertEquals(10, grid.getWidth());
    }

    @Test void returnsCellAtPoint() {
        CellGrid grid = new CellGrid(2,2);
        Cell cellToCheck = grid.getCellAt(0,0);
        assertSame(cellToCheck, grid.getCellAt(0,0));
        assertNotSame(cellToCheck, grid.getCellAt(0,1));
        assertNotSame(cellToCheck, grid.getCellAt(1,0));
        assertNotSame(cellToCheck, grid.getCellAt(1,1));
    }

    @Test void returnsNeighbouringCells() {
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

    @Test void doesNotReturnNonNeighbouringCells() {
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
      /*
       *     Should contain (x) below cells
       *     - x o x -
       *     - x x x -
       *     - - - - -
       *     - - - - -
       *     - - - - -
       */
      CellGrid grid = new CellGrid(5,5);
      ArrayList<Cell> neighbours = grid.getNeighbours(0,2);
      assertTrue(neighbours.contains(grid.getCellAt(0, 1)));
      assertTrue(neighbours.contains(grid.getCellAt(0, 3)));
      assertTrue(neighbours.contains(grid.getCellAt(1, 1)));
      assertTrue(neighbours.contains(grid.getCellAt(1, 2)));
      assertTrue(neighbours.contains(grid.getCellAt(1, 3)));
      assertFalse(grid.getNeighbours(0,0).contains(grid.getCellAt(0,0)));
    }

    @Test void doesNotReturnNonNeighbouringCellsWhenCellIsOnEdge() {
        /*
         *     Should not contain (-) below cells
         *     - x o x -
         *     - x x x -
         *     - - - - -
         *     - - - - -
         *     - - - - -
         */
        CellGrid grid = new CellGrid(5,5);
        ArrayList<Cell> neighbours = grid.getNeighbours(0,2);

        assertFalse(neighbours.contains(grid.getCellAt(0, 0)));
        assertFalse(neighbours.contains(grid.getCellAt(0, 4)));
        assertFalse(neighbours.contains(grid.getCellAt(1, 0)));
        assertFalse(neighbours.contains(grid.getCellAt(1, 4)));

        assertFalse(neighbours.contains(grid.getCellAt(2, 0)));
        assertFalse(neighbours.contains(grid.getCellAt(2, 1)));
        assertFalse(neighbours.contains(grid.getCellAt(2, 2)));
        assertFalse(neighbours.contains(grid.getCellAt(2, 3)));
        assertFalse(neighbours.contains(grid.getCellAt(2, 4)));

        assertFalse(neighbours.contains(grid.getCellAt(3, 0)));
        assertFalse(neighbours.contains(grid.getCellAt(3, 1)));
        assertFalse(neighbours.contains(grid.getCellAt(3, 2)));
        assertFalse(neighbours.contains(grid.getCellAt(3, 3)));
        assertFalse(neighbours.contains(grid.getCellAt(3, 4)));

        assertFalse(neighbours.contains(grid.getCellAt(4, 0)));
        assertFalse(neighbours.contains(grid.getCellAt(4, 1)));
        assertFalse(neighbours.contains(grid.getCellAt(4, 2)));
        assertFalse(neighbours.contains(grid.getCellAt(4, 3)));
        assertFalse(neighbours.contains(grid.getCellAt(4, 4)));

        assertFalse(grid.getNeighbours(0,0).contains(grid.getCellAt(0,0)));
    }

    @Test void returnsNeighbouringCellsWhenCellIsACorner() {
        /*
         *     Should contain (x) below cells
         *     o x -
         *     x x -
         *     - - -
         */
        CellGrid grid = new CellGrid(3,3);
        ArrayList<Cell> neighbours = grid.getNeighbours(0,0);
        assertFalse(neighbours.contains(grid.getCellAt(0, 0)));

        assertTrue(grid.getNeighbours(0,0).contains(grid.getCellAt(0,1)));
        assertTrue(grid.getNeighbours(0,0).contains(grid.getCellAt(1,0)));
        assertTrue(grid.getNeighbours(0,0).contains(grid.getCellAt(1,1)));
    }

    @Test void doesNotReturnNonNeighbouringCellsWhenCellIsACorner() {
        /*
         *     Should not contain (-) below cells
         *     o x -
         *     x x -
         *     - - -
         */
        CellGrid grid = new CellGrid(3,3);
        ArrayList<Cell> neighbours = grid.getNeighbours(0,0);

        assertFalse(neighbours.contains(grid.getCellAt(0, 2)));
        assertFalse(neighbours.contains(grid.getCellAt(1, 2)));

        assertFalse(neighbours.contains(grid.getCellAt(2, 0)));
        assertFalse(neighbours.contains(grid.getCellAt(2, 1)));
        assertFalse(neighbours.contains(grid.getCellAt(2, 2)));

        assertFalse(grid.getNeighbours(0,0).contains(grid.getCellAt(0,0)));
    }

    @Test void cellGridShouldCycleWithBlinkerOscillatorPattern() {
      //todo finish implementation, migrate to comparison tools
        /* https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life#Examples_of_patterns
         *
         *   Initial State (o dead, x alive)
         *
         *     o o o o o
         *     o o O o o
         *     o o O o o
         *     o o O o o
         *     o o o o o
         *
         *     Cycle state
         *
         *     o o o o o
         *     o o o o o
         *     o O O O o
         *     o o o o o
         *     o o o o o
         */
        //Given
        CellGrid grid = new CellGrid(5,5);
        grid.reviveCellAt(1, 2);
        grid.reviveCellAt(2, 2);
        grid.reviveCellAt(3, 2);
        CellState[] rowOneState = {
            CellState.DEAD,
            CellState.DEAD,
            CellState.DEAD,
            CellState.DEAD,
            CellState.DEAD
        };
        assertTrue(grid.isRowStateEqual(0, rowOneState));
        CellState[] rowTwoState = {
            CellState.DEAD,
            CellState.DEAD,
            CellState.ALIVE,
            CellState.DEAD,
            CellState.DEAD
        };
        assertTrue(grid.isRowStateEqual(1, rowTwoState));
        CellState[] rowthreeState = {
            CellState.DEAD,
            CellState.DEAD,
            CellState.ALIVE,
            CellState.DEAD,
            CellState.DEAD
        };
        assertTrue(grid.isRowStateEqual(2, rowthreeState));
        CellState[] rowFourState = {
            CellState.DEAD,
            CellState.DEAD,
            CellState.ALIVE,
            CellState.DEAD,
            CellState.DEAD
        };
        assertTrue(grid.isRowStateEqual(3, rowFourState));
        CellState[] rowFiveState = {
            CellState.DEAD,
            CellState.DEAD,
            CellState.DEAD,
            CellState.DEAD,
            CellState.DEAD
        };
        assertTrue(grid.isRowStateEqual(4, rowFiveState));

        //When
        grid.cycle();

        //Then
        CellState[] rowOneCycledState = {
            CellState.DEAD,
            CellState.DEAD,
            CellState.DEAD,
            CellState.DEAD,
            CellState.DEAD
        };
        assertTrue(grid.isRowStateEqual(0, rowOneCycledState));
        CellState[] rowTwoCycledState = {
            CellState.DEAD,
            CellState.DEAD,
            CellState.DEAD,
            CellState.DEAD,
            CellState.DEAD
        };
        assertTrue(grid.isRowStateEqual(1, rowTwoCycledState));
        CellState[] rowThreeCycledState = {
            CellState.DEAD,
            CellState.ALIVE,
            CellState.ALIVE,
            CellState.ALIVE,
            CellState.DEAD
        };
        assertTrue(grid.isRowStateEqual(2, rowThreeCycledState));
        CellState[] rowFourCycledState = {
            CellState.DEAD,
            CellState.DEAD,
            CellState.DEAD,
            CellState.DEAD,
            CellState.DEAD
        };
        assertTrue(grid.isRowStateEqual(3, rowFourCycledState));
        CellState[] rowFiveCycledState = {
            CellState.DEAD,
            CellState.DEAD,
            CellState.DEAD,
            CellState.DEAD,
            CellState.DEAD
        };
        assertTrue(grid.isRowStateEqual(0, rowFiveCycledState));
    }

    @Test public void TwoMatchingGridStatesAreEqual() {
       CellGrid gridOne = new CellGrid(3,3);
       CellGrid gridTwo = new CellGrid(3,3);
        assertTrue(gridOne.equals(gridTwo));
    }

    @Test public void TwoNonMatchingGridStatesAreNotEqual() {
        CellGrid gridOne = new CellGrid(3,3);
        gridOne.reviveCellAt(0,1);
        CellGrid gridTwo = new CellGrid(3,3);
        gridOne.reviveCellAt(2,1);
        gridOne.reviveCellAt(2,2);
        assertFalse(gridOne.equals(gridTwo));
    }

}
