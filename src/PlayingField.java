import com.codecool.termlib.Terminal;
import com.codecool.termlib.Direction;
import com.codecool.termlib.Attribute;
import com.codecool.termlib.Color;

import java.util.Scanner;
import java.util.Arrays;
import java.io.InputStream;
import java.io.*;

public class PlayingField {

    static Terminal terminal = new Terminal();
    
    private static String[][] playingField = new String[10][10];
    
    public void fillField() {
        for (int i = 0; i < 10; i++) {
            Arrays.fill(playingField[i], " ");
        }
    }
    
    public PlayingField() {
        fillField();
    }
    
    public void printArray() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Arrays.toString(playingField[i]));
        }
    }

    

    public void setFieldsValue(int x, int y, String value) {
        playingField[x][y] = value;
    }
    
	public boolean checkAvailability(int x, int y){
	return (playingField[x][y] == " ");
	}
	
	public void win (String player){
		terminal.clearScreen();
		System.out.print("\"" + player + "\" player win!");
		System.out.print(" If you want to start a new game, press \"y\"! Press \"x\" to exit!");
	}
	
	
	public boolean winconditionCheck(int x, int y, int turnNumber, String player) {
	    if (checkHorizontally(x, y, turnNumber, player)) {
	        return true;
	    } else if (checkVertically(x, y, turnNumber, player)) {
	        return true;
	    } else if (checkDiagonalRight(x, y, turnNumber, player)) {
	        return true;
	    } else if (checkDiagonalLeft(x, y, turnNumber, player)) {
	        return true;
	    }
	    return false;
	}
	
    public boolean checkHorizontally(int x, int y, int turnNumber, String player) {
		if (turnNumber > 8){
		    String sign = player;
		    for (int j = 1; j < 5; j++) {   
		        if (x + j < 10) {
		            if (playingField[x+j][y] == player) {
		                sign += playingField[x+j][y];
		                if (sign.length() == 5) {
		                    return true;
	                    }
		            } else {
		                break;
		            }
		        }
		    }
		    for (int j = 1; j < 5; j++) {
		        if (x - j > -1) {
		            if (playingField[x-j][y] == player) {
		                sign += playingField[x-j][y];
		                if (sign.length() == 5) {
		                    return true;
	                    }
		            } else {
		                break;
		            }
		        }
		    }
	    }
	    return false;
    }
		    
	public boolean checkVertically(int x, int y, int turnNumber, String player) {
		if (turnNumber > 8){	    
            String sign = player;
            for (int j = 1; j < 5; j++) {   
		        if (y + j < 10) {
		            if (playingField[x][y+j] == player) {
		                sign += playingField[x][y+j];
		                if (sign.length() == 5) {
		                    return true;
	                    }
		            } else {
		                break;
		            }
		        }
		    }
		    for (int j = 1; j < 5; j++) {
		        if (y - j > -1) {
		            if (playingField[x][y-j] == player) {
		                sign += playingField[x][y-j];
		                if (sign.length() == 5) {
		                    return true;
	                    }
		            } else {
		                break;
		            }
		        }
		    }
		}
	    return false;
	}
	
	public boolean checkDiagonalRight(int x, int y, int turnNumber, String player) {
		if (turnNumber > 8){	    
            String sign = player;
            for (int j = 1; j < 5; j++) {   
		        if (x + j < 10 && y - j  > -1) {
		            if (playingField[x+j][y-j] == player) {
		                sign += playingField[x+j][y-j];
		                if (sign.length() == 5) {
		                    return true;
	                    }
		            } else {
		                break;
		            }
		        }
		    }
		    for (int j = 1; j < 5; j++) {
		        if (x - j > -1 && y + j < 10) {
		            if (playingField[x-j][y+j] == player) {
		                sign += playingField[x-j][y+j];
		                if (sign.length() == 5) {
		                    return true;
	                    }
		            } else {
		                break;
		            }
		        }
		    }
		}
	    return false;
	}
	public boolean checkDiagonalLeft(int x, int y, int turnNumber, String player) {
		if (turnNumber > 8){	    
            String sign = player;
            for (int j = 1; j < 5; j++) {   
		        if (x + j < 10 && y + j  < 10) {
		            if (playingField[x+j][y+j] == player) {
		                sign += playingField[x+j][y+j];
		                if (sign.length() == 5) {
		                    return true;
	                    }
		            } else {
		                break;
		            }
		        }
		    }
		    for (int j = 1; j < 5; j++) {
		        if (x - j > -1 && y - j > -1) {
		            if (playingField[x-j][y-j] == player) {
		                sign += playingField[x-j][y-j];
		                if (sign.length() == 5) {
		                    return true;
	                    }
		            } else {
		                break;
		            }
		        }
		    }
		}
	    return false;
	}
}

