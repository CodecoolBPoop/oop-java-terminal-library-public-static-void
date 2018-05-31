import com.codecool.termlib.Terminal;
import com.codecool.termlib.Direction;
import com.codecool.termlib.Attribute;
import com.codecool.termlib.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.Scanner;
import java.io.InputStream;
import java.io.*;

public class Main {
    private static int xPosition = 0;
    private static int yPosition = 0;
    private static int curX = 2;
    private static int curY = 3; 
    private static String gameState = "on";   
    static Terminal terminal = new Terminal();
    private static int turnCounter = 1;
    static PlayingField playingField = new PlayingField();

	private static String makeBoard(){
		terminal.setColor(Color.BLACK);
		terminal.setBgColor(Color.WHITE);
		String board = new String();
		for (int i = 0; i < 11; i++) {
			if (i == 0){board += "\033[48;5;231m┏";
			    for (int j = 0; j < 9; j++) {board += "━━━┳";}
			    board += "━━━┓\033[48;5;16m\r\n";
		    }
			else if (i == 10) {board += "\033[48;5;231m┃";
                for (int j = 0; j < 9; j++) {board += "   ┃";}
                board += "   ┃\033[48;5;16m\r\n";
                board += "\033[48;5;231m┗";
                for (int j = 0; j < 9; j++) {board += "━━━┻";}
                board += "━━━┛\033[48;5;16m\r\n";
            }
			else {board += "\033[48;5;231m┃";
                for (int j = 0; j < 9; j++) {board += "   ┃";}
                board += "   ┃\033[48;5;16m\r\n";
                board += "\033[48;5;231m┣";
                for (int j = 0; j < 9; j++) {board += "━━━╋";}
                board += "━━━┫\033[48;5;16m\r\n";	
            }
		}
		return board;
	}
    
    private static void changePosition(Direction direction) {
        if (direction == Direction.FORWARD) {
            if (yPosition != 9) {
                yPosition += 1;
                curY += 4;
                terminal.moveCursor(Direction.FORWARD, 4);
            }
        }else if (direction == Direction.BACKWARD) {
            if (yPosition != 0) {
                yPosition -= 1;
                curY -= 4;
                terminal.moveCursor(Direction.BACKWARD, 4);
            }
        }else if (direction == Direction.UP) {
            if (xPosition != 0) {
                xPosition -= 1;
                curX -= 2;
                terminal.moveCursor(Direction.UP, 2);
            }
        }else {
            if (xPosition != 9) {
                xPosition += 1;
                curX += 2;
                terminal.moveCursor(Direction.DOWN, 2);
            }
        }
    }  
    
    
    private static Character tryToRead() {
        try {
            if (System.in.available() > 0) {
                return (char)System.in.read();
            }
        }
        catch (IOException e) {
            System.err.println("Error " + e.getMessage());
        }
        return '0';
    }
    
    private static void setUpTerminal() {
        try {
            String[] cmd = {"/bin/sh", "-c", "stty raw -echo </dev/tty"};
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            System.out.print("Error..");
        }
    }
    
    
    private static void restoreTerminal() {
        try {
            String[] cmdEnd = {"/bin/sh", "-c", "stty cooked echo </dev/tty"};
            Runtime.getRuntime().exec(cmdEnd);
        } catch (IOException e) {
            System.out.print("Error..");
        } 
    }
    
    
    public static void displayTurns() {
        terminal.moveTo(23, 1);
        System.out.print("\033[48;5;231m");
        if (turnCounter % 2 == 0) {
            terminal.setColor(Color.RED);                
            System.out.print("\"X\" Player's turn");
        } else {
            terminal.setColor(Color.BLUE);
            System.out.print("\"O\" Player's turn");
        }
        terminal.moveTo(curX, curY);
    }
    
    
    public static void game(){
    
        playingField.fillField();
        xPosition = 0;
        yPosition = 0;
        curX = 2;
        curY = 3;
        turnCounter = 1;
        
        terminal.clearScreen();
        terminal.moveTo(1, 1);
	    System.out.print(makeBoard());
	    setUpTerminal();
	    displayTurns();
	    terminal.moveTo(2, 3);
	
	    while (true) {
        char input = tryToRead();
        
            if (input == 'd') {
                changePosition(Direction.FORWARD);
            }
            if (input == 'a') {
                changePosition(Direction.BACKWARD);
            }
            if (input == 'w') {
                changePosition(Direction.UP);
            }
            if (input == 's') {
                changePosition(Direction.DOWN);
            }
            if (input == 'g') {
                playingField.printArray();
            }
            if (input == 'p') {
                System.out.print(turnCounter);
            }
            
            if (input == 'j') {
			    if (playingField.checkAvailability(xPosition, yPosition)) {               
				    System.out.print("\033[48;5;231m");
	                if (turnCounter % 2 == 0) {                
	                    terminal.setColor(Color.RED);
	                    terminal.setChar('X');
	                    playingField.setFieldsValue(xPosition, yPosition, "X");
	                    if (playingField.winconditionCheck(xPosition, yPosition, turnCounter, "X")) {
	                        break;
	                    }
	                } else {
	                    terminal.setColor(Color.BLUE);
	                    terminal.setChar('O');
	                    playingField.setFieldsValue(xPosition, yPosition, "O");
	                    if (playingField.winconditionCheck(xPosition, yPosition, turnCounter, "O")) {
	                        break;
	                    }
	                }
	                turnCounter++;
	                displayTurns();
	            }
           }
            if (input == 'x') {
                gameState = "off";
                terminal.resetStyle();
                terminal.clearScreen();
                terminal.moveTo(1, 1);
                restoreTerminal();           
                break;  
            }             
        }
    }
    
    
    public static void main(String[] args) {
        
        while (gameState == "on") {
            game();
            
            String playerSymbol = "";
            if (turnCounter % 2 == 0) {
                playerSymbol = "X";
            } else {
                playerSymbol = "O";
            }
            if (gameState == "on") {
                playingField.win(playerSymbol);
            }
            
            while (gameState == "on") {
                char input = tryToRead();
            
                if (input == 'y') {
                    terminal.resetStyle();
                    terminal.clearScreen();
                    terminal.moveTo(1, 1);
                    restoreTerminal();
                    break;
                }
                if (input == 'x') {
                    gameState = "off";
                    terminal.resetStyle();
                    terminal.clearScreen();
                    terminal.moveTo(1, 1);
                    restoreTerminal();           
                    break;  
                } 
            }
	    }
	}
}

