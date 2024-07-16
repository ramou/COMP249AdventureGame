package adventure;

import java.io.File;
import java.io.IOException;

public class Game {

	Game() {
		Map map;
		try {
			map = Map.loadMap(new File("level1.txt"));
			map.displayBoard();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game g = new Game();
	}

}
