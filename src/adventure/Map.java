package adventure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Map {
	private Tile[] board;
	private int playerPos;
	private int width = 0;
	private int height = 0;
	StringBuffer messages;
	
	public Map(File mapFile, StringBuffer messages) throws IOException {
		this.messages = messages;
		//Read in all the stuff in the file
		BufferedReader br = new BufferedReader(new FileReader(mapFile));

		//Figure out how wide the map is!
		String line = br.readLine();
		while(line != null) {
			if(line.length() > width) width = line.length();
			height++;
			line = br.readLine();	
		}
		//Figure out how many tiles there are
		int boardSize = height*width;
		board = new Tile[boardSize];
		//Initialize our array to the right size
		br = new BufferedReader(new FileReader(mapFile));
		line = br.readLine();
		int currentPos = 0;

		while(line != null) {
			for(int i = 0; i < line.length(); ++i) {
				switch(line.charAt(i)) {
				case '#':
					board[currentPos] = new Wall();
					break;
				case '.':
					board[currentPos] = new Floor();
					break;
				case '<':
					board[currentPos] = new Exit();
					break;
				case '@':
					board[currentPos] = new Floor();
					playerPos = currentPos; 
				}
				++currentPos;
			}
			for(int i = 0; i < width-line.length(); i++) {
				board[currentPos] = new Wall();
				++currentPos;
			}
			line = br.readLine();	
		}
		
		//go through the board text again and 
		//  assign tiles
	}
	
	public void displayBoard() {
		for(int row = 0; row < height; row++) {
			for(int col = 0; col < width; col++) {
				if(row*width+col == playerPos) System.out.print('@');
				else System.out.print(board[row*width+col].getSymbol());
			}
			System.out.println();
		}
	}

	public void doMove(DIR move) {
		int newPos = playerPos;
		switch (move) {
		case NORTH:
			newPos = playerPos - width;
			break;
		case WEST:
			newPos = playerPos - 1;
			break;
		case SOUTH:
			newPos = playerPos + width;
			break;
		case EAST:
			newPos = playerPos + 1;
			break;
		}

		if(board[newPos].canMove()) {
			playerPos = newPos;
		}
		messages.append(board[newPos].moveResult());

	}



	public static enum DIR {
		NORTH('w'),
		EAST('d'),
		SOUTH('s'),
		WEST('a');

		static DIR getDir(char c) {
			for(DIR d: DIR.values()) {
				if(d.key == c) return d;
			}
			return null;
		}
		
		char key;
		
		
		DIR(char c) {
			this.key = c;
		}
		
	}
}

