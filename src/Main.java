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

    public static void main(String[] args) {
		//System.out.print("\u2503\u254B");
        //System.out.print("\u250F\u2501\u2501\u2533\u2501\u2513\n\u2503\n\u2523\u2501\u254B\u2501\u252B\n\u2517\u2501\u253B\u2501\u251B");
		String[][] alist = new String[10][10];
		String board = new String();		
		for (int k = 0; k < 10; k++) {
			Arrays.fill(alist[k], " ");
		}
		for (int i = 0; i < 11; i++) {
			if (i == 0){board += "┏";
						for (int j = 0; j < 9; j++) {board += "━━━┳";}
						board += "━━━┓\n";
						}
			else if (i == 10) {board += "┃";
							  for (int j = 0; j < 9; j++) {board += "   ┃";}
							  board += "   ┃\n";
							  board += "┗";
							  for (int j = 0; j < 9; j++) {board += "━━━┻";}
							  board += "━━━┛\n";
							  }
			else {board += "┃";
				  for (int j = 0; j < 9; j++) {board += "   ┃";}
				  board += "   ┃\n";
				  board += "┣";
				  for (int j = 0; j < 9; j++) {board += "━━━╋";}
				  board += "━━━┫\n";	
				  }
		}
		
		System.out.print(board);
    	}
    
}

