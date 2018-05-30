import com.codecool.termlib.Terminal;
import com.codecool.termlib.Direction;
import com.codecool.termlib.Attribute;
import com.codecool.termlib.Color;

import java.util.Scanner;
import java.io.InputStream;
import java.io.*;

public class Main {
    
    private static int xPosition = 0;
    private static int yPosition = 0;
    static Terminal terminal = new Terminal();
    private static int turnCounter = 1;

	private static String makeBoard(){
		

		terminal.setColor(Color.BLACK);
		terminal.setBgColor(Color.WHITE);
		String board = new String();
		for (int i = 0; i < 11; i++) {
			if (i == 0){board += "\033[48;5;231m┏";
			    for (int j = 0; j < 9; j++) {board += "━━━┳";}
			    board += "━━━┓\033[48;5;16m\n";
		    }
			else if (i == 10) {board += "\033[48;5;231m┃";
                for (int j = 0; j < 9; j++) {board += "   ┃";}
                board += "   ┃\033[48;5;16m\n";
                board += "\033[48;5;231m┗";
                for (int j = 0; j < 9; j++) {board += "━━━┻";}
                board += "━━━┛\033[48;5;16m\n";
            }
			else {board += "\033[48;5;231m┃";
                for (int j = 0; j < 9; j++) {board += "   ┃";}
                board += "   ┃\033[48;5;16m\n";
                board += "\033[48;5;231m┣";
                for (int j = 0; j < 9; j++) {board += "━━━╋";}
                board += "━━━┫\033[48;5;16m\n";	
            }
		}
		return board;
	}
		
		
    
    private static void changePosition(Direction direction) {
        if (direction == Direction.FORWARD) {
            if (xPosition != 9) {
                xPosition += 1;
                terminal.moveCursor(Direction.FORWARD, 4);
            }
        }else if (direction == Direction.BACKWARD) {
            if (xPosition != 0) {
                xPosition -= 1;
                terminal.moveCursor(Direction.BACKWARD, 4);
            }
        }else if (direction == Direction.UP) {
            if (yPosition != 0) {
                yPosition -= 1;
                terminal.moveCursor(Direction.UP, 2);
            }
        }else {
            if (yPosition != 9) {
                yPosition += 1;
                terminal.moveCursor(Direction.DOWN, 2);
            }
        }
    }
    
    
    private static void moveCursor(Direction direction) {
        if (direction == Direction.FORWARD) {
            terminal.moveCursor(Direction.FORWARD, 4);
        }else if (direction == Direction.BACKWARD) {
            terminal.moveCursor(Direction.BACKWARD, 4);
        }else if (direction == Direction.UP) {
            terminal.moveCursor(Direction.UP, 2);
        }else {
            terminal.moveCursor(Direction.DOWN, 2);
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
    
    
    public static void main(String[] args) {
        terminal.clearScreen();
        terminal.moveTo(1, 1);
		System.out.print(makeBoard());
		setUpTerminal();
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
            
            if (input == 'j') {
                System.out.print("\033[48;5;231m");
                if (turnCounter % 2 == 0) {                
                    terminal.setColor(Color.RED);
                    terminal.setChar('X');
                } else {
                    terminal.setColor(Color.BLUE);
                    terminal.setChar('O');
                }
                turnCounter++;
            }
           
            if (input == 'x') {
                terminal.resetStyle();
                terminal.clearScreen();
                terminal.moveTo(1, 1);
                restoreTerminal();           
                break;  
            }             
	    }
	}
}
