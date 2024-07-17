package adventure;

public class Wall implements Tile {

	@Override
	public char getSymbol() {
		return '#';
	}

	@Override
	public boolean canMove() {
		return false;
	}
	
	@Override
	public Object moveResult() {
		return "BANG! You ran into a wall!";
	}
	
}
