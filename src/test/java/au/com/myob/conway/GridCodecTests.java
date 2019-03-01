package au.com.myob.conway;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GridCodecTests {

  @Test void decodesGridOfDeadCellsFromString() {
    String encodedGrid =
        "xxx|" +
        "xxx|" +
        "xxx";
    CellGrid expectedGrid = new CellGrid(3,3);
    CellGrid actualGrid = GridCodec.decode(encodedGrid);
    assertTrue(expectedGrid.equals(actualGrid));
  }

  @Test void decodesGridFromString() {
    String encodedGrid =
        "xox|" +
        "xox|" +
        "xox";
    CellGrid expectedGrid = new CellGrid(3,3);
    CellGrid actualGrid = GridCodec.decode(encodedGrid);
    expectedGrid.reviveCellAt(0,1);
    expectedGrid.reviveCellAt(1,1);
    expectedGrid.reviveCellAt(2,1);
    assertTrue(expectedGrid.equals(actualGrid));
  }

}
