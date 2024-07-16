package adventure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Map {
	private Tile[] board;
	private Tile playerPos;
	private int width = 0;
	private int height = 0;
	
	public Map(Tile[] board, 
			Tile playerPos, 
			int width, int height) {
		super();
		this.board = board;
		this.playerPos = playerPos;
		this.width = width;
		this.height = height;
	}

	public static Map loadMap(File mapFile) throws IOException {
		
		//Read in all the stuff in the file
		BufferedReader br = new BufferedReader(new FileReader(mapFile));
		
		int longestLine = 0;
		int rows = 0;
		//Figure out how wide the map is!
		String line = br.readLine();
		while(line != null) {
			if(line.length() > longestLine) longestLine = line.length();
			rows++;
			line = br.readLine();	
		}
		//Figure out how many tiles there are
		int boardSize = rows*longestLine;
		Tile[] board = new Tile[boardSize];
		//Initialize our array to the right size
		br = new BufferedReader(new FileReader(mapFile));
		line = br.readLine();
		int currentPos = 0;
		Tile playerPos = null;
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
					playerPos = board[currentPos]; 
				}
				++currentPos;
			}
			for(int i = 0; i < longestLine-line.length(); i++) {
				board[currentPos] = new Wall();
				++currentPos;
			}
			line = br.readLine();	
		}
		return new Map(board, playerPos, longestLine, rows);
		//go through the board text again and 
		//  assign tiles
	}
	
	public void displayBoard() {
		for(int row = 0; row < height; row++) {
			for(int col = 0; col < width; col++) {
				if(board[row*width+col] == playerPos) System.out.print('@');
				else System.out.print(board[row*width+col].getSymbol());
			}
			System.out.println();
		}
	}
	
}

