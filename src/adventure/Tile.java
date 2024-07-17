package adventure;

public interface Tile {
	public char getSymbol();

	default public boolean canMove() {
		return true;
	}

	public Object moveResult();
}
