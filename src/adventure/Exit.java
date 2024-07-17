package adventure;

public class Exit extends Floor {

	@Override
	public char getSymbol() {
		return '<';
	}

	@Override
	public Object moveResult() {
		return "You found the exit!";
	}

}