package au.org.random;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class CellTests {

    @Test
    public void toggleAliveOrDead() {
        Cell cell = new Cell(CellState.ALIVE);
        cell.kill();
        assertFalse(cell.isAlive());
    }

}
