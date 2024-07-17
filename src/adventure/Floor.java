package adventure;

public class Floor implements Tile {

	@Override
	public char getSymbol() {
		return '.';
	}

	@Override
	public Object moveResult() {
		return "You successfully moved.";
	}

}
