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
    
	public boolean checkOccupation(int x, int y){
	if (playingField[x][y] == "O" || playingField[x][y] == "X"){return false;}
	return true;
	}
	
	public void win (String player){
		terminal.clearScreen();
		System.out.print(player + "player win");
	}
	
	private void rowAndColumnCheck(int modifyCordinate, int otherCordinate, String player){
	    int markCheck = 0;
		for (int i = 4; i > - 4; i--) {
			if(modifyCordinate + i > -1 && modifyCordinate + i < 10){
				if(playingField[modifyCordinate + i][otherCordinate] == player){
					markCheck ++;
					if( markCheck > 4){
						win(player);
					}
				}else{
					markCheck = 0;
					}				
			}
		}
	}
	
	
	public void winconditionCheck(int x, int y, int turnNumber, String player) {
	    checkHorizontally(x, y, turnNumber, player);
	    checkVertically(x, y, turnNumber, player);
	    checkDiagonalRight(x, y, turnNumber, player);
	    checkDiagonalLeft(x, y, turnNumber, player);
	}
	
    public void checkHorizontally(int x, int y, int turnNumber, String player) {
		if (turnNumber > 8){
		    String sign = player;
		    for (int j = 1; j < 5; j++) {   
		        if (x + j < 10) {
		            if (playingField[x+j][y] == player) {
		                sign += playingField[x+j][y];
		                if (sign.length() == 5) {
		                    win(player);
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
		                    win(player);
	                    }
		            } else {
		                break;
		            }
		        }
		    }
	    }
    }
		    
	public void checkVertically(int x, int y, int turnNumber, String player) {
		if (turnNumber > 8){	    
            String sign = player;
            for (int j = 1; j < 5; j++) {   
		        if (y + j < 10) {
		            if (playingField[x][y+j] == player) {
		                sign += playingField[x][y+j];
		                if (sign.length() == 5) {
		                    win(player);
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
		                    win(player);
	                    }
		            } else {
		                break;
		            }
		        }
		    }
		}
	}
	
	public void checkDiagonalRight(int x, int y, int turnNumber, String player) {
		if (turnNumber > 8){	    
            String sign = player;
            for (int j = 1; j < 5; j++) {   
		        if (x + j < 10 && y - j  > -1) {
		            if (playingField[x+j][y-j] == player) {
		                sign += playingField[x+j][y-j];
		                if (sign.length() == 5) {
		                    win(player);
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
		                    win(player);
	                    }
		            } else {
		                break;
		            }
		        }
		    }
		}
	}
	public void checkDiagonalLeft(int x, int y, int turnNumber, String player) {
		if (turnNumber > 8){	    
            String sign = player;
            for (int j = 1; j < 5; j++) {   
		        if (x + j < 10 && y + j  < 10) {
		            if (playingField[x+j][y+j] == player) {
		                sign += playingField[x+j][y+j];
		                if (sign.length() == 5) {
		                    win(player);
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
		                    win(player);
	                    }
		            } else {
		                break;
		            }
		        }
		    }
		}
	}
}
