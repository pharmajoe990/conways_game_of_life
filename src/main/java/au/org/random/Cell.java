package au.org.random;

public class Cell {

  private CellState state;

  public Cell(CellState state) {
    this.state = state;
  }

  public boolean isAlive() {
    return this.state == CellState.ALIVE;
  }

  public void kill() {
    this.state = CellState.DEAD;
  }

}
