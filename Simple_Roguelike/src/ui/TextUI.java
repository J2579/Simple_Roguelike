package ui;

import java.util.Scanner;

import model.Game;

public class TextUI {
	
	public static void printLines(int n) {
		for(int i = 0; i < n; ++i) {
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		
		Game game = Game.getInstance();
		Scanner sc = new Scanner(System.in);
		String line;
		boolean playing = true;
		
		while(playing) {
			System.out.println(game.getMapDisplayString()); //Print map
			System.out.println(game.getMapName() + ": {" + game.getPosition()[0] + "," + game.getPosition()[1] + "}"); //Print position
			System.out.println("[U]p, [D]own, [L]eft, [R]ight, [E]xit"); //Print actions list
			
			do { //Get user input
				line = sc.nextLine();
			} while(line == null || line.isEmpty() || line.isBlank()); //DeMorgan's Law
			
			char first = line.toUpperCase().charAt(0); //Take the first letter, and capitalize it.
			
			switch(first) {
				case 'U': game.moveUp();
						  break;
				case 'D': game.moveDown();
				 		  break;
				case 'L': game.moveLeft();
						  break;
				case 'R': game.moveRight();
		 		  		  break;
				case 'E': playing = false;
				          break;				
				default: continue;
			}
			
			printLines(4); //Put space between board states
		}
		
		game.saveAndShutdownComponents();
		sc.close();
	}
}
