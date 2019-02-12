package au.org.random;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CellGridTests {

    @Test
    public void createsGridWithDimensions() {
        CellGrid grid = new CellGrid(10,10);
        assertEquals(10, grid.getLength());
        assertEquals(10, grid.getWidth());
    }

}
