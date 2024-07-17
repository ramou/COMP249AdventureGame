package adventure;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import adventure.Map.DIR;

public class Game {
	private Map currentLevel;
	private StringBuffer messages = new StringBuffer();
	Game() {
		try {
			currentLevel = new Map(new File("level1.txt"), messages);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game g = new Game();
		g.play();
	}

	private void play() {
		Scanner sc = new Scanner(System.in);
		while(true) {
			currentLevel.displayBoard();
			System.out.println(messages.toString());
			messages.setLength(0);
			char move = sc.nextLine().charAt(0);
			for(DIR d: DIR.values()) {
				if(d.key == move) currentLevel.doMove(DIR.getDir(move));
			}
			
			ConsoleCleaner.clearConsole();
			
			
		}
	}

	public static class ConsoleCleaner{
		public static void clearConsole() {
	        try {
	            // Windows
	            if (System.getProperty("os.name").contains("Windows")) {
	                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	            }
	            // Unix/Linux
	            else {
	                System.out.print("\033[H\033[2J");
	                System.out.flush();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	
}
