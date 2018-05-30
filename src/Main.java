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
	private static String makeBoard(){
		Terminal terminal = new Terminal();

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
    public static void main(String[] args) {
		System.out.print(makeBoard());
		}
		
    	
    
}

