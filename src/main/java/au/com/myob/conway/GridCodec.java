package au.com.myob.conway;

import java.util.ArrayList;
import java.util.List;

class GridCodec {

  private static final char deadCellEncoding = 'x';
  private static final String rowSeperatorEncoding = "\\|";

  /**
   * Create a grid from an encoded string. This string takes the
   * form of lines and cells. Each line, seperated by | must
   * contain the same amount of cells. A grid can be rectangular
   * or square. The encoded text must not contain characters
   * other than those specified below.
   *  -Lines are separated by a Pipe (|)
   *  -Cells are represented as dead (x) or alive (O) by a
   *   lowercase x/uppercase O letter x/O respectively.
   *
   *   Eg.        ooooo|ooxoo|ooxoo|ooxoo|ooooo|
   *
   *              ooooo|
   *              ooxoo|
   *              ooxoo|
   *              ooxoo|
   *              ooooo|
   *
   * @param encodedGrid String encoded grid
   * @return CellGrid decoded from String
   */
  static CellGrid decode(String encodedGrid) {
    String[] rows = encodedGrid.split(rowSeperatorEncoding);
    List<List<Cell>> gridRows = new ArrayList<>();
    for (String row : rows) {
      List<Cell> gridRow = new ArrayList<>();
      for(char encodedCell : row.toCharArray()) {
        CellState decodedCellState = encodedCell == deadCellEncoding
            ? CellState.DEAD
            : CellState.ALIVE;
        gridRow.add(new Cell(decodedCellState));
      }
      gridRows.add(gridRow);
    }
    return new CellGrid(gridRows);
  }
}
