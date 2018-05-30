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
    
    
    public void setFieldsValue(int y, int x, String value) {
        playingField[x][y] = value;
    }
    
	public void win (String player){
		terminal.clearScreen();
		System.out.print(player + "player win");
	}
	
	private void rowAndColumnCheck(int modifyCordinate, int otherCordinate, String player){
	    System.out.print(modifyCordinate + " " + otherCordinate);
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
	
    public void winconditionCheck(int y, int x, int turnNumber, String player) {
		int markCheck = 0;
		if (turnNumber > 8){
			rowAndColumnCheck(x,y,player);
			rowAndColumnCheck(y,x,player);
			for (int i = 4; i > - 4; i--) {
				if((x + i > -1 && x + i < 10) && (y + i > -1 && y + i < 10)){
					if(playingField[x + i][y + i] == player){
						markCheck ++;
						if( markCheck > 4){
							win(player);
					}else{
						markCheck = 0;
						}				
					}
				}
			}
			for (int i = 4; i > - 4; i--) {
				if((x - i > -1 && x - i < 10) && (y - i > -1 && y - i < 10)){
					if(playingField[x - i][y - i] == player){
						markCheck ++;
						if( markCheck > 4){
							win(player);
					}else{
						markCheck = 0;
						}				
					}
				}
			}
		}
	}
}
